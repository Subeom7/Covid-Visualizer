package prj5;

import student.TestCase;

/**
 * Test class for CompareCFR.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class CompareCFRTest extends TestCase {
    // Fields ..........................................................

    private CompareCFR compareCFR;

    // Methods .........................................................

    /**
     * Sets up the test fixture that's called before every test case method.
     */
    public void setUp() {
        compareCFR = new CompareCFR();
    }


    /**
     * Makes sure the compare(Race race1, Race race2) method returns:
     * 
     * - a negative number when 'race1' has a case fatality ratio (CFR)
     * greater than the CFR of 'race2',
     * 
     * - a positive number when 'race1' has a case fatality ratio (CFR)
     * less than the CFR of 'race2',
     * 
     * - a negative number when both 'race1' and 'race2' have the same
     * case fatality ratio (CFR) but 'race1' has a name that comes before
     * 'race2' alphabetically,
     * 
     * - a positive number when both 'race1' and 'race2' have the same
     * case fatality ratio (CFR) but 'race1' has a name that comes after
     * 'race2' alphabetically,
     * 
     * - 0 when both 'race1' and 'race2' have the same case fatality ratio
     * (CFR) and name.
     */
    public void testCompare() {
        Race smallCFR = new Race("James", 100, 20);
        Race bigCFR = new Race("Aden", 100, 80);
        
        
        assertEquals(1, compareCFR.compare(smallCFR, bigCFR));
        assertEquals(-1, compareCFR.compare(bigCFR, smallCFR));

        Race sameCFRsmallName = new Race("Zack", 100, 20);
        assertEquals(-16, compareCFR.compare(smallCFR, sameCFRsmallName));

        Race sameCFRbigName = new Race("Ben", 100, 20);
        assertEquals(8, compareCFR.compare(smallCFR, sameCFRbigName));

        Race sameCFRandName = new Race("James", 100, 20);
        assertEquals(0, compareCFR.compare(smallCFR, sameCFRandName));
    }
}