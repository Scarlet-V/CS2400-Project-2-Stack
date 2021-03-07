package StackProject;
import org.junit. *;
import static org.junit.Assert. *;

public class ArrayStackTest
{
    StackInterface<String> s1 = new ResizableArrayStack<String>();
    {   s1.push("c");
        s1.push("-");
        s1.push("a");
        s1.push("*");
        s1.push("(");
        s1.push("n");
        s1.push("-");
        s1.push("b");
        s1.push(")");
        s1.push("*");
        s1.push("f");
        s1.push("+");
        s1.push("z");
    }

    @Test
         
        public void testpop() throws Exception
        {
            
            assertEquals("z",(s1.pop()));
        }

     @Test           
        public void testpeek() throws Exception
        {
            assertEquals("+",(s1.peek()));
        }
}
