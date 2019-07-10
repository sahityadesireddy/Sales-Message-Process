package com.sd.smp.model;

import java.util.Map;

import com.sd.smp.io.MessageWriter;

/**
 * This interface defines Report. If you want to add new report type you must implement these methods
 */
public interface Report {
    /**
     * 
     * @param messageWriter: output method
     */
    public void setOutputMessageWriter(MessageWriter messageWriter);

    /**
     * 
     * @param listProducts
     * @param messageCount: indicator for (when the report is being generated ex: at message 5)
     */
    public void process(Map<String, Product> listProducts, int messageCount);
}
