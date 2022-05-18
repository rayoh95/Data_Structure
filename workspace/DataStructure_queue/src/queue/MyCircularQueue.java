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
