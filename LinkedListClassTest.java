import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

//remove obj
//remove index
//removeall
public class LinkedListClassTest {

	LinkedListClass<Object> list;
	LinkedListNode node;
	String expectedOutput;
	
	@Before
	public void setup() {
		list = new LinkedListClass<Object>();
		node = new LinkedListNode();
		expectedOutput = "";
	}
	@Test
	public void testToString() {
		list = createLinkedList(5);
		expectedOutput = "{0,1,2,3,4}";
		
		assertEquals(expectedOutput, list.toString());
	}
	
	@Test
	public void testToStringForSizeOneList() {
		list = createLinkedList(1);
		expectedOutput = "{0}";
		
		assertEquals(expectedOutput, list.toString());
	}
	
	@Test
	public void testToStringForEmptyList() {
		expectedOutput = "{}";
		
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testNodeEqualsForTwoEqualNodes() {
		Integer equalInt = new Integer(2);
		node = new LinkedListNode(equalInt);
		LinkedListNode otherNode = new LinkedListNode(equalInt);
		
		assertTrue(node.equals(otherNode));
	}
	
	@Test
	public void testNodeEqualsForTwoUnequalNodes() {
		node = new LinkedListNode(new Integer(2));
		LinkedListNode otherNode = new LinkedListNode(new Integer(3));
		
		assertFalse(node.equals(otherNode));
	}
	
	@Test
	public void testNodeEqualsForTwoDifferentTypes() {
		node = new LinkedListNode(new Integer(2));
		LinkedListNode otherNode = new LinkedListNode(new String("2"));
		
		assertFalse(node.equals(otherNode));
	}
	
	@Test
	public void testGetFirstForNullNode() {
		assertNull(list.getFirst());
	}
	
	@Test
	public void testGetFirstForNonNullNode() {
		node.setData(new Integer(2));
		list = new LinkedListClass(node);
		expectedOutput = "" + 2;
		
		assertEquals(expectedOutput,list.getFirst().toString());
	}
	
	@Test
	public void testNonDefaultConstructor() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4});
		expectedOutput = "{1,2,3,4}";

		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testNonDefaultConstructorWithEmptyArray() {
		list = new LinkedListClass<Object>(new Object[0]);
		expectedOutput = "{}";

		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testGetForValidIndex() {
		list = createLinkedList(5);
		
		assertEquals(2, list.get(2));
	}
	
	@Test
	public void testGetForInvalidIndex() {
		list = createLinkedList(5);
		
		assertNull(list.get(6));
	}
	
	@Test
	public void testAddToEmptyList() {
		list.add(new Integer(2));
		
		expectedOutput = "" + 2;
		
		assertEquals(expectedOutput, list.getFirst().toString());
	}
	
	@Test
	public void testAddToNonemptyList() {
		list = createLinkedList(5);
		
		list.add(new Integer(5));
		
		assertEquals(5, list.get(5));
	}

	@Test
	public void testAddIndexToNonemptyList() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,5});
		list.add(2,new Integer(3));
		expectedOutput = "{1,2,3,4,5}";

		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testAddIndexForInvalidIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,5});

		assertFalse(list.add(6,new Integer(3)));
	}

	@Test
	public void testAddIndexToFirstIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4});
		list.add(0,new Integer(0));
		expectedOutput = "{0,1,2,3,4}";

		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemove() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,3,4,5});
		expectedOutput = "{1,2,3,4,5}";

		assertEquals(new Integer(4), list.remove(2));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveInvalidIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,3,4,5});

		assertNull(list.remove(10));
	}

	@Test
	public void testRemoveZeroIndex() {
		list = new LinkedListClass<Object>(new Object[] {2,1,2,3,4,5});
		expectedOutput = "{1,2,3,4,5}";

		assertEquals(new Integer(2), list.remove(0));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveLastIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4,5,6});
		expectedOutput = "{1,2,3,4,5}";

		assertEquals(new Integer(6), list.remove(5));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveObject() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,3,4,5});
		expectedOutput = "{1,2,3,4,5}";

		assertTrue(list.remove(new Integer(4)));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveObjectNotContained() {
		list = new LinkedListClass<Object>(new Object[] {1,2,4,3,4,5});

		assertFalse(list.remove(new Integer(6)));
	}

	@Test
	public void testRemoveObjectFirstIndex() {
		list = new LinkedListClass<Object>(new Object[] {4,1,2,3,4,5});
		expectedOutput = "{1,2,3,4,5}";

		assertTrue(list.remove(new Integer(4)));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveObjectLastIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4,10});
		expectedOutput = "{1,2,3,4}";

		assertTrue(list.remove(new Integer(10)));
		assertEquals(expectedOutput, list.toString());
	}

	@Test
	public void testRemoveAllObject() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4,10});
		expectedOutput = "{1,2,3,4}";

		assertTrue(list.removeAll(new Integer(10)));
		assertEquals(expectedOutput, list.toString());
		assertEquals(4, list.size());
	}

	@Test
	public void testRemoveAllForMultipleObjects() {
		list = new LinkedListClass<Object>(new Object[] {1,2,10,3,4,10});
		expectedOutput = "{1,2,3,4}";

		assertTrue(list.removeAll(new Integer(10)));
		assertEquals(expectedOutput, list.toString());
		assertEquals(4, list.size());
	}

	@Test
	public void testRemoveAllForObjectNotContained() {
		list = new LinkedListClass<Object>(new Object[] {1,2,10,3,4,10});

		assertFalse(list.removeAll(new Integer(11)));
	}

	@Test
	public void testRemoveAllForFirstIndex() {
		list = new LinkedListClass<Object>(new Object[] {1,1,1,2,3,4});
		expectedOutput = "{2,3,4}";

		assertTrue(list.removeAll(new Integer(1)));
		assertEquals(expectedOutput, list.toString());
		assertEquals(3,list.size());
	}
	
	@Test
	public void testEqualsForTwoEqualLists() {
		list = createLinkedList(5);
		LinkedListClass<Object> otherList = createLinkedList(5);
		assertTrue(list.equals(otherList));
	}
	
	@Test
	public void testEqualsForTwoDifferentLengthLists() {
		list = createLinkedList(5);
		LinkedListClass<Object> otherList = createLinkedList(6);
		assertFalse(list.equals(otherList));
	}
	
	@Test
	public void testEqualsForTwoDifferentContentLists() {
		list = createLinkedList(5);
		list.add(new Integer(5));
		LinkedListClass<Object> otherList = createLinkedList(6);
		otherList.add(new Integer(7));
		assertFalse(list.equals(otherList));
	}
	
	@Test
	public void testClearMethod() {
		list = createLinkedList(6);
		list.clear();
		assertEquals(0, list.size());
		assertNull(list.getFirst());
	}
	
	@Test
	public void testClearMethodForEmptyList() {
		list.clear();
		assertEquals(0, list.size());
		assertNull(list.getFirst());
	}
	
	@Test
	public void testSet() {
		list = createLinkedList(6);
		assertEquals(4,list.set(4, 10));
		expectedOutput = "{0,1,2,3,10,5}";
		
		assertEquals(expectedOutput, list.toString());
	}
	
	@Test
	public void testSetForInvalidIndex() {
		expectedOutput = "Index out of bounds!";
		try {
			list = createLinkedList(3);
			assertEquals(4,list.set(4, 10));
			fail("Index out of bounds should be thrown");
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(expectedOutput, e.getMessage());
		}
	}
	
	@Test
	public void testAddFirstToEmptyList() {
		list.addFirst(new Integer(5));
		
		assertEquals(5, list.get(0));
		assertEquals(1, list.size());
	}
	
	@Test
	public void testAddFirstToNonemptyList() {
		list = createLinkedList(5);
		list.addFirst(new Integer(5));
		
		assertEquals(5, list.get(0));
		assertEquals(6, list.size());
	}
	
	@Test
	public void testToArrayForNonemptyArray() {
		list = createLinkedList(5);
		Object[] array = new Object[] {0,1,2,3,4};
		assertEquals(list.toArray(), array);
	}
	
	@Test
	public void testToArrayForEmptyArray() {
		Object[] array = new Object[0];
		assertEquals(list.toArray(), array);
	}

	@Test
	public void testIsEmptyWithEmptyArray() {
		assertTrue(list.isEmpty());
	}

	@Test
	public void testIsEmptyWithNonemptyArray() {
		list = createLinkedList(5);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetLastWithNonemptyArray() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4});
		expectedOutput = "4";

		assertEquals(expectedOutput, list.getLast().toString());
	}

	@Test
	public void testGetLastWithEmptyArray() {
		assertNull(list.getLast());
	}

	@Test
	public void testElementWithNonemptyArray() {
		list = new LinkedListClass<Object>(new Object[] {1,2,3,4});
		expectedOutput = "1";

		assertEquals(expectedOutput, list.element().toString());
	}

	@Test
	public void testElementWithEmptyArray() {
		assertNull(list.element());
	}

	@Test
	public void testIndexOf() {
		list = new LinkedListClass<Object>(new Object[] {"1","2","3","4"});

		assertEquals(1, list.indexOf("2"));
	}

	@Test
	public void testIndexOfOnFirstIndex() {
		list = new LinkedListClass<Object>(new Object[] {"1","2","3","4"});

		assertEquals(0, list.indexOf("1"));
	}

	@Test
	public void testIndexOfOnNonexistentObject() {
		list = new LinkedListClass<Object>(new Object[] {"1","2","3","4"});

		assertEquals(-1, list.indexOf("6"));
	}

	@Test
	public void testIndexOfOnEmptyArray() {
		assertEquals(-1, list.indexOf("6"));
	}

	@Test
	public void testIndexOfOnLastIndex() {
		list = new LinkedListClass<Object>(new Object[] {"1","2","3","4"});

		assertEquals(3, list.indexOf("4"));
	}

	@Test
	public void testLastIndexOf() {
		list = new LinkedListClass<Object>(new Object[] {"1","2","3","4"});

		assertEquals(3, list.lastIndexOf("4"));
	}

	@Test
	public void testLastIndexOfWithMultipleSameObject() {
		list = new LinkedListClass<Object>(new Object[] {"4","4","4","4"});

		assertEquals(3, list.lastIndexOf("4"));
	}

	@Test
	public void testLastIndexOfWithObjectNotContained() {
		list = new LinkedListClass<Object>(new Object[] {"1","1","1","1"});

		assertEquals(-1, list.lastIndexOf("4"));
	}

	@Test
	public void testContains() {
		list = new LinkedListClass<Object>(new Object[] {"1","1","1","1"});

		assertTrue(list.contains("1"));
	}

	@Test
	public void testContainsForArrayNotContainingObject() {
		list = new LinkedListClass<Object>(new Object[] {"1","1","1","1"});

		assertFalse(list.contains("2"));
	}

	@Test
	public void testContainsForEmptyArray() {
		assertFalse(list.contains("1"));
	}

	//creates a linkedlist with the number of nodes as the parameter specified and populates w/ an integer equal to the index
	private LinkedListClass<Object> createLinkedList(int numberOfNodes) {	
		LinkedListNode[] nodeList = new LinkedListNode[numberOfNodes];
		nodeList[0] = new LinkedListNode();
		nodeList[0].setData(new Integer(0));
		for(int i = 1; i < numberOfNodes; i++) {
			nodeList[i] = new LinkedListNode();
			nodeList[i].setData(new Integer(i));
			nodeList[i - 1].setNext(nodeList[i]);
		}
		return new LinkedListClass<Object>(nodeList[0]);	
	}
}
