https://javascript.info/tutorial/map

# Variables
## Syntax
```JavaScript
let mutableValriable; // declaration
mutableVariable = 'value'; // assignment
const IMMUTABLE_VARIABLE = 'value'; // combined declaration and assignment
let multiple = 1, variables = 2, can = 3, be = '4', assigned = 5;
```

In the olden days, only ``var`` was be used to declare variables.

## Scope and Context
Variables declared inside a function with the same name as a variable declared outside shadows the outer one. The outer one is ignored.

## Values
JavaScript is dynamically typed - variables are not bound to just holding a single type through their lifecycle.

## Primitives
* number - integer ranging from -2^{53} to 2^{53} and floating point, ``Infinity``, ``-Infinity``, ``NaN`` (not a number)
* * A script will never stop with a fatal error, but will instead return ``NaN`` as a result if you, for example, try to divide a string by 2.
* BigInt - arbirtrarily long numbers, created by appending ``n`` to the end of a larger integer literal: ``1234n``
* "string", 'string', \`string with ${embeddedVariables + or + operations + to + evaluate}!`
* boolean - `true` or `false`; 
* null - nothing, empty, value unknown, does not exist
* undefined - value is not assigned (yet)
* * `canCheckForUndefined == null;`

### Primitive Wrappers
Objects that wrap around primitives and offer methods and properties and is then destroyed. Undefined and null do not have corresponding wrapper objects.
```JavaScript
let str = "Hello"; // primitive created

alert( str.toUpperCase() ); // HELLO
// create a special object String that knows the value of str and has useful methods, like toUpperCase()
// run the method and return a new string
// destroy the special object, leaving str alone
```
#### Number
Can write with e, so 1 billion becomes 1e9 (9 trailing zeroes). 1 millionth of a second becomes 1e-6 (6 zeroes to the left from 1 including the leading zero before the decimal point).

Hexadecimal numbers are specified with a leading 0x.

Binary numbers have the prefix 0b.

Octal numbers have the prefix 0o.

Methods
* `num.toString(base)` where base is 2 for binary, 10 for decimal (default), 16 for hexadecimal, etc. with a maximum of 36 which is useful for turning long numbers into much shorter ones
* `Math.floor(num)` - rounds down. 3.1 => 3, -1.1 => -2
* `Math.ceil(num)` - rounds up. 3.1 => 4, -1.1. => -1
* `Math.round(num)` - rounds to nearest integer. 3.1 => 3, -3.6 => -4
* `Math.trunc(num)` - removes anything after the decimal point without rounding. 3.1 => 3
* * Not supported by Internet Explorer (IE)
* * To round to a certain decimal place, can multiply: `alert( Math.floor(1.23456 * 100) / 100 ); // 1.23456 -> 123.456 -> 123 -> 1.23`
* `num.toFixed(n)` - rounds to n digits after the point and returns a string representation of the result. Appends zeroes if necessary. `(12.36).toFixed(1) ); // "12.4"`
* `isNaN(num)` - converts a value to a number and tests if it worked
* * NaN !== NaN, so can't compare it to anything.
* `isFinite(num)` - converts to a number and returns tru if it's not NaN, Infinity, or -Infinity. 
* * Empty and space-only strings are 0.
* `parseInt(string, optionalRadix)` and `parseFloat(string)` - read a number from a string until they can't, then returns the number it gathered. Radix is the base of the numeral system.
* * `+"100px"` fails where `parseInt("100.3px") === 100`. Only returns NaN if no digits can be read, like with `'a123'`
* `Math.random()` - returns a random number [0, 1) (not including 1).
* `Math.max(a, b, c...)` and `Math.min(a, b, c...)`
* `Math.pow(n, power)` - returns n raised to the given power

More Math functions: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math 

> To call a method directly on a number, need to place two dots after it - one to imply the decimal part, and one for the start of hte method
* `123..toString(36)` or `(123).toString(36)`

As numbers are represented internally as 64-bit, with 52 storing the digits, 11 bits storing the position of the decimal point, and 1 sign bit, something too big or vsmall will overflow.

There is also a lack of precision.
```JavaScript
alert( 1e500 ); // Infinity
// Hello! I'm a self-increasing number!
alert( 9999999999999999 ); // shows 10000000000000000 because the format isn't bit enough

alert( 0.1 + 0.2 ); // 0.30000000000000004
// .1 and .2 in binary are unending fractions

alert( 0.1 + 0.2 == 0.3 ); // false

alert(+(.1 + .2).toFixed(2) == 0.30) // true

```

#### BigInt
#### String
Stored internally as UTL-16 format.

Backticks \`allow for ${variables} and ${results - from + operations}

    as well as

    multiple

    lines`

How this works: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals#Tagged_templates

Multi-line strings can also be represented with the newline character "\n": "Multiple \nlines"

Quotes must also be escaped if used in the middle of strings: `'I\'m escaped!'` which can also be accomplished with `"I'm not escaped!"`

Strings are immutable - characters cannot be changed

