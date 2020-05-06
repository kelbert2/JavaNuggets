
# Primitive Data Types
## Boolean
Stores either `true` or `false`.
## Byte

| Data Type |	Maximum Value |	Minimum Value|
|-----------|:-----------------:|--------------:|
|int |	2^32 = 2,147,483,647 |	-2^31 = -2,147,483,648|
|float |	3.4028235 E38 |	1.4 E-45|
|double |	1.7976931348623157 E308 |	4.9 E-324|
|char |	65,535 |	0|
|short |	32,767 |	-32,768|
|long |	9,223,372,036,854,775,807 |	-9,223,372,036,854,775,808

1 GB is 8 billion bits.

## Strings
End with ‘\0’
* `.length()`
* `Integer.parseInt(String, int radix) => int`
* * radix being the mathematical base. 10 is decimal, 8 is octal, 16 is hexadecimal.
* * if not provided, looks at what the string begins with. "0x" or "0X" is 16, "0" may be octal or decimal, and any other value for the beginning defualts to 10
* `Integer.valueOf(int or String, int base if String) => Integer`
* * Caches frequently requested values, so more space and time efficient
* `.split(“ “)`, also takes `\regex\`
* `.toCharArray()`

### Comparison
* Positive if string1 follows argument string2 
  * > b < c, A < a, returns difference at index where they differ
* Zero if both strings are equal
* Negative if string1 Object precedes argument string2 
  * > c is shorter than cc, returns difference in lengths

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
Modifiable String - growable and writable

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
* `deleateCharAt(int index)`
* `insert(int offset, Type dta, int dataOffset?, int dataLength?)`: Index before which data is to be inserted.
* `replace(int start, int end, String s)`

* `setCharAt(int index, char c)`
* `reverse()`
* `setLength(int newLength)`: if newLength < length(), last characters are truncated. If greater, null characters are added at the end.
* `ensureCapacity(int minCapacity)`
* `toString()`

### Compare strings
* == returns 0, `.equals(str)`, `.equalsIgnoreCase(str)`
* < 0 if this is shorter than str or the first character that doesn't match is smaller than that of str
  * = 0 if the strings are equal
  * &gt; 0 if this is longer than str or the first char that doesn't match is > str
  * lower case letters are 32 greater than uppercase letters a > A
  * later in the alphabet means greater A < Z

## Enums
Group of constants.

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

# Data Structures

## Arrays
Access, insert, delete O(1)
Search, space O(n)
* `.length`

```Java
Int[] array = new int[size];
/* 2D arrays by [row][column] */
int[][] arrayTwo = new int[][]{
    {1, 2, 3},
    {4, 5, 6},
};
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
* `.size()`
* `.isEmpty()`
* `.contains(element)`
* `.add(element)`
* `.addAll(Collection)`: Union of two collections
* `.remove(index or element)`
* `.removeAll(Collection)`
* `.clear()`
* `.retainAll(Collection)`: Intersection between two collections
* `.equals(Object o);`
* `.toArray()`

```Java
Collection<Type> c;
List<Type> list = new ArrayList<Type>(collection);
```
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
Best for random access and ordering, indexed
When reach end, doubles in size
* `.get(index)`
* `.add(index, value)` - adds to the end or inserts into position and pushes everything ahead of it one position.
* `.set(index, value)` - replaces value at index - value MUST exist.
* `.indexOf(value)`
* `.toArray()`
* `.clear()`
* `.sort(Comparator<Type> c)`

#### Vectors/ ArrayLists
Dynamically resizable array - when reaching limit, doubles in size. In Java, the resizing factor is 2.
Amortized time to insert.

```Java
List<Integer> honeydew = new ArrayList<Integer>();
    // can initialize with a collection or do .addAll()
List<String> fruits = new ArrayList<String>(Arrays.asList("cantelope","strawberry", "raspberry"));

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
LinkedList where add to and remove from one side.

```Java
Stack<Integer> cup = new Stack<Integer>();
```

* `.empty()`
* `.push(element);`
* `.pop()`
* `.peek()`

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
```

#### Iteration
```Java
Iterator<String> iter = hashbrowns.iterator();
while(iter.hasNext()) {
    System.out.println(iter.next());
}
```

### TreeSet
Maintains order by sorting with Comparator or comparable

## Maps
Unique keys, values
At each index, there is a bucket, often a linkedList or tree, of {key, value} pairs.

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
* `.containsKey(key)`
* `.containsValue()`
* `.keySet()`
* `.values()`

