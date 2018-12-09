package io.dependencies;

import main.interfaces.IReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Reader implements IReader {
    public String readTextFromFile(String filePath) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println("Please wait till the text is read from the specified file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return text;
    }

    public String readTextFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the text for the cipher to encrypt:");
        return scanner.nextLine();
    }

    public Integer getUserSelectedCypher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select what Cipher you want to use");
        System.out.println("Select 1 for Caesar Cipher");
        System.out.println("Select 2 for Vigenere Cipher");
        System.out.println("Select 3 for Playfair Cipher");
        System.out.println("Select 4 for Autokey Cipher");
        System.out.println("Select 5 for Foursquares Cipher");
        return scanner.nextInt();
    }
}