Methods and Properties
* `str.length`
* `str[index]` or `str.charAt(index)` - if no character is found, the first returns undefined, charAt returns an empty string
* * Last character: `str[str.length - 1)]`
* `str.toUpperCase()` and `str.toLowerCase()` - converts to all CAPS or no caps
* `str.indexOf(substr[, optionalPosition = 0])` - searches for the case-sensitive substring substr in str starting from the given optionalPosition, default 0. Returns the start index of the substring or -1 if it is not found.
* `str.lastIndexOf(substr[, fromIndex = +Infinity])` - searches from the from index, or the last index, backwards to the beginning looking for the substr. Returns the last occurance or -1 if none are found.
* `str.includes(substr[, optionalPosition = 0])` - tests for a substring starting from the given position but returns a boolean rather than a position
* `str.startsWith(substr)`
* `str.endsWith(substr)`
* `str.slice(start[, end = length])` - retursn the part of the string from [start, end) (not including the end)
* * Can do negative values that start counting from the right: `"stringify".slice(-4, -1) === 'gif'` starting at the 4th position from the right and ending at the first from the right
* * If start > end for slice, returns an empty string
* `str.substring(start[, end])` - returns string between start and end, allowing start > end: `"stringify".substring(6, 2) === "ring"`.
* * Negative arguments ain substring are treated as 0
* `str.substr(start[, length])` - returns the part from the start index with the given length
* * Allows for a negative start: `"stringify".substr(-4, 2) === 'gi'` 
* `str.trim()` - removes spaces from the beginning and end of the string
* `str.repeat(n)` - repeats the string n times
* `str.codePointAt(pos)` - gets the numeric representation of a character
* `String.fromCodePoint(code)` - creates a character by its numeric code
* `str.localeCompare(str2)` - returns an integer depending on how str compares to str2 according the language rules
* * negative number if str < str2
* * 0 if str === str2
* * positive number if str > str2
* `str.normalize()` - used for comparing characters composed of a base letter and some sort of decorator(s) (diacrticial mark)

> Codepoints deal with surrogate pair encodings, used for rare symbols and emojis. `String.fromCharCode` and `str.charCodeAt` do not work with these.

Iteration over Characters
```JavaScript
for (let char of "Hello") {
  alert(char); // H,e,l,l,o (char becomes "H", then "e", then "l" etc)
}
```

Find all occurances of a substring:
```JavaScript
let str = 'As sly as a fox, as strong as an ox';
let target = 'as'; // let's look for it

let pos = 0;
while (true) {
  let foundPos = str.indexOf(target, pos);
  if (foundPos == -1) break;

  alert( `Found at ${foundPos}` );
  pos = foundPos + 1; // continue the search from the next position
}

// Same as:
let pos = -1;
while ((pos = str.indexOf(target, pos + 1)) != -1) {
  alert( pos );
}
```

Searching for a substring
```JavaScript
let str = "Widget with id";

if (str.indexOf("Widget") != -1) {
    alert("We found it"); // works now!
}

// Old Style
// Using the Bitwise Not trick, where ~n = -(n+1), ~-1 = -(-1+1) = 0:
// only works if the string is not too long and can be truncated to 32 bits
if (~str.indexOf("Widget")) {
  alert( 'Found it!' ); // works
}

// Modern
alert( "Widget with id".includes("Widget") ); // true
```

Internationalization standard: http://www.ecma-international.org/ecma-402/1.0/ECMA-402.pdf 
#### Boolean

## Complex
* Objects can store collections of data
* Symbol creates unique identifiers for objects

Technically all of the below are objects

### Array

### Iterables
### Map and Set
#### WeakMap and WeakSet
### Date
### Error

### Symbol Type
Object property keys can be strings or symbols. Symbols are unique identifiers. They can have descriptions, which are useful for debugging.

Symbols are guaranteed to be unique, even if two have the same description.

Symbols don't autoconvert to strings - you have to explicitly call the `symbol.toString()` method.

```JavaScript
let id = Symbol();
let sym = Symbol("description str");
let sym2 = Symbol("description str");

alert(sym == sym2); // false, all symbols are unique

alert(sym.toString()); // Symbol("description str")
alert(sym.description); // "description str"
```

Symbols allow us to create “hidden” properties of an object, that no other part of code can accidentally access or overwrite.

With third-party code that you can't add fields to, you can still add symbols that cannot be accessed accidentally. They cannot be accessed in a `for...in` loop.

```JavaScript
let user = {
    name: "userName"
}; // from some third-party code

let id = Symbol("id");
user[id] = 1;

alert(user[id]); // 1

// Same as using a symbol in an object literal
user = {
    name: "userName,
    [id]: 1
}
// will not show up in for...in loop
Object.keys(user) ignores symbols.
Object.assign({}, user); // still copies symbol  properties

Object.getOwnPropertySymbols(user); // will return symbols
Reflect.ownKeys(user); // returns all keys, including symbolic ones
```
Someone else can even create multiple symbols with the same name and there will not be a conflict with yours.

Sometimes, however, you want to be able to access exactly the same property with just the same name. This is done through the global symbol registry.

```JavaScript
let id = Symbol.for("id"); // check the registry for a symbol described as "id". If it exists, return it, else create a new one and store vit.

let idAgain = Symbol.for("id"); // gets the same symbol as id because they have the same name.

alert(id === idAgain); // true

alert(Symbol.keyFor(idAgain)); // "id", returns the name. If idAgain was not global, would return undefined.
```

### Custom Objects
Objects are lists of properties, which are string key: any value pairs.

```JavaScript
let user = new Object(); // "object constructor" syntax
let userLiteral = {};  // "object literal" syntax
let computedProperty = prompt("User inputs key name", "default");
let obj = {

    key: "value",
    nextKey: "nextValue",

    "multi-word key": 3,

    [computedProperty]: "userInput",
    [computedProperty + '2']: "userInput plus 2",

    property, // same as property: property

    0: "0", // same as "0": "0"
    storingUndefined: undefined, // would generally use null

    sayHi: function() {
        alert("Hi!");
    },
    shorthandSayHi() {
        alert(`Hi, ${this.property}!`);
        // this is the current object, evaluated during run-time
    }, // method shorthand
};

obj.addedKey = "new key added or reassigned";
```

#### Constructors
Reusable object creation code with "new."

With "new", a new empty object is created and assigned to this. The function body executes, often adding new properties to this. The value of this (new object) is returned.

```JavaScript
function User(name) {
    // this = {};  (implicitly)

    // add properties
    this.name = name;
    this.sayHi = function() {
    alert( "My name is: " + this.name );
  };

    // return this;  (implicitly)
    // if there is an explicit return:
    /*
    If return is called with an object, then the object is returned instead of this.
    If return is called with a primitive or nothing, it’s ignored.
    */
}

let user = new User("Ferdinand");
alert(user.name); // "Ferdinand"

// Without future reusability:
let user = new function() {
  this.name = "John";

  // ...other code for user creation
  // maybe complex logic and statements
  // local variables, etc.
};

// if there are no arguments, don't need () after new User;
```

