
public class HeapDemo {
	public static void main(String[] args) {
		Heap hp = new Heap(20);
		hp.enqueue("A");
		hp.enqueue("B");
		hp.enqueue("C");
		hp.enqueue("D");
		hp.dequeue();
		System.out.println(hp);
		hp.enqueue("E");
		hp.dequeue();
		hp.dequeue();
		System.out.println(hp);
		//hp.dequeue();
		hp.enqueue("F");
		//hp.dequeue();
		System.out.println(hp);
	}
}
