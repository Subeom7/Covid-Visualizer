package prj5;

/**
 * Demonstrates the functionality of the GUICovidData class by creating
 * a simulation that is able to display the contents of a given COVID data
 * set.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class Input {
    // Methods ..........................................................

    /**
     * Creates a GUICovidData object to display the COVID data set in the
     * 'args' parameter if the latter is an array of string objects
     * containing a single text file that corresponds to a COVID data set;
     * if not, the GUICovidData object displays a default COVID data set.
     * 
     * @param args
     *            Array of string objects containing a single text file
     *            that corresponds to a COVID data set.
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            DataReader dataReader = new DataReader(args[0]);
        }
        else {
            DataReader dataReader = new DataReader(
                "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
        }
    }
}
