package prj5;

import student.TestCase;

/**
 * Test class for State.
 * 
 * @author Mahlet Zemui (mtzemui) , Faraz Vossoughian (Fv2146)
 * @version 11.15.2021
 */
public class StateTest extends TestCase {
    // Fields ..........................................................

    private State state;
    private Race black;
    private Race white;
    private Race asian;

    // Methods .........................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        state = new State("DC");

        black = new Race("black", 100, 80);
        state.add(black);
        white = new Race("white", 100, 40);
        state.add(white);
        asian = new Race("asian", 100, 40);
        state.add(asian);
    }


    /**
     * Makes sure the getName() method returns the appropriate name of a
     * State object.
     */
    public void testGetName() {
        assertEquals("DC", state.getName());
    }


    /**
     * Makes sure the toString() method returns the appropriate string
     * representation of a State object by displaying the state's name
     * and each race's COVID data on different lines.
     */
    public void testToString() {
        String result = "DC" + "\n" + "black: 100 cases, 80% CFR" + "\n"
            + "white: 100 cases, 40% CFR" + "\n" + "asian: 100 cases, 40% CFR";
        assertEquals(result, state.toString());
    }


    /**
     * Makes sure the sortAlphabetically() method sorts the list of Race
     * Race objects in a State object, alphabetically.
     */
    public void testSortAlphabetically() {
        String result = "DC" + "\n" + "black: 100 cases, 80% CFR" + "\n"
            + "white: 100 cases, 40% CFR" + "\n" + "asian: 100 cases, 40% CFR";
        assertEquals(result, state.toString());

        state.sortAlphabetically();
        result = "DC" + "\n" + "asian: 100 cases, 40% CFR" + "\n"
            + "black: 100 cases, 80% CFR" + "\n" + "white: 100 cases, 40% CFR";
        assertEquals(result, state.toString());
    }


    /**
     * Makes sure the sortByCFR() method sorts the list of Race objects in
     * a State object, according to each race's case fatality ratio (CFR)
     * in decreasing order.
     */
    public void testSortByCFR() {
        String result = "DC" + "\n" + "black: 100 cases, 80% CFR" + "\n"
            + "white: 100 cases, 40% CFR" + "\n" + "asian: 100 cases, 40% CFR";
        assertEquals(result, state.toString());

        state.sortByCFR();
        result = "DC" + "\n" + "black: 100 cases, 80% CFR" + "\n"
            + "asian: 100 cases, 40% CFR" + "\n" + "white: 100 cases, 40% CFR";
        assertEquals(result, state.toString());
    }


    /**
     * Makes sure the equals(Object obj) method returns true:
     * 
     * - when "obj" references the same object as a State object,
     * 
     * - when "obj" is a State object with the same name and COVID data,
     * as another State object.
     */
    public void testEquals1() {
        Object sameReference = state;
        assertTrue(state.equals(sameReference));

        State sameInformation = new State("DC");
        sameInformation.add(black);
        sameInformation.add(white);
        sameInformation.add(asian);
        assertTrue(state.equals(sameInformation));
    }


    /**
     * Makes sure the equals(Object obj) method returns false:
     * 
     * - when "obj" is null,
     * 
     * - when "obj" is not a State object,
     * 
     * - when "obj" is a State object but it has at a different name,
     * or COVID data as another State object.
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals2() {
        Object nullObject = null;
        assertFalse(state.equals(nullObject));

        assertFalse(state.equals("Not a State object."));

        State difName = new State("VA");
        difName.add(black);
        difName.add(white);
        difName.add(asian);
        assertFalse(state.equals(difName));

        State difData = new State("DC");
        difData.add(black);
        difData.add(white);
        assertFalse(state.equals(difData));
    }
}