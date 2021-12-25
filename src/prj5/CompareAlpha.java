package prj5;

import java.util.Comparator;

/**
 * Implements the comparator interface to compare Race objects,
 * alphabetically.
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class CompareAlpha implements Comparator<Race> {
    // Methods ..........................................................

    /**
     * Compares the names of two given Race objects, alphabetically.
     * 
     * @param race1
     *            First Race object in comparison.
     * 
     * @param race2
     *            Second Race object in comparison.
     * 
     * @return a negative number if the name of the first Race object
     *         comes before the name of the second Race object, a positive
     *         number if it comes after; or 0 if both names are the same.
     */
    @Override
    public int compare(Race race1, Race race2) {
        return race1.getName().compareTo(race2.getName());
    }
}