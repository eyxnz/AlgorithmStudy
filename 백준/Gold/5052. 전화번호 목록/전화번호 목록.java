import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

class Node {
	Map<Integer, Node> childNodes;
	boolean isLast;
	
	public Node() {
		childNodes = new TreeMap<>();
		isLast = false;
	}
}

class Trie {
	Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public void insert(String number) {
		Node node = root;
		
		for(int i = 0; i < number.length(); i++) {
			int n = number.charAt(i) - '0';

			if(!node.childNodes.containsKey(n)) {
				node.childNodes.put(n, new Node());
			}
			node = node.childNodes.get(n);
		}
		
		node.isLast = true;
	}
	
	public boolean check(String number) {
		Node node = root;
		
		for(int i = 0; i < number.length(); i++) {
			int n = number.charAt(i) - '0';
			
			if(node.isLast) {
				return false;
			}
			
			node = node.childNodes.get(n);
		}
		
		return true;
	}
}

public class Main {
	static int T;
	static int N;
	static String[] number;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			number = new String[N];
			Trie trie = new Trie();
			
			for(int i = 0; i < N; i++) {
				number[i] = br.readLine();
				trie.insert(number[i]);
			}
			
			boolean flag = true;
			for(int i = 0; i < N; i++) {
				if(!trie.check(number[i])) {
					flag = false;
					break;
				}
			}

			if(flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}