package trifid.cipher;

import main.interfaces.ICriptoSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class TrifidCipher implements ICriptoSystem {

    private String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String key;
    private char[][][] cube;

    public TrifidCipher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        this.key = scanner.next();
        this.cube = initializeCipher(key);
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
        String cleanText = cleanPlainTextInput(plainText);
        int[][] pCipher = getpCipher(cube, cleanText);

        return getCipher(cube, pCipher);
    }

    public String decryptCipherTextInput(String cipherText) {
        String cleanText = cleanPlainTextInput(cipherText);
        int[][] pDeCipher = getpDeCipher(cube, cleanText);

        return getMsg(cube, pDeCipher);
    }

    private String getCipher(char[][][] cube, int[][] pCipher) {
        StringBuilder stb = new StringBuilder();

        int count = 0;
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < pCipher.length; j++) {
                line.add(pCipher[j][i]);
            }
        }

        ArrayList<Integer> indices = new ArrayList<>();
        int x, y, z;
        for (int k = 0; k < line.size(); k++) {
            if (count < 3) {
                indices.add(line.get(k));
                count++;
            } else {
                x = indices.remove(0);
                y = indices.remove(0);
                z = indices.remove(0);
                stb.append(cube[x][y][z]);
                k--;
                count = 0;
            }
        }
        x = indices.remove(0);
        y = indices.remove(0);
        z = indices.remove(0);
        stb.append(cube[x][y][z]);

        return stb.toString();
    }

    private String getMsg(char[][][] cube, int[][] pDeCipher) {
        StringBuilder stb = new StringBuilder();

        int count = 0;
        ArrayList<Integer> line = new ArrayList<>();
        for (int i = 0; i < pDeCipher.length; i++) {
            for (int j = 0; j < 3; j++) {
                line.add(pDeCipher[i][j]);
            }
        }

        ArrayList<Integer> indices = new ArrayList<>();
        for (int k = 0; k < line.size(); k++) {
            if (count < 3) {
                indices.add(line.get(k));
                count++;
            } else {
                int x = indices.remove(0);
                int y = indices.remove(0);
                int z = indices.remove(0);
                stb.append(cube[x][y][z]);
                k--;
                count = 0;
            }
        }
        int x = indices.remove(0);
        int y = indices.remove(0);
        int z = indices.remove(0);
        stb.append(cube[x][y][z]);

        return stb.toString();
    }

    private int[][] getpDeCipher(char[][][] cube, String msg) {
        int[][] pDeCipher = new int[msg.length()][3];
        int i, j, k, m;
        int foundAt[] = {-1, -1, -1};
        ArrayList<Integer> line = new ArrayList<>();

        for (m = 0; m < msg.length(); m++) {
            i = 0;
            while (i < 5) {
                j = 0;
                while (j < 5) {
                    k = 0;
                    while (k < 5) {
                        if (msg.charAt(m) == cube[i][j][k]) {
                            foundAt[0] = i;
                            foundAt[1] = j;
                            foundAt[2] = k;
                        }
                        k++;
                    }
                    j++;
                }
                i++;
            }
            line.add(foundAt[0]);
            line.add(foundAt[1]);
            line.add(foundAt[2]);
        }
        for (int v = 0; v < 3; v++) {
            for (int w = 0; w < msg.length(); w++) {
                pDeCipher[w][v] = line.remove(0);
            }
        }
        return pDeCipher;
    }

    private int[][] getpCipher(char[][][] cube, String msg) {
        int[][] pCipher = new int[msg.length()][3];
        int i = 0, j = 0, k = 0, m = 0;
        int foundAt[] = {-1, -1, -1};

        for (m = 0; m < msg.length(); m++) {
            i = 0;
            while (i < 5) {
                j = 0;
                while (j < 5) {
                    k = 0;
                    while (k < 5) {
                        if (msg.charAt(m) == cube[i][j][k]) {
                            foundAt[0] = i;
                            foundAt[1] = j;
                            foundAt[2] = k;
                        }
                        k++;
                    }
                    j++;
                }
                i++;
            }

            int x = 0;
            for (int c = 0; c < 3; c++) {
                pCipher[m][c] = foundAt[x];
                x++;
            }
            x = 0;
        }

        return pCipher;
    }

    private char[][][] initializeCipher(String key) {
        char[][][] cube = new char[5][5][5];
        int u = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    cube[i][j][k] = key.charAt(u);
                    u++;
                }
            }
        }

        return cube;
    }
}