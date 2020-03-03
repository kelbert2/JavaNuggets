
# Primitive Data Types
## Boolean
Stores either `true` or `false`.
## Byte

| Data Type |	Maximum Value |	Minimum Value|
|-----------|:-----------------:|--------------:|
|int |	2,147,483,647 |	- 2,147,483,648|
|float |	3.4028235 E38 |	1.4 E-45|
|double |	1.7976931348623157 E308 |	4.9 E-324|
|char |	65,535 |	0|
|short |	32,767 |	-32,768|
|long |	9,223,372,036,854,775,807 |	-9,223,372,036,854,775,808

## Strings
End with ‘\0’
* `.length()`
* `Integer.parseInt(str)`
* `Integer.valueOf(str)`
* `.split(“ “)`, also takes `\regex\`
* `.toCharArray()`

### Comparison
* Positive if string1 follows argument string2 
  * > c > b, a > A, returns difference at index where they differ
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
* `.set(index, value)`
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

### Binary Minimum Heap

```Java
     * Root is a minimum and all of the levels are as full or as left as possible
     * Root at Arr[0], parent of i: Arr[i/2], left child of i: Arr[(2*i)+1], right child of i: Arr[(2*i)+2]
     * This is level-mode traversal
     * Sorts array in O(nlog(n)) time
interface BinaryMinHeap { // auto abstract and public
        boolean insert(int k);
        boolean delete(int i);
        boolean changeVal(int i, int newVal);
        int getMin();
        int extractMin();
        void BinaryMinHeap(int i);
    }
    void BinaryMinHeapType() {
        System.out.println("Hi!");
        
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
##### Thread Safe HashMap
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

#### TreeMap
Uses a red-black tree so can get, put, and remove in O(log(n)) time so can resort

## Graphs
Nodes connecting to other nodes

### Trees
Starts with a root node. Each node has zero (in the case of a leaf node) or more (branch) child nodes. No cycles.

Quicker than arrays at insertion/deletion and slower than unordered linkedLists
O(log(n))

#### Binary trees
Up to two children.

* All binary trees have the smallest possible height: O(log(n))
     * Parents have at most 2 children
     * Max nodes at level i: 2^(i-1)
     * Max nodes of tree height h: 2^h-1
     * Min height/levels for n Nodes: log(n+1) for log base 2
     * Min levels for L leaves: log(L)+1
     * Full binary tree (0-2 children): leaf nodes = internal nodes + 1
     * Complete binary trees mean all levels are completely filled except, possibly, the last level that has all keys as left as possible.

##### Binary Search Trees
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

# Algorithms

## Traversal

### In-order
Take left subtree down the the bottom, then root, then start with right subtree

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

## Sorting
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

### MergeSort

### Quick Sort

### BubbleSort
For each pair, swap them
O(n^2)
Modified, at each pass, check if the array is already sorted
O(n) best
Insertion sort
Worst case if sorted in reverse order O(n^2)

### QuickSort
Usings a pivot, best case if pivot happens to divide into n/2 halves.
```Java
int[] QuickSort(int[] array) {
        int pivot = array[0];
        int[] left = new int[array.length];
        int liter = 0;
        int[] right = new int[array.length];
        int riter = 0;
        for( int i = 0; i <= array.length; i++) {
            if (array[i] <= pivot) {
                left[liter++] = array[i];
            } else {
                right[riter++] = array[i];
            }
        }
        left = QuickSort(left);
        right = QuickSort(right);
        for(int i=0; i< left.length; i++) {
            array[i] = left[i];
        }
        for (int i=left.length; i < right.length; i++) {
            array[i] = right[i-left.length];
        }
        return array;
    }
```
Average o(n log(n))

#### Random
With a random pivot, worst case isn’t as bad
```Java
Random lolXD = new Random();
Int pivot = lolXD.nextInt(array.length - 1);
```

### MergeSort
Best to sort linked list with const extra space, best for very large number of elements that can’t fit in memory.
Around O(n log(n))
```Java
int[] mergeSortHelper(int[] array, int i, int j) {
        int[] L = mergeSortHelper(array, i, (i+j/2));
// split into halves
        int[] R = mergeSortHelper(array, (i+j)/2+1, j);
        return merge(L, R);
}
int[] merge(int[] left, int[] right) {
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

### HeapSort binary heap
Fast
Build max heap to sort elements in ascending order 
A heap is just a tree, just put the elements as you would a tree array, with kids (i*2)+1 or +2, left to right
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

### CountingSort
When the range of data _k_ isn’t that much different from the range of indexes _n_.
O(n+k)
Space (n+2^k)
```Java
General var = specificImplementation;
List<type> var = ArrayList<type>(capacity); // note size = 0
```

```Java
Enum name {
NAME, NAME, NAME
}
Custom comparable
Class implements Comparable<className>{
Public int compare(className o1, className o2){

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

    Selection median
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
# Comparator
Return 
* -1 if less than, as -1 < 0
* 0 if equals, as == 0
* 1 if greater than, as 1 > 0

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
        Scanner scan = new Scanner(System.in);
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

# Concepts
### Big O Time and Space

### Recursion

#### Recurrence Relations

## Bit Manipulations

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
