

import java.util.Stack;
import java.math.BigInteger;

 // Class to execute lowest level operations of the SRPN calculator
public class SrpnStackEngine {

    private Stack<Integer> calcStack = new Stack<>();
    
    private int MAX_STACK_SIZE = 21;
    private int MIN_STACK_SIZE_CALCS = 2;

    private BigInteger maxBigInt = new BigInteger(String.valueOf(Integer.MAX_VALUE));
    private BigInteger minBigInt = new BigInteger(String.valueOf(Integer.MIN_VALUE));

    public boolean canPerformCalcs() {
        boolean check = (calcStack.size() >= MIN_STACK_SIZE_CALCS);
        if (!check) {
            System.out.println("Not enough elements on stack.");
        }
        return check;
    }

    public boolean isStackEmpty() {
        return (calcStack.size() == 0);
    }

    public Stack<Integer> giveIterable() {
        // Function creates a copy of the stack for ease of iteration without risk of unwanted overwrites.
        Stack<Integer> safeCopy = new Stack<>();
        safeCopy.addAll(calcStack);
        return safeCopy;
    }

    public Integer peek() {
        return calcStack.peek();
    }

    public Integer pop() {
        return calcStack.pop();
    }

    public void push(int i) {
        calcStack.push(i);
    }

    public void printStack() {
        for (Integer i : calcStack) {
            System.out.println(i);
        }
    }

    // Function to perform the insertion logic on the stack objects
    public void insertion(SrpnStackEngine stack) {
        this.reverse();
        for (Integer i : stack.giveIterable()) {
            calcStack.push(i);
        }
    }

    public void clear() {
        calcStack.removeAllElements();
    }

    public void addElement(String s) { //generic adding an element to the stack
        if (calcStack.size() == MAX_STACK_SIZE) {
            System.out.println("Stack full.");
            return;
        }
        calcStack.push(processInt(new BigInteger(s)));
    }

    // Function to reverse stack order
    public void reverse() {
        int original_size = calcStack.size();
        int[] arr = new int[original_size];
        for (int i=0; i<original_size; i++) {
            arr[i] = calcStack.pop();
        }
        for (int i=0; i<original_size; i++) {
            calcStack.push(arr[i]);
        }
    }

    // Function for validation for BigInteger passed as value
    private int processInt(BigInteger val) {
        int result = 0;
        if (val.compareTo(minBigInt) < 0) {
            result = Integer.MIN_VALUE;
        } else if (val.compareTo(maxBigInt) > 0) {
            result = Integer.MAX_VALUE;
        } else {
            result = val.intValue();
        }
        return result;
    }

    // Function to get remove the last two elements from the stack and return them in the LastTwo class
    public LastTwo getLastTwo() {
        LastTwo lastTwo = new LastTwo();
        lastTwo.last = new BigInteger(String.valueOf(calcStack.get(calcStack.size()-1)));
        lastTwo.secondLast = new BigInteger(String.valueOf(calcStack.get(calcStack.size()-2)));
        return lastTwo;
    }

    // Each calculated result will have this logic
    public void processCalculatedResult(BigInteger BigInt) {
        int result = processInt(BigInt);
        calcStack.pop();
        calcStack.pop();
        calcStack.push(result);
    }

    // Class to act as only a data structure
    public static class LastTwo {
        public BigInteger last;
        public BigInteger secondLast;
    }

}