```Java
Map<String, Integer> breakfast = new HashMap<String, Integer>();
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

### TreeMap
O(log N) lookup and insertion.

Naturally ordered keys that implement the ``Comparable`` interface.

Uses a red-black tree so can get, put, and remove in O(log(n)) time so can re-sort.

Will also allow you to output the next x pairs after a given key.

### LinkedHashMap
O(1) lookup and insertion.

Keys ordere by insertion order.

Uses doubly-linked buckets.

Useful for caching, where insertion ordre matters.

## Graphs
Nodes connecting to other nodes

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

## Enums

```Java
Enum name {
    NAME1, NAME2, NAME3
}
```

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
}
Collections.sort(ArrayList<Type> ar, new SortByCustom());
// Now ar is sorted.
```

Or more involved: 

```Java
Class implements Comparable<className>{
    Public int compare(className o1, className o2){
        // comparison function
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
List<Object> objList = findObj(name); Collections.sort(objList, new Comparator<Object>() { @Override public int compare(Object a1, Object a2) { return a1.getType().compareToIgnoreCase(a2.getType()); } });
```

# Input

```Java
void dealingWithInputStreams() throws IOException { // uses specific input stream
        InputStream input = new FileInputStream("c:\\path\fileName.txt");
        int data = input.read(); // input.read(byte[], optional int offset, optional int length);
        while (data != -1) {
            //op(data)
            try {
                data = input.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        input.close();
    }
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

Buffered Reader is faster than scanner:
```
# Concepts
### Big O Time and Space

### Recursion

#### Recurrence Relations

## Bit Manipulation
^ XOR

~ NOT

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
Objects are specific instances of classes, defined by classes or interfaces.

### Classes have member variables
* Local: declared within the method and only visible there (stack)
* Instance variable: declared in the class, outside of methods and constructors (heap dynamically allocated malloc())
* Class or static variables: only one copy, shared by all different objects in a class - once declared, not changed - constant = static final

### Variable Visibility
* `public`
* `protected`: package, subclass
* `private`: only class
* No modifier: package and class

Inheritance of properties: super or base, subclass or derived class
    `extends`

Encapsulation hides implementation

> Bind data and code together as a single unit, safe from modification.
> use private variables, public getters and setters instead.

Abstraction: abstract class or interface and then implements

> Abstract cannot be instantiated (can’t create an object of it) - abstract (undefined) or concrete methods (defined)

Interfaces are a blueprint or a contract, telling you what it can do - assigns functions with return values but doesn’t define them: all methods are public abstract

Polymorphism: variable, method, and objects can take on multiple forms, define one interface or method and implement it in multiple ways

Runtime is where you call a specific child of the class

Method overloading: class can have multiple methods with the same name but different arguments (compile time polymorphism)

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
### Generics
Type-erasure: elimates parameterized types when translates source code into Java Virtual Machine byte code.

Syntactic sugar: Object<Type> is just Object where its instances are cast to (Type).

Cannot use primitives like int or string - must use Integer or String.

### Lambda Expressions
A step towards functional programming.

```Java
(param, eters) -> // do something
```

Match with functions that take the same inputs and hav ethe same type of outputs.

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

Can call methods by name when you don't know the method in advance - like if user inputs a pclass name, parameters for the constructor, and a method name, we can use this information to create an object and call a method.

### Private Constructors
Only the class, inner classes, and child classes can access this purely internal constructor.

Used in Singletons to ensure that there is only one instance of an object at a time.

a ``getInstance()`` method returns the existing instance or makes one.

### Error Catching

```Java
try {
    // Attempt to do something
    // As soon as tries something that raises an exception, control flow moves to the catch block.
} catch (Exception ex) {
    // Can do something with the exception here
} finally {
    // Optional block
    // Regardless of the control flow above, the finally block will execute
    // This includes the catch block raising its own exception or if one of the above blocks has a "return" statement.

    // Finally won't execute if the VM exits or the thread executing it is killed prematurely.
}

```
## Keywords

### Abstract
For classes - provide the implementation of an interface. Can have abstract (left to be defined by classes that extend it) and non-abstract methods.
Interfaces can only have abstract methods.

### Final
Declares a constant variable which cannot be changed; a method which cannot be overridden; or a class which cannot be inherited.

Must be initialized at declaration.

Can also apply to variable references, where vit restricts it from pointing to any other object on the heap.

### Static
Can be accessed before the class object is created - it can be used independently of any object of that class.

Can only access static members of the class and can only be called by other static methods.

## Access Modifiers
In order of increasing accessibility.

### Private
Class-level

Can only be accessed by that class, inner classes, and derived (child) classes.

Best practice: Declare variables private by default and supply getters and setters if they need to be accessed outside of the class.

### Default = (nonexplicit) Package-Private
Package-level

Accessibily by anything in the directory to which the class belongs.

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

# Graphs
Graphs often reprensented as arrays of numbers.
```Java
graph[i][j] = flowCapacity;
```
where i and j are two vertexes. 0 means there is no direct edge connecting them to each other.