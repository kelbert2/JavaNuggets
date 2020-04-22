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
## Lifecycle
## Values
JavaScript is dynamically typed - variables are not bound to just holding a single type through their lifecycle.

## Primitives
* number - integer ranging from -2^{53} to 2^{53} and floating point, ``Infinity``, ``-Infinity``, ``NaN`` (not a number)
* * A script will never stop with a fatal error, but will instead return ``NaN`` as a result if you, for example, try to divide a string by 2.
* BigInt - arbirtrarily long numbers, created by appending ``n`` to the end of a larger integer literal: ``1234n``
* "string", 'string', \`string with ${embeddedVariables + or + operations + to + evaluate}!`
* boolean - `true` or `false`; 
* null - nothing, empty, value unknown
* undefined - value is not assigned (yet)
* * `canCheckForUndefined == null;`

## Complex
* Objects can store collections of data
* Symbol creates unique identifiers for objects

### Objects
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
};
// access values
obj["key"] = "new value";
obj.key = true;
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

Everything else become true, so they're "truthy."
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

## Comparison
Boolean results.
* <= is less than or equal to
* == is the equality check
* != is not equal to
Strings are compared in dictionary or lexicographical order, by their unicode index.
```JavaScript
'Z' > 'A' // Z comes after A, so it is greater
'a' > 'A' // lowercase is after uppercase, so it is greater
'Bee' > 'Be' // longer string is greater than the substring
'Glow' > 'Glee'
```

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

# Flow Control
## For
## For In
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
## Switch Case

# Functions
## Definition
## Functions as First Class Objects
## This binding
## Arrow Functions

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
### LocalStorage
### Cookies

# Security
## Same Origin Policy
Both pages must agree to data exchange.
## Cross-site Scripting Attacks

# Modern Mode
``"use strict";`` at the very top of a script will cause it to work the modern, post-ES5 way that may break the functionality of old code.

Classes and modules enable strict mode automatically.