package main.interfaces;

public interface IReader {
    String readTextFromFile(String filePath);

    String readTextFromConsole();

    Integer getUserSelectedCypher();
}
