import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int t = 0; t < N; t++) {
			String str = br.readLine();
			
			boolean flag = true;
			boolean[] alphabet = new boolean[26]; // 0 ~ 25
			char temp = str.charAt(0);
			alphabet[temp - 'a'] = true;
			
			for(int i = 1; i < str.length(); i++) {
				if(temp == str.charAt(i)) { // 연속적으로 나온 알파벳
					continue;
				}
				if(!alphabet[str.charAt(i) - 'a']) { // 처음 나온 알파벳
					alphabet[str.charAt(i) - 'a'] = true;
					temp = str.charAt(i);
					continue;
				}
				
				flag = false;
				break;
			}
			if(!flag) {
				continue;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
}