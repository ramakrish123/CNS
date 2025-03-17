import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;

public class SimpleRSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 8; // Small bit length for simplicity

    public SimpleRSA() {
        Random rand = new Random();

        // Generate two random prime numbers p and q
        p = BigInteger.probablePrime(bitLength, rand);
        q = BigInteger.probablePrime(bitLength, rand);

        // Compute n = p * q
        n = p.multiply(q);

        // Compute phi(n) = (p - 1) * (q - 1)
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose e (Public Key exponent) such that 1 < e < phi and gcd(e, phi) = 1
        e = BigInteger.probablePrime(bitLength / 2, rand);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        // Compute d (Private Key exponent) such that (d * e) % phi = 1
        d = e.modInverse(phi);
    }

    // Encryption: c = (m^e) % n
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decryption: m = (c^d) % n
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }

    public void printKeys() {
        System.out.println("Generated Prime Numbers: p = " + p + ", q = " + q);
        System.out.println("Public Key: (e = " + e + ", n = " + n + ")");
        System.out.println("Private Key: (d = " + d + ", n = " + n + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SimpleRSA rsa = new SimpleRSA();
        rsa.printKeys();

        // Get a small number as input (In real cases, message should be encoded)
        System.out.print("Enter a number to encrypt (less than n): ");
        BigInteger message = scanner.nextBigInteger();

        if (message.compareTo(rsa.n) >= 0) {
            System.out.println("Error: The number must be less than " + rsa.n);
            return;
        }

        // Encrypt message
        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt message
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
