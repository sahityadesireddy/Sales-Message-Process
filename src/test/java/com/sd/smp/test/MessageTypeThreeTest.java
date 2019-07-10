package com.sd.smp.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sd.smp.model.Message;
import com.sd.smp.model.MessageTypeThree;
import com.sd.smp.model.MessageTypeTwo;
import com.sd.smp.model.Product;

public class MessageTypeThreeTest {
    Message messageTypeThree, messageTypeTwo;
    private Map<String, Product> listProducts;

    @Before
    public void setUp() {
        messageTypeThree = new MessageTypeThree();
        messageTypeTwo = new MessageTypeTwo();

        listProducts = new LinkedHashMap<String, Product>();
    }

    @Test
    public void testUnSupportedMassageType() {
        String messageLine = "asdsds";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testValidMessageTypeOne() {
        String messageLine = "apple at 10p";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testMultiplyMessageType() {
        String messageLine = "Multiply 20p apples";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testValidMessageTypeTwo() {
        String messageLine = "20 sales of oranges at 10p each";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void TestValidMessageTypeThree5() {
        String messageLine = "Subtract 20p oranges";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testAddMessageType() {
        String messageLine = "Add 20p oranges";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, true);
    }

    @Test
    public void testMissingOperationalType() {
        String messageLine = " 20p oranges";
        boolean valid = messageTypeThree.isValidMessage(messageLine);
        assertEquals(valid, false);
    }

    @Test
    public void testProductMultiplyCount() {
        String messageLine = "10 sales of apples at 10p each";
        listProducts.put("apple", new Product());
        messageTypeTwo.process(messageLine, listProducts, 1);
        messageLine = "Multiply 20p apples";
        messageTypeThree.process(messageLine, listProducts, 2);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 10);
        assertEquals(totalValue, 2000);
    }

    @Test
    public void testProductAddCount() {
        String messageLine = "10 sales of apples at 10p each";
        listProducts.put("apple", new Product());
        messageTypeTwo.process(messageLine, listProducts, 1);
        messageLine = "Add 30p apples";
        messageTypeThree.process(messageLine, listProducts, 2);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 10);
        assertEquals(totalValue, 400);
    }

    @Test
    public void TestMessageTypeThree3() {
        String messageLine = "10 sales of apples at 10p each";
        listProducts.put("apple", new Product());
        messageTypeTwo.process(messageLine, listProducts, 1);
        messageLine = "Subtract 5p apples";
        messageTypeThree.process(messageLine, listProducts, 2);

        int numberOfSales = listProducts.get("apple").getNumberOfSales();
        int totalValue = listProducts.get("apple").getTotalValue();
        assertEquals(numberOfSales, 10);
        assertEquals(totalValue, 50);
    }
}