To check whether or not the function was called with new, use new.target:
```JavaScript
function User() {
  alert(new.target);
}

// without "new":
User(); // undefined

// with "new":
new User(); // function User { ... }

function User(name) {
  if (!new.target) { // if you run me without new
    return new User(name); // ...I will add new for you
  }

  this.name = name;
}

let john = User("John"); // redirects call to new User
alert(john.name); // John
```

#### Accessing values:
```JavaScript
obj["key"] = "new value";
obj.key = true;
obj.newKey = 0;

alert(obj.propThatDoesntExist === undefined); // true
alert(obj.storingUndefined === undefined); // true
alert("key" in object); // true

delete user.newKey;
```

#### Iterating over keys
```JavaScript
for (let key in object) { // where key is our dummy variable
    alert(object[key]); // returns value;
}
```

#### Storage Order

Integer properties are sorted (even if they were input as strings "1", "2", etc.), others appear in creation order. You can cheat and make integer properties strings by adding a "+" before them

#### Copying
primitaves are assigned and copied as whole values.

```JavaScript
let message = "Hello!";
let phrase = message; // stores two separate independent "Hello!"s.
```
Variables don't store the object itself, but a reference to it, i.e. its address in memory that points to the actual instance in memory. So when an object variable is copied, the reference is copied, rather than the object being duplicated as is done with primitives.

> Like copying a key to a cabinet rather than the entire cabinet.

This means that either variable can access the underlying object and modify its contents, and the other variable will be able to see those changes. There is only one object.

```JavaScript
const user = { name: 'John' }; // const objects can be changed internally. This just means that user cannot be re-assigned to any other thing. The reference remains the same.

let admin = user;

admin.name = 'Pete'; // changed by the "admin" reference

alert(user.name); // 'Pete', changes are seen from the "user" reference
```

With equality, two references are equal only if they are the same object. So "==" won't return true for two separate instances of the same object type, even if they have all of the same properties, because they're stored in separate places in memory. "==" and "===" will act exactly the same when comparing objects.

To duplicate an object (independent copy, clones), can use Object.assign(dest, [src1, src2, src3...]);

```JavaScript
let user = { name: "John" };

let permissions1 = { canView: true };
let permissions2 = { canEdit: true };

// copies all properties from permissions1 and permissions2 into user
Object.assign(user, permissions1, permissions2);

// now user = { name: "John", canView: true, canEdit: true }

// overwrite name, add isAdmin
Object.assign(user, { name: "Pete", isAdmin: true });

// now user = { name: "Pete", isAdmin: true }

let clone = Object.assign({}, user);
```

If one of the properties of an object is an object, then cloning will only clone a reference to the internal object - they will share that same internal object. To properly clone this, you need deep cloning to check if something is an object that needs to replicated as well.

#### Object to Primitive
The three variants of type conversion are called "hints."

Setting conversion: 
```JavaScript
// where hint is "string" | "number" | "default"
obj[Symbol.toPrimitive] = function(hint) {
  // must return a primitive value
   return hint == "string" ? `{name: "${this.name}"}` : this.data;
};

// Old style of conversion:
let obj = {
    name: 'Name',
    data: 101,

    toString() {
        // default is "[object Object]
        return `{name: "${this.name}"}`;
    }

// Not necessary as toString() will be called for hints "number" and "default" if valueOf() is not found. Then the string primitive will be converted by the operand into a number primitive.
    valueOf() {
        // default is the object itself, which will be ignored if looking for a return type of number
        return this.data;
    }
}
```

Accessing conversion:
```JavaScript
// First try:
obj[Symbol.toPrimitive](hint); // uses Symbolic key Symbol.toPrimitive

// if the above does not exist and the hint is "string":
// try:
obj.toString();
// or
obj.valueOf();

// Ohterwise, if the hint is "number" or "defualt":
obj.valueOf();
// or
obj.toString();
```

##### Object to String

```JavaScript
alert(obj); // expects a string as input

// using object as a property key
anotherObj[obj] = 123;
```
##### Object to Number

```JavaScript
// explicit conversion
let num = Number(obj);

// maths (except binary plus)
let n = +obj; // unary plus
let delta = date1 - date2;

// less/greater comparison
let greater = user1 > user2;
```

##### Default Conversion
There is no boolean hint, as all objects are true as booleans.

When the operator isn't sure what type to expect (like with a binary +, which can concatenate two strings or add two numbers or when an object is compared using == to a string), the default conversion is used.

The greater and less comparison operators, such as < >, can work with both strings and numbers too. Still, they use the "number" hint, not "default". Most objects implement the "default" conversion the same way as "number" anyway.

```JavaScript
// binary plus uses the "default" hint
let total = obj1 + obj2;

// obj == number uses the "default" hint
if (user == 1) { ... };
```

## Validation

typeof allows us to see which type is stored in a variable or literal.

```JavaScript
typeof undefined // "undefined"

typeof 0 // "number"

typeof 10n // "bigint"

typeof true // "boolean"

typeof "foo" // "string"

typeof Symbol("id") // "symbol"

typeof Math // built-in "object"  (1)

typeof null // "object"  (2)

typeof alert // "function"  (3)
```

## Conversion
### To String
```JavaScript
let value = String(true); // "true"
```
### To Number
Number conversion can be done automatically or explicitly.

When calling `Number(str)`, whitespaces from the start and end are removed (the string is trimmed). If the remaining string is empty, the result is 0. Otherwise, the number is “read” from the string. An error gives NaN.

```JavaScript
alert("6" / "2"); // 3, automatically converts because "/" is used for division. Note that this would not work with strings and "+" as that operator is used for string concatenation as well.

let num = Number("6"); // 6
num = Number("arbitrary string"); // NaN, conversion failed
num = Number(null); // 0
num = Number(undefined); // NaN
num = Number(true); // 1;
num = Number(false); // 0
```
### To Boolean
Falsy: Empty values like 0, "", null, undefined, and NaN become false when converted to `Boolean(val)`.

