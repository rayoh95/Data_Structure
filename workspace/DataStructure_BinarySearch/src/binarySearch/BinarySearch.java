package binarySearch;

public class BinarySearch {

	public int search(int[] arr, int target) {
		
		// 1. 데이터의 중간 인덱스 값을 찾는다.
		// 2. 중간 인덱스 위치를 기준으로 arr 을 절반으로 나눈다.
		// 3. 나눠진 절반의 리스트에서 target 값을 찾는다.
		
		int l = 0;					// arr 의 가장 왼쪽 인덱스
		int r = arr.length - 1;		// arr 의 가장 오른쪽 인덱스
		int m;						// 인덱스의 중간값
		
		while (l <= r) {
			m = l + ((r - l) / 2);		// (l + r)/2 도 중간값이지만 (l + r) 이 int 의 범위를 벗어나 오버플로우 에러가 나는 것을 방지.
			
			if (arr[m] == target) {
				return m;
			}
			
			if (arr[m] < target) {
				l = m + 1;
			}
			else {
				r = m - 1;
			}
		}
		
		return -1;
	}
}
