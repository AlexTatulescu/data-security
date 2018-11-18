package main.interfaces;

public interface IWriter {
    void writeTextInFile(String filePath, String outputText);

    void writeTextInConsole(String outputText);
}
