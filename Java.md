Much taken from https://www.tutorialspoint.com/java/java_quick_guide.htm
http://tutorials.jenkov.com/java/lambda-expressions.html
https://www.baeldung.com/java-8-lambda-expressions-tips
https://winterbe.com/posts/2014/03/16/java-8-tutorial/
https://www.baeldung.com/java-fork-join
https://www.baeldung.com/java-8-collectors
https://www.baeldung.com/java-optional

# Primitive Data Types

| Data Type | Bits | Maximum Value      | Minimum Value | Default Value |
|------|---:|--------------------------:|-----------------------:|-----:|
|int   | 32 | 2^31-1 = 2,147,483,647    | -2^31 = -2,147,483,648 | 0    |
|float | 32 | 3.4028235 E38             |	1.4 E-45             | 0.0f |
|double| 64 | 1.7976931348623157 E308   |	4.9 E-324            | 0.0d |
|char  | 16 | 65,535 or '\uffff'        |	0 or '\u0000'        |      |
|byte  | 8  | 127                       | -128                   | 0    |
|short | 16 |	32,767                  |	-32,768              | 0    |
|long  | 64 | 9,223,372,036,854,775,807 | -9,223,372,036,854,775,808 | 0L |
boolean

1 GB is 8 billion bits.

Double and short data should not be used for precise values such as currency.

Literals are representations of fixed values. Binary literals are prefixed with 0b or 0B.

Underscores can appear anywhere between digits in numerical literals, allowing you to separate groups of digits and improve readability. They don't work adjacent to decimal points, prior to, after, or between suffixes (like L or F) or prefixes (0x), and at the end or beginning (would make it an idenfier) of the number.

```Java
byte a = 68;
char a = 'A';

// Prefixes
int decimal = 100;
int octal   = 0144;
int hexa    = 0x64;

String lit = "two\nlines";
String escaped = "\"This is in quotes\"";

// Unicode Characters
char a = '\u0001';
String a = "\u0001";

// Underscores are just fine here
long l = 908_556_5842;
int i = 5_________2;
int octal = 0_5_2;

```
## Boolean
Stores either `true` or `false`. 1 bit in size. Default value is `false`.

### Logical Operators
* && AND
* || OR
* ! NOT

# Operators
Precendence, then associativity (right to left or left to right). There is no explicit ordering, but here's what works:

| Level 	| Operator 	| Description 	| Associativity |
|----------:|-----------|:--------------|:--------------|
| 16 	| []   .   () 	| access array element, access object member, parentheses 	| left to right |
| 15 	| ++   -- 	| unary post-increment, unary post-decrement 	| not associative |
|14 	| ++   --   +   -   !   ~ 	| unary pre-increment, unary pre-decrement, unary plus, unary minus, unary logical NOT, unary bitwise NOT 	| right to left |
|13 	| ()   new 	| cast, object creation 	| right to left |
|12 	| *   /   % 	| multiplicative 	| left to right |
|11  |	+   -     + 	 |additive, string concatenation 	 | left to right |
| 10 	 | <<   >>   \>>> 	 | shift 	 | left to right |
| 9 	 | <   <=   \>   \>= | instanceof, 	relational 	 | not associative|
| 8 |	==   != 	 | equality 	 | left to right|
|7 	 | & 	| bitwise AND 	| left to right|
|6 	| ^ | 	bitwise XOR |	left to right|
|5 |	\| | 	bitwise OR 	| left to right|
|4 |	&& 	| logical AND 	| left to right|
|3 |	\|\| |	logical OR |	left to right |
|2 	| ? : | 	ternary 	| right to left |
|1 	 | =   +=   -=   *=   /=   %=   &=   ^=   \|=   <<=  >>=   >>>= 	| assignment 	| right to left

Above/ with assignment goes the lambda operator ->, such that `op = x -> x` is different from `(op = x) -> x`, which isn't syntactically valid. `op = x -> t = x` is the same as `op = (x -> (t = x))`

## Short-Circuiting
When conditional && and || operators are used, Java will not evaluate the second unless it is necessary to resolve the result. 

So you can do this:
`(x != null && x.length() < 10)` without throwing an error if `x == null`.

# Wrappers
Objects that wrap around primitives. Allows you to use special class (static) methods. Compiler handles conversion to and from primitive (unboxing and boxing). Wrappers are Serializable and Comparable. They are immutable and can be stored by collections.

```Java
Integer x = 5; // boxes int to an Integer object
x =  x + 10;   // unboxes the Integer to a int
```
## Number
Numerics are subclasses of abstract class Number. 
* `num.xxxValue()`: converts value of num to xxx data type and returns it.
* `num.compareTo(arg)`
* `num.equals(arg)`
* `valueOf()`: returns an Integer holding the value of the specified primitive.
* `toString()`: returns a String object holding the value.
* `parseInt()`: gets primitive from a String.
* `abs(arg)`: returns absolute value of argument.
* `ceil(arg)`: returns a double with the value of the smallest integer greater than or equal to the argument.
* `floor(arg)`: returns a double with the value of hte largest integer <= arg.
* `rint(arg)`: returns a double with the value of the integer closest in value of the argument.
* `round()`: returns the closest long or int.
* `min(a, b)`: returns the smaller of a and b.
* `max(a, b)`: returns the larger of a and b.
* `exp(arg)`: returns the base of the natural log, e, to the power of arg.
* `log(arg)`: returns the natural log of the arg.
* `.pow(a, b)`: returns a raised to the power of b.
* `sqrt(arg)`: returns the square root of arg.
* `sin(double a)`: returns the sine of arg.
* `cos(double a)`
* `tan(double a)`
* `asin(double a)`: returns the arcsine.
* `acos(double a)`
* `atan(double a)`
* `atan2(x, y)`: converts rectangular coordinates to polar (r, theta) and returns theta.
* `toDegrees(arg)`
* `toRadians(arg)`
* `random()`

### Character
`Character ch = new Character('a');`
* `isLetter()`
* `isDigit()`
* `isWhitespace()`
* `isUpperCase()`
* `isLowerCase()`
* `toUpperCase()`
* `toLowerCase()`
* `toString()`

## Strings
Immutable, so if you're modifying a lot of characters or extending the string, use StringBuffer or StringBuilder. Ends with ‘\0’.
* `int str.length()`
* `String trim()`: trimes off leading and trailing whitespace.
* `String[] .split(“ “)`, also takes `String \regex\`
* `String[] split(String regex, int limit)`
* `String string1.concat(String string2)`: returns a new string with string2 added to the end of string1.
* * Can also just use `string1 + string2` with + as an operator.
* `String substring(int beginIndex)`
* * `String substring(int beginIndex, int endIndex)`
* `String toLowerCase()`: uses rules of the default locale
* * `String toLowerCase(Locale locale)`
* `String toUpperCase()`
* * `String toUpperCase(Locale locale)`
* `String replace(char oldChar, char newChar)`: returns a new string with all occurances of oldChar replaced with newChar.
* `String replaceAll(String regex, String replacement)`: replace each substring that matches the given regex with replacement.
* `String replaceFirst(String regex, String replacement)`: replaces only the first occurance.
* `String.format("string " + " with variables like %f, %d, %s here", floatVar, intVar, stringVar)`
* * formats strings for use in `System.out.println(formattedString)`
* `int hashCode()`
* `String toString()`: returns same value.
* `String intern()`: Returns a canonical representation for the string object.


Conversion
* `Integer.parseInt(String, int radix) => int`
* * radix being the mathematical base. 10 is decimal, 8 is octal, 16 is hexadecimal.
* * if not provided, looks at what the string begins with. "0x" or "0X" is 16, "0" may be octal or decimal, and any other value for the beginning defualts to 10
* `Integer.valueOf(int or String, int base if String) => Integer`
* * Caches frequently requested values, so more space and time efficient
* `String String.valueOf(primitive data type x)`

Indices
* `int indexOf(String str, int fromIndex)`
* `int indexOf(int ch)`: returns first occurance of character.
* `int indexOf(int ch, int fromIndex)`: returns first occurance starting search at specified index.
* `int indexOf(String str)`: returns first occurance of specified substring.
* `int lastIndexOf(int ch)`
* `int lastIndexOf(int ch, int fromIndex)`
* `int lastIndexOf(String str)`
* `int lastIndexOf(String str, int fromIndex)`




Comparison
* `boolean matches(String regex)`
* `boolean regionMatches(int toffset, String other, int ooffset, int len)`: Tests if two string regions are equal.
* `boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)`
* `boolean equals(Object o)`
* `boolean equalsIgnoreCase(String str)`
* `int compareTo(Object o)`: if the object is a String, compares lexicographically.
* `int compareToIgnoreCase(String str)`
* `boolean contentEquals(StringBuffer sb)`
* `boolean endsWith(String suffix)`
* `boolean startsWith(String prefix)`
* `boolean startsWith(String prefix, int offset)`: Tests if string starts with prefext at offset index.

== checks references, not content,

Bytes
* `byte getBytes()`
* `byte[] getBytes(String charsetName)`

Chars
* `char[] .toCharArray()`
* `char charAt(int index)`
* `static String copyValueOf(char[] data)`
* `static String copyValueOf(char[] data, int offset, int count)`: uses a starting place (offset) and total desired length.
* `void src.getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)`: copies characters from String src string to dest character array.
* `CharSequence subSequence(int beginIndex, int endIndex)`



### Comparison
* Positive if string1 follows argument string2 
  * > b < c, A < a, returns difference at index where they differ
* Zero if both strings are equal
* Negative if string1 Object precedes argument string2 
  * > c is shorter than cc, returns difference in lengths

### Compare Strings
* == returns 0, `.equals(str)`, `.equalsIgnoreCase(str)`
* < 0 if this is shorter than str or the first character that doesn't match is smaller than that of str
  * = 0 if the strings are equal
  * &gt; 0 if this is longer than str or the first char that doesn't match is > str
  * lower case letters are 32 greater than uppercase letters a > A
  * later in the alphabet means greater A < Z

```Java
int res = string1.compareTo(string2);
int resNoCase = first.compareToIgnoreCase(second);
```

### Substring
[start, end) - includes start, excludes char at index end

```Java
og.substring(int startIndex, int endIndex);
```


### StringBuffer
Modifiable String - growable and writable. Synchronized and thread-safe, unlike StringBuilder.

Functions
* `.insert(int index, String str or char ch)`: Pushes self in to start at the specified index
* `.reverse()`: Inline function
* `.delete(int startIndex, int endIndex)`
* `.deleteCharAt(int loc)`
* `.replace(int startIndex, int endIndex, String str)`

Length
* `.length()`
* `.setLength(int newLength)`: Truncates or fills with null
* `.capacity()`: Total allocated
* `.ensureCapacity(int capacity?)`: Sets capcity to specified number or twice the current + 2, whichever is larger
* `.trimToSize()`: Attempts to reduce storage used for the character sequence

Strings
* `.substring(int start, int end?)`
* `.indexOf(String sub)`: returns index of the first occurance of substring sub
* `.lastIndexOf(String sub)`
* `.toString()`

Chars
* `.charAt(int index)`
* `.chars()`: Returns a stream of int zero-extending char values of type IntStream
* `.getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)`: Copy chars from sequence into destination character array dst
* `.setCharAt(int index. char ch)`
* `.subSequence(int start, int end)`: Returns a new CharSequence

Codepoints
* `.codePointAt(int index)`: Returns the character in int (Unicode code point) at the specified index
* `.appendCodePoint(int codePoint)`: Uses string representation of codePoint
* `.codePointBefore(int index)`
* `.codePointCount(int beginIndex, int endIndex)`: Number of Unicode code points in the specified text range of this sequence
* `.offsetByCodePoints(int index, int codePointOffset)`: Returns the index within this sequence that is offset from the given index by codePointOffset code points.

```Java
StringBuffer s = new StringBuffer();
StringBuffer sSize = new StringBuffer(int size);
StringBuffer sContents = new StringBuffer("intial content");
// reserves 16 more characters than initial without reallocation
```
### StringBuilder
Resizable array of strings, like an ArrayList. Automatically increases when needed so capacity > length
Else would take O(xn<sup>2<sup>) time to concatenate _n_ strings of _x_ length.

```Java
// creates empty builder, capacity 16
StringBuilder sb = new StringBuilder();
// adds 9 character string at beginning
sb.append("Greetings");
```
* `append(Type data)`: data converted to a string before appended.
* `delete(int start, int end)`: deletes the subsequence from start to end-1 (inclusive).
* `deleteCharAt(int index)`
* `insert(int offset, Type dta, int dataOffset?, int dataLength?)`: Index before which data is to be inserted.
* `replace(int start, int end, String s)`
* `indexof(String str)` returns te index of hte first occurance of the passed substring, else -1.

* `setCharAt(int index, char c)`
* `reverse()`
* `setLength(int newLength)`: if newLength < length(), last characters are truncated. If greater, null characters are added at the end.
* `ensureCapacity(int minCapacity)`
* `toString()`

## Enums
Group of constants.
```Java
Enum name {
    NAME1, NAME2, NAME3
}
```

```Java
enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS;
}
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
```
Enum.values() allows you to iterate over constants.
For Enums defined as NAME("some string"), enum.name() is NAME and enum.toString() or just enum is "some string".
```Java
enum MixedColor {
    PURPLE("red", "blue"), GREEN ("yellow", "blue"), ORANGE("red", "yellow");
    public String color1;
    public String color2;
    private Color(String c, String r) {
        color1 = c;
        color2 = r;
    }
    public String getColors() {
        return color1 + ", " + color2;
    }
}
MixedColor.ORANGE; // ORANGE
MixedColor.PURPLE.color1; // red
MixedColor.GREEN.getColors(); // yellow, blue
```
## Enumerations
Legacy interface. Obsolete for new code.
* `boolean hasMoreElements()`
* `Object nextElement()`

```Java
      Enumeration days;
      Vector dayNames = new Vector();
      
      dayNames.add("Sunday");
      dayNames.add("Monday");
      dayNames.add("Tuesday");
      dayNames.add("Wednesday");
      dayNames.add("Thursday");
      dayNames.add("Friday");
      dayNames.add("Saturday");
      days = dayNames.elements();
      
      while (days.hasMoreElements()) {
         System.out.println(days.nextElement()); 
      }
```
## Date and Time
Part of java.util package.
```Java
Date date = new Date();
Date since1970 = new Date(long millisec); // number of ms elapsed since midnight, January 1, 1970.
```
* `boolean a.after(Date date)`: true if a contains a date later than Date date.
* `boolean a.before(Date date)`
* `Object a.clone( )`
* `int a.compareTo(Date date)`: returns 0 if equal, negative if a is earlier than date, positive if a is later than date.
* `boolean equals(Object date)`
* `long getTime()`: returns ms since midnight, January 1, 1970.
* `void setTime(long time)`: sets based on ms from midnight, January 1, 1970.
* `int hashCode( )`
* `String toString()`: Mon 00 00:00:00 zzz Year where zzz is the time zone.

Can format with SimpleDateFormat:
```Java
      Date dNow = new Date( );
      SimpleDateFormat ft = 
      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
      System.out.println("Current Date: " + ft.format(dNow));
      // Sun 2004.07.18 at 04:14:09 PM PDT

      // G is era, like AD
      // H is for 24-hour time
      // S is ms
      // E day in week, like Tuesday
      // D Day in year, like 360
      // F day of week in month, like 2 for the 2nd Wed in July
      // w week in year
      // W week in month
      // a AM/ PM marker
      // k Hour in day, from 1 to 24
      // K Hour in AM/ PM, so 0-11
      // ' escape for text
      // " single quote

      Date t = ft.parse("1818-11-11"); 
      // Wed Nov 11 00:00:00 EST 1818
      // best to do in a try..catch block

      String str = String.format("Current Date/Time : %tc", date ); // complete date
      System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", dNow); // index of argument to be formatted
      System.out.printf("%s %tB %<te, %<tY", "Due date:", date); // < means same argument as preceding format specification
      // Due date: February 09, 2004

```

Clocks are aware of timezones and can be used instead of System.currentTimeMillis(). Timezones are represented by ZoneIds. LocalTime is a time without a timezone.
```Java
Clock clock = Clock.systemDefaultZone();
long millis = clock.millis();

Instant instant = clock.instant();
Date legacyDate = Date.from(instant);   // legacy java.util.Date

System.out.println(ZoneId.getAvailableZoneIds());
// prints all available timezone ids

ZoneId zone1 = ZoneId.of("Europe/Berlin");
ZoneId zone2 = ZoneId.of("Brazil/East");
System.out.println(zone1.getRules());
System.out.println(zone2.getRules());

// ZoneRules[currentStandardOffset=+01:00]
// ZoneRules[currentStandardOffset=-03:00]

LocalTime now1 = LocalTime.now(zone1);
LocalTime now2 = LocalTime.now(zone2);

System.out.println(now1.isBefore(now2));  // false

long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

System.out.println(hoursBetween);       // -3
System.out.println(minutesBetween);     // -239

LocalTime late = LocalTime.of(23, 59, 59);
System.out.println(late);       // 23:59:59

DateTimeFormatter germanFormatter =
    DateTimeFormatter
        .ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(Locale.GERMAN);

LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
System.out.println(leetTime);   // 13:37
```

LocalDate is immutable. Each manipulation returns a new instance:
```Java
LocalDate today = LocalDate.now();
LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
LocalDate yesterday = tomorrow.minusDays(2);

LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
System.out.println(dayOfWeek);    // FRIDAY

// Can parse:
DateTimeFormatter germanFormatter =
    DateTimeFormatter
        .ofLocalizedDate(FormatStyle.MEDIUM)
        .withLocale(Locale.GERMAN);

LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
System.out.println(xmas);   // 2014-12-24
```

LocalDateTime is a combination and is also immutable:
```Java
LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
System.out.println(dayOfWeek);      // WEDNESDAY

Month month = sylvester.getMonth();
System.out.println(month);          // DECEMBER

long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
System.out.println(minuteOfDay);    // 1439

// can convert to instant and, from there, legacy Date objects
Instant instant = sylvester
        .atZone(ZoneId.systemDefault())
        .toInstant();

Date legacyDate = Date.from(instant);
System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

// Custom formatting pattern
DateTimeFormatter formatter =
    DateTimeFormatter
        .ofPattern("MMM dd, yyyy - HH:mm");

LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
String string = formatter.format(parsed);
System.out.println(string);     // Nov 03, 2014 - 07:13
```
DateTimeFormatter is immutable and thread-safe.

# Custom Objects

Can have methods, variables, and inner classes.
```Java
class Obj {
    int instanceVariable;
    int a;

    // constructor
    obj() {
        this(1); // calls Obj(1), as seen below:
    }
    Obj(int a) {
        this.a = a; // a is a local variable, this.a refers to the instance variable.
    }

    // optional modifiers returnType methodName(formal parameters list)
    public static int methodName(int a, int b) {
        if (a < 0) return this.a + b; // can access instance variable
        return a + b;
    }
    // Method overloading, same name different parameters or types
    public static double methodName(double a, double b) {
        return a + b;
    }
    public static int methodName(int a, int b, int c) {
        return a + b + c;
    }
    // vaiable number of parameters
    public static int methodNameVariable(double... numbers) {
        double total = 0;
        for (int i = 0; i < numbers.length; i++) {
            total += numbers[i]; // treat as an arguments array
        }
    }

    // void if returns nothing
    public static void callingFunction() {
        int result = methodName(1, 2);
        System.out.println(result);
    }

    // called just before an object's final destruction by a garbage collector
    protected void finalize() {
        // clean up. Ex: close file.
    }


// Classes
   // instance method of the outer class 
   void my_Method() {
      int num = 23;

      // method-local inner class
      class MethodInner_Demo {
         public void print() {
            System.out.println("This is method inner class "+num);	   
         }   
      } // end of inner class
	   
      // Accessing the inner class
      MethodInner_Demo inner = new MethodInner_Demo();
      inner.print();

      // Anonymous inner classes
      AnonymousInner an_inner = new AnonymousInner() {
            public void my_method() {
                // quick class definition
          }   
      };
      // Can pass anonymous inner classes with the new keyward into methods
   }

    public class InnerClass { // can make private
      public void print() {
         System.out.println("This is an inner class");
      }
    }

    void display_Inner() {
      Inner_Demo inner = new Inner_Demo();
      inner.print();
   }

   public static class StaticInnerClass {
       public void print() {
         System.out.println("This is an inner class");
      }
   }
}

// Accessing inner classes outside of everything
public class OtherClass {
    public static void main(String args[]) {
        // don't need an instance to access static inner classes
        ClassName.StaticInnerClass staticInner = new ClassName.StaticInnerClass();
        staticInner.print();

        // do need an instance to access non-static inner classes
        ClassName outer = new ClassName();
        // get inner class
        ClassName.InnerClass inner = outer.new InnerClass();
        inner.print();
    }
}

```
Parameters passed by value are unchanged by the function. Parameters passed by reference can be modified by the function.

### Comparisons
Used for object reference variables to check if an object is of a particular class or interface type:
```Java
objReference instanceOf ClassName;

boolean result = name instanceof String;
```
if name is null, instanceof is false.

### Serialization into Streams
Objects can be represented as sequences of bytes that include information about the object's data, type, and types of data. Through deserialization, the object can be recreated.

All fields must be serializable. If they are not, they mus tbe marked transient.

```Java
public class Cereal implements java.io.Serializable {
    public String brand;
    public Double sugar;
    public Float servings;
    public transient int socialSecurityNumber;

    public void eat(int count) {
        servings -= count;
    }

