package caesar.cipher;

import main.interfaces.IReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CaesarReader implements IReader {
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

    public Integer readKeyFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        return scanner.nextInt();
    }
}
