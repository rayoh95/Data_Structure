package list;

import java.util.ArrayList;
import java.util.Scanner;

// 백준 사이트 문제 11728번 - 배열 합치기
public class ListAlgorithm {

	public static void main(String[] args) {
		// 정열되어있는 두 배열 A 와 B 가 주어진다. 두 배열을 합친 다음 정렬해서 출력하는 프로그램을 작성하시오.
		// input
		// A 배열의 크기, B 배열의 크기
		// A 의 요소들
		// B 의 요소들
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// 배열 A 의 크기
		int M = sc.nextInt();	// 배열 B 의 크기
		
		ArrayList<Integer> A = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			A.add(n);
		}
		
		ArrayList<Integer> B = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			B.add(n);
		}
		
		// 배열에 값이 잘 들어갔는지 확인
		A.forEach( e -> System.out.print(e + " "));
		System.out.println();
		B.forEach( e -> System.out.print(e + " "));
		System.out.println();
		
		ArrayList<Integer> result = new ArrayList<>();
		
		int i = 0, j = 0;	// 배열 A 의 인텍스, 배열 B 의 인덱스
		
		while (i < N && j < M){
			
			int a = A.get(i);
			int b = B.get(j);
			
			if (a <= b) {
				result.add(a);
				i++;
			}
			else {
				result.add(b);
				j++;
			}
		}
		
		for (; i < N; i++) {
			result.add(A.get(i));
		}
		
		for (; j < N; j++) {
			result.add(B.get(j));
		}
		
		// output
		StringBuilder sb = new StringBuilder();
		for (int e : result) {
			sb.append(e + " ");
		}
		
		System.out.println(sb.toString());
		
	}

}
