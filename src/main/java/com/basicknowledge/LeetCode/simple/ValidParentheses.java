package com.basicknowledge.LeetCode.simple;

/**
 *题目： 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if(s.equals("")) return true;
        char[] arr = s.toCharArray();
        Boolean flag = false;
        String pre = "";
        for(int i = 0; i<arr.length;i++){
            if(arr[i]=='{'||arr[i]=='('||arr[i]=='['){
               pre+=String.valueOf(arr[i]);
               flag=true;
               continue;
            }
            if(!pre.equals("")&&null!=pre){
                if(arr[i]=='}'&&pre.charAt(pre.length()-1)=='{') {
                    pre = pre.substring(0, pre.length() - 1);
                    continue;
                }
                if(arr[i]==')'&&pre.charAt(pre.length()-1)=='('){
                    pre=pre.substring(0,pre.length()-1);
                    continue;
                }
                if(arr[i]==']'&&pre.charAt(pre.length()-1)=='['){
                    pre=pre.substring(0,pre.length()-1);
                    continue;
                }
            }
            return false;
        }
        if((pre.equals("")||null==pre)&&flag==true){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        System.out.println(v.isValid("(])"));
    }
}
