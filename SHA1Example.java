import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Scanner;

public class SHA1Example {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = sc.nextLine();
        System.out.println("Original text is: " + text);
        
        try {
            // Get an instance of the SHA-1 algorithm
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            // Update the digest with the input text
            sha1.update(text.getBytes());

            // Calculate the message digest (hash value)
            byte[] hashBytes = sha1.digest();

            // Convert the hash bytes into a hexadecimal string
            String hashHex = byteArrayToHex(hashBytes);

            // Print the resulting SHA-1 hash
            System.out.println("SHA-1 Hash: " + hashHex);

            // Print the original text again after the hash
            System.out.println("Original text after hash: " + text);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 algorithm not found.");
        }
    }

    // Helper method to convert byte array to hexadecimal string
    public static String byteArrayToHex(byte[] byteArray) {
        Formatter formatter = new Formatter();
        for (byte b : byteArray) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
