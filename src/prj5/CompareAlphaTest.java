package prj5;

import student.TestCase;

/**
 * Test class for CompareAlpha.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class CompareAlphaTest extends TestCase {
    // Fields ..........................................................

    private CompareAlpha compareAlpha;

    // Methods .........................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        compareAlpha = new CompareAlpha();
    }


    /**
     * Makes sure the compare(Race race1, Race race2) method returns:
     * 
     * - a negative number when 'race1' has a name that comes before
     * 'race2' alphabetically,
     * 
     * - a positive number when 'race1' has a name that comes after
     * 'race2' alphabetically,
     * 
     * - 0 when both 'race1' and 'race2' have the same name.
     */
    public void testCompare() {
        Race smallName = new Race("Zack", 5, 1);
        Race bigName = new Race("Aden", 5, 1);
        
        
        assertEquals(-25, compareAlpha.compare(bigName, smallName));
        assertEquals(25, compareAlpha.compare(smallName, bigName));

        Race sameName = new Race("Zack", 5, 1);
        assertEquals(0, compareAlpha.compare(smallName, sameName));
    }
}