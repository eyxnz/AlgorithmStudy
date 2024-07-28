class Solution {
    static int N = 11;
    
    static int MAX = 0;
    static int[] answer = new int[N];
    
    public int[] solution(int left, int[] apeachInfo) {
        bfs(0, left, new int[N], apeachInfo, 0, 0);
        
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += answer[i];
        }
        
        if(sum == 0) {
            return new int[] {-1};
        } else {
            return answer;
        }
    }
    
    private static void bfs(int depth, int left, int[] ryanInfo, int[] apeachInfo, int ryan, int apeach) {
        if(depth == N) { // 모든 과녁 다 쏨
            if(apeach >= ryan) {
                return;
            }
            
            if(MAX > ryan - apeach) {
                return;
            }
            
            if(MAX < ryan - apeach) {
                MAX = ryan - apeach;
                answer = ryanInfo.clone();
            } else {
                for(int i = N - 1; i >= 0; i--) {
                    if(answer[i] > ryanInfo[i]) {
                        return;
                    } else if(answer[i] < ryanInfo[i]) {
                        answer = ryanInfo.clone();
                        return;
                    }
                }
            }
            
            return;
        }
        
        if(depth == N - 1) { // 0점
            ryanInfo[depth] = left;
            bfs(depth + 1, 0, ryanInfo, apeachInfo, ryan, apeach);
            ryanInfo[depth] = 0;
        } else {
            // 라이언 이김
            if(apeachInfo[depth] < left) {
                ryanInfo[depth] = apeachInfo[depth] + 1;
                bfs(depth + 1, left - (apeachInfo[depth] + 1), ryanInfo, apeachInfo, ryan + (10 - depth), apeach);
                ryanInfo[depth] = 0;
            }
            
            // 비김
            if(apeachInfo[depth] == 0) {
                bfs(depth + 1, left, ryanInfo, apeachInfo, ryan, apeach);
            }
            
            // 어피치 이김
            if(apeachInfo[depth] > 0) {
                bfs(depth + 1, left, ryanInfo, apeachInfo, ryan, apeach + (10 - depth));
            }
        }
    }
}