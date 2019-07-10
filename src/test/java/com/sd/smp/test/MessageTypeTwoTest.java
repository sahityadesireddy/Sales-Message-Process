package com.sd.smp.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sd.smp.model.Message;
import com.sd.smp.model.MessageTypeTwo;
import com.sd.smp.model.Product;

public class MessageTypeTwoTest {
    Message messageTypeTwo;
    private Map<String, Product> listProducts;

    @Before
    public void setUp() {
        messageTypeTwo = new MessageTypeTwo();
        listProducts = new LinkedHashMap<String, Product>();
    }

    @Test
    public void testUnSupportedMassageType() {
        String messageLine = "asdsds";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMessageTypeOne() {
        String messageLine = "apple at 10p";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMessageTypeThree() {
        String messageLine = "Multiply 20p apples";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testSupportedMassageType() {
        String messageLine = "20 sales of oranges at 10p each";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testMissedCurrency() {
        String messageLine = "20 sales of oranges at 10 each";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testIncorrectSentance() {
        String messageLine = "20 sales of oranges at 10p eachs";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMissingCount() {
        String messageLine = " sales of oranges at 10p each";
        boolean valid = messageTypeTwo.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testProductCount() {
        String messageLine = "20 sales of apples at 10p each";
        listProducts.put("apple", new Product());
        messageTypeTwo.process(messageLine, listProducts, 1);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 20);
        assertEquals(totalValue, 200);
    }

    @Test
    public void testMultipleProducts() {
        String messageLine = "20 sales of apples at 10p each";
        listProducts.put("apple", new Product());
        messageTypeTwo.process(messageLine, listProducts, 1);

        messageLine = "10 sales of apples at 5p each";
        messageTypeTwo.process(messageLine, listProducts, 2);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 30);
        assertEquals(totalValue, 250);
    }
}
