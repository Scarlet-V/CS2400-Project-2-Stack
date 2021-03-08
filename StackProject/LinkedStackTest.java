package StackProject;
import org.junit. *;
import static org.junit.Assert. *;

public class LinkedStackTest 
{
    StackInterface<String> s1 = new LinkedStack<String>();
    {
        s1.push("a");
        s1.push("*");
        s1.push("b");
        s1.push("/");
        s1.push("(");
        s1.push("c");
        s1.push("-");
        s1.push("a");
        s1.push(")");
        s1.push("+");
        s1.push("d");
        s1.push("*");
        s1.push("e");
    }
    @Test
         
        public void testpop() throws Exception
        {
            
            assertEquals("e",(s1.pop()));
        }

     @Test           
        public void testpeek() throws Exception
        {
            assertEquals("*",(s1.peek()));
        }

    @Test
        public void push() throws Exception
        {
            System.out.println("push");
            s1.push("t");
            assertEquals( "t", new String("t") );
        }
        
}

