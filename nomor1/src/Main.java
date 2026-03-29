import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class solution {
    public String[] solving(Stack<String> input) {
        String[] Month = { "january", "february", "march", "april", "may", "june", "july", "august", "september",
                "october", "november", "december" };
        String[] tempName = new String[12];
        String[] tempMonth = new String[12];

        while (!input.isEmpty()) {
            String month = input.pop();
            String name = input.pop();
            String temp = month.toLowerCase();

            for (int i = 0; i < Month.length; i++) {
                if (temp.equals(Month[i])) {
                    tempMonth[i] = month;
                    tempName[i] = name;
                    break;
                }
            }
        }

        Stack<String> stack = new Stack<>();
        for (int i = 11; i >= 0; i--) {
            if (tempName[i] != null && tempMonth[i] != null) {
                stack.push(tempMonth[i]);
                stack.push(tempName[i]);
            }
        }

        String[] result = new String[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }

        return result;
    }
}

public class Main {
    public static void main(String[] args) {// helped by autofill
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] split = input.split(" ");
            Stack<String> stack = new Stack<>();
            for (String s : split) {
                stack.push(s);
            }
            solution sol = new solution();
            String[] result = sol.solving(stack);
            System.out.println(Arrays.toString(result));
        }
        sc.close();
    }
} 