package cs2110;

/*
 * Assignment metadata
 * Name and NetID: Max Whitton (mmw243)
 * Hours spent on assignment: 3
 */

/**
 * Collection of misc. static functions for showcasing the capabilities of Java in a procedural
 * context.
 */
public class A1 {

    /**
     * Return the area of a regular polygon with `nSides` sides of length `sideLength`. Units of
     * result are the square of the units of `sideLength`. Requires `nSides` is at least 3,
     * `sideLength` is non-negative.
     */
    public static double polygonArea(int nSides, double sideLength) {
        double area; // The area of the polygon
        area = .25 * sideLength * sideLength * nSides / Math.tan(Math.PI / nSides); //area formula
        return area;
    }

    /**
     * Return the next term in the Collatz sequence after the argument.  If the argument is even,
     * the next term is it divided by 2.  If the argument is odd, the next term is 3 times it plus
     * 1.  Requires magnitude of odd `x` is less than `Integer.MAX_VALUE/3` (otherwise overflow is
     * possible).
     */

    public static int nextCollatz(int seed) {
        int next = seed;
        if ((next % 2) == 0) {
            next = next / 2;
        } else {
            next = next * 3 + 1;
        }
        return next;
    }

    /**
     * Return the sum of the Collatz sequence starting at `seed` and ending at 1 (inclusive).
     * Requires `seed` is positive, sum does not overflow.
     */
    public static int collatzSum(int seed) {
        // Implementation constraint: Use a while-loop.  Call `nextCollatz()` to
        // advance the sequence.
        int term = seed;
        int sum = term;
        while (term != 1) {
            term = nextCollatz(term);
            sum = sum + term;
        }
        return sum;
    }

    /**
     * Return the median value among `{a, b, c}`.  The median has the property that at least half of
     * the elements are less than or equal to it and at least half of the elements are greater than
     * or equal to it.
     */
    public static int med3(int a, int b, int c) {
        // Implementation constraint: Do not call any other methods.
        int median;
        if (((a >= b) && (a <= c)) | ((a <= b) && (a >= c))) { 
            median = a;
        } else if (((b >= a) && (b <= c)) | ((b <= a) && (b >= c))) {
            median = b;
        } else {
            median = c;
        }
        return median;
    }

    /**
     * Return whether the closed intervals `[lo1, hi1]` and `[lo2, hi2]` overlap.  Two intervals
     * overlap if there exists a number contained in both of them.  Notation: the interval `[lo,
     * hi]` contains all numbers greater than or equal to `lo` and less than or equal to `hi`.
     * Requires `lo1` is less than or equal to `hi1` and `lo2` is less than or equal to `hi2`.
     */
    public static boolean intervalsOverlap(int lo1, int hi1, int lo2, int hi2) {
        // Implementation constraint: Use a single return statement to return
        // the value of a Boolean expression; do not use an if-statement.
        return (!((hi1 < lo2) | (hi2 < lo1)));
    }

    /**
     * Return the approximation of pi computed from the sum of the first `nTerms` terms of the
     * Madhava-Leibniz series.  This formula states that pi/4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 - ...
     * Requires `nTerms` is non-negative.
     */
    public static double estimatePi(int nTerms) {
        // Implementation constraint: Use a for-loop.  Do not call any other
        // methods (including `Math.pow()`).
        double quarterEstimation = 0;
        for (int i = 0; i < nTerms; ++i) {
            if (i == 0) {
                quarterEstimation = quarterEstimation + 1.0;
            } else if (1 == (i % 2)) {
                quarterEstimation = quarterEstimation - 1.0 / ((2 * i) + 1);
            } else {
                quarterEstimation = quarterEstimation + 1.0 / ((2 * i) + 1);
            }
        }
        double estimation = 4 * quarterEstimation;
        return estimation;
    }

    /**
     * Returns whether the sequence of characters in `s` is equal (case-sensitive) to that sequence
     * in reverse order.
     */
    public static boolean isPalindrome(String s) {
        // Implementation constraint: Use the `charAt()` and `length()` methods
        // of the `String` class.
        boolean palindrome = true;
        for (int i = 0; i < (s.length() / 2); ++i) {
            if (palindrome) {
                palindrome = (s.charAt(i) == s.charAt(s.length() - i - 1));
            }
        }
        return palindrome;
    }

    /**
     * Return an order confirmation message in English containing the order ID and the number of
     * items it contains.  Message shall handle item plurality properly (e.g. "1 item" vs. "3
     * items") and shall surround the order ID in single quotes. Examples:
     * <pre>
     * formatConfirmation("123ABC", 1) should return
     *   "Order '123ABC' contains 1 item."
     * formatConfirmation("XYZ-999", 3)" should return
     *   "Order 'XYZ-999' contains 3 items."
     * </pre>
     * Requires `orderId` only contains digits, hyphens, or letters 'A' - 'Z'; `itemCount` is
     * non-negative.
     */
    public static String formatConfirmation(String orderId, int itemCount) {
        // Implementation constraint: Use Java's ternary operator (`?:`) to give "item" the
        // appropriate plurality.
        String message;
        String isS = itemCount == 1 ? "item" : "items";
        message = String.format("Order '%s' contains %d %s.", orderId, itemCount, isS);
        return message;
    }

    public static void main() {
        String myName;
        myName = "max";
        double squareArea;
        double pi;
        if (!(isPalindrome(myName))) {
            int n1 = 10;
            int n2 = 11;
            int n3 = 50;
            int nTerms = med3(n1, n2, n3);
            pi = estimatePi(nTerms);
            int nSides = 4;
            squareArea = polygonArea(nSides, pi);
        } else {
            squareArea = 1;
            pi = Math.PI;
        }
        System.out.print(String.format("The area of the square with sides of length %f is %f.", pi,
                squareArea));
    }
}
