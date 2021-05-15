package sociopath;

import java.util.Scanner;
import java.util.Stack;

//Event-4
public class LibraryBook {

    public static void main(String[] args) {
        //Declare & initialization
        Scanner s = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        int size = s.nextInt();
        int newSize;
        int count = 0;
        int[] sort = new int[size];
        
        //User input
        String input = s.next();
        input += s.nextLine();

        //store it in array
        String[] height = input.split(" ");
        
        //convert to int array
        for (int i = 0; i < size; i++) {
            sort[i] = Integer.parseInt(height[i]);
        }

        do {
            findRemove(sort);
            stack.addAll(findRemove(sort));
            newSize = stack.size();
            
            //check whether it has increasing subsequent
            if (checkIncreasingSubsequent(sort) == true) {
                sort = new int[newSize];
                for (int i = newSize - 1; i >= 0; i--) {
                    sort[i] = stack.pop();
                }
                //counting for output
                count++;
            }
             
        }
        //check for size
        while (newSize>3);

        //Print output
        System.out.println(count);
    }

    public static Stack<Integer> findRemove(int sort[]) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();
        int[] helperarr = new int[sort.length];

        for (int i = sort.length - 1; i >= 0; i--) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek() <= sort[i]) {
                    stack.pop();
                }
            }
            helperarr[i] = stack.empty() ? 0 : stack.peek();
            stack2.push(sort[i]);
            stack.push(sort[i]);
            stack2.removeElement(helperarr[i]);
        }
        while (!stack2.isEmpty()) {
            stack3.push(stack2.pop());
        }

        return stack3;
    }

    static boolean checkIncreasingSubsequent(int[] checking) {
        boolean check = false;
        for (int i = 0; i < checking.length; i++) {
            if (checking[i] >= checking[i + 1]) {
                check = true;
                break;
            }
        }
        return check;
    }
}