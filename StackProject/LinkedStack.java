package StackProject;

public class LinkedStack<T> implements StackInterface<T> 
{
    
    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    } //end Node

    // reference to the top of this stack
    private Node topNode;
    
    public LinkedStack()
    {
        topNode=null;
    } //end of default constructor

    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;

    } // end push

    public T pop() throws Exception
    {
        T top = peek();
        assert(topNode != null);       
        if (isEmpty())
            throw new Exception("Pop attempted on an empty stack.");
        else
        topNode = topNode.getNextNode();
        return top;
    } //end pop

    public T peek() throws Exception
    {
        if (isEmpty())
            throw new Exception("Pop attempted on an empty stack.");
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

} //end LinkedStack
