import java.util.*;

public class ColumnarTranspositionCipher {

    // Function to encrypt the plaintext using the Columnar Transposition technique
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

        // Read the grid column-wise based on the sorted key
        String ciphertext = "";
        for (int k = 0; k < keyLength; k++) {
            char currentKeyChar = sortedKey[k];
            for (int j = 0; j < keyLength; j++) {
                if (key.charAt(j) == currentKeyChar) {
                    for (int i = 0; i < rows; i++) {
                        ciphertext += grid[i][j];
                    }
                    break;
                }
            }
        }

        return ciphertext;
    }

    // Main method to test the Columnar Transposition Cipher
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().replaceAll("\\s", ""); // Remove spaces

        System.out.println("Enter the key (unique characters):");
        String key = scanner.nextLine();

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);

        
        System.out.println("Ciphertext: " + ciphertext);

        scanner.close();
    }
}
