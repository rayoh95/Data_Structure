##### 2-2. Linked list

STORAGE(HDD/SSD)  -데이터->  MEMORY(DRAM)  -데이터->  CPU

자료구조에서 가장 중요한 부품 : `MEMORY(메모리)`

##### 메모리를 효율적으로 사용하기 위한 것이 자료구조이다.



`RAM(Random Access Memory)`: 각 주소에 접근할 때 걸리는 시간이 동일하다. 즉, 찾고자 하는 데이터의 주소를 알고 있다면 굉장히 빠른 속도로 데이터를 가져올 수 있다는 중요한 특징이 있다. 이 점을 잘 활용하는 것이 최적화의 중요한 포인트이다.



##### `Array list` vs `Linked list`

* Array 는 같은 element 들이 메모리상에 연속적으로 붙어있는 특성이 있다.
* Linked 는 각각 element 들이 흩어져 있다. 대신 각 흩어진 element 들은 연결이 돼있다.



##### Linked list 의 구조

* 각 element 들이 연결되어 있는 특성으로, 내부적으로 element 라는 이름 대신 `node` 또는 `vertex` 라는 이름을 사용한다.

* 프로그래밍적으로 node 를 표현할 때는 객체를 이용한다. (C언어는 구조체로 표현)

* node 안에는 두 개의 필드(변수)를 갖고 있다.

  1. 저장되는 실제 값(Data field)

  2. Link field : 다음 node 가 무엇인가를 저장한다.

* Linked list 를 이용하기 위해서는 첫 번째 노드를 알아야한다. HEAD 에 첫 번째 node 를 의미하는 정보가 담긴다.



##### Linked list 의 동작

`https://visualgo.net/`

Linked list 는 데이터를 추가하고 삭제하는 것이 매우 빠르다 : 데이터의 전과 다음을 가르킬 노드만 알아내면 되니까.

리스트의 크기가 한정적이지 않다.





