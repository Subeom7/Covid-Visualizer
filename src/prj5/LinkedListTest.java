package prj5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Test class for LinkedList.
 * 
 * @author Subeom Kwon (subeom7)
 * @version 11.15.2021
 */
public class LinkedListTest extends TestCase {
    // Fields ......................................................

    private LinkedList<String> list;

    // Methods .....................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        list = new LinkedList<String>();
    }


    /**
     * Makes sure the add(T item) method successfully adds a new element to
     * the end of a LinkedList object.
     */
    public void testAdd1() {
        assertEquals(0, list.size());
        list.add("Apple");
        assertEquals(1, list.size());

        list.add("Orange");
        assertEquals(2, list.size());
        assertEquals("Orange", list.getEntry(1));
    }


    /**
     * Makes sure the add(int position, T item) method:
     * 
     * - successfully adds a valid element (i.e. non null) to the front of a
     * LinkedList object,
     * 
     * - successfully adds a valid element (i.e. non null) in the middle of a
     * LinkedList object,
     * 
     * - throws an IllegalArgumentException when 'item' is null.
     * 
     * - throws an IndexOutOfBoundsException when 'position' is less than zero
     * or greater than the size of the list.
     */
    public void testAdd2() {
        assertEquals(0, list.size());
        list.add(0, "Apple");
        assertEquals(1, list.size());
        list.add(0, "Orange");
        assertEquals(2, list.size());
        assertEquals("Orange", list.getEntry(0));
        list.add(0, "Kiwi");
        assertEquals(3, list.size());
        assertEquals("Kiwi", list.getEntry(0));

        list.add(1, "Mango");
        assertEquals(4, list.size());
        assertEquals("Mango", list.getEntry(1));

        Exception thrown = null;
        try {
            list.add(0, null);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
        assertEquals(4, list.size());

        thrown = null;
        assertNull(thrown);
        try {
            list.add(-3, "Chocolate");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IndexOutOfBoundsException);
        assertEquals(4, list.size());

        thrown = null;
        assertNull(thrown);
        try {
            list.add(5, "Chocolate");
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IndexOutOfBoundsException);
        assertEquals(4, list.size());
    }


    /**
     * Makes sure the remove(int position) method:
     * 
     * - returns true when removal of an element at the front of a LinkedList
     * object is successful,
     * 
     * - returns true when removal of an element in the middle of a LinkedList
     * object is successful,
     * 
     * - throws IndexOutOfBoundsException when there is no element at the
     * specified position in a LinkedList object.
     */
    public void testRemove() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        list.add("Kiwi");
        assertEquals(4, list.size());

        assertEquals("Apple", list.getEntry(0));
        assertTrue(list.remove(0));
        assertEquals(3, list.size());
        assertEquals("Mango", list.getEntry(0));

        assertEquals("Kiwi", list.getEntry(2));
        assertTrue(list.remove(2));
        assertEquals(2, list.size());
        assertEquals("Orange", list.getEntry(1));

        Exception thrown = null;
        try {
            list.remove(-1);
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IndexOutOfBoundsException);
        assertEquals(2, list.size());
    }


    /**
     * Makes sure the size() method returns the correct number of elements in
     * a LinkedList object.
     */
    public void testSize() {
        assertEquals(0, list.size());
        list.add("Apple");
        assertEquals(1, list.size());
    }


    /**
     * Makes sure the clear() method removes all of the elements in a
     * LinkedList object.
     */
    public void testClear() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        list.add("Kiwi");
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
        assertNull(list.getEntry(0));
    }


    /**
     * Makes sure the getEntry(int position) method returns:
     * 
     * - null if there is no element at the specified position in a LinkedList
     * object,
     * 
     * - element at the specified position in a LinkedList object when there
     * is one.
     */
    public void testGetEntry() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        list.add("Kiwi");
        assertEquals(4, list.size());

        assertNull(list.getEntry(100));

        assertEquals("Apple", list.getEntry(0));
        assertEquals("Orange", list.getEntry(2));
    }


    /**
     * Makes sure the contains(T item) method returns:
     * 
     * - true when the specified element is in a LinkedList object,
     * 
     * - false when the specified element is not in a LinkedList object.
     */
    public void testContains() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        list.add("Kiwi");

        assertTrue(list.contains("Orange"));
        assertTrue(list.contains("Mango"));

        assertFalse(list.contains("Chocolate"));
        assertFalse(list.contains("Soccer"));
    }


    /**
     * Makes sure the toString() method returns the appropriate string
     * representation of a LinkedList object by displaying every element in
     * the object on a new line.
     */
    public void testToString() {
        assertEquals("", list.toString());

        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        String result = "Apple" + "\n" + "Mango" + "\n" + "Orange";
        assertEquals(result, list.toString());
    }


    /**
     * Makes sure the equals(Object obj) method returns true:
     * 
     * - when "obj" references the same object as a LinkedList object,
     * 
     * - when "obj" is a LinkedList object with the same content and order
     * as another LinkedList object.
     */
    public void testEquals1() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");

        Object sameReference = list;
        assertTrue(list.equals(sameReference));

        LinkedList<String> sameInformation = new LinkedList<String>();
        sameInformation.add("Apple");
        sameInformation.add("Mango");
        sameInformation.add("Orange");
        assertTrue(list.equals(sameInformation));
    }


    /**
     * Makes sure the equals(Object obj) method returns false:
     * 
     * - when "obj" is null,
     * 
     * - when "obj" is not a LinkedList object,
     * 
     * - when "obj" is a LinkedList object with a number of elements different
     * to another LinkedList object,
     * 
     * - when "obj" is a LinkedList object with the same number of elements as
     * another LinkedList object but different in content.
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals2() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");

        Object nullObject = null;
        assertFalse(list.equals(nullObject));

        assertFalse(list.equals("Not a LinkedList object."));

        LinkedList<String> difSize = new LinkedList<String>();
        difSize.add("Apple");
        difSize.add("Mango");
        assertFalse(list.equals(difSize));

        LinkedList<String> difContent = new LinkedList<String>();
        difContent.add("Apple");
        difContent.add("Orange");
        difContent.add("Mango");
        assertFalse(list.equals(difContent));
    }


    /**
     * Makes sure the insertionSort(Comparator<T> comparator) method sorts the
     * elements in a LinkedList object according to the criteria specified by
     * the comparator:
     * 
     * - when there's one or less elements,
     * 
     * - when there's more than one element.
     */
    public void testInsertionSort() {
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String string1, String string2) {
                return string1.compareTo(string2);
            }
        };

        list.add("Mango");
        assertEquals(1, list.size());
        assertEquals("Mango", list.toString());
        list.insertionSort(comparator);
        assertEquals("Mango", list.toString());

        list.add("Orange");
        list.add("Apple");
        assertEquals(3, list.size());
        String result = "Mango" + "\n" + "Orange" + "\n" + "Apple";
        assertEquals(result, list.toString());
        list.insertionSort(comparator);
        result = "Apple" + "\n" + "Mango" + "\n" + "Orange";
        assertEquals(result, list.toString());
    }


    /**
     * Makes sure the iterator() method returns an iterator object that has a:
     * 
     * - hasNext() method that returns true when a LinkedList object has
     * more elements to traverse through or false otherwise,
     * 
     * - next() method that returns the next element in a LinkedList object
     * when there's more elements to traverse through or it throws a
     * NoSuchElementException otherwise.
     */
    public void testIterator() {
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");
        assertEquals(3, list.size());
        Iterator<String> iter = list.iterator();

        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());

        Exception thrown = null;
        try {
            iter.next();
        }
        catch (Exception exception) {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
    }
}