import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DES {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a message to encrypt: ");
        String originalText = scanner.nextLine();

        // Generate a DES key
        SecretKey desKey = generateDESKey();

        // Encrypt the original text
        String encryptedText = encrypt(originalText, desKey);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the encrypted text
        String decryptedText = decrypt(encryptedText, desKey);
        System.out.println("Decrypted Text: " + decryptedText);
        
        scanner.close();
    }

    private static SecretKey generateDESKey() throws Exception {
        // Generate a DES key using DESKeySpec
        DESKeySpec desKeySpec = new DESKeySpec("mysecret".getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(desKeySpec);
    }

    private static String encrypt(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedBytes = Base64.getDecoder().decode(input);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
