### 자료구조.알고리즘

<hr>



### `정렬 (Sorting)`

* 안정( stable ) 정렬    vs    불안정( unstable ) 정렬	: 중복된 값의 순서가 보장 되었는지 여부
  * {1a, 2, 1b, 4, 3, 5, 6} -> 정렬되어 있지 않은 list 에 중복된 값 1 이 두 개 있다.( 값의 구분을 위해 a, b 를 붙였다.)
  * 위 list 를 정렬할 때 두 가지의 경우가 존재한다 : {1a, 1b, 2, 3, 4, 5, 6}   과   {1b, 1a, 2, 3, 4, 5, 6}
  * 이렇게 중복된 1 에 대해서 순서가 보장이 되면 안정 정렬, 보장이 되지 않는 경우에는 불안정 정렬
* In-place 정렬    vs    Out-of-place 정렬    : 원본 데이터 내에서 정렬로 구현했는지의 여부





### `1. 버블 정렬 (Bubble Sort)`

정렬되어있지 않은 {5, 4, 1, 9, 3, 7, 2} 를 Bubble Sort 를 사용해 오름차순 정렬을 해보자.

* 맨 앞의 값 5 와 4 를 비교한다.
* 정렬되어 있지 않기 때문에 {4, 5} 의 순으로 정렬한다. -> {4, 5, 1, 9, 3, 7, 2}
* 한 칸 이동하여 5 와 1 을 비교한다.
* 정렬되어 있지 않기 때문에 {1, 5} 의 순으로 정렬한다. -> {4, 1, 5, 9, 3, 7, 2}
* 한 칸 이동하여 5 와 9 를 비교한다.
* 정렬되어 있기 때문에 {5, 9} 를 그대로 둔다. -> {4, 1, 5, 9 ,3, 7 ,2}
* 위의 과정을 반복하여 한 사이클을 돌면 {4, 1, 5, 3, 7, 2, 9} 로 정렬이 된다. -> 이 list 의 가장 큰 값인 9 가 맨 오른쪽에 위치하게 된다.
* {4, 1, 5, 3, 7, 2} 을 갖고 한 사이클을 또 돈다.
* 이렇게 한 사이클을 마무리할 때 마다 그 때의 가장 큰 값이 맨 오른쪽으로 위치하게 된다.
* 총 6 번의 사이클을 돌면 배열 내 모든 값이 오름차순으로 정렬이 될 것이다.



##### `정리`

* 인접한 두 element 의 값을 비교
* 두 값이 정렬되어 있지 않다면 위치를 교환 ( swap )
* 정렬이 완료된 elements 를 제외하고 위의 과정을 반복
  * (n-1) + (n-2) + (n-3) + ... + 3 + 2 + 1 = (n(n-1)) / 2
* 안정 정렬이면서 인플레이스 정렬이다.



#### 시간 복잡도 : `O(N^2)`

#### 직관적이고 단순한 알고리즘, 느린 속도로 인해 사용에는 어려움이 있다.



````java
package bubbleSort;

public class BubbleSort implements ISort {

