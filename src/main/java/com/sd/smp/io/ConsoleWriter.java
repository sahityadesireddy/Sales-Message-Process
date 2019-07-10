package com.sd.smp.io;

public class ConsoleWriter implements MessageWriter {

    public void writeMessageLine(String messageLine) {
        System.out.println(messageLine);
    }

    public void finish() {
        // do nothing in console
    }

}
