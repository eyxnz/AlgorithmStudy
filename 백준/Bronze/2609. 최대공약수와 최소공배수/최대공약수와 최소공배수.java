import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      int a = Math.max(x, y), b = Math.min(x, y);
      System.out.println(gcd(a, b));
      System.out.println(lcm(a, b));
   }
   
   private static int lcm(int a, int b) { // 최소 공배수
	   return a * b / gcd(a, b);
   }

   private static int gcd(int a, int b) { // 최대 공약수
	   int temp = 0;
	   
	   while(b > 0) {
		   temp = a % b;
		   a = b;
		   b = temp;
	   }
	   
	   return a;
	}
}
