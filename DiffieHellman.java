import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {
    private static final int BIT_LENGTH = 128; // Bit length for prime number
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        // Generate a large prime number (p)
        BigInteger p = generatePrime(BIT_LENGTH);

        // Choose a generator (g) - usually a small prime like 2, 5, or 7
        BigInteger g = BigInteger.valueOf(5);

        System.out.println("Generated Prime (p): " + p);
        System.out.println("Chosen Generator (g): " + g);

        // Generate private keys for Alice and Bob
        BigInteger keyA = randomBigInteger(p.subtract(BigInteger.TWO)); // Random key in range (2, p-2)
        BigInteger keyB = randomBigInteger(p.subtract(BigInteger.TWO));

        // Compute public keys
        BigInteger X_a = g.modPow(keyA, p); // Alice's public key
        BigInteger Y_b = g.modPow(keyB, p); // Bob's public key

        System.out.println("\nAlice's Public Key (X_a): " + X_a);
        System.out.println("Bob's Public Key (Y_b): " + Y_b);

        // Compute shared secret keys
        BigInteger sharedKeyAlice = Y_b.modPow(keyA, p); // Alice computes shared key
        BigInteger sharedKeyBob = X_a.modPow(keyB, p);   // Bob computes shared key

        System.out.println("\nShared Key Computed by Alice: " + sharedKeyAlice);
        System.out.println("Shared Key Computed by Bob: " + sharedKeyBob);

        // Both shared keys should be identical
        if (sharedKeyAlice.equals(sharedKeyBob)) {
            System.out.println("\n✅ Diffie-Hellman Key Exchange Successful! Shared Secret Key: " + sharedKeyAlice);
        } else {
            System.out.println("\n❌ Error: Shared keys do not match!");
        }
    }

    // Generate a prime number of given bit length
    private static BigInteger generatePrime(int bits) {
        return BigInteger.probablePrime(bits, random);
    }

    // Generate a random BigInteger within the given range
    private static BigInteger randomBigInteger(BigInteger upperLimit) {
        BigInteger randomNum;
        do {
            randomNum = new BigInteger(upperLimit.bitLength(), random);
        } while (randomNum.compareTo(BigInteger.TWO) < 0 || randomNum.compareTo(upperLimit) > 0);
        return randomNum;
    }
}
