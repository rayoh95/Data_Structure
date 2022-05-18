package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {
	
	private Node root;
	private int size;
	
	// 서브 트리도 모두 BST 의 성질을 갖고 있다.
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}
	
	public T min() {
		return this.minNode(this.root);
	}
	
	private T minNode(Node node) {
		T minData = node.data;
		
		while(node.left != null) {
			minData = node.left.data;
			node = node.left;
		}
		
		return minData;
	}
	
	public T max() {
		return this.maxNode(this.root);
	}
	
	private T maxNode(Node node) {
		T maxData = node.data;
		
		while(node.right != null) {
			maxData = node.right.data;
			node = node.right;
		}
		
		return maxData;
	}
	
	@Override
	public void insert(T val) {
		this.root = this.insertNode(this.root, val);
		this.size++;
	}
	
	private Node insertNode(Node node, T val) {
		// 자신의 자리를 찾았을 때
		if (node == null) {
			return new Node(val);
		}
		if (val.compareTo(node.data) < 0) {
			node.left = insertNode(node.left, val);
		}
		else if (val.compareTo(node.data) > 0) {
			node.right = insertNode(node.right, val);
		}
		
		return node;
	}

	@Override
	public void delete(T val) {
		this.deleteNode(this.root, val);
	}
	
	private Node deleteNode(Node node, T val) {
		// 삭제할 데이터를 찾지 못한 경우
		if (node == null) return null;
		
		if (val.compareTo(node.data) < 0) {
			node.left = deleteNode(node.left, val);
		}
		else if (val.compareTo(node.data) > 0) {
			node.right = deleteNode(node.right, val);
		}
		else {
			// val == node.data
			this.size--;
			if (node.left == null) {
				return node.right;
			}
			else if (node.right == null) {
				return node.left;
			}
			// 오른쪽 서브트리의 최솟값을 삭제할 위치에 구현하는 방법.
			node.data = this.minNode(node.right);
			node.right = deleteNode(node.right, node.data);
		}
		
		return node;
	}

	@Override
	public boolean containg(T val) {
		return this.containsNode(this.root, val);
	}
	
	private boolean containsNode(Node node, T val) {
		if (node == null) {
			return false;
		}
		// a.compareTo(b) 를 했을 때
		// a < b -> -1 반환
		// a == b -> 0 반환
		// a > b -> 1 반환
		if (val.compareTo(node.data) == 0) {
			return true;
		}
		
		if (val.compareTo(node.data) < 0) {
			return containsNode(node.left, val);
		}
		
		// val.compareTo(node.data) > 0 인 경우
		return containsNode(node.right, val);
	}

	@Override
	public int size() {
		return this.size;
	}
	
	// 전위 탐색
	public List<T> preOrder() {
		return this.preorderTree(root, new ArrayList<>());
	}
	
	private List<T> preorderTree(Node node, List<T> visited) {
		if (node == null)	return visited;
		
		visited.add(node.data);
		preorderTree(node.left, visited);
		preorderTree(node.right, visited);
		
		return visited;
	}
	
	// 중위 탐색
	public List<T> inOrder() {
		return this.inorderTree(root, new ArrayList<>());
	}
	
	private List<T> inorderTree(Node node, List<T> visited) {
		if (node == null)	return visited;
		
		inorderTree(node.left, visited);
		visited.add(node.data);
		inorderTree(node.right, visited);
		
		return visited;
	}
	
	// 후위 탐색
	public List<T> postOrder() {
		return this.postorderTree(root, new ArrayList<>());
	}
	
	private List<T> postorderTree(Node node, List<T> visited) {
		if (node == null)	return visited;
		
		postorderTree(node.left, visited);
		postorderTree(node.right, visited);
		visited.add(node.data);
		
		return visited;
	}
	
	
	private class Node {
		T data;
		Node left;
		Node right;
		
		Node(T data) { this.data = data; }
	}
	
	
	
}
