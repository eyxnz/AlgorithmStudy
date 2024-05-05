import java.io.*;
import java.util.*;

public class Main {
   static int N, K;
   static int[] num; // 객차에 타고 있는 손님의 수
   static int[] sum; // 누적합
   static int[][] dp;
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      N = Integer.parseInt(br.readLine());
      
      num = new int[N];
      st = new StringTokenizer(br.readLine(), " ");
      for(int i = 0; i < N; i++) {
    	  num[i] = Integer.parseInt(st.nextToken());
      }
      
      K = Integer.parseInt(br.readLine());
      
      sum = new int[N];
      sum[0] = num[0];
      for(int i = 1; i < N; i++) {
    	  sum[i] = sum[i - 1] + num[i];
      }
      
      dp = new int[N][3];
      dp[K - 1][0] = sum[K - 1];
      for(int i = K; i < N; i++) {
    	  dp[i][0] = Math.max(dp[i - 1][0], sum[i] - sum[i - K]);
    	  dp[i][1] = Math.max(dp[i - 1][1], dp[i - K][0] + sum[i] - sum[i - K]);
    	  dp[i][2] = Math.max(dp[i - 1][2], dp[i - K][1] + sum[i] - sum[i - K]);
      }
      
      System.out.println(dp[N - 1][2]);
   }
}
