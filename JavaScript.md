https://javascript.info/tutorial/map

# Variables
## Syntax
```JavaScript
let mutableValriable; // declaration
mutableVariable = 'value'; // assignment
const IMMUTABLE_VARIABLE = 'value'; // combined declaration and assignment
let multiple = 1, variables = 2, can = 3, be = '4', assigned = 5;
```

In the olden days, only `var` was be used to declare variables.
`var` has no block scope - they're either function-wide or global. If the code-block is inside a function, then `var` declares a function-level variable. This was in a pre-Lexical Environment world.

`var` declarations are processed when the function or script starts - so like functions, they can be used before they are declared. Their declaration is hoisted to the top of the function, but their assignment is not - they will still be undefined until that assignment (declaration is hoisted, assignment is not).

To emulate block-level visibility, programmers used "immediately-invoked function expressions," or IIFE:
```JavaScript
(function() { // with (), do not need to declare a name
  var message = "Hello";
  alert(message); // Hello
})(); // immediately calls

(function() {
  alert("Parentheses around the function");
})();

(function() {
  alert("Parentheses around the whole thing");
}());

!function() {
  alert("Bitwise NOT operator starts the expression");
}();

+function() {
  alert("Unary plus starts the expression");
}();
```

## Scope and Context
Variables declared inside a function with the same name as a variable declared outside shadows the outer one. The outer one is ignored.

