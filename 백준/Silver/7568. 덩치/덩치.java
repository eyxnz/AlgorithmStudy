import java.io.*;
import java.util.*;

class Person {
    int x;
    int y;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Person> people = new ArrayList<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 몸무게
            int y = Integer.parseInt(st.nextToken()); // 키

            people.add(new Person(x, y));
        }

        for(int i = 0; i < people.size(); i++) {
            Person person = people.get(i);

            int k = 1;
            for(int j = 0; j < people.size(); j++) {
                Person target = people.get(j);

                if(i == j) {
                    continue;
                }

                if(target.x > person.x && target.y > person.y) {
                    k++;
                }
            }

            sb.append(k).append(" ");
        }

        System.out.println(sb);
    }
}
