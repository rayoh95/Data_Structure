### 자료구조.알고리즘

<hr>



### Stack

* ##### `후입선출(Last-In-First-Out : LIFO)`

* 역순으로 돌아가는 것

  * 인터넷 브라우저의 뒤로 가기
  * Ctrl + z

##### 때문에 리스트의 삽입도(push), 삭제(pop)도 마지막 요소를 기준으로 이루어진다. 



#### Stack 에서 중요한 메서드

1. push() : 데이터를 삽입(쌓는) 것
2. pop() : 리스트에 있는 데이터 중 가장 마지막에 삽입되었던 데이터가 삭제하고, 그 데이터를 가져온다.
3. top(), peek() : 맨 마지막에 있는 데이터를 가져온다. 



`아래의 코드를 기반으로 Stack 을 구현해보자.`

```java
package stack;

public interface IStack<T> {
	void push(T data);
	T pop();
	T peek();
	int size();
}

```



`구현`

```java
package stack;

public class MyStack<T> implements IStack<T> {
	
	private int size;
	private Node head;	// stack 에서 head 는 출구라고 생각하자.(가장 최신의 데이터를 가리킨다.)
	
	public MyStack() {
		this.size = size();
		this.head = new Node(null);	// dummy Node
	}

	@Override
	public void push(T data) {
		
		Node node = new Node(data, this.head.next);	// this.head.next 는 null 을 가리킨다. 삽입되는 node 가 이 null 을 가리키게 한다.
		this.head.next = node;		// head.next 는 새로 생성되는 node 를 가리킨다.
		this.size++;
		
	}

	@Override
	public T pop() {
		
		if (this.isEmpty()) { return null; }
		
		Node curr = this.head.next;
		this.head.next = curr.next;
		curr.next = null;
		this.size--;
		return curr.data;
	}

	@Override
	public T peek() {
		
		if (this.isEmpty()) { return null; }
		
		return this.head.next.data;
	}

	private boolean isEmpty() {
		return this.head.next == null;
	}
	
		
	@Override
	public int size() {
		return this.size;
	}
	

	private class Node {
		T data;
		Node next;
		
		Node(T data) { this.data = data; }
		
		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

}

```



<hr>



### Queue

* ##### `선입선출(First-In-Fast-Out : FIFO)`

* 순서가 보장된 처리

  * 사용자가 몰린 서버



##### Queue 에서 중요한 메서드

1. `push()`, offer(), add() : 데이터 삽입
2. `pop()`, poll() : 데이터 빼오기
3. `peek()` : 데이터 조회



`enqueue` : Queue 에 데이터가 입력되는 동작

`dequeue` : Queue 에 데이터가 출력되는 동작



##### 만약 Queue 가 배열로 동작한다면 어떨까?

* 데이터를 전부 삽입했다고 가정하자.
* 데이터를 삭제한다면 배열의 첫 번째 요소가 삭제가 될 것이다.
* 삭제 후 배열의 첫 번째 공간은 비어있게 된다.
* 남은 뒤의 요소들을 전부 앞으로 땡겨와야 한다.
* 이렇게 할 경우 `시간 복잡도가 O(N) 까지 상승한다.`

즉, Queue 는 선형구조이지만 비효율성 때문에 배열 기반으로 구현해서 쓰지 않는다.



`아래의 코드를 기반으로 Queue 을 구현해보자.`

```java
package queue;

public interface IQueue<T> {
	void offer(T data);
	T poll();
	T peek();
	int size();
	void clear();
	boolean isEmpty();
}
```



`구현`

```java
package queue;

public class MyLinkedQueue<T> implements IQueue<T> {

	private Node head;
	private Node tail;
	private int size;
	
	public MyLinkedQueue() {
		this.size = 0;
		this.head = new Node(null);	// dummy node
		this.tail = this.head;
	}

	@Override
	public void offer(T data) {
		
		Node node = new Node(data);
		this.tail.next = node;
		this.tail = this.tail.next;
		this.size++;
	}

	@Override
	public T poll() {
		
		// Queue 가 비어있는 상태에서 에러
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		
		Node node = this.head.next;	// head 의 next 가 가장 처음 들어온 데이터이다.
		this.head.next = node.next;
		node.next = null;
		this.size--;
		
		// 데이터 삭제 후 데이터가 남아있지 않다면
		if (this.head.next == null) { this.tail = this.head; }
		return node.data;
	}

	@Override
	public T peek() {
		
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		
		return this.head.next.data;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		
		this.head.next = null;
		this.tail = head;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.head.next == null;
	}
	
	
	private class Node {
		T data;
		Node next;
		
		Node(T data) { this.data = data; }
		
		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
```





