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
