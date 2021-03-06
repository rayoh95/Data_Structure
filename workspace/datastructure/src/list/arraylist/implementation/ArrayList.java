package list.arraylist.implementation;

public class ArrayList {
	
	private int size = 0;	// 리스트의 크기를 담을 변수
	private Object[] elementData = new Object[100]; // 객체 내부적으로 사용할 배열이므로 외부에 노출되지 않도록 private 을 선언
	
	public boolean addFirst(Object element) {
		return add(0, element);
	}

	public boolean addLast(Object element) {
		
		elementData[size] = element;
		size++;
		return true;
	}
	
	public boolean add(int index, Object element) {
		
		for (int i = size-1; i >= index; i--) {
			elementData[i+1] = elementData[i];
		}
		elementData[index] = element;
		size++;
		return true;
	}
	
	public Object remove(int index) {
		
		Object removed = elementData[index];
		
		for (int i = index+1; i <= size-1; i++) {
			elementData[i-1] = elementData[i];
		}
		size--;
		elementData[size] = null;
		
		return removed;
	}
	
	public Object removeFirst() {
		return remove(0);
	}
	
	public Object removeLast() {
		return remove(size-1);
	}
	
	public Object get(int index) {
		
		return elementData[index];
	}
	
	public int size() {
		return size;
	}
	
	public int indexOf(Object o) {
		
		for (int i = 0; i < size; i++) {
			if (o.equals(elementData[i])) {
				return i;
			}
		}
		return -1;
	}
	

	public ListIterator listIterator() {
		return new ListIterator();
	}
	
	
	public class ListIterator {
		
		private int nextIndex = 0;
		
		// pointer 느낌. 값을 반환하고 다음 인덱스를 가리킨다.
		public Object next() {
			
			return elementData[nextIndex++];
		}
		
		public boolean hasNext() {

			return nextIndex < size();
		}
		
		public Object previous() {
			
			return elementData[--nextIndex];
		}
		
		public boolean hasPrevious() {
			return nextIndex > 0;
		}
		
		public void add(Object element) {
			ArrayList.this.add(nextIndex++, element);
		}
		
		public void remove() {
			ArrayList.this.remove(nextIndex - 1);
			nextIndex--;
		}
	}
	
	public String toString() {
		String str = "[";
		for (int i = 0; i < size; i++) {
			str += elementData[i];
			if (i < size-1) {
				str += ",";				
			}
		}
		return str + "]";
	}
}
