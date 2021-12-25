package prj5;

import java.text.DecimalFormat;

/**
 * Keeps track of the name, COVID cases, COVID deaths and case fatality
 * ratio related to COVID for a race.
 * 
 * @author Mahlet Zemui (mtzemui) , Faraz Vossoughian (fv2146)
 * @version 11.15.2021
 */
public class Race {
    // Fields ..........................................................

    private String name;
    private int cases;
    private int deaths;
    private double cfr;

    // Constructor .....................................................

    /**
     * Creates new Race object.
     * 
     * @param raceName
     *            Name of race.
     * 
     * @param numCases
     *            Number of COVID cases.
     * 
     * @param numDeaths
     *            Number of deaths related to COVID.
     */
    public Race(String raceName, int numCases, int numDeaths) {
        name = raceName;
        cases = numCases;
        deaths = numDeaths;
        calculateCFR();
    }

    // Methods .........................................................


    /**
     * Gets the name of the race.
     *
     * @return name of race.
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the number of COVID cases.
     *
     * @return number of COVID cases.
     */
    public int getCases() {
        return cases;
    }


    /**
     * Gets the number of deaths related to COVID.
     *
     * @return number of deaths related to COVID.
     */
    public int getDeaths() {
        return deaths;
    }


    /**
     * Gets the case fatality ratio (CFR).
     *
     * @return case fatality ratio (CFR).
     */
    public double getCFR() {
        return cfr;
    }


    /**
     * Calculates the case fatality ratio (CFR) if both the number of
     * COVID cases and deaths are valid (i.e. greater than or equal to 0);
     * if not CFR will be -1.
     */
    private void calculateCFR() {
        if (cases <= 0 || deaths < 0) {
            cfr = -1;
        }
        else {
            cfr = ((double)deaths / (double)cases) * 100;
        }
    }


    /**
     * Gets the string representation of a race's COVID data in the
     * following manners:
     * 
     * "[Race's name]: [COVID cases] cases, [CFR value]% CFR"
     * 
     * @return string representation of a race's COVID data in the above
     *         manner.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.#");
        return getName() + ": " + getCases() + " cases, " + df.format(cfr)
            + "% CFR";
    }


    /**
     * Checks whether the current Race object and a given object are two
     * Race objects with the same name, COVID cases and deaths.
     * 
     * @param obj
     *            Object to compare to for equality.
     * 
     * @return true if current Race object and given object are two Race
     *         objects with the same name, COVID cases and deaths;
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
            Race temp = (Race)obj;

            if (this.getName().equals(temp.getName()) && this.getCases() == temp
                .getCases() && this.getDeaths() == temp.getDeaths()) {
                return true;
            }
        }
        return false;
    }
}