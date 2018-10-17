import caesar.cipher.CaesarCipher;
import caesar.cipher.CaesarReader;
import caesar.cipher.CaesarWriter;

public class CipherTester {
    public static void main(String[] args) {
        CaesarReader caesarReader = new CaesarReader();
        CaesarWriter caesarWriter = new CaesarWriter();
        CaesarCipher cipherTester = new CaesarCipher();

        String data = caesarReader.readTextFromFile("C:\\My Data\\CaesarTestText.txt");
        Integer key = caesarReader.readKeyFromConsole();

        String encryptedText = cipherTester.encryptPlaintTextInput(data, key);
        String decryptedText = cipherTester.decryptCipherTextInput(encryptedText, key);

        caesarWriter.writeTextInConsole("The encrypted text is: " + encryptedText);
        caesarWriter.writeTextInConsole("The decrypted text is: " + decryptedText);
    }
}
