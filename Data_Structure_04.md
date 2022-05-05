### 자료구조.알고리즘

<hr>



### Hash



##### 해싱(Hashing)

* 데이터를 빠르게 저장하고 가져오는 기법 중 하나
* 키에 특정 `연산`(해시 함수)을 적용하여 `테이블의 주소`(해시 테이블)를 계산



##### 해시 테이블

* (Key, Value) 쌍을 저장한다
* 데이터간 순서가 존재하지 않는다.



##### Hashing - Key

* 키를 기준으로 값을 매핑
* 고유한 값을 갖는다. 중복이 불가능하다.



##### Hashing - Hash Function (해시 함수)

* 임이의 데이터(키)를 특정 값(해시값)으로 매핑시키는 함수

* 해시 함수를 통한 데이터 접근은 시간 복잡도 `O(1)`을 갖는다.

* ##### 좋은 해시 함수의 특징

  * 키 값을 고르게 분포 시킨다.
  * 해시 값을 계산하는 속도가 빠르다.
  * `충돌`을 최소화한다. (해시 충돌) -> 키 값이 다른데, 해시 함수의 결과값이 동일한 경우
    * LinkedList 를 이용한 `Chaining` 방식으로 충돌을 해결할 수 있다. (기본)





`아래 코드를 기반으로 Hash 를 구현해 보자.`

```java
package hashTable;

public interface IHashTable<K, V> {
	void put(K key, V value);
	V get(K key);
	boolean delete(K key);
	boolean contains(K key);
	int size();
}
```





`구현`

```java
package hashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

public class MyLinkedHashTable<K, V> implements IHashTable<K, V> {
	
	private static final int DEFAULT_BUCKET_SIZE = 1024;
	
	private List<Node>[] buckets;
	private int size;
	private int bucketSize;
	
	public MyLinkedHashTable() {
		
		this.buckets = new List[DEFAULT_BUCKET_SIZE];
		this.bucketSize = DEFAULT_BUCKET_SIZE;
		this.size = 0;
		
		// buckets 의 각 칸을 초기화시킨다. 초기화를 하지 않으면 default 값으로 NULL 이 들어가기 때문에 후에 데이터를 넣을 수 없다. 
		for (int i = 0; i < bucketSize; i++) {
			this.buckets[i] = new LinkedList<>();
		}
	}
	
	// 사용자가 원하는 사이즈의 bucket 사이즈를 만들 수 있도록.
	public MyLinkedHashTable(int bucketSize) {
		
		this.buckets = new List[bucketSize];
		this.bucketSize = bucketSize;
		this.size = 0;
		
		// buckets 의 각 칸을 초기화시킨다. 초기화를 하지 않으면 default 값으로 NULL 이 들어가기 때문에 후에 데이터를 넣을 수 없다. 
		for (int i = 0; i < bucketSize; i++) {
			this.buckets[i] = new LinkedList<>();
		}
	}

	@Override
	public void put(K key, V value) {
	
		int idx = this.hash(key);	// hash 함수로 인덱스 번호를 구한다.
		List<Node> bucket = this.buckets[idx];	// 해당 인덱스 번호의 주소로 bucket 을 생성
		
		// 이미 존재하는 키 값이라면, 해당 value 를 새로 덧 씌운다.
		for (Node node : bucket) {
			if (node.key.equals(key)) {
				node.data = value;
				return;
			}
		}
		
		Node node = new Node(key, value);		// key 와 value 값을 가진 Node 생
		bucket.add(node);						// 해당 데이터를 bucket 에 넣어준다.
		this.size++;
	}

	@Override
	public V get(K key) {
		
		int idx = this.hash(key);
		List<Node> bucket = this.buckets[idx];
		
		// 알맞은 key 값을 찾았다면 해당 값 리턴
		for (Node node : bucket) {
			if (node.key.equals(key)) {
				return node.data;
			}
		}
		
		return null;
	}

	@Override
	public boolean delete(K key) {
		
		int idx = this.hash(key);
		List<Node> bucket = this.buckets[idx];
		
		for (Iterator<Node> iter = bucket.iterator(); iter.hasNext();) {
			Node node = iter.next();
			if (node.key.equals(key)) {
				iter.remove();
				this.size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(K key) {
		
		int idx = this.hash(key);
		List<Node> bucket = this.buckets[idx];
		
		for (Node node : bucket) {
			if (node.key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	// 해시 함수 - 매개변수 key 의 타입별로 연산을 구현하는 것은 굉장히 LowLevel 구현이다. 현재로서는 제너릭으로 구현.
	private int hash(K key) {
		
		int hash = 0;
		for (Character ch : key.toString().toCharArray()) {
			hash += (int) ch;	// 아스키값을 hash 에 더한다.
		}
		
		return hash % this.bucketSize;	// 모듈러 연산
	}
	
	
	private class Node {
		K key;
		V data;
		
		Node(K key, V data){
			this.key = key;
			this.data = data;
		}
	}
}

```



