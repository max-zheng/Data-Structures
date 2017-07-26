import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArrayListClassTest {

    private ArrayListClass array;
    private String expectedResult;

    @Before
    public void setup() {
        array = new ArrayListClass<Object>();
    }

    @Test
    public void testToStringForEmptyArray() {
        Object[] emptyArray = new Object[0];
        array = new ArrayListClass<Object>(emptyArray);
        expectedResult = "{}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testToStringForNonemptyArray() {
        Integer[] copyArray = new Integer[] {0,1,2,3,4};

        array = new ArrayListClass<Integer>(copyArray);

        expectedResult = "{0,1,2,3,4}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testToArray() {
        array = new ArrayListClass<Object>(new Object[] {1,2,3,4,5,6,7,8});

        assertEquals(new Object[] {1,2,3,4,5,6,7,8}, array.toArray());
    }

    @Test
    public void testToArrayWithEmptyArray() {
        array = new ArrayListClass<Object>(new Object[] {});

        assertEquals(new Object[] {}, array.toArray());
    }

    @Test
    public void testEqualsMethodForTwoEqualArrayList() {
        ArrayListClass<Object> otherArray;

        Object[] sameArrayForBoth = new Object[] {0,1,2,3,4};

        otherArray = new ArrayListClass<Object>(sameArrayForBoth);
        array = new ArrayListClass<Object>(sameArrayForBoth);

        assertTrue(array.equals(otherArray));
    }

    @Test
    public void testEqualsMethodForTwoUnequalArrayList() {
        ArrayListClass<Object> otherArray;

        Object[] arrayForFirst = new Object[] {0,1,2,3,4};
        Object[] arrayForSecond = new Object[] {0};

        otherArray = new ArrayListClass<Object>(arrayForFirst);
        array = new ArrayListClass<Object>(arrayForSecond);

        assertFalse(array.equals(otherArray));
    }

    @Test
    public void testEqualsMethodForTwoUnequalArrayListContainingDifferentObjects() {
        ArrayListClass<String> otherArray;

        String[] arrayForFirst = new String[] {"1","2","3"};
        Object[] arrayForSecond = new Object[] {0};

        otherArray = new ArrayListClass<String>(arrayForFirst);
        array = new ArrayListClass<Object>(arrayForSecond);

        assertFalse(array.equals(otherArray));
    }

    @Test
    public void testIsEmptyForEmptyArrayList() {
        array = new ArrayListClass<Object>();

        assertTrue(array.isEmpty());
    }

    @Test
    public void testIsEmptyForNonemptyArrayList() {
        array = new ArrayListClass<Object>(new Object[] {1,2,3});

        assertFalse(array.isEmpty());
    }

    @Test
    public void testSizeForEmptyArrayList() {
        array = new ArrayListClass<Object>();

        assertEquals(0, array.size());
    }

    @Test
    public void testSizeForNonemptyArrayList() {
        array = new ArrayListClass<Object>(new Object[] {1,2,3,4,5});

        assertEquals(5, array.size());
    }

    @Test
    public void testContainsForArrayContainingObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});

        assertTrue(array.contains("b"));
    }

    @Test
    public void testContainsForArrayNotContainingObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});

        assertFalse(array.contains("d"));
    }

    @Test
    public void testGetForArrayValidIndex() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});

        assertEquals("c", array.get(2));
    }

    @Test
    public void testGetForArrayInvalidIndex() {
        try {
            array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
            array.get(4);
            fail("Supposed to throw exception");
        }

        catch(IndexOutOfBoundsException e) {
            expectedResult = "Index out of bounds!";
            assertEquals(expectedResult, e.getMessage());
        }
    }

    @Test
    public void testAdd() {
        array = new ArrayListClass<Integer>(new Integer[] {1,2,3});
        array.add(4);
        expectedResult = "{1,2,3,4}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testAddMultipleValues() {
        array = new ArrayListClass<Integer>(new Integer[] {1,2,3});
        array.add(4);
        array.add(5);
        array.add(7);
        expectedResult = "{1,2,3,4,5,7}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testAddAtIndex() {
        array = new ArrayListClass<String>(new String[] {"1","3","4"});
        array.add(1, "2");
        expectedResult = "{1,2,3,4}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testAddAtIndexForMultipleValues() {
        array = new ArrayListClass<String>(new String[] {"1","5","6"});
        array.add(1, "2");
        array.add(2, "3");
        array.add(3, "4");
        expectedResult = "{1,2,3,4,5,6}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testAddForInvalidIndex() {
        try {
            array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
            array.add(4, "d");
            fail("Supposed to throw exception");
        }

        catch(IndexOutOfBoundsException e) {
            expectedResult = "Index out of bounds!";
            assertEquals(expectedResult, e.getMessage());
        }
    }

    @Test
    public void testSet() {
        array = new ArrayListClass<String>(new String[] {"1","3","4"});
        array.add(1, "2");
        expectedResult = "{1,2,3,4}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testSetForMultipleValues() {
        array = new ArrayListClass<String>(new String[] {"1","3","4","5"});
        array.set(1, "2");
        array.set(2, "3");
        array.set(3, "4");
        expectedResult = "{1,2,3,4}";

        assertEquals(expectedResult, array.toString());
    }

    @Test
    public void testSetForInvalidIndex() {
        try {
            array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
            array.set(4, "d");
            fail("Supposed to throw exception");
        }

        catch(IndexOutOfBoundsException e) {
            expectedResult = "Index out of bounds!";
            assertEquals(expectedResult, e.getMessage());
        }
    }

    @Test
    public void testIndexOfForObjectExistingInArray() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});

        assertEquals(0, array.indexOf("a"));
    }

    @Test
    public void testIndexOfForObjectNotExistingInArray() {
        array = new ArrayListClass<String>(new String[] {"a","b"});

        assertEquals(-1, array.indexOf("c"));
    }

    @Test
    public void testRemoveForInvalidIndex() {
        try {
            array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
            array.remove(5);
            fail("Supposed to throw exception");
        }

        catch(IndexOutOfBoundsException e) {
            expectedResult = "Index out of bounds!";
            assertEquals(expectedResult, e.getMessage());
        }
    }

    @Test
    public void testRemoveForValidIndex() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
        expectedResult = "{b,c}";

        assertEquals("a", array.remove(0));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveForAnotherValidIndex() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
        expectedResult = "{a,c}";

        assertEquals("b", array.remove(1));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveForValidIndexMultipleTimes() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i"});
        expectedResult = "{d,f,g,h,i}";

        assertEquals("b", array.remove(1));
        assertEquals("c", array.remove(1));
        assertEquals("e", array.remove(2));
        assertEquals("a", array.remove(0));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveForNotContainedObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
        expectedResult = "{a,b,c}";
        
        assertFalse(array.remove("d"));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveArrayContainingObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
        expectedResult = "{a,c}";
        
        assertTrue(array.remove("b"));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveArrayContainingMultipleOfObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c", "d", "e", "a"});
        expectedResult = "{b,c,d,e,a}";
        
        assertTrue(array.remove("a"));
        assertEquals(expectedResult, array.toString());
        
        expectedResult = "{b,c,d,e}";
        assertTrue(array.remove("a"));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveMultipleItems() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c", "d", "e", "f"});
        expectedResult = "{f}";
        
        assertTrue(array.remove("b"));
        assertTrue(array.remove("e"));
        assertTrue(array.remove("d"));
        assertTrue(array.remove("c"));
        assertTrue(array.remove("a"));
        
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveAllForNotContainedObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c"});
        expectedResult = "{a,b,c}";
        
        assertFalse(array.removeAll("d"));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveAllArrayContainingMultipleOfObject() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c", "d", "e", "a"});
        expectedResult = "{b,c,d,e}";

        assertTrue(array.removeAll("a"));
        assertEquals(expectedResult, array.toString());
    }
    
    @Test
    public void testRemoveAllForMultipleObjects() {
        array = new ArrayListClass<String>(new String[] {"a", "b", "c", "d", "e", "a","d","c"});
        expectedResult = "{b,e}";

        assertTrue(array.removeAll("a"));
        assertTrue(array.removeAll("c"));
        assertTrue(array.removeAll("d"));
        assertFalse(array.removeAll("f"));
        
        assertEquals(expectedResult, array.toString());
    }
}