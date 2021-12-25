package prj5;

/**
 * Provides the definitions of all the necessary methods for a list
 * implementation in the COVID data visualization program.
 * 
 * @author Subeom Kwon (subeom7)
 * @version 11.15.2021
 *
 * @param <T>
 *            Type of elements in class.
 */
public interface ListADT<T> {
    // Methods .........................................................

    /**
     * Adds a given element to the end of the list.
     *
     * @param item
     *            Element to add.
     */
    public void add(T item);


    /**
     * Adds an element to the given position in the list.
     *
     * @param position
     *            Position in list.
     * 
     * @param item
     *            Element to add.
     * 
     * @throws IndexOutOfBoundsException
     *             if position is less than zero or greater than the size of
     *             the list.
     * 
     * @throws IllegalArgumentException
     *             if element to add is null.
     */
    public void add(int position, T item);


    /**
     * Removes an element at a given position in the list.
     *
     * @param position
     *            Position in list.
     * 
     * @return true if removal is successful; otherwise false.
     * 
     * @throws IndexOutOfBoundsException
     *             if there is no element at the specified position.
     */
    public boolean remove(int position);


    /**
     * Gets the number of elements in the list.
     * 
     * @return number of elements in list.
     */
    public int size();


    /**
     * Removes all of the elements in the list.
     */
    public void clear();


    /**
     * Checks if the list is empty.
     * 
     * @return true if list is empty; otherwise false.
     */
    public boolean isEmpty();


    /**
     * Gets the element at a given position in the list.
     *
     * @param position
     *            Position in list.
     * 
     * @return element at the given position in list or null if there isn't
     *         one.
     */
    public T getEntry(int position);


    /**
     * Checks if the list contains a given element.
     *
     * @param item
     *            Element to look for in list.
     * 
     * @return true if element is in list; otherwise false.
     */
    public boolean contains(T item);
}