#### `해시 충돌 Hash Collision`

* 해시 자료구조의 성능을 떨어뜨리는 가장 큰 원인이다.

* 해시 함수를 구현할 때 해시 충돌이 가능하면 적게 발생하도록 구현해야 한다. (아예 충돌을 없애는 것은 불가능..)

* #### `비둘기 집 원리` 

  * N + 1 개의 물건으 N 개의 상자에 넣을 때 적어도 어느 한 상자에는 두 개 이상의 물건이 들어있다.
  * 해시 테이블에서는 인덱스의 개수가 한정되어 있다. 우리가 사용 가능한 데이터(키)의 숫자는 그 보다 많다. 또한 해시 함수의 특성 상 무한대의 key 입력도 가능하다. 즉, 해시 충돌이 발생할 수 밖에 없는 구조이다.

* #### `Birthday Problem`

  * 임이의 사람 N 명이 모였을 때 그 중에 생일이 같은 두 명이 존재할 확률
  * 생일의 가능한 가짓수는 355 개 (2월 29일 포함)
  * 366명 이상 모여야 생일이 같은 경우가 존재하게 될까? -> 실제로는 23명만 나와도 50.7% 의 확률로 존재

* `결론은 key 의 개수가 해시 테이블의 인덱스에 일정 비율 이상의 데이터가 채워져도 충돌 발생 가능성이 굉장히 높다.`



#### 해시 충돌 해결 방법

#### `1. Chaining`

* 특정 key 에 해시 함수를 이용해 인덱스화해서 데이터를 저장
* 이후 다른 key 를 넣었는데 같은 인덱스를 가리킨다면, 처음 저장된 bucket 과 이후 저장될 bucket 이 마치 LinkedList 처럼 다음 노드를 가리키게 하는 방식으로 노드에 새로운 데이터를 저장한다.
* 또 key 값이 동일한 해시 값으로 같은 인덱스를 가리킨다면, 같은 방식으로 bucket 을 더 이어 붙인다.

* 데이터에 접근할 때 시간 복잡도는 최초 인덱스를 찾을 때 `O(1)` , 그 후 해당 인덱스에서 value 를 찾으려면 연결된 노드가 길어질수록 탐색 시간이 비례하여 늘어나는 `O(N)` 의 시간 복잡도를 갖는다.
* 최악의 경우 해시 테이블임에도 `O(N)` 의 시간 복잡도가 생긴다.
* Chaining 은 전통적인 해결 방식이지만, 최근 List 대신 `Tree` 라는 자료구조를 사용해 시간 복잡도를 조금 더 단축시킨다.



#### `2. Open Addressing`

* 충돌 발생시 다른 버킷에 데이터를 저장
* 다른 버킷을 어떻게 찾느냐에 따라 방법이 나뉜다.
  1) `선형 탐색`
     * 해시 충돌 시 n 칸을 건너뛴 다음 버킷에 저장 (n 은 정해지지 않은 값.)
     * 계산이 단순하다.
     * 검색 시간이 많이 소요된다. -> n 칸을 건너뛰어도 그 자리에 데이터가 또 있다면 n 칸을 한 번 더 건너뛴다. 이러한 탐색 과정은 시간을 소요한다 -> `O(N)`
     * 데이터들이 특정 위치에만 밀집하게 될 수 있다.(`clustering`) -> 좋은 해시 함수는 키를 고르게 분포시킨다. 밀집 될수록 충돌이 발생해 데이터의 위치를 재탐색을 해야 할 일이 많아질 수 있다.
  2) `제곱 탐색`
     * N^2 칸 (1, 4, 9, 16, ...) 을 건너뛴 버킷에 데이터를 저장
     * 데이터들이 특정 위치에 밀집하는 문제를 해결 가능
     * 하지만 만약 처음 해시 값이 같게 나온다면 여전히 N 번의 탐색을 해야 한다.
  3) `이중 해시`
     * 해시 값에 다른 해시 함수를 한번 더 적용
     * Hashfunction1(): 최초의 해시 값을 구한다.
     * Hashfunction2(): 충돌 발생시 이동 폭을 구한다.
     * 최초 해시 값이 같더라도 이동 폭이 다르기 때문에 밀집 현상(`clustering`)을 해결 가능 -> 규칙성을 없앤 방법