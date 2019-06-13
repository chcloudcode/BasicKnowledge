package com.server.basic.LeetCode.simple;

/**
 * 问题：最长公共前缀
 *
 *编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class MaxSamePrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String result = "";
        String minString = strs[0];
        for(int i = 1;i<strs.length;i++){
            if(minString.length()>strs[i].length()){
                minString=strs[i];
            }
        }
        int j=1;
        while(j<=minString.length()){
            String tmp = minString.substring(0,j);
            for(int m =0;m<strs.length;m++){
                if(strs[m].equals(minString)) continue;
                if(!strs[m].equals(minString)&&!tmp.equals(strs[m].substring(0,j))){
                    return result;
                }
                continue;
            }
            result=tmp;
            j++;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSamePrefix m = new MaxSamePrefix();
        String[] arr = new String[]{"abb","abb","abb"};
        System.out.println(m.longestCommonPrefix(arr));
    }

}
