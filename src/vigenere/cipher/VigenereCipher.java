package vigenere.cipher;

import main.interfaces.ICriptoSystem;

import java.util.Scanner;

public class VigenereCipher implements ICriptoSystem {
    private String alphabet;
    private String key;

    public VigenereCipher() {
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        this.key = cleanPlainTextInput(scanner.next());
    }

    public String cleanPlainTextInput(String text) {
        text = text.replaceAll("\\s+", "");

        for (int x = 0; x < text.length(); x++) {
            int position = alphabet.indexOf(text.charAt(x));

            if (position == -1) {
                text = text.replace(text.charAt(x), ' ');
            }
        }
        return text.toUpperCase();
    }

    public String encryptPlaintTextInput(String plainText) {
        String cleanText = cleanPlainTextInput(plainText);

        System.out.println(cleanText);
        StringBuilder encrypted = new StringBuilder(cleanText.length());

        for (int i = 0, j = 0; i < cleanText.length(); i++) {
            char c = cleanText.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            encrypted.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }

        return encrypted.toString();
    }

    public String decryptCipherTextInput(String cipherText) {
        String cleanText = cleanPlainTextInput(cipherText);
        StringBuilder decrypted = new StringBuilder(cleanText.length());

        for (int i = 0, j = 0; i < cleanText.length(); i++) {
            char c = cleanText.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            decrypted.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }

        return decrypted.toString();
    }
}
