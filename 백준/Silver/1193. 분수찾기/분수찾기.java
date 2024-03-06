import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int X;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());
		
		int line = 1;
		int cnt = 1;
		while(true) {
			if(X <= cnt) {
				break;
			}
			
			line++;
			cnt += line;
		}
		
		int left = cnt - line + 1; // 해당 라인에서 가장 작은 수
		int a = 0, b = 0; // 분모, 분자
		
		if(line % 2 == 0) {
			int temp = X - left; // 차이
			a = line - temp;
			b = 1 + temp;
		} else {
			int temp = X - left; // 차이
			a = 1 + temp;
			b = line - temp;
		}
		
		System.out.println(b + "/" + a);
	}
}