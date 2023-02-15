import java.util.*;
/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST
    protected Comparator<T> comp;
    protected boolean found;
    
    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        root = null;
        comp = new Comparator<T>(){
            public int compare(T element, T element2){
                return((Comparable)element).compareTo(element2);
            }
        };
    }
    BinarySearchTree(Comparator<T> comp){
        root = null;
        this.comp = comp;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return (root == null);
    }

    // Returns the number of elements in this BST.

    public int size()
    {
        return size(root);
    }

    // Returns the number of elements in tree.

    private int size(BinaryNode<T> tree)
    {
        if(tree == null){
            return 0;
        }
        else{
            return size(tree.getLeft()) + size(tree.getRight()) +1;
        }
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        root = recAdd(element, root);
    }
    
    private BinaryNode<T> recAdd(T element, BinaryNode<T> node){
        if(node == null){
            node = new BinaryNode<T>(element);
        }
        else if (comp.compare(element, node.getInfo()) <= 0){
                node.setLeft(recAdd(element, node.getLeft()));
        }
        else{
            node.setRight(recAdd(element, node.getRight()));
        }
        return node;
    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        return recContains(element, root);
    }
    private boolean recContains(T target, BinaryNode<T> node){
        if(node == null){
            return false;
        }
        else if(comp.compare(target, node.getInfo()) < 0){
            return recContains(target, node.getLeft());
        }
        else if(comp.compare(target, node.getInfo()) > 0){
            return recContains(target, node.getRight());
        }
        else{
            return true;
        }
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    //getIterator allows us to use a queue for the traversals
    public Iterator<T> getIterator(BSTInterface.Traversal orderType){
        final Queue<T> infoQueue = new PriorityQueue<T>();
        if(orderType == BSTInterface.Traversal.preOrder){
            preorderTraverse(root, infoQueue);
        }
        else if(orderType == BSTInterface.Traversal.inOrder){
            inorderTraverse(root, infoQueue);
        }
        else if(orderType == BSTInterface.Traversal.postOrder){
            postorderTraverse(root, infoQueue);
        }
        return new Iterator<T>(){
            public boolean hasNext(){
                return !infoQueue.isEmpty();
            }
            public T next(){
                if (!hasNext()){
                    throw new IndexOutOfBoundsException("illegal invocation of next " + " in BinarySearchTree iterator.n");
                }
                return infoQueue.remove();
            }
            public void remove(){
                throw new UnsupportedOperationException("Unsupported remove attempted " + "on BinarySearchTree iterator.n");
            }
        };
    }
    
    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        final LinkedList<T> infoQueue = new LinkedList<T>();
        preorderTraverse(root, infoQueue);
        return infoQueue;
    }
    private void preorderTraverse(BinaryNode<T> node, Queue<T> q)
    {
        if(node != null){
            q.add(node.getInfo());
            preorderTraverse(node.getLeft(), q);
            preorderTraverse(node.getRight(), q);
        }
    }
    
    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        final LinkedList<T> infoQueue = new LinkedList<T>();
        inorderTraverse(root, infoQueue);
        return infoQueue;
    }
    private void inorderTraverse(BinaryNode<T> node, Queue<T> q)
    {
        if(node != null){
            inorderTraverse(node.getLeft(), q);
            q.add(node.getInfo());
            inorderTraverse(node.getRight(), q);
        }
    }
    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        final LinkedList<T> infoQueue = new LinkedList<T>();
        postorderTraverse(root, infoQueue);
        return infoQueue;
    }
    private void postorderTraverse(BinaryNode<T> node, Queue<T> q)
    {
        if(node != null){
            postorderTraverse(node.getLeft(), q);
            postorderTraverse(node.getRight(), q);
            q.add(node.getInfo());
        }
    }

    
    
    
    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        root = recRemove(element, root);
    }
    private BinaryNode<T> recRemove(T target, BinaryNode<T> node){
        if(node == null){
            found = false;
        }
        else if(comp.compare(target, node.getInfo()) < 0){
            node.setLeft(recRemove(target, node.getLeft()));
        }
        else if(comp.compare(target, node.getInfo()) > 0){
            node.setRight(recRemove(target, node.getRight()));
        }
        else{
            node = removeNode(node);
            found = true;
        }
        return node;
    }
    private BinaryNode<T> removeNode(BinaryNode<T> node){
        T data;
        if(node.getLeft() == null){
            return node.getRight();
        }
        else if(node.getRight() == null){
            return node.getLeft();
        }
        else{ 
            data = getPredecessor(node.getLeft());
            node.setInfo(data);
            node.setLeft(recRemove(data, node.getLeft()));
            return node;
        }
    }
    private T getPredecessor (BinaryNode<T> subtree){
        BinaryNode<T> temp = subtree;
        while(temp.getRight() != null){
            temp = temp.getRight();
        }
        return temp.getInfo();
    }

    
    
    // Restructures this BST to be optimally balanced
    public void balance()
    {
        List<T> list = inorderTraverse();
        root = null;
        refillTree(0, list.size() - 1, list);
    }
    public void refillTree(int low, int high, List<T> list){
        int mid = (low + high)/2;
        if(low == high){
            add(list.get(low));
        }
        else if((low + 1) == high){
            add(list.get(low));
            add(list.get(high));
        }
        else{
            add(list.get(mid));
            refillTree(low, mid - 1, list);
            refillTree(mid + 1, high, list);
        }
    }
    }