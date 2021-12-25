package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parses the COVID input file to generate the data set that'll be used
 * in the COVID data visualization program.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class DataReader {
    // Fields ..........................................................

    private LinkedList<State> data;

    // Constructors ....................................................

    /**
     * Creates new DataReader object.
     * 
     * @param covidData
     *            Text file of COVID data.
     */
    public DataReader(String covidData) {
        data = readData(covidData);
        Dataset dataset = new Dataset(data);
        GUICovidData guiCovidData = new GUICovidData(dataset);
    }

    // Methods .........................................................


    /**
     * Generates the list of states (and their respective data) from a
     * given COVID text file after parsing it.
     * 
     * @param fileName
     *            COVID text file.
     * 
     * @return list of states (and their respective data) in given COVID
     *         text file.
     */
    private LinkedList<State> readData(String fileName) {
        LinkedList<State> parsedData = new LinkedList<State>();

        try {
            Scanner file = new Scanner(new File(fileName));
            String[] header = null;
            int numRaces = 0;
            boolean firstLine = true;

            while (file.hasNextLine()) {
                if (firstLine) {
                    header = file.nextLine().split(",");
                    numRaces = header.length / 2;
                    firstLine = false;
                }
                else {
                    String[] stateDetails = file.nextLine().split(",");
                    String name = stateDetails[0].trim();

                    State state = new State(name);
                    for (int i = 1; i <= numRaces; i++) {
                        state.add(new Race(header[i].substring(6),
                            convertString(stateDetails[i].trim()),
                            convertString(stateDetails[i + numRaces].trim())));
                    }
                    parsedData.add(state);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }
        return parsedData;
    }


    /**
     * Converts the given string to the correct integer value for the
     * COVID data visualization program.
     * 
     * @param value
     *            String to convert.
     * 
     * @return integer that corresponds to the number specified by the
     *         string; otherwise -1.
     */
    private int convertString(String value) {
        if (value.equals("NA")) {
            return -1;
        }
        return Integer.parseInt(value);
    }
}