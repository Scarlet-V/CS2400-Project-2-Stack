package StackProject;

public class LinkedStack<T> implements StackInterface<T> 
{
    private Node topNode; //references the fist node in the chain
    public LinkedStack()
    {
        topNode=null;
    } //end of default constructor


    class Node
    {
        T info;
        Node link;
        link=null;
     //end Node

    public Node(T info)
    {
        this.info = info;
        link = null;
    }

    public void setLink(Node link)
    {
        this.link = link;
    }
    //return the link
    public Node getLink()
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
    private Node top;

    public void push(T newEntry)
    {
        Node newNode=new Node(newEntry);
        newNode.setLink(top);
        topNode = newNode;
    } // end push

    public T pop() throws Exception
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

    class InfixToPostfix
{
   //method to return priority of a operator
   private int getPriority(char c)
   {
       switch(c)
       {
           case '(': return 0;
           case '/': case '*': return 2;
           case '+': case '-': return 1;
           default: return 999;
       }
   }
   //method to convert infix expression to postfix expression
   public String convertToPostfix(String infix) throws Exception
   {
       infix = infix + ")";
  
       //create an object of LinkedStack class
       LinkedStack<Character> stk = new LinkedStack<Character>();
      
       stk.push('(');
      
       String postfix = "";
      
       //convert from infix to postfix
       for(int i=0; i<infix.length(); i++)
       {
           char ch = infix.charAt(i);
          
           //check for variable
           if(Character.isLetter(ch))
           {
               postfix = postfix + ch + " ";
           }
           //check for left parenthesis
           else if(ch=='(')
           {
               stk.push(ch);
           }
           //check for righr parenthesis
           else if(ch==')')
           {
               while(stk.peek()!='(')
               {
                   postfix = postfix + stk.peek() + " ";
                   stk.pop();
               }
               stk.pop();
           }
           //operator
           else
           {
               int p1 = getPriority(ch);
               int p2 = getPriority(stk.peek());
              
               while(p1<=p2)
               {
                   postfix = postfix + stk.peek() + " ";
                   stk.pop();
                   p2 = getPriority(stk.peek());
               }
               stk.push(ch);
           }
       }
      
       //check for error  
       if(!stk.isEmpty())
           System.out.println("Invalid Expression!");
  
       //return postfix expression
       return postfix;
   }

} 
}//end LinkedStack
