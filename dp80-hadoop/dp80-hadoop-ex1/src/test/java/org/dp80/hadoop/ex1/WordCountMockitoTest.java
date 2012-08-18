
package org.dp80.hadoop.ex1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.dp80.hadoop.ex1.WordCount;
import org.dp80.hadoop.ex1.WordCount.WordCountMapper;
import org.dp80.hadoop.ex1.WordCount.WordCountReducer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * References:
 * 
 * - http://java.dzone.com/articles/effective-testing-strategies
 */
@RunWith(MockitoJUnitRunner.class)
public class WordCountMockitoTest {
	
	@InjectMocks
	WordCountMapper		wordCountMapper		= new WordCountMapper();
	
	@InjectMocks
	WordCountReducer	wordCountReducer	= new WordCountReducer();
	
	@Mock
	@SuppressWarnings("rawtypes")
	Mapper.Context		mapperContextMock	= mock(WordCountMapper.Context.class);
	
	@Mock
	@SuppressWarnings("rawtypes")
	Reducer.Context		reducerContextMock	= mock(WordCountReducer.Context.class);
	
	@Mock
	Text				textMock			= mock(Text.class);
	
	/**
	 * Test driver
	 */
	@Test
	@Ignore
	public void driver() throws Exception {
	
		final Configuration conf = new Configuration();
		conf.set("fs.default.name", "file:///");
		conf.set("mapred.job.tracker", "local");
		
		final Path input = new Path("src/test/resources/input");
		final Path output = new Path("target/output");
		
		final FileSystem fs = FileSystem.getLocal(conf);
		fs.delete(output, true);
		
		final WordCount wordCount = new WordCount();
		wordCount.setConf(conf);
		
		final int exitCode = wordCount.run(new String[] { input.toString(), output.toString() });
		assertEquals(0, exitCode);
		
		this.validateOuput(fs);
		
	}
	
	/**
	 * Test map operation
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void map() throws Exception {
	
		final Text value = new Text("This is a... This is");
		
		this.wordCountMapper.map(null, value, this.mapperContextMock);
		
		final InOrder inOrder = inOrder(this.textMock, this.mapperContextMock);
		
		this.verifyInOrder(inOrder, "This");
		this.verifyInOrder(inOrder, "is");
		this.verifyInOrder(inOrder, "a...");
		this.verifyInOrder(inOrder, "This");
		this.verifyInOrder(inOrder, "is");
	}
	
	/**
	 * Test reduce operation
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void reducer() throws Exception {
	
		final Iterable<IntWritable> values = Arrays.asList(new IntWritable(1), new IntWritable(4), new IntWritable(7));
		
		this.wordCountReducer.reduce(new Text("foo"), values, this.reducerContextMock);
		
		verify(this.reducerContextMock).write(new Text("foo"), new LongWritable(12));
	}
	
	/**
	 * 
	 */
	private void validateOuput(FileSystem fs) throws Exception {
	
		InputStream in = null;
		
		try {
			in = fs.open(new Path("target/output/part-r-00000"));
			
			final BufferedReader br = new BufferedReader(new InputStreamReader(in));
			assertEquals("five\t1", br.readLine());
			assertEquals("four\t1", br.readLine());
			assertEquals("one\t3", br.readLine());
			assertEquals("six\t1", br.readLine());
			assertEquals("three\t1", br.readLine());
			assertEquals("two\t2", br.readLine());
			
		} finally {
			IOUtils.closeStream(in);
		}
	}
	
	/**
	 * verify inOrder
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void verifyInOrder(InOrder inOrder, String word) throws Exception {
	
		final IntWritable one = new IntWritable(1);
		
		inOrder.verify(this.textMock).set(eq(word));
		inOrder.verify(this.mapperContextMock).write(eq(this.textMock), eq(one));
	}
}
