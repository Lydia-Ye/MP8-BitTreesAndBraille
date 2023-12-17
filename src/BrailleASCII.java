import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * A utility program that translates the source characters to the target character set 
 * according to command line argument.
 * 
 * @author Lydia Ye
 * @version December 2023
 */
public class BrailleASCII {
  public static void main(String[] args) throws FileNotFoundException, Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Check if the number of command line arguments are valid
    if (args.length != 2) {        
      System.err.println("Invalid number of command line argument");
      System.exit(1);
    } // if

    String targetChSet = args[0];
    String sourceCh = args[1];
    String result = "";

    switch (targetChSet) {
      case "braille":
        for (int i = 0; i < sourceCh.length(); i++) {
          result += BrailleASCIITables.toBraille(sourceCh.charAt(i));
        } // for
        break;
      case "ascii":
        for (int i = 0; i <= (sourceCh.length() - 6); i += 6) {
          result += BrailleASCIITables.toASCII(sourceCh.substring(i, i + 6));
        } // for
        break;
      case "unicode":
        for (int i = 0; i < sourceCh.length(); i++) {
          String letterBits = Integer.toBinaryString((int) sourceCh.charAt(i));
          result += BrailleASCIITables.toUnicode(letterBits);
        } // for
        break;
    } // switch

    pen.println(result);
    return;
  } // main(String[])
} // class brailleASCII


