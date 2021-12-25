package prj5;

import student.TestCase;

/**
 * Test class for Race.
 * 
 * @author Mahlet Zemui (mtzemui) , Faraz Vossoughian (Fv2146)
 * @version 11.15.2021
 */
public class RaceTest extends TestCase {
    // Fields ..........................................................

    private Race race;

    // Methods .........................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        race = new Race("black", 100, 50);
    }


    /**
     * Makes sure the getName() method returns the appropriate name of a
     * Race object.
     */
    public void testGetName() {
        assertEquals("black", race.getName());
    }


    /**
     * Makes sure the getCases() method returns the correct number of
     * COVID cases of a Race object.
     */
    public void testGetCases() {
        assertEquals(100, race.getCases());
    }


    /**
     * Makes sure the getDeaths() method returns the correct number of
     * COVID related deaths of a Race object.
     */
    public void testGetDeaths() {
        assertEquals(50, race.getDeaths());
    }


    /**
     * Makes sure the getCFR() method:
     * 
     * - returns the correct case fatality ratio (CFR) of a Race object
     * when the number of COVID cases and deaths are valid,
     * 
     * - returns -1 when the number of cases or deaths of a Race object is
     * not valid.
     */
    public void testGetCFR() {
        assertEquals(50.0, race.getCFR(), 0.1);

        Race wrongCases = new Race("white", -1, 50);
        assertEquals(-1.0, wrongCases.getCFR(), 0.1);

        Race wrongDeaths = new Race("white", 100, -1);
        assertEquals(-1.0, wrongDeaths.getCFR(), 0.1);
    }


    /**
     * Makes sure the toString() method returns the appropriate string
     * representation of a Race object in the following manner:
     * 
     * "[Race's name]: [COVID cases] cases, [CFR value]% CFR"
     */
    public void testToString() {
        String result = "black: 100 cases, 50% CFR";
        assertEquals(result, race.toString());
    }


    /**
     * Makes sure the equals(Object obj) method returns true:
     * 
     * - when "obj" references the same object as a Race object,
     * 
     * - when "obj" is a Race object with the same name, COVID cases and
     * deaths as another Race object.
     */
    public void testEquals1() {
        Object sameReference = race;
        assertTrue(race.equals(sameReference));

        Race sameInformation = new Race("black", 100, 50);
        assertTrue(race.equals(sameInformation));
    }


    /**
     * Makes sure that the equals(Object obj) method returns false:
     * 
     * - when "obj" is null,
     * 
     * - when "obj" is not a Race object,
     * 
     * - when "obj" is a Race object but it has at a different name,
     * number of COVID cases or deaths as another Race object.
     */
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals2() {
        Object nullObject = null;
        assertFalse(race.equals(nullObject));

        assertFalse(race.equals("Not a Race object."));

        Race difName = new Race("white", 100, 50);
        assertFalse(race.equals(difName));

        Race difCases = new Race("black", 60, 50);
        assertFalse(race.equals(difCases));

        Race difDeaths = new Race("black", 100, 20);
        assertFalse(race.equals(difDeaths));
    }
}