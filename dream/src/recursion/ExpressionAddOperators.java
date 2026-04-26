package recursion;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        System.out.println(expressionAll("123",6));
        System.out.println(expressionAll("232",8));
    }
    public static List<String> expressionAll(String num,int target){
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", result);
        return result;
    }

    public static void dfs(String num, int target, int start, long current_value, long last_operand, String expression, List<String> result) {
        if (start == num.length()) {
            if (current_value == target) {
                result.add(expression);
            }
            return;
        }

        for (int i = start; i < num.length(); i++) {
            if (i > start && num.charAt(start) == '0') return;
            String current_num = num.substring(start, i + 1);
            long current_num_val = Long.parseLong(current_num);

            if (start == 0) {
                dfs(num, target, i + 1, current_num_val, current_num_val, current_num, result);
            } else {
                dfs(num, target, i + 1, current_value + current_num_val, current_num_val, expression + "+" + current_num, result);

                dfs(num, target, i + 1, current_value - current_num_val, -current_num_val, expression + "-" + current_num, result);

                dfs(num, target, i + 1, current_value - last_operand + last_operand * current_num_val, last_operand * current_num_val, expression + "*" + current_num, result);
            }
        }
    }
}
