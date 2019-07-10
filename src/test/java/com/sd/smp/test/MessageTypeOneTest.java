package com.sd.smp.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sd.smp.model.Message;
import com.sd.smp.model.MessageTypeOne;
import com.sd.smp.model.Product;

public class MessageTypeOneTest {
    private Message messageTypeOne;
    private Map<String, Product> listProducts;

    @Before
    public void setUp() {
        messageTypeOne = new MessageTypeOne();
        listProducts = new LinkedHashMap<String, Product>();

    }

    @Test
    public void testUnSupportedMassageType() {
        String messageLine = "asdsds";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testSupportedMassageType() {
        String messageLine = "apple at 10p";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testIncorectCurrency() {
        String messageLine = "apple at 10";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMissingCharacters() {
        String messageLine = "apple   10p";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMultipleCurrencies() {
        String messageLine = "apple at 10pp";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testSupportedMassageTypeWithDifferantProduct() {
        String messageLine = "orange at 10p";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testUnSupportedSentance() {
        String messageLine = "bate on 10p";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMessageTypeTwo() {
        String messageLine = "20 sales of oranges at 41p each";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMessageTypeThree() {
        String messageLine = "Multiply 20p apples";
        boolean valid = messageTypeOne.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testTypeOneSalesCount() {
        String messageLine = "apple at 10p";
        listProducts.put("apple", new Product());
        messageTypeOne.process(messageLine, listProducts, 1);
        messageLine = "apple at 10p";
        messageTypeOne.process(messageLine, listProducts, 2);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 2);
        assertEquals(totalValue, 20);
    }

    @Test
    public void TestMessageTypeOne2() {
        String messageLine = "apple at 10p";
        listProducts.put("apple", new Product());

        messageTypeOne.process(messageLine, listProducts, 1);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 1);
        assertEquals(totalValue, 10);
    }

}