#### `큐 - 원형 큐(Circular Queue)`

큐를 구현하는 방법은 크게 두 가지가 있다.

1. LinkedList 를 이용한 구현
2. 배열을 이용한 원형 큐 구현
   * 배열을 이용한 선현 큐 구현은 비효율적이다.



##### 원형 큐는 배열로 구현하되, 단점을 보완한 구조라고 보면 된다. - front , rear

1. front 와 rear 가 같은 위치를 가리키고 있다. (아직 아무 데이터도 들어오지 않은 상태)
2. 데이터를 한 개 삽입한다.
3. rear 가 그 데이터의 위치를 가리킨다.
4. 데이터가 들어올 때 마다 가장 나중에 들어온 데이터를 rear 가 가리킨다.

데이터를 가져올 때

1. front 의 인덱스를 통해 데이터를 가져온다.
2. front 의 위치를 한 칸 뒤로 위치기킨 후, 그 자리의 데이터를 가져온다.
3. 마지막에는 front 와 rear 의 위치가 같아진다.
4. 다만 처음과 달리 front 와 rear 의 인덱스는 0 이 아니다.

##### 인덱스가 0 인지의 여부와는 관계없이 front 와 rear 가 같은 위치이면 비어있는 Queue 이다.

이후 배열에 데이터를 계속 추하가하다가 배열의 크기가 다 차면 rear 는 0 번 인덱스부터 다시 데이터를 채운다. -> 원형적으로 구동

##### `적어도 배열의 한 칸은 비워둔다.` 그래야 꽉 찬 상태와 비어있는 상태를 구분할 수 있기 때문이다.



#### Circular Queue

* 고정된 크기의 배열로 구현
* 데이터가 꽉 차지 않는다면 데이터를 무한하게 넣고 뺄 수 있다.
* 데이터 접근 속도도 빠르다.
* 데이터를 넣고 빼는데 있어서 추가 연산의 필요도 없다.
* 크기가 정해져 있다는 단점이 있다.
* 인덱스의 위치를 넘어선 데이터의 인덱스 (모듈러 연산) : `Index % QueueSize`
  * Ex) Queue size = 5
  * Idx = 1 -> index = 1
  * idx = 2 -> index = 2
  * Idx = 6 -> index = 1
  * Idx = 7 -> index = 2



##### Queue 에서 추가적으로 중요한 메서드

1. isFull()
2. isEmpty()



`구현`

```java
package queue;

import java.nio.channels.IllegalSelectorException;

public class MyCircularQueue<T> implements IQueue<T>{

	private T[] elements;
	private int front;
	private int rear;
	private int maxSize;
	
	public MyCircularQueue(int size) {
		this.elements = (T[]) new Object[size + 1];	// +1 을 한 이유는 빈 상태와 꽉 찬 상태를 구분하기 위해 넣은 더미 공간
		this.front = 0;
		this.rear = 0;
		this.maxSize = size + 1;
	}
	
	
	@Override
	public void offer(T data) {
		
		if (this.isFull()) {
			throw new IllegalSelectorException();
		}
		
		this.rear = (this.rear + 1) % this.maxSize;
		this.elements[this.rear]= data; 
	}

	@Override
	public T poll() {
		
		if (this.isEmpty()) {
			throw new IllegalSelectorException();
		}
		
		this.front = (this.front + 1) % this.maxSize;
		return this.elements[this.front];	// 굳이 해당 인덱스의 요소를 0으로 초기화할 필요가 없다. 비어있는 여부와 상관없이 데이터를 넣기 때
	}

	@Override
	public T peek() {
		
		if (this.isEmpty()) {
			throw new IllegalStateException();
		}
		
		return this.elements[this.front + 1];
	}

	@Override
	public int size() {
		
		if (this.front <= this.rear) {
			return this.rear - this.front;
		}
		else {
			return this.maxSize - this.front + this.rear;
		}
	}

	@Override
	public void clear() {
		this.front = 0;
		this.rear = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.front == this.rear;
	}
	
	private boolean isFull() {
		return (this.rear + 1) % this.maxSize == front;	// 모듈러 연산
	}

}
```





