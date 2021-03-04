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
    private int numberOfEntries=0;
    private Node firstNode;

    public ResizableArrayStack()
    {
        this(DEFAULT_CAPACITY);
        firstNode = null;
        integrityOK=false;
        @SuppressWarnings("unchecked")
            T[] tempStack = (T[])new Object[MAX_CAPACITY]; 
            stack = tempStack;
            integrityOK = true;
    } //end default constructor

    public ResizableArrayStack(int initialCapacity)
    {
        firstNode = null;
        integrityOK = false;
        checkCapacity(initialCapacity);

        //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack=(T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex=-1;
        integrityOK = true;
    }

    private void checkCapacity(int capacity)
    {
        if(capacity>MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose"+"capacity exceeds allowed"+"maximum of"+MAX_CAPACITY);
    }

    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ResizableArray object is corrupt.");
    }

    private void doubleCapacity()
    {
        int newLength=2*stack.length;
        checkCapacity(newLength);
        stack=Arrays.copyOf(stack, newLength);
    }

    public int getCurrentSize() 
    {
        return numberOfEntries;
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

    private int getIndexOf(T anEntry)
    {
        int where =-1;
        boolean found = false;
        int index = 0;

        while (!found &&(index<numberOfEntries))
        {
            if (anEntry.equals(stack[index]))
            {
                found=true;
                where=index;
            }
            index++;
        }
        return where;
    }

    public boolean contains(T anEntry) 
    {
        boolean found = false;
		Node currentNode=firstNode;

		while (!found && (currentNode !=null))
		{
			if (anEntry.equals(currentNode.data))
				found = true;
				else
					currentNode =currentNode.next;
		}
		return found;
    }

    public T[] toArray() {
        T[] copy = (T[])new Object[numberOfEntries];
          for (int i = 0; i < this.numberOfEntries; i++) {
              if(stack[i]!=null){
                  copy[i] = stack[i];
              }
          }
          return copy;
      }
 

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

    public boolean isArrayFull()
    {
        return numberOfEntries==bag.length;
    }

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