    public static void main(String [] args) {
      Cereal c = new Cereal();
      c.brand = "Chocolate Frosted Sugar Bombs";
      c.sugar = 50000L;
      c.SSN = 11122333;
      c.servings = 101;
      
      try {
         FileOutputStream fileOut =
         new FileOutputStream("/tmp/cereal.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);

         out.writeObject(c); // Serialize

         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/cereal.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }

       Cereal e = null;
       try {
         FileInputStream fileIn = new FileInputStream("/tmp/cereal.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);

         e = (Cereal) in.readObject(); // Deserialize! Need to cast

         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Cereal class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized Cereal...");
      System.out.println("Brand: " + e.brand);
      System.out.println("Sugar: " + e.sugar);
      System.out.println("SSN: " + e.SSN);
      System.out.println("Servings: " + e.servings);
      /*
      Deserialized Cereal...
      Brand: Chocolate Frosted Sugar Bombs
      Sugar: 50000L
      SSN: 0 // transient values are not sent to the output stream.
      Servings:101
      */
   }
}
```

# Data Structures

## Legacy
### BitSet
Like an arrayList of bits or flags that can be set and cleared individually. Can just use a set of boolean.

```Java
new BitSet();
new BitSet(int size);
```
Cloneable and has methods:
* `void set(int index)`: Sets bit at index to 1.
* `void set(int index, boolean v)`: Sets to 1 if true, 0 if false.
* `void set(int startIndex, int endIndex)`
* `void set(int startIndex, int endIndex, boolean v)`
* `boolean get(int index)`
* `BitSet get(int startIndex, int endIndex)`
* `void and(BitSet bitSet)`: Places result into the invoking object.
* `void andNot(BitSet bitSet)`: For each 1 in bitSet, the corresponding bit in the invoking BitSet is cleared.
* `void or(BitSet bitSet)`: Places result into the invoking object.
* `void xor(BitSet bitSet)`: Places result into the invoking object.
* `void clear()`
* `void clear(int index)`
* `void clear(int startIndex, int endIndex)`
* `void flip(int index)`
* `void flip(int startIndex, int endIndex)`
* `boolean equals(Object bitSet)`
* `boolean intersects(BitSet bitSet)`: True if at least one pair of corresponding bits are 1.
* `boolean isEmpty()`: True if all bits are 0.
* `int nextClearBit(int startIndex)`: Returns index of next zero (cleared) bit starting from startIndex.
* `int nextSetBit(int startIndex)`: If no bits after and including startIndex are 1, returns -1.
* `int size()`
* `int length()`: Determined by the location of the last 1 bit.
* `int cardinality()`: Number of 1 (set) bits.
* `int hashCode()`
* `String toString()`

Prints as {index, index, index} of indices where the value is 1.

### Vector
Shrinks and grows automatically to accommodate new elements, which are accessible by index.

Unlike arrayLists, is synchronized.
```Java
Vector vec = new Vector();
Vector sized = new Vector(int size);
Vector increment = new Vector(int size, int incr); // incr specifies number of elements to allocate each time it is resized upward
Vector collect = new Vector(Collection c);
```
Some legacy methods:
* `void setSize(int newSize)`
* `void trimToSize()`
* `void setElementAt(Object obj, int index)`
* `void addElement(Object obj)`
* `void insertElementAt(Object obj, int index)`
* `boolean removeElement(Object obj)`: Removes first/ lowest-indexed occurence.
* `void removeElementAt(int index)`
* `protected void removeRange(int fromIndex, int toIndex)`: (fromIndex. toIndex] exclusive on that last one.
* `void removeAllElements()`
* `Object elementAt(int index)`
* `int lastIndexOf(Object elem)`
* `int lastIndexOf(Object elem, int index)`: Searches backwards from specified index.
* `Object firstElement()`
* `Object lastElement()`
* `int capacity()`
* `void copyInto(Object[] anArray)`
* `Enumeration elements()`
* `String toString()`

### Dictionary
Abstract class that maps keys to values. Obsolete as Maps are preferred.

Legacy methods in addition to many Collection ones:
* `Enumeration keys( )`
* `Enumeration elements()`
### Hashtable
Organizes data based on some user-defined key structure. Concrete implementation of Dictionary, now implements the Map interface, so acts as a collection.

Unlike HashMap, is synchronized.
```Java
new Hashtable();
new Hashtable(int size);
new Hashtable(int size, float fillRatio); // fillRatio between 0.0 and 1.0, the point at which the table will resize upward.
new Hashtable(Map<Type extends K, Type extends V> map);
```

Some legacy functions, in addition to all Map ones:
* `boolean contains(Object value)`
* `void rehash()`: Increases size of the hash table and rehashes all keys.
* `Enumeration keys()`
* `Enumeration elements()`
* `String toString()`

#### Properties
Maintains list of values with String keys and values.

```Java
// Empty property lists:
new Properties(); // no default values
new Properties(Properties propDefault); // same default values
```
* `Object setProperty(String key, String value)`: Returns previous value associated with the key or null if nothing existed before.
* `String getProperty(String key)`: null if not in the list or the default property list.
* `String getProperty(String key, String defaultProperty)`: Returns value associated with key, returns defaultProperty if not found in the list or the default property list.
* `void list(PrintStream streamOut)`
* `void list(PrintWriter streamOut)`
* `void load(InputStream streamIn) throws IOException`
* `Enumeration propertyNames()`
* `void store(OutputStream streamOut, String description)`


## Arrays
Fixed-size sequential collection of elements of the same type.

Access, insert, delete O(1)

Search, space O(n)

As a class, has methods and fields:
* `.length`
* `boolean equals(Type[] a, Type[] a2)`: equal if same elements at the same locations.
* `void Arrays.fill(Type[] a, Type val)`: Assigns specified val to each element in array.
* `void Arrays.sort(Object[] a)`: sorts in ascending natural order.
* `int index = Arrays.binarySearch(Object[] a, Object key)`: where a is sorted already. Returns `-(insertion point + 1)` if cannot find.

```Java
int[] array = new int[size]; // preferred way
// Same as
int array[] = new int[size]; // for C/ C++ programmers
/* 2D arrays by [row][column] */
int[][] arrayTwo = new int[][]{
    {1, 2, 3},
    {4, 5, 6},
};
int[][] arrayTwo = {
    {1, 2, 3},
    {4, 5, 6},
};
```

### Iteration
```Java
// Summing all elements
double total = 0;
for (int i = 0; i < myList.length; i++) {
    total += myList[i];
}
System.out.println("Total is " + total);

// foreach loop or enhanced for loop - don't need an index variable
// Print all the array elements
for (double element: myList) {
    System.out.println(element);
}
```
### Sorting
Inline function: `Arrays.sort(sortedArray);`

### Largest continuous subarray sum
`localMax = Integer.MIN_VALUE` or something less than the range or can be 0 if have a subarray of 0
Max ending here = 0 maximum up until that point

If can get a larger or = value by adding to max ending here, add to the subarray

Else

Max ending here is smaller than the value that is here, so instead we just start from this node clean 

There’s a max value that can be made with the array, finding the longest possible way to make it

For each element i in the array

Max to point += array[i]

If it becomes negative, know that will only be taking away from any subsequent values, so reset to 0, since we won’t be using this string of values

overallMax is still 0 if max to point is <0
    Otherwise, set it to the max to that point

## Collections
Standard implementations for legacy structures.
* `int .size()`
* `boolean .isEmpty()`
* `boolean .contains(Object o)`
* `boolean .containsAll(Collection c)`
* `boolean .add(element)`
* `boolean .addAll(Collection c)`: Union of two collections.
* `boolean .remove(index or element)`
* `boolean .removeAll(Collection c)`
* `boolean .retainAll(Collection c)`: Intersection between two collections. Returns true if elements were removed, i.e. the invoking collection was changed.
* `void .clear()`
* `boolean .equals(Object o);`
* `Object[] .toArray()`
* `Object[] .toArray(Object[] array)`: Returns an array containing only the collection elements with the same type as that of array.
* `int .hashCode()`
* `Iterator .iterator()`

```Java
Collection<Type> c;
List<Type> list = new ArrayList<Type>(collection);
```
#### Algorithms
Static methods.
* `static void fill(List list, Object obj)`: Assigns obj to each element of the list.
* `static void copy(List list1, List list2)`
* `static boolean replaceAll(List list, Object old, Object new)`: Returns true if at least one replacement occurred.
* `static void swap(List list, int idx1, int idx2)`: Exchange or switch elements at indices.
* `static int indexOfSubList(List list, List subList)`: Returns index first occurence or -1 if not found.
* `static int lastIndexOfSubList(List list, List subList)`
* `static Object max(Collection c)`: Returns max based on natural ordering. Collection need not be sorted.
* `static Object max(Collection c, Comparator comp)`: Returns max according to comp.
* `static Object min(Collection c)`
* `static Object min(Collection c, Comparator comp)`
* `static List nCopies(int num, Object obj)`: Returns num copies of obj contained in an immutable list for num >= 0.
* `static ArrayList list(Enumeration enum)`
* `static Enumeration enumeration(Collection c)`
* `static Set singleton(Object obj)`: Returns obj as an immutable set. Easy way to convert a single object into a set.
* `static List singletonList(Object obj)`: Returns obj as an immutable list. Easy way to convert a single object into a list.
* `static Map singletonMap(Object k, Object v)`: Returns the key, value pair k, v as an immutable map. Easy way to convert a single key, value pair into a map.
* `static Collection unmodifiableCollection(Collection c)`
* `static Set unmodifiableSet(Set s)`
* `static SortedSet unmodifiableSortedSet(SortedSet ss)`
* `static List unmodifiableList(List list)`
* `static Map unmodifiableMap(Map m)`
* `static SortedMap unmodifiableSortedMap(SortedMap sm)`
* `static Collection synchronizedCollection(Collection c)`: Returns a thread-safe collection backed by c.
* `static Set synchronizedSet(Set s)`
* `static SortedSet synchronizedSortedSet(SortedSet ss)`
* `static List synchronizedList(List list)`
* `static Map synchronizedMap(Map m)`
* `static SortedMap synchronizedSortedMap(SortedMap sm)`


Ordering
* `static void sort(List list)`: Sorts by natural ordering.
* `static void sort(List list, Comparator comp)`: Sorts by comparator.
* `static void reverse(List list)`: Reverses in place.
* `static Comparator reverseOrder()`: Returns a reverse comparator.
* `static void rotate(List list, int n)`: Rotates list by n places to the right. To go left, use negative n.
* `static void shuffle(List list)`: Randomizes elements in list.
* `static void shuffle(List list, Random r)`: Uses r as source of random numbers.

Search
* `static int binarySearch(List list, Object value)`: List must be sorted. Returns -1 if not found, else position in list.
* `static int binarySearch(List list, Object value, Comparator c)`: Searches for value in list ordered according to comparator c.

#### Iteration
```Java
Collection<Type> collection = new HashSet<Type>();

Iterator<Type> iterator = collection.iterator();
 
while (iterator.hasNext()) {
    // can read and remove
    //  moving cursor to next element 
    Type i = (Type)iterator.next(); 
  
    // getting even elements one by one 
    System.out.println(i + " "); 
  
    // Removing odd elements 
    if (i % 2 != 0) {
        itr.remove();  
    }
}

for (Type t : collection) {
    // do something
}
```

### Lists
Best for random access and ordering, indexed (zero-based)

When reach end, doubles in size. Can contain duplicates.
* `Object .get(int index)`
* `void .add(int index, Object value)` - adds to the end or inserts into position and pushes everything ahead of it one position.
* `boolean .addAll(int index, Collection c)`: Shift up any pre-existing elements at or beyond the index of insertion; no elements are overwritten. Returns true if invoking list changes.
* `Object remove(int index)`: Compacts the resulting list by decrementing subsequent element indices. Returns the deleted element.
* `.set(int index, Object value)`: Replaces value at index - value MUST exist.
* `int .indexOf(Object value)`: If nothing there, returns -1.
* `.sort(Comparator<Type> c)`
* `ListIterator .listIterator()`
* `ListIterator .listIterator(int index)`: begins at the specified index.
* `List subList(int start, int end)`: From start to end - 1.
* `Object clone()`: Shallow copy of the LinkedList

#### Vectors/ ArrayLists
Dynamically resizable array - when reaching limit, doubles in size. In Java, the resizing factor is 2.
Amortized time to insert.
* `void ensureCapacity(int minCapacity)`
* `void trimToSize()`: Trims capacity to list's current size().
* `int lastIndexOf(Object o)`
* `protected void removeRange(int fromIndex, int toIndex)`: removes all elements with indices between (fromIndex, toIndex] (excluding at toIndex).

```Java
List<Integer> specifiedCapacity = new ArrayList<Integer>(3); // sets initial capacity
List<Integer> honeydew = new ArrayList<Integer>();
    // can initialize with a collection or do .addAll()
List<String> fruits = new ArrayList<String>(Arrays.asList("cantelope","strawberry", "raspberry")); // new ArrayList<Type>(Collection c)

for (String sweet : fruits) {
    System.out.println(sweet);
}
Iterator<String> iter = fruits.iterator();
while(iter.hasNext()) {
    System.out.println(iter.next());
}
        
fruits.forEach((tmp) -> {
    System.out.println(tmp);
});
/* ternary operator (boolean) ? (value if true) : (value if false);  */
        int tmp = (fruits.contains("honeydew")) ? fruits.indexOf("honeydew") : -1;
String firstIndex = fruits.get(0);
```

Vectors are synchronized.

```Java
Vector<String> vec = new Vector<String>();
vec.add("value");
System.out.println(vec.get(0));
```
#### Linked Lists
* `Object getFirst()`
* `Object getLast()`
* `int indexOf(Object o)`: First occurence of o. -1 if does not contain.
* `int lastIndexOf(Object o)`
* `void addFirst(Object o)`
* `void addLast(Object o)`
* `void add(int index, Object element)`: IndexOutOfBoundsException if index < 0 or index > size().
* `boolean add(Object o)`: Appends to end.
* `boolean addAll(Collection c)`: Append to end in order that the collection's iterator returns them.
* `boolean addAll(int index, Collection c)`: Adds starting at the specified index.
* `Object remove(int index)`
* `boolean remove(Object o)`
* `Object removeFirst()`
* `Object removeLast()`

```Java
LinkedList<Type> list = new LinkedList<Type>(Collection c); // or no collection c
```

#### Custom Linked List
Sequence of Nodes, each with data and pointers to other nodes
Constant time to add/remove from the beginning

* `.addFirst(value)`
* `.addLast(value)`
* `.getFirst()`
* `.getLast()`

Access, Search, Space O(n)

Insert, Delete O(1)

> Finding the midpoint: 
> Iterate with two pointers, one advancing by 1 each time, the other by 2. When the second pointer reaches the end, the first will be at the midpoint, provided the length is even.

##### Singly Linked List
Each node points to the next.
```Java
        Node head;
        Node foot;
        public class Node {
            int data;
            Node next;
            Node prev;
            Node(){this.data=-1;}
            Node(int data){ this.data = data; }
        }
        public void addEnd(int newData) {
            Node newNode = new Node(newData);
            newNode.next = null;
            newNode.prev = foot;
            foot = newNode;
        }
```
##### Doubly Linked List
Each node has pointers to the next and the previous.

## Stack (LIFO)
Push to the top of the stack and pop off the top. 
LinkedList where add to and remove from one side. Subclass of Vector.

```Java
Stack<Integer> cup = new Stack<Integer>();
```

* `boolean .empty()`
* `Object .push(element);`
* `Object .pop()`: Removes and returns top element of the stack.
* `Object .peek()`: Returns element at the top, but doesn't remove.
* `int search(Object element)`: Returns offset from top of the stack, else if not found, returns -1.

### Persistence/ Immutability
If an item is added or deleted, maintain the previous version and return a new one with the modifications
```Java
class Node() {
        private Object myItem;
        private Node next = null;
        Node(Object item, Node next){
            myItem = item;
            this.next = next;
        }
        Object get() {
            return myItem;
        }
        Node next() {
            return next;
        }
    }
    void PersistentStackType() {
        private Node top = null;
        PersistentStackType(Object item){
            top = new Node(item, null);
        }
        boolean isEmpty() {
            return (top == null);
        }
        Object peek() {
            if(isEmpty()) { return null; }
            return top.get();
        }
        /* alters */
        Object pop() {
            if(isEmpty()) { return null; }
            Object ret = top.get();
            top = top.next();
            return ret;
        }
        /* alters */
        PersistentStackType push(Object item) {
            return new PersistentStackType(new Node(item, top));
        }
        
    }
```

## Queue (FIFO)
Insert at the end and delete from the start.
LinkedList where you add to and remove from opposite sides.

Ideally no null values
```Java
Queue<Integer> eye = new LinkedList<>();
```

* `.add()`
* `.poll() or .remove()`
* `.peek()`
* `.isEmpty()`

### PriorityQueue
No null
Objects must be comparable, or order by natural ordering
Head is the least element
```Java
PriorityQueue<Integer> trix = new PriorityQueue<Integer>();
```

## Heap
Come in min heaps or max heaps.

Insert new elements left to right to fill up the heap. Each parent will only have up to two children.

Can store in an array.

Finding indices:
```Java
int parentIndex = (index - 2) / 2;
int leftChild = index * 2 + 1;
int rightChild = index * 2 + 2;
```

### Min Heap
Root element is the minimum. All children are greater than their parents.

```Java
public class MinHeap {
    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    private int leftChild(int index) { return items[getLeftChildIndex(index)]; }
    private int rightChild(int index) { return items[getRightChildIndex(index)]; }
    private int parent(int index) { return items[getParentIndex(index)]; }

    private void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException();
        return items[0];
    }

    public int poll() {
        if (size ==0) throw new IllegalStateException();
        int root = items[0];
        items[0] = items[size - 1]; // set last added element as root
        size--;
        heapifyDown(); // bubble the new root down to its proper place
        return root;
    }

    public void add(int item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp(); // bubble up most recently added = last item
    }

    public void heapifyUp() {
        int index = size - 1; // most recently added element, which may be out of order
        while (hasParent(index) && parent(index) > items[index]) {
            // if this element is less than its parent, it needs to be above this parent
            swap(getParentIndex(index), index);
            index = getParentIndex(index); // keep up with your original element as you move it up
        }
    }

    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            // if has right child, will definitely have a left child, so only need to check for that
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                // if child is greater than the parent, need to move the parent down and switch it with its smaller child
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex; // keep up with element as move down
        }
    }
}

```

### Binary Minimum Heap

* Root is a minimum and all of the levels are as full or as left as possible
* Root at Arr[0], parent of i: Arr[i/2], left child of i: Arr[(2*i)+1], right child of i: Arr[(2*i)+2]
* This is level-mode traversal
* Sorts array in O(n log(n)) time

```Java
interface BinaryMinHeap { // auto abstract and public
        boolean insert(int k);
        boolean delete(int i);
        boolean changeVal(int i, int newVal);
        int getMin();
        int extractMin();
        void BinaryMinHeap(int i);
    }
    void BinaryMinHeapType() {
        //int minHeap.getMin( (a1, a2) -> return ());
        BinaryMinHeap BinHeap = new BinaryMinHeap() {
        private int[] array;
        private int residency = 0;
        private int capacity = 0;
        public void BinaryMinHeap(int count){
            array = new int[count];
            capacity = count;
        }
        private void swap(int i1, int i2) {
            int tmp = array[i1];
            array[i1] = array[i2];
            array[i2] = tmp;
        }
        int parent(int i){ return (i-1)/2;}
        int leftChild(int i) { return 2*i+1; }
        int rightChild(int i) { return 2*i+2; }
        
        //Arrays.sort(BinaryMinHeap, (i1, i2) -> array[i1] - array[i2]);
        private void sort() {
            sort(0);
        }
        BiPredicate<Integer, Integer> compare = (i, j) -> (j < capacity && array[j] > array[i]);
        private void sort(int i) {
            while(compare.test(parent(i), i) && i != 0) {
                System.out.println("Child: "+array[i]+" Parent: "+array[parent(i)]);
                swap(i, parent(i));
                i = parent(i);
            }
            int smallest = i;
            while(compare.test(i,  leftChild(i))) {
                System.out.println("LeftChild: "+array[leftChild(i)]+" Parent: "+array[i]);
                smallest = leftChild(i);
            }
            while(compare.test(i,  rightChild(i))) {
                System.out.println("RightChild: "+array[rightChild(i)]+" Parent: "+array[i]);
                smallest = rightChild(i);
            }
            if (smallest != i) {
                swap(i, smallest);
                sort(smallest);
            }
        }
        public boolean insert(int k) {
            if(residency >= capacity) return false;
            int i = residency++;
            array[i] = k;
            sort(i);
            return true;
        }
        public boolean delete(int i) {
            if(residency < i+1) {
                return false;
            }
            array[i] = array[capacity-1];
            residency--;
            return true;
        }
        public int getMin() {
            if(residency < 1) {
            return array[0];
            }
            return 0;
        }
        public int extractMin() {
            int tmp = getMin();
            delete(0);
            return tmp;
        }
        public boolean changeVal(int i, int newVal) {
            if(capacity < i) {
                return false;
            }
            array[i] = newVal;
            sort(i);
            return true;
        }
        public void printAll() {
            printBFS(0);
        }
        public void printBFS(int i) {
            if(i < residency) {
                System.out.print(" "+ i);
            }
            printBFS(leftChild(i));
            printBFS(rightChild(i));
            
        }
    };
```
## Hash Tables/ Dictionary
Synchronized and slow - thread safe as only one thread can access and modify at one time.
Does not allow null for both key and value.
* `.put(key, value)`
* `.get(key)`

```Java 
        Hashtable<Integer, String> top = 
                      new Hashtable<Integer, String>(int size?, float fillRatio?); 
```
> fillRatio is how full the hash table can be before it is resized. Between 0.0 and 1.0.

## Sets
Also a type of Collection
Unordered, no repeats
* `.size()`

### Hashset
Does not allow duplicate values - maintains a unique list.
* `.add()`
* `.remove()`
* `.contains()`
```Java
Set<String> hashbrowns = new HashSet<String>();
TreeSet sortedSet = new TreeSet<String>(hashbrowns); // Collection c
// int capacity
// int capacity, float fillRatio or loadCapacity


```

#### Iteration
```Java
Iterator<String> iter = hashbrowns.iterator();
while(iter.hasNext()) {
    System.out.println(iter.next());
}
```
### LinkedHashSet
Linked list of entries in insertion order. Hash code is used as index.

### TreeSet
Maintains order by sorting with Comparator or comparable.
* `Comparator .comparator()`: null if natural ordering is used.
* `Object .first()`
* `Object .last()`
* `SortedSet subSet(Object start, Object end)`: returns elements from start to end - 1.
* `SortedSet .headSet(Object end)`: returns elements less than end that are in the invoking set.
* `SortedSet .tailSet(Object start)`: returns elements greater than or equal to the start that are in the invoking set.
```Java
new TreeSet();
new TreeSet(Collection c);
new TreeSet(Comparator comp);
new TreeSet(SortedSet ss);
```

## Maps
Unique keys, values

At each index, there is a bucket, often a linkedList or tree, of {key, value} pairs.
* `Object .get(Object k)`: Returns associated value.
* `Object .put(Object k, Object v)`: Overwrites any previous value associated with the key k. Returns null if key did not already exist, else returns the previous value linked to the key.
* `void .putAll(Map m)`
* `Object remove(Object k)`: Removes entry with key k.
* `boolean .containsKey(Object k)`
* `boolean .containsValue(Object v)`
* `Set .entrySet()`
* `Set .keySet()`
* `Collection values()`: As there can be duplicate values but not duplicated keys.

Entries: {unique key, value} pairs.
* `boolean .equals(Object obj)`
* `Object .getKey()`
* `Object .getValue()`
* `Object .setValue(Object v)`: Sets the vlaue for entry to v. If this is not the correct type, throw ClassCastException. If map doesn't permit null keys and v is null, NullPointerException. If map cannot be changed, UnsupportedOperationException.
* `int .hashCode()`

```Java 
int index = hashFunction(key) % array.length;
```

High number of collisions: all _N_ {key, value}s stored at the same index - will take O(N) to search
Minimal number of collisions: O(1) to lookup.
Or implement with balanced binary search tree for O(log N) lookup time and a smaller array

* rehashing: when a load factor is reached, say load = .75, when the map is 75% full it will double in size and redefine all of the hashCodes
* don't use hashmap in multi-threaded apps because if two threads resize the map, could get an infinite loop in a bucket with a collision
* Keys are best when they're wrapper classes like String or Integer :
* need to be immutable so get the same hashcode for the same input
* null keys map to index 0

### HashMap
O(1) lookup and insertion.

Stored order of keys is essentially arbitrary.

Array of Linked Lists.

Not syncrhonized - not thread safe.

> Don't use HashMap in multi-threaded apps because if two threads resize the map, could get an infinite loop in a bucket with a collision.

Accepts null for key and value.

Keys are objects, so need to use Integer or String to use int and string as keys.

* `.put(key, value)`
* `.get(key)`
* `.remove(key, Optional value)`
>    Will remove only if mapped to that value
* `.replace(key, value)`
* `boolean .containsKey(key)`
* `boolean .containsValue(value)`
* `Set .entrySet()`
* `Set .keySet()`
* `Collection .values()`

```Java
Map<String, Integer> breakfast = new HashMap<String, Integer>();
Map<String, Integer> meals = new HashMap<String, Integer>(breakfast);
Map<String, Integer> specifiedCapacity = new HashMap<String, Integer>(5); // intitializes capacity to a certain amount.
Map<String, Integer> breakfast = new HashMap<String, Integer>(int capcity, float fillRatio); // when the fill ratio is met, will double in size
```
##### Thread-Safe HashMap
```Java
Collections.synchronizedMap(new HashMap<K,V>());
```
##### Iteration
For each loop: 

for (Type key : set)

```Java
for (Map.Entry mapElement : map.entrySet()) { 
    Type key = (Type)mapElement.getKey(); 
    Type modifiedValue = ((Type)mapElement.getValue() + 10); 
    System.out.println(key + " : " + modifiedValue); 
} 
```

ForEach Lambda:
```Java
        map.forEach((key, value) -> {System.out.println(key + " : " + (value + 10));}); 
```

Iterate over just keys or values:

Iterator:
if want to be able to remove while iterating.
```Java
 // Getting an iterator 
Iterator iter = map.entrySet().iterator(); 

while (iter.hasNext()) { 
    Map.Entry mapElement = (Map.Entry)iter.next(); 
    Type modifiedValue = ((Type)mapElement.getValue() + 10); 
    System.out.println(mapElement.getKey() + " : " + modifiedValue); 
} 
```
### WeakHashMap
Stores only weak references to its keys, so if a key is no longer referenced outside of the WeakHashMap, its key-value pair can be garbage collected.

### LinkedHashMap
Linked list of entries in order of insertion.
* `new LinkedHashMap(Map m)`
* `new LinkedHashMap(int capacity)`
* `new LinkedHashMap(int capacity, float fillRatio)`
* `LinkedHashMap(int capacity, float fillRatio, boolean Order)`: If order is true, store by order of last access, else insertion order (default).
* `protected boolean removeEldestEntry(Map.Entry eldest)`

### IdentityHashMap
Uses reference equality when comparing elements

### TreeMap
O(log N) lookup and insertion. Sorted, rapid retrieval.

Naturally ordered keys that implement the ``Comparable`` interface.

Uses a red-black tree so can get, put, and remove in O(log(n)) time so can re-sort.

Will also allow you to output the next x pairs after a given key.

Maintains order by sorting with Comparator or comparable.
* `Comparator .comparator()`: null if natural ordering is used.
* `Object .firstKey()`
* `Object .lastKey()`
* `SortedMap subMap(Object start, Object end)`: returns elements with keys greater than or equal to start and less than end.
* `SortedMap .headMap(Object end)`: returns elements less than end that are in the invoking set.
* `SortedMap .tailMap(Object start)`: returns elements greater than or equal to the start that are in the invoking set.

```Java
TreeMap<String, Integer> aspen = new TreeMap<String, Integer>(Comparator comp);
TreeMap<String, Integer> beech = new TreeMap<String, Integer>(Map m);
TreeMap<String, Integer> chestnut = new TreeMap<String, Integer>(SortedMap sm); // intializes with same sorted order as sm

```

### LinkedHashMap
O(1) lookup and insertion.

Keys ordere by insertion order.

Uses doubly-linked buckets.

Useful for caching, where insertion ordre matters.

### IdentityHashmap

## Graphs
Nodes connecting to other nodes

Graphs often reprensented as arrays of numbers.
```Java
graph[i][j] = flowCapacity;
```
where i and j are two vertexes. 0 means there is no direct edge connecting them to each other.

### Trees
Starts with a root node. Each node has zero (in the case of a leaf node) or more (branch) child nodes. No cycles.

Height is the longest length of the path from root to leaf. At least theta(log n), exactly if balanced. Height is from below, to a leaf, which is the maximum of the heights of its children + 1. Depth of a node is its distance to the root.

Quicker than arrays at insertion/deletion and slower than unordered linkedLists

O(log(n))

#### Binary trees
Up to two children, where all keys to the left of the root are less than the root which is less than all keys to the right.

* All binary trees have the smallest possible height: O(log(n))
     * Parents have at most 2 children
     * Max nodes at level i: 2^(i-1)
     * Max nodes of tree height h: 2^h-1
     * Min height/levels for n Nodes: log(n+1) for log base 2
     * Min levels for L leaves: log(L)+1
     * Full binary tree (0-2 children): leaf nodes = internal nodes + 1
     * Complete binary trees mean all levels are completely filled except, possibly, the last lev el that has all keys as left as possible.

Predecesors are a node's left subtree's right-most child, if it exists, or the 
```Java
// Recursive
public static Node findPredecessor(Node root, Node prec, int key) {
    // base case
    if (root == null) {
        return prec;
    }

    // if node with key's value is found, the predecessor is the maximum node its left subtree - i.e. the rightmost node in the left subtree
    if (root.data == key) {
        if (root.left != null) {
            return findMaximum(root.left);
        }
    } else if (key < root.data) {
        // if given key is less, recur for the left subtree
        return findPredecessor(root.left, prec, key);
    } else {
        // given key is more than the root node, recur for the right subtree
        // update predecessor
        prec = root;
        return findPredecessor(root.right, prec, key);
    }
    return prec;
}
// Iterative
public static Node findPredecessorIterative(Node root, int key) {
    Node prec = null;
    while (true) {
        // if key is less than root, visit left subtree
        if (key < root.data) {
            root = root.left;
        } else if (key > root.data) {
            // if key is more than root, visit right subtree
            // update predecessor
            prec = root;
            root = root.right;
        } else {
            // if find node with key's value, predecessor is rightmost node in its left subtree
            if (root.left != null) {
                prec = findMaximum(root.left);
            }
            break;
        }
        // key doesn't exist in the tree
        if (root == null) {
            return null;
        }
    }
    return prec;
}

// Find right-most node.
public static Node findMaximum(Node root) {
    while (root.right != null) {
        root = root.right;
    }
     return root;
}

    Node inOrderSuccessor(Node root, Node n) { 
        // if the right subtree of node exists, the successor is there, so find the leftmost node of the right subtree
        if (n.right != null) { 
            return minValue(n.right); 
        } 
        // else the sucessor is an ancestor - travel up the parent pointers until find a node that is a left child - its parent is the successor
        Node p = n.parent; 
        while (p != null && n == p.right) { 
            n = p; 
            p = p.parent; 
        } 
        return p; 
    } 
  
    /* Given a non-empty binary search tree, return the minimum data   
     value found in that tree. Note that the entire tree does not need 
     to be searched. */
     // leftmost node
    Node minValue(Node node) { 
        Node current = node; 
  
        /* loop down to find the leftmost leaf */
        while (current.left != null) { 
            current = current.left; 
        } 
        return current; 
    } 

    // Without parent pointers
    
    Node inOrderSuccessor(Node root, Node n) { 
        if (n.right != null) {
            return minValue(n.right);
        }
        Node succ = null; 
  
        // Start from root and search for successor down the tree 
        while (root != null) { 
            if (n.key < root.key) { 
                succ = root; 
                root = root.left; 
            } else if (n.key > root.key) {
                root = root.right;
            } else {
                break;
            }
        }
    } 
  
    return succ; 
} 