Everything else become true, including objects, so they're "truthy."
Do note that `Boolean("0")` and `Boolean(" ")` are true.

## Operators
Operators apply to operands, or arguments.

Operators are unary if they have a single operand. Think of adding a minus sign to make something negative.
```JavaScript
x = -x;
```

Operators are binary if there are two operators
```JavaScript
z = y - x;
```

The assignment operator (=) writes a value and then returns that value, so the following is valid:
```JavaScript
let a = 1;
let b = 2;
let c = 3 - (a = b + 1);
// a = 3
// c = 0
```

Special operators:
* % Remainder of integer division 
* * `5 % 2 = 1`
* \** Exponentiation
* * `2 ** 3 = 8`
* ++ Increment and -- Decrement
* * Postfix: `x++` is `x = x + 1` or `x += 1` and returns the previous value of x before the + 1
* * Prefix: `++x` does the same operators but returns the new value (does the operation before returning);
* Modify in-place
* * operator=, like `x *= 5` is the same as `x = x * 5`

### String Concatenation, binary +
Even if only one of the operands is a string, the other will be converted to a string too.
```JavaScript
alert(1 + '2'); // "12"
alert("con" + "cat" + "enated"); // "concatenated"
```
### Numeric Conversion, unary +
If the operand is not a number, the unary plus will convert it. Same as `Number(...)`.
```JavaScript
alert(+true); // 1
alert(+""); // 0
alert(+"2" + +"3"); // 5
```
Unary pluses are applied before binary pluses.

### Order of Operations
Precedence, or priority order, is applied. Generally, grouping () is done, unary operators are applied before binary ones, which are applied in the standard order of operations, with postfix(++) incre/decrementors higher priority than arithmetical operations and (++)prefix incre/decrementors on par with multiplication/division. Modify-in-place (/=) and assignment (=) are applied with very low priorities.

Comma operators have a lower precedence than everything above. 

Commas evaluate several expressions, divided with cxommas, but only returns the result of the last one
```JavaScript
let a = (1 + 2, 3 + 4);

alert( a ); // 7 (the result of 3 + 4)

a = 1 + 2, 3 + 4; // sums to a = 3, 7 then applies the = assignment operator and ignores the rest
// a = 3;

for (a = 1, b = 3, c = a * b; a < 10; a++) {
 ...
}

```

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Operator_Precedence

### Bitwise Operators
Treat arguments as 32-bit integers and work on the level of their binary representations.
* & AND
* | OR
* ^ XOR
* ~ NOT
* << LEFT SHIFT
* \>> RIGHT SHIFT
* |>>> ZERO-FILL RIGHT SHIFT

#### Bitwise Not Trick

Converts the number into a 32-bit integer, removing hte decimal part if it exists, then reverses all bits in this binary representation. 

In decimal, this means `~n === -(n+1)`.

### Logical Operators
* || OR - true if all aren't falsey
* && AND - true if all are truthy
* ! NOT - true if falsey

#### OR
OR finds the first truthy value from left to right and returns the original value of the operand. If all operands were found to be falsy, it returns the last operand.

OR can be used to return the first non-null, non-zero, non-undefined value.
```JavaScript
alert( null || 0 || 1 ); // 1 (the first truthy value)
alert( undefined || null || 0 ); // 0 (all falsy, returns the last value)
```

OR can also be used to set a value if something is not truthy.
```JavaScript
let x;

true || (x = 1);
alert(x); // undefined, because (x = 1) not evaluated

false || (x = 1); // needs to run assignment to evaluate
alert(x); // 1

// Same as
if (! false) {
    x = 1;
}
```

#### AND
AND finds the first falsey value from left to right and returns the original value of the operand. If all operands were found to be truthy, returns the last operand.
```JavaScript
alert( 1 && 2 && null && 3 ); // null
alert( 1 && 2 && 3 ); // 3, the last one
```

AND precedes OR in the order of operations. `a && b || c && d` is essentially the same as if the && expressions were in parentheses: `(a && b) || (c && d)`.

```JavaScript
(x > 0) && alert( 'Greater than zero!' );
// Same as
if (x > 0) {
  alert( 'Greater than zero!' );
}
```

#### NOT
Returns the inverse of the boolean value.
```JavaScript
alert( !true ); // false
alert( !0 ); // true
```

Can be used to convert to boolean types.
```JavaScript
alert( !!"non-empty string" ); // true
alert( !!null ); // false
```
## Comparison
Boolean results.
* <= is less than or equal to
* == is the equality check
* != is not equal to

> Object.is is a built-in method that compares values like === but works with NaN, so `Object.is(NaN, NaN) === true` and 0 and -0 are not equal. Doesn't work right with functions and arrays.

### Strings
Strings are compared in dictionary or lexicographical order, by their unicode index.
```JavaScript
'Z' > 'A' // Z comes after A, so it is greater
'a' > 'A' // lowercase is after uppercase, so it is greater
'Bee' > 'Be' // longer string is greater than the substring
'Glow' > 'Glee'
```
### Different Types
When comparing two different types, javaScript converts values to numbers. '01' becomes 1, true becomes 1.
```JavaScript
let a = 0;
alert( Boolean(a) ); // false

let b = "0";
alert( Boolean(b) ); // true

alert(a == b); // true!
```

### Strict Equality
== does not differentiate 0, '', and false. === checks equality without type conversion. !== is strict inequality

```JavaScript
alert( null == undefined );  // true, they == equal each other and nothing else.

alert( null == 0 ); // false
alert( null > 0 );  // false, converts null to a number, 0
alert( null >= 0 ); // true, converts null to a number, 0

alert( undefined == 0 ); // false
alert( undefined > 0 ); // false, converts undefined to NaN
alert( undefined < 0 ); // false, converts undefined to NaN
```
Don't use >=, >, <, <= with variables that may be null or undefined unless you're really sure of what you're doing. or just check for null or undefined beforehand with == null.

