package StackProject;

public class LinkedStack<T> implements StackInterface<T> 
{
    private Node topNode; //references the fist node in the chain
    public LinkedStack()
    {
        topNode=null;
    } //end of default constructor


    class Node<T>
    {
        T info;
        Node<T>link;
        link=null;
     //end Node

    public Node(T info)
    {
        this.info = info;
        link = null;
    }

    public void setLink(Node<T> link)
    {
        this.link = link;
    }
    //return the link
    public Node<T> getLink()
    {
        return link;
    }
    //return the info
    public T getInfo()
    {
        return info;
    }
    }
    // reference to the top of this stack
    private Node<T> top;

    public void push(T newEntry)
    {
        Node<T>newNode=new Node<>(newEntry);
        newNode.setLink(top);
        topNode = newNode;
    } // end push

    public void pop() throws Exception
    {
        if (isEmpty())
            throw new Exception("Pop attempted on an empty stack.");
       else
        top = top.getLink();
    } //end pop

    public T peek() throws Exception
    {
        if (isEmpty())
            throw new Exception("Pop attempted on an empty stack.");
        else
            return top.getInfo();
    } //end peak

    public boolean isEmpty()
    {
        return top == null;
    } // end isEmpty

    public boolean isFull()
   {
       return false;
   }

    public void clear ()
    {
        topNode = null;
    } // end clear


} //end LinkedStack
