import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BitTree {
  // +--------+-----------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree
   */
  BitTreeNode root;

  /**
   * The number of levels in the tree
   */
  int size;

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+
  public BitTree(int n) {
    this.size = n;
    BitTreeNode temp = new BitTreeNode(null, null);
    for (int i = 0; i < n; i++) {
      this.root.left = new BitTreeNode(temp, temp);
      this.root.right = new BitTreeNode(temp, temp);
      temp = root;
    } // for
  } // BitTree(int)

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Adds or replaces the value at the end of the path specified by the 
   *  given bits with input value.  
   */
  public void set(String bits, String value) throws Exception {
    byte[] bit = bits.getBytes();
    // Create a new leaf using the given value.
    BitTreeNode.BitTreeLeaf leaf = new BitTreeNode.BitTreeLeaf(value);
    // Set the value at the end as the input value.      
    setHelper(root, bit, 0, leaf);
  } // set(String, String)

  public void setHelper(BitTreeNode node, byte[] bit, int i, BitTreeNode.BitTreeLeaf leaf) {
    if (node == null) {
      node = leaf;
      return;
    } // if

    // Follow the path of given bits to find the correct position of leaf
    if (bit[i] == 0) {
      setHelper(node.left, bit, i++, leaf);
    } else if (bit[i] == 1) {
      setHelper(node.right, bit, i++, leaf);
    } // if
  } // setHelper


  /*
   * Return the value at the end of the path specified by the given bits.
   */
  public String get(String bits) {
    byte[] bit = bits.getBytes();
    BitTreeNode current = root;

    for (int i = 0; i < bit.length; i++) {
      if (bit[i] == 0) {
        current = current.left;
      } else if (bit[i] == 1) {
        current = current.right;
      } // if
    } // for
    return ((BitTreeNode.BitTreeLeaf)current).value;
  } // get(String, String)

  /**
   * Print out the contents of the tree in CSV format.
   */
  public void dump(PrintWriter pen) {
    dumpHelper(pen, "", root);
  } //dump(PrintWriter)

  public void dumpHelper(PrintWriter pen, String bits, BitTreeNode node) {
    String value;
    if (node == null) {
      return;
    } // if

    if (node.right == null && node.left == null) {
      if (bits.length() == this.size) {
        value = get(bits);
        pen.println(bits + ", " + value);
      } // if
      bits = "";
      return;
    } // if
     
    if (node.left != null ) {
      dumpHelper(pen, bits + "0", node.left);
    } // if
    
    if (node.right != null) {
      dumpHelper(pen, bits + "1", node.right);
    } // if
  } // dumpHelper(PrintWriter, String, BitTreeNode)

  /**
   * Read a series of lines of the form bits, value and stores them in the tree.
   * @throws Exception
   */
  public void load(InputStream source) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(source));
    while(reader.ready()) {
      String line = reader.readLine();
      set(line.substring(0, size), line.substring(size));
    } // while
  } // load(InputStream)

} // class BitTree