```


##### Binary Search Trees
Relative ordering and quick insert time

Ordered children.
Example: all left descendents <= _n_ are less than all right descendents for each parent node of value _n_.
Can also define by having all duplicates on one side or the other, or no duplicates at all
Left are less,
Right are greater

Balanced if O(log n) insert and find.
Full if no node has only one child.
Complete if every level is fully filled, fromt left to right, except maybe the last level.
Perfect if full and complete.

2^k-1 nodes for _k_ levels.

#### Self-Balancing Binary Trees
Maintain In-Order ordering.
##### AVL Tree
Have left and right subtree heights differ by at most +- 1.

Most unbalanced if, say, the right subtree is always 1 larger than the left.

Balancing one level may affect the balance of the next above.
```Java
T1, T2 and T3 are subtrees of the tree 
rooted with y (on the left side) or x (on 
the right side)           
     y                               x
    / \     Right Rotation          /  \
   x   T3   - - - - - - - >        T1   y 
  / \       < - - - - - - -            / \
 T1  T2     Left Rotation            T2  T3
Keys in both of the above trees follow the 
following order 
 keys(T1) < key(x) < keys(T2) < key(y)
 ```

 Insert new node, w. Starting from it, travel up and find the first unbalanced node, z, with some child y between z and the newly inserted node w.

 * Right Rotation - node x inserted into the left subtree of z, making node z unbalanced with some child y between them. Make y the new root, z the right child (because z > y), x the left child. z gets y's right child as its left child.
 ```Java
         z                                      y 
        / \                                   /   \
       y   T4      Right Rotate (z)          x      z
      / \          - - - - - - - - ->      /  \    /  \ 
     x   T3                               T1  T2  T3  T4
    / \
  T1   T2
```
 * Left Rotation - same but symmetric. Switch the child in the unbalanced subtree with the subtree's root. In this case, the former parent is now the right subtree and the previous left child of the child remains there.
 ```Java
  z                                y
 /  \                            /   \ 
T1   y     Left Rotate(z)       z      x
    /  \   - - - - - - - ->    / \    / \
   T2   x                     T1  T2 T3  T4
       / \
     T3  T4
```
 * Left-Right Rotation - Combination. The right child of a left child makes the root unbalanced. Need to switch the child and grandchild, and then the new child with the parent.
 ```Java
     z                               z                           x
    / \                            /   \                        /  \ 
   y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
  / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
T1   x                          y    T3                    T1  T2 T3  T4
    / \                        / \
  T2   T3                    T1   T2
  ```
 * Right-Left Rotation - Combination. The left child of a right child makes the node unbalanced.
 ```Java
   z                            z                             x
  / \                          / \                         /    \ 
T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
    / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
   x   T4                      T2   y                  T1  T2  T3  T4
  / \                              /  \
T2   T3                           T3   T4
```

Code:
Normal BST insertion.

Update height of current node.

Get balance factor - difference between heights of children for the current node.

If balance factor > 1, then unbalanced and need to rotate. Check for case by comparing newly inserted key with key in the left subtree's root.

If balance factor < -1, then unbalanced. Check for case by comparing newly inserted key with key in the right subtree's root.
```Java
class Node { 
    int key, height; 
    Node left, right; 
  
    Node(int d) { 
        key = d; 
        height = 1; 
    } 
} 

class AVLTree { 
    Node root; 
  
    // A utility function to get the height of the tree 
    int height(Node N) { 
        if (N == null) 
            return 0; 
        return N.height; 
    } 
  
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    // A utility function to right rotate subtree rooted with y 
/*
         y                                      x 
        / \                                   /   \
       x   T1      Right Rotate (y)          z      y
      / \          - - - - - - - - ->      /  \    /  \ 
     z   T2                               T4  T3  T2  T1
    / \
  T4   T3
*/
    Node rightRotate(Node y) { 
        Node x = y.left; 
        Node T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Return new root 
        return x; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
/*
  x                                y
 /  \                            /   \ 
T1   y     Left Rotate(x)       x      z
    /  \   - - - - - - - ->    / \    / \
   T2   z                     T1  T2 T3  T4
       / \
     T3  T4
*/
    Node leftRotate(Node x) { 
        Node y = x.right; 
        Node T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
    // tree.root = tree.insert(tree.root, 25); 
    Node insert(Node node, int key) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(key)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key); 
        else if (key > node.key) 
            node.right = insert(node.right, key); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    } 

    Node deleteNode(Node root, int key) {  
        // STEP 1: PERFORM STANDARD BST DELETE  
        if (root == null)  
            return root;  
  
        // If the key to be deleted is smaller than the root's key, then it lies in left subtree  
        if (key < root.key)  {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key)  {
            // Key is larger than the root's key, so in the right subtree
            root.right = deleteNode(root.right, key);
        } else {
            // Same as root's key, is the node to be deleted  

            // node with only one child or no child  
            if ((root.left == null) || (root.right == null)) {  
                Node temp = null;  
                if (temp == root.left) {
                     temp = root.right;
                } else {
                    temp = root.left;
                } // temp has the contents of the non-empty child, if it exists
  
                if (temp == null) { 
                    // No child 
                    temp = root;  
                    root = null;  
                } else {
                    // One child
                    root = temp; // copy the contents of the non-empty child
                }    
            } else {  
                // node with two children: Get the inorder successor (smallest in the right subtree), leftmost node
                Node temp = minValueNode(root.right);  
  
                // Copy the inorder successor's data to this node  
                root.key = temp.key;  
  
                // Delete the inorder successor  
                root.right = deleteNode(root.right, temp.key);  
            }  
        }  
  
        // If the tree had only one node then return  
        if (root == null) {
            return root;
        }
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        root.height = max(height(root.left), height(root.right)) + 1;  
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
        // this node became unbalanced)  
        int balance = getBalance(root);  
  
        // If this node becomes unbalanced, then there are 4 cases  
        if (balance > 1 && getBalance(root.left) >= 0)  
            return rightRotate(root);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(root.left) < 0)  
        {  
            root.left = leftRotate(root.left);  
            return rightRotate(root);  
        }  
   
        if (balance < -1 && getBalance(root.right) <= 0)  
            return leftRotate(root);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(root.right) > 0)  
        {  
            root.right = rightRotate(root.right);  
            return leftRotate(root);  
        }  
  
        return root;  
    }
  
    // A utility function to print preorder traversal 
    // of the tree. 
    // The function also prints height of every node 
    void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.key + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    } 
}
```
##### Red-Black Tree

#### Splay Tree


# Algorithms

## Traversal

### In-order
Take left subtree down the the bottom, then root, then start with right subtree.

Prints binary trees in order.

```Java
public void traverseInOrder(Node node, ArrayList output) {
            if (node != null) {
                traverseInOrder(node.left, output);
                output.add(node.key);
                traverseInOrder(node.right, output);
            }
        }
```

### Pre-order
Current node, then left children, then right children.
```Java
public void traversePreOrder(Node node, ArrayList output) {
            if (node != null) {
                output.add(node.key);
                traversePreOrder(node.left, output);
                traversePreOrder(node.right, output);
            }
        }
```
### Post-order
Left children, right children, then current node.

```Java
public void traversePostOrder(Node node, ArrayList output) {
    if (node != null) {
            traversePostOrder(node.left, output);
            traversePostOrder(node.right, output);
            output.add(node.key);
    }
        }
```

## Search

### Breadth-First Search BFS Queue
Finds the shortest path from the root to all vertices

Visits all the nodes of a level before going to the next
```Java
Object[] printBFS(BinaryTree maple) {
        Queue<Node> eye = new LinkedList<Node>(); // linkedList or priorityQueue in java
        // priorityQueue stores in natural order, linkedList in order placed.
        eye.add(maple.root);
        Object[] bfs = new Object[maple.size];
        Map<Node, Integer> distances = new HashMap<Node, Integer>();
        distances.put(maple.root, 0);
        int i = 0;
        Set<Node> marked = new HashSet<Node>();
        marked.add(maple.root);
        bfs[i] = maple.root.key;
        while(!eye.isEmpty() && eye != null) {
            Node neuron = eye.poll();
            // for all neighbors
            if(!marked.contains(neuron.left) && neuron.left!=null) {
                marked.add(neuron.left);
                bfs[i++]=(neuron.left.key);
                // if unweighted
                distances.put(neuron.left, distances.get(neuron)+1);
                eye.add(neuron.left);
            }
            if(!marked.contains(neuron.right) && neuron.right!=null) {
                marked.add(neuron.right);
                bfs[i++]=(neuron.right.key);
                // if unweighted
                distances.put(neuron.right, distances.get(neuron)+1);
                eye.add(neuron.right);
            }
        }
        return bfs;
    }

public void traverseLevelOrder(Node start, ArrayList output) {
            if (start == null) {
                return;
            }
            Queue<Node> thisLevel = new LinkedList<>();
            thisLevel.add(start);
            while(!thisLevel.isEmpty()) {
                Node node = thisLevel.remove();
                output.add(node.key);
                if (node.left != null){
                    thisLevel.add(node.left);
                }
                if (node.right != null) {
                    thisLevel.add(node.right);
                }
            }
        }
    }
```

### Depth-First Search (DFS)
Stack and marked set
Goes as deep as possible in every child, finds the all vertices reachable from the root.
```Java
Object[] printDFS(BinaryTree beech) { // Tree tree
        Stack<Node> cup = new Stack<Node>();
        Set<Node> marked = new HashSet<Node>();
        Object[] dfs = new Object[beech.size];
        int i = 0;
        cup.add(beech.root);
        while(!cup.isEmpty()) {
            Node knew = cup.pop();
            dfs[i++] = knew.key;
            if(!marked.contains(knew)) {
                marked.add(knew);
        // for all neighbors
                if(knew.left != null && !marked.contains(knew.left)) {
                    cup.add(knew.left);
                }
                if(knew.right != null && !marked.contains(knew.right)) {
                    cup.add(knew.right);
                }
            }
        }
        return dfs;
    }
```

## Find all paths from a source
Store visited[0 or 1 for all vertices v] in current path

Mark the current node as visited
Check if it is the destination node
    If it is, print the list<Integer>
Recur for all neighbors of the current vertex, so long as its not visited yet (checking for cycles)
    If it isn’t visited
    Add it to the pathList
    recur(neighbor, destination, boolean[] isVisited, List localPathList)
    // by now, it’s found the destination again
    So remove the current neighbor vertex from the pathList
Now mark the original vertex as not visited so can use in other paths.
Traverse in-, pre-, or post-order.


### Binary Search

In sorted Arrays:

Look for an element x by comparing x to the midpoint of the array. If it's less than the midpoint, search the left half of the array - finding that point at 25%; otherwise, search the right side of the array. Repeat treating the half as a subarray, that you find the midpoint of and then determine which subhalf to search next. Repeat until element x is found or the subarray is 0.

Iterative:
```Java
int binarySearch(int[] array, int x) {
    int low = 0;
    int high = array.length - 1;
    int middle;

    while (low <= high) {
        middle = (low + high) / 2;
        if (array[middle] < x) {
            // search the right side next
            low = middle + 1;
        } else if (array[middle] > x){
            // search the left side next
            high = middle - 1;
        } else {
            // x has been found!
            return middle;
        }
    }

    return -1; // Error
}
```

Recursive:
```Java
int binarySearchRecursive(int[] array, int x, int low, int high) {
    if (low > high) return -1; // Error

    int middle = (low + high) / 2;
    if (array[middle] < x) {
        // search the right side next
        return binarySearchRecursive(array, x, middle + 1, high);
    } else if (array[middle] > x) {
        return binarySearchRecursive(array, x, low, middle - 1);
    } else {
        return mid;
    }
} 
```

## Sorting
For small arrays N <= 20, InsertionSort is faster than QuickSort. MergeSort uses additional storage - QuickSort needs just a few variables. QuickSort doesn't work as well as MergeSort does for large datasets.

QuickSort is preferred for arrays. MergeSort os preferred for LinkedLists. In general QuickSort is faster as it exhibits good cache locality. MergeSort has a consistent speed.

QuickSort is unstable while MergeSort is stable - that is, in case of a tie, MergeSort will not re-order (thereby maintaining the original order or equal keys) while QuickSort will. This is evident when sorting key-value pairs that share the same key.


Complexity
|Algorithm|Best Time|Average Time|Worst Time|Worst Space|
|---|---|---|---|---|
|Linear Search|__O(1)__|O(n)|O(n)|__O(1)__|
|Binary Search|__O(1)__|__O(log n)__|__O(log n)__|__O(1)__|
|Bubble Sort|O(n)|_O(n^2)_|_O(n^2)_|__O(1)__|
|Selection Sort|_O(n^2)_|_O(n^2)_|_O(n^2)_|__O(1)__|
|Insertion Sort|O(n)|_O(n^2)_|_O(n^2)_|__O(1)__|
|Merge Sort|O(n log n)|O(n log n)|O(n log n)|O(n)|
|Quick Sort|O(n log n)|O(n log n)|_O(n^2)_|O(log n)|
|Heap Sort|O(n log n)|O(n log n)|O(n log n)|__O(1)__?|
|Bucket Sort|__O(n + k)__|__O(n + k)__|_O(n^2)_|O(n)|
|Radix Sort|__O(n k)__|__O(n k)__|__O(n k)__|O(n + k)|
|Counting Sort|__O(n + k)__|__O(n + k)__|__O(n + k)__|O(k)|
|Tim Sort|O(n)|O(n log n)|O(n log n)|O(n)|
|Cubesort|O(n)|O(n log n)|O(n log n)|O(n)|
|Shell Sort|O(n)|O((n log n)^2)|O((n log n)^2)|__O(1)__|

### BubbleSort
Start at the beginning of the array. Swap the first two elements if the first is greater than the second. Then move on to the next pair (third and fourth). Continue making sweeps of the entire array until it is sorted.

For each pair, swap them

Funtime Worst Case: O(n^2)

Modified, at each pass, to check if the array is already sorted
Best case: O(n) best

Space: O(1)

BubbleSort with swap counting:
```Java
    static void countSwaps(int[] a) {
        int length = a.length;
        int swaps = 0;
        for (int i = 0; i < length; i++) {
            int roundSwaps = 0;
              for (int j = 0; j < length - 1; j++) {
               // Swap adjacent elements if they are in decreasing order
                   if (a[j] > a[j + 1]) {
                       swaps++;
                       roundSwaps++;
                       swap(a, j, j + 1);
                    }
             }
             if (roundSwaps == 0) break;
        }
        System.out.println("Array is sorted in " + swaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[length-1]);
        return;
    }

    static void swap(int[] array, int index, int otherIndex) {
        int temp = array[index];
        array[index] = array[otherIndex];
        array[otherIndex] = temp;
    }

```

### Selection Sort
Simple and inefficient. Linearly scan the array to find the smallest element and swap it with the front element. Then find the second smallest in the remaining array and continue.

Runtime: O(n^2)

Space: O(1)

### Insertion sort
Worst case if sorted in reverse order O(n^2)

### MergeSort
Best to sort linked list with const extra space, best for very large number of elements that can’t fit in memory.

Divide the array in half, sort each half, then merge them back together. Keep dividing into halves until you are merging just two single-element arrays. The merge method does all of the heavy lifting.

Runtime: O(n log(n))

```Java
int[] mergeSortHelper(int[] array, int i, int j) {
        // split into halves
        int[] L = mergeSortHelper(array, i, (i+j/2));
        int[] R = mergeSortHelper(array, (i+j)/2+1, j);
        return merge(L, R);
}
int[] merge(int[] left, int[] right) {
        // combine the halves
        int[] D = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left.length && j < right.length) {
            if(left[i]<right[j]) { D[k]=left[i]; i++; }
            else { D[k]=right[j]; j++; }
            K++;
        // put bigger or smaller
        }
        for(int ii = i; ii < left.length; ii++) {
            D[k] = left[ii];
            k++;
        }
        for(int jj = j; jj < right.length; jj++) {
            D[k] = right[jj];
            k++;
        }
// if any left over (ie weren’t the same size), just add them all
        return D;
}
```

An improved version, in O(2n) space = O(n):
```Java
void mergeSort(int[] array) {
    int[] helper = new int[array.length];
    mergeSort(array, helper, 0, array.length - 1);
}
void mergeSort(int[] array, int[] helper, int low, int high) {
    if (low < high) {
        int middle = (low + high) / 2;
        mergeSort(array, helper, low, middle); // sort left half
        mergeSort(array, helper, middle + 1, high); // sort right half
        merge(array, helper, low, middle, high); // combine halves
    }
}

void merge(int[] array, int [] helper, int low, int middle, int high) {
    // copy into helper array, as originally starts out blank
    for (int i = low; i <= high; i++) {
        helper[i] = array[i];
    }

    int helperLeftIndex = low;
    int helperRightIndex = middle + 1;
    int arrayIndex = low;

    // Iterate through helper array, comparing the left and right halves and choosing to copy the smaller back into the original array
    while (helperLeftIndex <= middle && helperRightIndex <= high) {
        if (helper[helperLeftIndex] <= helper[helperRightIndex]) {
            array[arrayIndex] = helper[helperLeftIndex];
            helperLeftIndex++;
        } else {
            array[arrayIndex] = helper[helperRightIndex];
        }
        arrayIndex++;
    }

    // Copy the rest of the left side into the target array, as the right side's sorted remainder is still be in place
    int remaining = middle - helperLeftIndex;
    for (int i = 0; i <= remaining; i++) {
        array[arrayIndex + i] = helper[helperLeftIndex + i];
    }
    // array is now sorted.
}

```

Done in constant space, O(1):
```Java
public int[] mergeConstSpace(int[] first, int firstsize, int[] second, int secondSize) {
        if(secondSize == 0) return first;
        int i = 0;
        int j = 0;
        int k = 0;
        Queue<Integer> storage = new LinkedList<Integer>();
        //int[] storage = new int[firstsize]; will not be > firstSize
        while(i < firstsize && j < secondSize) {
            if(!storage.isEmpty()) {
                if(storage.peek() > second[j]) {
                    storage.add(first[i]);
                    first[i++] = second[j++];
                } else {
                    storage.add(first[i]);
                    first[i++] = storage.remove();
                }
            } else {
                if(first[i] > second[j]) {
                    //storage[k++] = first[i++];
                    storage.add(first[i]);
                    first[i++] = second[j++];
                } else {
                    i++;
                }
            }            
        }
        while(!storage.isEmpty()) {
            first[i++] = storage.remove();
        }
        while (j < secondSize) {
            first[i++] = second[j++];
        }
        return first;
    }
    
    int[] mergeRec(int[] left, int liter, int[] right, int riter, int[] array, int aiter) {
        // check existence
        // if(left.length != 0) {
        //    if(right.length != 0) {
        if(right[riter]< left[liter]) {
            array[++aiter] = right[riter];
            mergeRec(right, ++riter, left, liter, array, aiter);
        } else {
            array[++aiter] = left[liter];
            mergeRec(right, riter, left, ++liter, array, aiter);
        }
            return array;
        //    }
        //}
    }
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return;
        int i = 0;
        int j = 0;

        Queue<Integer> storage = new LinkedList<Integer>();
        
        while(i < m+n) {
            if(!storage.isEmpty()) {
                if(j < n){
                if(storage.peek() > nums2[j]) {
                    if(i < m) {storage.add(nums1[i]);}
                    nums1[i++] = nums2[j++];
                } else {
                    if(i < m) {storage.add(nums1[i]);}
                    nums1[i++] = storage.remove();
                }
                } else {
                    while(!storage.isEmpty()){
                        if(i < m) {storage.add(nums1[i]);}
                        nums1[i++] = storage.remove();
                    }
                }
            } else {
                if(i < m && j < n){
                  if(nums1[i] > nums2[j]) {
                    //storage[k++] = first[i++];
                    storage.add(nums1[i]);
                    nums1[i++] = nums2[j++];
                    } else {
                        i++;
                    }  
                } else {
                    // all values from the first array are either already sorted
                    while(j < n){
                        nums1[i++] = nums2[j++];
                    }
                }
            }            
        }
    }
```

### QuickSort
Pick a random element to partition the array, so that all numbers less than the element come before all elements greater than it. 
Repeatedly partitioning the array will eventually sort it.

Best case: the partitioning element is the median, which would divide the array into two equal halves of length n/2.

Average Runtime: O(n log(n))

Worst Case Runtime: O(n^2)

Space: O(log(n))

Space-inefficient version:
```Java
int[] QuickSort(int[] array) {
        int pivot = array[0]; // choose first element as the pivot
        int[] left = new int[array.length]; // all elements less than the pivot
        int liter = 0;
        int[] right = new int[array.length]; // all elements greater than the pivot
        int riter = 0;

        for( int i = 0; i <= array.length; i++) {
            if (array[i] <= pivot) {
                left[liter++] = array[i]; // all elements <= pivot go on the left side
            } else {
                right[riter++] = array[i]; // all other elements go on the right 
            }
        }
        left = QuickSort(left); // sort the left side
        right = QuickSort(right); // sort the right side

        for(int i = 0; i < left.length; i++) {
            array[i] = left[i]; // copy into the return array
        }
        for (int i = left.length; i < right.length; i++) {
            array[i] = right[i-left.length]; // copy into the return array
        }
        return array;
    }
```

Better space-wise: 

```Java
// sort increasingly smaller subsections of the array
void quickSort(int[] array, int leftIndex, int rightIndex) {
    int index = partition(array, leftIndex, rightIndex);

    if (leftIndex < index - 1) { // sort to the left of index
        quickSort(array, leftIndex, index - 1);
    }
    if (rightIndex > index) { // sort to the right of index
        quickSort(array, index, rightIndex);
    }
}

