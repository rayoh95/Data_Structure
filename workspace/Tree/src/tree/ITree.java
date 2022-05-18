package tree;

public interface ITree<T> {
	void insert(T val);
	void delete(T val);
	boolean containg(T val);
	int size();
}
