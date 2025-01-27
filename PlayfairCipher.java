import java.util.Scanner;

public class PlayfairCipher {
    private char[][] keyMatrix = new char[5][5];
    private String key;

    public PlayfairCipher(String key) {
        this.key = formatKey(key);
        generateKeyMatrix();
    }

    private String formatKey(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder uniqueKey = new StringBuilder();
        for (char c : key.toCharArray()) {
            if (uniqueKey.indexOf(String.valueOf(c)) == -1) {
                uniqueKey.append(c);
            }
        }
        return uniqueKey.toString();
    }

    private void generateKeyMatrix() {
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // J is excluded
        StringBuilder fullKey = new StringBuilder(key);

        for (char c : alphabet.toCharArray()) {
            if (fullKey.indexOf(String.valueOf(c)) == -1) {
                fullKey.append(c);
            }
        }

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keyMatrix[i][j] = fullKey.charAt(index++);
            }
        }
    }

    private String formatText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c1 = text.charAt(i);
            char c2 = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (c1 == c2) {
                formatted.append(c1).append('X');
            } else {
                formatted.append(c1).append(c2);
                i++;
            }
        }

        if (formatted.length() % 2 != 0) {
            formatted.append('X');
        }

        return formatted.toString();
    }

    private String processText(String text, boolean encrypt) {
        text = formatText(text);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char c1 = text.charAt(i);
            char c2 = text.charAt(i + 1);
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);

            if (pos1[0] == pos2[0]) { // Same row
                result.append(keyMatrix[pos1[0]][(pos1[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(keyMatrix[pos2[0]][(pos2[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                result.append(keyMatrix[(pos1[0] + (encrypt ? 1 : 4)) % 5][pos1[1]]);
                result.append(keyMatrix[(pos2[0] + (encrypt ? 1 : 4)) % 5][pos2[1]]);
            } else { // Rectangle rule
                result.append(keyMatrix[pos1[0]][pos2[1]]);
                result.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return result.toString();
    }

    private int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should never reach here
    }

    public String encrypt(String plaintext) {
        return processText(plaintext, true);
    }

    public String decrypt(String ciphertext) {
        return processText(ciphertext, false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        PlayfairCipher cipher = new PlayfairCipher(key);
        String encryptedText = cipher.encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = cipher.decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
