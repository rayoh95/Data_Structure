package list.arraylist.implementation;


public class Main {

	public static void main(String[] args) {
		
		ArrayList numbers = new ArrayList();
		
		numbers.addLast(10);
		numbers.addLast(20);
		numbers.addLast(30);
		numbers.addLast(40);
		
		numbers.add(1, 15);
		
		numbers.addFirst(5);
		
		numbers.removeFirst();
		numbers.removeLast();
		System.out.println(numbers.remove(1));
		
		System.out.println(numbers.get(0));
		System.out.println(numbers.get(1));
		System.out.println(numbers.get(2));
		
		System.out.println(numbers.size());
		
		System.out.println(numbers.indexOf(20));	// 값 20이 위치한 인덱스 번호를 반환한다.
		
		System.out.println(numbers);
		
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
		
		// Iterator pattern
		ArrayList.ListIterator li = numbers.listIterator();
		while(li.hasNext()) {
			System.out.println(li.next());
		}
		
		while(li.hasPrevious()) {
			System.out.println(li.previous());
		}

		numbers.addLast(40);
		
		while(li.hasNext()) {
			int number = (int)li.next();
			if(number == 30) {
				li.add(35);
			}
		}
		
		System.out.println(numbers);
		
		while(li.hasNext()) {
			int number = (int)li.next();
			if(number == 30) {
				li.remove();
			}
		}
		
		System.out.println(numbers);
	}

}
