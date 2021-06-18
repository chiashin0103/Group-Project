package sociopath;

import java.util.Scanner;
import java.util.Stack;

//Event-4
public class LibraryBook {

    public static void main(String[] args) {
        //Declare & initialization
        Scanner s = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        System.out.print("Enter the number of book: ");
        int size = s.nextInt();
        int newSize;
        int count = 0;
        int[] sort = new int[size];

       //User input
        System.out.print("Enter the heights of books: ");
        String input1 = s.next();
        input1 += s.nextLine();
        //store it in array
        String[] height = input1.split(" ");
        //convert to int array
        for (int i = 0; i < size; i++) {
            sort[i] = Integer.parseInt(height[i]);
        }

        do {
            findRemove(sort);
            stack.addAll(findRemove(sort));
            newSize = stack.size();
            sort = new int[newSize];
            for (int i = newSize - 1; i >= 0; i--) {
                sort[i] = stack.pop();
            }

            //counting for output
            count++;

        } //check whether it has increasing subsequent
        while (checkIncreasingSubsequent(sort) == true);

        //Print output
        System.out.println("The number of round(s): " + count);
    }

    public static Stack<Integer> findRemove(int sort[]) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Integer a;
        
        for (int i = sort.length - 1; i >= 0; i--) {
            stack.push(sort[i]);
        }

        a = stack.pop();
        while (!stack.isEmpty()) {
            if (stack.peek() > a) {
                stack.pop();
                if (stack.isEmpty() == true) {
                    stack2.push(a);
                } else {
                    a = stack.pop();
                }
            } else if (stack.peek() == a) {
                stack.pop();
                stack2.push(a);
            } else {
                stack2.push(a);
                a = stack.pop();
                stack2.push(a);
            }
        }

        return stack2;
    }

    static boolean checkIncreasingSubsequent(int[] checking) {
        Stack<Integer> stack = new Stack<>();
        boolean check = false;
        Integer a;

        for (int i = checking.length - 1; i >= 0; i--) {
            stack.push(checking[i]);
        }

        a = stack.pop();

        while (!stack.isEmpty()) {
            if (stack.peek() > a) {
                check = true;
                break;
            } else {
                a = stack.pop();
            }
        }

        return check;
    }
}
