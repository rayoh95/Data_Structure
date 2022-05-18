package stack;

import java.util.Scanner;
import java.util.Stack;

//백준 사이트 문제 9012번 - 괄호
public class StackAlgorithm {
	
	public static void foo(String s) {
		
		Stack<Character> stack = new Stack<>();
		
		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			
			if (c == '(') { stack.push(c); }
			else {
				
				if (stack.size() < 1) {
					System.out.println("NO");
					return;
				}
				stack.pop();
			}
			
			i++;
		}
		
		if (stack.size() > 0) { System.out.println("NO"); }
		else { System.out.println("YES"); }
	}

	public static void main(String[] args) {
		// 괄호 쌍이 맞는지 안 맞는지 확인하는 문제
		// input			output
		// (())())			No
		// (((()())()		NO
		// (()())((()))		YES
		
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		
		for (int tc = 0; tc < testCase; tc++) {
			foo(sc.next());
		}
	}

}
