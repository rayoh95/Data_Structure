package list;

public class MyDoubleLinkedList<T> implements IList<T> {
	
	private Node head;
	private Node tail;
	private int size;
	
	public MyDoubleLinkedList() {
		
		this.size = 0;
		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}
	
	
	@Override
	public void add(T t) {
		
		Node last = this.tail.prev;
		Node node = new Node(t, last, this.tail);
		last.next = node;
		this.tail.prev = node;
		this.size++;
	}

	@Override
	public void insert(int index, T t) {
		
		Node prev = null;
		Node curr = null;
		int i = 0;
		
		if (index < this.size / 2) {
			
			prev = this.head;
			curr = this.head.next;
			
			while (i++ < index) {
				prev = prev.next;
				curr = curr.next;
			}
			
		}
		else {
			
			curr = this.tail;
			prev = this.tail.prev;
			
			while (i++ < (this.size - index)) {
				curr = curr.prev;
				prev = prev.prev;
			}
			
		}
		
		Node node = new Node(t, prev, curr);
		prev.next = node;
		curr.prev = node;

		this.size++;
		
	}

	@Override
	public void clear() {
		
		this.size  = 0;
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	@Override
	public boolean delete(T t) {
		return false;
	}

	@Override
	public boolean deleteByIndex(int index) {
		
		Node prev = null;
		Node curr = null;
		Node next = null;
		int i = 0;
		
		if (index < this.size / 2) {
			
			prev = this.head;
			curr = this.head.next;
			
			while (i++ < index) {
				prev = prev.next;
				curr = curr.next;
			}
			
			prev.next = curr.next;
			curr.next.prev = prev;
			curr.next = null;
			curr.prev = null;
		}
		else {
			
			curr = this.tail.prev;
			next = this.tail;
			
			while (i++ < (this.size - index - 1)) {
				next = next.prev;
				curr = curr.prev;
			}
			
			next.prev = curr.prev;
			curr.prev.next = next;
			curr.next = null;
			curr.prev = null;
			
		}
		
		this.size--;
		
		return false;
	}

	@Override
	public T get(int index) {
		
		if (index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		int i = 0;
		Node curr = null;
		
		if (index < this.size / 2) {
			
			curr = this.head.next;
			
			while (i++ < index) {
				curr = curr.next;
			}
		}
		else {
			
			curr = this.tail.prev;
			
			while (i++ < (this.size - index - 1)) {
				curr = curr.prev;
			}
		}
		return curr.data;
	}

	@Override
	public int indexOf(T t) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(T t) {	// O(N) -> O(N/2)
		
		Node curr1 = this.head.next;
		Node curr2 = this.tail.prev;
		
		while (curr1 != null && curr2 != null) {
			
			if (curr1.data != null & curr1.data.equals(t)) {
				return true;
			}
			
			curr1 = curr1.next;
			
			if (curr2.data != null & curr2.data.equals(t)) {
				return true;
			}
			
			curr2 = curr2.prev;
			
		}
		
		return false;
	}

	@Override
	public int size() {
		return this.size;
	}

	
	private class Node {
		
		T data;
		Node next;
		Node prev;
		
		
		// 생성자
		Node(T data) { this.data = data; }
		
		// 생성자 오버로딩
		Node (T data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}
