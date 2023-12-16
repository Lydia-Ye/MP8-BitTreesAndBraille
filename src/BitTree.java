import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * A simple implementation of Bit tree that stores mappings from bits to values.
 * 
 * @author Lydia Ye
 * @version November 2023
 */

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

  /**
   * Builds a tree that will store mappings from strings of n bits to strings.
   * 
   * @param n
   */
  public BitTree(int n) {
    // we'll need n+1 levels to create a bit tree for n bits
    this.size = n + 1;
    this.root = new BitTreeNode(null, null);

    // for (int i = 0; i < n; i++) {
    //   this.root.left = new BitTreeNode(temp, temp);
    //   this.root.right = new BitTreeNode(temp, temp);
    //   temp = root;
    // } // for
  } // BitTree(int)

  // +---------+----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Adds or replaces the value at the end of the path specified by the 
   *  given bits with input value.  
   */
  public void set(String bits, String value) throws Exception {
    int bitsLength = bits.length();

    // Check if bits have an appropriate length
    if (bitsLength != this.size - 1) {
      throw new Exception("Inappropriate bits length!");
    } // if

    // Create a new leaf using the given value.
    BitTreeNode leaf = new BitTreeNode.BitTreeLeaf(value);

    // Set the value at the end as the input value.      
    setHelper(root, bits, 0, leaf);
  } // set(String, String)


  /**
   * A helper function for set
   */
  public void setHelper(BitTreeNode node, String bits, int level, BitTreeNode leaf) throws Exception {
    // Base case: we reach the lowest level of the tree
    if (level == this.size) {
      node = leaf; // set the node to the leaf with input value
      return;
    } // if

    // Follow the path of given bits to find the correct position of leaf
    if (bits.charAt(level) == 0) { // go to left child
      if(node.left == null) {
        // create a new node for left child
        node.left = new BitTreeNode(null, null);
      } // if
      setHelper(node.left, bits, level++, leaf);
    } else if (bits.charAt(level) == 1) {  // go to right child
      if(node.right == null) {
        // create a new node for right child
        node.right = new BitTreeNode(null, null);
      } // if
      setHelper(node.right, bits, level++, leaf);
    } else {
      throw new Exception("Invalid bit value: a bit should be either 0 or 1.");
    } // if...else
  } // setHelper


  /*
   * Return the value at the end of the path specified by the given bits.
   */
  public String get(String bits) throws Exception {
    int bitsLength = bits.length();
    // Check if bits have an appropriate length
    if (bitsLength != this.size - 1) {
      throw new Exception("Inappropriate bits length!");
    } // if

    BitTreeNode current = root;

    for (int i = 0; i < bitsLength; i++) {
      if (bits.charAt(i) == 0) { // go to the left childe
        if(current.left == null) {
          // if there's no left child, the path is invalid
          throw new Exception("Invalid path!");
        } // if
        current = current.left;
      } else if (bits.charAt(i) == 0) {  // go to the right child
        if(current.right == null) { 
          // if there's no right child, the path is invalid
          throw new Exception("Invalid path!");
        } // if
        current = current.right;
      } // if
    } // for

    // return the value at the end of the path
    return ((BitTreeNode.BitTreeLeaf)current).value;
  } // get(String, String)


  /**
   * Print out the contents of the tree in CSV format.
   */
  public void dump(PrintWriter pen) {
    String csvContent = "";
    dumpHelper(pen, csvContent, 0, this.root);
  } //dump(PrintWriter)

  /**
   * A helper function for dump.
   */
  public void dumpHelper(PrintWriter pen, String csvContent, int level, BitTreeNode node) {

    // Base case: we reach the lowest level of the tree
    if (level == this.size) {
      csvContent = csvContent + "," + ((BitTreeNode.BitTreeLeaf)node).value;
      // print one row of braille tree
      pen.println((csvContent + "\n"));
      return;
    } // if

    if (node.left != null ) {
      // go to left child and add 0 to the csvContent
      csvContent += "0";
      dumpHelper(pen, csvContent, level + 1, node.left);
    } // if
    
    if (node.right != null ) {
      // go to right child and add 0 to the csvContent
      csvContent += "1";
      dumpHelper(pen, csvContent, level + 1, node.right);
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
      // set the tree using the path and value provided in each line of input
      set(line.substring(0, this.size - 1), line.substring(size - 1));
    } // while
  } // load(InputStream)
} // class BitTree
