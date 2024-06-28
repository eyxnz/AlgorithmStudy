import java.io.*;
import java.util.*;

public class Main {
	static int N, d, k, c, answer;
	static int[] belt;
	static Map<Integer, Integer> kind;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
    	d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
    	k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
    	c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
    	belt = new int[N]; // 회전 초밥 벨트
    	kind = new HashMap<>(); // 현재 범위에 어떤 종류의 초밥이 있는지
    	
    	for(int i = 0; i < N; i++) {
    		belt[i] = Integer.parseInt(br.readLine());
    	}
    	
    	// 0 ~ k - 1
    	for(int idx = 0; idx < k; idx++) {
    		int sushi = belt[idx];
    		
    		if(kind.containsKey(sushi)) {
    			int count = kind.get(sushi);
    			
    			kind.put(belt[idx], count + 1);
    		} else {
    			kind.put(belt[idx], 1);
    		}
    	}
    	
    	answer = kind.size();
    	if(!kind.containsKey(c)) {
    		answer++;
    	}
    	
    	if(N != k) {
    		for(int start = 1; start < N; start++) { // 시작 인덱스
    			int end = (start + k - 1) % N; // 끝 인덱스
    			
    			if(belt[start - 1] != belt[end]) { // 현재 범위에 있는 초밥 종류 갱신 필요
    				int count = kind.get(belt[start - 1]);
    				
        			if(count == 1) {
        				kind.remove(belt[start - 1]);
        			} else {
        				kind.put(belt[start - 1], count - 1);
        			}
        			
        			if(kind.containsKey(belt[end])) {
            			count = kind.get(belt[end]);
            			
            			kind.put(belt[end], count + 1);
            		} else {
            			kind.put(belt[end], 1);
            		}
    			}
    			
    			int temp = kind.size();
    	    	if(!kind.containsKey(c)) {
    	    		temp++;
    	    	}
    	    	
    	    	answer = answer > temp ? answer : temp;
    		}
    	}
    	
    	System.out.println(answer);
    }
}
