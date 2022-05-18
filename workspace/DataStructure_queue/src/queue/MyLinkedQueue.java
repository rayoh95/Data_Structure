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
