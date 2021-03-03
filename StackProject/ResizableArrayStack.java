package StackProject;

import java.util.EmptyStackException;
import java.util.Arrays;

public final class ResizableArrayStack<T> implements StackInterface<T> 
{
    private T[] stack; //Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK =false;
    private static final int DEFAULT_CAPACITY=50;
    private static final int MAX_CAPACITY = 1000;

    public ResizableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    } //end default constructor

    public ResizableArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);

        //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack=(T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex=-1;
        integrityOK = true;
    }

    public void push(T newEntry) 
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex+1]=newEntry;
        topIndex++;
    } //end push

    private void ensureCapacity()
    {
        if (topIndex>=stack.length-1) //if array is full, double its size
        {
            int newLength = 2*stack.length;
            checkCapacity(newLength);
            stack=Arrays.copyOf(stack,newLength);
        } //end if
    } // end ensureCapacity

    public T pop() 
    {
        checkIntegrity();
        if(isEmpty())
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
        return topIndex<0;
    } //end isEmpty

    public void clear()
    {
        checkIntegrity();
        // Remove references to objects in the stack but do not deallocate the array
        while (topIndex>-1)
        {
            stack[topIndex]=null;
            topIndex--;
        } //end while
        //Assertion: topIndex is -1
    } //end clear
}
