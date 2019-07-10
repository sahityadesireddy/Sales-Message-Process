package com.sd.smp.test;

import java.util.List;

import com.sd.smp.io.MessageReader;

public class TestMessageReader implements MessageReader {
    private List<String> lines;
    private int count = 0;;

    public TestMessageReader(List<String> lines) {
        this.lines = lines;
    }

    public String getNextMessageLine() {
        return lines.get(count++);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean hasNext() {
        return (count < lines.size());
    }

    public void finish() {
        // TODO Auto-generated method stub

    }

}
