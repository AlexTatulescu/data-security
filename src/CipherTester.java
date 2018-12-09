import autokey.cipher.AutokeyCipher;
import caesar.cipher.CaesarCipher;
import foursquares.cipher.FourSquaresCipher;
import io.dependencies.Reader;
import io.dependencies.Writer;
import playfair.cipher.PlayfairCipher;
import vigenere.cipher.VigenereCipher;

public class CipherTester {
    public static void main(String[] args) {
        Reader reader = new Reader();
        Writer writer = new Writer();

        String data = reader.readTextFromFile("C:\\My Data\\Workspace\\Java Workspace\\DataSecurity\\src\\TextInput.txt");

        String encryptedText = null;
        String decryptedText = null;
        while (true) {
            switch (reader.getUserSelectedCypher()) {
                case 1:
                    CaesarCipher caesarCipher = new CaesarCipher();
                    encryptedText = caesarCipher.encryptPlaintTextInput(data);
                    decryptedText = caesarCipher.decryptCipherTextInput(encryptedText);
                    break;
                case 2:
                    VigenereCipher vigenereCipher = new VigenereCipher();
                    encryptedText = vigenereCipher.encryptPlaintTextInput(data);
                    decryptedText = vigenereCipher.decryptCipherTextInput(encryptedText);
                    break;
                case 3:
                    PlayfairCipher playfairCipher = new PlayfairCipher();
                    encryptedText = playfairCipher.encryptPlaintTextInput(data);
                    decryptedText = playfairCipher.decryptCipherTextInput(encryptedText);
                    break;
                case 4:
                    AutokeyCipher autokeyCipher = new AutokeyCipher();
                    encryptedText = autokeyCipher.encryptPlaintTextInput(data);
                    decryptedText = autokeyCipher.decryptCipherTextInput(encryptedText);
                    break;
                case 5:
                    FourSquaresCipher fourSquaresCipher = new FourSquaresCipher();
                    encryptedText = fourSquaresCipher.encryptPlaintTextInput(data);
                    decryptedText = fourSquaresCipher.decryptCipherTextInput(encryptedText);
                    break;

                default:
                    System.out.println("Please select a valid option!");
            }

            writer.writeTextInConsole("The encrypted text is: " + encryptedText);
            writer.writeTextInConsole("The decrypted text is: " + decryptedText);
        }
    }
}
