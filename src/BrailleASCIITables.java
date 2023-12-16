import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BrailleASCIITables {
  /**
   * Converts an ASCII character to a string of bits representing the corresponding braille
   * character
   * 
   * @throws Exception
   * @throws FileNotFoundException
   */
  public static String toBraille(char letter) throws FileNotFoundException, Exception {
    // Create a braille tree that stores all translation info
    BitTree ASCIIToBrailleTree = new BitTree(8);
    File translationInfo = new File("ASCIIToBraille.txt");
    ASCIIToBrailleTree.load(new FileInputStream(translationInfo));

    // Get the binary string of input letter
    String letterBits = Integer.toBinaryString((int) letter);

    // return the braille value of input letter
    return ASCIIToBrailleTree.get(letterBits);
  } // toBraille(char)

  
  /**
   * Converts a string of bits representing a braille character to the corresponding ASCII 
   * character.
   * 
   * @throws Exception
   * @throws FileNotFoundException
   */
  public static String toASCII(String bits) throws FileNotFoundException, Exception {
    // Create a braille tree that stores all translation info
    BitTree brailleToASCIITree = new BitTree(6);
    File translationInfo = new File("BrailleToASCII.txt");
    brailleToASCIITree.load(new FileInputStream(translationInfo));

    // return the braille value of input letter
    return brailleToASCIITree.get(bits);
  } // toASCII(String)

  /**
   * Converts a string of bits representing a braille character to the corresponding 
   * Unicode braille character for those bits.
 * @throws Exception
   */
  public static String toUnicode(String bits) throws Exception {
    // Create a braille tree that stores all translation info
    BitTree brailleToUnicodeTree = new BitTree(6);
    File translationInfo = new File("BrailleToUnicode.txt");
    brailleToUnicodeTree.load(new FileInputStream(translationInfo));

    // return the braille value of input letter
    return brailleToUnicodeTree.get(bits);
  } // toUnicode(String)
} // class BrailleASCIITables



