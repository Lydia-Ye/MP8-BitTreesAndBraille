/**
 * Nodes in a Bit Tree
 */
public class BitTreeNode {
  // +--------+-----------------------------------------------------------
  // | Fields |
  // +--------+

  /*
   * The left subtree, representing bit 0
   */
  BitTreeNode left;

  /*
   * The right subtree, representing bit 1
   */
  BitTreeNode right;

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Creates a new bit tree node
   */
  public BitTreeNode(BitTreeNode left, BitTreeNode right) {
    this.left = left;
    this.right = right;
  } // BitTreeNode(BitTreeNode, BitTreeNode)

  // +---------------+----------------------------------------------------
  // | Inner Classes |
  // +---------------+

  /**
   * Leaves in the bit tree
   */
  public static class BitTreeLeaf extends BitTreeNode{
    // +--------+-----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The value stored in the leaf.
     */
    String value;

    // +--------------+-----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new leaf
     */
    public BitTreeLeaf(String val) {
      // set both children of the leaf node to null
      super(null, null);
      this.value = val;
    } // BitTreeLeaf(String)
  } // class BitTreeLeaf
} // class BitTreeNde
