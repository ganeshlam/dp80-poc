
package org.dp80.hadoop.ex1;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.dp80.hadoop.ex1.WordCount;
import org.junit.Before;
import org.junit.Test;

public class WordCountMrTest {
	
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable>	mapReduceDriver;
	
	MapDriver<LongWritable, Text, Text, IntWritable>							mapDriver;
	
	ReduceDriver<Text, IntWritable, Text, LongWritable>							reduceDriver;
	
	@Test
	public void map() {
	
		final String data = "cat cat dog";
		this.mapDriver.withInput(new LongWritable(1), new Text(data));
		
		this.mapDriver.withOutput(new Text("cat"), new IntWritable(1));
		this.mapDriver.withOutput(new Text("cat"), new IntWritable(1));
		this.mapDriver.withOutput(new Text("dog"), new IntWritable(1));
		this.mapDriver.runTest();
	}
	
	@Test
	public void mapReduce() {
	
		this.mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat dog"));
		this.mapReduceDriver.addOutput(new Text("cat"), new LongWritable(2));
		this.mapReduceDriver.addOutput(new Text("dog"), new LongWritable(1));
		this.mapReduceDriver.runTest();
	}
	
	@Test
	public void reducer() {
	
		final List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		this.reduceDriver.withInput(new Text("cat"), values);
		this.reduceDriver.withOutput(new Text("cat"), new LongWritable(2));
		this.reduceDriver.runTest();
	}
	
	@Before
	public void setUp() {
	
		final WordCount.WordCountMapper mapper = new WordCount.WordCountMapper();
		final WordCount.WordCountReducer reducer = new WordCount.WordCountReducer();
		this.mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		this.mapDriver.setMapper(mapper);
		this.reduceDriver = new ReduceDriver<Text, IntWritable, Text, LongWritable>();
		this.reduceDriver.setReducer(reducer);
		this.mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable>();
		this.mapReduceDriver.setMapper(mapper);
		this.mapReduceDriver.setReducer(reducer);
	}
}
