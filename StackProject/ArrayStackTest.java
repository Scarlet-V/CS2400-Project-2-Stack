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
        public void testPop() throws Exception {
            s1.push("c");
            s1.push("-");
            s1.push("a");
            s1.pop();
            assertEquals("-", s1.pop());
        }

     @Test           
        public void testPeek() throws Exception {
            s1.push("c");
            assertEquals("c", s1.peek());
        }

    @Test           
        public void testPush() throws Exception {
            s1.push("c");
            assertEquals(false, s1.isEmpty());
        }

}
