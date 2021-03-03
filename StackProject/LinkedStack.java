package StackProject;
import java.util.EmptyStackException;

public class LinkedStack 
{
    private Node topNode; //references the fist node in the chain
    public LinkedStack()
    {
        topNode=null;
    } //end of default constructor


    private class Node
    {
        private T data;
        private Node next;
    } //end Node

    public void push(T newEntry)
    {
        Node newNode= new Node(newEntry, topNode);
        topNode = newNode;
    } // end push

    public T pop()
    {
        T top = peak(); // Might throw EmptyStackException
        // Assertion: topNode !=null
        topNode = topNode.getNextNode();

        return top;
    } //end pop

    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    } //end peak

    public boolean isEmpty()
    {
        return topNode == null;
    } // end isEmpty

    public void clear ()
    {
        topNode = null;
    } // end clear


} //end LinkedStack