## Keyed Collections

# Custom Objects
## Prototypal Inheritance

# Classes
## Prototypes
## Inheritance

## Lifecycle and Garbage Collectoin
Check if a value is reachable. These are guarenteed to be stored in memory.

Local variables, parameters of the current function; global variables; and values reachable by chain of references are all reachable.

If the value of a variable pointing to an object is overwritten, the reference to the previous object is lost and has become unreachable. 

* The garbage collector takes roots and “marks” (remembers) them.
* Then it visits and “marks” all references from them.
* Then it visits marked objects and marks their references. All visited objects are remembered, so as not to visit the same object twice in the future.
* …And so on until every reachable (from the roots) references are visited.
* All objects except marked ones are removed.

### Optimizations to Garbage Collection
* Generational collection – objects are split into two sets: “new ones” and “old ones”. Many objects appear, do their job and die fast, they can be cleaned up aggressively. Those that survive for long enough, become “old” and are examined less often.
* Incremental collection – if there are many objects, and we try to walk and mark the whole object set at once, it may take some time and introduce visible delays in the execution. So the engine tries to split the garbage collection into pieces. Then the pieces are executed one by one, separately. That requires some extra bookkeeping between them to track changes, but we have many tiny delays instead of a big one.
* Idle-time collection – the garbage collector tries to run only while the CPU is idle, to reduce the possible effect on the execution.

http://jayconrod.com/posts/55/a-tour-of-v8-garbage-collection

https://mrale.ph/

# Flow Control
## General Loops
Can break out of any loop (for, while, etc.) with `break` directive. With break out of just that internal loop - may keep you in a containing loop.
```JavaScript
let sum = 0;

while (true) {

  let value = +prompt("Enter a number", '');

  if (!value) break; // (*)

  sum += value;

}
alert( 'Sum: ' + sum );
```
To break out of just that iteration but continue with loop, use `continue` to start a new iteration if the condition allows.
```JavaScript
for (let i = 0; i < 10; i++) {

  // if true, skip the remaining part of the body
  if (i % 2 == 0) continue;

  alert(i); // 1, then 3, 5, 7, 9
}
// Same as:
for (let i = 0; i < 10; i++) {
  if (i % 2) {
    alert( i );
  }
}
// Just with less nesting
```
You can add labels before a loop to break back to a specified place.
```JavaScript
outer: for (let i = 0; i < 3; i++) {

  for (let j = 0; j < 3; j++) {

    let input = prompt(`Value at coords (${i},${j})`, '');

    // if an empty string or canceled, then break out of both loops
    if (!input) break outer; // (*)

    // do something with the value...
  }
}
// In the code above, break outer looks upwards for the label named outer and breaks out of that loop.
// A call to break/continue is only possible from inside a loop and the label must be somewhere above the directive.
```

## For
```JavaScript
for (begin; condition; step) {
  // ... loop body ...
  // executes the beginning step upon entering the loop.
  // checks the condition before each iteration. If false, breaks out of the loop.
  // Runs the body while the condition is truthy.
  // Executes the step after the body on each iteration.
}
for (let i = 0; i < 3; i++) { // shows 0, then 1, then 2
  alert(i);
}
// Can skip steps
let i = 0; // we have i already declared and assigned

for (; i < 3; i++) { // no need for "begin"
  alert( i ); // 0, 1, 2
}

for (; i < 3;) {
  alert( i++ );
}

for (;;) {
  // repeats without limits
}
```

## For In
Allows iteration through object keys.
## For Of
## If Else
```
if (statement to be evaluated into a boolean) is true {
    do this;
} else it is false, if (another conditional statement) {
    do this instead;
} else second conditional statement is also false {
    do this then;
}
```
### Ternary Operator
```
let result = (conditional statement) ? result if true : result if false;
```
## While
```JavaScript
let i = 0;
while (i < 3) { // shows 0, then 1, then 2
  alert( i );
  i++;
}
```

As the while condition is checking for truthiness, a shorter way to write `while (i != 0)` is `while (i)`.

To first execute the body and then check for the condition, guaranteeing at least one iteration of the loop regardless of the condition's truthiness:
```JavaScript
do {
  // loop body
} while (condition);
```
## Switch Case
Check for strict equality to the value of each case until find one, then start to execute code from that place until the nearest break or the end of the switch. If no match is found, execute the default case, if it exists.
```JavaScript
let a = 2 + 2;

switch (a) {
  case 3:
    alert( 'Too small' );
    break;
  case 4:
    alert( 'Exactly!' );
    break;
  case 5:
  case 6: // can group cases if don't separate with breaks
    alert( 'Too large' );
    break;
  default:
    alert( "I don't know such values" );
}

// Can use arbitrary expressions
let a = "1";
let b = 0;

switch (+a) {
  case b + 1:
    alert("this runs, because +a is 1, exactly equals b+1");
    break;

  default:
    alert("this doesn't run");
}
```

# Functions
Perform the same action in multiple places.

## Function Declarations
A Function Declaration can be called earlier than it is defined. It is visible to the whole script because it is created at initialization of the script.

Function Declarations are block-scoped.
```JavaScript
name(parameters); // call 
function name(parameters, if, any = defaultValueIfNoneSupplied) { // declaration
  ...body...
  // variables declared in here are only visible inside here
  // variables declared outside of the function can be accessed and modified
  return value; // if no value is supplied, returns undefined  
  // After return, the function exits
}
```
Parameters are passed by value, not reference, so the outer value cannot be modified inside the function - a local copy will be modified instead.

Parameters that are not provided are undefined.

## Function Expressions
A Function Expression is created when the execution reaches it and is usable only from that moment.
```JavaScript
let sayHi = function() { // function expression
    alert("Hi!");
};
sayHi(); // call, get the result
```

## Callbacks
Callback Functions - functions passed as values.

