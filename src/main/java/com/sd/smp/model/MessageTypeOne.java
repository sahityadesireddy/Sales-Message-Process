package com.sd.smp.model;

import java.util.List;
import java.util.Map;

import com.sd.smp.utils.Regex;

/**
 * 
 * This type of messages can add only one sale to product
 */
public class MessageTypeOne implements Message {
    // regex for essage format validating
    private final String MessageTypeOne_REGEX = "(\\w+)\\sat\\s(\\d+)p$";

    public boolean isValidMessage(String messageLine) {
        return Regex.isRegexMatch(MessageTypeOne_REGEX, messageLine);
    }

    public boolean process(String Message, Map<String, Product> ListProducts, int messageCount) {
        List<String> tokens = Regex.getRegexTokens(this.MessageTypeOne_REGEX, Message);
        String productName = tokens.get(1);
        // if product not found before add it to map
        if (!ListProducts.containsKey(productName))
            ListProducts.put(productName, new Product());
        Product product = ListProducts.get(productName);

        int saleValue = Integer.parseInt(tokens.get(2));
        product.addSaleToProduct(new Sale(saleValue));

        String historyRecordLine = "At message#" + messageCount + " Adding one sale to: " + productName
                + " with value: " + saleValue;
        product.addHistoryRecord(historyRecordLine);

        return true;
    }

}
