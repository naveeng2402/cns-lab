import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AES {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a message to encrypt: ");
    String originalText = scanner.nextLine();

    try {
      // Generate an AES key
      SecretKey aesKey = generateAESKey();

      // Encrypt the original text
      String encryptedText = encrypt(originalText, aesKey);
      System.out.println("Encrypted Text: " + encryptedText);

      // Decrypt the encrypted text
      String decryptedText = decrypt(encryptedText, aesKey);
      System.out.println("Decrypted Text: " + decryptedText);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }

  private static SecretKey generateAESKey() throws Exception {
    // Generate an AES key
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128); // 128, 192, or 256 bits
    return keyGenerator.generateKey();
  }

  private static String encrypt(String input, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedBytes = cipher.doFinal(input.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  private static String decrypt(String input, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] encryptedBytes = Base64.getDecoder().decode(input);
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    return new String(decryptedBytes);
  }
}
