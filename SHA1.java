import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a string: ");
    String input = scanner.nextLine();

    String sha1Hash = calculateSHA1(input);
    System.out.println("SHA-1 Hash: " + sha1Hash);
    scanner.close();
  }

  private static String calculateSHA1(String input) {
    try {
      MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
      byte[] hashBytes = sha1.digest(input.getBytes());

      // Convert the byte array to a hexadecimal string
      StringBuilder hexStringBuilder = new StringBuilder();
      for (byte b : hashBytes) {
        hexStringBuilder.append(String.format("%02x", b));
      }

      return hexStringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      // Handle the exception
      e.printStackTrace();
      return null;
    }
  }
}
