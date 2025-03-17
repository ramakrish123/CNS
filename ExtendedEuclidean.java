import java.util.Scanner;

public class ExtendedEuclidean {
    // Function to implement Extended Euclidean Algorithm
    public static int extendedGCD(int a, int b, int[] x, int[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }

        int[] x1 = {0}, y1 = {0};
        int gcd = extendedGCD(b, a % b, x1, y1);

        x[0] = y1[0];
        y[0] = x1[0] - (a / b) * y1[0];

        return gcd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input two numbers
        System.out.print("Enter first number (a): ");
        int a = scanner.nextInt();
        System.out.print("Enter second number (b): ");
        int b = scanner.nextInt();

        int[] x = {0}, y = {0};

        int gcd = extendedGCD(a, b, x, y);

        // Output results
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("Coefficients x and y (Bezout's Identity): x = " + x[0] + ", y = " + y[0]);

        scanner.close();
    }
}
