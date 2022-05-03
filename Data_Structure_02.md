### 자료구조.알고리즘



#### List

* 선형적인 자료구조
* 데이터를 일렬로 늘여 놓은 형태를 갖는 자료구조이다.
* `순서`가 존재한다.



#### List 의 연산

* 데이터 삽입
* 데이터 삭제
* 리스트 탐색



#### 1. ArrayList

* 배열 기반의 리스트이다.
* 메모리 공간을 연속적으로 사용한다. (Random Access Memory)
* 실제 컴퓨터 메모리가 연산하기 쉬운 구조를 갖고 있다. -> 접근 속도가 빠르다. -> 시간 복잡도 : `O(1)`
* 데이터를 추가 시, `O(1)` 시간 복잡도를 갖는다. 다만, 배열이 꽉 차있는 경우 배열을 재할당 해야하기 때문에 이 때는 `O(N)`
* 데이터의 삽입, 삭제 시 데이터의 양에 비례해 이동시켜야 하는 데이터의 양이 많아지므로 시간 복잡도가 늘어난다. -> 시간 복잡도 : `O(N)`





#### 2. LinkedList

* `Node` 라는 `객체`로 구성되어 있다.
* 각 Node 는 데이터를 저장하는 필드와 `다음 노드를 가리키는 포인터`가 존재.
* 맨 앞 Node 는 Head 라고 부르고, 마지막 Node 는 Tail 이라고 부른다.
* Random Access 가 불가능. N개의 노드를 갖는 LinkedList 의 검색 시간 복잡도 : `O(N)`
* 추가 작업 역시 마지막 Node 까지 도달해 추가하고자 하는 데이터를 추가. 시간 복잡도 : `O(N)`
* 중간에 데이터를 삽입할 때 데이터를 ArrayList 처럼 전부 밀어줘야 할 필요가 없다. 하지만 삽입할 위치까지 도달하는데 걸리는 시간 복잡도 역시 `O(N)` 이다.
* 단, 가장 앞에 데이터를 삽입할 때, Head 의 포인터만 설정하면 되기 때문에 작업이 수월하다.
* Node 를 삭제할 때, 삭제할 노드를 찾는데 `O(N)` 의 시간 복잡도가 생긴다. 단, ArrayList 는 삭제 후 뒤의 데이터를 앞으로 끌어오면서 O(N) 의 시간 복잡도가 한 번 더 생긴 반면, LinkedList 는 포인터만 바꾸면 된다.

##### `장점`

* 배열의 복사나 재할당 없이 데이터 추가 가능
* 유연한 공간

##### `단점`

* 데이터 접근에 대한 시간이 늘어남



##### LinkedList 와 ArrayList 는 tradeoff 관계이다.





#### 3. DoubleLinkedList

* LinkedList 에서 `prev 정보`를 추가한 것 이다.

* prev 데이터를 추가한 만큼 메모리를 더 잡아먹는 List 이다.

* 단, 데이터를 마지막에 위치에 추가할 때, Tail 노드( 더미 노드 )에 prev 로 바로 설정이 가능하므로 시간 복잡도는 `O(1)` 을 갖는다.

* 또한 index 를 통한 데이터 조회의 연산소요 시간은 역시 `N/2` 으로 줄어들지만, `점근 표기법의 규칙`에 따라 `O(N)` 이라고 표기.





`아래 코드를 기반으로 ArrayList 와 LinkedList 그리고 DoubleLinkedList 를 구현해보자.`

```java
public interface IList<T> {
	
	void add(T t);
	
	void insert(int index, T t);
	
	void clear();
	
	boolean delete(T t);
	
	boolean deleteByIndex(int index);
	
	T get(int index);
	
	int indexOf(T t);
	
	boolean isEmpty();
	
	boolean contains(T t);
	
	int size();
	
}

```





