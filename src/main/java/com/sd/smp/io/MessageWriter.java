package com.sd.smp.io;

/**
 * This interface This interface defines output method. If you want to add your own output method, just implement this
 */
public interface MessageWriter {
    
    public void writeMessageLine(String messageLine);

    /**
     * This method to be called after finishing writing messages. </br>
     */
    public void finish();

}
