package com.server.DataStructure.StackAndQueue;


/**
 * 顺序栈工具类
 */
public class StackUtils {

    /**
     * 使用栈实现表达式求值 (前提：表达式是正确的 ：0-9 + - *  /  ）
     *
     * @param express
     * @return
     */
    public static int CalculateExpress(String express) {
        int result = 0;
        OrderStack numStack = new OrderStack(20);
        OrderStack opeStack = new OrderStack(20);
        char[] arr = express.trim().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            String currentOpe = opeStack.getTop();
            if (0 <= arr[i] - '0' && arr[i] - '0' <= 9) {
                numStack.push(String.valueOf(arr[i]));
            }
            if (arr[i] == '*' || arr[i] == '/') {
                opeStack.push(String.valueOf(arr[i]));
            }
            while ((arr[i] == '+' || arr[i] == '-') && (currentOpe.equals("*") || currentOpe.equals("/"))) {
                int num2 = Integer.valueOf(numStack.pop());
                int num1 = Integer.valueOf(numStack.pop());
                if (currentOpe.equals("*")) {
                    numStack.push(String.valueOf(num1 * num2));
                }
                if (currentOpe.equals("/")) {
                    numStack.push(String.valueOf(num1 / num2));
                }
                opeStack.pop();
                currentOpe = opeStack.getTop();
            }
            if ((arr[i] == '+' || arr[i] == '-') && !currentOpe.equals("*") && !currentOpe.equals("/")) {
                opeStack.push(String.valueOf(arr[i]));
            }
        }
        while (opeStack.count != 0) {
            int num2 = Integer.valueOf(numStack.pop());
            int num1 = Integer.valueOf(numStack.pop());
            switch (opeStack.pop()) {
                case "+":
                    numStack.push(String.valueOf(num1 + num2));
                    break;
                case "-":
                    numStack.push(String.valueOf(num1 - num2));
                    break;
                case "*":
                    numStack.push(String.valueOf(num1 * num2));
                    break;
                case "/":
                    numStack.push(String.valueOf(num1 / num2));
                    break;
            }
        }
        return Integer.valueOf(numStack.pop());
    }

    public static void main(String[] args) {
        String express = "1+2+3+4+5";
//        String express = "1+4/5+2";
        System.out.println(StackUtils.CalculateExpress(express));
    }

}
