import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] str = new String[numbers.length];
        
        // int -> String
        for(int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        
        // 내림차순 정렬
        Arrays.sort(str, (a, b) -> {
            return (b + a).compareTo(a + b);
        });
        
        if(str[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }
        
        return sb.toString();
    }
}