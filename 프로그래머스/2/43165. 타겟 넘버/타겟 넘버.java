class Solution {
    static int N; // 배열 길이
    static int answer; // 방법의 수
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        subset(0, numbers, target, 0);
        
        return answer;
    }
    
    public void subset(int depth, int[] numbers, int target, int total) {
        if(depth == N) {
            if(target == total) {
                answer++;
            }
            
            return;
        }
        
        subset(depth + 1, numbers, target, total - numbers[depth]);
        subset(depth + 1, numbers, target, total + numbers[depth]);
    }
}