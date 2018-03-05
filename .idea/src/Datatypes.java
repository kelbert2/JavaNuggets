import java.util.Scanner;
public class Datatypes {
    /*
     * Describes the range and storage size of primitive data types in Java
     */
    void booleanType() {
        /* 1 bit */
        boolean oneBit = true;
        oneBit = false;
    }

    void charType() {
        /* 16 bits */
        char nullChar = '\u0000';
        char sixteenBits = 'a';
        sixteenBits = '\\';
    }

    void byteType() {
        /* int */
        byte eightBits = (byte) -128;
        eightBits = 127;
    }

    void shortType() {
        /* int */
        short sixteenBits = -32768;
        sixteenBits = (short) (Math.pow(2, 16) / 2 - 1);
    }

    void intType() {
        /* int */
        int thirtyTwoBits = -2147483648;
        thirtyTwoBits = (int) (Math.pow(2, 32) / 2 - 1);
    }

    void longType() {
        /* int */
        long sixtyFourBits = (long) (-9.223372036854775808 * Math.pow(10, 18));
        sixtyFourBits = (long) (Math.pow(2, 64) / 2 - 1);
    }

    void floatType() {
        /* floating point */
        float thirtyTwoBits = (float) (1.402398 * Math.pow(10, -45));
        thirtyTwoBits = (float) (3.4028 * Math.pow(10, 38));
        /* s * m * 2^e */
    }

    void doubleType() {
        /* floating point */
        double sixtyFourBits = 4.94065 * Math.pow(10, -324);
        double max = Double.MAX_VALUE; // (2-2^52) * 2^1023
        sixtyFourBits = 1.79769 * Math.pow(10, 308);
    }

    public static void main(String[] args) {
        /* String to Int conversions */
        Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter an integer value: ");
//		String input = scanner.next();

        /* Double also has Double.toString(); */
        String input = "101";
        String fromInt = Integer.toString(101);
        /* Only takes in values that can be made into integers */

        System.out.print("toString: " + fromInt + "\n");
        int decoded = Integer.decode(input);
        int i = decoded + 0;
        System.out.printf("Decoded: %d \n", i);

        /* Double also has Double.parseDouble(); */
        int parsed = Integer.parseInt(input);
        i = parsed + 0;
        System.out.printf("ParseInt: %d \n", i);

//		int getInt = Integer.getInteger(input);
//		i = getInt + 0;
//		System.out.printf("GetInt: %d\n", getInt);

        /* Double also has Double.valueOf(); */
        int valueOf = Integer.valueOf(input);
        i = valueOf + 0;
        System.out.printf("ValueOf: %d \n", i);

        /* Binary operations */

        int bittyBaby = 13;
        System.out.println(Integer.toBinaryString(bittyBaby));
        /* get the number of 1 bits in the binary representation */
        int bitsOfBaby = Integer.bitCount(bittyBaby);
        System.out.println("bitCount: " + bitsOfBaby);
        int highestBit = Integer.highestOneBit(bittyBaby);
        System.out.println("highestBit: " + highestBit);
        int leadingZeroes = Integer.numberOfLeadingZeros(bittyBaby);
        System.out.println("leadingZeroes: " + leadingZeroes);
        int reversed = Integer.reverse(bittyBaby);
        System.out.println(Integer.toBinaryString(reversed));
        scanner.close();

        /* Double Operations */
    }
}
