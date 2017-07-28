import java.util.Objects;

public class LinkedListClass<T> {
	
	private int size;
	private LinkedListNode first;
	
	public LinkedListClass() {
		this.size = 0;
		this.first = null;
	}
	
	public LinkedListClass(LinkedListNode first) {
		this.first = first;
		this.size = determineSize();
	}
	
	public boolean add(T obj) {
		LinkedListNode last = getReferenceToLastNode();
		if(last == null) {
			first = new LinkedListNode(obj);
		}
		else {
			last.setNext(new LinkedListNode(obj));
		}
		size++;
		return true;
	}
//	
//	public boolean add(int index, T obj) {
//		
//	}
	
//	public void addFirst(T obj) {
//		
//	}
//	
	public void addLast(T obj) {
		add(obj);
		size++;
	}
	
	public void clear() {
		this.first = null;
		this.size = 0;
	}
//	
//	public boolean contains(Object obj) {
//		
//	}
//	
//	//retrieves but does not remove head of list
//	public T element() {
//		
//	}
//	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if(index >= size) return null;
		return (T) getReferenceToSpecificNode(index).getData();
	}
//	
	@SuppressWarnings("unchecked")
	public T getFirst() {
		if(this.first == null) return null;
		return (T) this.first.getData();
	}
//	
//	public T getLast() {
//		
//	}
//	
//	public int indexOf(Object obj) {
//		
//	}
//	
//	public int lastIndexOf(Object obj) {
//		
//	}
	
	@SuppressWarnings("unchecked")
	public T set(int index, T obj) {
		if(index >= size) throw new IndexOutOfBoundsException("Index out of bounds!");
		LinkedListNode node = getReferenceToSpecificNode(index);
		Object previousObj = node.getData();
		node.setData(obj);
		
		return (T) previousObj;
	}
	
	public int size() {
		return this.size;
	}
//	
//	public Object[] toArray() {
//		
//	}
//	
//	public boolean isEmpty() {
//		
//	}
//	
//	public T remove(int index) {
//		
//	}
//	
//	public boolean remove(Object obj) {
//		
//	}
//	
//	public boolean removeAll(Object obj) {
//		
//	}
	
	@Override
	public String toString() {
		if(size == 0) {
			return "{}";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		LinkedListNode next = first;
		while(next != null) {
			builder.append(next.toString() + ",");
			next = next.getNext();
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("}");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof LinkedListClass) {
			LinkedListClass o = (LinkedListClass) other;
			LinkedListNode next = first;
			LinkedListNode oNext = o.getReferenceToSpecificNode(0);
			while(next.getNext() != null || oNext.getNext() != null) {
				if(null == next.getNext()) return false;
				if(null == oNext.getNext()) return false;
				if(!next.getNext().equals(oNext.getNext())) return false;
				
				next = next.getNext();
				oNext = oNext.getNext();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(first == null) return 0;
		int hashcode = 0;
		LinkedListNode next = first;
		hashcode += Objects.hash(next.getData());
		while(next.getNext() != null) {
			hashcode += Objects.hash(next.getNext().getData());
		}
		return hashcode;
	}
	
	//checks the size of the linkedlist
	private int determineSize() {

		LinkedListNode next = first;
		if(next == null) {
			return 0;
		}
		int count = 1;
		while(next.getNext() != null) {
			count++;
			next = next.getNext();
		}
		return count;
	}

	private LinkedListNode getReferenceToLastNode() {
		if(first == null) {
			return null;
		}
		LinkedListNode next = first;
		while(next.getNext() != null) {
			next = next.getNext();
		}
		return next;
	}
	
	private LinkedListNode getReferenceToSpecificNode(int index) {
		if(index >= size) return null;
		LinkedListNode next = first;
		for(int i = 0; i < index; i++) {
			next = next.getNext();
		}
		return next;
	}
}
