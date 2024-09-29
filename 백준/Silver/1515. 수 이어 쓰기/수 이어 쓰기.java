import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine(); // 지우고 남은 수

        int index = 0;
        for(int N = 1; N <= 30000; N++) {
            String str = String.valueOf(N); // 원래 수

            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == target.charAt(index)) {
                    index++;
                }

                if(index == target.length()) {
                    System.out.println(N);
                    return;
                }
            }
        }
    }
}
