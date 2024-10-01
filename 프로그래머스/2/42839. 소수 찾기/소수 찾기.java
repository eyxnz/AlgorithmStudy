import java.util.*;

class Solution {
    static int answer = 0;
    static List<Character> choice = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();
    static boolean[] visited;
    
    public int solution(String numbers) {
        subSet(0, numbers);
        
        return answer;
    }
    
    public static void combi(int depth, String nums) {
        if(depth == choice.size()) {
            int num = Integer.parseInt(nums);
            
            if(isPrime(num)) {
                if(set.contains(num)) {
                    return;
                }
                
                set.add(num);
                answer++;
            }
            
            return;
        }
        
        for(int i = 0; i < choice.size(); i++) {
            if(visited[i]) {
                continue;
            }
            
            visited[i] = true;
            combi(depth + 1, nums + choice.get(i));
            visited[i] = false;
        }
    }
    
    public static void subSet(int depth, String numbers) {
        if(depth == numbers.length()) {
            if(choice.size() == 0) {
                return;
            }
            
            visited = new boolean[choice.size()];
            combi(0, "");
            
            return;
        }
        
        char num = numbers.charAt(depth);
        
        choice.add(num);
        subSet(depth + 1, numbers);
        choice.remove(choice.size() - 1);
        
        subSet(depth + 1, numbers);
    }
    
    public static boolean isPrime(int num) {
        if(num < 2) {
            return false;
        }
        
        if(num == 2) {
            return true;
        }
        
        for(int i = 2; i < Math.pow(num, 0.5) + 1; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}