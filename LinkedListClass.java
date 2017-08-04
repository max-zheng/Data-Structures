import java.util.Objects;

public class LinkedListClass<T> {
	
	private int size;
	private LinkedListNode first;
	
	public LinkedListClass() {
		this.size = 0;
		this.first = null;
	}

	public LinkedListClass(Object[] list) {
		size = list.length;
		if(size == 0) {
			return;
		}
		first = new LinkedListNode();
		LinkedListNode next = first;
		for(int i = 0; i < list.length - 1; i++) {
			next.setData(list[i]);
			next.setNext(new LinkedListNode());
			next = next.getNext();
		}
		next.setData(list[list.length - 1]);
	}
	
	public LinkedListClass(LinkedListNode first) {
		this.first = first;
		this.size = determineSize();
	}
	
	public boolean add(T obj) {
		LinkedListNode last = getReferenceToLastNode();
		if(last == null) first = new LinkedListNode(obj);
		else last.setNext(new LinkedListNode(obj));
		size++;
		return true;
	}

	public boolean add(int index, T obj) {
		if(index >= size) return false;
		size++;
		LinkedListNode newNode = new LinkedListNode(obj);
		if(first == null) {
			first = newNode;
			return true;
		}
		if(index > 0) {
			LinkedListNode previousNode = getReferenceToSpecificNode(index - 1);
			newNode.setNext(previousNode.getNext());
			previousNode.setNext(newNode);
		}
		else {
			newNode.setNext(first);
			first = newNode;
		}
		return true;
	}
	
	public void addFirst(T obj) {
		LinkedListNode newFirst = new LinkedListNode(obj);
		newFirst.setNext(this.first);
		this.first = newFirst;
		size++;
	}
	
	public void addLast(T obj) {
		add(obj);
		size++;
	}
	
	public void clear() {
		this.first = null;
		this.size = 0;
	}

	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	//retrieves but does not remove head of list
	@SuppressWarnings("unchecked")
	public T element() {
		return (T) first;
	}

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

	@SuppressWarnings("unchecked")
	public T getLast() {
		LinkedListNode lastNode = getReferenceToLastNode();
		return (lastNode != null) ? (T) lastNode.getData() : null;
	}

	public int indexOf(Object obj) {
		LinkedListNode next = first;
		for(int i = 0; i < size; i++) {
			if(next.getData().equals(obj)) return i;
			next = next.getNext();
		}
		return -1;
 	}

	public int lastIndexOf(Object obj) {
		int index = -1;
		LinkedListNode next = first;
		for(int i = 0; i < size; i++) {
			if(next.getData().equals(obj)) index = i;
			next = next.getNext();
		}
		return index;
	}
	
	@SuppressWarnings("unchecked")
	public T set(int index, T obj) {
		if(index >= size) throw new IndexOutOfBoundsException("Index out of bounds!");
		LinkedListNode node = getReferenceToSpecificNode(index);
		Object previousObj = node.getData();
		node.setData(obj);
		
		return (T) previousObj;
	}
	
	public int size() {
		return size;
	}
	
	public Object[] toArray() {
		if(size == 0) {
			return new Object[0];
		}
		Object[] array = new Object[size];
		int index = 0;
		array[index] = first.getData();
		LinkedListNode next = first;
		while(next.getNext() != null) {
			next = next.getNext();
			array[++index] = next.getData();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public boolean isEmpty() {
		return size == 0;
	}

	public T remove(int index) {
		if(index >= size) return null;
		size--;
		if(index == 0) {
			LinkedListNode firstNode = first;
			first = firstNode.getNext();
			return (T) firstNode.getData();
		}
		LinkedListNode previousNode = getReferenceToSpecificNode(index - 1);
		LinkedListNode removedNode = previousNode.getNext();
		previousNode.setNext(removedNode.getNext());
		return (T) removedNode.getData();
	}

	public boolean remove(Object obj) {
		LinkedListNode next = first;
		if(next.getData().equals(obj)) {
			first = next.getNext();
			size--;
			return true;
		}
		boolean found = false;
		for(int i = 0; i < size; i++) {
			if(next.getNext() != null && next.getNext().getData().equals(obj)) {
				found = true;
				break;
			}
			next = next.getNext();
		}
		if(found == false) return false;
		next.setNext(next.getNext().getNext());
		size--;
		return true;
	}

	public boolean removeAll(Object obj) {
		boolean removed = false;
		while(remove(obj) == true) {
			removed = true;
			continue;
		}
		return removed;
	}
	
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
