package prj5;

import student.TestCase;

/**
 * Test class for Dataset.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class DatasetTest extends TestCase {
    // Fields ..........................................................

    private Dataset dataset;
    private LinkedList<State> covidData;

    // Methods .........................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        covidData = new LinkedList<State>();
        dataset = new Dataset(covidData);
    }


    /**
     * Makes sure that the constructor throws the appropriate exception
     * when the COVID data being passed to a Dataset object is null.
     */
    public void testConstructor() {
        covidData = null;
        Exception thrown = null;
        try {
            dataset = new Dataset(covidData);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * Makes sure that the getData() method returns the appropriate COVID
     * data that corresponds to a Dataset object.
     */
    public void testGetData() {
        assertEquals(covidData, dataset.getData());
    }


    /**
     * Makes sure that the getState(String name) method returns:
     * 
     * - the appropriate state in the COVID data set that has the name
     * specified by the parameter,
     * 
     * - null when the name specified by the parameter does not correspond to
     * any of the states in the data set.
     */
    public void testGetState() {
        assertNull(dataset.getState("DC"));

        State va = new State("VA");
        covidData.add(va);
        State md = new State("MD");
        covidData.add(md);
        assertEquals(md, dataset.getState("MD"));
        assertNull(dataset.getState("NY"));
    }
}