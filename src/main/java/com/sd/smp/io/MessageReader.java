package com.sd.smp.io;

/**
 * 
 * This interface This interface defines input method. If you want to add your own input method, just implement this
 */
public interface MessageReader {

    public String getNextMessageLine();

    /**
     * check if message reader has another lines or not
     */
    public boolean hasNext();

    /**
     * This method to be called after finishing reading messages.
     */
    public void finish();
}