int partition(int[] array, int leftIndex, int rightIndex) {
    int pivot = array[(leftIndex + rightIndex) / 2]; // picked pivot halfway through the array to distribute the load

    while (leftIndex <= rightIndex) { // until left and right meet eachother, about in the middle

        while (array[leftIndex] < pivot) leftIndex++; // elements less than the pivot can stay on the left side

        while(array[rightIndex] > pivot) rightIndex--; // elements greater than the pivot can stay on the right side

        // Get here once have found some left element that needs to go on the right side and some right element that needs to go on the left
        // Swap elements, then advance the left and right indices and go through the loop again
        if (leftIndex <= rightIndex) {
            swap(array, leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
    }
    return leftIndex; // this is the location that pivot should end up at
}
```

Iterative:
```Java
static int partition(int array[], int low, int high) {
    int pivot = array[high];
    int i = low - 1;
    
    for (int j = low, j < high; j++) {
        if (array[j] <= pivot) {
            i++;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    // swap pivot into its proper place
    i++;
    int temp = array[i];
    array[i] = array[high];
    array[high] = temp;

    return i;
}

static void quickSortIterative(int[] array, int low, int high) {
    int stack = new int[h - low + 1];
    int top = -1;
    // push initial values to stack
    stack[++top] = low;
    stack[++top] = h;

    // keep popping from stack until it is empty
    while (top >= 0) {
        high = stack[top--];
        low = stack[top--];

        int pivotPosition = partition(array, low, high);

        //optimizaiton: first push indexes of the smaller half, to reduce stack size

        // elements to the left of pivot pushed to stack
        if (pivotPosition - 1 > low) {
            stack[++top] = low;
            stack[++top] = pivotPosition - 1;
        }
        // elements to the right side of pivot are pushed to stack
        if (pivotPosition + 1 < high) {
            stack[++top] = pivotPosition + 1;
            stack[++top] = high;
        }
    }
}
````

#### Random
With a random pivot, worst case isn’t as bad
```Java
Random lolXD = new Random();
Int pivot = lolXD.nextInt(array.length - 1);
```
##### Median of 3 
Best-case scenario is when the pivot is the median of the (sub)array.

Look at first, last, and middle of the array. Choose the one in the middle as the pivot, as this is more likely to be closer to the median.

```Java
public static int[] quicksort(int[] array, int low, int high) {
    if (array.length <= 1) return array;
    
    if (low < high) {
        // sort low, middle, and high
        int middle = (low + high) / 2;
        if (array[middle] < array[low]) {
            swap(array, low, middle);
        }
        if (array[high] < array[low]) {
            swap(array, low, high);
        }
        if (array[high] < array[middle]) {
            swap(array, middle, high);
        }

        // Place pivot at high to get it out of the way
        swap(array, middle, high);

        int pivotLocation = partition(array, low, high);
        quicksort(array, low, pivotLocation - 1);
        quicksort(array, pivotLocation + 1, high);
    }
}
public static int partition(int[] array, int low, int high) {
    int pivot = array[high];
    int leftWall = low - 1; // will be the wall in front of which all values are <= pivot. All beyond should be > pivot.
    for (int j = low; j < high; j++) {
        if (array[j] <= pivot) {
            leftWall++;
            swap(array, j, leftWall);
        }
    }
    swap(array, high, leftWall + 1); // proper place of pivot, will all below in front of and all above behind.
    return leftWall + 1;

    // int i = low, j = high - 1;

    // while(i <= j) {
    //     while(array[i] < pivot) {
    //         i++;
    //     }
    //     while(array[j] > pivot) {
    //         j--;
    //     }
    //     if (i <= j) { 
    //         int temp = array[i];
    //         array[i] = array[j];
    //         array[j] = temp;
    //         i++;
    //         j--;
    //     });
    // }
    // array[high] = array[i]; // restore pivot to its proper place
    // array[i] = pivot;

    // return i;
}


// partition(int[] array, int low, int high) {
//     int pivot = array[high];
//     int leftWall = low;

//     for (int i = low, int j = high - 1; i <= j;) {
//         while(array[++i].compareTo(pivot) < 0) {
//             ;
//         }
//         while(pivot.compareTo(a[--j]) < 0) {
//             ;
//         }
//         swap(array, i, j);
//     }
//     swap(array, i, high); // pivot now in proper position
//     return i;
// }
```

### HeapSort binary heap
Fast

Build max heap to sort elements in ascending order .

A heap is just a tree, just put the elements as you would a tree array, with kids (i*2)+1 or +2, left to right. In constnat space.

Max heap: parent node is always >= child nodes = Build binary tree

Check if bigger than parent, if so, swap - do the same with the values in the array

Left lower, right higher from first number as the root

Now go back to original argument array and get the last one, replace the root with this, reduce the size of the heap

Swap the root arr[0] with the last and delete the last node from the heap - prune it from the tree, don’t remove from the graph, just have a pointer to current last

Keep doing , remembering to swap once you’ve made the heap until there is only one element left, this is your minimum

```Java
 void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
```

### Radix Sort
Sorts integers and some other data types using the fact that ints have a finite number of bits. Iterate through each digit of the number and group numbers by each digit.

Sort each of the groupings by the next digit until the whole array is sorted.

Runtime: O(kn), where n is the number of elements and k is the number of passes of the sorting algorithm.

### CountingSort
When the range of data _k_ isn’t that much different from the range of indexes _n_.

O(n+k)

Space (n+2^k)

```Java
General var = specificImplementation;
List<type> var = ArrayList<type>(capacity); // note size = 0
```

### Tim Sort
### Cube Sort
### Shell Sort

# Java

## Lambda functions
Functional interfaces: only one abstract method
```Java
    interface anonInterface {
        int op(int a, int b);
        default anonInterface swap() {
            return (a, b) -> op(b, a);
        }
    }
    private int applyOp(int a, int b, anonInterface oppenheimer) {
        return oppenheimer.op(a,  b);
    }
    void printAnon() {
        anonInterface addition = (a, b) -> a + b;
        anonInterface multiplication = (a, b) -> a * b;
        System.out.println("2 * 400 = " + applyOp(40, 2, multiplication));
    }
    /*
     * Runs in O(n/2) = O(n) time
     */
    int[] reverseArray(int arr[]) {
        return reverseArrayHelper(arr, 0, arr.length);
    }
    private int[] reverseArrayHelper(int arr[], int start, int end){
        if (start >= end) {
            return arr;
        }
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
        return reverseArrayHelper(arr, start+1, end-1);
    }
```

## Selection median
```Java
    /*
     * Find kth value in unsorted list
     */
    Random lolXD = new Random();
    int findValue(int k, int[] array) {
        int[] left = new int[array.length-1];
        int liter = 0;
        int[] right = new int[array.length-1];
        int riter = 0;
        int[] pivotArr = new int[array.length];
        int piter = 0;
        int pivot = lolXD.nextInt(array.length - 1);
        // may need try/catch block
        for (int i = 0; i < array.length; i++) {
            if(array[i] == array[pivot]) {
                pivotArr[piter++] = array[i];
            }
            else if(array[i]<array[pivot]) {
                left[liter++] = array[i];
            } else {
                right[riter++] = array[i];
            }
        }
        if (k == liter) {
            return array[pivot];
        } else if (k < liter) {
            return findValue(k, left);
        } else {
            return findValue(k-pivot, right);
        }
    }
    /*
     * Find median of medians
     */
    int findMedian(int[] array) {
        //for(int i = 0; i < Math.ceil(array.length/5); i++) {
            
        //}
        // sort in groups of 5, find median of medians in array of medians
        // select to find the n/10th element in the array
        // partition all elements around this median, with k being its rank
        // if i== k, return k
        // if i < k, recurse over Select(L, k-1, i)
        // else i > k, Select(R, n-k, 9-k) T(n-k)
    }
}
```
### QuickSelect
Find the k-th smallest element in an unsorted list using a pivot to partition the array such that every element to the left of the pivot is less than it and every element to the right is greater than it (as with QuickSort). 

Then can compare the sizes of the subarrays using the pivot's proper index to figure out which part we will be searching for k in - if the pivot's index is more than k, recur over the left side, else if it is the same, we've found it! Otherwise, recur over the right.

```Java
public static int partition(int[] array, int low, int high) {
    int pivot = array[high], pivotLocation = low;
    for (int i = low; i <= high; i++) {
        if (array[i] < pivot) {
            // if less than the pivot, swap with something greater than the pivot that was previously skipped over for being >= pivot
            int temp = array[i];
            array[i] = array[pivotLocation];
            array[pivotLocation] = temp;
            pivotLocation++;
        }
    }
    // Swap pivot into its final, proper location
    int temp = array[high];
    array[high] = array[pivotLocation];
    array[pivotLocation] = temp;

    return pivotLocation;
}

public static int kthSmallest(int[] array, int low, int high, int k) {
    // partition
    int partition = partition(array, low, high);

    if (partition == k) {
        // found it!
        return array[partition];
    } else if (partition < k) {
        // if partition index is less than k, search the right side.
        return kthSmallest(array, partition + 1, high, k);
    } else {
        return kthSmallest(array, low, partition - 1, k);
    }
}
```
# Iterator
Cycle through elements in a collection, obtaining or removing elements. 

Each collection class has a `c.iterator()` method that returns an iterator to the start of the collection.
* `boolean iter.hasNext()`: true if there are more elements.
* `Object iter.next() throws NoSuchElementException`: so best to check if .hasNext() first.
* `void iter.remove()`: Removes current element. Need to precede by a call to next() else throws IllegalStateException.

ListIterator allows for bidirectional traversal and the modification of elements.
* `void liter.set(Object obj)`: Assigns obj to the current element (the last returned by a call to liter.next() or liter.previous()).
* `void liter.add(Object ob)`: Inserts obj into the list in front of the element that will be returned by the next call to `liter.next()`.
* `boolean liter.hasPrevious()`
* `Object liter.previous()`
* `int liter.nextIndex()`: If no next element, returns size of the list.
* `int liter.previousIndex()`: if no previous element, returns -1.



# Comparator
Return 
* -1 if less than, as -1 < 0
* 0 if equals, as == 0
* 1 if greater than, as 1 > 0

## Custom comparable
```Java
class SortByCustom implements Comparable<Type> {
    public int compare(Type a, Type b) {
        // custom sorting method. Example:
        return (a > b ? 1 : 0) - (a < b ? 1 : 0);
        // if a is after b, 1 - 0 = 1
        // if a is equal to b, 0 - 0 = 0
        // if a is before b, 0 - 1 = -1
    }
    // optional, only if want to override:
    public boolean equals(Object obj) {
        // returns true if obj and this are both comparator objects that use the same ordering
    }
}
Collections.sort(ArrayList<Type> ar, new SortByCustom());
// Now ar is sorted.
```

Or more involved: 

```Java
Class implements Comparable<className>, Comparator<classname>{
    private Type variable;
    public int compare(className o1, className o2){
        // comparison function
        return o1.variable - o2.variable;
    }
    public int compareTo(className a) {
        return (this.variable).compareTo(a.variable);
    }
}
List<String> definedOrder = // define your custom order
    Arrays.asList("Red", "Green", "Magenta", "Silver");

Comparator<Car> comparator = new Comparator<Car>(){

    @Override
    public int compare(final Car o1, final Car o2){
        // let your comparator look up your car's color in the custom order
        return Integer.valueOf(
            definedOrder.indexOf(o1.getColor()))
            .compareTo(
                Integer.valueOf(
                    definedOrder.indexOf(o2.getColor())));
    }
};
Comparator<Car> carComparator = Comparator.comparing( c -> definedOrder.indexOf(c.getColor()));

List<Object> objList = findObj(name); Collections.sort(objList, 
new Comparator<Object>() { 
    @Override 
    public int compare(Object a1, Object a2) { 
        return a1.getType().compareToIgnoreCase(a2.getType()); 
    } 
});
```

# Input
Streams are sequences of data. Using the java.io package, can support many data types.

## Streams from keyboard input
System.in - Standard input, usually uses the keyboard. 

System.out - Standard output, usually a computer screen.

System.err - Standard error, used to output usually to a computer screen.

```Java
      // Read until user presses 'q':
      InputStreamReader cin = null;

      try {
         cin = new InputStreamReader(System.in);
         System.out.println("Enter characters, 'q' to quit.");
         char c;
         do {
            c = (char) cin.read();
            System.out.print(c);
         } while(c != 'q');
      }finally {
         if (cin != null) {
            cin.close();
         }
      }
```

## Streams from Files

Java NIO Class Files to generate Stream<String> of a Text file, where each line becomes an element:
```Java
Path path = Paths.get("C:\\file.txt");
Stream<String> streamOfStrings = Files.lines(path);
Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
```

InputStream reads data from a source. FileInputStreams can take files, FileDescriptor objects, or string names from which to read.

OutputStream writes data to a destination. FileOutputStreams read from files.

Operates as Byte Streams.
```Java
void dealingWithInputStreams() throws IOException { // uses specific input stream
        InputStream input = new FileInputStream("c:\\path\fileName.txt");
        int data = input.read(); // input.read(byte[], optional int offset, optional int length);

        FileOutputStream output = null;
        File f = new File("output.txt");
        output = new FileOutputStream(f); // creates file if pass a string path and no file with that name is at that location

        String dirname = "/tmp/user/java/bin";
        Fild d = new File(dirname);
        d.mkdir(); // returns true on success. Failure if path already exists or the entire path does not exist yet so can't make the directory in it
        d.mkdirs(); // creates a directory and all parents of it too.

        String[] paths = d.list(); // array of files and directories

        while (data != -1) {
            //op(data)
            try {
                data = input.read();
                output.write(data);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        input.close();
        output.close();
    }
```

Character streams perform input and output for 16-bit unicode. FileReader and FileWriter read and write two bytes at a time and read as characters. They can take Files, FileDescriptors, or String filenames.
```Java
      FileReader in = null;
      FileWriter out = null;

      try {
         in = new FileReader("input.txt");
         out = new FileWriter("output.txt");
         
         int c;
         while ((c = in.read()) != -1) {
            out.write(c);
         }
      }finally {
         if (in != null) {
            in.flush(); // writes the content of the buffer to the destination and makes the buffer empty for further data to store but it does not close the stream permanently. Otherwise, some data might be buffered.
            in.close(); // flushes and closes the stream permanently
         }
         if (out != null) {
            out.close();
         }
      }
   }
```
RandomAccessFiles behave like large arrays of bites. Accepts a File or String filename, and a String mode. Can read and write to as you please - replacing existing parts if desired. The mode is "r" (read) or "w" (write) or "rw" (combination, either "d" synchronous writing to disk or "s" updates meta data synchronously as well).

## Scanners
 ```Java   
    void dealingWithScanners() { // uses available input stream
        Scanner scan = new Scanner(System.in); // or new Scanner(FileReader(fileName));
        String tilSpace = scan.next();
        String tilLine = scan.nextLine();
        String[] tilLineSplit = tilLine.split("\\s+");
        int l = scan.nextInt(); // InputMismatchException
        int s = Integer.parseInt(tilLineSplit[0]); // NumberFormatException
        float o = scan.nextFloat();
        scan.close();
    }
    void dealingWithOutput() {
        //System.out object is an instance of the PrintStream class, which is an extension/ type of OutputStream
        System.out.print("hey!");
        System.out.println("/nho!"); // can override public String toString() in custom objects so they print out what you want to see
        
    }
```

String Tokenizer is quicker than split:
```JavaScript
public static void main(String[] args) {
    List<String> tokens = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    StringTokenizer st = new StringTokenizer(scanner.nextLine());
    while (st != null && st. hasMoreElements()) {
        tokens.add(st.nextToken());
    }
    // OR
    while(scanner.hasNext()) {
        tokens.add(scanner.next()); // converts tokens to String values
    }

    System.out.println(tokens);
    scanner.close();
}
```
#### BufferedReader

Buffered Reader is faster than scanner. Scanner is slwoer because it parses the data while reading it.

Best to wrap a BufferedReader around any Reader whose read() operations may be costly, such as FileReaders and InputStreamReaders. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, converted into characters, and then returned, which can be very inefficient. BUfferedReader minimizes the number of I/O operations by reading chunks of characters each time and storing them in an internal buffer. While the buffer has data, the reader only needs to read from the buffer rather than directly from the underlying stream.

Takes a Reader or any input stream in its constructor.

Unlike Scanners, BufferedReaders are thread-safe. Scanners can parse primitive types and strings using regex. Scanners have a fixed buffer size, while BufferedReader's default size is larger. Scanner hides IOExceptions while BufferedReaders force us to handle them. 
https://www.baeldung.com/java-buffered-reader

# Concepts
### Big O Time and Space

### Recursion

#### Recurrence Relations

## Bit Manipulation
* & AND
* | OR
* ^ XOR
* ~ NOT (compliment)
* << left shift
* \>> right shift
* \>>> zero-fill right shift

Can be used in assignment. Example:
```Java
(C <<=2) == (C = C << 2);
```

*2 to the nth power is equivalent to shifting to the left by n

### Shortcuts
* x ^ 0s = x
* x ^ 1s = ~x
* x ^ x = 0
* x & 0s = 0
* x & 1s = x
* x & x = x
* x | 0s = x
* x | 1s = 1s
* x | x = x

### Background
Computers store ints in two's complement representation, where negative numbers are represented as the two's complement (flip each bit then add 1 to the entire value) with 1 as the sign bit.

### Basic Operations

01000 - 1 = 00111
Binary_number & (binary_number - 1) = 0 if binary_number has only one 1.

#### Shifts
Arithmetic shifts fill with 0 (left) or the most significant bit (right);
Logical shifts fill with 0.

\>> Arithmetic right shift divides by 2, filling in with the sign bit.
Arithmetic left shift multiples by 2.

#### Get Bit at i
Create a mask, where every bit value is 0 except at i, where the value is 1. Can then AND to zero all bits except the one at i. Compare the entire result with 0 to get the value at i, as anything greater than 0 means that the bit at i is 1.

```Java
boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
}
```

#### Flip Bit at i
Shift 1 over by i bits, filling with 0 (as left shifts do) so it is now at position i. OR the values so that only the value at bit i will change to 0 if it was 1 and 1 if it was 0. No other bits will be affected.

```Java
int setBit(int num, int i) {
    return num | (1 << i);
}
```

#### Clear Bit at i
Reverse the mask we used in setBit by negating it (so something like 0010 turns into 1101) so that the only 0 is at position i. AND this value to set the bit at position i to 0.

```Java
int clearBit(int num, int i) {
    return num & (~(1 << i));
}
```
#### Clear All Bits Within Range (MSB, i)
To clear all bits from the most significant one (farthest left if we're using big endian) to i, inclusive, create a mask with 0s from the MSB to i and 1s everywhere else. To do this, create a mask with 1 at i, then subtract 1, giving a sequence of 0s up to and including position i, followed by 1s. AND this value to leave just the last i bits.

```Java
int clearBitsMSBThroughI(int num, int i) {
    return num & ((1 << i) - 1);
}
```

#### Clear All Bits Within Range (i, 0)
To clear all bits from position i to 0 (the least significant bit), inclusive, take a sequence of all 1s (which is -1 in base ten) and shift them left by i + 1 bits, filling in with 0s as left shifts do. This results in a sequence of 1s followed by i 0s, which can be ANDed to act as a mask.

```Java
int clearBitsIThrough0(int num, int i) {
    return num & ((-1 << (i + 1)));
}
```

#### Set Bit at i
To set the ith bit to a given value, clear it first so you know what is there using a mask that is ANDed with the given number. In a separate number, create a number where bit i is equal to the given value and all other values are 0. This can be done by simply shifting the given value left by i as it will be filled in with 0s. OR these two results so the bit at i will change to 1 if the given value is 1 or remain 0 if the given value is 0.

```Java
int updateBit(int num, int i, boolean bitIs1) {
    int valueAtI = bitIs1 ? 1 : 0;
    return (num & (~(1 << i)) | (valueAtI << i));
}
```

## Mathematical Concepts

### Power of Primes

Every positive integer can be decomposed into a product of primes. Can represent as a product of primes `p` to some power `e`.

Therefor, for a number x that divides a number y (`x\y` or `mod(y, x) = 0`), all of the primes in x's facorization must be in y's prime factorization, each to some power `e_xi <= e_yi`.

The greatest common divisor is the prime factors to the power `min(e_xi, e_yi)`. 

The least common multiple is the product of prime factors each to the power `max(e_xi, e_yi)`.

This makes the product of the gcd and lcm equal xy.

#### Checking for Primality
Naive: iterate 2 throuvgh n-1, checking for divisibility of i by each number (2, i - 1), returning as soon as one `(n % i ) == 0`.

Improvement: Only need to iterate through to the square root of n, as every factor above that previously had its complement, or has no complement with which it cleanly divides into n.

Sieve of Eratosthenes:
Really need to check if number n is divisible by prime numbers less than sqrt(n), as all non-prime numbers are divisible by primes. Only prime numbers have the prime factorization of themselves and 1.

```Java
// Set the multiples of prime to false.
void crossOff(boolean[] flags, int prime) {
    /* Cross off the remaining multiples of prime. Start with (prime * prime) because if we have a k * prime, where k < prime, this value would have already been crossed off in a prior iteration. */
    for (int i = prime * prime; i < flags.length; i += prime) {
        flags[i] = false;
    }
}

// Find the next highest value of flags that is still true, i.e., has not been crossed out yet.
int getNextPrime(boolean[] flags, int prime) {
    int next = prime + 1;
    while (next < flags.length && !flags[next]) {
        next++;
    }
    return next;
}
// Get an array that has true at all prime indexes below max.
boolean[] sieveOfEratosthenes(int max) {
    boolean[] flags = new boolean(max + 1);
    int count = 0;

    init(flags); // Set all values to true other than indexes 0 and 1.
    int prime = 2;

    while (prime <= Math.sqrt(max)) {
        crossOff(flags, prime);
        prime = getNextPrime(flags, prime);
    }

    return flags;
}
```

Improvement: Use only odd numbers in the array, as any even number above 2 is not going to be prime.

```Java
int oddNumber = indexInOnlyOddArray * 2 + 1;
```

#### Primal Power
Multiplying two large primes is easy. Factoring their product back into those two primes is not. This is a trapdoor - a function that's easy one way but difficult to reverse, despite the fact that each number has only one prime factorization.

Super basic representation: The public key `pq` where `p` and `q` are both large primes can be used to encrypt messages that can be decrypted only by knowing the individual values.

Real world: Hyprid systems. Public key encryption is really slow, so  it's mostly used to distribute temporary keys (often called session keys) which are used to encrypt and decrypt messages symmetrically (i.e., the same key is used for both encryption and decryption). The temporary key is first encrypted with the public key, which is then decrypted with the private key of the reciever and then used for that session.

### Probability
Probability of getting the intersection of A and B (where both A and B are true) is the Probability of getting B given A is true (the percentatge of A that's also in B) times the probability of getting A (whether or not this includes the section that overlaps with B).

* A | B is A given B is true
* A u B is A or B, which includes A n B
* A n B is A and B

> P(A and B) = P(B given A) P(A)

Since P(A and B) = P(A given B) P(B),  

> P(A given B) = P(B given A) P(A) / P(B)

Also known as Bayes' Theorem.

The probability of A or B happening, which includes the probability of A and B both happening:

> P(A or B) = P(A) + P(B) - P(A and B), to not double count the overlap

If A and B are independent events, i.e., A happening doesn't impact  vthe probability of B happening and vice versa, then

> Independent: P(A and B) = P(A) P(B) as P(B | A) = P(B)

If A and B are mutually exclusive, i.e., if A happens, B cannot happen and vice versa, then 

> Mutually exclusive: P(A or B) = P(A) + P(B) as P(A and B) = 0

#### Permutations - Order Matters
Permutation of n objects taken k at a time
Out of n obvjects available for selection, form result number of permutations using k objects selected at a time where the order of selection matters.

> nPk = n! / (n - k)!

Can then multiply the number of permutations with the probability of each occuring, like p * p * (1-p) where you're using the probability of selecting only 2 out of 3 objects to meet some true condition where the order of selection matters.

#### Combinations
Combination of n objects taken k at a time, where the order of the k objects doesn't matter.
> nCk = n! / (k! (n - k)!)

## Memory (Stack vs. Heap)
```
===============     Highest Address (e.g. 0xFFFF)
|             |
|    STACK    |
|             |
|-------------|  <- Stack Pointer   (e.g. 0xEEEE)
|             |
.     ...     .
|             |
|-------------|  <- Heap Pointer    (e.g. 0x2222)
|             |
|    HEAP     |
|             |
===============     Lowest Address  (e.g. 0x0000)
```

Stack grows down from the top of the memory region
> Return addresses, local variables
Heap grows up from the bottom of the memory region
> Malloc into heap. 
> Executable (data?) section also there
Threads have own stack space

(?) In x86 architecture, most strings end at higher memory addresses than they started, so if you have a buffer overflow, you’re writing up towards the stack
Stack canary before return pointer, so won’t be returning to some malicious code, such as an executable stored in the stack
Don’t let the stack be executable, but then can still store shellcode in the heap, can disable with some library calls, return-oriented programming
Randomize where store memory within memory space, so can’t store some executable code and know where to find it, but possible to load into a fixed space, so may need position-independent executables (PIE)
Morris, Witty, Slammer, Blaster worms

Frames


### Stack smash
Highest addres 0xFFFF

Stack contains 
* Variables into function, in order
* Then return address
* Then frame pointer
* Then the values that the function allocates, in order
  > Let’s call one buffer

Lower address 0x0000

Want to change return address

Add more than the size of the buffer to set the return address

`Strcopy` doesn’t check the length of the source, but will just add to destination location byte by byte
For a user process with multiple threads, kernel won’t know which one violated something and will just quit the entire process

Heartbeat message: server, are you still there? If so, respond “STRING” (# letters)

    Make sure communication lines are open, and both server and client can read from each other

Heartbleed exploited this buffer overflow
 
### Dynamic Programming
Memoization - remember the result of a previous calculation so can avoid having to recalculate.

Look for repeated subproblems and cache results.

# Object-Oriented Programming (OOP)
Everything is an object that can perform and have functions performs on them, have data associated with them, encourages reusability.
Objects are specific instances of classes, defined by classes or interfaces. They can have states (instance variables) and behaviors (methods), which classes describe, like templates or blueprints.

### Variable Types
```Java
Type variable [ = value][, variable [ = value] ...] ; // all of the same Type
```

* Local: declared within the method and only visible there (stack)
* Instance variable: declared in the class, outside of methods and constructors (heap dynamically allocated malloc())
* Class or static variables: only one copy, shared by all different objects in a class - once declared, not changed - constant = static final

Local variables are declared in methods, constructors, and blocks. They're created only when their scope is entered and will be destroyed after exit, so they're only visible within that scope. They are stored on the stack and have no default values.

Instance variables are declared in classes, outside of blocks. They're created when a new instance of the object is created and will be destroyed upon its destruction. They're visible within that entire class and even outside (`objectReference.varName`), depending on their access modifier. They are stored on the heap and have default values:
* 0 for numbers
* false for Booleans
* null for object references

There is only one copy of each `static` variable per class, regardless of the number of instances of the class - they all have access to the same variable with the same value. For this reason, most are used as constants. They're stored in static memory and are created when either a static member of the class is referred to or when an instance of the class is created. Most are declared public. They are stored in static memory and have the above default values. You don't need an instance of the class to access the variable.

```Java
public class ClassName extends ParentClass implements Interface, OtherInterface {
    accessModifier Type instanceVariable;
    private Type instVar = defaultValue;

    static private double VAR_NAME; // private static variable
    public static final String CONSTANT_STRING = "Constant"; 
    // No-Argument Constructor
    // if none defined, Java compiler will build a default which will initialize all member variables to zero
    public ClassName() {
        super();
        this.instanceVariable = defaultValue;

        // no explict return type
    }
    // Parameterized Constructor
    public ClassName(Type initial, Type arguments) {
        super(); // if extending anything
        this.instanceVariable = initial;

        Type localVar = tempValue;

    }
    public method() {
        return value;
    }
}
// Declaration = instantiation new and initialization ClassName()
ClassName class = new ClassName();
```

### Variable Visibility
* `public`: whole world
* `protected`: package, subclass
* `private`: only class
* No modifier: package and class

See Access Modifiers below.

### Context
`this()` refers to the current object's constructor. `super()` refers to the super class or base class's constructor. Any call to `this()` implicitly calls `super()`. Static contexts don't have an instance, so they don't have a `this` context.

Must call `super()` in first line of the derived class constructor or compiler will automatically invoke it. You can pass parameters or even call `super.method()` for a specific method as defined in the super class.

### Coupling
How much direct knowledge an element has of another.
### Encapsulation
Encapsulation hides implementation.

Bind data and code together as a single unit, safe from modification.

> Use private variables, public getters and setters instead to modify and view the variables, if you want to allow such things. This is also called data hiding.

```Java
public class Encap {
    private String name;
    // Getter
    public String getName() {
        return name;
    }
    // Setter
    public void setName(String name) {
        this.name = name;
    }
}
```

If want something to be read- or write-only, just don't include the setter or getter.

### Inheritance Extends
Inheritance of properties: subclass or derived class inherits from the superclass or base class. They inherit the implementation of methods, but this can be overridden. 

A subclass IS-A superclass type. Subclass also IS-A superclass of its own superclass.

Classes can have references to other classes, i.e. a class HAS-A reference to another class, which can help you avoid needing to inherit from multiple things at once, which is not supported in Java.
```Java
class Super {
    Type variable;
    // Super HAS-A variable, which can be a class itself
    // cannot override a final method
    final superMethod() {
    }
    // cannot be overridden, only re-declared
    static anotherSuperMethod() {
    }
    // if made private, could only be overridden by subclasses in the same package
    protected method() throws Exception, AnotherException {
    }
    // Can't override constructors
    Super() {
    }
    Super(values) {
    }
}
class Sub extends Super {
    Type variable;
    // implied Sub() { super(); }
    Sub(values) {
        super(values); // calls Super() constructor
        super.variable; // references super's variable
    }
    subMethod() {
    }
    // Same name as super class, overrides if same argument list, return can be a subtype of the original, can be less restrictive in access, can throw narrower or fewer exceptions
    public method() throws Exception {
        super.method(); // if want, can access super methods rather than call this one again
    }
}
Sub sub = new Sub();
sub.superMethod();

Super superRefToSub = new Sub(); // Super reference but Sub object
// can't call any subMethods even though instantiated with the Sub constructor.
superRefToSub.method(); // will call sub's method

Super duper = new Super();
duper = new Sub(); // can refer to subtypes of its declared type, works with declared interfaces too
```

### Interfaces are Implemented
Like contracts on how tp communicate or behave. Interfaces define methods but leave implementation up to the subclass. They're collections of abstract methods, default methods, static methods, constants, and nested types.

Interfaces are a blueprint or a contract, telling you what it can _do_ - assigns functions with return values but doesn’t define them: all methods are public abstract.

Interfaces cannot be instantiated. There are no constructors or instance fields - only static final ones. Multiple interfaces can be implemented for one class. Implementing classes can be abstract themselves.

```Java
public interface InterfaceName {
    static final Type variable = value;
    public void method(); // implicitly abstract and public
}
public interface ChildInterface extends InterfaceName {
    public void childMethod();
}
public interface AnotherInterface {
    public void anotherMethod();
}

public class Implementor implements ChildName, AnotherInterface {
    public void method() { // same return type or subtype
        implementation();
    }
    public void childMethod() {
        implementation();
    }
    public void anotherMethod() {
        implementation();
    }
}
```
Interfaces with no methods are tagging interfaces. They are used to create common parents for groups with similar uses. Classes that implement a tagging interface now have an associated datatype - that interface type.

Interfaces can have non-abstract method implementations by using the default keyword.
```Java
interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

Formula formula = new Formula() { // impelementation of the interface as an anonymous object
    @Override
    public double calculate(int a) {
        return sqrt(a * 100);
    }
};

formula.calculate(100);     // 100.0
formula.sqrt(16);           // 4.0
```
Abstract classes can implement interfaces. They can have static fields and static methods as well as field sthat are not static and final.

Default methods in interfaces cannot reference state. All fields in an interface are automatically public, static, and final and all declared or defined (default) methods are public.
### Abstraction
Abstraction: abstract class or interface and then implements. Only provides the functionality, not the implementation. User knows what it does but not how.

> Abstract cannot be instantiated (can’t create an object of it), but they can be inherited. Inheriting classes can also be abstract.

> Methods can be abstract (undefined) or concrete methods (defined), and the non-abstract child class must provide implementations to all abstract methods.

```Java
public abstract class Parent {
    private Type variable;
    public Parent() {
        variable = value;
    }
    public void concreteMethod() {
        // can be overridden
        implementation();
    }
    public abstract void abstractMethod(); // no implementation
}
public class Child extends Parent {
    // can do if want:
    public void concreteMethod() {
        override();
    }
    public void abstractMethod() {
        implementation();
    }
}

Child c = new Child();
Parent p = new Child(); // uses Child's version of concreteMethod but can't see any methods unique to child and not in parent
```

### Polymorphism
Polymorphism: variable, method, and objects can take on multiple forms, define one interface or method and implement it in multiple ways. Anything that IS-A for muliptle things. Any custom object IS-A Object.

Runtime is where you call a specific child of the class

Method overloading: class can have multiple methods with the same name but different arguments (compile time polymorphism).

You can even overload the main method, but only `public static void main(String[] args)` or `(String... args)` will be used when launched by the JVM.

### Functional Programming

# Misch.
|Power of 2|Exact Value, bits|Approximate Value|Bytes|
|---|---|---|---|
|0|1||1 bit|
|7|127|||
|8|256||1 byte|
|10|1024|1 thousand|1 KB|
|16|65,536|||
|20|1,048,576|1 million|1 MB|
|30|1,073,741,824|1 billion|1 GB|
|32|4,294,967,296||4 GB|
|40|1,099,511,627,776|1 trillion|1 TB|


## Useful Functions
### Greatest Common Divisor
Find that largest number that divides two numbers, a and b.

Same as factorizing and multiplying their common factors. Subtracting the smaller number from the larget number doesn't change the GCD (a-b, b).

* 18 = 3 * 3 *2
* 6 = 3 * 2
* 18 - 6 = 12 = 2 * 3 * 2


```Java
int gcd(int a, int b) {
    if (a == 0) return b;
    return gcd(b % a, a);
}
```

### Least Common Multiple
Smallest whole number that is a multiple of both a and b.

Prime factorization, and multiply each prime at the highest power you see. Or can multiply the nuers and divide the result by the GCD

```Java
int lcm(int a, int b) {
    if (a == 0 || b == 0) return 0;
    return a / gcd(a, b) * b; // ordered this way, has better chance of avoiding overflows
}
```

### Linear Index of 2D Array
Converting from a linear index to a 2D (row, col) coordinate:

```Java
index = row + col * rowCount
index = col + row * colCount;

row = index % rowCount;
col = index / rowCount;

row = index / colCount;
col = index % colCount;
```

### Shuffle an Array
Iterate through the array and, for each index, pick a random index and swap it.

```Java
void shuffle<T>(T[] array) {
    Random random = new Random();

    for (int i = 0; i < array.length; i++) {
        int j = i + random.nextInt(array.length - i);
        if (i != j) {
            T iData = array[i];
            T jData = array[j];

            array[i] = jData;
            array[j] = iData;
        }
    }
}

void shuffleGrid<T>(T[][] array) {
    int rows = array.length;
    int columns = array[0].length;
     int total = rows * columns;
     Random random = new Random();

    for (int i = 0; i < total; i++) {
        int j = i + random.nextInt(total - i);
        if (i != j) {
            int iRow = i / columns;
            int iCol = (i - iRow * columns) % columns;
            T iData = array[iRow][iCol];

            int jRow = j / columns;
            int jCol = (j - jRow * columns) % columns;
            T jData = array[jRow][jCol];

            array[iRow][iCol] = jData;
            array[jRow][jCol] = iData;
        }
    }
}
```

# Common Approaches
If given a string, can sort alphabetically.

Boolean frequency arrays the size of the number of chars or ints or objects.

If given a buffer at the end of an array, start iterating from there and keep an index that iterates from the end of the filled array.

Palindromes have even numbers of each character, except for one that can have an odd count, though this would make the entire palindrome length odd.


# Java
## Source File Declaration
Only one public class per source file, but potentially multiple non-public ones. Public class is the name of the source file.java.

If the class is defined inside a package, import it first, then any other inputs, then you can start writing the class.

```Java
// in command-line:
$java ClassName arguments that will appear in args[] array

// in Java file:
package packagename;
import java.io.*;

public class ClassName {
    // if want to be runnable
    public static void main(String args[]) {

    }
}
```
### System
```Java
System.out.println("Prints this string to console.");
long timeInMS = System.currentTimeMillis();
```
### Casting types
Widening casting is automatic - converts a smaller type to a larger one. You can pass an int value to a double variable.
byte -> short -> char -> int -> long -> float -> double

Narrow casting must be manual.
```Java
double myDouble = 9.78;
int myInt = (int) myDouble; // Manual casting: double to int, 9
```

Need to be careful if using a more general type:
```Java
public static void main(String []args){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String key = "Key1", val = "123";
        putInMap(map, key, val); // doesn't throw an error
        Integer test = map.get(key); // ClassCastException error
     }
     private static void putInMap(HashMap map, Object key, Object value) {
         map.put(key, value);
     }
}
```

### Generics
Type-erasure: elimates parameterized types when translates source code into Java Virtual Machine byte code. Can specify sets of related methods or types.

Syntactic sugar: Object<Type> is just Object where its instances are cast to (Type).

Cannot use primitives like int or string - must use Integer or String. Can use mutliple if separate them with commas.

Generics are not polymorphic - ArrayList<Integer> is not a subtype of ArrayList<Long> so it cannot be cast as such.

```Java
Live Demo

public class GenericMethodTest {
   // generic method printArray
   public static < E > void printArray( E[] inputArray ) {
      // Display array elements
      for(E element : inputArray) {
         System.out.printf("%s ", element);
      }
      System.out.println();
   }

   public static void main(String args[]) {
      // Create arrays of Integer, Double and Character
      Integer[] intArray = { 1, 2, 3, 4, 5 };
      Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
      Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

      System.out.println("Array integerArray contains:");
      printArray(intArray);   // pass an Integer array

      System.out.println("\nArray doubleArray contains:");
      printArray(doubleArray);   // pass a Double array

      System.out.println("\nArray characterArray contains:");
      printArray(charArray);   // pass a Character array
   }
}
```
Bounded type parameters restrict the number of types that can be passed. Specify that the type extends some class or interface to ensure this.
```Java
public class MaximumTest {
   // determines the largest of three Comparable objects
   
   public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
      T max = x;   // assume x is initially the largest
      
      if(y.compareTo(max) > 0) {
         max = y;   // y is the largest so far
      }
      
      if(z.compareTo(max) > 0) {
         max = z;   // z is the largest now                 
      }
      return max;   // returns the largest object   
   }
   
   public static void main(String args[]) {
      System.out.printf("Max of %d, %d and %d is %d\n\n", 
         3, 4, 5, maximum( 3, 4, 5 ));

      System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
         6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));

      System.out.printf("Max of %s, %s and %s is %s\n","pear",
         "apple", "orange", maximum("pear", "apple", "orange"));
   }
}
```
Classes can also be generic.

```Java
public class Box<T, B> {
   private T t;
   private B b;

   public void add(T t, B b) {
      this.t = t;
      this.b = b;
   }

   public T get() {
      return t;
   }
   public B get() {
       return b;
   }

   public static void main(String[] args) {
      Box<Integer> integerBox = new Box<Integer, Double>();
      Box<String> stringBox = new Box<String, String>();
    
      integerBox.add(new Integer(10), new Double(20.0));
      stringBox.add(new String("Hello World"), new String("It's me again."));

      System.out.printf("Integer Value :%d\n\n", integerBox.get());
      System.out.printf("String Value :%s\n", stringBox.get());
   }
}
```
### Lambda Expressions
A step towards functional programming.

```Java
(param, eters) -> // do something
```

Match with functions that take the same inputs and have the same type of outputs.

### Reflection
A way to examine or modify the behavior of methods, classes, and interfaces at runtime.

Gives information about the class to which an object belonds and its methods which can be invoked at runtime irrespective of their access specifier.

Get a new instance of a class.

Get and set object fields directly by getting a field reference, regardless of its access modifier.

```Java
/* Parameters */
Object[] tupleArgs = new Object[] { 4.2, 3.9};

/* Get Class */
Class rectangleDefinition = Class.forName("MyProject.Rectangle");

Class tupleArgsClass = new Class[] {double.class, double.class};
Constructor tupleArgsConstructor = rectangleDefinition.getConstructor(tupleArgsClass);
Rectangle rectangle = (Rectangle) typleArgsConstructor.newInstance(typleArgs);
// Equivalent to Rectangle rectangle = new Rectangle(4.2, 3.9);

Method m = rectangleDefinition.getDeclaredMethod("area");
Double area = (Double) m.invoke(rectangle);
// Equivalent to Double area = rectangle.area();
```

Used to observe or manipulate the runtime behavior of applications.

Useful for debugging as it offers direct access to methods, constructors, and fields.

Can call methods by name when you don't know the method in advance - like if user inputs a class name, parameters for the constructor, and a method name, we can use this information to create an object and call a method.

```Java
public double publicSum(int a, double b) {
    return a + b;
}

// For any public method, including static or instance, defined in the class or any of its superclasses:
Method sumInstanceMethod
  = Operations.class.getMethod("publicSum", int.class, double.class); // Get some method object by method name and types of arguments

// For any method, public, protected, default access, some private but not-inherited ones:
Method andPrivateMethod
  = Operations.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);

// Invoke on some instance and set of arguments. If static, can pass null as the instance. 
Operations operationsInstance = new Operations();
Double result = (Double) sumInstanceMethod.invoke(operationsInstance, 1, 3);
 
assertThat(result, equalTo(4.0));

// For methods with variable number of arguments, like main(String args[]), treat as if packed into an array:
Class[] argTypes = new Class[] { String[].class };
Method main = c.getDeclaredMethod("main", argTypes);
String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);
main.invoke(null, (Object)mainArgs);

```
Calling a private method outside of its defining method or a protected method outside of a subclass or its class's package throws an IllegalAccessException. `methodInstance.setAccessible(true)` suppresses these access control checks and won't throw an exception on invokation.

Getting classes through reflection:
```Java
// If have an instance of the class:
Class c = "foo".getClass(); // String

// If have type, including a primitive type, but no instance:
Class c = boolean.class; // boolean
Class c = int[][][].class; // int[][][]

// If have a primitive type:
Class c = Double.TYPE; // same as double.class
Class c = Void.TYPE; // same as void.class

// If have the fully-qualified name of a non-primitive type:
Class c = Class.forName("package.ClassName");
Class cDoubleArray = Class.forName("[D"); // same as double[].class
Class cStringArray = Class.forName("[[Ljava.lang.String;"); // same as String[][].class

// If have a subclass:
Class c = javax.swing.JButton.class.getSuperclass(); // javax.swing.AbstractButton

Class<?>[] c = Character.class.getClasses(); // all public classes, interfaces, and enums that are members or inherited members. // Character.subset, Character.UnicodeBlock

Class<?>[] c = Character.class.getDeclaredClasses(); // all of the class's interfaces, enums, and member classes // public member classes Character.Subset and Character.UnicodeBlock and one private class Character.CharacterCache

Class c = System.class.getField("out").getDeclaringClass(); // Class where members were declared. // System
Class c = Thread.State.class().getEnclosingClass(); // Class immediately enclosing the class. // Thread

// Anonymous classes don't have a declaring class but do have enclosing classes
public class MyClass {
    static Object o = new Object() { 
        public void m() {} 
    };
    static Class<c> = o.getClass().getEnclosingClass(); // MyClass
}

```
### Private Constructors and Singletons
Only the class, inner classes, and child classes can access this purely internal constructor.

Used in Singletons to ensure that there is only one instance of an object at a time.

A `getInstance()` method returns the existing instance or makes one.

```Java
class Singleton { 
    // static variable single_instance of type Singleton 
    private static Singleton single_instance = null; 
  
    // variable of type String 
    public String s; 
  
    // private constructor restricted to this class itself 
    private Singleton() { 
        s = "Hello I am a string part of Singleton class"; 
    } 
  
    // static method to create instance of Singleton class 
    public static Singleton getInstance() { // static so doesn't require an instance to initially run
        if (single_instance == null) 
            single_instance = new Singleton(); 
        return single_instance; 
    } 
} 
```

### Error Catching
Exceptions arise during compilation (checked exceptions) or execution (unchecked or runtime exceptions) and disrupt the normal flow of the program. Handle them to prevent the program from terminating abnormally.
```Java
try {
    // protected code
    // Attempt to do something
    // As soon as tries something that raises an exception, control flow moves to the catch block.
} catch (ExceptionType | AnotherExceptionType e) {
    // Can do something with the exception here
} catch (Exception e) {
    // Catches a type of exception not caught by the first catch block.
} finally {
    // Optional block
    // Regardless of the control flow above, the finally block will execute
    // This includes the catch block raising its own exception or if one of the above blocks has a "return" statement.

    // Finally won't execute if the VM exits or the thread executing it is killed prematurely.
}

// If a method doesn't handle a checked exception, use the throws keyword to postponse handling and throw to invoke an exception explicitly
public void deposit(double amount) throws RemoteException, AnotherExceptionType {
    if (amount > 0) {
        throw new RemoteException();
    }
    throw new AnotherExceptionType();
}

// File readers
public static void read() {
      FileReader fr = null;		
      try {
         File file = new File("file.txt");
         fr = new FileReader(file); char [] a = new char[50];
         fr.read(a);   // reads the content to the array
         for(char c : a)
         System.out.print(c);   // prints the characters one by one
      } catch (IOException e) {
         e.printStackTrace();
      }finally {
         try {
            fr.close();
         } catch (IOException ex) {		
            ex.printStackTrace();
         }
      }

      // do the same using a try-with-resources block which will automatically close streams and connections that are passed into it
      try(FileReader fr = new FileReader("E://file.txt")) {
         char [] a = new char[50];
         fr.read(a);   // reads the contentto the array
         for(char c : a)
         System.out.print(c);   // prints the characters one by one
      } catch (IOException e) {
         e.printStackTrace();
      } // auto closes AutoCloseable resources. Closes in reverse order listed
}

// Custom Checked Exception
class CustomException extends Exception {
    private double amount;
   //  throw new InsufficientFundsException(needs);
   public InsufficientFundsException(double amount) {
      this.amount = amount;
   }
   // e.getAmount() in catch (e) block
   public double getAmount() {
      return amount;
   }
}
```

Order for nested try..catch blocks:
```Java
public class HelloWorld{
     public static void main(String []args){
         String message = "";
        try {
            message = getMessage();
            System.out.println(message); // ABCDE, returns without throwing an exception
        } catch (Exception e) {
            message += "F"; // string needs to be defined outside of the blocks, else error
            System.out.println(message);
        } finally {
            System.out.println(message); // ABCDE
        }
     }
     public static String getMessage() throws Exception {
         String message = "A";
         try {
             throw new Exception(); // jump to catch block
         } catch (Exception e) {
             try {
                 try {
                     throw new Exception();
                 } catch (Exception ex) {
                     message += "B";
                 } // continue execution
                 throw new Exception();
             } catch (Exception x) {
                 message += "C";
             } finally {
                 message += "D"; // regardless of how it jumped around the try..catch blocks above, will get to finally.
             }
         } finally {
             message += "E";
         }
         return message; // if gets here, all exceptions were handled and none are thrown.
     }
}
```
Transfer to the parent to handle exceptions until find a block that will handle your specific exception type.

## Keywords

### Abstract
For classes - provide the implementation of an interface. Can have abstract (left to be defined by classes that extend it) and non-abstract methods.
Interfaces can only have abstract methods.

### Final
Declares a constant variable which cannot be changed; a method which cannot be overridden; or a class which cannot be inherited.

Must be initialized at declaration.

Can also apply to variable references, where it restricts it from pointing to any other object on the heap, i.e. it cannot be reassigned to another object.

Final means immutable, which means these are resources that can be safely shared among multiple threads and are side-effect-free.

### Static
Can be accessed before the class object is created - it can be used independently of any object of that class.

Can only access static members of the class and can only be called by other static methods.

### Synchronized and Volatile

## Access Modifiers
In order of increasing accessibility.

### Private
Class-level

Can only be accessed by that class, inner classes, and derived (child) classes.

Best practice: Declare variables private by default and supply getters and setters if they need to be accessed outside of the class.

### Default = (nonexplicit) Package-Private
Package-level

Accessibile by anything in the directory to which the class belongs.

#### Packages
Packages are groups of related types (classes, enums, interfaces, and annotations). Packages are used to prevent naming conflicts (common namespace), control access (access protection), and make searching through a project easier.

java.lang bundles fundamental classes, and java.io is a package for bundling input and output functions.

To use another class in the same package, you don't need to specify the package name. If you need to access something in a different package, you must use the prefix `differentPackage.thingTryingToAccess` or you can import it at the top of the file: `import differentPackage.*` (imports all exportable things in differentPackage) or `import differentPackage.thingTryingToAccess`.

Each soure file can have one package statement. If none is provided, the current default one is used.
```Java
// Compile a file into a .class file in the same destination folder (can be .):
javac -d DestinationFolder fileName.java

// in fileName:
package pkge;

class ClassName {
    ...
}
```
Many companies use reversed internet domain name for package names. A path to a file might look like:
`...\com\apple\computers\Dell.java` for `Dell.java` in `package com.apple.computers`. When `javac -d . Dell.java` is called, this compiles to `.\com\apple\computers\Dell.class` with the same path and different file name for any other classes within `Dell.java`.

### Protected
Subclass-level

Accessible to extensions of the class.

### Public
All levels

Accessible anywhere.

## Garbage Collector
Calls ``finalize()`` when it determines no more references exist for an object. A class can override this method to define custom behavior.

```Java
protected void finalize() throws Throwable {
    /* Close open files, release resources, etc. */
}
```
## Java Virtual Machine (JVM)
Runs compiled JVM bytecode (filename.class) on a range of computers, with a different implementation for each, in order to pass actual processing work to processors in their own language.

Part of the Java Runtime Environment (JRE).

Software Development Kits (SDKs) are collections of tools, like compilers, debuggers, and even frameworks. They are specific to hardware and OS combinations.

Applets are Java programs that can run in Web Browsers using the <applet> tag.

Applications are stand-alone programs that run on the local machine.

Java Applications are WORA (Write Once Run Anywhere), meaning a programmer can develop code on one system and it'll be able to run on any other Java-enabled system without adjustment.

1. Loading: The Class Loader reads the _.class_ file, generates corresponding binary, and saves the following in the Method Area of JVM Memory:
* Fully qualified name of the loaded class and any immediate parent class.
* If the _.class_ file is related to Class, Interface, or Enum.
* Modifier, Variables, and Method information.
A _java.lang.Class_ object represents this file in the JVM Heap memory. You can access this object with `instance.getClass()`.

2. Linking: Preforms the following:
* Verification: Ensure the correctness of the _.class_ file by checking for formatting and if it is generated by a valid compiler. If fails, return run-time exception _java.lang.VerifyError_.
* Preparation: Allocate memory for class variables and initialize to default values.
* (Optional) Resolution: Replace symbolic references with direct ones by searching into the Method Area to locate the referenced entitiy.

3. Initialization: Assign values to all static variables. Execute from top to bottom and parent to child. Done using three loaders:
* Boostrap or primoridal class loader: Can load trusted classes,, core java API classes from the _JAVA\_HOME/jre/lib_ directory (the boostrap path). Implemented in native languages like C or C++.
* Extension class loader: Child of boostrap class loader. Loads classes present in the extension directories _JAVA\_HOME/jre/lib/ext_ (Extension path) or any other dictory specified by the system property _java.ext.dirs_. Implemented in Java by _sun.misc.Launcher$ExtClassLoader_ class.
* System/ Applicatoion class loader: Child of extension class loader. Loads classes from the application class path using the Environment Variable which is mapped to _java.class.path_. Implemented in Java by _sun.misc.Launcher$AppClassLoader_ class.

# ClassLoader
Dynamically loads classes into the JVM's memory, when required at runtime.

ClassLoaders are classes themselves, so the bootstrap or the primordial class loader serves as teh parent and is written in native code.

When the JVM requests a class, the class loader tries to locate it and load it into runtime based on a fully qualified name. If the class isn't already loaded, it delegates the request to the parent class loader. If it still can't find the class, the child calss java.net.URLClassLoader.findClass() method to look for classes in the file system itself, else throws java.lang.NoClassDefFoundError or java.lang.ClassNotFoundException.
# Multithreaded Custom Class Loaders
When we need to load classes out of the local hard drive or a network, we need help modifying existing bytecode (weaving agents), we are creating classes dynamically, or we are implementing a class versioning mechanism while loading different bytecodes for classes with same names and packages. Browsers use to load executable content from websites (applets from remote servers instead of the local file system. Even if these applets have the same name, they are considered as different components if loaded by different class loaders).

```Java
public class CustomClassLoader extends ClassLoader {
 
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException;
    // fully qualified class name
    // resolve is false if only need to determine if class exists or not

    // final, so can't override
    protected final Class<?> defineClass(
  String name, byte[] b, int off, int len) throws ClassFormatError;
    // Converts an array of bytes into an instance of a class

    @Override // in custom implementations.v loadClass invokes if parent class couldn't find
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }
 
    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
```

Modified to avoid deadlock. Must override loadClass() to use a different delegation model. may use an internal locking scheme based on the requested class name so not requesting a lock on a class loader that is delegating to the class loader that is trying to delegate to it.

In static initializer, `registerAsParallelCapable()` and make sure all class loader classes that your custom one extends from invoke this as well.

If overriding loadClass in addition to findClass, ensure that the protected defineClass() method is called only once for each class loader + class name pair.

## Test Harness
Collection of stubs, drivers, and other supported tools needed to execute tests automatically and generate reports.

# Regex
java.util.regex allows you to pattern match with regular expressions in sets of strings.

Patterns are compiled representations of a regular expression. `compile(regex)` returns a Pattern object.

Matcher are engines that interpret the Pattern and perform match operations against an input string. `pattern.matcher()` returns a Matcher object.

PatternSyntaxExceptions are thrown when there's a syntax error in a regex pattern.
* `String getDescription()`
* `int getIndex()`
* `String getPattern()`
* `String getMessage()`: returns a multi-line string containing the description of the syntax error and its index, the erroneous regular expression pattern, and a visual indication of the error index within the pattern.

```Java
      // String to be scanned to find the pattern.
      String line = "This order was placed for QT3000! OK?";
      String pattern = "(.*)(\\d+)(.*)"; // with 3 capturing groups

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Create matcher object.
      Matcher m = r.matcher(line);

      if (m.find( )) {
         System.out.println("Found value: " + m.group(0) );
         System.out.println("Found value: " + m.group(1) );
         System.out.println("Found value: " + m.group(2) );
      } else {
         System.out.println("NO MATCH");
      }
      // Found value: This order was placed for QT3000! OK?
      // Found value: This order was placed for QT300
      // Found value: 0
```
Index methods
* `int matcher.start()`: returns start index of previous match.
* * `int matcher.start(int group)`: returns start index of the subsequence captured by the given group during the previous match operation.
* `int matcher.end()`: returns the offset after the last character matched.
* * `int matcher.end(int group)`: returns the offset after the last character of the subsequence captured by a given group from the previous match operation.

Study methods review input strings and return booleans indicating whether or not the pattern has been found.
* `boolean matcher.matches()`: attempts to match the entire region against the pattern.
* `boolean matcher.lookingAt()`: attempts to match input against the pattern, will be true if any part of the input matches.
* `boolean find()`: attempts to find the next subsequence of the input sequence that matches the pattern.
* * `boolean find(int start)`: starts searching at specified index.

Replacement methods
* `String matcher.replaceAll(String regex)`: replaces every subsequence of the input sequence that matches the pattern with the given regex replacement.
* `String matcher.replaceFirst(String regex)`: replaces the first subsequence of the input sequence that matches the pattern with the given regex replacement.
* `String quoteReplacement(String s)`: returns a literal replacement String for the specified String,which will work in `matcher.appendReplacement()`.
* `Matcher appendReplacement(StringBuffer sb, String replacement)`: does not have a terminal append-and-replace step.
* `public StringBuffer appendTail(StringBuffer sb)`: has a terminal append-and-replace step.

# Javadoc
/** documentation */ comments help to preare automatically-generated documentation. part of JDK, makes HTML documentation. You can use HTML tags in the description. There are other @tags javadoc recognizes.

```Java
// Command line to process
javadoc AddNum.java

   /**
   * This method is used to add two integers. This is
   * a the simplest form of a class method, just to
   * show the usage of various javadoc Tags.
   * @param numA This is the first paramter to addNum method
   * @param numB  This is the second parameter to addNum method
   * @return int This returns sum of numA and numB.
   */
   public int addNum(int numA, int numB) {
      return numA + numB;
   }
```
# Embedded JavaScript
Applets are Java programs that run in Web browsers, embedded in HTML pages. Need a JVM to view.
* `init`: Called after <param> tags have been processed.
* `start`: called automatically after the browser calls init. Also called whenever the user returns to the page with the applet having gone off to other pages.
* `paint`: Invoked immediately after start() and anytime the applet needs to repaint intself in the browser. Can also get parameters here.
* `stop`: Automatically called when the user moves off the page.
* `destroy : Called when the browser shuts down notmally.

```Java
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.applet.Applet;
import java.awt.Graphics;

// Must be a public class to load code.
public class CheckerApplet extends Applet implements MouseListener{ // must extend the Applet class so the browser can obtain information about the applet
   int squareSize = 50;   // initialized to default size

   public void init() {
        addMouseListener(this);
        String squareSizeParam = getParameter ("squareSize");
        parseSquareSize (squareSizeParam);
   
        String colorParam = getParameter ("color");
        Color fg = parseColor (colorParam);
   
        setBackground (Color.black);
        setForeground (fg);
   }
   private void parseSquareSize (String param) {
        if (param == null) return;
        try {
            squareSize = Integer.parseInt (param);
        } catch (Exception e) {
            // Let default value remain
        }
   }
   public void paint(Graphics g) {
      // Draw a Rectangle around the applet's display area.
      g.drawRect(0, 0, 
      getWidth() - 1,
      getHeight() - 1);

      // display the string inside the rectangle.
      g.drawString(strBuffer.toString(), 10, 20);
   }
   private Color parseColor (String param) {}
   public void mouseEntered(MouseEvent event) {
   }
   public void mouseExited(MouseEvent event) {
   }
   public void mousePressed(MouseEvent event) {
   }
   public void mouseReleased(MouseEvent event) {
   }
   public void mouseClicked(MouseEvent event) {
      parseColor("mouse clicked! ");
   }
   // no main method
}

```
```HTML
   <title>Checkerboard Applet</title>
   <hr>
   <applet code = "CheckerApplet.class" width = "480" height = "320">
      <param name = "color" value = "blue">
      <param name = "squaresize" value = "30"> 
      <!-- Parameter names are not case-sensitive. -->
      If your browser was Java-enabled, a Checkerboard would appear here.
   </applet>
   <hr>
</html>
```
As containers, Applets inherit event-handling methods. With Graphics, you can display images. Applets can also play audio clips.


Sandbox security


# Concurrency and Multithreading
Two or more threads can run concurrently, each handling a different task at the same time.

Threads go from new Thread() New -> start() Runnable -> run() Running -> (optional) sleep(), wait() Waiting -> end of execution Dead or Terminated.

Thread priorities from 1 to 10, default 5, influence the order in which threads are scheduled.

Threads
* `void start()`: Starts the thread in a separate path of execution and invokes the run() method on the Thread object.
* `void run()`
* `public final void setName(String name)`
* `public final void setPriority(int priority)`: Between 1 and 10, default 5.
* `public final void setDaemon(boolean on)`: True denotes as a daemon thread.
* `public final boolean isAlive()`: True if thread is alive - has been started and not completed.

Interaction
* `public final void join(long millisec)`: Current thread invokes on a second thread, causing current thread to block until the second terminates or the number of millisec passes.
* `public void interrupt()`: Interrupts this thread, causing it to continue execution if it was blocked for any reason.

Static methods, performed on the currently running thread.
* `public static Thread currentThread()`: Reference to the thread that invoked it.
* `public static void yield()`: Yield to other threads of the same priority that are waiting to be scheduled.
* `public static void sleep(long millisec)`: Block for at least millisec time.
* `public static boolean holdsLock(Object x)`
* `public static void dumpStack()`: Print stack trace.

Deprecated static methods to control behaviors
* `public void stop()`: Completely stops a thread.
* `public void suspend()`: Pauses execution.
* `public void resume()`: Resumes a suspended thread.

```Java
// Implement Runnable
class RunnableDemo implements Runnable { // has a run() method.
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

// Or Extend the Thread Class
class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   
   ThreadDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() { // Override the run method
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

public class TestThread {
   public static void main(String args[]) {
      Runnable hello = new DisplayMessage("Hello");
      Thread thread1 = new Thread(hello);
      thread1.setDaemon(true);
      thread1.setName("hello");
      System.out.println("Starting hello thread...");
      thread1.start();

      Runnable bye = new DisplayMessage("Goodbye");
      Thread thread2 = new Thread(bye);
      thread2.setPriority(Thread.MIN_PRIORITY); // 1
      thread2.setDaemon(true);
      System.out.println("Starting goodbye thread...");
      thread2.start();

      RunnableDemo R1 = new RunnableDemo( "Thread-1");
      R1.start();
      
      ThreadDemo T2 = new ThreadDemo( "Thread-2");
      T2.start();
   }   
}
```
Because Runnable is an interface that defines a single method run(), can use lambda expressions to define it:
```Java
Runnable task = () -> {
    String threadName = Thread.currentThread().getName();
    System.out.println("Hello " + threadName);
};

task.run(); // current thread is main

Thread thread = new Thread(task);
thread.start(); // no guarentee when runnable is invoked - could be after Done!
System.out.println("Done!");
```

The ThreadLocal class wraps a variable that only one thread can read and write to. You can have two threads with references to the same ThreadLocal variable, but each thread will only see its own ThreadLocal variable, as set with `threadLocalInstance.set("value")`. Also has get, remove, and optional generic typing.
http://tutorials.jenkov.com/java-concurrency/threadlocal.html

## Thread Synchronization
Don't want to override data or get stale data, so need to synchronize threads and their access to certain resouces. Each object in Java is associated with a monitor which a thread can lock or unlock, with only one thread at a time able to hold a lock on that monitor.

You can create synchronized blocks in your code to surround shared resources. `synchronized(objectIdentifier)` where objectIdentifier is a reference to an object whose lock associates with the monitor the synchronzied statement needs in order to be accessed.

```Java
class PrintDemo {
   public void printCount() {
      try {
         for(int i = 5; i > 0; i--) {
            System.out.println("Counter   ---   "  + i );
         }
      } catch (Exception e) {
         System.out.println("Thread  interrupted.");
      }
   }
}

class ThreadDemo extends Thread {
   private Thread t;
   private String threadName;
   PrintDemo PD;

   ThreadDemo( String name,  PrintDemo pd) {
      threadName = name;
      PD = pd;
   }
   
   public void run() {
      synchronized(PD) { // object reference is the lock
         PD.printCount();
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }

   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

public class TestThread {

   public static void main(String args[]) {
      PrintDemo PD = new PrintDemo();

      ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
      ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );

      T1.start();
      T2.start();

      // wait for threads to end
      try {
         T1.join();
         T2.join();
      } catch ( Exception e) {
         System.out.println("Interrupted");
      }
   }
}
/*
Starting Thread - 1
Starting Thread - 2
Counter   ---   5
Counter   ---   4
Counter   ---   3
Counter   ---   2
Counter   ---   1
Thread Thread - 1  exiting.
Counter   ---   5
Counter   ---   4
Counter   ---   3
Counter   ---   2
Counter   ---   1
Thread Thread - 2  exiting.
*/
```
## Interthread Communication
Nesting synchronized blocks can cause deadlocks, where a thread is waiting on a resource another thread is holding, while the other thread is waiting on a resource the first is holding.

* `public void wait()`: Causes the current thread to wait until another thread invokes the notify().
* `public void notify()`: Wakes up a single thread that is waiting on this object's monitor.
* `public void notifyAll()`: Wakes up all the threads that called wait( ) on the same object. Uncertain which thread will then get the lock.

```Java
class Chat {
   boolean flag = false;

   public synchronized void Question(String msg) {
      if (flag) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(msg);
      flag = true;
      notify();
   }

   public synchronized void Answer(String msg) {
      if (!flag) {
         try {
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      System.out.println(msg);
      flag = false;
      notify();
   } // releases lock
}

class T1 implements Runnable {
   Chat m;
   String[] s1 = { "Hi", "How are you ?", "I am also doing fine!" };

   public T1(Chat m1) {
      this.m = m1;
      new Thread(this, "Question").start();
   }

   public void run() {
      for (int i = 0; i < s1.length; i++) {
         m.Question(s1[i]);
      }
   }
}

class T2 implements Runnable {
   Chat m;
   String[] s2 = { "Hi", "I am good, what about you?", "Great!" };

   public T2(Chat m2) {
      this.m = m2;
      new Thread(this, "Answer").start();
   }

   public void run() {
      for (int i = 0; i < s2.length; i++) {
         m.Answer(s2[i]);
      }
   }
}
public class TestThread {
   public static void main(String[] args) {
      Chat m = new Chat();
      new T1(m);
      new T2(m);
   }
}
/*
Hi
Hi
How are you ?
I am good, what about you?
I am also doing fine!
Great!
*/
```
## Locks
For a synchronized block of code that would otherwise result in a race condition and lost writes:
```Java
int count = 0;
synchronized void incrementSync() {
    count++;
}
// Same as
void incrementSync() {
    synchronized (this) { // monitor lock is on this context
        count = count + 1;
    }
}

ExecutorService executor = Executors.newFixedThreadPool(2);

IntStream.range(0, 10000)
    .forEach(i -> executor.submit(this::incrementSync));

stop(executor);

System.out.println(count);  // 10000
```
This uses implicit locks. There are multiple explicit lock implementations:

### ReentrantLock
Mutual exclusion lock.
* `lock()`
* `unlock()`
* `isLocked()`
* `isHeldByCurrentThread()`
* `boolean tryLock()`: Returns true if lock is acquired. Tries without pausing the current thread.
```Java
ReentrantLock lock = new ReentrantLock();
int count = 0;

void increment() {
    lock.lock(); // acquire lock
    try {
        count++;
    } finally {
        lock.unlock(); // ensure unlocking in case of exceptions
    }
}
```

### ReadWriteLock
Pair of locks for read and write access, as it's usually safe to read mutable variables concurrently so long as no one is writing to it. Multiple threads can hold the read-lock so long as no one is holding the write-lock.
```Java
ExecutorService executor = Executors.newFixedThreadPool(2);
Map<String, String> map = new HashMap<>();
ReadWriteLock lock = new ReentrantReadWriteLock();

executor.submit(() -> { // Lambda Runnable
    lock.writeLock().lock();
    try {
        sleep(1);
        map.put("foo", "bar");
    } finally {
        lock.writeLock().unlock();
    }
});

Runnable readTask = () -> {
    lock.readLock().lock();
    try {
        System.out.println(map.get("foo"));
        sleep(1);
    } finally {
        lock.readLock().unlock();
    }
};

executor.submit(readTask);
executor.submit(readTask);

stop(executor);
```

### StampledLock
Supports read and write locks as above does, but also returns a stamp represented by a long value that can be used to either release a lock or check if it is still valid. Supports optimistic locking.
```Java
ExecutorService executor = Executors.newFixedThreadPool(2);
Map<String, String> map = new HashMap<>();
StampedLock lock = new StampedLock();

executor.submit(() -> {
    long stamp = lock.writeLock();
    try {
        sleep(1);
        map.put("foo", "bar");
    } finally {
        lock.unlockWrite(stamp);
    }
});

Runnable readTask = () -> {
    long stamp = lock.readLock();
    try {
        System.out.println(map.get("foo"));
        sleep(1);
    } finally {
        lock.unlockRead(stamp);
    }
};

executor.submit(readTask);
executor.submit(readTask);

// Optimistic Lock
executor.submit(() -> {
    long stamp = lock.tryOptimisticRead(); // always returns a stamp without blocking the current thread, whether or not the lock is actually available. 0 if a write lock is active
    // Write locks don't have to wait for the optimistic read lock to be released
    try {
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        sleep(1);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        sleep(2);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
    } finally {
        lock.unlock(stamp);
    }
});
/*
Optimistic Lock Valid: true
Write Lock acquired
Optimistic Lock Valid: false
Write done
Optimistic Lock Valid: false // still invalid even though write lock has been released
*/

stop(executor);
```

Can convert a read lock to a write lock without unlocking and locking again:
```Java
ExecutorService executor = Executors.newFixedThreadPool(2);
StampedLock lock = new StampedLock();

executor.submit(() -> {
    long stamp = lock.readLock();
    try {
        if (count == 0) {
            stamp = lock.tryConvertToWriteLock(stamp); // doesn't block but may return a 0 stamp if no write lock is currently available, so call writeLock() to block until it is available
            if (stamp == 0L) {
                System.out.println("Could not convert to write lock");
                stamp = lock.writeLock();
            }
            count = 23;
        }
        System.out.println(count);
    } finally {
        lock.unlock(stamp);
    }
});

stop(executor);
```

### Semaphores
Semaphores can maintain whole sets of permits compared to the pretty exclusive access of locks.
```Java
ExecutorService executor = Executors.newFixedThreadPool(10);

Semaphore semaphore = new Semaphore(5); // maximum of 5 have access to the longRunningTask at a time

Runnable longRunningTask = () -> {
    boolean permit = false;
    try {
        permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
        if (permit) {
            System.out.println("Semaphore acquired");
            sleep(5);
        } else {
            System.out.println("Could not acquire semaphore");
        }
    } catch (InterruptedException e) {
        throw new IllegalStateException(e);
    } finally {
        if (permit) {
            semaphore.release(); // release even if there were exceptions
        }
    }
}

IntStream.range(0, 10)
    .forEach(i -> executor.submit(longRunningTask));

stop(executor);
```

### AtomicInteger
Atomic operations can be safely performed in parallel on multiple threads without the use of the synchronized keyword or locks. Prefer atomic classes over locks in case you just have to change a single mutable variable concurrently (in a thread-safe way).

There are also AtomicBoolean, AtomicLong, and AtomicReference.
```Java
AtomicInteger atomicInt = new AtomicInteger(0); // replacement for Integer
ExecutorService executor = Executors.newFixedThreadPool(2);

IntStream.range(0, 1000)
    .forEach(i -> executor.submit(atomicInt::incrementAndGet)); // atomic operatoin

IntStream.range(0, 1000)
    .forEach(i -> {
        Runnable task = () ->
            atomicInt.updateAndGet(n -> n + 2); // arbitrary arithmetic operations
        executor.submit(task);
    });

// Sum all values 0 to 1000
IntStream.range(0, 1000)
    .forEach(i -> {
        Runnable task = () ->
            atomicInt.accumulateAndGet(i, (n, m) -> n + m); // accepts IntBinaryOperator
        executor.submit(task);
    });

stop(executor);

System.out.println(atomicInt.get());    // => 1000
```

LongAdder is an alternative to AtomicLong that can be used to consecutively add values to a number. Has methods `add()` and `increment()` that are thread-safe. `sum()` or `sumThenReset()` retrieves the actual result. 

Preferable over atomic numbers when updates from multiple threads are more common than reads. This is often the case when capturing statistical data, e.g. you want to count the number of requests served on a web server. The drawback of LongAdder is higher memory consumption because a set of variables is held in-memory as such a set is used internally to reduce contention over threads.

LongAccumulator is more generalized and built around a lambda expression of type LongBinaryOperator.
```Java
LongBinaryOperator op = (x, y) -> 2 * x + y;
LongAccumulator accumulator = new LongAccumulator(op, 1L);

ExecutorService executor = Executors.newFixedThreadPool(2);

IntStream.range(0, 10)
    .forEach(i -> executor.submit(() -> accumulator.accumulate(i))); // passes current value and i as parameters to the lambda expression op

stop(executor);

System.out.println(accumulator.getThenReset());     // => 2539
```

### ConcurrentMap
Extends the map interface. `forEach()` accepts a BiConsumer lambda with key and value passed as parameters.

a parallelismThreshold is the minimum collection size for which the operation should be executed in paralel.

```Java
ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
map.put("foo", "bar");
map.put("han", "solo");
map.put("r2", "d2");
map.put("c3", "p0");

map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));

String value = map.putIfAbsent("c3", "p1");
System.out.println(value);    // p0

String value = map.getOrDefault("hi", "there"); // returns default value there if no entry exists for this key hi
System.out.println(value);    // there

map.replaceAll((key, value) -> "r2".equals(key) ? "d3" : value); // accepts a BiFunction
System.out.println(map.get("r2"));    // d3

map.compute("foo", (key, value) -> value + value);
System.out.println(map.get("foo"));   // barbar
// also computeIfAbsent() and computeIfPresent()

map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
System.out.println(map.get("foo"));   // boo was foo
```
## Fork/Join Framework
Divide - fork - recursively break task into smaller independent subtasks until they can be executed asynchronously.

Conquer - join - recursively join results into a single result, or, if they return void, wait until the subtasks are executed.

Uses a pool of threads called the ForkJoinPool which manages threads of type ForkJoinWorkerThread.

ForkJoinPool is an implementation of ExecutorService. It doesn't create a separate thread for every single subtask, but instead has each thread store tasks in a double-ended queue, which it selects a task off the head of. When its deque is empty, it takes a task from the tail of a busy thread's deque or from the global entry queue, where the biggest pieces of work are likely to be located. This way, threads are unlikely to compete for tasks and the number of times a thread goes looking for work is reduced, as threads work on the biggest available chunks of work first.

Default thread pool is the commonPool().
```Java
ForkJoinPool commonPool = ForkJoinPool.commonPool();
public static ForkJoinPool forkJoinPool = new ForkJoinPool(2); // parallelism level of 2; uses 2 processor cores
ForkJoinPool forkJoinPool = PoolUtil.forkJoinPool;
```

To define a task, extend RecursiveAction if it returns void of RecursiveTask<V> if it returns a value of type V. Define the task's logic in the compute() method.

Create an object to represent the total amount of work, choose a threshold, define a method to divide the work, and define a method to do it.

```Java
public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr; // work
 
    private static final int THRESHOLD = 20;
 
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
 
    @Override
    protected Integer compute() { // return a list of Future
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks()) // submit tasks to common pool
              .stream()
              .mapToInt(ForkJoinTask::join) // return IntStream
              .sum();
        } else {
            return processing(arr);
        }
    }
 
    private Collection<CustomRecursiveTask> createSubtasks() { // divide the task into smaller pieces of work until pass the threshold
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
 
    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
          .filter(a -> a > 10 && a < 27)
          .map(a -> a * 10)
          .sum();
    }
}
```

To submit tasks to the thread pool:
```Java
forkJoinPool.execute(customRecursiveTask);
int result = customRecursiveTask.join();

int result = forkJoinPool.invoke(customRecursiveTask); // Fork the task and wait for the result, don't need to manually join.

```

Executors use pre-configured thread pool instances.

### ThreadPoolExecutor
Extensible thread bpool implementation.

The ThreadPoolExecutor can fine-tune thread pools of a fixed number of core threads that are kept inside all the time (corePoolSize) and some excessive threads (up to maximumPoolSize) which may bve spawned and then terminated as needed - if all core threads are busy and the internal queue is full, the pool will grow. These excessive threads are allowed to be idle for as long as the keepAliveTime allows. Only non-core threads can be removed, unless allowCoreThreadTimeOut(true).

```Java
ThreadPoolExecutor executor = 
  (ThreadPoolExecutor) Executors.newFixedThreadPool(2); // corePoolSize = maximumPoolSize = 2, keepAliveTime = 0
executor.submit(() -> {
    Thread.sleep(1000);
    return null;
});
executor.submit(() -> {
    Thread.sleep(1000);
    return null;
});
executor.submit(() -> {
    Thread.sleep(1000);
    return null;
});
 
assertEquals(2, executor.getPoolSize());
assertEquals(1, executor.getQueue().size());

Executors.newCachedThreadPool(); // corePoolSize = 0; maximumPoolSize = Integer.MAX_VALUE; keepAliveTime = 60 sec. Cached pool can grow without bounds. Good for lots of short-living tasks.
// Queue is 0 because uses a SynchronousQueue where pairs of insert and remove operations occur simultaneously

AtomicInteger counter = new AtomicInteger();
 
ExecutorService executor = Executors.newSingleThreadExecutor();
// corePoolSize = maximumPoolSize = 1; keepAliveTime = 0; immutable
executor.submit(() -> {
    counter.set(1);
});
executor.submit(() -> {
    counter.compareAndSet(1, 2);
});

```

### Futures
Futures are results of asynchronous computations. 

Callable<Type> are tasks just like Runnables, only they return a value. Executors submitting a Callable Task return a Future<Type> object that will retrieve the actual result once it exists.
```Java
Callable<Integer> task = () -> {
    try {
        TimeUnit.SECONDS.sleep(1);
        return 123;
    }
    catch (InterruptedException e) {
        throw new IllegalStateException("task interrupted", e);
    }
};
ExecutorService executor = Executors.newFixedThreadPool(1);
Future<Integer> future = executor.submit(task);

System.out.println("future done? " + future.isDone());

Integer result = future.get(3, TimeUnit.SECONDS); // block and wait for Callable to complete or for timeout to occur

System.out.println("future done? " + future.isDone());
System.out.print("result: " + result);
/*
future done? false
future done? true
result: 123
*/
executor.shutdownNow();
```
You can batch submit multiple callables and return a list of futures.
```Java
ExecutorService executor = Executors.newWorkStealingPool();

List<Callable<String>> callables = Arrays.asList(
        () -> "task1",
        () -> "task2",
        () -> "task3");

executor.invokeAll(callables)
    .stream()
    .map(future -> {
        try {
            return future.get();
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    })
    .forEach(System.out::println);
```

To return only the fastest result of a list of callables, use `invokeAny()`.

#### CompletableFuture
To compose, combine ,execute asynchronous computation steps and handle possible errors through the implementation of the CompletionStage interface. 

Getting a future blocks the current thread until the result is completed.

```Java
// If you already know the result and won't need to block to get:
Future<String> completableFuture = 
  CompletableFuture.completedFuture("Hello");

// Cancel execution:
completableFuture.cancel(false);
completableFuture.get(); // CancellationException
```

Static methods runAsync and supplyAsync create CompletableFutures out of Runnables (no return value) and Suppliers (no arguments, returns a parameterized type). They run a corresponding step of execution in another thread.
```Java
CompletableFuture<String> future
  = CompletableFuture.supplyAsync(() -> "Hello");
 
// ...
 
assertEquals("Hello", future.get());
```

thenApply accepts a function and uses it to process a result and returns a Future.
```Java
// Function
CompletableFuture<String> completableFuture
  = CompletableFuture.supplyAsync(() -> "Hello");
 
CompletableFuture<String> future = completableFuture
  .thenApply(s -> s + " World"); // if used a consumer like s -> System.out.println("Computation returned: " + s), future would be void.
 
assertEquals("Hello World", future.get());

// Runnable
CompletableFuture<String> completableFuture 
  = CompletableFuture.supplyAsync(() -> "Hello");
 
CompletableFuture<Void> future = completableFuture
  .thenRun(() -> System.out.println("Computation finished."));
 
future.get();
```

You can combine CompletableFutures and chain computation steps (mondaic design pattern).

```Java
CompletableFuture<String> completableFuture 
  = CompletableFuture.supplyAsync(() -> "Hello")
    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
 
assertEquals("Hello World", completableFuture.get());

// Future and Function:
CompletableFuture<String> completableFuture 
  = CompletableFuture.supplyAsync(() -> "Hello")
    .thenCombine(CompletableFuture.supplyAsync(
      () -> " World"), (s1, s2) -> s1 + s2));
 
assertEquals("Hello World", completableFuture.get());

// Two Futures, no passing any resulting value:
CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
  .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
    (s1, s2) -> System.out.println(s1 + s2));
```
thenCompose returns an object of the same type, just as a flatMap does - futures aren't nested in futures. Uses the previous stage as an argument. 

thenApply works with the result of a previous call and returns combined of all calls. Will return nested futures, like map.
```Java
CompletableFuture<Integer> finalResult = compute().thenApply(s-> s + 1);
```

Executing in parallel:
```Java
CompletableFuture<Void> combinedFuture 
  = CompletableFuture.allOf(future1, future2, future3); // returns a void, so need to manually get results from futures
combinedFuture.get(); // blocks until all have executed
 
assertTrue(future1.isDone());
assertTrue(future2.isDone());
assertTrue(future3.isDone());

// Join results of futures
String combined = Stream.of(future1, future2, future3)
  .map(CompletableFuture::join)
  .collect(Collectors.joining(" "));
 
assertEquals("Hello Beautiful World", combined);
```

Catching Errors using a special handle method, with two parameters: the result of a computation (if it finished successfully) and the exception thrown (if some computation step did not complete normally).

```Java
CompletableFuture<String> completableFuture  
  =  CompletableFuture.supplyAsync(() -> {
      if (name == null) {
          throw new RuntimeException("Computation error!");
      }
      return "Hello, " + name;
  })}).handle((s, t) -> s != null ? s : "Hello, Stranger!");

// Throw exception upon completion
completableFuture.completeExceptionally(
  new RuntimeException("Calculation failed!"));
 
// ...
 
completableFuture.get(); // ExecutionException
```

https://www.baeldung.com/java-completablefuture

### Scheduling
Tasks can be scheduled to run periodically or once after a certain amount of time has passed.
```Java
ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

TimeUnit.MILLISECONDS.sleep(1337);

long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
System.out.printf("Remaining Delay: %sms", remainingDelay);
```
For periodicity, use `scheduleAtFixedRate()` or `scheduleWithFixedDelay()`. The former does not take into account the actual duration of the task (execution time), the latter applies the wait time between the end of a task and the start of the next.
```Java
ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

int initialDelay = 0;
int period = 1;
executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
```

ScheduledThreadPoolExecutor allows task execution after a specific delay (schedule() method), execution after an initial delay and then repeatedly with a certain period (scheduleAtFixedRate() method) where the period is the time between the starting times of tasks (so execution rate is fixed), or repeated execution with a specific delay measured between the end of one task and the start of the next (scheduleWithFixedDelay) so the execution rate may vary based on the time it takes to execute a given task.

```Java
CountDownLatch lock = new CountDownLatch(3);
 
ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
// corePoolSize = 5; maximumPoolSize is unbounded; keepAliveTime = 0
ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
    System.out.println("Hello World");
    lock.countDown();
}, 500, 100, TimeUnit.MILLISECONDS);
 // Execute after 500 ms then repeat every 100 ms

lock.await(1000, TimeUnit.MILLISECONDS);
// cancel after firest three times
future.cancel(true);
```

### ForkJoinPool
Spawns multiple tasks in recurisve algorithms without requiring the creation of a new thread for each task or subtask. Speds up processing of large tasks. Best to use one thread pool per application or system to minimize the number of total threads. Use the default common thread pool if no specific tuning is needed. Use a threshold to split ForkJoinTask into subtasks and avoid blocking.

ThreadPoolExecutor will run out of threads quickly, so fork tasks to spawn subtasks and join together their results.

Forks a task into smaller independent subtasks until they're simple enough to be executed asynchronousnly, then joins them all back into a single result, or (if the subtasks return void), waits until every subtask is executed.

Each thread in the ForkJoinPool has its own double-ended queue (deque, pronounced deck) which stores tasks. Free threads operate under a work-stealing algorithm, where they try to steal work from tails vof deques of busy threads or the global entry queue if their own deque is empty (which they pull the head from). This minimizes the possibility that threads will compete for tasks and the number vof times a thread will have to go looking for work - it first goes to the biggest available chunks of work first.

```Java
ForkJoinPool commonPool = ForkJoinPool.commonPool();
public static ForkJoinPool forkJoinPool = new ForkJoinPool(2); // parallelism level of 2, uses 2 processor cores

// Traverse tree of nodes and calculate sum of leaf values in parallel:
static class TreeNode {
    int value;
 
    Set<TreeNode> children;
 
    TreeNode(int value, TreeNode... children) {
        this.value = value;
        this.children = Sets.newHashSet(children);
    }
}

// Each task will recieve its own node and add its value to the sum of its children.
public static class CountingTask extends RecursiveTask<Integer> {
    private final TreeNode node;
 
    public CountingTask(TreeNode node) {
        this.node = node;
    }
 
    @Override
    protected Integer compute() {
        return node.value + node.children.stream() // stream the children set
          .map(childNode -> new CountingTask(childNode).fork()) // map over stream, creating new tasks for each element and execute by forking
          .collect(Collectors.summingInt(ForkJoinTask::join)); // collect results by joining each forked task and sum results using summingInt collector
    }
}

TreeNode tree = new TreeNode(5,
  new TreeNode(3), new TreeNode(2,
    new TreeNode(2), new TreeNode(8)));
 
ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
int sum = forkJoinPool.invoke(new CountingTask(tree));

```
ForkJoinTasks are executed inside ForkJoinPools. RecursiveActions are used for void tasks and RecursiveTask<V> return values and compute() their logic.

```Java
public class CustomRecursiveAction extends RecursiveAction {
 
    private String workload = "";
    private static final int THRESHOLD = 4;
 
    private static Logger logger = 
      Logger.getAnonymousLogger();
 
    public CustomRecursiveAction(String workload) {
        this.workload = workload;
    }
 
    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) { // splits up task 
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
           processing(workload);
        }
    }
 
    private List<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<>();
 
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
 
        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));
 
        return subtasks; // list of CustomRecursiveTasks to pass to ForkJoinPool
    }
 
    private void processing(String work) {
        String result = work.toUpperCase();
        logger.info("This result - (" + result + ") - was processed by "
          + Thread.currentThread().getName());
    }
}
```

For custom recursive tasks that return results:
```Java
public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr;
 
    private static final int THRESHOLD = 20;
 
    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }
 
    @Override 
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
              .stream() // stream out of a list of Futures
              .mapToInt(ForkJoinTask::join)
              .sum();
        } else {
            return processing(arr);
        }
    }
    
    // divide tasks until each piece is smaller than the threshold
    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
          Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }
 
    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
          .filter(a -> a > 10 && a < 27)
          .map(a -> a * 10)
          .sum();
    }
}
```

Submitting tasks to the thread pool:
```Java
forkJoinPool.execute(customRecursiveTask);
int result = customRecursiveTask.join();

	
int result = forkJoinPool.invoke(customRecursiveTask); // forks the task, waits for the result without the need for manual joining
```

invokeAll() submits a sequence of ForkJoinTasks to the ForkJoinPool that takes tasks or a collection of tasks as parameters and returns a collection of Future objects in the order they were produced. 

Can be done separately:
```Java
customRecursiveTaskFirst.fork(); // submit task to pool, but doesn't trigger execution
result = customRecursiveTaskLast.join(); // triggers execution, returns either null or the result of execution.
```

# Classes
# Optionals
Containers for values which may be null or non-null. Used to prevent NullPointerExceptions. Meant to be return types, not parameters.

```Java
Optional<String> empty = Optional.empty();
assertFalse(empty.isPresent());

