import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int index = 0, num = 666;
		while(true) {
			if(String.valueOf(num).contains("666")) {
				index++;
				
				if(index == N) {
					break;
				}
			}
			num++;
		}
		
		System.out.println(num);
	}
}