
package org.dp80.hadoop.ex1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Word Count example
 * 
 */
public class WordCount extends Configured implements Tool {
	
	/**
	 * Map operation
	 * 
	 * Split each line of the file to get the words and add each word to
	 * the context with '1' as value.
	 * 
	 * Ex:
	 * Input: "one line for mapper, one ..."
	 * Output: <one, 1>, <line, 1>, <for, 1>, <mapper, 1>, <one, 1>, ...
	 */
	static public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		
		final private static IntWritable	ONE		= new IntWritable(1);
		
		private Text						word	= new Text();
		
		/**
		 * @return the word
		 */
		public Text getWord() {
		
			return this.word;
		}
		
		/**
		 * @param word the word to set
		 */
		public void setWord(Text word) {
		
			this.word = word;
		}
		
		/**
		 * 
		 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
		 */
		@Override
		protected void map(LongWritable offset, Text text, Context context) throws IOException, InterruptedException {
		
			final StringTokenizer stringTokenizer = new StringTokenizer(text.toString());
			
			while ( stringTokenizer.hasMoreTokens() ) {
				
				this.word.set(stringTokenizer.nextToken());
				context.write(this.word, ONE);
			}
		}
	}
	
	/**
	 * Reducer operation
	 * 
	 * Count all occurrences of each word and add the result to the context
	 * 
	 * Ex:
	 * Input: <one, <1, 1>>, <line, 1>, <for, 1>, <mapper, 1>, ..
	 * Output: <one, <2, 1, 5>>, <line, <1, 10>>, <for, <1>>, <mapper, <>>, ...
	 */
	static public class WordCountReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
		
		private final LongWritable	totalWords	= new LongWritable();
		
		@Override
		protected void reduce(Text token, Iterable<IntWritable> counts, Context context) throws IOException, InterruptedException {
		
			//
			// count all occurrences of this word
			long sum = 0;
			for ( final IntWritable count : counts ) {
				
				sum += count.get();
			}
			
			//
			// add to context
			this.totalWords.set(sum);
			context.write(token, this.totalWords);
		}
	}
	
	/**
	 * Main app
	 */
	public static void main(String[] args) throws Exception {
	
		System.exit(ToolRunner.run(new WordCount(), args));
	}
	
	/**
	 * MapReducer runner
	 * 
	 * @see org.apache.hadoop.util.Tool#run(java.lang.String[])
	 */
	@Override
	public int run(String[] args) throws Exception {
	
		final Configuration configuration = this.getConf();
		
		final Job job = new Job(configuration, "Word Count");
		job.setJarByClass(WordCount.class);
		
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		return job.waitForCompletion(true) ? 0 : -1;
	}
}
