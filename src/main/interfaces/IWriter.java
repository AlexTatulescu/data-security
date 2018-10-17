package main.interfaces;

public interface IWriter {
    public void writeTextInFile(String filePath, String outputText);

    public void writeTextInConsole(String outputText);
}
