public class Node extends A_Node{
    private Object value;
    private Node next;
    Node(Object item){
        value = item;
        next = null;
    }
    Node(Object item, Node nextNode){
        value = item;
        next = nextNode;
    }
    public Node next() {
        return next;
    }
}
