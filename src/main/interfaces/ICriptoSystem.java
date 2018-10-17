package main.interfaces;

public interface ICriptoSystem {
    String cleanPlainTextInput(String text);

    String encryptPlaintTextInput(String plainText, Integer key);

    String decryptCipherTextInput(String cipherText, Integer key);
}
