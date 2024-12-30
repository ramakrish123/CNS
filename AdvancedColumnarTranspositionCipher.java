import java.util.*;

public class AdvancedColumnarTranspositionCipher {

    // Function to encrypt the plaintext using the Advanced Columnar Transposition technique
    public static String encrypt(String plaintext, String key) {
        int keyLength = key.length();
        int textLength = plaintext.length();

        // Calculate the number of rows required
        int rows = (int) Math.ceil((double) textLength / keyLength);

        // Fill the grid row-wise with the plaintext
        char[][] grid = new char[rows][keyLength];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (index < textLength) {
                    grid[i][j] = plaintext.charAt(index++);
                } else {
                    grid[i][j] = 'X'; // Fill empty cells with 'X'
                }
            }
        }

        // Sort the key to determine column order
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        // Encrypt by reading columns based on the sorted key order
        String ciphertext = "";
        for (char c : sortedKey) {
            for (int col = 0; col < keyLength; col++) {
                if (key.charAt(col) == c) {
                    for (int row = 0; row < rows; row++) {
                        ciphertext += grid[row][col];
                    }
                    break;
                }
            }
        }

        return ciphertext;
    }

    // Function to decrypt the ciphertext using the Advanced Columnar Transposition technique
    public static String decrypt(String ciphertext, String key) {
        int keyLength = key.length();
        int textLength = ciphertext.length();

        // Calculate the number of rows and columns
        int rows = (int) Math.ceil((double) textLength / keyLength);
        int columns = keyLength;

        // Prepare the grid for decryption
        char[][] grid = new char[rows][columns];
        int index = 0;

        // Sort the key to determine column order
        char[] sortedKey = key.toCharArray();
        Arrays.sort(sortedKey);

        // Decrypt by placing ciphertext back into the grid according to the sorted key order
        for (char c : sortedKey) {
            for (int col = 0; col < keyLength; col++) {
                if (key.charAt(col) == c) {
                    for (int row = 0; row < rows && index < textLength; row++) {
                        grid[row][col] = ciphertext.charAt(index++);
                    }
                    break;
                }
            }
        }

        // Rebuild the plaintext from the grid
        String plaintext = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                plaintext += grid[row][col];
            }
        }

        // Remove trailing 'X' (padding)
        return plaintext.replaceAll("X+$", "");
    }

    // Main method to test the Advanced Columnar Transposition Cipher
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().replaceAll("\\s", ""); // Remove spaces

        System.out.println("Enter the key (unique characters):");
        String key = scanner.nextLine();

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
