
public class Othertypes {

    void stringType() {
        /* really just an array of chars */
        String str = "Theory";
        int length = str.length();
    }
    void stringMethods() {
        String charArray = "character array";
        int i = 0;
        while (i < charArray.length()) {
            System.out.print(charArray.charAt(i));
            i++;
        }
        String str = "character arrayA";
        /* < 0 if this is shorter than str or the first character that doesn't match is smaller than that of str
         * = 0 if the strings are equal
         * > 0 if this is longer than str or the first char that doesn't match is > str
         * lower case letters are 32 greater than uppercase letters a > A
         * later in the alphabet means greater A < Z
         */
        System.out.println("\n" + charArray.compareTo(str));
        /* boolean equals, also equalsIgnoreCase */
        System.out.println(charArray.equals(charArray));
        /* does the string contain the other? */
        System.out.println(str.contains(charArray));
        System.out.println(charArray.indexOf("ra")); // 3
        System.out.println(charArray.lastIndexOf("ra")); // 12

        String[] splat = charArray.split(" ");
        /* .toUpperCase(); .toLowerCase(); .trim() to get rid of front and back whitespace */
        System.out.println(splat[0].concat(" ").concat(splat[1]));
    }
    /*
     * When you know all the possible values at compile time.
     */
    enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS;
        //	internal implementation is as a class {
//		public static final Suit CLUBS = new Suit();
        // since static, can access using enum Name
        // because final, no child enums
        // these are also in a specific order
//	}
        public void enumMethod() {

        }
    }
    public class SuitClass {
        Suit mySuit;
        public SuitClass(Suit suit) {
            this.mySuit = suit;
        }
    }
    void enumType() {
        Suit card = Suit.CLUBS;
        String hearty = "HEARTS";
        SuitClass otherCard = new SuitClass(Suit.valueOf(hearty));
        switch (otherCard.mySuit) {
            case SPADES:
                System.out.println("Spades");
                break;
            default:
                System.out.println("Not Spades lol");
        }
        Suit arr[] = Suit.values();
        for (Suit bathing : arr) {
            System.out.println(bathing + " index " + bathing.ordinal());
        }
    }
    public static void main (String[] args) {
        Othertypes o = new Othertypes();
        //o.enumType();
        o.stringMethods();
    }
}
