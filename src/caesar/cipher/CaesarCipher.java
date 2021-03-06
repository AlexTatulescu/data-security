package caesar.cipher;

import main.interfaces.ICriptoSystem;

import java.util.Scanner;

public class CaesarCipher implements ICriptoSystem {

    private String alphabet;
    private Integer key;

    public CaesarCipher() {
        this.alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the key for the cipher:");
        this.key = scanner.nextInt();
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

        StringBuilder encrypted = new StringBuilder(cleanText.length());

        for (int i = 0; i < cleanText.length(); i++) {
            int position = alphabet.indexOf(cleanText.charAt(i));

            if ((position + key) < alphabet.length()) {
                encrypted.append(alphabet.charAt(position + key));
            } else {
                encrypted.append(alphabet.charAt((position + key) - alphabet.length()));
            }
        }
        return encrypted.toString();
    }

    public String decryptCipherTextInput(String cipherText) {
        String cleanText = cleanPlainTextInput(cipherText);

        StringBuilder decrypted = new StringBuilder(cleanText.length());

        for (int i = 0; i < cleanText.length(); i++) {
            int position = alphabet.indexOf(cleanText.charAt(i));

            if ((position - key) < 0) {
                decrypted.append(alphabet.charAt((position - key) + alphabet.length()));
            } else {
                decrypted.append(alphabet.charAt(position - key));
            }
        }
        return decrypted.toString();
    }
}
