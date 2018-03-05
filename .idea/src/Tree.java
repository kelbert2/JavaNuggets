import java.util.List;

public abstract class Tree implements I_Tree {
    private Node root;
    public List traverseInOrder();
    public List traverseInOrder(Node start);
    public List traversePreOrder();
    public List traversePreOrder(Node start);
    public List traversePostOrder();
    public List traversePostOrder(Node start);
    public List traverseLevelOrder();
    public List traverseLevelOrder(Node start);
}
