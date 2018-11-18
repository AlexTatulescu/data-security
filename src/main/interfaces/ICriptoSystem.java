package main.interfaces;

public interface ICriptoSystem {
    String cleanPlainTextInput(String text);

    String encryptPlaintTextInput(String plainText);

    String decryptCipherTextInput(String cipherText);
}
