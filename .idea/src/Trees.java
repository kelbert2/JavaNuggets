import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Trees {

    /*
     * Trees are quicker than arrays at insertion/ deletion and slower than unordered linkedLists
     * Access: O(log(n)
     * Insert: O(log(n)
     * Delete: O(log(n)
     * Search: O(log(n)
     * Space: O(n)
     */
    /*
     * All binary trees have the smallest possible height: O(log(n))
     * Parents have at most 2 children
     * Max nodes at level i: 2^(i-1)
     * Max nodes of tree height h: 2^h-1
     * Min height/levels for n Nodes: log(n+1) for log base 2
     * Min levels for L leaves: log(L)+1
     * Full binary tree (0-2 children): leaf nodes = internal nodes + 1
     * Complete binary trees mean all levels are completely filled except, possibly, the last level that has all keys as left as possible.
     */
    class Node {
        int key;
        Node left, right;
        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }
    class BinaryTree{
        Node root;
        int size = 0;
        BinaryTree(int key){
            root = new Node(key);
            size++;
        }
        BinaryTree(){
            root = null;
        }
        public void add(int value) {
            root = addHelper(root, value);
            size++;
        }
        private Node addHelper(Node current, int value) {
            if (current == null) {
                return new Node(value);
            }
            if (value < current.key) {
                // will add as child if no child there
                current.left = addHelper(current.left, value);
            } else if (value > current.key) {
                current.right = addHelper(current.right, value);
            } else { // value is already in the tree
                return current;
            }
            return current;
        }
        public boolean contains(int value) {
            return (containsHelper(root, value) != null) ? true : false;
        }
        private Node containsHelper(Node current, int value) {
            if (current == null) {
                return null;
            }
            if (value == current.key) {
                return current;
            }
            return (value < current.key) ? containsHelper(current.left, value) :
                    containsHelper(current.right, value);
        }
        private Node deleteHelper(Node start, int value) {
            Node toDelete = containsHelper(start, value);
            if (toDelete != null) {
                // no children
                if(toDelete.left == null && toDelete.right == null) {
                    return null;
                }
                // if only one child, want it to replace the parent
                if(toDelete.right == null) {
                    return toDelete.left;
                }
                if (toDelete.left == null) {
                    return toDelete.right;
                }
                // if have two children, we have a decision to make as to whom will become the parent
                // using the smallest node of the branch is just finding the leftmost leaf
                toDelete.key = findLeftMost(toDelete.right).key;
                toDelete.right = deleteHelper(toDelete.right, toDelete.key);
                return toDelete;
            }
            return null;
        }
        public void delete(int value) {
            deleteHelper(root, value);
            size--;
        }
        private Node findLeftMost(Node root) {
            return (root.left == null) ? root : findLeftMost(root.left);
        }
        /* Depth-First Search ==================================================== */
        // Goes as deep as possible in every child before exploring its children
        /* in order: left subtree, then root, then right */
        public void traverseInOrder(Node node, ArrayList output) {
            if (node != null) {
                traverseInOrder(node.left, output);
                output.add(node.key);
                traverseInOrder(node.right, output);
            }
        }
        /* pre-order: root, then left, then right */
        public void traversePreOrder(Node node, ArrayList output) {
            if (node != null) {
                output.add(node.key);
                traversePreOrder(node.left, output);
                traversePreOrder(node.right, output);
            }
        }
        /* post-order: left, right, root */
        public void traversePostOrder(Node node, ArrayList output) {
            traversePostOrder(node.left, output);
            traversePostOrder(node.right, output);
            output.add(node.key);
        }
        /* Breadth First Search ========================================================== */
        // Visits all the nodes of a level before going to the next level
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
    void BinarySearchTreeType() {
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
    }
    /*
     * Finds all edges reachable from the node, in this case the root
     */
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
    /*
     * Finds shortest path from the root to all vertices
     */
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
    /*
     * Dijkstra's algorithm
     * Bellman-Ford
     * Floyd-Wheeler
     */
    /*
     * Binary Heap Tree
     * Either min-heap property, where the value of each node >= parent.value, with the min value at the root
     * OR max-heap property where the value of each child <= parent.value, with max(value) at the root
     *
     * Not sorted, only partially ordered
     *
     * Insert: O(log(n))
     * Delete: O(log(n))
     * Search: O(n)
     * Space: O(n)
     */
    public class BinaryArray {
        // Storing in an array in level-order traversal:
        // for the kth element, its left child is at 2*k+1, its right child at 2*k+2, its parent at k/2
        // the root is at 0.
        Object[] array;
//		public void insert(Comparable x) {
//			if(array.length )
//		}
    }
    void BinaryHeapType() {
    }
    void CartesianTreeType() {

    }
    void BTreeType() {

    }
    /*
     * Binary tree of O(log(n)) height (perfect binary tree) since # of black nodes on every root to leaf path is the same and there are no adjacent red nodes
     */
    void RedBlackTreeType() {

    }
    void SplayTreeType() {

    }
    /*
     * Binary tree wherethe O(log(n)) height is maintained by checking the difference between the heights of left and right subtrees = 1
     */
    void AVLTreeType() {

    }
    public static void main (String[] args) {
        //BinaryTree aspen = new BinaryTree();

    }
}
