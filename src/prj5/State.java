package prj5;

/**
 * Keeps track of, stores and sorts the name and COVID data of each race
 * for a state.
 * 
 * @author Mahlet Zemui (mtzemui) , Faraz Vossoughian (fv2146)
 * @version 11.15.2021
 */
public class State extends LinkedList<Race> {
    // Fields ..........................................................

    private String name;

    // Constructor .....................................................

    /**
     * Creates new State object.
     * 
     * @param stateName
     *            Name of state.
     */
    public State(String stateName) {
        name = stateName;
    }

    // Methods .........................................................


    /**
     * Gets the name of the state.
     *
     * @return name of state.
     */
    public String getName() {
        return name;
    }


    /**
     * Sorts the list of Race objects alphabetically.
     */
    public void sortAlphabetically() {
        CompareAlpha compare = new CompareAlpha();
        insertionSort(compare);
    }


    /**
     * Sorts the list of Race object according to their case fatality
     * ratio (CFR), in decreasing order.
     */
    public void sortByCFR() {
        CompareCFR compare = new CompareCFR();
        insertionSort(compare);
    }


    /**
     * Gets the string representation of a state's COVID data by
     * displaying the state's name and each race's COVID data on different
     * lines.
     * 
     * @return string representation of a state's COVID data by
     *         displaying the state's name and each race's COVID data on
     *         different lines.
     */
    @Override
    public String toString() {
        return getName() + "\n" + super.toString();
    }


    /**
     * Checks whether the current State object and a given object are two
     * State objects with the same name and COVID data for each race.
     * 
     * @param obj
     *            Object to compare to for equality.
     * 
     * @return true if current State object and given object are two State
     *         objects with the same name and COVID data for each race;
     *         otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (obj == this) {
            return true;
        }
        else if (obj.getClass() == this.getClass()) {
            State temp = (State)obj;

            if (this.getName().equals(temp.getName()) && super.equals(temp)) {
                return true;
            }
        }
        return false;
    }
}