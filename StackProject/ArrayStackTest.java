package StackProject;

public class ArrayStackTest
{
    public static void main(String[] args) throws Exception {
        ResizableArrayStack<String> stack = new ResizableArrayStack<>();
        String[] bag2 = {"B", "C", "C", "D", "E", "B"};
        String example = "a*b/(c-a)+d*e";
        String postfix = "2 3 * 4 2 - / 5 6 * + ";
        System.out.println(stack.convertToPostfix(example));
        System.out.println(stack.evaluatePostfix(postfix));
    }
}
