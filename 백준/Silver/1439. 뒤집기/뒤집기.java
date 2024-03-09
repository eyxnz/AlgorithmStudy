import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static String S;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		
		char temp = '?';
		for(int i = 0; i < S.length(); i++) {
			if(temp == S.charAt(i)) {
				continue;
			}
			
			temp = S.charAt(i);
			answer++;
		}
		
		answer /= 2;
		System.out.println(answer);
	}
}