import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiPredicate;
public class Datastructures_Linear {

    /* Arrays
     *
     * Indexed
     * Fixed size - dynamically allocated
     *
     * Unsorted Array
     * Access: O(1)
     * Insert: O(1) increase size by 1, add in element, if adding to a certain position O(n)
     * Delete: O(1) unless at a certain position unless need to shift: O(n) - or can swap with the last element and decrement the array size by 1
     * Search: O(n)
     * Space: O(n)
     *
     * Sorted Array
     * Access: O(1)
     * Insert: O(n)
     * Delete: O(n)
     * Search: O(log(n))
     * Space: O(n)
     */

    void ArrayType() {
        int[] arrayOne = new int[4];
        for (int i=0; i < arrayOne.length; i++) {
            arrayOne[i] = i+1;
        }
        /* 2D arrays by [row][column] */
        int[][] arrayTwo = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
        };
    }


    /* Lists ========================================================================
     *
     * Lists allow duplicates
     * Ordered Collection in order of when inserted
     * Indexed
     *
     * Best for random access
     * Expandable array - when reach then end, it doubles in size
     *
     * Insert:
     * Delete:
     * Search:
     * Space:
     */

    void ArrayListType() {
        List<Integer> honeydew = new ArrayList<Integer>();
        // can initialize with a collection or do .addAll()
        List<String> fruits = new ArrayList<String>(Arrays.asList("cantelope","strawberry", "raspberry"));
        boolean bool = honeydew.isEmpty();
        int value = 2;
        // adds to the end of the list
        honeydew.add(value);
        int index = 0;

        value = honeydew.get(index);
        index = honeydew.indexOf(value);
        honeydew.set(index, value);

        int size = honeydew.size();
        Object[] arr = honeydew.toArray();
        honeydew.remove(index);

        bool = fruits.contains("melon");
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

        /* ternary operator ? : ================================================== */
        int tmp;
        if (fruits.contains("honeydew")) {
            //fruits.add("honeydew");
            tmp = fruits.indexOf("honeydew");
        } else {
            //fruits.add("durian durian");
            tmp = -1;
        }

        tmp = ( fruits.contains("honeydew") ) ? fruits.indexOf("honeydew") : -1 ;
        // use .clear() to remove everything or .removeAll(Collection)
    }
    /*
     * Best for adding and removing elements
     * Access: O(n)
     * Insert: O(1) //if know where putting
     * Delete: O(1)
     * Search: O(n)
     * Space:  O(n)
     */
    void LinkedListType() {
        List<String> linked = new LinkedList<String>();
        // can do an .addAll(Collection)
        linked.add("chain1");
        linked.add("chain2");
        ((LinkedList<String>) linked).addFirst("chain0"); // because we declared it as a list, and List doesn't have the addFirst method
        ((LinkedList<String>) linked).addLast("chain3");

        boolean bool = linked.contains("chain0");
        int index = linked.indexOf("chain1");
        String value = linked.get(index);
        linked.remove(index);
        index = linked.size()-1;

        String first = ((LinkedList<String>) linked).getFirst();
        String last = ((LinkedList<String>) linked).getLast();
        // peek keeps it safe, poll removes, pop removes, push adds on

        linked.toArray();
        // iterate with descendingIterator() or listIterator( int startingIndex)
    }
    int findLengthIterative(LinkedList<String> rhett) {
        int counter = 0;
        String current = rhett.getFirst();
        while(current != null) {
            //current = current.next();
            current= rhett.get(++counter);
        }
        return counter;
    }
    int findLengthRecursive(LinkedList<Object> rhett, int index) {
        if (rhett.get(index) == null) {
            return 0;
        }
        return 1 + findLengthRecursive(rhett, ++index);
    }
    /*
     *
     * Access: O(n)
     * Insert: O(1)
     * Delete: O(1)
     * Search: O(n)
     * Space: O(n)
     */
    public class DoublyLinkedList {
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
            newNode.prev = foot.prev;
            newNode.prev = foot.prev;
            foot = newNode;
        }
    }

    void DoublyLinkedListType() {
        // traversing
        DoublyLinkedList dll = new DoublyLinkedList();
        DoublyLinkedList.Node currNode = dll.head;
        while (currNode.next != null) {
            currNode = currNode.next;
            System.out.println(currNode.data);
        }

    }
    /*
     *
     * Access: O(log(n)) - O(n)
     * Insert: O(log(n)) - O(n)
     * Delete: O(log(n)) - O(n)
     * Search: O(log(n)) - O(n)	 * Space: O(nlog(n))
     */
    void SkipListType() {

    }

    void VectorType() {

    }

    /* Other ========================================================================
     *
     */


    /*
     * A Collection
     * Ordered in insertion order
     * FIFO
     * Limited to inserting elements at the end and deleting elements from the start
     * Can also initialize as a linkedList
     */
    void QueueType() {
        Queue<Integer> eye = new LinkedList<>();
        for (int i=0; i<5; i++) {
            eye.add(i);
        }
        int head = eye.remove();
        head = eye.peek();
        // head is still 1
        /* Collection functions */
        // can also do .contains() because it's a collection
        if(!eye.isEmpty()) {
            int size = eye.size();
        }
    }
    /*
     * Doesn't allow null
     * Objects must be comparable
     * Ordered by natural ordering or some Comparator
     * Head is the least element
     */
    void PriorityQueueType() {
        PriorityQueue<Integer> trix = new PriorityQueue<Integer>();
        for (int i=0; i<5; i++) {
            trix.add(5-i);
            System.out.println(5-i);
        }
        System.out.println("Following the add: ");
        Iterator<Integer> iter = trix.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        int head = trix.peek();
        // retrieve and remove the head
        head = trix.poll();
        System.out.println("Following the pop: ");
        iter = trix.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        int value = 3;
        trix.remove(value);
        System.out.println("Following the remove: ");
        iter = trix.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
        Object arr[] = trix.toArray();
    }
    /*
     * Extends Vector
     * push to the top of the stack and pop off the top
     * LIFO
     *
     * Access: O(n)
     * Insert: O(1)
     * Delete: O(1)
     * Search: O(n)
     * Space: O(n)
     */
    void StackType() {
        Stack<Integer> cup = new Stack();
        if (cup.empty()) {
            for (int i=0; i<5; i++) {
                cup.push(i);
            }
        }
        System.out.println(cup.search(4));
        int top = cup.pop();
        top = cup.peek();

    }
    void CustomStackType() {
        private Node top = null;
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
        void push(Object item) {
            top = new Node(item, top);
        }
    }

    /*
     * Immutable object: does not change
     * If an item is added or deleted, the previous stack is maintained and a new one with the modifications is made.
     */
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
    /*
     * Root is a minimum and all of the levels are as full or as left as possible
     * Root at Arr[0], parent of i: Arr[i/2], left child of i: Arr[(2*i)+1], right child of i: Arr[(2*i)+2]
     * This is level-mode traversal
     * Sorts array in O(nlog(n)) time
     */
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

        BinHeap.BinaryMinHeap(5);
        BinHeap.insert(4);
        System.out.println("Min: " +BinHeap.extractMin());
        BinHeap.insert(2);
        System.out.println("Min: " +BinHeap.extractMin());
        BinHeap.insert(1);
        System.out.println("Min: " +BinHeap.extractMin());
        BinHeap.insert(3);
        System.out.println("Min: " +BinHeap.extractMin());
        BinHeap.insert(0);
        System.out.println("Min: " +BinHeap.extractMin());
        System.out.println("Min: "+ BinHeap.getMin());
        BinHeap.changeVal(0, 15);
        BinHeap.delete(0);
        System.out.println("Min: " + BinHeap.getMin());

    }
    /* Sets ===========================================================================
     *
     * Sets don't have repeats
     * Unordered Collection
     */

    void HashSetType() {
        /*
         * Actually implements a HashMap with unique keys and identical values
         */
        Set<String> hashbrowns = new HashSet<String>();
        hashbrowns.add("mushrooms");
        hashbrowns.add("salt");
        hashbrowns.add("onions");
        if(hashbrowns.contains("salt")) {
            hashbrowns.add("pepper");
        }
        hashbrowns.add("anthrax");
        hashbrowns.remove("anthrax");

        Iterator<String> iter = hashbrowns.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
    /*
     * Maintains order
     */
    void LinkedHashSetType() {

    }

    /*
     * Maintains order
     * Sort using Comparator or Comparable
     */
    void TreeSetType() {

    }

    /* Maps ==============================================================================
     * Set of unique keys, potentially repeat values
     */
    /*
     * can store and retrieve in constant time O(1)
     * accepts null
     * non-sychronized, so don't use in multithreaded apps
     */
    void HashMapType() {
        Map<String, Integer> breakfast = new HashMap<String, Integer>();
        breakfast.put("cereal", 7); // creates a (key, value) entry, gets put in a bucket
        breakfast.put("cereals", 10); // let's say this is a collision: is put in the same bucket
        // can find the value 10 even if hashCode("cereal") = hashCode("cereals") by going through a linked list or a tree
        // (depending on the version of java) because java will store BOTH the key and the value in some sort of list in the same bucket
//		ListIterator<Integer> bucketList = (ArrayList breakfast.get("cereal")).listIterator();
//		while(bucketList.next() != null && !bucketList.equals("cereal")) {
//			int i= bucketList.next();
//		}
        System.out.println(breakfast.get("cereal"));
        /* keys are immutable because generate quasi-unique hashcodes */

        //rehashing: when a load factor is reached, say load = .75, when the map is 75% full it will double in size and redefine all of the hashCodes
        // don't use hashmap in multitrheaded apps because if two threads resize the map, could get an infinite loop in a bucket with a collision

        // Keys are best when they're wrapper classes like String or Integer :
        // need to be immutable so get the same hashcode for the same input

        // null keys map to index 0

        // failfast iterator
    }
    /*
     * Sorted according to insertion order
     * allows null values
     * doubly-linked list of buckets
     */
    void LinkedHashMapType() {

    }
    /*
     * Does not allow null values
     * better for thread safety than ConcurrentHashMap
     * 	otherwise, kinda obsolete
     * Slower than HashMap in a single thread environ
     *
     * Insert: O(1) - O(n)
     * Delete: O(1) - O(n)
     * Search: O(1) - O(n)
     * find min/max: O(n)
     * Space: O(n)
     */
    void HashtableType() {

    }
    /*
     * Better scalability than HashTable
     */
    void ConcurrentHashMapType() {

    }
    /*
     * Maintains natural order of trees, so get put and remove run in O(log(n)) time so can resort
     * no null values allowed
     * uses a red-black tree
     */
    void TreeMapType() {

    }
    public static void main (String[] args) {
        //Datastructures_Linear dl = new Datastructures_Linear();
        //dl.PriorityQueueType();

        Datastructures_Linear h = new Datastructures_Linear();
        h.BinaryMinHeapType();

    }
}
