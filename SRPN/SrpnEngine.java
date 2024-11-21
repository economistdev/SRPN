

import java.math.BigInteger;
import java.util.List;

// Class to manage the SrpnStackEngine class and NumberCarousel class
public class SrpnEngine {

    public static List<String> validCommands = List.of("=", "+", "-", "/", "*", "^", "%", "c", "p", "r", "f", "i");

    private SrpnStackEngine mainEngine = new SrpnStackEngine();
    private SrpnStackEngine insertModeTempEngine;
    private NumberCarousel carousel = new NumberCarousel();

    private boolean insertMode = false;

    // Function to add element to SrpnStackEngine Instance
    public void addElement(String s) {
        mainEngine.addElement(s);
    }

    // Function for logic to execute any given command
    public void executeOperation(char c) {
        if (insertMode && (c != 'i')) {
            insertMode = !insertMode;
        }
        switch (c) {
            case '=':
                if (mainEngine.isStackEmpty()) {
                    System.out.println("Empty stack.");
                } else {
                    System.out.println(mainEngine.peek());
                }
                break;
            case '+':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    BigInteger resultBigInt = lastTwo.secondLast.add(lastTwo.last);
                    mainEngine.processCalculatedResult(resultBigInt);
                }
                break;
            case '-':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    BigInteger resultBigInt = lastTwo.secondLast.add(lastTwo.last.negate());
                    mainEngine.processCalculatedResult(resultBigInt);
                }
                break;
            case '/':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    BigInteger resultBigInt = lastTwo.secondLast.divide(lastTwo.last);
                    mainEngine.processCalculatedResult(resultBigInt);
                }
                break;
            case '*':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    BigInteger resultBigInt = lastTwo.secondLast.multiply(lastTwo.last);
                    mainEngine.processCalculatedResult(resultBigInt);
                }
                break;
            case '^':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    if (lastTwo.last.intValue() > 0 ) {
                        BigInteger resultBigInt = lastTwo.secondLast.pow(lastTwo.last.intValue());
                        mainEngine.processCalculatedResult(resultBigInt);
                    } else {
                        System.out.println("Cannot raise to a negative power.");
                    }
                }
                break;
            case '%':
                if (mainEngine.canPerformCalcs()) {
                    SrpnStackEngine.LastTwo lastTwo = mainEngine.getLastTwo();
                    BigInteger resultBigInt = lastTwo.secondLast.mod(lastTwo.last.abs());
                    mainEngine.processCalculatedResult(resultBigInt);
                }
                break;
            case 'c':
                mainEngine.clear();
                break;
            case 'p':
                mainEngine.printStack();
                break;
            case 'r':
                mainEngine.addElement(String.valueOf(carousel.getNextInt()));
                break;
            case 'f':
                mainEngine.reverse();
                break;
            case 'i':
                if (insertMode) {
                    mainEngine.insertion(insertModeTempEngine);
                    insertMode = !insertMode;
                } else {
                    insertModeTempEngine = mainEngine;
                    mainEngine = new SrpnStackEngine();
                    insertMode = !insertMode;
                }
                break;
            default:
                break;
        }
    }
}