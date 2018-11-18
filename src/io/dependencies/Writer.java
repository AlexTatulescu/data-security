package io.dependencies;

import main.interfaces.IWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer implements IWriter {
    public void writeTextInFile(String filePath, String outputText) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(outputText);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTextInConsole(String outputText) {
        System.out.println(outputText);
    }
}
