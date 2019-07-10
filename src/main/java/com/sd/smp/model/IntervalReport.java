package com.sd.smp.model;

import java.util.Map;
import java.util.Map.Entry;

import com.sd.smp.io.MessageWriter;

public class IntervalReport implements Report {
    private MessageWriter messageWriter;

    public void setOutputMessageWriter(MessageWriter messageWriter) {
        this.messageWriter = messageWriter;

    }

    public void process(Map<String, Product> listProducts, int messageCount) {
        String logId = "SaleAfter: " + messageCount;
        messageWriter.writeMessageLine(logId);

        for (Entry<String, Product> product : listProducts.entrySet()) {
            String Line = "Product: " + product.getKey() + "  Value: " + product.getValue().getTotalValue() + " Sales:"
                    + product.getValue().getNumberOfSales();
            messageWriter.writeMessageLine(Line);
        }
    }

}
