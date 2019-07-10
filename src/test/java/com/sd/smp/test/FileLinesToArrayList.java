package com.sd.smp.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileLinesToArrayList {
    public static void readFileLinesToArrayList(String fileName, List<String> inputList) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            inputList.add(line);
        }
        reader.close();
    }
}
