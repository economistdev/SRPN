
/**
 * Program class for an SRPN calculator. Current it outputs "0" for every "=" sign.
 */

public class SRPN {
  
  public void processCommand(String s) {
    char c = s.charAt(s.length()-1);
    if (c == '=') {
      System.out.println(0);
    }
  }
  
}
