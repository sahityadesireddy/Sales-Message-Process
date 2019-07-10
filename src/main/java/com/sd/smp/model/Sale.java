package com.sd.smp.model;

public class Sale {
    private int value;

    public Sale() {

    }

    public Sale(int value) {
        this.setValue(value);
    }

    public Sale(Sale sale) {
        this.value = sale.getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int changeValue) {
        this.value += changeValue;
    }

    /**
     * subtract sale value. Assuming sale value cannot be negative
     * 
     * @param changeValue
     */
    public void subValue(int changeValue) {
        this.value = (this.value - changeValue) < 0 ? 0 : this.value - changeValue;
    }

    public void multValue(int changeValue) {
        this.value *= changeValue;
    }

    public void dividValue(int changeValue) throws Exception {
        if (changeValue == 0)
            throw new Exception("Error Divid By Zero");
        this.value /= changeValue;
    }
}