```JavaScript
function ask(question, yes, no) {
  if (confirm(question)) yes()
  else no();
}

function showOk() {
  alert( "You agreed." );
}

function showCancel() {
  alert( "You canceled the execution." );
}

// usage: functions showOk, showCancel are passed as arguments to ask, acting as callbacks
ask("Do you agree?", showOk, showCancel);

ask(
  "Do you agree?",
  function() { alert("You agreed."); },
  function() { alert("You canceled the execution."); }
); // anonymous functions - without names
```

## Functions as First Class Objects
## This binding
"this" is evaluated at run-time and generally refers to the object "before the dot."

```JavaScript
let user = {
    name: "Ferdinand",
    hi() { alert(this.name); },
    bye() { alert("Bye!"); },
};
let hi = user.hi; // returns not a function but a reference type
hi(); // Error because this is undefined
(user.hi)(); // works
(user.name == "Ferdinand" ? user.hi : user.bye)();  // Error
(user.hi || user.bye)(); // Error because operations turn property accessors into Reference types which do not carry "this" information
```
https://javascript.info/object-methods

## Arrow Functions
Have no "this". They take their "this" from the outer context.

```JavaScript
let func = (arg1, arg2, ...argN) => {
    ...expression...
    return value;
} // multi-line
let func = (arg1, arg2, ...argN) => value; // inline
func(arg1, arg2, ...argN); // call

let user = {
    name: "Ferdinand",
    hi() { 
        let arrow = () => alert(this.name); 
        arrow();
    },
};

```

# Operators and Expressions
## Numeric Operators
## String Operators
### String Handling
### String Manipulation
### Regular Expressions

# Asynchronous Programming
## Promises
## AJAX
## Callbacks

# DOM
Can run JavaScript in the broser by inserting a <script src="path/to/script.js">Or write it here, inline;</script> tag. "path/to/script.js" is a relative path from the current folder (of the HTML page). "/path/to/script.js" is an absolute path from the site root. Your source can also be a full "https://url.domain.com/script.js". If the src is set, any content between the tags will be ignored.

Longer scripts are best stored in separate files (rather thn inline) because then the browser will download and store it in its cache for faster loading times and reduced traffic.
## Document Object
### Methods for Dynamically Generating Web Pages

## Window Object Hierarchy
## Inline-Frames (IFrames)
## Navigation
Forward and backward paging

## Events
### onChange
### onClick
### onLoad

## Forms
### Input Objects
### Validation

## Persistent Storage
### Client-Side Data
### LocalStorage and SessionStorage
Stores string key to string value pairs. If you're trying to store objects, need to JSON stringify them.
```JavaScript
sessionStorage.user = {name: "John"};
alert(sessionStorage.user); // [object Object]

sessionStorage.user = JSON.stringify({name: "John"});

// sometime later
let user = JSON.parse( sessionStorage.user );
alert( user.name ); // John

// added formatting options to JSON.stringify to make the object look nicer
alert( JSON.stringify(localStorage, null, 2) );
```

LocalStorage survives full browser restarts.

SessionStorage survies page refreshes.

Most browsers allow at least 2 mb of data.

#### Methods
* `setItem(key, value)`
* `getItem(key)`
* `removeItem(key)`
* `clear()` - deletes everything
* `key(index)` - get the key on a given position
* `length` - get the number of stored items

Like a Map collection but allows access by index.

#### LocalStorage
Shared between all tabs and windows from the same origin (domain/ port/ protocol; the url path can be different).
Doesn't expire, even after an OS reboot.

Old way of accessing:
```JavaScript
// set key
localStorage.test = 2;

// get key
alert( localStorage.test ); // 2

// remove key
delete localStorage.test;

// Bad way of doing it because can set key like length or toString or other built-in methods that will not work with getItem/ setItem or assignments.
let key = 'length';
localStorage[key] = 5; // Error, can't assign length
```

Iteration:
```JavaScript
for(let i=0; i<localStorage.length; i++) {
  let key = localStorage.key(i);
  alert(`${key}: ${localStorage.getItem(key)}`);
}

// bad try
for(let key in localStorage) {
  alert(key); // shows getItem, setItem and other built-in stuff
}

// better
for(let key in localStorage) {
  if (!localStorage.hasOwnProperty(key)) {
    continue; // skip keys like "setItem", "getItem" etc
  }
  alert(`${key}: ${localStorage.getItem(key)}`);
}

// best
let keys = Object.keys(localStorage);
for(let key of keys) {
  alert(`${key}: ${localStorage.getItem(key)}`);
}
```

#### SessionStorage
Exists only within the current browser tab. Shared between iframes in the same tab if they come from the same origin.

Survives page refreshes, but not opening/ closing the tab.

#### Storage Events
When data is updated (setItem, removeItem, clear) in localStorage or sessionStorage, the storage event triggers with properties:
* key - key that was changed, null if .clear() was called
* oldValue - null if the key is newly added
* newValue - null if the key is removed
* url - of the document where the update happened
* storageArea - either localStorage or sessionStorage that was modified by the event

The event triggers on all window objects where the storage is accessible except the one that caused it, i.e. all the ones that share that storage.

This allows different windows from the same origin to exchange messages.

```JavaScript
// triggers on updates made to the same storage from other documents
window.onstorage = event => { // same as window.addEventListener('storage', () => {
  if (event.key != 'now') return;
  alert(event.key + ':' + event.newValue + " at " + event.url);
};

localStorage.setItem('now', Date.now());
```

### Cookies
Small strings of data stored directly in the browser. Usually set by a "Set-Cookie" HTTP-header with a unique session identifier. The next time a request is set to the same domain, the browser sends the cookie using the "Cookie" HTTP- header so the server knows which session made the request.

Can access cookies from the browser using `document.cookie` which returns name=value pairs deliminated by ;s:  `cookie1=value1; cookie2=value2;...`

> The total number of cookies per domain is limited to around 20+, the exact limit depends on a browser. Each cookie shouldn't exceed 4kB.

