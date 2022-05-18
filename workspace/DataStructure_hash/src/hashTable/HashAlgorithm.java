package hashTable;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 백준 사이트 문제 1920번 - 수 찾기
public class HashAlgorithm {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Set<Integer> A = new HashSet<>();	// Set 은 중복된 데이터가 입력되면 한 값만 저장한다.
		
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			A.add(n);
		}
		
		StringBuilder result = new StringBuilder();
		
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int m = sc.nextInt();
			
			if (A.contains(m)) {
				result.append(1 + "\n");
			}
			else {
				result.append(0 + "\n");
			}
		}
		
		System.out.println(result);
	}

}