{ Declare within a block, and it's only visible in there. You can't declare a variable with the same name twice, or you'll get an error.}

If, for, and while also create their own blocks. Every running function, code block, and the script as a whole have internal associated objects called _lexical environments_. These have an environment record that stores all local variables as its properties and the value of this and a reference to the outer code's lexical environment.

```JavaScript
function makeCounter() {
  let count = 0;

  return function() {
    return count++;
  };
}

let counter = makeCounter(); // creates a new lexical environment for the call
// counter.[[Environment]] has a reference to {count: 0}.

alert( counter() ); // 0 // creates a new lexical environment for function() to run, outer reference taken from counter
alert( counter() ); // 1
alert( counter() ); // 2

let c = counter(); // stores a reference to the lexical environment of the call to counter()
```
When a function runs at the beginning of a call, a new Lexical Environment is automatically created to store the variables and parameters of the call. When the code wants to access a variable, it searches the inner Lexical Environment first, then the outer one, then the one outside that, on and on until the global Lexical Environment is reached or the variable is found.

A closure is a function that remembers its outer variables and can access them. In JavaScript, all functions are naturally closures - they automatically remember where they were created using the hidden [[Environment]] property, allowing their code to access outer variables.

Lexical Environments are gnerally garbage collected when the function call finishes - if there are no references to it. 

### Global Object
In the browser, this is called `window`; in Node.js, this is called `global`. `globalThis` has now been added to the language and is gaining support.

```JavaScript
alert("Hello");
// is the same as
window.alert("Hello");
```
If a value is so important that you’d like to make it available globally, write it directly as a property:
```JavaScript
// make current user information global, to let all scripts access it
window.currentUser = {
  name: "John"
};

// somewhere else in code
alert(currentUser.name);  // John

// or, if we have a local variable with the name "currentUser"
// get it from window explicitly (safe!)
alert(window.currentUser.name); // John
```
The use of global variables is still greatly discouraged. They are useful to test support of modern language features. From there, you can "polyfill": add functions that are not supported by the environment but exist in the modern standard in order to maintain compatibility.
```JavaScript
if (!window.Promise) {
  window.Promise = ... // custom implementation of the modern language feature
}
```

## Values
JavaScript is dynamically typed - variables are not bound to just holding a single type through their lifecycle.

## Primitives
* number - integer ranging from -2^{53} to 2^{53} and floating point, `Infinity`, `-Infinity`, `NaN` (not a number)
* * A script will never stop with a fatal error, but will instead return `NaN` as a result if you, for example, try to divide a string by 2.
* BigInt - arbirtrarily long numbers, created by appending `n` to the end of a larger integer literal: `1234n`
* "string", 'string', \`string with ${embeddedVariables + or + operations + to + evaluate}!`
* boolean - `true` or `false`; 
* null - nothing, empty, value unknown, does not exist
* undefined - value is not assigned (yet)
* * `canCheckForUndefined == null;`

### Primitive Wrappers
Objects that wrap around primitives and offer methods and properties and are then destroyed. Undefined and null do not have corresponding wrapper objects.
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
Ordered collections of elements.
```JavaScript
let arr = new Array();
let arr = new Array(numericLength); // fills with undefined
let arr = [];
let arr = ["initial", "elements",]; // can be a mix of strings, booleans, objects, numbers, functions, etc.

arr[0]; // "initial"
arr[1] = "replacementValue";
arr[2] = addedFunction;
arr[2](); // runs the function at index 2

// Multidimensional
let matrix = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
];

alert( matrix[1][1] ); // 5, the central element
```

Methods and Properties
* `arr.length` - length of the array, really the greatest index + 1.
* * Writable, so can increase or truncate the array, irreversibly. Can clear the array with `arr.length = 0;`.
* `arr.push(element[, element, ...])` - append element(s) to the end
* * Same as `arr[arr.length] = element`
* `arr.pop()` - pop off an element from the end - return it and remove it, like a stack .
* `arr.unshift(element[, element, ...])` - add element(s) to the beginning of the array.
* `arr.shift()` - extract the first element of array and return it (removes it), like a queue.
* `String(arr)` - returns a comma-separated list of elements
* `delete arr[index]` - deletes element at index, makes it undefined
* `arr.slice([start = 0], [end = length])` - returns a new array copying all items from [start, end) (not including end). Returns a subarray.
* * Allows for negative indices that will count from the end of the array: `["t", "e", "s", "t"].slice(-2) = s,t`.
* `arr.splice(index[, deleteCount, ele1, ..., eleN])` - starting from position index, removes deleteCount elements and then inserts ele1, ..., eleN at their place, returns the array of removed elements
* * To insert elements without removals, set deleteCount to 0
* * Negative indices specify position from the end of the array.
* * Modifies the array itself.
* `arr.concat(arg1, arg2...)` - creates a new array from arr, then items from arg1, arg2, etc. which can be arrays (copies all elements) or values themselves
* * Array-like objects that have `[Symbol.isConcatSpreadable]: true` property are treated like arrays and their elements added instead.
* `arr.forEach(function(item, index, array) {// do something});` - runs a function for each item in an array. The return of the function, if anything, is thrown away and ignored.
* `arr.indexOf(item, from)` - looks for an item starting from index from. Returns the index where it was found, else returns -1.
* * Uses === comparison, which doesn't work with NaN
* `arr.lastIndexOf(item, from)` 0 looks for from from right to left.
* `arr.includes(item, from)` - returns true if finds index starting search at index from.
* * Works with NaN.
* `arr.find((item, index, array) => {// Some condition. If returns true, the search is stopped and item is returned, else undefined is returned; })` and `arr.find((item, index, array) => {similar, can choose to only use (item) => {}, will return the index when the function returns true})`
* `arr.filter((item, index, array) => {if true, pushes item to result array and conteinues iteration, returns empty array [] if nothing is found that passes the filter})` - Technically returns the type of arr.constructor(), so this works even if extended from an array class
* `arr.map((item, index, array) => {transforms each item into a different value and returns an array of these new values})`
* `arr.sort()` - sorts the array in place (modifies the array itself) and returns the sorted array
* * Items are sorted by strings by default, so "15" comes before "2". For better string sorting, pass in the function `(a, b) => a > b ? 1 : -1`
* * Can pass own sorting function, like `function compareNumeric(a, b) { if (a > b) return 1; if (a == b) return 0; if (a < b) return -1;} // or {return a - b}` and `arr.sort(compareNumeric);`
* `arr.reverse()` - returns the array after the reversal and modifies the array itself.
* `str.split(deliminator[, arrayLengthLimit])` - splits a string into an array by the string deliminator. If an arrayLengthLimit is provided, extra elements past it are ignored.
* * If no deliminator is supplied, the string splits at every character.
* `arr.join(glue)` - creates a string by joining each element with glue between them.
* `arr.reduce((accumulator, item, index, array) => {}, [initial])` - where accumulator is the result of the previous function call, with an initial value of initial, if supplied. The result of the previous function call is passed to the next until it reaches the end, at which point it becomes the result of reduce.
* * If no initial value is supplied, the first element is taken as the initial value and iteration starts from the 2nd element.
* * Ex: `arr.reduce(sum, current) => sum + current, 0);`
* * Attempting to reduce an empty array without an initial value gives an error.
* `arr.reduceRight((accumulator, item, index, array) => {}, [optionalInitialValue])` does the same but from right to left.
* `Array.isArray(arr)` - returns true if the object is an array.
* `arr.some(fn)`/ `arr.every(fn)` check the array by calling fn on each element. If any/ all the results are true, returns true, otherwise returns false.
* `arr.fill(value, start, end)` – fills the array with repeating value from index start to end.
* `arr.copyWithin(target, start, end)` – copies its elements from position start until position end into itself, at position target (overwrites existing).

Many array methods like find, filter, and map (but not sort) take an additional optional parameter thisArg that becomes "this" for the function argument passed before it - it passes context, which is useful if the function uses "this" because the function is called as a standalone function, where this is otherwise undefined. Passing an arrow function instead avoids any issue with this.

Like objects, arrays are copied by reference, so variables all point and can modify to the same underlying object. 

```JavaScript
let arr = [1, 2];

let arrayLike = {
  0: "something",
  1: "else",
  [Symbol.isConcatSpreadable]: true,
  length: 2
};

alert( arr.concat([3,4], 5, arrayLike) ); // 1,2,3,4,5,something,else
```

Iterating through elements
```JavaScript
let arr = ["Apple", "Orange", "Pear"];

for (let i = 0; i < arr.length; i++) {
  alert( arr[i] );
}

// iterates over array elements without an index
for (let ele of arr) {
  alert( ele );
}

arr.forEach(function(item, index, array) {
  // ... do something with item
});
["Bilbo", "Gandalf", "Nazgul"].forEach((item, index, array) => {
  alert(`${item} is at index ${index} in ${array}`);
});
```
Don't iterate using for..in because that would iterate over all properties, not just the numeric ones.

Array-likes are objects that have numeric indexes and `length`.

`Array.from(arrayLike)` takes an object or iterable and makes a real Array from it (copying all of its objects into it), that can then call methods like `pop()`.

There is also an optional mapping function: `Array.from(obj[, mapFn, thisArg])`, mapFn, which is applied to each element before adding it to the array. thisArg sets up context, if necessary.

This works with strings with surrogate pairs unlike `str.split()`

### Iterables
Arrays, strings, and collections are iterable.  

To make a custom object iterable, add a Symbol.iterator method that for...of will call.

```JavaScript
let range = {
  from: 1,
  to: 5
};
// Call to for..of initially calls this:
range[Symbol.iterator] = function() {
  // ...which returns the iterator object, which has a next() method:
  return {
    current: this.from,
    last: this.to,
    // next() is called on each iteration by the for..of loop
    next() {
      // it should return the value as an object {done: Boolean, value : any}
      if (this.current <= this.last) {
        return { done: false, value: this.current++ }; // value is the next value
      } else {
        return { done: true }; // means the iteration is finished
      }
    }
  };
};

// now it works!
for (let num of range) {
  alert(num); // 1, then 2, 3, 4, 5
}

// Using Range itself as the iterator:
// Note that this way makes it impossible to have multiple for...of loops running over hte object simultaneously as they would be sharing the iteration state because there's only a single iterator - the object itself.
let range = {
  from: 1,
  to: 5,

  [Symbol.iterator]() {
    this.current = this.from;
    return this; // returns the range object itself, which has the necessary next() message
  },

  next() {
    if (this.current <= this.to) {
      return { done: false, value: this.current++ };
    } else {
      return { done: true };
    }
  }
};

```

Explicit iterator with direct calls instead of for...of:
```JavaScript
let iterator = str[Symbol.iterator]();

while (true) {
  let result = iterator.next();
  if (result.done) break;
  alert(result.value); // outputs characters one by one
}
```

### Map and Set
Maps are collections of keyed data items, with keys of any type, such as numbers, booleans, and objects.
* `new Map()`
* * `new Map([ [key, value], ["key", value]])` - can pass an array or other iterable with key/ value pairs for initialization
* * `new Map(Object.entries(obj))`
* * `Object.fromEntries(["key", value], ["key2", value])` is the reverse of this that works with `Object.fromEntries(map.entries())` which is the same as `Object.fromEntries(map)` becaise the standard iteration for map is `entries`
* `map.set(key, value)` - returns the map itself, so calls can be chained.
* `map.get(key)` - returns the values, undefined if key is not in map.
* `map.has(key)` - true if key exists in the map, otherwise false.
* `map.delete(key)`
* `map.clear()`
* `map.size` - returns current element count
* `map.forEach((value, key, map) => {// do something})`

`map[key]` converts key to a string, so avoid doing this if you want to use maps to their full potential.

Map compares keys using SameValueZero, which is like strict equality === but NaN is considered equal to NaN.

Iterating over Maps
* `map.keys()` - returns an iterable for keys.
* `map.values()` - returns an iterable for values.
* `map.entries()` - returns an iterables for entries [key, value].
* * Used by default in for..of.

```JavaScript
let recipeMap = new Map([
  ['cucumber', 500],
  ['tomatoes', 350],
  ['onion',    50]
]);

// iterate over keys (vegetables)
for (let vegetable of recipeMap.keys()) {
  alert(vegetable); // cucumber, tomatoes, onion
}
// iterate over [key, value] entries
for (let entry of recipeMap) { // the same as of recipeMap.entries()
  alert(entry); // cucumber,500 (and so on)
}

recipeMap.forEach((value, key) => {
alert(value + ", " + key);
});
```

The order of iteration is the order of insertion.
#### Sets
Sets are sets of values where each value can only occur once - they're unique. There are no keys.
* `new Set(optionalIterable)` - if an iterable is provided, like an array, copies values into hte new set.
* `set.add(value)` - returns the set itself, so can be chained. If the value already exists, does nothing and just returns the set.
* `set.delete(value)` - removes the value, returns true if the value existed at the moment of the call, otherwise false.
* `set.has(value)` - returns true if the value exists in the set.
* `set.clear()`
* `set.size` - returns the element count.
* `set.forEach((value, valueAgain, set) => {})`

Iterating over Sets

It's a bit funny to maintain compatibility with maps.
* `set.keys()` - returns an iterable for values, same as `set.values()`.
* `set.values()` - returns an iterable for values.
* `set.entries()` - returns an iterables for entries [value, value].
```JavaScript
for (let value of set) alert(value);

// the same with forEach:
set.forEach((value, valueAgain, set) => {
  alert(value);
});
```

#### WeakMap and WeakSet
Putting an object into an array or a map will keep it alive, even if we later overwrite the reference to null; it won't be garbage collected.
```JavaScript
let john = { name: "John" };

let array = [ john ];

let map = new Map();
map.set(john, "...");

john = null; // overwrite the reference

// john is stored inside the array, so it won't be garbage-collected
// we can get it as array[0]
// we can get it by using map.keys()
```

WeakMap doesn't prevent this garbage-collection of key objects.

In WeakMaps, keys must be objects - they can't be primitives. As soon as there are no other references to the key object, it is removed from memory and, therefore, the map. 
```JavaScript
let john = { name: "John" };

let weakMap = new WeakMap();
weakMap.set(john, "secret documents");

john = null; // overwrite the reference

// When john dies, secret documents will be destroyed automatically
```
There are no iteration or methods weakMap.keys(), weakMap.values(), weakMap.entries() so there's no way to get all of these from a WeakMap.
* `weakMap.get(key)`
* `weakMap.set(key, value)`
* `weakMap.delete(key)`
* `weakMap.has(key)`
The current count (size) of a WeakMap is not known as the time that the garbage collector performs memory cleanup is not known.

WeakMap elements only exist while their objects are alive. Like if you're storing the count of users currently viewing a page. This also keeps the map from growing in memory indefinitely.

Caching - when a function result should be remembered so that future calls on the same object reuse it. With a weak map, the cache is automatically cleaned when the object is no longer needed.
```JavaScript
// 📁 cache.js
let cache = new WeakMap();

// calculate and remember the result
function process(obj) {
  if (!cache.has(obj)) {
    let result = /* calculate the result for */ obj;

    cache.set(obj, result);
  }
  return cache.get(obj);
}

// 📁 main.js
let obj = {/* some object */};

let result1 = process(obj);
let result2 = process(obj); // remembered result taken from cache

// ...later, when the object is not needed any more:
obj = null;
```

WeakSets also can only take objects - no primitives - that are only reachable from somewhere else. There is no size, keys(), or iterations. Only individual operations are allowed.

### Date and Time
New Date
* `new Date()` - takes the exact current date and time.
* `new Date(milliseconds)` - milliseconds passed after 1 Jan 1970 UTC+0 (timestamp), can be negative.
* `new Date(datestring)` - parsed with Date.parse
* `new Date(year, month, date = 1, hours = 0, minutes = 0, seconds = 0, ms = 0)` - only the first two arguments are obligatory. Years must have 4 digits and months start with 0 (January) to 11 (December).

Access components relative to the local time zone. To get UTC-counterparts, add "UTC" after "get."
* `getFullYear()` - 4 digits
* `getMonth()` - from 0 to 11
* `getDate()` - returns the day of the month
* `getHours()`, `getMinutes()`, `getSeconds()`, `getMilliseconds()`
* `getDay()` - returns day of the week, 0 (Sunday) to 6 (Saturday)
The following don't have a UTC-variant:
* `getTime()` - returns timestamp - the  number of milliseconds passed from the 1st of January, 1970 UTC+0.
* `getTimezoneOffset(0` - returns the difference between UTC and the local time zone, in minutes.
```JavaScript
// if you are in timezone UTC-1, outputs 60
// if you are in timezone UTC+3, outputs -180
alert( new Date().getTimezoneOffset() );
```

Setting components (all except setTime() have UTC-variants):
* `setFullYear(year, [month], [date])`
* `setMonth(month, [date])`
* `setDate(date)`
* `setHours(hour, [min], [sec], [ms])`
* `setMinutes(min, [sec], [ms])`
* `setSeconds(sec, [ms])`
* `setMilliseconds(ms)`
* `setTime(milliseconds)` - sets the whole date by milliseconds since 01.01.1970 UTC.
Components that are not mentioned are not modified.

Dates autocorrect, so `new Date(2013, 0, 32); // 32 Jan 2013` turns into 1 Feb 2013.

`+date` conversion to numbers returns the timestamp. When dates are subtracted, the result is their difference in ms.

`Date.now()` returns the current timestamp but doesn't create an intermediate Date object like `new Date().getTime()` does, making it faster and easier on the garbage collection.

`Date.parse(str)` parsing a string in the format: `YYYY-MM-DDTHH:mm:ss.sssZ` where T is the delimiter and the optional 'Z' is the timezone in the format `+-hh:mm`. A single letter Z that would mean UTC+0.

### Error
### Dictionary
```JavaScript
let dictionary = Object.create(null, { // descriptor flags are enumerable:false by default
  toString: { // define toString property
    value() { // the value is a function
      return Object.keys(this).join();
    }
  }
});

dictionary.apple = "Apple";
dictionary.__proto__ = "test";

// apple and __proto__ is in the loop
for(let key in dictionary) {
  alert(key); // "apple", then "__proto__"
}

// comma-separated list of properties by toString
alert(dictionary); // "apple,__proto__"
```
### Linked List
```JavaScript
let list = {
  value: 1,
  next: {
    value: 2,
    next: {
      value: 3,
      next: {
        value: 4,
        next: null
      }
    }
  }
};
```
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
// will not show up in for..in loop
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
let pureObject = Object.create(null); // creates a fully blank object - there's no prototype, and therefore no helpful methods like toString(). Object-related methods like Object.something(), like Object.keys(pureObject), will work

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

Object-Related Methods
* `Object.keys(obj)`, `Object.values(obj)`, `Object.entries(obj)` - returns an array of enumerable own string property names/ values/ key-value pairs
* `Object.getOwnPropertyNames(obj)` - returns an array of all own string keys
* `Object.getOwnPropertySymbols(obj)` - returns an array of all own symbolic keys
* `Reflect.ownKeys(obj)` - returns an array of all own keys
* `obj.hasOwnProperty(key)` - returns true if obj has its own (not inherited) key named "key"

Own keys do not include inherited ones. for..in loops will find inherited ones as well.

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
#### Supporting Keys, Values, and Entries
Plain objects can use:
* `Object.keys(user) = ["name", "age"]`
* `Object.values(user) = ["John", 30]`
* `Object.entries(user) = [ ["name","John"], ["age",30] ]`
for for..of loops. Compare to `map.keys()` and `set.values()`.

```JavaScript
let prices = {
  banana: 1,
  orange: 2,
  meat: 4,
};

let doublePrices = Object.fromEntries(
  // convert to array, map, and then fromEntries gives back the object
  Object.entries(prices).map(([key, value]) => [key, value * 2])
);
```

#### Property Flags
Object properties have values and three special attributes called flags:
* `writable` - if true, value can be changed, otherwise it's read-only.
* `enumerable` - if true, then this property is listed in for..in loops and `Oject.keys(obj)`, otherwise it is hidden
* `configurable` - if true, the property can be deleted, and these attributes/ flags cannot be modified, otherwise not
By default, all are true.

Something like Math.PI is nonwritable, nonenumerable, and nonconfigurable - its value cannot be overwritten, it does not show up when enumerating over the Math Object, and it cannot be deleted.
```JavaScript
// Getting Flags
let descriptor = Object.getOwnPropertyDescriptor(obj, propertyName);

alert( JSON.stringify(descriptor, null, 2 ) );
/* property descriptor:
{
  "value": "John",
  "writable": true,
  "enumerable": true,
  "configurable": true
}
*/

// Changing Flags
Object.defineProperty(obj, propertyName, descriptor);
// if propertyName exists, update its flags. Otherwise, create a property with the given value and flags, default flags to false if not supplied.

let user = {
  name: "John",
  toString() {
    return this.name;
  }
};
// Writable
Object.defineProperty(user, "name", {
  writable: false
});

user.name = "Pete"; // Error: Cannot assign to read only property 'name'

// Enumerable
Object.defineProperty(user, "toString", {
  enumerable: false
});

// Now our toString disappears:
for (let key in user) alert(key); // name
alert(Object.keys(user)); // name

// Configurable
// Making a property nonconfigurable cannot be undone. You cannot change any other flags after setting this one to false. 

Object.defineProperty(user, "name", {
  value: "John",
  writable: false,
  configurable: false
});
// You can assign the property if you haven't yet, but once you do, it's a forever sealed constant.

// won't be able to change user.name or its flags
// all this won't work:
//   user.name = "Pete"
//   delete user.name
//   defineProperty(user, "name", { value: "Pete" })
Object.defineProperty(user, "name", {writable: true}); // Error
```

Cloning with property descriptors:
```JavaScript
let clone = Object.defineProperties({}, Object.getOwnPropertyDescriptors(obj));
```
Limiting access to the object:
* `Object.preventExtensions(obj)` - Forbids addition of new properties to the object.
* `Object.seal(obj)` - Forbides adding and removing of properties. Sets `configurable: false` for all existing properties.
* `Object.freeze(obj)` - Forbids adding, removing, and changing of properties. Sets `configurable: false, writable: false` for all existing properties.
Testing properties:
* `Object.isExtensible(obj)` - Returns false if adding properties is forbidden, otherwise true.
* `Object.isSealed(obj)` - Returns true if adding and removing properties is forbidden and all existing properties have `configurable: false`.
* `Object.isFrozen(obj)` - Returns true if adding, removing, and changing properties is forbidden and all current properties are `configurable: false, writable: false`.

#### Accessor Properties
Data properties are the ones we're most familiar with - they have values. There are also accessor properties that are essentially functions that work on getting and setting values, but externally look like regular properties.

They are call and read normally. They have `enumerable` and `configurable` data properties.
```JavaScript
let obj = {
  get propName() {
    // getter, the code executed on getting obj.propName
  },
  set propName(value) {
    // setter, the code executed on setting obj.propName = value
  }
};

let user = {
  name: "John",
  surname: "Smith",

  get fullName() {
    return `${this.name} ${this.surname}`;
  }
};
alert(user.fullName); // John Smith
user.fullName = "Test"; // Error (property has only a getter)

// Adding the below will allow the property to be set
  set fullName(value) {
    if (value.length < 4) { // setters allow greater control over what will be set.v
      alert("Name is too short, need at least 4 characters");
      return;
    }
    [this.name, this.surname] = value.split(" ");
  }

// Although external code can access user._name, generally view properties that start with _ as internal
let user = {
  get name() {
    return this._name;
  },

  set name(value) {
    if (value.length < 4) {
      alert("Name is too short, need at least 4 characters");
      return;
    }
    this._name = value;
  }
};
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

To create a fully identical shallow clone of obj, can also use Object.create - this is more powerful than copying properties with for..in:
```JavaScript
let clone = Object.create(Object.getPrototypeOf(obj), Object.getOwnPropertyDescriptors(obj));
// This call makes a truly exact copy of obj, including all properties: enumerable and non-enumerable, data properties and setters/getters – everything, and with the right [[Prototype]].
```

If one of the properties of an object is an object, then cloning will only clone a reference to the internal object - they will share that same internal object. To properly clone this, you need deep cloning to check if something is an object that needs to replicated as well.

#### Prototypal Inheritance
To extend an object with its properties an methods and build a new object on top of it.

Objects have a special hidden property `[[Prototype]]` that is either null or a reference to another object. When we try to read a property from the object and it's missing, we automatically take it from the prototype.

Everything inherits from objects.
```JavaScript
// it inherits from Array.prototype?
alert( arr.__proto__ === Array.prototype ); // true

// then from Object.prototype?
alert( arr.__proto__.__proto__ === Object.prototype ); // true

// and null on the top.
alert( arr.__proto__.__proto__.__proto__ ); // null
// Prototypes are global, so don't modify the prototype for something like a primitive or you'll be changing how everything acts.
```

Setting it yourself (do note that this is very slow):

```JavaScript
// Historical getter/ setter
let animal = {
  name: "animal",
  eats: true,
  walk() {
    alert(this.name + " walk");
  },
};
let rabbit = {
  __proto__: animal, // must be either an object or null
  name: "rabbit",
  jumps: true,
};
// Can chain references, just don't make a circle
let longEar = {
  __proto__: rabbit,
  name: "long-ear rabbit",
  earLength: 10,
};

// we can find both properties in rabbit now:
alert( rabbit.eats ); // true (**)
alert( rabbit.jumps ); // true
longEar.walk(); // long-ear walk
// the value of "this" is still the object before the dot
// Methods are shared, not internal object state

rabbit.walk = function() { // define own walk function, won't take from Animal
  alert("Rabbit! Bounce-bounce!");
};
rabbit.walk(); // Rabbit! Bounce-bounce!
```

Iterating Over Properties

The for..in loop will iterate over inherited properties as well.
```JavaScript
let animal = {
  eats: true,
  stomach: [], // shared amongst all that inherit from animal
  eat(food) {
      this.stomach = [food]; // creates a local stomach for each that inherit from it
  }
};

let rabbit = {
  jumps: true,
  __proto__: animal // as objects are passed by reference, if animal changes, so will rabbit's prototype
};

// Object.keys only returns own keys
alert(Object.keys(rabbit)); // jumps

// for..in loops over both own and inherited keys
for(let prop in rabbit) alert(prop); // jumps, eats
// __proto__ is not expressed

for(let prop in rabbit) {
  let isOwn = rabbit.hasOwnProperty(prop);

  if (isOwn) {
    alert(`Our: ${prop}`); // Our: jumps
  } else {
    alert(`Inherited: ${prop}`); // Inherited: eats
  }
}
```
Object.prototype properties, such as hasOwnProperty, are not seen because they have the flat `enumerable:false`.

Other ways of setting a prototype:
```JavaScript
let animal = {
  eats: true
};
function Rabbit(name) {
  this.name = name;
}

// The default prototype is an object with the only property constructor, which points back to the function itself
/* default prototype */
Rabbit.prototype = { constructor: Rabbit };
let rabbit2 = new rabbit.constructor("Black Rabbit");

// When a new Rabbit is created, assign its [[Prototype]] to animal. This is only used when new is called.
Rabbit.prototype = animal;
let rabbit = new Rabbit("White Rabbit"); //  rabbit.__proto__ == animal
alert(rabbit.constructor === Rabbit); // false
// To avoid this last case, don't overwrite Rabbit.prototype fully, just add to it
Rabbit.prototype.jumps = true;
// or recreate the constructor manually
Rabbit.prototype = {
  jumps: true,
  constructor: Rabbit
};
```

Modern ways of setting the prototype:
* `Object.create(proto[, descriptors])` – creates an empty object with given proto as `[[Prototype]]` and optional property descriptors.
* `Object.getPrototypeOf(obj)` – returns the `[[Prototype]]` of obj.
* `Object.setPrototypeOf(obj, proto)` – sets the `[[Prototype]]` of obj to proto
```JavaScript
// create a new object with animal as a prototype
let rabbit = Object.create(animal);
let rabbit = Object.create(animal, {
  jumps: {
    value: true
  }
});

alert(rabbit.jumps); // true

alert(rabbit.eats); // true

alert(Object.getPrototypeOf(rabbit) === animal); // true

Object.setPrototypeOf(rabbit, {}); // change the prototype of rabbit to {}

```
__proto__ is not a property of an object, but an accessor property of Object.prototype. It's a getter/ setter, a way to access `[[Prototype]]` but not `[[Protytpe]]` itself.

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

##### JavaScript Object Notation (JSON)
Useful for converting complex objects to strings in order to send them over networks or output them to logs without needing to update a `toString()` method for each newly updated property. 
* `JSON.stringify(value[, replacer, space])` - converta objects into JSON. The resulting json string is called a JSON-encoded/ serialized/ stringified/ marshalled object.
* * Replacer is an array of properties to encode or a mapping `function(key, value)`. A null replacer just returns as default.
* * Space is the number of spaces to use for prettier formatting (indents for nested objects), otherwise there are no spaces.
* `JSON.parse(str[, reviver])` - converts JSON back into an object.
In JSON, strings use double quotes and object names are double-quoted.
* * Reviver is an optional `function(key, value)` that gets called for each pair and can transform its value.

JSON supports {Objects}, [Arrays], strings, numbers, booleans, and null. JSON skips Function properties (methods), Symbolic properties, comments, and undefined. There cannot be any circular references. 

To filter out circular references, use the replacer argument. Passing an array of properties ensures that only those properties will be encoded. A function can return the value to replace a value with, or undefined if it should be skipped.
```JavaScript
let room = {
  number: 23
};

let meetup = {
  title: "Conference",
  participants: [{name: "John"}, {name: "Alice"}],
  place: room // meetup references room
};

room.occupiedBy = meetup; // Circular reference: room references meetup

alert( JSON.stringify(meetup, ['title', 'participants']) );
// {"title":"Conference","participants":[{},{}]}
// Skip only the circular reference: 
alert( JSON.stringify(meetup, function replacer(key, value) {
  alert(`${key}: ${value}`);
  return (key == 'occupiedBy') ? undefined : value;
}));

/* key:value pairs that come to replacer:
:             [object Object] // The first call is special. It is made using a special “wrapper object”: {"": meetup}. In other words, the first (key, value) pair has an empty key, and the value is the target object as a whole. 
title:        Conference
participants: [object Object],[object Object]
0:            [object Object]
name:         John
1:            [object Object]
name:         Alice
place:        [object Object]
number:       23
*/

// The reverse with JSON. parse: 
let meetup = JSON.parse(str, function(key, value) {
  if (key == 'date') return new Date(value);
  return value;
});
```

`JSON.stringify()` will automatically call a `obj.toJSON()` method if it exists.

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

### Destructuring
Unpacks arrays and objects into a bunch of variables, for convenience. The underlying array or object is not modified.

Works with strings, sets, arrays, maps, and objects and with any assignables on the left side.
```JavaScript
// we have an array with the name and surname
let arr = ["Ilya", "Lee", "Kantor"]

// destructuring assignment
// sets firstName = arr[0]
// and surname = arr[1]
let [firstName, middlenName, surname] = arr;
// Skipping values
let [firstName, , surname] = "Ilya Lee Kantor".split(' '); // skip the middle name
// Missing values
let [firstName, surname] = []; // firstName and surName are now both undefined
// Default values
let [name = "Guest", surname = "Anonymous"] = ["Julius"];
let [name = prompt('name?'), surname = prompt('surname?')] = ["Julius"];

// loop over keys-and-values
for (let [key, value] of Object.entries(user)) {
  alert(`${key}:${value}`); // name:John, then age:30
}

let [name1, name2, ...rest] = ["Julius", "Caesar", "Consul", "of the Roman Republic"];

alert(name1); // Julius
alert(name2); // Caesar

// Note that type of `rest` is Array.
alert(rest[0]); // Consul
alert(rest[1]); // of the Roman Republic
alert(rest.length); // 2

// Object Destructuring
let options = {
  title: "Menu",
  width: 100,
  height: 200
};

let {title, width, height} = options;
// Order Doesn't Matter
let {height, width, title} = { title: "Menu", height: 200, width: 100 }
// Assigning to different variable names
let {width: w, height: h, title} = options;
// width -> w
// height -> h
// title -> title
// Default Values
let {width = 100, height: h = 200, title} = options;
// Extract only what you need
let { title } = options;
let {title, ...rest} = options;
// now title="Menu", rest={height: 200, width: 100}

// Without let, need to make sure it won't think it's a code block
let title, width, height;
({title, width, height} = {title: "Menu", width: 200, height: 100});

// More detailed
let options = {
  size: {
    width: 100,
    height: 200
  },
  items: ["Cake", "Donut"],
  extra: true
};

// destructuring assignment split in multiple lines for clarity
let {
  size: { // put size here
    width,
    height
  },
  items: [item1, item2], // assign items here
  title = "Menu" // not present in the object (default value is used)
} = options;
```

Destructuring makes things a lot more readable when it comes to functions too.
```JavaScript
function({
  incomingProperty: varName = defaultValue
  ...
}) {
    // do something with varName;
}
```
Example:
```JavaScript
function showMenu(title = "Untitled", width = 200, height = 100, items = []) {
  // ...
}
// undefined where default values are fine
showMenu("My Menu", undefined, undefined, ["Item1", "Item2"]);

// Becomes

function showMenu({title = "Untitled", width: w = 200, height = 100, items : [item1, item2]= []}) {
  // title, items – taken from options,
  // width, height – defaults used
  alert( `${title} ${w} ${height}` ); // My Menu 200 100
  alert( item1 ); // Item1, Item2
}
let options = {
  title: "My menu",
  items: ["Item1", "Item2"]
};
showMenu(options);
showMenu({}); // all values are default

function showMenu({ title = "Menu", width = 100, height = 200 } = {}) {
  alert( `${title} ${width} ${height}` );
}
showMenu(); // all values are default
```
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

Methods are non-enumerable. Can have getters and setters and computed properties.
```JavaScript
class User {
  // Field
  static staticField = 'static field'; // without initializer is undefined

  constructor(name) {
    this.name = name;
    this.sayHi = this.sayHi.bind(this);
  }

  sayHi() {
    alert(this.name);
  }
  click = () => {
    alert(this.value);
  } // doesn't need this binding

  // computed property
  ['say' + 'Hello']() {
    alert("Hello");
  }
}

// Usage:
let user = new User("John"); // calls the constructor
user.sayHi();

// Expression
let User = class {
  sayHi() {
    alert("Hello");
  }
};
// Named Class Expression
// (no such term in the spec, but that's similar to Named Function Expression)
let User = class MyClass {
  sayHi() {
    alert(MyClass); // MyClass name is visible only inside the class
  }
};

new User().sayHi(); // works, shows MyClass definition

alert(MyClass); // error, MyClass name isn't visible outside of the class
```

### Inheritance
To build new functionality upon existing (without overwriting), we extend classes. Then `Child.prototype.__proto__ = Parent.prototype` and methods can be inherited.
```JavaScript
function f(phrase) {
  return class {
    sayHi() { alert(phrase) }
  }
}

class User extends f("Hello") {}

new User().sayHi(); // Hello
```

Parent functions can be overwritten in the child by defining them there:
```JavaScript
class Animal {
  constructor(options) {
    this.speed = 0;
    let {name = 'default name'} = options
    this.name = name;
  } 
  /*
  constructor({ template }) {
    this.template = template;
  }
  */

  run(speed) {
    this.speed = speed;
    alert(`${this.name} runs with speed ${this.speed}.`);
  }

  stop() {
    this.speed = 0;
    alert(`${this.name} stands still.`);
  }
}

class Rabbit extends Animal {
  constructor(name, earLength) {
    super({name}); // have to call before use "this" because it creates this
    this.earLength = earLength;
  }
  hide() {
    alert(`${this.name} hides!`);
  }

  stop() { // overrides parent function - this will be called instead
    super.stop(); // optional - call parent stop
    // Note that arrow functions do not have super
    this.hide(); // and then hide
  }
}

let rabbit = new Rabbit("White Rabbit");

rabbit.run(5); // White Rabbit runs with speed 5.
rabbit.stop(); // White Rabbit stands still. White rabbit hides!
```

In order to enable extension from an extended class that allows for super to be called, properties have the internal property [[HomeObject]]. When the function is specified as a class or object method, [[HomeObject]] is set to that object and cannot be changed. Copying this function, if it uses a super call, may result in errors.

Methods must be defined as method(), not like this:
```JavaScript
let animal = {
  eat: function() { // intentionally writing like this instead of eat() {...
    // ...
  }
};

let rabbit = {
  __proto__: animal,
  eat: function() {
    super.eat();
  }
};

rabbit.eat();  // Error calling super (because there's no [[HomeObject]])
```
More detail:
https://javascript.info/class-inheritance

Properties and methods can be static. They can be accessed outside of an instance of the class. These are inheritable.
```JavaScript
class Article {
  static publisher = "Ilya Kantor";

  constructor(title, date) {
    this.title = title;
    this.date = date;
  }

  static createTodays() {
    // remember, this = Article
    return new this("Today's digest", new Date());
  }
}
alert( Article.publisher ); // Ilya Kantor
let article = Article.createTodays();

alert( article.title ); // Today's digest

// Same as assigning to the class itself
Article.publisher = "Ilya Kantor";
```

Objects can only have one [[Prototype]] and extend only one other class. Mixins are classes that contain methods that can be used by other classes without the need to inherit from it. They can add behavior.
```JavaScript
// mixin
let sayMixin = {
  say(phrase) {
    alert(phrase);
  }
};
// more complicated mixin
let sayHiMixin = {
  __proto__: sayMixin, // (or we could use Object.create to set the prototype here)
  sayHi() {
    // call parent
    super.say(`Hello, ${this.name}`);
  },
  sayBye() {
    super.say(`Bye, ${this.name}`);
  }
};

// usage:
class User extends Person{
  constructor(name) {
    super(name);
    this.name = name;
  }
}

// copy the methods
Object.assign(User.prototype, sayHiMixin);

// now User can say hi
new User("Dude").sayHi(); // Hello Dude!// mixin
let sayHiMixin = {
  sayHi() {
    alert(`Hello ${this.name}`);
  },
  sayBye() {
    alert(`Bye ${this.name}`);
  }
};

// usage:
class User {
  constructor(name) {
    this.name = name;
  }
}

// copy the methods
Object.assign(User.prototype, sayHiMixin);

// now User can say hi
new User("Dude").sayHi(); // Hello Dude!
``` 
Event handling mixin:
```JavaScript
let eventMixin = {
  /**
   * Subscribe to event, usage:
   *  menu.on('select', function(item) { ... }
  */
  on(eventName, handler) { // when that name occurs, function handler runs
    if (!this._eventHandlers) this._eventHandlers = {};
    if (!this._eventHandlers[eventName]) {
      this._eventHandlers[eventName] = [];
    }
    this._eventHandlers[eventName].push(handler); // add to list of event handlers, even if has the same name as another
  },

  /**
   * Cancel the subscription, usage:
   *  menu.off('select', handler)
   */
  off(eventName, handler) {
    let handlers = this._eventHandlers && this._eventHandlers[eventName];
    if (!handlers) return;
    for (let i = 0; i < handlers.length; i++) {
      if (handlers[i] === handler) {
        handlers.splice(i--, 1); // remove from the list
      }
    }
  },

  /**
   * Generate an event with the given name and data
   *  this.trigger('select', data1, data2);
   */
  trigger(eventName, ...args) {
    if (!this._eventHandlers || !this._eventHandlers[eventName]) {
      return; // no handlers for that event name
    }

    // call the handlers
    this._eventHandlers[eventName].forEach(handler => handler.apply(this, args));
  }
};
```

### Restricting Property and Method Access
Encapsulation - separate the internal from the external interface
* Internal interface – methods and properties, accessible from other methods of the class, but not from the outside. Best to make these private. Conventionally, these names are prefixed with an _.
* * Almost standard: #private prop or method that can't be accessed from outside or from inheriting classes
* External interface – methods and properties, accessible also from outside the class. These are public.
```JavaScript
class CoffeeMachine {

  #waterAmount = 0;

  get waterAmount() {
    return this.#waterAmount;
  }

  set waterAmount(value) {
    if (value < 0) throw new Error("Negative water");
    this.#waterAmount = value;
    // this['#name'] doesn’t work
  }
}
```

### Checking Classes
`instanceOf` operator checks whether an existing object belongs to a certain class, taking inheritance into account.

This looks for a static method [Symbol.hasInstance](obj) and calls it. If this is not found, check whether Class.prototype is one of the prototypes in the obj prototype chain.
```JavaScript
alert( new Rabbit() instanceof Rabbit ); // true
let arr = [1, 2, 3];
alert( arr instanceof Object ); // true

// Under the hood:
obj.__proto__ === Class.prototype?
obj.__proto__.__proto__ === Class.prototype?
obj.__proto__.__proto__.__proto__ === Class.prototype?
...
// if any answer is true, return true
// otherwise, if we reached the end of the chain, return false
// which is used in objA.isPrototypeOf(objB)
Class.prototype.isPrototypeOf(obj);

// Custom
class Animal {
  static [Symbol.hasInstance](obj) {
    if (obj.canEat) return true;
  }
}

let obj = { canEat: true };

alert(obj instanceof Animal); // true: Animal[Symbol.hasInstance](obj) is called

// Can also immitate typeOf returning a string with
let user = {
  [Symbol.toStringTag]: "User"
};
{}.toString.call(user); // [object User]

```
 Other ways: https://javascript.info/instanceof

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

## For..In
Allows iteration through object keys. Loops over all enumerable properties. Best for objects.

Iterates over all properties, so not good for arrays where you only want to iterate over numeric ones.
## For..Of
Iterates over elements in an iterable array-like object. Doesn't work on objects. Better for arrays and strings.

Works with characters in strings and any object that implements Symbol.iterator to make it iterable.

```JavaScript
for (let char of "test") {
  alert( char ); // t, then e, then s, then t
}
```
## If...Else
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

## Error Handling, try..catch
Instead of dying when an error is encountered, you can do something else.

Code in the try block is executed. If no errors are found, the catch block is ignored. If there is a finally block, that gets executed.

If there is an error, the try execution stops and control flow switches to the beginning of hte catch block. The err variable will have some details about what happened. Then the finally block will execute, even if an error was encountered in the catch block or it returns.

Try..catch only works for runtime errors - not syntax or other parse-time ones. Try..catch also only works for synchronous code. Calling setTimeout(function) within a try block won't work because by the time the function is executed, the engine has already left the try block. The try..catch block can be inside the setTimeout(function).

Errors have:
* name
* *  for an undefined variable that’s "ReferenceError"
* message
Other non-standard properties are supported in most environments, like
* stack - current call stack - a string with information about the sequence of nested calls that led to the error.

```JavaScript
let json = "{ bad json }";

try {

  let user = JSON.parse(json); // <-- when an error occurs...

 if (!user.name) {
    throw new CustomValidationError("Incomplete data: no name"); // generate an error with the given message if the json doesn't contain a name property
  }
} catch (e) { // optional block, error object e not needed up here if not needed down there
  // ...the execution jumps here
  if (e instanceof "SyntaxError") { 
     alert( "Our apologies, the data has errors, we'll try to request it one more time." );
     alert( e.name );
     alert( e.message ); // JSON Error: Incomplete data: no name
  } else if (e instanceof "CustomValidationError") { // works even if we extend CustomValidationError in the future
  } else {
      throw e; // rethrow  if don't know how to handle the error
      // can be caught by an outer try-catch
  }
  // send a new network request
  // offer alternatives to the visitor
  // log the error
} finally { // optional block
    // runs after try or after catch in all cases, including if they return.
}
```

You can make your own errors.
```JavaScript
let error = new Error(message);
// or 
let error = new SyntaxError(message);
let error = new ReferenceError(message);
let error = new TypeError(message);

// For built-in errors, the name property is the name of the constructor
let error = new Error("Things happen o_O");
alert(error.name); // Error
alert(error.message); // Things happen o_O

class CustomValidationError extends Error {
    constructor(property) {
    super("No property" + property);
    this.name = "CustomValidationError"; // this.constructor.name
    this.property = property;
  }
}
``` 

Reactions to global errors depend on the environment. Node.js has `process.on("uncaughtException")` and the browser can assign a function to `window.onerror = (message, url, line, col, error)` to run in case of an uncaught error, such as one outside of a try..catch block.

# Functions
Perform the same action in multiple places.

Primitives are passed by value, objects like arrays are passed by reference, so such things can be modified within the function.

Functions can accept an arbirtrary number of arguments. Too many and the extra ones are ignored. Too few and the missing ones are undefined. 

All declared functions havea special `arguments` local variable.

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

Lets are only usable once they've been declared, but functions are ready to use even before then.

## Function Expressions
A Function Expression is created when the execution reaches it and is usable only from that moment.
```JavaScript
let sayHi = function() { // function expression
    alert("Hi!");
};
sayHi(); // call, get the result
```

They can also be Named Function Expressions (NFE). This allows the function to reference itself internally (for recursion). This name is not visible outside of the function.
```JavaScript
let sayHi = function func(who) { // name is func, which is funciton-local - an internal function name
    if (who) {
    alert(`Hello, ${who}`);
  } else {
    func("Guest"); // use func to re-call itself
  }
};
sayHi("John"); // Hello, John
func(); // Error, func is not defined (not visible outside of the function)

// Could use sayHi for the nested call, but error if it gets redefined
let sayHi = function(who) {
  if (who) {
    alert(`Hello, ${who}`);
  } else {
    sayHi("Guest"); // Error: sayHi is not a function if redefined in the outer Lexial Environment
  }
};

let welcome = sayHi;
sayHi = null;

welcome(); // Error, the nested sayHi call doesn't work any more!
```

## New Functions
Rarely used. Create a function literally from a string that is passed at runtime. When created, it references the glocal Lexical Environment, rather than the more specific one where it was created - it will not have access to outer variables, only global ones.

This means it still works with minifiers, which shrink code by removing extra comments and spaces and renaming local variables with shorter names. 
```JavaScript
let func = new Function ([arg1, arg2, ...argN], functionBody);
let sum = new Function('a', 'b', 'return a + b');

alert( sum(1, 2) ); // 3

function getFunc() {
  let value = "test"; // local variable
  let func = new Function('alert(value)'); // can only see global variables
  return func;
}
getFunc()(); // error: value is not defined
```
## Rest Parameters and Spread Syntax
To accept an unknown number of arguments, use rest parameters to create an array.
```JavaScript
function sumAll(...args) { // args is the name for the array
  let sum = 0;

  for (let arg of args) sum += arg;

  return sum;
}

alert( sumAll(1) ); // 1
alert( sumAll(1, 2) ); // 3
alert( sumAll(1, 2, 3) ); // 6

function showName(firstName, lastName, ...titles) { // titles gathers all remaining arguments into an array
  alert( firstName + ' ' + lastName ); // Julius Caesar

  // the rest go into titles array
  // i.e. titles = ["Consul", "Imperator"]
  alert( titles[0] ); // Consul
  alert( titles[1] ); // Imperator
  alert( titles.length ); // 2
}
```

In the olden days, rest parameters didn't exist, but an array-like object named arguments could be used to access arguments by index. It was iterable, but not array - so there was no ability to call methods like `arguments.map(...)` on it. Arrow functions don't have this object - they get it from the outer function.

To do the opposite of this, we can pass a spread object to expand an iterable, like an array or string, into a list of arguments.
```JavaScript
let arr = [3, 5, 1];

alert( Math.max(...arr) ); // 5 (spread turns array into a list of arguments)
alert( Math.max(...arr1, ...arr2, 25) ); // 25

let merged = [0, ...arr, 2, ...arr2];
alert([...("Hello")]); // H, e, l, l, o
```

Copying Iterables

Arry.from(obj) works on array-likes, whereas [...obj] only works on iterables.
```JavaScript
let arrCopy = [...arr]; // spread the array into a list of parameters
                        // then put the result into a new array
let objCopy = { ...obj }; // spread the object into a list of parameters
                          // then return the result in a new object
```

## Functions as First Class Objects
Functions are objects - callable action objects that can be passed by reference to other functions, returned as values, and assigned to variables or stored in data structures. As objects, they also have properties that can be added and removed. 

Properties
* `.name`
* `.length` - number of parameters, not counting ...rest parameters
* can also add custom properties - these are not the same as variables
```JavaScript
function sayHi() {
  alert("Hi");
}
let sayHi = function() { // also assigns a name
  alert("Hi");
};
// Name Property
alert(sayHi.name); // sayHi
function f(sayHi = function() {}) {
  alert(sayHi.name); // sayHi (works!)
}

f();

// function created inside array
let arr = [function() {}]; // name is ""

// Custom properties
function sayHi() {
  alert("Hi");

  // let's count how many times we run
  sayHi.counter++;
}

// Properties instead of closures
function makeCounter() {

  function counter() {
    return counter.count++;
  };

  counter.count = 0; // stored in the function directly, not its outer Lexical Environment
  return counter;
}

let counter = makeCounter();
alert( counter() ); // 0
alert( counter() ); // 1

counter.count = 10; // can be accessed by external code, unlike variables defined within the function
```
## Callbacks
Functions - functions passed as values.

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
Error-first callback style, where the firts argument of hte callback is reserved for an error if it occurs, so it can then call callback(error). Any subsequent arguments are for the successful result, where callback(null, arg1, arg2, ...) are called.
```JavaScript
function loadScript(src, callback) {
  let script = document.createElement('script');
  script.src = src;

  script.onload = () => callback(null, script);
  script.onerror = () => callback(new Error(`Script load error for ${src}`));

  document.head.append(script);
}

// Usage
loadScript('/my/script.js', function(error, script) {
  if (error) {
    // handle error
  } else {
    // script loaded successfully
  }
});
```
Beware of callback hell or the pyramid of doom, which develops when too many callbacks are nested within each other. You can break up callbacks into distinct functions, rather than having them all be anonymous. The best alternative is to use Promises.

## setTimeout and setInterval
Used for scheduling function execution a certain time later. This is supplied for most enviornments, like browsers and Node.js.
* `setTimeout` - after an interval of time
* `setInterval` - run after an interval of time, then repeat continuously at that interval
```JavaScript
/*
 * @param func|code: Function (including arrow functions) or string of code to execute (passing a string is not recommended, but will be done as with new Function(str)).
 * @param delay: Delay before running in milliseconds.
 * @param args: Arguments for the function. Not supported in IE9-.
 */
let timerId = setTimeout(func|code, [delay = 0], [arg1], [arg2], ...);
// After one second, say hi:
let timer = setTimeout(sayHi, 1000, "Hello", "John"); // Hello, John
clearTimeout(timer); // cancels the execution

let timerId = setInterval(func|code, [delay], [arg1], [arg2], ...);
// repeat with the interval of 2 seconds
let timerId = setInterval(() => alert('tick'), 2000);
// In most browsers, the internal timer will continue clicking while showing the alert/ confirm/ or prompt - it won't pause until you dismiss the window.

// after 5 seconds stop
setTimeout(() => { clearInterval(timerId); alert('stop'); }, 5000);

// Nested setTimeouts - allow for variation in time between calls, unlike with setInterval
let timerId = setTimeout(function tick() {
  alert('tick');
  timerId = setTimeout(tick, 2000); // schedule the next call right at the end of the current one
}, 2000);

// Zero delay setTimeout
setTimeout(() => alert("World")); // , 0) delay
// schedules func as soon as possible, but only after the currently executing script is complete
alert("Hello,");
// Hello,
// World
```
The garbage collector will not clean up the function until clearInterval or clearTimeout are called.

In the browser, there’s a limitation of how often nested timers can run. The HTML5 standard says: “after five nested timers, the interval is forced to be at least 4 milliseconds.”. The same thing will happen with a setInterval with zero-delay.

Exact delays are otherwise not guaranteed. They might slow down if the function takes longer to execute than its delay, or the CPU is overloaded, or the browser tab is in background mode, or the laptop is on battery. This all may increase the minimal timer resolution (minimal timer delay) to 300ms or even 1000ms depending on the browser and OS-level performance settings.

setTimeout in browsers sets this context to window.

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

When passing object methods as callbacks, you don't want to lose this context of the object.

We can bind this using a special function-like exotic object that is callable as a function and passing the call with a setting of this. Even if the original object that was passed is later changed, the value of this will remain with its original version that was passed to it.

You can also fix some parameters of the original function
```JavaScript
let boundFunc = func.bind(context[, arg1]); // fixes the value of this
boundFunc(); // call

// Fix arguments - Partial Function Application
function mul(a, b) {
  return a * b;
}

let double = mul.bind(null, 2); // partial function - less trivial variant

alert( double(3) ); // = mul(2, 3) = 6
alert( double(4) ); // = mul(2, 4) = 8
alert( double(5) ); // = mul(2, 5) = 10
```
Fix some arguments, but not the context of this:
```JavaScript
function partial(func, ...argsBound) {
  return function(...args) { // (*)
    return func.call(this, ...argsBound, ...args);
  }
}
user.sayNow = partial(user.say, new Date().getHours() + ':' + new Date().getMinutes());
```
## Arrow Functions
Have no "this". They take their "this" from the outer context. They also do not have an `arguments` array, but you can still pass parameters.

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
## Decorators and Forwarding, Call/ Apply
For functions with results that are stable - for the same input, they always return the same output (deterministic), you can cache results to avoid recalculating the same thing.

Decorators are functions that take another function and alter its behavior. This is a design pattern.
```JavaScript
function slow(x) {
  // there can be a heavy CPU-intensive job here
  alert(`Called with ${x}`);
  return x;
}
function hash(args) { // where args is iterable and array-like, but not necessarily an array
  return args[0] + ',' + args[1]; // super simple joining of two elements
  // or see below
  return [].join.call(args);
}
function cachingDecorator(func, hash) {
  let cache = new Map();

  return function(x) { // create a wrapper around the function
    let key = hash(arguments); // create single key from array of arguments
    if (cache.has(key)) {    // if there's such key in cache
      return cache.get(key); // read the result from it
    }

    let result = func(x);  // otherwise call func, with this = undefined
    // OR to use context:
    let result = func.call(this, x); // "this" is passed correctly now
    // has no effect if func doesn't use a value of this

    // If multiple arguments
    let result = func.call(this, ...arguments); // where args is iterable
    // Same as
    let result = func.apply(this, arguments); // where args is array-like, often faster because more optimized

    cache.set(key, result);  // and cache (remember) the result
    return result;
  };
}

slow = cachingDecorator(slow); // redefine slow to use the cache
alert( slow(1) ); // slow(1) is cached
alert( "Again: " + slow(1) ); // the same, taken from the cache

alert( slow(2) ); // slow(2) is cached
alert( "Again: " + slow(2) ); // the same as the previous line

let worker = {
  someMethod() {
    return 1;
  },

  slow(x) {
    alert("Called with " + x);
    return x * this.someMethod(); // (*)
  }
};
worker.slow = cachingDecorator(worker.slow, hash); // now make it caching with the proper use of this
```
This doesn't work with object methods (defined within an object) that access other methods or properties within the object, because this becomes undefined. We need to explicitly set "this" to access that context using `func.call(context, args)`.
```JavaScript
func.call(context, arg1, arg2, ...); // runs func with context of this
function say(phrase) {
  alert(this.name + ': ' + phrase);
}

let user = { name: "John" };
let admin = { name: "Admin" };

// use call to pass different objects as "this"
say.call( user, "Hello" ); // John: Hello
say.call( admin, "Hi" ); // Admin: Hi
```
 
Method borrowing - borrow a join method from a regular array (make it blank for ease) and call it in the context of arguments.

This works because join appends this[0] to "," to this[1] to "," to this[2], etc. So we just set this to arguments, and then we can treat an iterable array-like object with actual array methods.
```JavaScript
[].join.call(arguments); // this = arguments
```

# Asynchronous Programming
## Promises
There is so producing code that takes time. The consuming code receives the result when its ready through a promise.
```JavaScript
let promise = new Promise(function(resolve, reject) {
  // if job finishes successfully, resolve(value) else reject(error)
  // any code after a resolve(value) or reject(error) in a block is ignored
});

function loadScript(src) {
  return new Promise(function(resolve, reject) {
    let script = document.createElement('script');
    script.src = src;

    script.onload = () => resolve(script);
    script.onerror = () => reject(new Error(`Script load error for ${src}`));

    document.head.append(script);
  });
}
let promise = loadScript("https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.11/lodash.js");

promise.then(
  script => alert(`${script.src} is loaded!`),
  error => alert(`Error: ${error.message}`)
);

promise.then(script => alert('Another handler...')); // new handler added to the same promise
```
Internal properties of Promises:
* state - initally pending, then settles into either fulfilled or rejected - together called its settled state
* result - initially undefined, then value or error
If the Promise is already settled, handlers get the result or error immediately.

Consuming function are registered (subscribed) to the promise using these methods:
* .then - operates on and returns a promise (or at least a thenable object), so can check together
* .catch
* .finally
```JavaScript
promise.then(
  function(result) { /* handle a successful result */ },
  function(error) { /* handle an error */ }
);
// often written as 
promise.then(
  result => {},
  error => {}
);

// When only interested in doing something in case of an error:
promise.then(
  null,
  error => {errorHandlingFunction(error)}
);
// OR
promise.catch(errorHandlingFunction)

// When want something to run whether or not there was an error:
new Promise((resolve, reject) => {
  /* do something that takes time, and then call resolve/reject */
})
  // runs when the promise is settled, doesn't matter successfully or not
  .finally(() => stop loading indicator)
  // finally has no arguments but does pass through results and errors to the next handler
  .then(result => show result, err => show error);
  // can be chained

// Don't have to operate on Promises, just need Thenables
class Thenable {
  constructor(num) {
    this.num = num;
  }
  then(resolve, reject) {
    alert(resolve); // function() { native code }
    // resolve with this.num*2 after the 1 second
    setTimeout(() => resolve(this.num * 2), 1000); // (**)
  }
}
```
To catch all errors, append .catch to the end of a chain. If any of the previous promises reject, it will be caught. Throw new Error and reject(new Error) will both get caught.
```JavaScript
fetch('/article/promise-chaining/user.json')
  .then(response => response.json())
  .then(user => fetch(`https://api.github.com/users/${user.name}`))
  .then(response => response.json())
  .then(githubUser => new Promise((resolve, reject) => {
    let img = document.createElement('img');
    img.src = githubUser.avatar_url;
    img.className = "promise-avatar-example";
    document.body.append(img);

    setTimeout(() => {
      img.remove();
      resolve(githubUser);
    }, 3000);
  }))
  .catch(error => alert(error.message));
  ```

  The JavaScript engine tracks unhandled promise rejections (no reject function, no .catch) and generates a global error. Errors like this can be caught in the browser: 
  ```JavaScript
  window.addEventListener('unhandledrejection', function(event) {
  // the event object has two special properties:
  alert(event.promise); // [object Promise] - the promise that generated the error
  alert(event.reason); // Error: Whoops! - the unhandled error object
});

new Promise(function() {
  throw new Error("Whoops!");
}); // no catch to handle the error
```

To execute many promises in parallel and wait until all of them are ready, use Promise.all. This is all or nothing - all are successful or the entire object isn't.
```JavaScript
let promise = Promise.all([...promises...]);

Promise.all([
  new Promise(resolve => setTimeout(() => resolve(1), 3000)), // 1
  new Promise(resolve => setTimeout(() => resolve(2), 2000)), // 2
  new Promise(resolve => setTimeout(() => resolve(3), 1000))  // 3
]).then(alert); // 1,2,3 when promises are ready: each promise contributes an array member at the same index it was inserted into

// Don't have to pass promises exclusively
Promise.all([
  new Promise((resolve, reject) => {
    setTimeout(() => resolve(1), 1000)
  }),
  2,
  3
]).then(alert); // 1, 2, 3

// Array of jobs
let names = ['iliakan', 'remy', 'jeresig'];
let requests = names.map(name => fetch(`https://api.github.com/users/${name}`));

Promise.all(requests)
  .then(responses => {
    // all responses are resolved successfully
    for(let response of responses) {
      alert(`${response.url}: ${response.status}`); // shows 200 for every url
    }
    return responses;
  })
  // map array of responses into an array of response.json() to read their content
  .then(responses => Promise.all(responses.map(r => r.json())))
  // all JSON answers are parsed: "users" is the array of them
  .then(users => users.forEach(user => alert(user.name)));
```
Accepts any iterable and returns a new promise that resolves when all listed promises are settled. The array of their results becomes its result. If any promise is rejected, the promise returned by Promise.all immediately rejects with that error and the results of other promises are ignored, though the other promises are not cancelled.

Promise.allsettled([]) waits for all promises to settle, regardless of their results, and has an array of:
* {status:"fulfilled", value:result} for successful responses,
* {status:"rejected", reason:error} for errors.

As this is a newer addition, it may need to be polyfilled:
```JavaScript
if(!Promise.allSettled) {
  Promise.allSettled = function(promises) {
    return Promise.all(promises.map(p => Promise.resolve(p).then(value => ({
      state: 'fulfilled',
      value
    }), reason => ({
      state: 'rejected',
      reason
    }))));
  };
}
```

If you only want to wait for the first settled promise to get its result or error, there's Promise.race(iterable).
```JavaScript
Promise.race([
  new Promise((resolve, reject) => setTimeout(() => resolve(1), 1000)),
  new Promise((resolve, reject) => setTimeout(() => reject(new Error("Whoops!")), 2000)),
  new Promise((resolve, reject) => setTimeout(() => resolve(3), 3000))
]).then(alert); // 1
```

Rarely used now with async/ await: Promise.resolve(value) / .reject(error), which return a resolved or a rejected promise. Useful for when a function is expected to return a promise, so you can then use .then() on the next line.
```JavaScript
new Promise.resolve(value);
// Same as
new Promise(resolve => resolve(value));
```

Functions that use callbacks can be converted to use promises through promisification:
```JavaScript
let loadScriptPromise = function(src) {
  return new Promise((resolve, reject) => {
    loadScript(src, (err, script) => {
      if (err) reject(err)
      else resolve(script);
    });
  })
}
// usage:
// loadScriptPromise('path/script.js').then(...)
```

Promise handlers like .then, .catch, and .finally are asynchronous. Even if they're immediately resolved, the code lines below them will run before these handlers.

There's an internal queue, PromiseJobs, also called the microtask queue. An unhandled rejection occurs when a promise error is not handled at the end of the microtask queue.

# Async/ Await
async before a function means that it returns a promise. Values are wrapped in a promise automatically.

```JavaScript
async function f() {
  return 1;
}

// Same as:
async function f() {
  return Promise.resolve(1);
}

f().then(alert); // 1
```
await works inside async functions that makes JavaScript wait until that promise settles and returns its result. It acts like promise.then.
```JavaScript
async function f() {
  let promise = new Promise((resolve, reject) => {
    setTimeout(() => resolve("done!"), 1000)
  });

  let result = await promise; // pause at this line - wait until the promise settles

  alert(result); // "done!"
}

f();
```
Converting from Promise to async / await:
```JavaScript
async function showAvatar() {

  // read our JSON
  let response = await fetch('/article/promise-chaining/user.json');
  let user = await response.json();

  // read github user
  let githubResponse = await fetch(`https://api.github.com/users/${user.name}`);
  let githubUser = await githubResponse.json();

  // show the avatar
  let img = document.createElement('img');
  img.src = githubUser.avatar_url;
  img.className = "promise-avatar-example";
  document.body.append(img);

  // wait 3 seconds
  await new Promise((resolve, reject) => setTimeout(resolve, 3000));

  img.remove();

  return githubUser;
}

showAvatar();

// Can wrap in an anonymous async function:
(async () => {
  let response = await fetch('/article/promise-chaining/user.json');
  let user = await response.json();
  ...
})();
```
await accepts thenables. If the promise resolves normally, the result is returned. If it is rejected, it throws an error.
```JavaScript
async function f() {
  await Promise.reject(new Error("Whoops!"));
}
// Same as
async function f() {
  throw new Error("Whoops!");
}
```
You can wrap the await in a try..catch block or append .catch(function) at the end of the call to the async function.
```JavaScript
async function f() {
  try {
    let response = await fetch('/no-user-here');
    let user = await response.json();
  } catch(err) {
    // catches errors both in fetch and response.json
    alert(err);
  }
}
// OR
f().catch(alert); // takes in the error object that gets passed
```



# Operators and Expressions
## Numeric Operators
## String Operators
### String Handling
### String Manipulation
### Regular Expressions

# Call Stack
Allows the interpreter to keep track of its place in the script - which function is currently running, which ones are called from within it, etc.

When a script calls a function, the interpreter adds it to the (LIFO) call stack and then starts carrying it out. Any functions it calls are added further up the call stack and run when their calls are reached. When the current function is finished, the interpreter takes it off the stack and resumes execution where it left off in the last code listing.

If the stack takes up more space than it was assigned, you get a stack overflow error.

Function calls form stacks of frames, with all of the function's arguments and local variables. 

## Event Table and Event Queue
Functions like `setTimeout()` or async functions run asynchronously after finishing the current execution block. They are added to the event table, which knows which functions are triggered after which events. If the event occurs, it sends a notice to the Event Queue, but it does not execute them or add them to the call stack.

The Event Loop is a constantly (on each tick) running process that checks if the call stack is empty and, if so, it looks at the event queue and moves whatever something that is in there to the call stack.

```JavaScript
setTimeout(() => console.log('first'), 0); // add callback function to event table after timeout has passed, add to event queue, if empty, add to call stack and run
console.log('second'); // add to call stack and run
// Logs
// second
// first

// After all, the 0 in setTimeout is just the minimum delay after which it will be pushed into the queue
```
If you are calling an insane number of recursive calls, to avoid a stack overflow, you can put them in a `setTimeout(() => recursiveCall(), 0)`, so the calls will not directly pile up on the stack.

Whenever a function runs, it cannot be pre-empted and will run entirely before any others.

The Event Loop allows for the performance of non-blocking Input/ Output (I/O) operations by offloading them to the (often) multi-threaded system kernel to execute in the background. Such operations include network requrests and filesystem operations.

### Message Queue
For user-initiated events like click or keyboard events, fetch responses, DOM events like onLoad, and timeout callbacks. Once everything in the call stack is processed, it picks things in the message queue.

### Job Queue
As of ES6 (ECMAScript 2015), used by Promises rather than putting results of async functions at the end of the call stack. Promises that resolve before the current function ends are execute right after the current function.

Acts as a fastpass ticket compared to the rollercoaster line of the message queue. 

```JavaScript
const bar = () => console.log('bar')

const baz = () => console.log('baz')

const foo = () => {
  console.log('foo')
  setTimeout(bar, 0)
  new Promise((resolve, reject) =>
    resolve('should be right after baz, before bar')
  ).then(resolve => console.log(resolve))
  baz()
}

foo()

/*
foo
baz
should be right after baz, before bar
bar
*/
```
https://flaviocopes.com/javascript-event-loop/ 

# Browsers
Platforms are hosts that provide specific functionality, like objects and functions.

The root object of a browser, a host environment, is the window - it's the global object and represents the browser window and allows control of it. The DOM (Document Object Model) is the main entry point to the page that can be used to change or create things there.

CSSOM specification describes stylesheets and style rules.

HTML specification describes the HTML language (tags) and the BOM.
## BOM
THe Browser Object Model reprents additional objects provided by the broswer for working with everything except the document.
* `navigator` - object that provides background information about the browser and the OS.
* * `navigator.userAgent` - about current browser.
* * `navigator.platform` - OS platform.
* * `navigator.cookieEnabled` - Boolean, read-only.
* screen
* location - read the current URL and direct the browser to a new one
* * location.href - current url, can be set to redirect
* frames
* history
* alert/ confirm/ prompt - pure browser methods to communicate with the user
* `setTimeout`
* `XMLHttpRequest`

Preferred Languages (read-only).
```JavaScript
preferredLanguages = globalObj.navigator.languages; //["en-US", "zh-CN", "ja-JP"] 
let firstElemOfArray = navigator.language; //"en-US"
// changed with a langaugechange event fired upon window.
```
## DOM
The DOM describes the document structure, manipulations, and events.
https://dom.spec.whatwg.org

Every HTML tag is an object. Tags are element nodes (elements) and form the tree structure. Nested tags are children of their enclosing parent. Inner text is an object as well - text nodes which contain only a string and no children. There are also comment nodes, since everything in the HTML MUST be in the DOM tree. The document is also a node.

Tags have multiple properties. Some include:
* `style` - which has its own assortment of properties
* `innerHTML` - tags and text. Can modify, but can't pass a <script> tag that will execute. Can append with +="string".
* * If overwrite, all content is rewritten and reloaded and repainted and may lose any :selected status.
* `outerHTML` - `innerHTML` plus the element itself. Writing to thise replaces it in the DOM rather than rewriting it, so won't update any variables pointing to the old element, but will update the DOM.
* `offsetWidth` - node width in pixels.
* `childNodes` - returns a collection of all direct children, including inner text that separates them. This is read-only.
* * Iterate over using for..of. Note that array methods will not work.
* `firstChild`
* `lastChild`
* `hasChildNodes()` - compared to empty. 
* `parentNode` - of html tag (document.documentElement) is document
* `nodeName` - for elements, is the same as tagName, otherwise is the string with the node type. Always uppercased in HTML mode.
* `nodeType` - 1 for element nodes, 3 for text nodes, 9 for the document object
* `nodeContent`/ `data` - usually use data, can be modified.

Element nodes have:
* `children` - only children that are element nodes.
* `firstElementChild`
* `lastElementChild`
* `previousElementSibling`, `nextElementSibling` - neighbors.
* `parentElement` - of html tag (`document.documentElement`) is null, as it is the root node
* `contains(elemB) `- true if elemB is inside elem (decendent) or when elem == elemB.
* `tagName` - uppercased in HTML mode.
* `textContent` - only text, no <tags> - just extracts their inner text and returns them in one line. Can write to this and will display the literal string, not HTML.
* `hidden` - true if in CSS or style: `display: none`.
* `tabindex` - if assigned, deviates from the default focusable order.

With all attributes, even the non-standard ones that will not create corresponding properties:
* `elem.attributes` - a collection of objects that belong to a built-in Attr class, with name and value properties.
* `elem.hasAttribute(name)` – checks for existence.
* `elem.getAttribute(name)` – gets the value.
* `elem.setAttribute(name, value)` – sets the value.
* `elem.removeAttribute(name)` – removes the attribute.
* `elem.dataset.propertyName` - returns the value of data-property-name attribute.
Classes and styling:
* `elem.className` - assigning this replaces the whole string of classes
* `elem.classList` - special object, iterable with for..of
* * `elem.classList.add('class name')`
* * `elem.classList.remove('class name')`
* * `elem.classList.toggle('class name')` - add the class if it doesn't exist, otherwise remove it.
* * `elem.classList.contains('class name')` - returns true or false.
* `elem.style` - can't read anything that comes from CSS classes - only operates on the value of the "style" attribute. There is no CSS cascade.
* * Setting `elem.style.width="100px"` is the same as setting `elem.style="width:100px"` or `elem.setAttribute("style", "width:100px")`.
* * Multi-word properties convert to camelCase. z-index -> zIndex. The dash means uppercase with browser-prefixed properties as well: -moz-border-radius -> MozBorderRadius.
* * To remove some property of style, set the value to "".
* `elem.style.cssText` can be set with multi-property strings: \`color:red; background-color: yellow;`.
* * This will also replace (remove) existing styles.

``` HTML
<body>
  <div id="elem" about="Elephant"></div> 
  <!-- Non-standard attribute -->
  <script>
    alert( elem.getAttribute('About') ); // 'Elephant', reading
    // Case-insensitive
    elem.setAttribute('Test', 123); // writing value as "123"
    // any value that is assigned becomes a string
    alert( elem.outerHTML ); // see if the attribute is in HTML (it is)
    for (let attr of elem.attributes) { // list all
      alert( `${attr.name} = ${attr.value}` );
    }
  </script>
</body>
```
HTML attributes are case-insentive and their values are always strings.

Malformed HTML is automatically created during the making of the DOM. The top tag is alway HTML (DOM node is document.documentElement); spaces before head (node: and after /body (node: document.body, which can be null) are ignored, both tags which are also required; tags are closed; tables must have tbody; etc.

### CSS
Computed styles in CSS are the values after all CSS rules and inheritance are applied - the result of the CSS cascade, such as relative values like `height: 1em` or `font-size: 125%`.

Resolved stayles are the values finally applied to the element - the absolute fixed ones. Some values may have a floating point, like `height: 50.5px`.

`getComputedstyle(elem[, pseudo = ""])` - reads the resolved values in the CSS of elem, with any pseudo-elements like ::before added.

Example: Can read `getComputedStyle(document.body).marginTop` to get the resolved value of `margin-top`, usually in px. There is no standard rule for properties like `margin` or `padding`, as these are made up of four properties (`*Top`, `*Bottom`, `*Left`, `*Right`).

Styles applied to :visited (psuedoclass) links cannot be accessed from `getComputedStyle(link, ':visited')` because then any arbitrary page could find out whether the user vvisited a link by checking the styles. There is also no way to apply CSS geometry-changing styles in :visited as that too could break privacy.

### Nodes
Specific HTMLElements inherit from `HTMLElement`, which has siblings `SVGElement` and `XMLElement` which also inherits from `Element`. `Text`, `Element`, and `Comment` inherit from abstract class `Node`, which inherits from `EventTarget`, which allows them to support events.

Some DOM elements have additional properties. HTMLInputElement and HTMLSelectElement have value (<input>, <select>, <textarea>).
HTMLAnchorElement has href.

Most attributes and corresponding properties auto-update when the other changes. There are some exceptions: input.value synchronizes only from attribute -> property.
```HTML
<input>
<script>
  let input = document.querySelector('input');

  // attribute => property updates
  input.setAttribute('value', 'text');
  alert(input.value); // text

  // NOT property => attribute
  input.value = 'newValue';
  alert(input.getAttribute('value')); // text (not updated!)
</script>
```

Some properties are not strings. For checkboxes, input.checked is a boolean. The style property is an object. Some differ from the attribute:
```HTML
<a id="a" href="#hello">link</a>
<script>
  // attribute
  alert(a.getAttribute('href')); // #hello

  // property
  alert(a.href ); // full URL in the form http://site.com/page#hello
</script>
``` 

### Passing Custom Attributes
To avoid the potential of future standards using the same name as your custom attributes, use `data-*` as a prefix.
```HTML
<!-- mark the div to show "name" here -->
<div data-show-info="name"></div>
<!-- and age here -->
<div data-show-info="age"></div>

<style>
  /* styles rely on the custom attribute "order-state" */
  .order[data-order-state="new"] {
    color: green;
  }

  .order[data-order-state="pending"] {
    color: blue;
  }

  .order[data-order-state="canceled"] {
    color: red;
  }
</style>

<div class="order" data-order-state="new">
  A new order.
</div>
<div class="order" data-order-state="pending">
  A pending order.
</div>
<div class="order" data-order-state="canceled">
  A canceled order.
</div>

<script>
  // the code finds an element with the mark and shows what's requested
  let user = {
    name: "Pete",
    age: 25
  };

  for(let div of document.querySelectorAll('[data-show-info]')) {
    // insert the corresponding info into the field
    let field = div.getAttribute('data-show-info');
    div.innerHTML = user[field]; // first Pete into "name", then 25 into "age"
  }

    // read
  alert(order.dataset.orderState); // new

  // modify
    // a bit simpler than removing old/adding a new class
  div.setAttribute('data-order-state', 'canceled');
  // Same as
  order.dataset.orderState = "pending"; // (*)
</script>
```

#### Table Elements
Table elements have additional properties:
* `rows` - collection of <tr> elements.
* `caption`, `tHead`, `tFoot` - reference to elements <caption>, <thead>, and <tfoot>.
* `tBodies` - collection of <tbody> elements, always at least one.

<thead>, <tfoot>, and <tbody> also have .rows

Table row elements <tr> elements:
* `cells` - collection of <td> and <th> cells inside the given <tr>.
* `sectionRowIndex` -  the position (index) of the given <tr> inside the enclosing <thead>/<tbody>/<tfoot>.
* `rowIndex` - the number of the <tr> in the table as a whole (including all table rows).

<td> and <th> have `cellIndex` - number of the cell inside the enclosing <tr>.


## Usage
Can run JavaScript in the browser by inserting a <script src="path/to/script.js">Or write it here, inline;</script> tag. "path/to/script.js" is a relative path from the current folder (of the HTML page). "/path/to/script.js" is an absolute path from the site root. Your source can also be a full "https://url.domain.com/script.js". If the src is set, any content between the tags will be ignored.

Longer scripts are best stored in separate files (rather thn inline) because then the browser will download and store it in its cache for faster loading times and reduced traffic.

## Searching
Get by Id:
```HTML
<div id="elem"> 
<!-- ids must be unique -->
  <div id="elem-content">Element</div>
</div>

<script>
  // get the element - not necessary as elem is already a reference to the DOM-element with id="elem", though accessing through this way is not recommended
  let elem = document.getElementById('elem'); // only a function of document

  // make its background red
  elem.style.background = 'red';

  let elemContent = window['elem-content']; // ids name global variables that reference their elements. Accessing through this way is not the prefered method, .getElementById is.
</script>
```

Get all elements inside elem that match the given CSS selector:
```HTML
<ul>
  <li>The</li>
  <li>test</li>
</ul>
<ul>
  <li>has</li>
  <li>passed</li>
</ul>
<script>
  // Can check that css exists in the document
  if (elem.matches('ul > li:last-child')) {

    let elements = document.querySelectorAll('ul > li:last-child');

    for (let elem of elements) {
      alert(elem.innerHTML); // "test", "passed"
    }
    let firstElement = elem.querySelector('ul > li:last-child');

    // Get nearest ancestor elem that matches the CSS-selector - go up from the element and check each parent, and return the ancestor that matches
    alert(firstElement.closest('h1')); // null (because h1 is not an ancestor)
  }
</script>
```
CSS selectors work with pseudo-classes like :hover and :active as well.

There are other ways of getting elements:
* `elem.getElementsByTagName(tag)` - look for elements with the given tag and returns the collection of them. The tag parameter can also be a star "*" for “any tags”.
* * `elem.getElementByTagName(tag)` - returns just the first one
* `elem.getElementsByClassName(className`) - returns elements that have the given CSS class.
* `document.getElementsByName(name)` - returns elements with the given name attribute, document-wide. Very rarely used.
* `document.elementFromPoint(clientX, clientY)` - returns the most nested element on given window-relative coordinates, or null if coordinates are out of the window.

These returned collections are live, meaning they reflect the current state of the document and auto-update when it changes. `querySelector/All` and `getElementById` are not live.

### Methods for Dynamically Generating Web Pages

Creating new nodes:
```JavaScript
let div = document.createElement('div'); // create a new element with the tag
div.className = "alert";
div.innerHTML = "<strong>Hi there!</strong> You've read an important message.";
let textNode = document.createTextNode('Here I am'); // create new text node with give ntext
```
Inserting nodes and text with other nodes:
* `node.append(...nodes or strings)` – append nodes or strings at the end of node.
* `node.prepend(...nodes or strings)` – insert nodes or strings at the beginning of node.
* `node.before(...nodes or strings)` – insert nodes or strings before node.
* `node.after(...nodes or strings)` – insert nodes or strings after node - removes the node from the old place.
* `node.replaceWith(...nodes or strings)` – replaces node with the given nodes or strings.
* `node.remove()` - removes the node.
All text is inserted as text, not HTML.

There is also `elem.insertAdjacentText(where, text)` and `elem.insertAdjacentElement(where, elem)`, but these are rarely used.

You can insert strings that will get parsed as HTML using `elem.insertAdjacentHTML(where, html)` where "where" is a codeword:
* "beforebegin" – insert html immediately before elem,
* "afterbegin" – insert html into elem, at the beginning,
* "beforeend" – insert html into elem, at the end,
* "afterend" – insert html immediately after elem.
```HTML
<div id="div"></div>
<script>
  div.insertAdjacentHTML('beforebegin', '<p>Hello</p>');
  div.insertAdjacentHTML('afterend', '<p>Bye</p>');
</script>

<!-- Creates: -->
<p>Hello</p>
<div id="div"></div>
<p>Bye</p>
```

Nodes can be cloned.
* `elem.cloneNode(true)` - returns a “deep” clone of the element – with all attributes and sub-elements.
* `elem.cloneNode(false)` - returns a clone is without child elements.

`DocumentFragment` is a special DOM node that serves as a wrapper to pass around lists of nodes. Nodes can be appended to it, but when it is inserted, its content is inserted instead.
```HTML
<ul id="ul"></ul>

<script>
function getListContent() {
  let fragment = new DocumentFragment();

  for(let i=1; i<=3; i++) {
    let li = document.createElement('li');
    li.append(i);
    fragment.append(li);
  }

  return fragment;
}

ul.append(getListContent()); 

// Same as

function getListContent() {
  let result = [];

  for(let i=1; i<=3; i++) {
    let li = document.createElement('li');
    li.append(i);
    result.push(li);
  }

  return result;
}

ul.append(...getListContent()); // append + "..." operator = friends!

</script>


<!-- Returns: -->
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
</ul>
```

Ancient peoples once used (all of which return node):
* `parentElem.appendChild(node)` 
* `parentElem.insertBefore(node, nextSibling)` - which works even with parentElem.firstChild as the nextSibling
* `parentElem.replaceChild(node, oldChild)`
* `parentElem.removeChild(node)`
* `document.write('HTML string')` - only works while the page is loading, so if called during the parsing stage, the browser will consume it just as if it were initially there in the HTML text. If called afterwards, the existing document content is erased. Very speedy because no DOM modification involved (if called while browser is still reading the HTML).

The method comes from times when there was no DOM, no standards… Really old times. It still lives, because there are scripts using it.

## Geometry
### Element Geometry
Calculating the coordinates of an element. Let's look at some properties of elements.

The offset parent is the nearest ancestor that the browser uses for calculating coordinates during rendering. The parent can be:
* CSS-positioned (position is absolute, relative, fixed, or sticky)
* <td>, <th>, or <table>
* <body>
offsetParent is null for
* Not-shown elements (display:none or not in the document)
* For <body> and <html>
* For elements with position: fixed
`offsetLeft`/ `offsetTop` are relative to the upper-left corner.

`offsetWidth`/ `offsetHeight` provide the outer width/ height of the element (full size including borders, padding, and width). These are zero/ null for elements that are not displayed.

Within the element are borders. `clientLeft` and `clientTop` are the relative coordinates of the inner side from the outer side - they are generally the width and height of the border, except in languages that are read from right to left. In such languages, scroll bars are on the left and are factored into the `clientLeft` coordinates.

`clientWidth` and `clientHeight` include padding and content width and height.

`scrollWidth` and `scrollHeight` are like `clientWidth` and `clientHeight` but include the scrolled out (hidden) parts. They can be used to expand elements to their full heights

`scrollLeft` and `scrollTop` are the width and height of the hidden, scrolled out part of the element to the left and top (how much is scrolled up or left). This can be used to scroll elements - setting `scrollTop` to 0 scrolls all the way up and `scrollTop` to Infinity all the way down.

Beware when using `getComputedStyle(elem).width` or `.height`, as these depend on box-sizing - how CSS interprets these values. They also may be "auto", which getComputedStyle will return - which is less than helpful. Some browsers will return width - the scrollbar width while others will ignore the scrollbar.

### Window Geometry
The `clientWidth` and `clientHeight` of `document.documentElement` (html tag) is the width and height of the window that is available for content (minus the scrollbar - `window.innerWidth` and `window.innerHeight` will include the scrollbar).

To get the full size with cross-browser compatibility:
```JavaScript
let scrollHeight = Math.max(
  document.body.scrollHeight, document.documentElement.scrollHeight,
  document.body.offsetHeight, document.documentElement.offsetHeight,
  document.body.clientHeight, document.documentElement.clientHeight
);

// Read-only properties on how much is scrolled:
alert('Current scroll from the top: ' + window.pageYOffset);
alert('Current scroll from the left: ' + window.pageXOffset);
```

To scroll the page from JavaScript, the DOM must be fully built. To scroll the page use `scrollTo(pageX, pageY)` for absolute coordinates of the top left corner. Scrolling to the beginning is `window.scrollTo(0,0)`.

To scroll an element into view: `elem.scrollIntoView(top = true)` scrolls to make the elem visible. If top is true, as it is by default, the page will be scrolled to make elem appear on the top of the window - the upper edge will be aligned with the window top. If top is false, the page scrolls to make elem appear at the bottom - the bottom edge will be aligned with the window bottom.

To make the document unscrollable, as when displaying modals that require immediate attention:
```JavaScript
document.body.style.overflow = "hidden"; // forbid scrolling
document.body.style.overflow = ""; // allow scrolling
```
This causes the scrollbar to disappear and content to jump to fill the space it once occupied. You can add padding into the document.body in place of the scrollbar to maintain the content width.

### Coordinates
Two coordinate systems:
* Relative to the window - as with position:fixed, calculates from the window's top/ left edge. Denoted as `clientX` and `clientY`.
* Relative to the document - as with position:absolute, calculates from the document's top/ left edge, including width that has been scrolled past. Denoted as `pageX` and `pageY`.

The method `elem.getBoundingClientRect()` returns window coordinates for a minimal rectangle that encloses elem as an object of built-in DOMRect class. Main DOMRect properties:
* `x` / `y` - coordinates of the rectangle origin relative to window, can be negative if the element is scrolled past and is now above or to the left of the window. Unsupported by IE and Edge, which do support top/ left (which is the same as x/ y if the width/ height are positive).
* `width` / `height` - of the rectangle, can be negative if rectangle is flipped. 
Derived properties:
* `top` / `bottom` - Y-coordinate/ distance from the top rectangle edge, change as scroll. `bottom = y + height`, where `y = top`. Note that bottom is not necessarily the same as with CSS positioning, which would calculate bottom from the distance to the bottom edge.
* `left` / `right` - X-coordinate/ distance from the left rectangle edge. `right = x + width`, where `x = left`.

To get the most nested element at window coordinates (x, y): `document.elementFromPoint(x, y)`, where x and y are in the visible area. If the coordinates are negative or exceed the window width/ height, then returns null.
```JavaScript
// Get tag in the middle of the window:
let centerX = document.documentElement.clientWidth / 2;
let centerY = document.documentElement.clientHeight / 2;

let elem = document.elementFromPoint(centerX, centerY);
```

To get the document coordinates of an element rather than the window (so the element will scroll with the document): `elem.getBoundingClientRect()` plus the current page scroll:
```JavaScript
function getCoords(elem) {
  let box = elem.getBoundingClientRect();

  return {
    top: box.top + window.pageYOffset,
    left: box.left + window.pageXOffset
  };
}

 let message = document.createElement('div');
  message.style.cssText = "position:absolute; color: red"; // stay with the element on scroll

  let coords = getCoords(elem);

  message.style.left = coords.left + "px";
  message.style.top = coords.bottom + "px";

  message.innerHTML = html;
```

## Window Object Hierarchy
## Inline-Frames (IFrames)
## Navigation
Forward and backward paging

## Events
Signal that something has happened. To react to events, assign a handler - a function that runs in the case of the event.

Adding handlers rather than overwriting a previous one is done with eventListeners.
```JavaScript
/*
 * @param event: event name, like "click"
 * @param handler: handler function(event) with optional event parameter being passed
 * @param options: object with properties:
 *   once: if true, listener is automatically removed after it triggers
 *   capture: where to handle the event. If the entire options argument is false/true, that's the same as {capture: false/true}
 *   passive: if true, then the handler will not preventDefault()
 */
element.addEventListener(event, handler, [options]);
element.removeEventListener(event, handler, [options]);
// need to pass the same function that was used in the assignment - so passing an arrow function will not remove it as two arrow functions don't reference the same function. You need to store the function in a variable in order to remove them later
```

Document events:
* `DOMContentLoaded` – when the HTML is loaded and processed, DOM is fully built. Cannot set with a property - can only set with addEventListener("DOMContentLoaded", function(event)).

CSS events:
* `transitionend` – when a CSS-animation finishes. Cannot set with a property - can only set with addEventListener("transitionend", function(event)).

Handlers assigned to elements also run if any nested tags experience that event because events bubble. When an event happens on an element, it first runs the handlers on it, then on its parent, then all the way up on other ancestors.

Event properties:
* `type` - Such as "click".
* `target` - element that initiated the event, doesn't change through the bubbling process.
* `currentTarget` - Element that handled the event. Same as "this" unless this is bound to something else or the handler is an arrow function. May change with bubbling.
* `stopPropagation()` - stops bubbling up for this event. Generally, you don't want to stopPropagation.
* `stopImmediatePropagation()` - no handler executes on the current element.
* `clientX` / `clientY` - Window-relative coordinates of the cursor for mouse events.

Some events, like focus events, don't bubble up.

There are three phases of event propagation:
* Capturing - the event goes down to the element through its ancestors.
* * Normally invisible, unless options in addEventListener is set to true or capture is set to true - then the event is caught in this early phase.
* Target - element reaches the target element.
* Bubbling - event bubbles up fro mthe element.

Bubbling and capturing allow us to handle elements in a single common ancestor. Be sure to bind `elem.onclick = this.onClick.bind(this)` in the class you want it to be bound to, else elem will be this.

### Default Actions
Some events are performed automatically. Clicking a link navigates to its URL. Clicking the submit button submits it to the server. Clicking and dragging with the mouse button over text selects that text.

To prevent the browser from performing the default action, there's `event.preventDefault()` or, if the event is assigned using on* (like elem.onclick = fucntion() {return false;}) and not `addEventListener`, return false (this is the only case where the return value from an event handler is ignored).
```JavaScript
<a href="/" onclick="return false">Click here</a>
or
<a href="/" onclick="event.preventDefault()">here</a>

element.addEventListener(event, handler, {passive: true});
```

Some events follow each other, like focus events after mousedowns. These will be prevented if the default is prevented. `event.defaultPrevented` will let other handlers know that the event was handled and not propagated further.
```JavaScript
elem.oncontextmenu = function(event) {
    event.preventDefault();
    event.stopPropagation();
    alert("Button context menu");
  };
// better than stopping propagation:
 elem.oncontextmenu = function(event) {
    event.preventDefault();
    alert("Button context menu");
  };

  document.oncontextmenu = function(event) {
    if (event.defaultPrevented) return; // check if already handled

    event.preventDefault();
    alert("Document context menu");
  };
```
### Custom Events

`elem.on*` events only work for built-in events.

The property `event.isTrusted` is true for events that come from real user actions and false for script-generated events.
```HTML
<button id="elem" onclick="alert('Click!');">Autoclick</button>

<script>
/* 
 * @param type: event type, a string like "click" or our own like "my-event"
 * options: the object with two optional properties, by default both are false:
 *   bubbles: true/false – if true, then the event bubbles.
 *   cancelable: true/false – if true, then the “default action” may be prevented.
 */

let event = new Event(type[, options]);

  let event = new Event("click");
  elem.dispatchEvent(event);

  let event = new MouseEvent("click", {
  bubbles: true,
  cancelable: true,
  clientX: 100,
  clientY: 100
});
</script>
```

It is best to specify which type of event you're creating.
* `UIEvent`
* `FocusEvent`
* `MouseEvent` - includes `clientX`, `clientY` properties in options.
* `WheelEvent`
* `KeyboardEvent`
* `CustomEvent` - in the options argument, can add an additional property detail for any custom information that can then be accessed by handlers as `event.detail`.
```JavaScript

  // additional details come with the event to the handler
  elem.addEventListener("hello", function(event) {
    alert(event.detail.name);
  });

  elem.dispatchEvent(new CustomEvent("hello", {
    detail: { name: "John" }
  }));

    function hide() {
    let event = new CustomEvent("hide", {
      cancelable: true // without that flag preventDefault doesn't work
    });
    if (!elem.dispatchEvent(event)) {
      alert('The action was prevented by a handler');
    } else {
      rabbit.hidden = true;
    }
  }

  elem.addEventListener('hide', function(event) {
    if (confirm("Call preventDefault?")) {
      event.preventDefault();
    }

```

Usually events are processed asynchronously - if while the browser is processing onclick a new event occurs, it waits until the `onclick` processing is finished. This is true except when an event initiates another event - control will jump to the nested event handler and then come back.
### cut, copy, and paste
`ClipboardEvents` provide access to data that is copied/ pasted. event.`preventDefault()` prevents the action (copy or paste).

Prevent all clipboard interaction:
```JavaScript
<input type="text" id="input">
<script>
  input.oncut = input.oncopy = input.onpaste = function(event) {
    alert(event.type + ' - ' + event.clipboardData.getData('text/plain'));
    return false;
  };
</script>
```

The clipboard is global OS-level. Most browsers allow read/ write access to the clipboard only in the scope of certain user actions, like `onclick` event handlers. Most browsers forbid the generation of custom clipboard events with `dispatchEvent`.

### onclick
Handler for a click event. Can only have one handler for a click event at a time.
```HTML
<input value="Click me" onclick="countRabbits() // need () here" type="button">
<!-- Assign in JavaScript: -->
<script>
  elem.onclick = countRabbits; // no () here, else would be taking the result of the function running
  // also note the case-sensitive onclick
</script>
<!-- Sending parameters -->
<button onclick="alert(this.innerHTML)">Click me</button> 
<!-- Value of this is the element -->
```
### onchange
The change event triggers when the element has finished changing. For text inputs, `onchange` fires when it loses focus. For select and checkbox and radio inputs, `onchange` fires right after the selection changes.
### onLoad
The DOM lifecycle goes through three important events:
* `DOMContentLoaded` - Browser is fully loaded HTML and the DOM tree is built, but external resources like <img> and stylesheets may not yet be loaded.
* * DOM is ready, so can lookup DOM nodes.
* `load` - External resources are also loaded.
* * Styles are applied, image sizes are known.
* `beforeunload`/ `unload` - User is leaving the page.
* * `beforeunload` - Check if user saved changes, ask if they really want to leave
* * `unload` - User has almost left, but we can still initiate some operations, like sending out stats.

`document.readyState` tells the current loading state:
* loading
* interactive - document was fully read
* complete - fully read and all resources (images, stylesheets, etc.) are loaded too

```JavaScript
document.addEventListener("DOMContentLoaded", ready);
// not "document.onDOMContentLoaded = ..."

if (document.readyState == 'loading') {
  // loading yet, wait for the event
  document.addEventListener('DOMContentLoaded', work);
} else {
  // DOM is ready!
  work();
}

// print state changes, rarely used:
document.addEventListener('readystatechange', () => console.log(document.readyState));
```
When processing an HTML document and finding a <script>
 tag, the browser pauses and executes the script before it finishes building the DOM. This is true unless scripts have async attribute or are genearted dynamically with `document.createElement('script')` and then added to the webpage.

Scripts after <link type="text/css" rel="stylesheet" href="style.css"> will wait to execute for the stylesheet to load, meaning `DOMContentLoaded` will wait on the stylesheet if its waiting on the script.

Once the page fully loaded, on `DOMContentLoaded`, some browsers may try to autofill forms.

```JavaScript
window.onload = function() {}; // same as window.addEventListener('load', (event) => {});

window.onbeforeunload = function() { // cancelling the event asks the user for additional confirmation before they navigate away
  return false; // also counts as false if return a non-empty string
};
```

On `unload`, can do things that don't involve delays, like closing related popup windows. To send data to a server, use `sendBeacon` to send data in the background so the transition to another page is not delayed - the browser leaves the page, but it still performs `sendBeacon`.
```JavaScript
let analyticsData = { /* object with gathered data */ }; // usually stringified

window.addEventListener("unload", function() {
  navigator.sendBeacon("/analytics", JSON.stringify(analyticsData));
}; // sent as a POST
```

To load a script in the background - rather than having the HTML pause - use the `defer` attribute. These will never block the page and always execute when the DOM is ready, but before `DOMContentLoaded` event. `defer` is ignored if the script has no `src` - it doesn't work for inline scripts. `defer` is used for scripts that need the whole DOM or whose relative execution order is important.
```HTML
<script defer src="https://javascript.info/article/script-async-defer/long.js?speed=1"></script>
```
Browsers scan the page for scripts and download them in parallel. But scripts will still execute in document order.

The HTML attribute `async` means that it's completely independent - `DOMContentLoaded` won't wait for it. They can execute in any order, depending on what loads first. This works great with ads.

Appending a script to the document body - adding a script dynamically - starts it loading right away and behaving asynchronously. Nothing waits for them, and they wait for nothing. The scripts run in load-first order. To avoid this, set the attribute to false.
```JavaScript
  let script = document.createElement('script');
  script.src = src;
  script.async = false;
  document.body.append(script);

// Tracking the loading of the script
script.onload = function() {

};
script.onerror = function() {
  alert("Error loading " + this.src);
};
```

Basically any resource that has an external source has an `.onload` and `.onerror` function that can be set. This works for <img> and <iframe>. Images start loading when they get a source and iframes trigger `onload` when the loading is finished, both for success and in case of error.

#### Cross-Origin Policy (CORS)
Scripts from one site can't access contents of the other site. One origin (domain/ port/ protocol triplet) can't access content from another (including subdomains and other ports). Information about the internals of a script with an outside source, including stack traces after an error, are hidden.

For the page:
`http://store.company.com/dir/page.html`
* `http://store.company.com/dir2/other.html` - same origin, different path
* `http://store.company.com/dir/inner/another.html` - same origin, different path
* `https://store.company.com/page.html` - different origin - different protocol
* `http://store.company.com:81/dir/page.html` - different origin - different port (http:// is 80 by default)
* `http://news.company.com/dir/page.html` - different origin - different host


To allow cross-origin access, the script tag needs to have the `crossorigin` attribute, plus the remote server must provide special headers.
* No `crossorigin` attribute – access prohibited.
* `crossorigin="anonymous"` – access allowed if the server responds with the header `Access-Control-Allow-Origin` with `*` or our origin. Browser does not send authorization information and cookies to remote server.
* `crossorigin="use-credentials"` – access allowed if the server sends back the header `Access-Control-Allow-Origin` with our origin and `Access-Control-Allow-Credentials: true`. Browser sends authorization information and cookies to remote server.
```HTML
<script crossorigin="anonymous" src="https://cors.javascript.info/article/onload-onerror/crossorigin/error.js"></script>
<!-- If the server provides an Access-Control-Allow-Origin header, we can receive the full error report, if it errors out. -->
```
#### JSONP
Before Cross-Origin Resouorce Sharing standards, JSON with padding was the only way to get a JOSN response from a server of a different origin by using hte <script> element.

Requests can be sent, but not read from websites with different origins.

The <script> element can load and excute JavaScript from a source of foreign origin. It automatically parses and executes returned code as JavaScript. By passing a callback into the query, a function will be called when the response is parsed and excuted in the browser's context.
```JavaScript
https://www.server.com/api/resource/1?callback=callbackName

callbackName({
  "server": "response"
}); // executed as if window were calling it.
```
#### Cross-Side Scripting Attacks (XSS)
Injecting malicious scripts client-side. Can access cookies, session tokens, and any other sensitive information because it appears to be from a trusted source.
##### DOM-Based XSS
Entire tainted data flow from source to sink takes place in the browser - the data never leaves, all sources and sinks are in the DOM. Could be the URL or an element and the sink is a sensitive method that causes the execution of the malicious script. vThe DOM environment is modified so the code vfor the page itself executes differently. The attack payload is placed in the response page, due to a server-side flaw.

##### Stored XSS/ Persistent
User input permanently stored on the target server - database, message forum, visitor log, comment field. Victim retrieves the stored data when it requests the stored information.

##### Reflected XSS/ Non-Persistent
Injected script is reflected off the web server - error message, search result, any response that includes some of the input sent to server as part of the request. Reflected attacks are delivered to victims via another route, like an email message or on some other website. The user is tricked into clicking a malicious link, submitting a form, or browsing to the malicious site, and the injected code travels to the vulnerable website which the browser then executes because it comes from a seemingly trusted source.
### Mouse Events
Can use with` on*="function()"` in HTML tags.
* `click` – when the mouse clicks on an element (touchscreen devices generate it on a tap) (mousedown and then mouseup over the same element as done with the left mouse button).
* `contextmenu` – when the mouse right-clicks on an element.
* `dblclick` - when the mouse is clicked twice. May select text
* `mousemove` - checked from time to time, so fast movement will cause some elements to be skipped.
* `mouseover` / `mouseout` – when the mouse cursor comes over / leaves an element. The mouse can only be over a single element at a time, so the most nested one and top by z-index, but will bubble up to the parent, which may be behind a child. 
* * `event.target` - element where mouse came over in mouseover or the element the mouse left in mouseout.
* * `event.relatedTarget` - element from which the mouse came in mouseover, new under-the-pointer element with `mouseout`. Can be `null` if came from out of the window or left the window. Useful when we want to avoid a parent's `mouseout` event when going from parent to child.
* `mouseenter` / `mouseleave` - transitions inside the element, like to or from decendants, are not counted; events don't bubble, so they cannot be delegated to be handled by an ancestor.
* `mousedown` / `mouseup` – when the mouse button is pressed / released over an element.
* * `event.which` determines with button on the mouse was pressed. The left button is 1, middle is 2, and right is 3.
* `mousemove` – when the mouse is moved.

Mouse events have coordinates of the mouse at the time of the event:
* Window-relative: `clientX` and `clientY`, as measured from the upper-left corner.
* Document-relative: `pageX` and `pageY`.

All mouse events also include information about pressed modifier keys, which are true if the corresponding key was pressed during the event:
* `shiftKey`: Shift
* `altKey`: Alt (or Opt for Mac)
* `ctrlKey`: Ctrl
* `metaKey`: Cmd for Mac
Macs generally use Cmd where Windows and Linux users would use Ctrl. Left click with Ctrl is interpreted as a right-click on Macs automatically.
```JavaScript
 button.onclick = function(event) {
    if (event.altKey && event.shiftKey) {
      alert('Hooray!');
    }
  };
```
Disabling copying, as triggered with ctrl+c.
```HTML
<div oncopy="alert('Copying forbidden!');return false">
  Dear user,
  The copying is forbidden for you.
  If you know JS or HTML, then you can get everything from the page source though.
</div>
```

Delegating events in a parent table with the power of bubbling:
```JavaScript
// <td> under the mouse right now (if any)
let currentElem = null;

table.onmouseover = function(event) {
  // before entering a new element, the mouse always leaves the previous one
  // if currentElem is set, we didn't leave the previous <td>,
  // that's a mouseover inside it, ignore the event
  if (currentElem) return;

  let target = event.target.closest('td');

  // we moved not into a <td> - ignore
  if (!target) return;

  // moved into <td>, but outside of our table (possible in case of nested tables)
  // ignore
  if (!table.contains(target)) return;

  // hooray! we entered a new <td>
  currentElem = target;
  onEnter(currentElem);
};


table.onmouseout = function(event) {
  // if we're outside of any <td> now, then ignore the event
  // that's probably a move inside the table, but out of <td>,
  // e.g. from <tr> to another <tr>
  if (!currentElem) return;

  // we're leaving the element – where to? Maybe to a descendant?
  let relatedTarget = event.relatedTarget;

  while (relatedTarget) {
    // go up the parent chain and check – if we're still inside currentElem
    // then that's an internal transition – ignore it
    if (relatedTarget == currentElem) return;

    relatedTarget = relatedTarget.parentNode;
  }

  // we left the <td>. really.
  onLeave(currentElem);
  currentElem = null;
};

// any functions to handle entering/leaving an element
function onEnter(elem) {
  elem.style.background = 'pink';

  // show that in textarea
  text.value += `over -> ${currentElem.tagName}.${currentElem.className}\n`;
  text.scrollTop = 1e6;
}

function onLeave(elem) {
  elem.style.background = '';

  // show that in textarea
  text.value += `out <- ${elem.tagName}.${elem.className}\n`;
  text.scrollTop = 1e6;
}
```

#### Drag and Drop
In the spec: https://html.spec.whatwg.org/multipage/dnd.html#dnd

To get a greater degree of control, can implement with mouse events:
```JavaScript
// Draggable
ball.onmousedown = function(event) {

  // shift position so can pick up the target on one of its corners and it won't jump to, say, centered on the mouse
  let shiftX = event.clientX - ball.getBoundingClientRect().left;
  let shiftY = event.clientY - ball.getBoundingClientRect().top;

  ball.style.position = 'absolute';
  ball.style.zIndex = 1000;
  document.body.append(ball);

  moveAt(event.pageX, event.pageY);

  // moves the ball at (pageX, pageY) coordinates
  // taking initial shifts into account
  function moveAt(pageX, pageY) {
    ball.style.left = pageX - shiftX + 'px';
    ball.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  // move the ball on mousemove
  document.addEventListener('mousemove', onMouseMove);

  // drop the ball, remove unneeded handlers
  ball.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    ball.onmouseup = null;
  };
};

ball.ondragstart = function() { // disable default behavior for dragging images
  return false;
};

// Droppable Target
let currentDroppable = null; // potential droppable that we're flying over right now

function onMouseMove(event) {
  moveAt(event.pageX, event.pageY);

  // Make the ball hidden for an instant to check if over the droppable target
  ball.hidden = true;
  let elemBelow = document.elementFromPoint(event.clientX, event.clientY);
  ball.hidden = false;

  // mousemove events may trigger out of the window (when the ball is dragged off-screen)
  // if clientX/clientY are out of the window, then elementFromPoint returns null
  if (!elemBelow) return;

  // potential droppables are labeled with the class "droppable" (can be other logic)
  let droppableBelow = elemBelow.closest('.droppable');

  if (currentDroppable != droppableBelow) {
    // we're flying in or out...
    // note: both values can be null
    //   currentDroppable=null if we were not over a droppable before this event (e.g over an empty space)
    //   droppableBelow=null if we're not over a droppable now, during this event

    if (currentDroppable) {
      // the logic to process "flying out" of the droppable (remove highlight)
      leaveDroppable(currentDroppable);
    }
    currentDroppable = droppableBelow;
    if (currentDroppable) {
      // the logic to process "flying in" of the droppable
      enterDroppable(currentDroppable);
    }
  }
}
```
Taken from: https://javascript.info/mouse-drag-and-drop

### Keyboard Events
Keyboard events:
* `keydown` and `keyup` – when the visitor presses and then releases the button.
* older days - `keypress`
If a key is pressed for a long enough time, it starts to auto-repeat, triggering the keydown event again and again until it's finally released with keyUp.

Properties
* `event.repeat` - if true, can trigger events based on auto-repeat (when held down).
* `event.code` - physical key code, like the location on the keyboard (so unaffected by language changes, even if the keyboard is laid out differently due to the language), like "KeyZ", "Digit0", "Enter", and "Tab". `ShiftRight` and `ShiftLeft` are different.
* `event.key` - get the character, which will be different if it is pressed with or without Shift or depending on the language. All Shifts are the same, something like F1 has the same value as `event.code`.
Olden days, now deprecated due to so many browser incompatibilities.
* `keyCode`
* `charCode`
* `which`

OS-based special keys cannot be canceled, like Alt+F4 in Windows closing the current browser window.

The `Fn` key has no associated keyboard event because often implemented on a lower level the the OS.

### Scrolling
Event
* `scroll`
```JavaScript
window.addEventListener('scroll', function() {
  document.getElementById('showScroll').innerHTML = window.pageYOffset + 'px';
});
```
`onscroll` triggers after the scroll has already happened, so event.`preventDefault()` won't prevent scrolling. It's more reliable to use the CSS overflow property to disable scrollbars.

Infinite scroll:
```JavaScript
function populate() {
  while(true) {
    // document bottom
    let windowRelativeBottom = document.documentElement.getBoundingClientRect().bottom;

    // if the user scrolled far enough (<100px to the end)
    if (windowRelativeBottom < document.documentElement.clientHeight + 100) {
      // let's add more data
      document.body.insertAdjacentHTML("beforeend", `<p>Date: ${new Date()}</p>`);
    }
  }
}
```

## Forms
### Input Objects
### Validation

## Forms
`document.forms` stores a collection of all the forms in the document - accessible by index `document.forms[number]` or `document.forms.name`, set in <form name="name">. 

All control elements of some form formy are accessible through formy.elements. For elements with the same name, like radio buttons have to have, `form.elements.radioButtonName` is a collection. Fieldsets also have an elements collection. `formy.elements.name = formy.name`. All elements reference the form they're part of through `element.form`.

input and textarea
* `.value` - string, note is not innerHTML.
* `input.checked` - boolean for textboxes.

### select and option

select elements have:
* `select.options` - collection of <option> subelements.
* `select.value` - value of currently selected <option>.
* `select.selectedIndex` - number of the currently selected <option>.
Setting values:
```JavaScript
select.options[2].selected = true;
select.selectedIndex = 2;
select.value = 'banana';
```
If the select tag has the multiple attribute, can select multiple options at once.
```HTML
<select id="select" multiple>
  <option value="blues" selected>Blues</option>
  <option value="rock" selected>Rock</option>
  <option value="classic">Classic</option>
</select>

<script>
  // get all selected values from multi-select
  let selected = Array.from(select.options)
    .filter(option => option.selected)
    .map(option => option.value);

  alert(selected); // blues,rock
</script>
```
Options have
* `selected`
* `index`
* `text` - content seen by the visitor.

Option elements are easy to create outside of HTML:
```JavaScript
option = new Option(text, value, defaultSelected, selected);
```
Where `defaultSelected` sets the HTML attribute (`option.getAttribute('selected')`) and `selected` is whether the option is selected.

### Events
The input event triggers every time after a value is modified by the user - pasting or speech recognition trigger, as do keyboard actions. Pressing arrow keys and other actions that don't involve value changes doesn't trigger oninput to fire.

### Form element events
* `submit` – when the visitor submits a <form>.
* * click <input type="submit"> or <input type="image"> generates an event.
* * press Enter on an input field generates an event. Also triggers a click event.
* * `form.submit()`
* `focus` – when the visitor focuses on an element, either by clicking or using Tab to navigate into it. The autofocus HTML attribute puts the focus into an element by default when the page loads.
* `blur` - when the element loses focus.
<button>s, <input>s, <select>s, <a>/ links are guaranteed to be able to be interacted with. Formatting elements like <div>, <span>, and <table> are unfocusable by default. To cheange this, set a `tabindex`.

* `focusin` / `focusout` - bubbling focus and blur. Must be assigned with `elem.addEventListener("focusin", function())` rather than `onfocusin`.

#### Submission
Handler checks data, shows any errors and calls `event.preventDefault()` so error-ridden data isn't sent to the server. If there are no errors, can submit the form.

#### Focus, Blur, and TabIndex
The HTML attribute `tabindex` is the order of the element when Tab is used to switch between elements. Elements with `tabindex == 1` go first, then the ones above, then elements that are focusable but don't have tabindexes. `tabindex="0"` puts an element among those without tabindexes, so they are still focusable but only after the greater tab indexes. It maintains the default switching order. `tabindex="-1"` allows for programmic focusing - the Tab key will ignore these elements, but `elem.focus()` still works.

`focus` and `blur` do not bubble. Some frameworks have them bubble, however. Due to historical reasons, `focus` and `blur` propagate down during the capturing phase, and the `focusin` and `focusout` events bubble.

The currently focused element is `document.activeElement`.

## Web Workers
Allow for parallel execution inside the browser. They can't access the DOM (so no `document` or `window`). Instead they use a `WorkerGlobalScope` object. 

They must be loaded from the same origin (domain, port, and protocol). They won't work if you serve the page using the file protocol (`file://`). 

They communicate with the main JavaScript program using messaging. - the postMessage API on the Web Worker object or the Channel Messaging API.

If Web Workers don't stay in listening mode through `worker.onmessage` or an event listener, they'll be shut down as soon as their code is run through completion. You can use `workerInstance.terminate()` from the main thread or the globally-scoped `close()` inside of the worker to end it.

Libraries can be loaded with `importScripts('../utils/file.js', './something.js')`. Numerous APIs can be reached, just not the DOM.
https://flaviocopes.com/web-workers/

### postMessage:
```JavaScript
if (typeof Worker !== 'undefined') { // check for support
  const worker = new Worker('worker.js');
  worker.postMessage('hello'); // send message - transfers it rather than shares it

  worker.onmessage = event => {
    console.log(event.data);
  } // do something with the message that gets sent back
}
// or
worker.addEventListener('message', event => {
  console.log(event.data)
}, false)

// worker.js
onmessage = event => {
  console.log(event.data);
  postMessage('hey'); // send back a message to the function that created it
}

onerror = event => {
  console.error(event.message);
}

// to setup multiple listeners for the message event, don't use onmessage or onerror but set listeners for whichever type of event you're targetting
addEventListener('message', event => {
  console.log(event.data);
  postMessage('hey');
}, false);

addEventListener('message', event => {
  console.log(`I'm curious and I'm listening too`);
}, false);

addEventListener('error', event => {
  console.log(event.message);
}, false);

```

### Channel Messaging API
Use Web Workers to send messages by posting it to messageChannel.port#.
```JavaScript
const worker = new Worker('worker.js')
const messageChannel = new MessageChannel()
messageChannel.port1.addEventListener('message', event => {
  console.log(event.data)
})
worker.postMessage(data, [messageChannel.port2])

// worker.js
addEventListener('message', event => {
  event.ports[0].postMessage(data)
})

```

# AJAX
Asynchronous JavaScript and XML for network requests to get information from the server.

let `promise = fetch(url[, options])` where options are parameters like method, headers, etc. Without options, a `GET` request is sent.
```JavaScript
let promise = fetch(url, {
  method: "GET", // POST, PUT, DELETE, etc.
  headers: {
    // the content type header value is usually auto-set
    // depending on the request body
    "Content-Type": "text/plain;charset=UTF-8"
  },
  body: undefined // string, FormData, Blob, BufferSource, or URLSearchParams
  referrer: "about:client", // or "" to send no Referer header,
  // or an url from within the current origin
  referrerPolicy: "no-referrer-when-downgrade", // no-referrer, origin, same-origin...
  mode: "cors", // same-origin, no-cors - only simple cross-origin requests are allowed
  credentials: "same-origin", // - don't send for cross-origin requests, omit, include
  cache: "default", // no-store, reload - populate cache with response if allowed, no-cache, force-cache - even if it's stale or make a regular HTTP-request, or only-if-cached - even if it's stale, or error
  redirect: "follow", // manual, error
  integrity: "", // a checksum hash, like "sha256-abcdef1234567890"
  keepalive: false, // true - perform the request in the background even after leaves the page. Body limit is 64kb
  signal: undefined, // AbortController to abort request
  window: window // null
});
```

The promise resolves with an object of the Response class as soon as the server responds with headers - when it might not have the body yet. The promise rejects if the fetch was unable to make an HTTP-request (network problems) or there is no such site.

Response properties:
* `status` - HTTP status code.
* `ok` - boolean, true if status code is 200-299.
* `headers` - map-like object with HTTP headers

To get the response body, we need to make an additional method call:
* `response.text()` – read the response and return as text.
* `response.json()` – parse the response as JSON.
* `response.formData()` – return the response as FormData object.
* `response.blob()` – return the response as Blob (binary data with type).
* `response.arrayBuffer()` – return the response as ArrayBuffer (low-level representaion of binary data).

`response.body` is a `ReadableStream` object, so the body can be read chunk-by-chunk.

Only one body-reading method can be used. If we’ve already got the response with `response.text()`, then `response.json()` won’t work, as the body content has already been processed.
```JavaScript
let url = 'https://api.github.com/repos/javascript-tutorial/en.javascript.info/commits';
let response = await fetch(url, options); // resolves with response headers

let result = await response.json(); // read response body and parse as JSON

alert(commits[0].author.login);

// Same as with promises:
fetch('https://api.github.com/repos/javascript-tutorial/en.javascript.info/commits', options)
  .then(response => response.json())
  .then(result => alert(result[0].author.login));
```

#### Response Headers
Response-like headers are available in Map-like headers object in `response.headers`.
```JavaScript
let response = await fetch('https://api.github.com/repos/javascript-tutorial/en.javascript.info/commits');

// get one header
alert(response.headers.get('Content-Type')); // application/json; charset=utf-8

// iterate over all headers
for (let [key, value] of response.headers) {
  alert(`${key} = ${value}`);
}

// Setting headers
let response = fetch(protectedUrl, {
  headers: {
    Authentication: 'secret'
  }
});
```
Some HTTP headers cannot be set. They're exclusively controlled by the browser.

#### POST Requests
`POST` requests are requests with another methods.

fetch options
* `method` – HTTP-method, e.g. POST.
* `headers` - object with request headers.
* `body` – the request data to send, one of:
* * a string (e.g. JSON-encoded), most often used.
* * `FormData` object, to submit the data as form/multipart.
* * `Blob` / `BufferSource` to send binary data.
* * `URLSearchParams`, to submit the data in x-www-form-urlencoded encoding, rarely used.
```JavaScript
let user = {
  name: 'John',
  surname: 'Smith'
};

let response = await fetch('/article/fetch/post/user', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  },
  body: JSON.stringify(user)
});

let result = await response.json();
alert(result.message);
```
If the request body is a string, `Content-Type` header is set to `text/plain;charset=UTF-8` by default. Sending JSON-encoded data requires this to be reset.

Binary data like images can be sent using `Blob` or `BufferSource` objects.

Black/ white drawing with mouseover:
```HTML
<body style="margin:0">
  <canvas id="canvasElem" width="100" height="80" style="border:1px solid"></canvas>

  <input type="button" value="Submit" onclick="submit()">

  <script>
    canvasElem.onmousemove = function(e) {
      let ctx = canvasElem.getContext('2d');
      ctx.lineTo(e.clientX, e.clientY);
      ctx.stroke();
    };

    async function submit() {
      let blob = await new Promise(resolve => canvasElem.toBlob(resolve, 'image/png'));
      let response = await fetch('/article/fetch/post/image', {
        method: 'POST',
        body: blob
      });

      // the server responds with confirmation and the image size
      let result = await response.json();
      alert(result.message);
    }

    // Same as  with pure promises:
    function submit() {
  canvasElem.toBlob(function(blob) {
    fetch('/article/fetch/post/image', {
      method: 'POST',
      body: blob
    })
      .then(response => response.json())
      .then(result => alert(JSON.stringify(result, null, 2)))
  }, 'image/png');
}

  </script>
</body>
```
### FormData and Sending Forms
Using a form element, capture its fields. fetch accepts `FormData` as an object that will be encoded and sent out with `Content-Type: multipart/ form-data`.
```JavaScript
let formData = new FormData([form]);

formElem.onsubmit = async (e) => {
    e.preventDefault();

    let response = await fetch('/article/formdata/post/user', {
      method: 'POST',
      body: new FormData(formElem)
    });

    let result = await response.json();

    alert(result.message);
  };
```

FormData can be modified with methods:
* `formData.append(name, value)` – add a form field with the given name and value.
* * `formData.append(name, blob, fileName)` – add a field as if it were <input type="file">, the third argument fileName sets file name (not form field name), as it were a name of the file in user’s filesystem.
* `formData.delete(name)` – remove the field with the given name.
* `formData.get(name)` – get the value of the field with the given name.
* `formData.set(name, value)` or `formData.set(name, blob, fileName)` - remove all fields with the given name and append a new field. 
* `formData.has(name)` – if there exists a field with the given name, returns true, otherwise false.
Forms can have multiple fields with the same name. You can iterate over formData fields using a for..of loop.

Appending information allows more information to be sent, like `formData.append("image", imageBlob, "image.png");` which is the same as submitting a file named "image.png" with <input type="file" name="image">.  

#### Tracking Download Progress
Can't track upload progress. `response.body` is a `ReadableStream` - a special object that sends data chunk-by-chunk as it comes. We can track download progress:
```JavaScript
// Step 1: start the fetch and obtain a reader
let response = await fetch('https://api.github.com/repos/javascript-tutorial/en.javascript.info/commits?per_page=100');

// instead of response.json() and other methods, obtain a stream reader
const reader = response.body.getReader();

// Step 2: get total length - may be absent for cross-origin requests
const contentLength = +response.headers.get('Content-Length');

// Step 3: read the data
let receivedLength = 0; // received that many bytes at the moment
let chunks = []; // array of received binary chunks (comprises the body)

// infinite loop while the body is downloading
while(true) {
  // done is true for the last chunk
  // value is Uint8Array of the chunk bytes
  const {done, value} = await reader.read();

  if (done) {
    break;
  }

  chunks.push(value);
  receivedLength += value.length;

  console.log(`Received ${receivedLength} of ${contentLength}`)
}

// if need a string:
// Step 4: concatenate chunks into single Uint8Array
let chunksAll = new Uint8Array(receivedLength); // (4.1)
let position = 0;
for(let chunk of chunks) {
  // copy chunks
  chunksAll.set(chunk, position); // (4.2)
  position += chunk.length;
}

// Step 5: decode into a string
let result = new TextDecoder("utf-8").decode(chunksAll);

// if find with binary:
let blob = new Blob(chunks);

// We're done!
let commits = JSON.parse(result);
alert(commits[0].author.login);
```

#### Aborting Asynchronous Events
`AbortController` can abort asynchronous tasks including fetch. It a single method `abort()` and a single property `signal`. When `abort` is called, the abort event triggers on `controller.signal` and the `controller.signal.aborted` property becomes true.

```JavaScript
let controller = new AbortController();
let signal = controller.signal;
// Listen to abort on signal
fetch(url, {
  signal: controller.signal
});

// triggers when controller.abort() is called
signal.addEventListener('abort', () => alert("abort!"));

controller.abort(); // abort!

alert(signal.aborted); // true

// Aborting a fetch rejects its promise with error AbortError, which can be handled in try..catch.
try {
  let response = await fetch('/article/fetch-abort/demo/hang', {
    signal: controller.signal
  });
} catch(err) {
  if (err.name == 'AbortError') { // handle abort()
    alert("Aborted!");
  } else {
    throw err;
  }
}
```
One `AbortController` can be used with multiple fetches. It will be able to abort them all.

#### Cross-Origin Resource Sharing (CORS)
Protects the internet from evil hackers - a script from one site cannot access the content of another site. The browser adds an `Origin` header: /your/url (domain, protocol, port without a path) to cross-origin requests. If the request is accepted, there is special `Access-Control-Allow-Origin` header added to the response that contains /your/origin or a star.

Simple requests have:
* Simple method: `GET`, `POST`, or `HEAD`
* Simple headers:
* * `Accept`
* * `Accept-Language`
* * `Content-Language`
* * `Content-Type` - `application/x-www-form-urlencoded`, `mutlipart/form-data`, or `text/plain`.
Simple requests can be made with a form or a script without any special methods.

JavaScript can only access simple response headers:
* `Cache-Control`
* `Content-Language`
* `Content-Type`
* `Expires`
* `Last-Modified`
* `Pragma`

To access any other response header, the service must send an `Access-Control-Expose-Headers: non-simple,header,names`.

To access the percentage of progress of a download, can use `XMLHttpRequest` instead of fetch (supporting in old browsers without polyfills).

To send a non-simple request, the browser sends a preflight request asking for permission using method `OPTIONS` with headers:
*  `Access-Control-Request-Method` - header has the method of the non-simple request.
* `Access-Control-Request-Headers` - header provides a comma-separated list of its non-simple HTTP-headers.

If the server agrees to serve these requests, it will respond with an empty body, status 200, and headers:
* `Access-Control-Allow-Methods` - must have the allowed method.
* `Access-Control-Allow-Headers` - must have a list of allowed headers.
* Additionally, `Access-Control-Max-Age` - may specify a number of seconds to cache the permissions, so the browser won’t have to send a preflight for subsequent requests that satisfy given permissions.

```JavaScript
// For the non-simple request:
let response = await fetch('https://site.com/service.json', {
  method: 'PATCH',
  headers: {
    'Content-Type': 'application/json',
    'API-Key': 'secret'
  }
});
// The browser on its own sends a preflight request:
OPTIONS /service.json // path is same as main request
Host: site.com
Origin: https://javascript.info // source origin
Access-Control-Request-Method: PATCH // requested method
Access-Control-Request-Headers: Content-Type,API-Key // non-simple headers

// If allowed, the server responds with
200 OK
Access-Control-Allow-Methods: PUT,PATCH,DELETE
Access-Control-Allow-Headers: API-Key,Content-Type,If-Modified-Since,Cache-Control
Access-Control-Max-Age: 86400

// The broswer now makes the main request
PATCH /service.json
Host: site.com
Content-Type: application/json
API-Key: secret
Origin: https://javascript.info // used because it's cross-origin

// The server appends this to the main response:
Access-Control-Allow-Origin: https://javascript.info
```

By default, a cross-origin request doesn't bring any credentials, like cookies or HTTP authentication. Usually requests to a site are accompanied by all cookies from that domain. To send credentials in fetch, the option `credentials: "include"` must be added.
``` JavaScript
fetch('http://another.com', {
  credentials: "include"
});
// If the server agrees to accept the request with credentials:
200 OK
Access-Control-Allow-Origin: https://javascript.info // cannot use * with credentials
Access-Control-Allow-Credentials: true
```

#### URLs
`protocol://hostname:port/pathname?search#hash`

Origin: `https//site.com:8080`

href: `/path/page?p1=v1&p2=v2#hash`

```JavaScript
new URL(url, [base]);

let url1 = new URL('https://javascript.info/profile/admin');
// Same as:
let url2 = new URL('/profile/admin', 'https://javascript.info');
```

Search Parameters

Need to be encoded if contain spaces, non-latin letters, etc.
`url.searchParams` provides methods:
* `append(name, value)` – add the parameter by name,
* `delete(name)` – remove the parameter by name,
* `get(name)` – get the parameter by name,
* `getAll(name)` – get all parameters with the same name (that’s possible, e.g. ?user=John&user=Pete),
* `has(name)` – check for the existance of the parameter by name,
* `set(name, value)` – set/replace the parameter,
* `sort()` – sort parameters by name, rarely needed,
`.searchParams` is also iterable with decoded values, similar to Map.
```JavaScript
// iterate over search parameters (decoded)
for(let [name, value] of url.searchParams) {
  alert(`${name}=${value}`); // q=test me!, then tbs=qdr:y
}
```

If using a string instead of a url object, need to encode/ decode manually:
* `encodeURI` – encodes URL as a whole, only characters that are totally forbidden in the URL (:, ?, =, &, #).
* `decodeURI` – decodes it back.
* `encodeURIComponent` – encodes a single URL component, such as a search parameter, or a hash, or a pathname, including characters #, $, &, +, ,, /, :, ;, =, ? and @.
* `decodeURIComponent` – decodes it back.

#### XMLHttpRequest
```JavaScript
let xhr = new XMLHttpRequest();
xhr.withCredentials = true; // if want to send cookies and HTTP-authorization, which aren't enabled by default

/*
 * @param method - HTTP-method, like "GET" or "POST"
 * @param URL - string or URL object
 * @param async - if false, then request is synchronous
 * @param user, password - login for basic HTTP auth (if required)
 */
xhr.open(method, URL, [async, user, password]); // configures the request, does not open it
// async = false: execution pauses at send and resumes when the response is received. Blocks in-page JavaScript, may make it impossible to scroll. Used rarely.

xhr.responseType = 'json';
/*

    "" (default) – get as string,
    "text" – get as string,
    "arraybuffer" – get as ArrayBuffer (for binary data, see chapter ArrayBuffer, binary arrays),
    "blob" – get as Blob (for binary data, see chapter Blob),
    "document" – get as XML document (can use XPath and other XML methods),
    "json" – get as JSON (parsed automatically).
*/

xhr.send([body]);

// Events triggered solely on uploading
// loadstart
// progress - triggers periodically during the upload
xhr.upload.onprogress = function(event) {
  alert(`Uploaded ${event.loaded} of ${event.total} bytes`);
};
// abort
// load - finished successfully
xhr.upload.onload = function() {
  alert(`Upload finished successfully.`);
};
// timeout - if timeout property is set
// error - non-HTTP error
xhr.upload.onerror = function() {
  alert(`Error during the upload: ${xhr.status}`);
};
// loadend - finished with either success or error, on abort or timeout
  // track completion: both successful or not
xhr.onloadend = function() {
  if (xhr.status == 200) {
    console.log("success");
  } else {
    console.log("error " + this.status);
  }
};

// Listen for a response
xhr.onload = function() { // when complete - even if status of failure, response is fully downloaded
  if (xhr.status != 200) { // analyze HTTP status of the response
    alert(`Error ${xhr.status}: ${xhr.statusText}`); // e.g. 404: Not Found
  } else { // show the result
    alert(`Done, got ${xhr.response.length} bytes`); // responseText is the server
  }
};

xhr.onerror = function() { // only triggers if the request couldn't be made at all - like network being down or invalid URL
  alert(`Network Error`);
};

xhr.onprogress = function(event) { // triggers periodically
  // event.loaded - how many bytes downloaded
  // event.lengthComputable = true if the server sent Content-Length header
  // event.total - total number of bytes (if lengthComputable)
  alert(`Received ${event.loaded} of ${event.total}`);
};

xhr.timeout = 10000; // timeout in ms, 10 seconds
```
XMLHttpRequest can send custom headers
* `setRequestHeader(name, value)` - can't be undone, deleted, or overwritten.
* `getResponseHeader(string name)`
* `getAllResponseHeaders()` - returns all respons headers except `Set-Cookie` and `Set-Cookie2` in a single line with line breaks `\r\n` in between.
```JavaScript
// Example:
Cache-Control: max-age=31536000
Content-Length: 4260
Content-Type: image/png
Date: Sat, 08 Sep 2012 16:53:16 GMT
```

Once server has response, `xhr` has the properties
* `status`
* `statusText` - message.
* `response` - body.

##### Resumable File Upload
If upload fails, like if it was buffered by a local network proxy or the remote server died and couldn't process it or it was lost in the middle and never reached the receiver, we need to know how many bytes were successfully received by the server. This is done through an additional request.
```JavaScript
let fileId = file.name + '-' + file.size + '-' + +file.lastModifiedDate;
// Send request to ask how many bytes the server has, assuming the server tracks uploads by the X-File-Id header
let response = await fetch('status', {
  headers: {
    'X-File-Id': fileId
  }
});

// The server has that many bytes
let startByte = +await response.text(); // 0 if doesn't yet exist
xhr.open("POST", "upload", true);

// File id, so that the server knows which file we upload
xhr.setRequestHeader('X-File-Id', fileId);

// The byte we're resuming from, so the server knows we're resuming
xhr.setRequestHeader('X-Start-Byte', startByte);
// server can check records to see if there was an upload of that fileId with the uploaded size of X-Start-Byte.

xhr.upload.onprogress = (e) => {
  console.log(`Uploaded ${startByte + e.loaded} of ${startByte + e.total}`);
};

// file can be from input.files[0] or another source
xhr.send(file.slice(startByte)); // Blob method to send file from startByte
```

### Persistent Connections with Servers
Regular requests, or periodic polling, can get you new information ofa server. However, the delay with which messages are passed is up to 10 seconds between requests. Being bombarded with requests every 10 seconds is quite a lot to handle performance-wise anyway.
#### Long Polling
This works when messages are rare. Each message is received in a separate request, supplied with headers, authentication overhead, etc.

Send a request to the server. Server won't close the connection until it has a message to send (the connection hangs). When a message appears, the server responds to the request with it. Upon receipt or loss of connection, the browser immediately makes a new request. This works with servers that can work with many pending connections, such as those that don't run one memory-heavy process per connect.
```JavaScript
async function subscribe() {
  let response = await fetch("/subscribe");

  if (response.status == 502) {
    // Status 502 is a connection timeout error,
    // may happen when the connection was pending for too long,
    // and the remote server or a proxy closed it
    // let's reconnect
    await subscribe();
  } else if (response.status != 200) {
    // An error - let's show it
    showMessage(response.statusText);
    // Reconnect in one second
    await new Promise(resolve => setTimeout(resolve, 1000));
    await subscribe();
  } else {
    // Get and show the message
    let message = await response.text();
    showMessage(message);
    // Call subscribe() again to get the next message
    await subscribe();
  }
}

subscribe();
```
#### WebSocket
For services that require continuous data exchange, like online games or real-time trading systems.

A protocol that provides a way to exchange data between browser and server via a persistent connection. Data is passed in both directions as packets without breaking the connection or additional HTTP-requests.

```JavaScript
let socket = new WebSocket("ws://javascript.info"); // wss:// for encrypted, like HTTPS
// wss:// is more reliable.
// starts connecting immediately
socket.readyState
// vlaue of:
// 0 - CONNECTING, not yet established
// 1 - OPEN, communicating
// 2 - CLOSING
// 3 - CLOSED

// Listen to events on the socket
socket.onopen = function(e) {
  alert("[open] Connection established");
  alert("Sending to server");
  socket.send("My name is John");
};

socket.onmessage = function(event) {
  alert(`[message] Data received from server: ${event.data}`);
  // always receives text as strings, can choose bvetween Blob (direct integration with <a> and <img> tags) and ArrayBuffer (for accessing individual data bytes) for binary data
  socket.bufferType = blobl // default
};

socket.onclose = function(event) {
  if (event.wasClean) {
    alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
  } else {
    // e.g. server process killed or network down
    // event.code is usually 1006 in this case
    alert('[close] Connection died');
  }
};

socket.onerror = function(error) {
  alert(`[error] ${error.message}`);
};

// Send Text or binary data
socket.send(body); // where body is in string or binary format, including Blob, ArrayBuffer, etc.
// Can only send as fast as the network speed allows. 
// Can see how many bytes are buffered (stored) in memory because the network isn't fast enough to send their calls:
// every 100ms examine the socket and send more data
// only if all the existing data was sent out
setInterval(() => {
  if (socket.bufferedAmount == 0) {
    socket.send(moreData());
  }
}, 100);

// Closing the Connection
// Sen a connection close frame:
socket.close([code = 1000], [reason]);
// closing party:
socket.close(1000, "Work complete");

// the other party
socket.onclose = event => {
  // event.code === 1000
  // event.reason === "Work complete"
  // event.wasClean === true (clean close)
};
```
Common close values:
* `1000` - default, normal closure
* `1006` - can't code manually, but indicates connection was lost - no close frame was sent.
* `1001` – the party is going away, e.g. server is shutting down, or a browser leaves the page.
* `1009` – the message is too big to process.
* `1011` – unexpected error on server.
https://tools.ietf.org/html/rfc6455#section-7.4.1

WebSocket objects are cross-origin by nature and don't require any special headers to be. JavaScript can't set WebSocket headers, so it can't emulate them.

Example request:
```JavaScript
let socket = new WebSocket("wss://javascript.info/chat");
let socket = new WebSocket("wss://javascript.info/chat", ["soap", "wamp"]); // array of subprotocols

GET /chat
Host: javascript.info
Origin: https://javascript.info // origin of client page
Connection: Upgrade // means client wants to change the protocol
Upgrade: websocket // requested protocol is websocket
Sec-WebSocket-Key: Iv8io/9s+lYFgZWcXczP8Q== // random browser-generated key for security
Sec-WebSocket-Version: 13 // WebSocket protocol version
// Optional Headers
Sec-WebSocket-Extensions: deflate-frame // browser supports data compression
Sec-WebSocket-Protocol: soap, wamp // indicates desire to transfer data in SOAP or WAMP (WebSocket Application Messaging Protocol)

// Response
101 Switching Protocols
Upgrade: websocket
Connection: Upgrade
Sec-WebSocket-Accept: hsBlbuDTkk24srzEOTBUlZAlC2g= // Sec-WebSocket-Key recoded using a special algorithm to establish that the response corresponds to the request
// Optional Headers
Sec-WebSocket-Extensions: deflate-frame
Sec-WebSocket-Protocol: soap // if only supports SOAP of the requested subprotocols
```
WebSocket communication consists of frames - dat afragments that can be sent from either side with form:
* text frames
* binary data frames
* ping/pong frames - check connection to the client from the server, browser responds automatically
* connection close frame


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
When data is updated (`setItem`, `removeItem`, `clear`) in `localStorage` or `sessionStorage`, the storage event triggers with properties:
* `key` - key that was changed, `null` if `.clear()` was called.
* `oldValue` - `null` if the key is newly added.
* `newValue` - `null` if the key is removed.
* `url` - of the document where the update happened.
* `storageArea` - either `localStorage` or `sessionStorage` that was modified by the event.

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
Small strings of data stored directly in the browser. Usually set by a `"Set-Cookie"` HTTP-header with a unique session identifier. The next time a request is set to the same domain, the browser sends the cookie using the "Cookie" HTTP- header so the server knows which session made the request.

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

If a cookie has sensitive content that shouldn't be sent over unencrypted HTTP, set the `secure` flag so it doesn't appear when accessed over HTTP but will over HTTPs.
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

request.onerror = function(event) {
  // ConstraintError occurs when an object with the same id already exists
  if (request.error.name == "ConstraintError") {
    console.log("Book with such id already exists"); // handle the error
    event.preventDefault(); // don't abort the transaction
    event.stopPropagation(); // don't bubble error up, "chew" it
    // use another key for the book?
  } else {
    // unexpected error, can't handle it
    // the transaction will abort
    transaction.abort(); // implicitly calls
    // Cancels all modification made by the requests in it and triggers transaction.onabort event.
  }
};

// Only complete guarantees that the transaction is saved as a whole. Individual requests may succeed, but the final write operation may go wrong (e.g. I/O error or something).
transaction.oncomplete = function() {
  console.log("Transaction is complete");
};
```

Don't need onerror/ onsuccess for each request because all events are DOM events and will bubble up from the request -> transaction -> database, so we only need to watch for and handle db errors.

```JavaScript
db.onerror = function(event) {
  let request = event.target; // the request that caused the error

  console.log("Error", request.error);
};
```

Searching in an Object Store

Can search by keys or by another object field. The object store sorts all values by key internally, so values will return in an order sorted by their keys.

Create a range of keys
* `IDBKeyRange.only(key)` – a range that consists of only one key, rarely used.
* `IDBKeyRange.lowerBound(lower, [open])` means: ≥lower (or >lower if open is true)
* `IDBKeyRange.upperBound(upper, [open])` means: ≤upper (or <upper if open is true)
* `IDBKeyRange.bound(lower, upper, [lowerOpen], [upperOpen])` means: between lower and upper. If the open flags is true, the corresponding key is not included in the range.

Search methods with a key range query argument
* `store.get(query)` – search for the first value by a key or a range.
* `store.getAll([query], [count])` – search for all values, limit by count if given.
* `store.getKey(query)` – search for the first key that satisfies the query, usually a range.
* `store.getAllKeys([query], [count])` – search for all keys that satisfy the query, usually a range, up to count if given.
* `store.count([query])` – get the total count of keys that satisfy the query, usually a range.

```JavaScript
// get one book
books.get('js')

// get books with 'css' <= id <= 'html'
books.getAll(IDBKeyRange.bound('css', 'html'))

// get books with id < 'html'
books.getAll(IDBKeyRange.upperBound('html', true))

// get all books
books.getAll()

// get all keys: id > 'js'
books.getAllKeys(IDBKeyRange.lowerBound('js', true))
```

Search by any field using a data structure called an index.
```JavaScript
/*
 * @param name: index name
 * @param keyPath: path to the object field that the index tracks - the field you're searching by
 * @param option: with properties:
 *   unique: if true, then there may be only one object in the store with the given value at the keyPath. Enfoce this by generating an error if we try to add a duplicate.
 *   multiEntry: if the value on the keyPath is an array. By default, the index will treat the whole array as the key, but if multiEntry is true, the index will keep a list of store objects for each value of the array, making each array member become an index key.
 */
objectStore.createIndex(name, keyPath, [options]);

openRequest.onupgradeneeded = function() {
  // we must create the index here, in versionchange transaction
  let books = db.createObjectStore('books', {keyPath: 'id'});
  let index = inventory.createIndex('price_idx', 'price'); // index tracks price
  // price is not unique, so do not set this to true
  // price is not an array, so multiEntry is not true
};

// The index now keeps a list of keys at each price
let transaction = db.transaction("books"); // readonly
let books = transaction.objectStore("books");
let priceIndex = books.index("price_idx");

let request = priceIndex.getAll(10);
// find books where price <= 5
let request = priceIndex.getAll(IDBKeyRange.upperBound(5));

request.onsuccess = function() {
  if (request.result !== undefined) {
    console.log("Books", request.result); // array of books with price = 10, another request with array of books with price <= 5
  } else {
    console.log("No such books");
  }
};

// delete the book with id='js'
books.delete('js');

// find the key where price = 5
let request = priceIndex.getKey(5);

request.onsuccess = function() {
  let id = request.result;
  let deleteRequest = books.delete(id); // delete the book where price = 5
};

books.clear(); // clear the storage
```

Cursors are special objects that traverse the object story, given a query, and return one key/ value pair at a time to save memory, because sometimes object storage is greater than the available memroy.

Cursor walks the store in key order (default: ascending).

```JavaScript
/*
 * @param query: key or key range, same as for getAll.
 * @param direction: optional argument, which order to use:
 *   "next": the default, the cursor walks up from the record with the lowest key.
 *   "prev": the reverse order: down from the record with the biggest key.
 *   "nextunique", "prevunique": same as above, but skip records with the same key (only for cursors over indexes, e.g. for multiple books with price=5 only the first one will be returned).
 */
let request = store.openCursor(query, [direction]);
// cursor made over object store

let transaction = db.transaction("books");
let books = transaction.objectStore("books");

let request = books.openCursor();

// called for each book found by the cursor
request.onsuccess = function() {
  let cursor = request.result;
  if (cursor) {
    let key = cursor.key; // book key (id field)
    let value = cursor.value; // book object
    console.log(key, value);

    cursor.advance(count); // advance the cursor count times, skipping values
    // OR
    cursor.continue(); // advance the cursor to the next value in range matching (or immediately after key if given).
  } else {
    console.log("No more books");
  }
};
// Cursor will trigger request.onsuccess multiple times - once for each result.

// Cusor over index:
// cursor.key is the index key, like price, cusor.primaryKey property is the object key
let request = priceIdx.openCursor(IDBKeyRange.upperBound(5));

// called for each record
request.onsuccess = function() {
  let cursor = request.result;
  if (cursor) {
    let key = cursor.primaryKey; // next object store key (id field)
    let value = cursor.value; // next object store object (book object)
    let key = cursor.key; // next index key (price)
    console.log(key, value);
    cursor.continue();
  } else {
    console.log("No more books");
  }
};
```

Promise wrappers: https://github.com/jakearchibald/idb  

# Security
## Same Origin Policy
Both pages must agree to data exchange.
## Cross-site Scripting Attacks

# Modern Mode
``"use strict";`` at the very top of a script will cause it to work the modern, post-ES5 way that may break the functionality of old code.

Classes and modules enable strict mode automatically.

Without `use strict`, if a variable is not found anywhere, a new global variable is created. In `use strict`, this throws an error.

## Recursion and Iteration
Recursion is a function that calls/ returns itself or a base case, meaning it remakes the method overhead and creates a new stack frame. Recursion makes for concise, elegant code and easy readability, making for easier maintainability.

We can rely on the engine allowing a maximal depth of 10000. 

Each function execution ges an internal data structure called the execution context, which stores where the control flow is now, current values, this, and other details. When a function makes a nested call:
* The current function is paused and its execution context is remembered in a special data structure called the execution context stack.
* The nested call executes, creating a new context.
* When it returns,its execution context is no longer needed and is removed from memory. The old execution context is popped off the stack and resumed from where it stopped.

Anything that can be made with recursion can be made with iteration, which uses a loop. This saves memory but decreases readability. Sometimes the memory optimizations are not worth the effort to rewrite the function.