```JavaScript
document.cookie = "user=John"; // update only cookie named 'user'
```
Technically, name and value can have any characters, to keep the valid formatting they should be escaped using a built-in `encodeURIComponent` function:
```JavaScript
// special characters (spaces), need encoding
let name = "my name";
let value = "John Smith"

// encodes the cookie as my%20name=John%20Smith
document.cookie = encodeURIComponent(name) + '=' + encodeURIComponent(value); // this pair should not exceed 4kB

alert(document.cookie); // ...; my%20name=John%20Smith
```

Cookies have options:
```JavaScript
document.cookie = "user=John; path=/; domain=site.com; expires=Tue, 19 Jan 2038 03:14:07 GMT";
```

Path allows the cookie to be accessible for all pages under that absolute path. Default is the current path. 

If a cookie is set with `path=/admin`, it’s visible at pages `/admin` and `/admin/something`, but not at `/home` or `/adminpage`.

Usually, we should set path to the root: `path=/` to make the cookie accessible from all website pages.

Domain is where the cookie is accessible. By default, it is only accessible at the domain that set it. 

```JavaScript
// at site.com
document.cookie = "user=John"

/* Accessibility from subdomains */
// at forum.site.com
alert(document.cookie); // no user
// make the cookie accessible on any subdomain *.site.com:
document.cookie = "user=John; domain=site.com"
alert(document.cookie); // has cookie user=John

/* Not accessible from another 2nd-level domain */
// at other.com
alert(document.cookie); // no user
```

Cookies by default disappear when the broswer is closed. These are "session cookies." To have them persist, need to set an expiration date  in GMT timezone or a max-age in seconds.

```JavaScript
// +1 day from now
let date = new Date(Date.now() + 86400e3);
date = date.toUTCString();
document.cookie = "user=John; expires=" + date;

// cookie will die +1 hour from now
document.cookie = "user=John; max-age=3600";

// delete cookie (let it expire right now)
document.cookie = "user=John; max-age=0";
```

Cookies are domain-based; they don't distinguish between protocols. By default, if we set a cookie at http://site.com, then it also appears at https://site.com and vice versa.

If a cookie has sensitive content that shouldn't be sent over unencrypted HTTP, set the secure flag so it doesn't appear when accessed over HTTP but will over HTTPs.
```JavaScript
// assuming we're on https:// now
// set the cookie secure (only accessible if over HTTPS)
document.cookie = "user=John; secure";
```
#### Cross-Site Request Forgery (XSRF)
Say you're logged into a banking site in one window, meaning you have an authentication cookie that recognizes you. In another window, you're broswing another site that submits a form to the banking site. The browser will send cookies even if the form was submitted from a different site - it will still recognize you and perform the request.

In reality, bank forms generate XSRF protection tokens that can't be generated or extracted from a remote page.

Another way around this is with the `samesite` cookie option.

A cookie with `samesite=strict` is never sent if the user comes from outside the same site.

In other words, whether a user follows a link from their mail or submits a form from evil.com, or does any operation that originates from another domain, the cookie is not sent.

If authentication cookies have samesite option, then XSRF attack has no chances to succeed, because a submission from evil.com comes without cookies. So bank.com will not recognize the user and will not proceed with the payment.

However, when a user follows a legitimate link to bank.com, like from their own notes, they’ll be surprised that bank.com does not recognize them. Indeed, samesite=strict cookies are not sent in that case.

We could work around that by using two cookies: one for “general recognition”, only for the purposes of saying: “Hello, John”, and the other one for data-changing operations with `samesite=strict`. Then a person coming from outside of the site will see a welcome, but payments must be initiated from the bank website, for the second cookie to be sent.

Lax mode, just like strict, forbids the browser to send cookies when coming from outside the site, but adds an exception.

A `samesite=lax` cookie is sent if both of these conditions are true:

* The HTTP method is “safe” (e.g. GET, but not POST).

* * The full list of safe HTTP methods is in the RFC7231 specification. Basically, these are the methods that should be used for reading, but not writing the data. They must not perform any data-changing operations. Following a link is always GET, the safe method.

* The operation performs top-level navigation (changes URL in the browser address bar).

* * That’s usually true, but if the navigation is performed in an <iframe>, then it’s not top-level. Also, JavaScript methods for network requests do not perform any navigation, hence they don’t fit.

So, what samesite=lax does is basically allows a most common “go to URL” operation to have cookies. E.g. opening a website link from notes satisfies these conditions.

`samesite` is not supported by old browsers, year 2017 or so.

`httpOnly` option forbids any JavaScript access to the cookie. We can’t see such cookie or manipulate it using document.cookie.

Useful Functions

```JavaScript
// returns the cookie with the given name,
// or undefined if not found
function getCookie(name) {
  let matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : undefined;
} // cookie values are encoded, so getCookie uses a built-in decodeURIComponent function to decode it.

function setCookie(name, value, options = {}) {

  options = {
    path: '/',
    // add other defaults here if necessary
    ...options
  };

  if (options.expires instanceof Date) {
    options.expires = options.expires.toUTCString();
  }

  let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

  for (let optionKey in options) {
    updatedCookie += "; " + optionKey;
    let optionValue = options[optionKey];
    if (optionValue !== true) {
      updatedCookie += "=" + optionValue;
    }
  }

  document.cookie = updatedCookie;
}

// Example of use:
setCookie('user', 'John', {secure: true, 'max-age': 3600});

function deleteCookie(name) {
  setCookie(name, "", {
    'max-age': -1
  })
}
// Should use the same path and domain options as when the cookie was set

```

##### Third-Party Cookies

When the cookie is placed by a domain other than the page the user is visiting (think ads). Any time that same third-party domain is accessed, you'll be recognized and can be tracked across pages.

If a script sets a cookie, then no matter where the script came from – the cookie belongs to the domain of the current webpage.

GDPR