Optional<String> opt = Optional.ofNullable(null); // No exception thrown, just an empty Optional object
Optional<String> optional = Optional.of("bam"); // NullPointerException if null

optional.isPresent();           // true if not null
optional.isEmpty();             // false if not null
optional.get();                 // "bam"
optional.orElse("fallback");    // "bam"

optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

String name = Optional.ofNullable(nullName).orElse("john"); // if present, return wrapped value (nullName) else argument ("john"). Creates the default object regardless, so if pass a function, the function will be called.
String name = Optional.ofNullable(nullName).orElseGet(() -> "john"); // takes a supplier functional interface and returns the result of the invocation. Will not call the function if not needed.
String name = Optional.ofNullable(nullName).orElseThrow(
      IllegalArgumentException::new);

Optional<String> opt = Optional.ofNullable(null);
String name = opt.get(); // NoSuchElementException
```

Reject wrapped values based on a pre-defined rule.

Filters take a predicate and return an optional argument. If the wrapped value passes the predicate, returns the optional as-is, else returns an empty optional.
```Java
Integer year = 2016;
Optional<Integer> yearOptional = Optional.of(year);
boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
assertTrue(is2016);


    boolean isInRange = false;
    if (modem != null && modem.getPrice() != null
      && (modem.getPrice() >= 10
        && modem.getPrice() <= 15)) {
 
        isInRange = true;
    }
    return isInRange;

