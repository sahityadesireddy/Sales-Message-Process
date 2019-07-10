package com.sd.smp.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sd.smp.MessageProcessor;
import com.sd.smp.ReportProcessor;
import com.sd.smp.io.MessageReader;
import com.sd.smp.io.MessageWriter;


public class MessageProcessingApplicationTest {
	private MessageProcessor messageProcessor;
	private ReportProcessor reportProcessor;

	private MessageReader testMessageReader;
	private MessageWriter testMessgeWriter;
	private List<String> inputList;
	private List<String> outputList;

	private static final String resouceFolderPath = "src/test/resources/";

	@Before
	public void setUp() {
		inputList = new ArrayList<String>();
		outputList = new ArrayList<String>();
		testMessageReader = new TestMessageReader(inputList);
		testMessgeWriter = new TestMessageWriter();
		reportProcessor = new ReportProcessor(testMessgeWriter);
		messageProcessor = new MessageProcessor(testMessageReader, testMessgeWriter, reportProcessor);
	}

	@Test
	public void testIntermetentReport() throws IOException {
		inputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "input1.txt", inputList);
		outputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "output1.txt", outputList);
		messageProcessor.startProcessing();
		assertEquals(((TestMessageWriter) testMessgeWriter).getLines(), outputList);
	}

	@Test
	public void testUnSupportedMessagetype() {
		inputList.clear();
		inputList.add("applesss10p");
		inputList.add("20 sales of oranges at 41p each");
		inputList.add("Multiply 20p apples");
		inputList.add("orange at 10p");
		inputList.add("20 sales of oranges at 10p each");
		inputList.add("Multiply 20p apples");
		inputList.add("Subtract 20p oranges");
		inputList.add("Subtract 20p oranges");
		inputList.add("Multiply 20p apples");
		inputList.add("Add 20p apples");
		outputList.clear();
		outputList.add("Message Type not supported. Use as below\n apple at 10p\n 20 sales of apples at 10p each\n"
                + " Add 20p apples");
		outputList.add("SaleAfter: 10");
		outputList.add("Product: orange  Value: 20 Sales:41");
		outputList.add("Product: apple  Value: 0 Sales:0");
		messageProcessor.startProcessing();
		assertEquals(((TestMessageWriter) testMessgeWriter).getLines(), outputList);
	}

	@Test
	public void testFinalReport() throws IOException {
		inputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "input2.txt", inputList);
		outputList.clear();
		FileLinesToArrayList.readFileLinesToArrayList(resouceFolderPath + "output2.txt", outputList);
		messageProcessor.startProcessing();
		assertEquals(((TestMessageWriter) testMessgeWriter).getLines(), outputList);
	}

}
