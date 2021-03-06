package StackProject;

import java.util.EmptyStackException;
import java.util.Arrays;

public final class ResizableArrayStack<T> implements StackInterface<T> 
{
    private T[] stack; //Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 1000;

    /**Creates an empty resizable array stack with a capacity of 50. */
    public ResizableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    } //end default constructor

    /** Creates an empty resizable array stack with the designated capacity.
     * @param initialCapacity Initial capacity of the stack. */
    public ResizableArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);

        //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    /** Throws IllegalStateException when trying to create a stack greater than 1000.
     * @param capacity Capacity of the stack. */
    private void checkCapacity(int capacity)
    {
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack whose"+"capacity exceeds allowed"+"maximum of"+MAX_CAPACITY);
    }

    /** Checks to see if the stack is corrupt. */
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ResizableArrayStack object is corrupt.");
    }   

    public void push(T newEntry) 
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push

    /** Checks to see if array is full, calls doubleCapacity if it is. */
    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1) //if array is full, double its size
        {
            doubleCapacity();
        } //end if
    } // end ensureCapacity

    /** Doubles the capacity of the stack if full up to 1000.
     * If trying to push into a max_capacity stack throws
     * IllegalStateException. */
    private void doubleCapacity()
    {
        if (stack.length == MAX_CAPACITY) {
            checkCapacity(stack.length + 1);
        }
        int newLength = 2 * stack.length;
        if (newLength > MAX_CAPACITY) {
            newLength = MAX_CAPACITY;
        }
        stack = Arrays.copyOf(stack, newLength);
    }

    public T pop() 
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } //end if
    } //end pop

    public T peek() 
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } // end peak

    public boolean isEmpty()
    {
        return topIndex < 0;
    } //end isEmpty

    public void clear()
    {
        checkIntegrity();
        // Remove references to objects in the stack but do not deallocate the array
        while (topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        } //end while
        //Assertion: topIndex is -1
    } //end clear

    /** Finds the priority of a character in the stack,
     * @param c Character passed from the infix.
     * @return Priority of the character in the stack. */
    private int getPriority(char c) {
       switch(c) {
           case '(': case ')' : return 0;
           case '/': case '*': return 2;
           case '+': case '-': return 1;
           default: return 999;
       }
   }

   /** Converts an equation of variable in infix form to postfix form.
    * @param infix An infix equation to be converted.
    * @return A converted infix equation to postfix.
    * @throws Exception If popping/peeking an empty stack. */
    public String convertToPostfix(String infix) throws Exception {
        StackInterface<Character> operatorStack = new ResizableArrayStack<>();
        String postfix = "";
        int index = 0;

        while (infix.length() != index) {
            char nextCharacter = infix.charAt(index);
            if (Character.isLetter(nextCharacter)) {
                postfix = postfix.concat(Character.toString(nextCharacter));
                index++;
            }
            else {
                switch (nextCharacter) {
                
                case '+' : case '-' : case '*' : case '/' :
                    while (!operatorStack.isEmpty() && (getPriority(nextCharacter) <= getPriority(operatorStack.peek()))) {
                        postfix = postfix.concat(operatorStack.peek().toString());
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    index++;
                    break;

                case '(' :
                    operatorStack.push(nextCharacter);
                    index++;
                    break;

                case ')' :
                    char topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix = postfix.concat(Character.toString(topOperator));
                        topOperator = operatorStack.pop();
                        index++;
                    }
                    break;

                default: 
                    index++;
                    break;
                }
            }   
        }

        while (!operatorStack.isEmpty()) {
            char topOperator = operatorStack.pop();
            postfix = postfix.concat(Character.toString(topOperator));
        }
        return postfix;
    }
  
    /** Evaluates an equation of numbers in postfix form.
     * (Must have spaces between numbers and operators).
     * @param postfix A postfix equation to be evaluated.
     * @return The value of the postfix equation.
     * @throws Exception If popping/peeking an empty stack. */
    public int evaluatePostfix(String postfix) throws Exception {
        StackInterface<Integer> valueStack = new ResizableArrayStack<>();
        String [] tokens = postfix.split("[ ]");
        int index = 0;
        while (tokens.length != index) {
            String nextCharacter = tokens[index];
            if (isNumber(nextCharacter)) {
                int value = Integer.parseInt(nextCharacter);
                valueStack.push(value);
                index++;
            }
            else {
                switch (nextCharacter) {

                    case "+" : 
                        int operandTwo = valueStack.pop();
                        int operandOne = valueStack.pop();
                        int result = (operandOne + operandTwo);
                        valueStack.push(result);
                        index++;
                        break;
    
                    case "-" : 
                        int secondOperand = valueStack.pop();
                        int firstOperand = valueStack.pop();
                        int result1 = (firstOperand - secondOperand);
                        valueStack.push(result1);
                        index++;
                        break;
    
                    case "*" : 
                        int operand2 = valueStack.pop();
                        int operand1 = valueStack.pop();
                        int result2 = (operand1 * operand2);
                        valueStack.push(result2);
                        index++;
                        break;
    
                    case "/" : 
                        int operandSecond = valueStack.pop();
                        int operandFirst = valueStack.pop();
                        int result3 = (operandFirst / operandSecond);
                        valueStack.push(result3);
                        index++;
                        break;
    
                    case "^" :
                        int operandSecondNumber = valueStack.pop();
                        int operandFirstNumber = valueStack.pop();
                        int result4 = (int)Math.pow(operandFirstNumber, operandSecondNumber);
                        valueStack.push(result4);
                        index++;
                        break;
    
                    default :
                        index++;
                        break;
                }
            } 
        }
        return valueStack.peek();
    }

    /** Method that checks if a string is a number.
     * @param nextCharacter String to be checked.
     * @return True if it is a number, false if not. */
    private boolean isNumber(String nextCharacter) {
        if (nextCharacter == null) {
            return false;
        }

        try {
            int number = Integer.parseInt(nextCharacter);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

}