// Same as

    return Optional.ofNullable(modem2)
       .map(Modem::getPrice) // original value is not modified
       .filter(p -> p >= 10)
       .filter(p -> p <= 15)
       .isPresent();
```
Map transforms an Optional value through some action.

FlatMap unwraps values before transforming them.
```Java
    Optional<Person> personOptional = Optional.of(person);
 
    Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName); // returns an Optional<String>
    Optional<String> nameOptional  = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
    String name1 = nameOptional.orElse("");
    assertEquals("john", name1);
 
 // Same as

    String name = personOptional
      .flatMap(Person::getName) // unwraps Optional<String>
      .orElse("");
    assertEquals("john", name);
```

Get the first non-empty Optional using Streams:
```Java
    Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
      .filter(Optional::isPresent)
      .map(Optional::get)
      .findFirst();
// executes all get methods regardless of where the first non-empty one appears
// To lazily evaluate instead:
    Optional<String> found =
      Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
        .map(Supplier::get)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findFirst();

// If want lazy evaluation for methods that take arguments:
    Optional<String> found = Stream.<Supplier<Optional<String>>>of(
      () -> createOptional("empty"),
      () -> createOptional("hello")
    )
      .map(Supplier::get)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .findFirst()
      .orElseGet(() -> "default");
```
# Spliterator
 Traverses and partitions sequences. Base utility for Streams.

 tryAdvance takes a Consumer that consumes one by one and returns false if no elements to be traversed. If remaining element  exists, perform given action and return true, else false. Combines hasNext() and next() of iterators.
 ```Java
 int current = 0;
    while (spliterator.tryAdvance(a -> a.setName(article.getName()
      .concat("- published by Baeldung")))) {
        current++;
    }
