package columnar_transpose.cipher;

import main.interfaces.ICriptoSystem;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ColumnarTransposeCipher implements ICriptoSystem {
    private String alphabet;
    private String key;

    public ColumnarTransposeCipher() {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        this.key = scanner.next();
    }

    public String cleanPlainTextInput(String text) {
        text = text.replaceAll("\\s+", "");

        for (int x = 0; x < text.length(); x++) {
            int position = alphabet.indexOf(text.charAt(x));

            if (position == -1) {
                text = text.replace(text.charAt(x), ' ');
            }
        }
        return text;
    }

    public String encryptPlaintTextInput(String plainText) {

        String text = cleanPlainTextInput(plainText);
        int[] arrange = arrangeKey(key);

        int lenKey = arrange.length;
        int lenText = text.length();

        int row = (int) Math.ceil((double) lenText / lenKey);

        char[][] grid = new char[row][lenKey];
        int z = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenKey; y++) {
                if (lenText == z) {
                    // at random alpha for trailing null grid
                    grid[x][y] = RandomAlpha();
                    z--;
                } else {
                    grid[x][y] = text.charAt(z);
                }

                z++;
            }
        }
        String enc = "";
        for (int x = 0; x < lenKey; x++) {
            for (int y = 0; y < lenKey; y++) {
                if (x == arrange[y]) {
                    for (int a = 0; a < row; a++) {
                        enc = enc + grid[a][y];
                    }
                }
            }
        }
        return enc;
    }

    public String decryptCipherTextInput(String cipherText) {
        String text = cleanPlainTextInput(cipherText);
        int[] arrange = arrangeKey(key);
        int lenKey = arrange.length;
        int lenText = text.length();

        int row = (int) Math.ceil((double) lenText / lenKey);

        String regex = "(?<=\\G.{" + row + "})";
        String[] get = text.split(regex);

        char[][] grid = new char[row][lenKey];

        for (int x = 0; x < lenKey; x++) {
            for (int y = 0; y < lenKey; y++) {
                if (arrange[x] == y) {
                    for (int z = 0; z < row; z++) {
                        grid[z][y] = get[arrange[y]].charAt(z);
                    }
                }
            }
        }

        StringBuilder dec = new StringBuilder();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < lenKey; y++) {
                dec.append(grid[x][y]);
            }
        }

        return dec.toString();
    }

    private char RandomAlpha() {
        //generate random alpha for null space
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    private int[] arrangeKey(String key) {
        //arrange position of grid
        String[] keys = key.split("");
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "")) {
                    num[y] = x;
                    break;
                }
            }
        }

        return num;
    }
}
