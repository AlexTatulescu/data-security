package bifid.cipher;

import main.interfaces.ICriptoSystem;

public class BifidCipher implements ICriptoSystem {
    private String alphabet;
    private BifidCoder bifidCoder;
    private BifidDecoder bifidDecoder;

    public BifidCipher() {
        alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        PolybiusSquare polybiusSquare = new PolybiusSquare();
        bifidCoder = new BifidCoder(polybiusSquare);
        bifidDecoder = new BifidDecoder(polybiusSquare);
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
        System.out.println(cleanText);

        return bifidDecoder.getDecodedString(cleanText);
    }

    public String decryptCipherTextInput(String cipherText) {
        String cleanText = cleanPlainTextInput(cipherText);

        return bifidCoder.getCodedString(cleanText);
    }
}
