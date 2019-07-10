package com.sd.smp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sd.smp.config.Configuration;
import com.sd.smp.io.MessageReader;
import com.sd.smp.io.MessageWriter;
import com.sd.smp.model.Message;
import com.sd.smp.model.MessageTypeOne;
import com.sd.smp.model.MessageTypeThree;
import com.sd.smp.model.MessageTypeTwo;
import com.sd.smp.model.Product;

public class MessageProcessor {
    // reports
    private ReportProcessor reportProcessor;
    // List Messages
    private List<Message> listMessages;
    // List products
    private Map<String, Product> listProducts;

    private MessageReader messageReader;
    private MessageWriter messgeWriter;
    
    public MessageProcessor(MessageReader messageReader, MessageWriter messgeWriter, ReportProcessor reportProcessor) {
        initalizeMessages();
        this.messageReader = messageReader;
        this.messgeWriter = messgeWriter;
        this.reportProcessor = reportProcessor;
        listProducts = new LinkedHashMap<String, Product>();
        this.reportProcessor.setListProducts(listProducts);
    }

    /**
     * This method initialises Messages and its type </br>
     * <b> NOTE: Don't forget to add new message types if you want to add new message type. </b>
     */
    private void initalizeMessages() {
        listMessages = new ArrayList<Message>();
        Message messageTypeOne = new MessageTypeOne();
        Message messageTypeTwo = new MessageTypeTwo();
        Message messageTypeThree = new MessageTypeThree();

        listMessages.add(messageTypeOne);
        listMessages.add(messageTypeTwo);
        listMessages.add(messageTypeThree);
    }

    public void startProcessing() {
        int messageCount = 0;
        while ((messageCount < Configuration.Max) && messageReader.hasNext()) {
            String messageLine = messageReader.getNextMessageLine();
            if (messageLine.trim().length() == 0)
                continue;
            messageCount++;
            boolean validMessage = false;
            for (Message message : listMessages) {
                if (message.isValidMessage(messageLine))
                    if (message.process(messageLine, listProducts, messageCount)) {
                        validMessage = true;
                        break;
                    }
            }
            // if not found valid message -- throw exception
            if (!validMessage)
                messgeWriter.writeMessageLine("Message Type not supported. Use as below\n apple at 10p\n"
                        + " 20 sales of apples at 10p each\n Add 20p apples");
            if (messageCount % Configuration.REPORT_INTERVAL == 0)
                reportProcessor.processIntervalReports(messageCount);
        }
        // check if reach max number of messages
        if ((messageCount == Configuration.Max)) {
            this.messgeWriter.writeMessageLine(Configuration.FINSH_MESSAGE);
            reportProcessor.processFinalReport(Configuration.Max);
        }

    }

    public ReportProcessor getReportProcessor() {
        return reportProcessor;
    }

    public void setReportProcessor(ReportProcessor reportProcessor) {
        this.reportProcessor = reportProcessor;
    }

    public MessageReader getMessageReader() {
        return messageReader;
    }

    public void setMessageReader(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    public MessageWriter getMessgeWriter() {
        return messgeWriter;
    }

    public void setMessgeWriter(MessageWriter messgeWriter) {
        this.messgeWriter = messgeWriter;
    }
}
