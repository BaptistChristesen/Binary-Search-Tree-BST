/**
 * This BinaryNode object defines an implementation of Comparable nodes for a binary search tree (BST)
 * 
 * @author  Tom Bredemeier
 * @version January 31, 2013
 */

public class BinaryNode<T extends Comparable<T>>
{
  // Used to hold references to binary nodes for the linked implementation
  private T info;                   // The info in a binary node
  private BinaryNode<T> left;       // A link to the left child node
  private BinaryNode<T> right;      // A link to the right child node

  public BinaryNode(T info)
  {
    this.info = info;
    left = null;
    right = null;
  }
 
  // Sets info of this BinaryNode
  public void setInfo(T info)
  {
    this.info = info;
  }

  // Returns the info of the BinaryNode
  public T getInfo()
  {
    return info;
  }
 
  // Sets left link of this BinaryNode
  public void setLeft(BinaryNode<T> link)
  {
    left = link;
  }

  // Sets right link of this BinaryNode
  public void setRight(BinaryNode<T> link)
  {
    right = link;
  }

  // Returns left link of this BinaryNode
  public BinaryNode<T> getLeft()
  {
    return left;
  }

  // Returns right link of this BinaryNode
  public BinaryNode<T> getRight()
  {
    return right;
  }
}
 
 