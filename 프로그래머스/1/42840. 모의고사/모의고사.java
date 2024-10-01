import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int index = 0;
        int oneScore = 0;
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == one[index]) {
                oneScore++;
            }
            
            index = (index + 1) % one.length;
        }
        
        index = 0;
        int twoScore = 0;
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == two[index]) {
                twoScore++;
            }
            
            index = (index + 1) % two.length;
        }
        
        index = 0;
        int threeScore = 0;
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == three[index]) {
                threeScore++;
            }
            
            index = (index + 1) % three.length;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o2[1] - o1[1] == 0) {
                return o1[0] - o2[0]; // 2. 수포자 오름차순
            }
            
            return o2[1] - o1[1]; // 1. 점수 높은 순
        });
        pq.offer(new int[] {1, oneScore});
        pq.offer(new int[] {2, twoScore});
        pq.offer(new int[] {3, threeScore});
        
        int max = 0;
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()) {
            int[] value = pq.poll();
            
            if(max <= value[1]) {
                max = value[1];
                list.add(value[0]);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}