package list.doublylinkedlist.implementation.copy;

public class Main {

	public static void main(String[] args) {
		DoublyLinkedList numbers = new DoublyLinkedList();
		
		numbers.addFirst(30);
		numbers.addFirst(20);
		numbers.addFirst(10);
		
		numbers.addLast(10);
		numbers.addLast(20);
		numbers.addLast(30);
		
		System.out.println(numbers.node(0));	// index 번호 0번의 값이 출력되어야 한다. 내부적으로 쓰는걸 지양해야하는 메소드. 객체 그 자체를 반환하는 것은 좋지 않다.
		
		numbers.add(1, 15);
		
		System.out.println(numbers.removeFirst());
		
		System.out.println(numbers.remove(0));
		
		System.out.println(numbers.removeLast());
		
		System.out.println(numbers);
		System.out.println(numbers.size());
		
		System.out.println(numbers.get(0));
		
		System.out.println(numbers.indexOf(20));
		
		System.out.println("==============================");
		
		DoublyLinkedList.ListIterator i = numbers.listIterator();
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		System.out.println(i.next());
		System.out.println(i.hasNext());
		
		System.out.println("==============================");
		
		DoublyLinkedList.ListIterator j = numbers.listIterator();
		j.add(5);;
		System.out.println(numbers);
		System.out.println(j.next());
		j.add(15);
		System.out.println(numbers);
		System.out.println(j.next());
		j.remove();
		
		System.out.println("==============================");
		
		DoublyLinkedList.ListIterator k = numbers.listIterator();
		
		while (k.hasNext()) {
			if ((int)k.next() == 30) {
				k.remove();
			}
		}
		
	}

}
