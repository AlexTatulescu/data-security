package main.interfaces;

public interface IReader {
    public String readTextFromFile(String filePath);

    public String readTextFromConsole();

    public Integer readKeyFromConsole();
}
