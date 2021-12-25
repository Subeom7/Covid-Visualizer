package prj5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements the ListADT interface using a singly linked list and provides
 * additional methods to sort the items in the list.
 * 
 * 
 * @author Subeom Kwon (subeom7)
 * @version 11.15.2021
 * 
 * @param <T>
 *            Type of elements in the class.
 */
public class LinkedList<T> implements ListADT<T>, Iterable<T> {
    // Fields ......................................................

    private Node<T> firstNode;
    private int size;

    // Constructor .................................................

    /**
     * Creates new LinkedList object.
     */
    public LinkedList() {
        firstNode = null;
        size = 0;
    }

    // Methods .....................................................


    /**
     * Adds an element to the end of the list.
     *
     * @param item
     *            Element to add.
     */
    @Override
    public void add(T item) {
        add(size, item);
    }


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
    @Override
    public void add(int position, T item) {
        if (item == null) {
            throw new IllegalArgumentException("Element to add is null.");
        }

        if (position < 0 || size < position) {
            throw new IndexOutOfBoundsException("Position is out of bounds");
        }

        Node<T> newNode = new Node<T>(item);

        Node<T> current = firstNode;
        if (position == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        }
        else {
            int index = 0;

            while (current != null) {
                if ((index + 1) == position) {
                    Node<T> nextNode = current.getNext();
                    current.setNext(newNode);
                    newNode.setNext(nextNode);
                }
                current = current.getNext();
                index++;
            }
        }
        size++;
    }


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
    @Override
    public boolean remove(int position) {
        if (getEntry(position) == null) {
            throw new IndexOutOfBoundsException("Position is out of bounds");
        }

        if (position == 0) {
            firstNode = firstNode.next;
        }
        else {
            Node<T> current = firstNode;
            int index = 0;
            while ((index + 1) != position) {
                current = current.getNext();
                index++;
            }
            Node<T> remove = current.getNext();
            Node<T> newNext = remove.getNext();
            current.setNext(newNext);
        }
        size--;
        return true;
    }


    /**
     * Gets the number of elements in the list.
     * 
     * @return number of elements in list.
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Removes all of the elements in the list.
     */
    @Override
    public void clear() {
        firstNode = null;
        size = 0;
    }


    /**
     * Checks if the list is empty.
     * 
     * @return true if list is empty; otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Gets the element at a given position in the list.
     *
     * @param position
     *            Position in list.
     * 
     * @return element at the given position in list or null if there isn't
     *         one.
     */
    @Override
    public T getEntry(int position) {
        int index = 0;
        for (T element : this) {
            if (index == position) {
                return element;
            }
            index++;
        }
        return null;
    }


    /**
     * Checks if the list contains a given element.
     *
     * @param item
     *            Element to look for in list.
     * 
     * @return true if element is in list; otherwise false.
     */
    @Override
    public boolean contains(T item) {
        for (T element : this) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the string representation of the list, by displaying every
     * element in the list on a new line.
     *
     * @return string representing of list, by displaying every element in the
     *         list on a new line.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for (T element : this) {
            if (element.equals(getEntry(size - 1))) {
                builder.append(element.toString());
            }
            else {
                builder.append(element.toString() + "\n");
            }
        }
        return builder.toString();
    }


    /**
     * Checks whether the current LinkedList object and a given object are two
     * two LinkedList objects with the same content in the same order.
     * 
     * @param obj
     *            Object to compare to for equality.
     * 
     * @return true if current LinkedList object and given object are two
     *         LinkedList objects with the same content in the same order;
     *         otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            LinkedList<T> temp = (LinkedList<T>)obj;

            if (temp.size == this.size) {
                Node<T> tempCurrent = temp.firstNode;

                for (T element : this) {
                    if (!element.equals(tempCurrent.getData())) {
                        return false;
                    }
                    tempCurrent = tempCurrent.getNext();
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Uses the 'insertion sort method' to sort the elements in the list
     * according to a given comparator.
     * 
     * @param compare
     *            Comparator used to sort elements in list.
     */
    public void insertionSort(Comparator<T> compare) {
        if (size > 1) {
            Node<T> unsortedPart = firstNode.getNext();
            Node<T> sortedPart = firstNode;
            sortedPart.setNext(null);

            while (unsortedPart != null) {
                Node<T> nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNext();
                insertInOrder(nodeToInsert, compare);
            }
        }
    }


    /**
     * Adds a given element to the 'correct' position in the list, according
     * to the comparator used.
     * 
     * @param element
     *            Element to add.
     * 
     * @param comparator
     *            Comparator used to add element in list.
     */
    private void insertInOrder(Node<T> nodeToInsert, Comparator<T> comparator) {
        T item = nodeToInsert.getData();
        Node<T> currentNode = firstNode;
        Node<T> previousNode = null;

        while (currentNode != null && (comparator.compare(item, currentNode
            .getData()) > 0)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (previousNode != null) {
            previousNode.setNext(nodeToInsert);
            nodeToInsert.setNext(currentNode);
        }
        else {
            nodeToInsert.setNext(firstNode);
            firstNode = nodeToInsert;
        }
    }


    /**
     * Creates an iterator object for the list.
     *
     * @return new Iterator object for list.
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>();
    }

    // .................................................................

    /**
     * Stores and keeps track of the current and next elements in a singly
     * linked list, such as a LinkedList object.
     * 
     * @param <T>
     *            Type of elements in the class.
     */
    private static class Node<T> {
        // Fields ......................................................

        private T data;
        private Node<T> next;

        // Constructor .................................................

        /**
         * Creates new Node object.
         *
         * @param data
         *            Data in node.
         */
        public Node(T data) {
            this.data = data;
            next = null;
        }

        // Methods .....................................................


        /**
         * Gets the data in the current node.
         *
         * @return data in current node.
         */
        public T getData() {
            return data;
        }


        /**
         * Sets the current node's next node to a given node.
         *
         * @param newNext
         *            Node to set current node's next node to.
         */
        public void setNext(Node<T> newNext) {
            next = newNext;
        }


        /**
         * Gets the current node's next node.
         *
         * @return current node's next node.
         */
        public Node<T> getNext() {
            return next;
        }
    }

    // .................................................................


    /**
     * Provides the necessary methods to traverse a LinkedList object.
     * 
     * @param <T>
     *            Type of elements in the class.
     */
    private class LinkedListIterator<E> implements Iterator<T> {
        // Fields ......................................................

        private Node<T> next;

        // Constructor .................................................

        /**
         * Creates new LinkedListIterator object.
         */
        public LinkedListIterator() {
            next = firstNode;
        }

        // Methods .....................................................


        /**
         * Checks if there's another element in the list.
         *
         * @return true if there's another element in the list; otherwise
         *         false.
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }


        /**
         * Gets the next element in the list.
         *
         * @return next element in the list.
         * 
         * @throws NoSuchElementException
         *             if there are no elements left in the list.
         */
        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException("No elements left in the "
                    + "list.");
            }
            T value = next.getData();
            next = next.getNext();
            return value;
        }
    }
}
