package playfair.cipher;

import main.interfaces.ICriptoSystem;

import java.awt.*;
import java.util.Scanner;

public class PlayfairCipher implements ICriptoSystem {

    private String alphabet;
    private String key;
    private Boolean changeJtoI;
    private char[][] table;
    private Point[] positions;

    public PlayfairCipher() {
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Replace J with I? true/false: ");
        this.changeJtoI = scanner.nextBoolean();

        System.out.println("Please insert the key for the cipher:");
        this.key = scanner.next();

        this.table = createTable();
    }

    public String cleanPlainTextInput(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        return changeJtoI ? text.replace("J", "I") : text.replace("Q", "");
    }

    public String encryptPlaintTextInput(String plainText) {

        StringBuilder encrypted = new StringBuilder(cleanPlainTextInput(plainText));

        for (int i = 0; i < encrypted.length(); i += 2) {

            if (i == encrypted.length() - 1)
                encrypted.append(encrypted.length() % 2 == 1 ? 'X' : "");

            else if (encrypted.charAt(i) == encrypted.charAt(i + 1))
                encrypted.insert(i + 1, 'X');
        }
        return codec(encrypted, 1);
    }

    public String decryptCipherTextInput(String cipherText) {

        return codec(new StringBuilder(cleanPlainTextInput(cipherText)), 4);
    }

    private char[][] createTable() {
        char[][] charTable = new char[5][5];
        this.positions = new Point[26];

        String cleanText = cleanPlainTextInput(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        int len = cleanText.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = cleanText.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }

        return charTable;
    }

    private String codec(StringBuilder text, int direction) {
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;

            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;

            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            text.setCharAt(i, table[row1][col1]);
            text.setCharAt(i + 1, table[row2][col2]);
        }
        return text.toString();
    }
}