```
forEachRemaining(Consumer<T> action) for each remaining element in the current thread, until all processed or exception is thrown.

trySplit processes in parallel, returns a new spliterator to iterate over the other portion of the sequence.

estimateSize(): number of elements left to iterate or Long.MAX_VALUE if infinite, unknown, or too expensie to compute

getExactSizeIfKnown(): estimateSize() if spliterator is SIZED, else -1.

comparator if SORTED. null if natural order, IllegalStateException if not SORTED.

characteristics()
* SIZED – if it's capable of returning an exact number of elements with the estimateSize() method
* SORTED – if it's iterating through a sorted source
* SUBSIZED – if we split the instance using a trySplit() method and obtain Spliterators that are SIZED as well
* CONCURRENT – if source can be safely modified concurrently
* DISTINCT – if for each pair of encountered elements x, y, !x.equals(y)
* IMMUTABLE – if elements held by source can't be structurally modified
* NONNULL – if source holds nulls or not
* ORDERED – if iterating over an ordered sequence

Custom spliterator

https://www.baeldung.com/java-spliterator 

# Internationalization
i18n - Built-in support for different languages, number formats, and date and time adjustments (localization or L10n).
## Legacy Date and Time Handling
Dates store in UTC, locale-independent. Date and Calendar were not thread-safe.
## Locales
Identifiers of language + region combinations.

Locales are usually represented by language, country, and variant abbreviation separated by an underscore:

    de (German)
    it_CH (Italian, Switzerland)
    en_US_UNIX (United State, UNIX platform)

Script and Extensions
    Language can be an ISO 639 alpha-2 or alpha-3 code or registered language subtag.
    Region (Country) is ISO 3166 alpha-2 country code or UN numeric-3 area code.
    Variant is a case-sensitive value or set of values specifying a variation of a Locale.
    Script must be a valid ISO 15924 alpha-4 code.
    Extensions is a map which consists of single character keys and String values.

Locale.Builder:
```Java
Locale locale = new Locale.Builder()
  .setLanguage("fr")
  .setRegion("CA")
  .setVariant("POSIX") // no restrictions on values
  .setScript("Latn")
  .build();

// New Locale

    new Locale(String language);
    new Locale(String language, String country);
    new Locale(String language, String country, String variant);

// By Language
Locale uk = Locale.forLanguageTag("en-UK");

// Available
Locale[] numberFormatLocales = NumberFormat.getAvailableLocales();
Locale[] dateFormatLocales = DateFormat.getAvailableLocales();
Locale[] locales = Locale.getAvailableLocales();

// Defaults
Locale defaultLocale = Locale.getDefault();
Locale.setDefault(Locale.CANADA_FRENCH);
```

Locale class alaos has several static constants, like `Locale.JAPAN` and `Locale.JAPANESE`. Must be installed within the Java Runtime.

# Currencies
Value has an alphabetic code, a numeric code, and a minor unit.

https://docs.oracle.com/javase/8/docs/api/java/util/Currency.html
https://www.baeldung.com/java-money-and-currency

## ResourceBundles
Represent containers of resources for particular locales for loading loacle-specific data.

 localized messages/descriptions which can be externalized to the separate files

https://www.baeldung.com/java-resourcebundle
## Time Zones
Calendar objects (such as GregorianCalendar) arev ms from midnight GMT, January 1, 1970 and can do arithmetic on various fields like year, month, and week.

TimeZone encapsulates a time offset from GMT and possible daylight savings. TimeZone.getTimeZone() gvets an instance for a time zone ID. TimeZone.getDefault() returns the platform time zone.

https://www.baeldung.com/java-8-date-time-intro

LocalDate is immutable but does not store a time-zone or offset.

ChronoUnit is an enum with units of varying lengths and and Forever.

Can calculate the number of complete time units between two YearMonth objects with until() and a TemporalUnit

## Formatting
DateFormat, NumberFormat, and MessageFormat

DateFormat parses and formats locale-independent. SimpleDateFormat is locale-sensitive and can use custom patterns. DateFormatSymbols encapsulates date-time formatting data like names of months and time of day.
* `DateFormat.getDateTimeInstance(DateFormat.FUll, DateFormat.FULL, Locale.ITALY)`
* `getDateInstance`
* `getTimeInstance`


DateTimeFormatter:
```Java
Locale.setDefault(Locale.US);
LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 1, 10, 15, 50, 500);
String pattern = "dd-MMMM-yyyy HH:mm:ss.SSS";
 
DateTimeFormatter defaultTimeFormatter = DateTimeFormatter.ofPattern(pattern);
DateTimeFormatter deTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.GERMANY);
 
assertEquals(
  "01-January-2018 10:15:50.000", 
  defaultTimeFormatter.format(localDateTime));
assertEquals(
  "01-Januar-2018 10:15:50.000", 
  deTimeFormatter.format(localDateTime));


ZoneId losAngelesTimeZone = TimeZone.getTimeZone("America/Los_Angeles").toZoneId();
 
DateTimeFormatter localizedTimeFormatter = DateTimeFormatter
  .ofLocalizedDateTime(FormatStyle.FULL);
String formattedLocalizedTime = localizedTimeFormatter.format(
  ZonedDateTime.of(localDateTime, losAngelesTimeZone));
 
assertEquals("Monday, January 1, 2018 10:15:50 AM PST", formattedLocalizedTime);
```
https://www.baeldung.com/java-8-localization

NumberFormat is locale-specific - decimal points, thousands-sepraators, currency, percentage. Numbers are stored locale-independent. DecimalFormat formats decimals to a certain precision or currency. DecimalFormatSymbols stores symbols like the decimal separator, grouping separator. ChoiceFormat allows you to attach a format to a range of numbers.

    NumberFormat.getInstance(Locale locale)
    NumberFormat.getCurrencyInstance(Locale locale)

```Java
Locale usLocale = Locale.US;
double number = 102300.456d;
NumberFormat usNumberFormat = NumberFormat.getInstance(usLocale);
 
assertEquals(usNumberFormat.format(number), "102,300.456");

BigDecimal number = new BigDecimal(102_300.456d);
 
NumberFormat usNumberFormat = NumberFormat.getCurrencyInstance(usLocale); 
assertEquals(usNumberFormat.format(number), "$102,300.46");
```

MessageFormat produces conceatenated messages in a language-neutral way according to a pattern. ParsePosition keeps track of position during parsing. FieldPosition identifies fields in a formatted output. 

The Collator class performs locale-speciic string comparison to build searching and alphabetical sorting routines for natural language text. 

# Streams from Collections, references, and primitives
Streams are monads, which represent computations defined as sequences of steps. They are sequences of elements on which one or more operations can be performed. 

Streams are created on sources, like Collections. `Collection.stream()` or `Collection.parallelStream()`. Maps don't support streams but do have their own set of interesting methods.

```Java
Stream.of("a1", "a2", "a3")
    .findFirst()
    .ifPresent(System.out::println);  // a1

// Same as

Arrays.asList("a1", "a2", "a3")
    .stream()
    .findFirst()
    .ifPresent(System.out::println);  // a1

// Maps
map.forEach((id, val) -> System.out.println(val));
map.putIfAbsent(i, "val" + i);
map.computeIfPresent(3, (num, val) -> val + num);
map.computeIfAbsent(3, num -> "bam");
map.remove(3, "val33");
map.getOrDefault(42, "not found");  // not found
map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
map.get(9);             // val9concat
```

Streams also work on Primitives, which also support terminal aggregate functions like `sum()` and `average()`.

```Java
// Primitives
IntStream.range(1, 4)
    .forEach(System.out::println); // use IntFunction instead of Function and IntPredicate instead of Predicate
// also LongStream, DoubleStream
Arrays.stream(new int[] {1, 2, 3})
    .map(n -> 2 * n + 1)
    .average()
    .ifPresent(System.out::println);  // 5.0
// Can also sum()

Stream.of("a1", "a2", "a3") // from an array
    .map(s -> s.substring(1))
    .mapToInt(Integer::parseInt) // convert to primtive stream
    .max()
    .ifPresent(System.out::println);  // 3
// also mapToLong() and mapToDouble()

// Convert from primtive stream to object stream:
IntStream.range(1, 4) // startInclusive, endExclusive, incrementing by 1
    .mapToObj(i -> "a" + i)
    .forEach(System.out::println);

LongStream longStream = LongStream.rangeClosed(1, 3); // startInclusive, endInclusive

Random random = new Random();
DoubleStream doubleStream = random.doubles(3);

// Char string represented by an IntStream
IntStream streamOfChars = "abc".chars();
Stream<String> streamOfString =
  Pattern.compile(", ").splitAsStream("a, b, c"); // substrings from Regex

// Build a stream by adding items
Stream<String> streamBuilder =
  Stream.<String>builder().add("a").add("b").add("c").build();

// Build a stream from iteration. First element, function applied to first and then the subsequent ones:
Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

// Generate a stream from a Supplier<T>. need to specify desired size else will work until reaches a memory limit (otherwise infinite in size):
Stream<String> streamGenerated =
  Stream.generate(() -> "element").limit(10);
```

Streams can be sequential or parallel. With sequential streams, operations are performed on a single thread while parallel streams are performed concurrently on multiple threads.
```Java
long count = values.stream().sorted().count();
long count = values.parallelStream().sorted().count(); // Much faster

// Parallel streams for things other than Collections or arrays:
IntStream intStreamParallel = IntStream.range(1, 150).parallel();
boolean isParallel = intStreamParallel.isParallel();

