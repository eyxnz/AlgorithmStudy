import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Word implements Comparable<Word> {
	String str;
	
	public Word(String str) {
		this.str = str;
	}

	@Override
	public int compareTo(Word o) {
		if(str.length() == o.str.length()) {
			return str.compareTo(o.str); // 사전 순
		}
		return str.length() - o.str.length(); // 길이가 짧은 순
	}

	@Override
	public String toString() {
		return str;
	}

	// equals & hashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(str);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(str, other.str);
	}
}

public class Main {
	static int N;
	static Set<Word> words;
	static List<Word> newWords;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		words = new HashSet<>();
		newWords = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			words.add(new Word(br.readLine()));
		}
		newWords.addAll(words);
		
		Collections.sort(newWords);
		
		for(Word word : newWords) {
			sb.append(word).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}