package com.sd.smp.io;

import java.util.Scanner;

public class ConsoleReader implements MessageReader {
    private Scanner in;

    public ConsoleReader() {
        in = new Scanner(System.in);
    }

    public String getNextMessageLine() {
        return in.nextLine();
    }

    public boolean hasNext() {
        return in.hasNext();
    }

    public void finish() {
        in.close();
    }
}