GDPR is about cookies that track or identify users. If you're going to set a cookie with an authentication session, authorization, or tracking id, the user must allow this.

### IndexedDB
Built-in database intended for offline apps to be combined with ServiceWorkers and other technologies. Different websites can't access each other's databases.

* Supports multiple key types, almost any value type.
* Supports transactions for reliability.
* Supports key range queries, indexes.
* Can store more data than `localStorage`.

Open a database, joins all the dbs existing within the current origin v(domain/ protocol/ port):
```JavaScript
/*
 * @param name: string, a database name
 * @param version: positive integer, default 1
 */
let openRequest = indexedDB.open("name", version);

// listen for events on the openRequest object:

// upgradeneeded: db is ready, but its version is outdated, or the database does not exist and should be intialized
openRequest.onupgradeneeded = function() {
  // triggers if the client had no database
  // ...perform initialization...
};
// success: db is ready, the db object is in openRequest.result and should be used for future calls
openRequest.onsuccess = function() {
  let db = openRequest.result;
  // continue to work with database using db object
};
// error: opening failed, such as if you try to open a version lower than the existing version
openRequest.onerror = function() {
  console.error("Error", openRequest.error);
};

openRequest = indexedDB.open("store", 2);

openRequest.onupgradeneeded = function() {
  // the existing database version is less than 2 (or it doesn't exist)
  let db = openRequest.result;
  switch(db.version) { // existing db version
    case 0:
      // version 0 means that the client had no database
      // perform initialization
    case 1:
      // client had version 1
      // update
  }
};

// Delete the database
let deleteRequest = indexedDB.deleteDatabase(name)
// deleteRequest.onsuccess/onerror tracks the result
```
IndexedDB has a built-in mechanism of “schema versioning”, absent in server-side databases. If the local client-side database is less than the version specified in open, then a special event upgradeneeded is triggered, and we can compare versions and upgrade data structures as needed.

If the local client-side database is greater than the version specified, onerror event triggers.

If two tabs share a database version that needs to be updaed, all connections to the previous version must bve closed. This triggers the `versionchange` event on the open databvase object while a parallel upgrade is attempted. Listen to it and close the database (and maybe suggest to the visitor to reload the page) so the second, new connection won't be blocked with the `blocked` event.
```JavaScript
let openRequest = indexedDB.open("store", 2);

openRequest.onupgradeneeded = ...;
openRequest.onerror = ...;

openRequest.onsuccess = function() {
  let db = openRequest.result;

  // Add db.onversionchange listener after a successful opening, to be informed about a parallel update attempt.
  db.onversionchange = function() {
    db.close();
    alert("Database is outdated, please save data and reload the page.")
  };

  // ...the db is ready, use it...
};

// Add openRequest.onblocked listener to handle the case when an old connection wasn’t closed. This doesn’t happen if we close it in db.onversionchange.
openRequest.onblocked = function() {
  // there's another open connection to same database
  // and it wasn't closed after db.onversionchange triggered for them
};
```

Any value can be stored in IndexDB's "object store." A database can have multiple stores that can also store primitives. Anything that can be serialized (ex: something without circular references) can be stored.

An object store can only be created/modified while updating the DB version, in upgradeneeded handler.

Outside of the handler we’ll be able to add/remove/update the data, but object stores can be created/removed/altered only during version update.

Each value in the objectStore must have a unique key of type: number, date, string, binary, or array.
```JavaScript
/*
 * @param name: store name
 * @param keyOptions: optional object vwith one of two properties:
 *  @param keyPath: path to an object property that the IndexedDB will use as the key, like an id
 *  @param autoIncrement: if true, then the key for a newly stored object is auto-generated as an ever-incrementing number
 */
db.createObjectStore(name[, keyOptions]); // synchronous, no await needed

db.createObjectStore('books', {keyPath: 'id'});

// Creating in the proper place
let openRequest = indexedDB.open("db", 2);

// create/upgrade the database without version checks
openRequest.onupgradeneeded = function() {
  let db = openRequest.result;
  if (!db.objectStoreNames.contains('books')) { // if there's no "books" store
    db.createObjectStore('books', {keyPath: 'id'}); // create it
  }
};

// Delete a store
db.deleteObjectStore('books')
```

Transactions are groups of operations that should either all succeed or all fail. Like when a person buys something, money needs to be subtracted from their account and the item added to their inventory - should either one fail, neither should go through.

All data operations must be made within a transaction in an IndexedDB.

```JavaScript
/*
 * @param store: store name that the transactions accessed. Can be an array of names
 * @param type: 
 *  readonly: can only be read, default; can access the same store concurrently
 *  readwrite: can only read and write data, but not create/ remove/ alter object stores; store needs to be locked before writing
 */
db.transaction(store[, type]);
// There’s also versionchange transaction type: such transactions can do everything, but we can’t create them manually. IndexedDB automatically creates a versionchange transaction when opening the database, for updateneeded handler. That’s why it’s a single place where we can update the database structure, create/remove object stores.

// Create a transaction, mention all stores it’s going to access
let transaction = db.transaction("books", "readwrite"); 

// get an object store to operate on it
let books = transaction.objectStore("books");

let book = {
  id: 'js',
  price: 10,
  created: new Date()
};

// Perform the request
let request = books.put(book, optionalKey); // Add the value to the store. The key is supplied only if the object store did not have keyPath or autoIncrement option. If there’s already a value with same key, it will be replaced.
// OR
let request = books.add(book, optionalKey); // Same as put, but fails if there’s already a value with the same key, and an error with the name "ConstraintError" is generated.

// Handle request success/error
request.onsuccess = function() { 
  console.log("Book added to the store", request.result);
};

request.onerror = function() {
  console.log("Error", request.error);
};
```

# Security
## Same Origin Policy
Both pages must agree to data exchange.
## Cross-site Scripting Attacks

# Modern Mode
``"use strict";`` at the very top of a script will cause it to work the modern, post-ES5 way that may break the functionality of old code.

Classes and modules enable strict mode automatically.