import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();

        boolean flag = false; // 괄호가 열린 적 있나?
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if(ch == ' ') {
                if(flag) {
                    sb.append(temp).append(' ');
                } else {
                    sb.append(temp.reverse()).append(' ');
                }

                temp = new StringBuilder();

                continue;
            }

            if(ch == '<') {
                flag = true;
                sb.append(temp.reverse()).append('<');

                temp = new StringBuilder();

                continue;
            }

            if(ch == '>') {
                flag = false;
                sb.append(temp).append('>');

                temp = new StringBuilder();

                continue;
            }

            temp.append(ch);
        }

        sb.append(temp.reverse());

        System.out.println(sb);
    }
}
