package prj5;

/**
 * Stores the COVID data set of each state as a LinkedList object.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class Dataset {
    // Fields ..........................................................

    private LinkedList<State> data;

    // Constructor .....................................................

    /**
     * Creates new Dataset object.
     * 
     * @param data
     *            COVID data of each state.
     */
    public Dataset(LinkedList<State> data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        this.data = data;
    }

    // Method ..........................................................


    /**
     * Gets the list of states (and their respective COVID data) in the
     * data set.
     * 
     * @return list of states (and their respective COVID data) in data
     *         set.
     */
    public LinkedList<State> getData() {
        return data;
    }


    /**
     * Gets the state in the data set that corresponds to a given name.
     * 
     * @param name
     *            Name of state to look for in data set.
     * 
     * @return state in the data set that corresponds to the given name;
     *         otherwise null.
     */
    public State getState(String name) {
        for (State state : this.getData()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}