// Convert back to sequential:
IntStream intStreamSequential = intStreamParallel.sequential();
boolean isParallel = intStreamSequential.isParallel();
```

To avoid null streams with no elements, can create an empty stream: 
```Java
public Stream<String> streamOf(List<String> list) {
    return list == null || list.isEmpty() ? Stream.empty() : list.stream();
}
```

## Intermediate and Terminal Stream Operations
Terminal operations return a result of a certain type. Intermediate operations return the stream itself so you can chain multiple method calls.

Operations on streams don't change the source. Executing a terminal operation makes a stream inaccessible (IllegalStateException runtime exception) - they can't be reused.

```Java
stringCollection
    .stream()
    .filter((s) -> s.startsWith("a")) // intermediate
    .forEach(System.out::println); // terminal
```

Intermediate operations are lazy, so they will only be executed when a terminal operation is present that deems them necessary. 

The pipeline executes vertically:
```Java
Stream.of("d2", "a2", "b1", "b3", "c")
    .map(s -> {
        System.out.println("map: " + s);
        return s.toUpperCase();
    })
    .anyMatch(s -> {
        System.out.println("anyMatch: " + s);
        return s.startsWith("A");
    });

// map:      d2
// anyMatch: D2
// map:      a2
// anyMatch: A2
```
Only continues on with an element through the pipeline if it passes a step. Intermediate operations which reduce the size of the stream  (skip, filter, distinct) should be placed before operations that are applied to each element.

Once a terminal operation is called, the stream is closed.
```Java
Stream<String> stream =
    Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> s.startsWith("a"));

stream.anyMatch(s -> true);    // ok
stream.noneMatch(s -> true);   // exception

// This works:
Supplier<Stream<String>> streamSupplier =
    () -> Stream.of("d2", "a2", "b1", "b3", "c")
            .filter(s -> s.startsWith("a"));

streamSupplier.get().anyMatch(s -> true);   // ok
streamSupplier.get().noneMatch(s -> true);  // ok
```

### Filter
Accepts a predicate to filter all elements of the stream. Intermediate operation, so can chain.

```Java
Stream<String> stream = list.stream().filter(element -> element.contains("d"));
```

### Distinct
Intermediate operaiton that creates a new Stream<T> of unique elements.
### Skip
Itermediate operation that skips over a few elements.
### Sorted
Intermediate operation sorted in natural order unless passed a Comparator. Only creates a sorted view of the stream - it doesn't manipulate the order of the backed collection, which remains untouched and unchanged.
```Java
stringCollection
    .stream()
    .sorted()
    .filter((s) -> s.startsWith("a"))
    .forEach(System.out::println);
```
### Map
Intermediate operation that converts each element into another via the given function. Can transform objects to other types.

```Java
stringCollection
    .stream()
    .map(String::toUpperCase)
    .sorted((a, b) -> b.compareTo(a))
    .forEach(System.out::println);
```

### flatMap
Intermediate operaiton that converts one object into multiple others (in the form of a stream that are all placed into the return stream) or none at all.

```Java
class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
}

List<Foo> foos = new ArrayList<>();

// create foos by transforming an int stream
IntStream
    .range(1, 4)
    .forEach(i -> foos.add(new Foo("Foo" + i)));

// create bars
foos.forEach(f ->
    IntStream
        .range(1, 4)
        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

// Resolve bar objects of each foo
foos.stream()
    .flatMap(f -> f.bars.stream())
    .forEach(b -> System.out.println(b.name));

// Bar1 <- Foo1
// Bar2 <- Foo1
// Bar3 <- Foo1
// Bar1 <- Foo2
// Bar2 <- Foo2
// Bar3 <- Foo2
// Bar1 <- Foo3
// Bar2 <- Foo3
// Bar3 <- Foo3

// Same as

IntStream.range(1, 4)
    .mapToObj(i -> new Foo("Foo" + i))
    .peek(f -> IntStream.range(1, 4)
        .mapToObj(i -> new Bar("Bar" + i + " <- " f.name))
        .forEach(f.bars::add))
    .flatMap(f -> f.bars.stream())
    .forEach(b -> System.out.println(b.name));
```

To avoid null checks, can take Optional classes that return Optional objects of another type.
```Java
Outer outer = new Outer();
if (outer != null && outer.nested != null && outer.nested.inner != null) {
    System.out.println(outer.nested.inner.foo);
}

// Same as

Optional.of(new Outer())
    .flatMap(o -> Optional.ofNullable(o.nested))
    .flatMap(n -> Optional.ofNullable(n.inner))
    .flatMap(i -> Optional.ofNullable(i.foo))
    .ifPresent(System.out::println);
// Each flatMap returns an Optional wrapping or null if the object is absent
```

### Count
Terminal operation that returns the stream's size.
### forEach
Terminal operation that returns void, so cannot be used as a non-ending link in a chain.
### Match
Terminal operation that checks whether a predicate matches the stream. Returns a boolean result.
```Java
boolean anyStartsWithA =
    stringCollection
        .stream()
        .anyMatch((s) -> s.startsWith("a"));

System.out.println(anyStartsWithA);      // true

boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
```
### Count
Terminal operation returning the number of elements in the stream as a long.
```Java
long startsWithB =
    stringCollection
        .stream()
        .filter((s) -> s.startsWith("b"))
        .count();

System.out.println(startsWithB);    // 3
```
### Collect
Terminal operation that transforms the elements of the stream into a different kind of result, like a List, Set, or Map. Accepts a Collector which consists of four operations: supplier, accumulator, combiner, and finisher. 

To Set or List:
```Java
List<Person> filtered =
    persons
        .stream()
        .filter(p -> p.name.startsWith("P"))
        .collect(Collectors.toList());
        // also Collectors.toList

System.out.println(filtered);    // [Peter, Pamela]
```
To Map, where mapped keys must be unique (else IllegalStateException), so merge can be useful to ensure uniqueness:
```Java
Map<Integer, String> map = persons
    .stream()
    .collect(Collectors.toMap(
        p -> p.age,
        p -> p.name,
        (name1, name2) -> name1 + ";" + name2)); 

System.out.println(map);
// {18=Max, 23=Peter;Pamela, 12=David}
```

Grouping:
Separates results in [ , ] array.
```Java
Map<Integer, List<Person>> personsByAge = persons
    .stream()
    .collect(Collectors.groupingBy(p -> p.age));

personsByAge
    .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

// age 18: [Max]
// age 23: [Peter, Pamela]
// age 12: [David]

Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
  .collect(Collectors.groupingBy(Product::getPrice));

Map<Boolean, List<Product>> mapPartioned = productList.stream()
  .collect(Collectors.partitioningBy(element -> element.getPrice() > 15))
```

Aggregations of elements:
```Java
Double averageAge = persons
    .stream()
    .collect(Collectors.averagingInt(p -> p.age));

System.out.println(averageAge);     // 19.0
```
Statistical summaries (min, max, average, sum, and count):
```Java
IntSummaryStatistics ageSummary =
    persons
        .stream()
        .collect(Collectors.summarizingInt(p -> p.age));

System.out.println(ageSummary);
// IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
```
Join into a string with deliminator, prefix, and suffix:
```Java
String phrase = persons
    .stream()
    .filter(p -> p.age >= 18)
    .map(p -> p.name)
    .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

System.out.println(phrase);
// In Germany Max and Peter and Pamela are of legal age.
```
### Collector Interface
Terminal processes that perform multiple fold operations (repackaging elements to some data structures while applying additional lobic).
* `.collect(Collectors.toList())`, also toMap(), toSet(). 
* `.toCollection(LinkedList::new)` allows more specific implementations, like HashSets and LinkedLists by passing such an instance.
* `.toMap()` doesn't evaluate if values are duplicates - will just throw an IllegalStateException
```Java
// Strings as keys and lengths as values
Map<String, Integer> result = givenList.stream()
  .collect(toMap(Function.identity(), String::length));
  // keyMapper and valueMapper
// Function.identity() accepts and returns the same value
// If have a collision, picks any of the two colliding values:
Map<String, Integer> result = givenList.stream()
  .collect(toMap(Function.identity(), String::length, (item, identicalItem) -> item)); // handles collisions with duplicates keys
```

* `.collect(collectingAndThen(toList(), ImmutableList::copyOf))` performs another action straight after collecting ends, such as collecting into a list and converting it into an immutable one.
* `joining()` or `joining(" separator ")` or `joining(deliminator, prefix, suffix)` joins Stream<String> elements together.
* `counting()` returns a Long.
* Statistical summaries in Double, Int, and Long:
```Java
DoubleSummaryStatistics result = givenList.stream()
  .collect(summarizingDouble(String::length));

assertThat(result.getAverage()).isEqualTo(2);
assertThat(result.getCount()).isEqualTo(4);
assertThat(result.getMax()).isEqualTo(3);
assertThat(result.getMin()).isEqualTo(1);
assertThat(result.getSum()).isEqualTo(8);
```
* `averagingDouble/Long/Int()` returns a Double, Long, or Integer.
* `summingDouble/Long/Int()`
```Java
Double result = givenList.stream()
  .collect(summingDouble(String::length));
```
* `minBy(Comparator.naturalOrder())` or `maxBy(Comparator comp)` returns an object wrappe in Optional, as there may be empty values.
* `groupingBy(some property, store results in Collector object)`: Groups objects by some property and stores groups as values in a Map instance.
```Java
Map<Integer, Set<String>> result = givenList.stream()
  .collect(groupingBy(String::length, toSet()));
```
* `partitioningBy(Predicate)`: returns a Map with boolean values as keys and collections as values based on how they return from a Predicate.
```Java
Map<Boolean, List<String>> result = givenList.stream()
  .collect(partitioningBy(s -> s.length() > 2))
// {false=["a", "bb", "dd"], true=["ccc"]}
```
* `teeing()`: Duplicates input - combines the results of two different collectors operating on the same stream.
```Java
// Find min and max from a given stream:
List<Integer> numbers = Arrays.asList(42, 4, 2, 24);

numbers.stream().collect(teeing(
  minBy(Integer::compareTo), // The first collector
  maxBy(Integer::compareTo), // The second collector
  (min, max) -> // Receives the result from those collectors and combines them
));
// Same as
Optional<Integer> min = numbers.stream().collect(minBy(Integer::compareTo));
Optional<Integer> max = numbers.stream().collect(maxBy(Integer::compareTo));
// do something useful with min and max
```

#### Custom Collectors
Collector<Type of objects available for collection, type of mutable Accumulator object, type of final Result>:
```Java
private class ImmutableSetCollector<T>
  implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {...}

// Collector.of
Collector<Product, ?, LinkedList<Product>> toLinkedList =
  Collector.of(LinkedList::new, LinkedList::add, 
    (first, second) -> { 
       first.addAll(second); 
       return first; 
    });
 
LinkedList<Product> linkedListOfPersons =
  productList.stream().collect(toLinkedList);
```
Mutable accumulators are collections or some other class. ImmutableSet.Builder implements:
* `Supplier<ImmutableSet.Builder<T>> supplier()`: generates an empty accumulator instance.
* `BiConsumer<ImmutableSet.Builder<T>, T> accumulator()`: adds a new element.
* `BinaryOperator<ImmutableSet.Builder<T>> combiner()`: merges two accumulators together.
* `Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher()`: Converts an accumulator to the final result type.
* `Set<Characteristics> characteristics()`: Provides Stream with additional information to use for internal operations.

```Java
public class ImmutableSetCollector<T>
  implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {
 
@Override
public Supplier<ImmutableSet.Builder<T>> supplier() {
    return ImmutableSet::builder;
}
 
@Override
public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
    return ImmutableSet.Builder::add;
}
 
@Override
public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
    return (left, right) -> left.addAll(right.build());
}
 
@Override
public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
    return ImmutableSet.Builder::build;
}
 
@Override
public Set<Characteristics> characteristics() {
    return Sets.immutableEnumSet(Characteristics.UNORDERED);
}
 
public static <T> ImmutableSetCollector<T> toImmutableSet() {
    return new ImmutableSetCollector<>();
}
}

List<String> givenList = Arrays.asList("a", "bb", "ccc", "dddd");
 
ImmutableSet<String> result = givenList.stream()
  .collect(toImmutableSet());
```


Using Collector.of():
```Java
Collector<Person, StringJoiner, String> personNameCollector =
    Collector.of(
        () -> new StringJoiner(" | "),          // supplier
        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
        (j1, j2) -> j1.merge(j2),               // combiner
        StringJoiner::toString);                // finisher

String names = persons
    .stream()
    .collect(personNameCollector);

System.out.println(names);  // MAX | PETER | PAMELA | DAVID
```
Four operations: supplier (initial actions), accumulator, combiner (for parallel streams), and finisher (like a finally statement). 

### Min and Max
Terminal operations that take comparators. Special case of reduction.
```Java
Optional<Integer> min = list.stream().min(Integer::compareTo); // returns an optional because the stream could be empty.

// Dealing with Optionals:
Integer smallest = minimal.orElse(absent); // default value
Integer smallest = minimal.isPresent() ? minimal.get() : absent;
```

### Reduce
Terminal operation that reduces all elements to a single result using the given function. Returns an Optional holding the reduced value. Accepts a starting value and a BinaryOperator accumulator function, which accepts two operands of the same type.
```Java
Optional<String> reduced =
    stringCollection
        .stream()
        .sorted()
        .reduce((s1, s2) -> s1 + "#" + s2);

reduced.ifPresent(System.out::println);
// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

// Get eldest
persons
    .stream()
    .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
    .ifPresent(System.out::println);    // Pamela
// Get accumulated person
Person result =
    persons
        .stream()
        .reduce(new Person("", 0), (p1, p2) -> {
            p1.age += p2.age;
            p1.name += p2.name;
            return p1;
        });

System.out.format("name=%s; age=%s", result.name, result.age);
// name=MaxPeterPamelaDavid; age=76

Integer ageSum = persons
    .stream()
    .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
    // identity value (initial value), BiFunction accumulator, BinaryOperator combiner, which is used with parallel streams
System.out.println(ageSum);  // 76

List<Integer> integers = Arrays.asList(1, 1, 1);
Integer reduced = integers.stream().reduce(23, (a, b) -> a + b); // 23 + 1 + 1 + 1;
```

## Parallelism
Parallel streams use a common ForkJoinPool method to use a custom Thread Pool:
```Java
@Test
public void giveRangeOfLongs_whenSummedInParallel_shouldBeEqualToExpectedTotal() 
  throws InterruptedException, ExecutionException {
     
    long firstNum = 1;
    long lastNum = 1_000_000;
 
    List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
      .collect(Collectors.toList());
 
    ForkJoinPool customThreadPool = new ForkJoinPool(4); // generally the number of cores of your CPU
    long actualTotal = customThreadPool.submit(
      () -> aList.parallelStream().reduce(0L, Long::sum)).get();
  
    assertEquals((lastNum + firstNum) * lastNum / 2, actualTotal);
}
```

# Lambda 
Useful for functional programming. Pass around an expression as if it was an object and execute it on demand. Useful for simple event listeners and callbacks.

Functional interfaces provide target types for lambda expressions and method references.

Lambdas are particularly useful in parallel computing. They can't change the value of an object from the enclosing scope, but can do something like `array[0]++` as the reference `array` is not changing.

```Java
StateOwner stateOwner = new StateOwner();

stateOwner.addStateListener(new StateChangeListener() {

    public void onStateChange(State oldState, State newState) {
        // do something with the old and new state.
    }
});

// Same as

stateOwner.addStateListener(
    (oldState, newState) -> System.out.println("State changed")
); // lambda!
```
When used as a parameter, must implement the same interface as that parameter. To match to single method interfaces, or functional interfaces, needs to have the same parameters and return type.

```Java
// Can add because one method of the interface is unimplemented:
public interface MyInterface {

    public String printIt(String text); // unimplemented

    default public void printUtf8To(String text, OutputStream outputStream){
        try {
            outputStream.write(text.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("Error writing String as UTF-8 to OutputStream", e);
        }
    }

    static void printItToSystemOut(String text){
        System.out.println(text);
    }
}
MyInterface myInterface = (String text) -> { // type of the parameter is unnecessary here as can be inferred from method declaration above
    System.out.print(text);
    return "Hi!";
}; // implementation, stateless
// use {curly brackets} for multiple lines

// Using anonymouse interface implementation:
MyInterface myInterface = new MyInterface() {
    private int eventCount = 0; // can have own state/ member variables
    public void printIt(String text) {
        System.out.print(text);
        return "Hi!";
    }
};
```

## Functional Interfaces

```Java
@FunctionalInterface // helpful annotation
public interface Foo {
    String method(String string); // abstract method
    default void defaultMethod() {} // implemented method
}

public String add(String string, Foo foo) {
    return foo.method(string);
}

Foo foo = parameter -> parameter + " from lambda";
String result = useFoo.add("Message ", foo);

// Really just
public String add(String string, Function<String, String> fn) {
    return fn.apply(string);
}

Function<String, String> fn = 
  parameter -> parameter + " from lambda";
String result = useFoo.add("Message ", fn);


Returning values can be done implicitly.
```Java
Function<String, String> toLowerCase = (String input) -> input.toLowerCase(); // returns input.toLowerCase()

// Method reference
MyPrinter myPrinter = s -> System.out.println(s);
// Same as
MyPrinter myPrinter = System.out::println; // where println is the method referenced (after ::)
```

To create a lamba expression that references another class's static method:
```Java
public interface Finder {
    public int find(String s1, String s2); // method to implement
}
public class MyClass{
    public static int doFind(String s1, String s2){ // method to reference
        return s1.lastIndexOf(s2);
    }
}

Finder finder = MyClass::doFind;
// since have same parameters and return types
```

Each lambda corresponds to a given type, specified by a functional interface which has one abstract method declaration. Interfaces decorated with @FunctionalInterface can only have one abstract method.


### Method References
The compiler will attempt to match the referenced method against the first parameter type and use the second parameter as a parameter to the referenced method:
```Java
Finder finder = (s1, s2) -> s1.indexOf(s2);
// Same as
Finder finder = String::indexOf;
```

You can also reference constructors using ::new:
```Java
public interface Factory {
    public String create(char[] val);
}

Factory factory = chars -> new String(chars);
// Same as
Factory factory = String::new;
```

## Comparator
Easy to reverse comparators with `comparator.reversed().compare(p1, p2);`.
```Java
// Comparing
// Anonymous inner class
Comparator<Developer> byName = new Comparator<Developer>() {
	@Override
	public int compare(Developer o1, Developer o2) {
		return o1.getName().compareTo(o2.getName());
	}
};
// Same as
Comparator<Developer> byName =
	(Developer o1, Developer o2) -> o1.getName().compareTo(o2.getName();

developerList.sort(byName.reversed()); // sort by reversing the comparator

developerList.sort(
    (o1, o2) -> o1.getName().compareTo(o2.getName(); // infers type
)

// to some static method in Developer that takes some Developer a, Developer B and returns an int
developerList.sort(Developer::compareByNameThenAge);

// String together multiple comparators
developerList.sort(
      Comparator.comparing(Developer::getName).thenComparing(Developer::getAge)
    );

// Sorting
// Anonymous inner class
Collections.sort(listDevs, new Comparator<Developer>() {
	@Override
	public int compare(Developer o1, Developer o2) {
		return o1.getAge() - o2.getAge();
	}
});
//Same as
listDevs.sort(new Comparator<Developer>() {
	@Override
	public int compare(Developer o1, Developer o2) {
		return o2.getAge() - o1.getAge();
	}
});
```

The inside of lambdas acan also see instance variables, which are allowed to change. Lambdas cannot have their own instance variables, so this always points to the enclosing object. They can also access static variables.
```Java
public interface MyComparator {
    private String name = "MyComparator";
    public void attach(MyEventProducer eventProducer) {
         eventProducer.listen(e -> {
            System.out.println(this.name);
        }); 
    }
    public boolean compare(int a1, int a2);
}
int outside = 0;
// can reference local variables that are effectively final - don't change value after being assigned.
MyComparator myComparator = (a1, a2) -> a1 > a2 && a1 > outside;

boolean result = myComparator.compare(2, 5);
```
## Predicates
Boolean-valued functions of one argument.

```Java
Predicate<String> predicate = (s) -> s.length() > 0;

predicate.test("foo");              // true
predicate.negate().test("foo");     // false

Predicate<Boolean> nonNull = Objects::nonNull;
Predicate<Boolean> isNull = Objects::isNull;

Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNotEmpty = isEmpty.negate();
```

## Functions
Functions accept one argument and produce a result.
```Java
Function<String, Integer> toInteger = Integer::valueOf;
Function<String, String> backToString = toInteger.andThen(String::valueOf);

backToString.apply("123");     // "123"
```

## Consumers
Takes in one argument and doesn't return any value. Operates via side-effects.

```Java
Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
greeter.accept(new Person("Luke", "Skywalker"));

Consumer<List<Integer> > modify = list -> { 
    for (int i = 0; i < list.size(); i++) {
        list.set(i, 2 * list.get(i));
    } 
}; 
modify.accept(list);

// Consumer to display a list of integers 
Consumer<List<Integer> > dispList = list -> 
list.stream().forEach(a -> System.out.print(a + " ")); 

// using addThen() 
modify.andThen(dispList).accept(list); 

first.andThen(second).andThen(third).accept(input);
     
```

andThen will execute another Consumer after the first one.

## Suppliers
Produce a result of a given generic type - supplier of results. Doesn't take any argument but produces one of type T.
```Java
Supplier<Person> personSupplier = Person::new;
personSupplier.get();   // new Person

        // This function returns a random value. 
        Supplier<Double> randomValue = () -> Math.random(); 
  
        // Print the random value using get() 
        System.out.println(randomValue.get()); 
```
## Binary Operators
Used as a target. Takes an object of type T and returns a value of the same type.
```Java
 BinaryOperator<Person> getMax = BinaryOperator.maxBy((Person p1, Person p2) -> p1.age-p2.age);
 // static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator)
 // also have minBy()

 Person maxPerson = getMax.apply(person1, person2);

 BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;

 System.out.println(adder.apply(3, 4));
```

# @SafeVarargs Method Invocation
Assertion that body of the annotated method doesn't perform potentially unsafe operations on varargs parameter (variable length parameter). Suppresses unchecked warnings about a non-reifiable variable arity (vararg) type and suppresses unchecked warnings about parameterized array creation at call sites.

You're not supposed to return a parameterized varargs array, which will be type Object[] regardless of `method(T... elements)` T.

# Memory Leaks
When objects are no longer bein used by the application but the Garbage Collector can't remove them from working memory, as they are still being referenced.

OutOfMemoryError.

Referencing a big object with a static field - will never be collected even after calculations are done.

str.intern() returns in canonical form, placing it into the PermGen space of the JVM memory pool where it can't be collected. PermGen is replaced by Metaspace in Java 8 which won't lead to OutOfMemoryErrors with intern on strings.

Leaving instantiated streams unconsumed or unclosed. low-level resource leak as well of file descriptors, open connecions, etc. Close streams, use terminal operations, or try-with-resources to automatically close without an explicit finally block.

Connections to databases and servers must be closed.

Adding objects with no hashCode() or equals() implementations to a HashSet won't allow it to ignore duplicates or remove them once added.

## Heap Information
```Java
// Get current size of heap in bytes
long heapSize = Runtime.getRuntime().totalMemory(); 

// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
long heapMaxSize = Runtime.getRuntime().maxMemory();

 // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
long heapFreeSize = Runtime.getRuntime().freeMemory();
```

Garbage collection:
https://codeahoy.com/2017/08/06/basics-of-java-garbage-collection/
https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/sizing.html
https://plumbr.io/blog/garbage-collection/understanding-garbage-collection-logs

# JAR Files
Java Archive file. Aggregates many Java class files and associated metadata and resources (text, images, etc.) into one file for distribution. JAR files are archive files that include a Java-specific manifest file.

Packaged with ZIP.

 lossless data compression, archiving, decompression, and archive unpacking
 https://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html

 Generally name the file with the name of the public class with a main method that will be run (where execution will start). Will compile as man .class files as there are classes in the file and can individually test all those with their own main() stubs.

 Entry points to JAR files can be set in the manifest or overridden in the command line. The entry point is the class whose main method will be executed when the JAR file is run.
 https://docs.oracle.com/javase/tutorial/deployment/jar/appman.htmlv

# Type Inference for Generic Instance Creation
Within the <diamond> we put types, but these can be inferred and the diamond of type parameters left empty.
```Java
List<Integer> list = new List<>();
```

Can infer from a generic type constructor:
```Java
class GenericClass<X> {  
    <T> GenericClass(T t) {  
        System.out.println(t);  
    }  
} 

GenericClass<String>gc2 = new GenericClass<>("Hello");  
```
Using a <?> is a wildcard. Can also have <? extends ParentClassName> to limit the number of acceptable types that can be passed. 

# Operator Precedence
c = (b2 = b1 == false) where b1 is compared to false and the result of that comparison is passed to b2, the assignment of which returns the value of b2 to set c.

a++==a++ for a = 0 intially is the same as 0==1, with a = 2 by the end. The expression on each side is first evaluated, then they are compared.