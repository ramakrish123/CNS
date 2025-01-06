import java.util.Arrays;

public class HillCipherExample {
    public static void main(String[] args) {
        // Define the key matrix
        int[][] keyMatrix = {
                {17, 17, 5},
                {21, 18, 21},
                {2, 2, 19}
        };

        // Plaintext: PAY
        String plaintext = "PAY";
        int[] plaintextVector = toNumericalArray(plaintext);
        System.out.println("original pliantext :"+ plaintext);

        // Encrypt the plaintext
        int[] ciphertextVector = encrypt(keyMatrix, plaintextVector);
        String ciphertext = toText(ciphertextVector);

        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        int[][] inverseKeyMatrix = {
                {4, 9, 15},
                {15, 17, 6},
                {24, 0, 17}
        }; // Precomputed inverse key matrix mod 26
        int[] decryptedVector = encrypt(inverseKeyMatrix, ciphertextVector);
        String decryptedText = toText(decryptedVector);

        System.out.println("Decrypted Text: " + decryptedText);
    }

    // Convert a string to numerical representation (A=0, B=1, ..., Z=25)
    private static int[] toNumericalArray(String text) {
        int[] result = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            result[i] = text.charAt(i) - 'A';
        }
        return result;
    }

    // Convert numerical representation back to text
    private static String toText(int[] numbers) {
        StringBuilder result = new StringBuilder();
        for (int num : numbers) {
            result.append((char) ('A' + (num % 26)));
        }
        return result.toString();
    }

    // Perform matrix multiplication and mod 26
    private static int[] encrypt(int[][] matrix, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < vector.length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum % 26; // Take mod 26
        }
        return result;
    }
}
