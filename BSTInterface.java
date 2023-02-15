import java.util.*;

/**
 * This BSTInterface defines an interface that implements a binary search tree (BST)
 * 
 * @author  Tom Bredemeier
 * @version January 31, 2013
 */

public interface BSTInterface<T extends Comparable<T>>
{
    public enum Traversal {inOrder, preOrder, postOrder};
    
    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty();

    // Returns the number of elements in this BST.
    public int size();

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element);

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element);

    // Returns a graphical representation of the tree
    public String toString();

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse();

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse();

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse();

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element);
    
    // Restructures this BST to be optimally balanced
    public void balance();

}