	@Override
	public void sort(int[] arr) {
    
		for (int i = 0; i < arr.length - 1; i++) {		// 전체 리스트
			for (int j = 0; j < arr.length - 1 - i; j++) {	// 정렬된 리스트를 제외
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

}
````





### `2. 삽입 정렬 (Insertion Sort)`

* 리스트의 앞에서부터
* 이미 정렬된 서브 리스트의 값들과 비교
* 자신의 위치에 삽입

이 때, 이미 정렬된 서브 리스트란 어떤 리스트일까?

* 만약 배열이 한 칸 짜리라면 그 배열은 이미 정렬되어 있다고 할 수 있다.
* 이 점에서부터 출발하는 것이 정렬된 서브리스트이다.
* 길이가 2 이상인 리스트에서 가장 앞에 있는 하나의 원소를 이미 정렬된 서브 리스트로 보고 정렬을 시작하는 것. -> 실질적인 정렬 logic 은 리스트의 두 번째( 인덱스 번호 1번 ) 부터 시작한다.



정렬되어 있지 않은 {5, 4, 1, 9, 3, 7, 2} 를 삽입 정렬을 이용해 오름차순 정렬을 해보자.

* 맨 앞의 5 를 서브 리스트로 본다.
* 두 번째 4 를 서브리스트 {5} 와 비교해 자신의 위치를 찾는다. -> {4, 5}
* 세 번째 1 을 서브리스트 {4, 5} 와 비교해 자신의 위치를 찾는다. -> {1, 4, 5}
* 이러한 과정을 반복하여 정렬된 리스트 {1, 2, 3, 4, 5, 7, 9} 를 얻는다.



##### `정리`

* 안정 정렬
* 단순한 알고리즘
* 데이터의 이동이 많다.
  * 하지만 리스트 내의 데이터가 어느 정도 정렬이 되어 있는 상태일 경우 데이터의 이동이 적어짐.



#### `시간 복잡도`

* 평균 : `O(N^2)`
* 최선의 경우 ( 모두 정렬이 되어있는 경우 ) : `O(N)`
* 최악의 경우 ( 역으로 정렬되어 있는 경우) : `O(N^2)`



````java
public class InsertionSort implements ISort {

	@Override
	public void sort(int[] arr) {
		
		for (int i = 1; i < arr.length; i++) {
			int key = arr[i];	// 삽입 위치를 찾아줄 데이터
			int j = i - 1;		// 0 ~ j 까지를 정렬된 서브 리스트로 사용
			
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
 			
			arr[j + 1] = key;
		}
	}

}
````





### `3. 합병 정렬 (Merge Sort)`

* 하나의 리스트를
* 두 개의 균등한 크기의 리스트로 분할하고
* 부분 리스트를 합치면서 정렬하여
* 전체가 정렬되게 하는 방법



정렬되어 있지 않은 {5, 4, 1, 9, 3, 7, 2, 6} 를 합병 정렬을 이용해 오름차순 정렬을 해보자.

* 크키가 같은 두 개의 리스트로 분할한다. -> {5, 4, 1, 9} 와 {3, 7, 2, 6}
* 분할한 서브 리스트들을 다시 같은 크기의 서브 리스트 두 개로 분할한다. -> {5, 4} 와 {1, 9} 그리고 {3, 7} 와 {2, 6}
* 이러한 분할을 부분 리스트의 크기가 1 이 될 때 까지 진행한다. -> {5} ,{4}, {1}, {9}, {3}, {7}, {2}, {6}
* 분할된 부분 리스트들을 분할과 동일한 크기로 합치면서 정렬하면 된다. -> 합치는 과정에서 원소들은 자신의 위치에 정렬된다.
* {4, 5} 로 정렬, {1, 9} 로 정렬, {3, 7} 로 정렬, {2, 6} 로 정렬
* {1, 4, 5, 9} 로 정렬, {2, 3, 6, 7} 로 정렬
* {1, 2, 3, 4, 5, 6, 7, 9} 로 정렬



##### `정리`

* 하나의 문제를 동일한 유형의 작은 문제로 분할한 다음 작은 문제를 해결하여 점차 큰 문제를 해결하는 방식의 알고리즘을 `분할 정복 ( Divide and Conquer )` 알고리즘 이라고 한다.
* 분할 정복 알고리즘은 재귀함수를 통해 구현한다.



#### 시간 복잡도 : `O(NlogN)`

* N 의 길이를 가진 리스트를 크기가 1인 서브 리스트로 분할할 때 시간 복잡도 : `logN`
* 분할이 된 상태에서 다시 리스트를 합칠 때 각 요소들을 비교하면서 합치기 때문에 비교 연산이 N 번 수행된다.



```java
public class MergeSort implements ISort {

	@Override
	public void sort(int[] arr) {
		// 인플레이스 방법으로 구현.
		mergeSort(arr, 0, arr.length - 1);
	}
	
	// 분할
	private void mergeSort(int[] arr, int low, int high) {
		
		if (low >= high) {
			return;
		}
		
		int mid = low + ((high - low) / 2);
		
		mergeSort(arr, low, mid);
		mergeSort(arr, mid + 1, high);
		
		merge(arr, low, mid, high);
	}
	
	// 합병
	private void merge(int[] arr, int low, int mid, int high) {
		
		int[] temp = new int[high - low + 1];
		int idx = 0;
		
		int left = low;
		int right = mid + 1;
		
		while (left <= mid && right <= high) {
			if (arr[left] <= arr[right]) {
				temp[idx] = arr[left];
				left++;
			}
			else {
				temp[idx] = arr[right];
				right++;
			}
			idx++;
		}
		
		while (left <= mid) {
			temp[idx] = arr[left];
			idx++;
			left++;
		}
		
		while (right <= high) {
			temp[idx] = arr[right];
			idx++;
			right++;
		}
		
		for (int i = low; i <= high; i++) {
			arr[i] = temp[i - low];
		}
	}

}
```





### `4. 퀵 정렬 (Quick Sort)`

* 합병 정렬 ( Merge Sort )와 마찬가지로 배열을 둘 씩 분할하면서 정렬하는 과정을 거친다.
* 시간 복잡도 : `O(NlogN)` -> 같은 시간 복잡도라도 실제 정렬에는 합병 정렬보다 짧은 시간이 소요된다.(컴퓨터 하드웨터의 특성)
  * 참조 지역성 ( locality of reference ) -> 동일한 배열 내에서 자리이동이 일어난다. 인접한 데이터 사이의 이동이라서 제일 처음 접근할 때만 실제 메모리에서 배열을 가져오고 이후에는 캐시( Cache )로 배열에 접근한다.
  * 한 번 결정된 pivot 값은 이후의 연산에서 제외 -> 분할이 진행 될수록 계산해야 할 데이터 수가 점점 줄어드는 특성.
* 추가적인 메모리 공간 사용이 없다.
* 불안정 정렬
* `Divide and conquer`



정렬되어 있지 않은 {3, 4, 1, 5, 7, 2, 6} 를 퀵 정렬을 이용해 오름차순 정렬을 해보자.

* pivot 값을 정하는 것 부터 시작한다. 이 값은 가장 앞의 값도, 가장 뒤에 값도, 가장 중간에 위치한 값도 될 수 있다.
* pivot 값을 중간에 위치한 5 로 지정해보자.
* pivot 값을 중심으로 원소들을 재배치한다. 왼쪽에는 pivot 값 보다 작은 값, 오른쪽에는 pivot 값 보다 큰 값 -> {3, 4, 1, 2, 5, 7, 6} -> 이 때 pivot 값의 인덱스 번호는 바뀔 수 있다. (중요한 것은 인덱스가 아닌 값)
* 재배치 후 pivot 값을 중심으로 왼쪽의 서브 리스트와 오른쪽의 서브 리스트로 나눈다. -> {3, 4, 1, 2} 와 {7, 6}
* 각 서브 리스트에서 pivot 값을 정해준다. -> 각 1 과 6
* 정해진 pivot 값을 기준으로 재배치한다. -> {1, 4, 3, 2,} 와 {6, 7}
* 오른쪽의 서브 리스트는 원소의 개수가 한 개만 남았기 때문에 더 이상 분할할 수 없다. -> 퀵 정렬 종료
* 왼 쪽의 {4, 3, 2} 에서 pivot 값을 3 으로 정한다.
* {2, 3, 4} 로 재배치 후 퀵 정렬 종료 -> {1, 2, 3, 4, 5, 6, 7}



#### `시간 복잡도`

* 평균 : `O(NlogN)`
* 최악의 경우 ( 이미 정렬된 리스트에서 pivot 값을 가장 왼쪽이나 오른쪽의 값으로 정할 때 ) : O(N * N) = `O(N^2)`

* 치우친 분할을 극복하기 위해 pivot 값을 고를 때 몇가지 pivot 값을 후보로 둔 후, 그 중 가장 중간값을 갖는 데이터를 pivot 값으로 정하는 알고리즘을 사용한다. -> `Median of Three`



```java
public class QuickSort implements ISort {

	@Override
	public void sort(int[] arr) {
		
		quickSort(arr, 0, arr.length - 1);
	}
	
	private void quickSort(int[] arr, int low, int high) {
		
		if (low >= high) {
			return;
		}
		
		int pivot = low + ((high - low) / 2);
		int pivotValue = arr[pivot];
		
		int left = low;
		int right = high;
		
		while (left <= right) {
			while (arr[left] < pivotValue) {
				left++;
			}
			
			while (arr[right] > pivotValue) {
				right--;
			}
			
			if (left <= right) {
				int temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
				left++;
				right--;
			}
		}
		
		quickSort(arr, low, right);	// 왼쪽의 서브 리스트
		quickSort(arr, low, high);	// 오른쪽의 서브 리스트
		
	}

}

```

