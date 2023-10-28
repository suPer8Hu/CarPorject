
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

  public static class RBTNode<T> extends Node<T> {
    public int blackHeight = 0;

    public RBTNode(T data) {
      super(data);
    }

    public RBTNode<T> getUp() {
      return (RBTNode<T>) this.up;
    }

    public RBTNode<T> getDownLeft() {
      return (RBTNode<T>) this.down[0];
    }

    public RBTNode<T> getDownRight() {
      return (RBTNode<T>) this.down[1];
    }
  }

  private void enforceRBTreePropertiesAfterInsert(RBTNode<T> node) {
    RBTNode<T> parent = node.getUp();

    if (parent == null) {
      return;
    }

    if (parent.blackHeight == 1) {
      return;
    }

    RBTNode<T> grandparent = parent.getUp();

    if (grandparent == null) {
      parent.blackHeight = 1;
      return;
    }

    RBTNode<T> uncle = getUncle(parent);

    if (uncle != null && uncle.blackHeight == 0) {
      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
      uncle.blackHeight = 1;

      enforceRBTreePropertiesAfterInsert(grandparent);
    }

    else if (parent == grandparent.getDownLeft()) {
      if (node == parent.getDownRight()) {
        rotate(node, parent);
        parent = node;
      }

      rotate(parent, grandparent);

      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
    }

    else {
      if (node == parent.getDownLeft()) {
        rotate(node, parent);
        parent = node;
      }

      rotate(parent, grandparent);

      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
    }
  }

  private RBTNode<T> getUncle(RBTNode<T> parent) {
    RBTNode<T> grandparent = parent.getUp();
    if (grandparent.getDownLeft() == parent) {
      return grandparent.getDownRight();
    } else if (grandparent.getDownRight() == parent) {
      return grandparent.getDownLeft();
    } else {
      throw new IllegalStateException("Parent is not a child of its grandparent");
    }
  }


  @Override
  public boolean insert(T data) throws NullPointerException {
    // TODO
    RBTNode<T> newNode = new RBTNode<T>(data);
    if (!insertHelper(newNode)) {
      return false;
    }

    enforceRBTreePropertiesAfterInsert(newNode);

    RBTNode<T> tmpRootNode = (RBTNode<T>) root;
    tmpRootNode.blackHeight = 1;

    return true;
  }


  /**
   * After inserting node to a empty tree, root should be black
   */
  @Test
  public void testInsertIntoEmptyTree() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();

    tree.insert(10);

    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
  }


  /**
   * After inserting 15 to:
   * 
   * 20 / 10
   * 
   * Should give: 10 / \ 15 20
   */
  @Test
  public void testInsertAsRightChild() {
    RBTNode<Integer> root = new RBTNode<>(20);
    root.blackHeight = 1;
    root.down[0] = new RBTNode<>(10);
    root.down[0].up = root;
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.root = root;
    tree.size = 2;

    // Perform insertion that triggers a left rotation
    tree.insert(15);

    // Verify the tree structure and properties
    Assertions.assertEquals("[ 15, 10, 20 ]", tree.toLevelOrderString());
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownLeft().blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownRight().blackHeight);
  }


  /**
   * After inserting 5 to:
   * 
   * 20 / 10
   * 
   * Should give: 10 / \ 5 20
   */
  @Test
  public void testInsertAsLeftChild() {
    RBTNode<Integer> root = new RBTNode<>(20);
    root.blackHeight = 1;
    root.down[0] = new RBTNode<>(10);
    root.down[0].up = root;
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.root = root;
    tree.size = 2;

    // Perform insertion that triggers a recursion in fix-up
    tree.insert(5);

    // Verify the tree structure and properties
    Assertions.assertEquals("[ 10, 5, 20 ]", tree.toLevelOrderString());
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownLeft().blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownRight().blackHeight);
  }
}

