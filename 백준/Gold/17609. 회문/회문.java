import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			String str = br.readLine();
			int len = str.length();
			sb.append(isPalindrome(str, 0, len - 1, 0)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int isPalindrome(String str, int left, int right, int count) {
		while(left < right) {
			if(str.charAt(left) != str.charAt(right)) {
				if(count++ != 0) { // 이전에 이미 삭제된 적이 있다면
					return 2;
				}

				if(str.charAt(left) == str.charAt(right - 1) && str.charAt(right) == str.charAt(left + 1)) { // 아래 둘 다 만족
					if(isPalindrome(str, left + 1, right, count) != 2 || isPalindrome(str, left, right - 1, count) != 2) {
						return count;
					}
					
					return 2;
				} else if(str.charAt(left) == str.charAt(right - 1)) { // right 위치에 있는 문자 삭제
					right--;
				} else if(str.charAt(right) == str.charAt(left + 1)) { // left 위치에 있는 문자 삭제
					left++;
				} else { // 둘 다 만족 안 함
					return 2;
				}
			}
			
			left++;
			right--;
		}
		
		return count;
	}
}
