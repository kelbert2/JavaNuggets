public class TreeNode extends A_Node {
    private Node left;
    private Node right;
    private Object value;
    TreeNode(Object item){
        TreeNode(item, null, null);
    }
    TreeNode(Object item, Node leftChild, Node rightChild){
        value = item;
        left = leftChild;
        right = rightChild;
    }
}
