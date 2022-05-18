package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//// 백준 사이트 문제 2164번 - 카드2
public class QueueAlgorithm {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// logic
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) { queue.add(i); }
		
		int count = 1;
		while (queue.size() != 1) {
			
			int q = queue.poll();
			if (count % 2 == 0) {
				queue.offer(q);
			}
			// 디버깅
			System.out.println(count + "번쨰 => " + queue);
			count++;
		}
		
		System.out.println(queue.peek());
		
	}

}
