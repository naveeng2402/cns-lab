import java.security.*;
import java.util.Base64;

public class DigitalSignature {

    public static void main(String[] args) {
        try {
            // Create a key pair for the signer
            KeyPair keyPair = generateKeyPair();

            // Create a digital signature for the message
            String message = "Hello, Digital Signature!";
            byte[] signature = sign(message, keyPair.getPrivate());

            System.out.println("Original Message: " + message);
            System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(signature));

            // Verify the digital signature
            boolean isVerified = verify(message, signature, keyPair.getPublic());
            System.out.println("Signature Verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    private static boolean verify(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        return verifier.verify(signature);
    }
}
