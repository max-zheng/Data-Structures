import java.util.Objects;

public class LinkedListNode {

	private Object data;
	private LinkedListNode next;
	
	public LinkedListNode() {
		this.data = null;
		this.next = null;
	}
	
	public LinkedListNode(Object data) {
		this.data = data;
		this.next = null;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public void setNext(LinkedListNode next) {
		this.next = next;
	}
	
	public LinkedListNode getNext() {
		return this.next;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof LinkedListNode) {
			LinkedListNode o = (LinkedListNode) other;
			return o.getData().equals(data);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(data, next);
	}
	
}
