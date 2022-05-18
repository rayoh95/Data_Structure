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
