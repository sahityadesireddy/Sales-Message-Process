package com.sd.smp;

import com.sd.smp.io.ConsoleReader;
import com.sd.smp.io.ConsoleWriter;
import com.sd.smp.io.MessageReader;
import com.sd.smp.io.MessageWriter;

/**
 * 
 * This class is the manager of application components and it is responsible for define input/output methods
 * (console/file ...) define message / report processors Note: This class is singleton
 */
public class ApplicationManager {
    private MessageProcessor messageProcessor;
    private ReportProcessor reportProcessor;
    private MessageReader consoleMessageReader;
    private MessageWriter consoleMessgeWriter;

    private static ApplicationManager instance;

    private ApplicationManager() {
        consoleMessageReader = new ConsoleReader();
        consoleMessgeWriter = new ConsoleWriter();
        reportProcessor = new ReportProcessor(consoleMessgeWriter);
        messageProcessor = new MessageProcessor(consoleMessageReader, consoleMessgeWriter, reportProcessor);
    }

    // one instance singleton
    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public void start() {
        messageProcessor.startProcessing();
    }

}
