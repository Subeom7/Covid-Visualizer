package prj5;

import java.util.Comparator;

/**
 * Implements the comparator interface to compare Race objects according
 * to their case fatality ratio (CFR).
 * 
 * @author Mahlet Zemui (mtzemui)
 * @version 11.15.2021
 */
public class CompareCFR implements Comparator<Race> {
    // Methods ..........................................................

    /**
     * Compares the case fatality ratio (CFR) of two given Race objects.
     * 
     * @param race1
     *            First Race object in comparison.
     * 
     * @param race2
     *            Second Race object in comparison.
     * 
     * @return a negative number if the case fatality ratio (CFR) of the
     *         first Race object is greater than the CFR of the second
     *         Race object or a positive number if it's less than. If
     *         both objects have the same CFR, return a negative number
     *         if the name of the first Race object comes before the name
     *         of the second Race object, a positive number if it comes
     *         after; or 0 if both names are the same.
     */
    @Override
    public int compare(Race race1, Race race2) {
        int value = Double.compare(race2.getCFR(), race1.getCFR());
        if (value != 0) {
            return value;
        }
        return race1.getName().compareTo(race2.getName());
    }

}