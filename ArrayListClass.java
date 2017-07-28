import java.util.Arrays;
import java.util.Objects;

// implementation of ArrayList

public class ArrayListClass<T>{

    private T[] list;
    private int size;

    public ArrayListClass() {
        size = 0;
        list = (T[])new Object[size];
    }

    public ArrayListClass(T[] array) {
        size = array.length;
        list = array;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (Object obj : list) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        return list[index];
    }

    public void add(T obj) {
        growListByOne();
        list[list.length - 1] = obj;
    }

    public void add(int index ,T obj) {
        if(index >= list.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        growListByOne();
        for(int i = size - 1; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = obj;
    }

    public void set(int index ,T obj) {
        if(index >= list.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        list[index] = obj;
    }

    public int indexOf(T obj) {
        for(int i = 0; i < size; i++) {
            if(list[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public T remove(int index) {
        if(index >= size) throw new IndexOutOfBoundsException("Index out of bounds!");
        if(list[index] == null) return null;
        T removedObj = list[index];
        list[index] = null;
        reduceListByOneIfEmptySpaceExists();
        return removedObj;
    }

    public boolean remove(T obj) {
        int indexOfObj = indexOf(obj);
        if(indexOfObj == -1) {
        	return false;
        }
        
        T removedObj = remove(indexOfObj);
        return true;
    }

    public boolean removeAll(T obj) {
        boolean removed = false;
        while(remove(obj)) {
            removed = true;
            continue;
        }
        return removed;

    }

    public T[] toArray() {
        return list;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ArrayListClass) {
            ArrayListClass<T> o = (ArrayListClass<T>) other;
            if(this.list.equals(o.toArray())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(list, size);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("{");
        for(T obj: list) {
            buffer.append(obj.toString() + ",");
        }
        if(buffer.length() >= 2) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append("}");
        return buffer.toString();
    }

    private void growListByOne() {
        size++;
        T[] newList = (T[])new Object[size];
        for(int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    private void reduceListByOneIfEmptySpaceExists() {
        int emptySpaceIndex = -1;
        for(int i = 0; i < size; i++) {
            if(list[i] == null) {
                emptySpaceIndex = i;
                break;
            }
        }
        if(emptySpaceIndex == -1) return;
        size--;
        T[] newList = (T[])new Object[size];
        int oldListIndex = 0;
        for(int newListIndex = 0; newListIndex < newList.length; newListIndex++, oldListIndex++) {
            if(oldListIndex == emptySpaceIndex) oldListIndex++;
            
            newList[newListIndex] = list[oldListIndex];
           
        }
        list = newList;
    }
}
