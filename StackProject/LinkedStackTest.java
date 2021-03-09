package StackProject;
import org.junit.*;
import static org.junit.Assert.*;

public class LinkedStackTest 
{
    LinkedStack<String> s1 = new LinkedStack<String>();
    {
        s1.push("a");
        s1.push("");
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
        public void testPop() throws Exception
        { 
            s1.pop();
            s1.pop();               
            assertEquals("d",(s1.pop()));
        }

     @Test           
        public void testPeek() throws Exception
        {
            s1.push("p");
            assertEquals("p",(s1.peek()));
        }

    @Test
        public void testPush() throws Exception
        {
            s1.push("t");
            s1.push("p");
            assertEquals( "p", s1.peek() );
        }

        @Test
        public void testConvertToPostfix() throws Exception 
        {
            String infix = "a*b/(c-a)+d*e";
            assertEquals("a b * c a - / d e * + ", s1.convertToPostfix(infix));
        }

        
}

