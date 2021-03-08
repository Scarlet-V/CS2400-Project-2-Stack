package StackProject;
import org.junit.*;
import static org.junit.Assert.*;

public class ArrayStackTest {
    private ResizableArrayStack<String> s1;
    
    @Before
    public void createStack() {
        s1 = new ResizableArrayStack<>();
        
    }

    @Test
        public void testConvertToPostfix() throws Exception {
            String infix = "a*b/(c-a)+d*e";
            assertEquals("ab*ca-/de*+", s1.convertToPostfix(infix));
        }

    @Test
        public void testEvaluatePostfix() throws Exception {
            String postfix = "2 3 * 4 2 - / 5 6 * + "; 
            assertEquals(33, s1.evaluatePostfix(postfix));
        }

    @Test
        public void testpop() throws Exception {
            s1.push("c");
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
            assertEquals("z", s1.pop());
        }

     @Test           
        public void testpeek() throws Exception {
            s1.push("c");
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
            s1.pop();
            assertEquals("+", s1.peek());
        }

}
