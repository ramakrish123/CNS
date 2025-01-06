import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class BlowFish {
    private static final String ALGORITHM = "Blowfish";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the plaintext:");
        String plaintext = sc.nextLine();
        
        // Generate a secret key
        SecretKey secretKey = generateKey();
        System.out.println("original:"+ plaintext);

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, secretKey);
        System.out.println("Encrypted: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, secretKey);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
        return new String(decryptedBytes);
    }
}