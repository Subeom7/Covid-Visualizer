package prj5;

import java.awt.Color;
import java.text.DecimalFormat;
import cs2.Button;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;

/**
 * Creates and updates the front-end of this simulation to make a visual
 * representation of a COVID data set.
 * 
 * @author Mahlet Zemui (mtzemui) , Faraz Vossoughian (fv2146) ,
 *         Subeom Kwon (subeom7)
 * @version 11.20.2021
 */
public class GUICovidData {
    // Fields ..........................................................

    private Window window;
    private Dataset dataset;
    private State stateOnScreen;

    private static final int MAX_BAR_HEIGHT = 2000;
    private static final int BAR_WIDTH = 10;
    private static final int VERTICAL_SPACING = 20;
    private static final int BOTTOM_BORDER = 60;

    private Button sortAlpha;
    private Button sortCFR;
    private Button quit;

    // Constructors ....................................................

    /**
     * Creates new GUICovidData object.
     * 
     * @param data
     *            COVID data set.
     */
    public GUICovidData(Dataset dataset) {
        this.dataset = dataset;
        window = new Window();
        window.setTitle(
            "Visualization of a COVID dataset by mtzemui, subeom7, fv2146");

        sortAlpha = new Button("Sort Alphabetically");
        sortAlpha.onClick(this, "clickedSortAlpha");
        window.addButton(sortAlpha, WindowSide.NORTH);

        quit = new Button("Quit");
        quit.onClick(this, "clickedQuit");
        window.addButton(quit, WindowSide.NORTH);

        sortCFR = new Button("Sort by CFR");
        sortCFR.onClick(this, "clickedSortCFR");
        window.addButton(sortCFR, WindowSide.NORTH);

        for (State state : dataset.getData()) {
            Button stateButton = new Button("Represent " + state.getName());
            stateButton.onClick(this, "clickedState");
            window.addButton(stateButton, WindowSide.SOUTH);
        }

        stateOnScreen = null;
    }

    // Methods .........................................................


    /**
     * Displays the data of the desired state on the window when clicked by
     * the user.
     * 
     * @param button
     *            State button clicked by user.
     */
    public void clickedState(Button button) {
        String buttonName = button.getTitle();
        String stateName = buttonName.substring(10);

        stateOnScreen = dataset.getState(stateName);
        if (stateOnScreen != null) {
            window.removeAllShapes();
            buildBarChart(stateOnScreen);
        }
    }


    /**
     * Sorts the data displayed on the window alphabetically by race
     * when clicked by the user.
     * 
     * @param button
     *            Alphabetically sorting button clicked by user.
     */
    public void clickedSortAlpha(Button button) {
        if (stateOnScreen != null) {
            stateOnScreen.sortAlphabetically();
            window.removeAllShapes();
            buildBarChart(stateOnScreen);
        }
    }


    /**
     * Sorts the data displayed on the window by case fatality ratio
     * of each race when clicked by the user.
     * 
     * @param button
     *            CFR sorting button clicked by user.
     */
    public void clickedSortCFR(Button button) {
        if (stateOnScreen != null) {
            stateOnScreen.sortByCFR();
            window.removeAllShapes();
            buildBarChart(stateOnScreen);
        }
    }


    /**
     * Closes the window when clicked by the user.
     * 
     * @param button
     *            Quit button clicked by user.
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }


    /**
     * Creates the bar chart, that corresponds to a given COVID data of a
     * state, on the window.
     * 
     * @param state
     *            State object to 'parse' to create a valid bar chart.
     * 
     * @return bar chart that corresponds to the given COVID data of a state.
     */
    private void buildBarChart(State state) {
        int midWidth = window.getGraphPanelWidth() / 2;

        String title = state.getName() + " Case Fatality Ratios by Race";
        positionText(title, midWidth, 0);

        int yEndPoint = window.getGraphPanelHeight() - BOTTOM_BORDER;
        for (Race race : state) {
            int xPos = midWidth - 200;

            positionText(race.getName(), xPos, yEndPoint);

            double raceCFR = race.getCFR();
            if (raceCFR >= 0) {
                buildBar(raceCFR, xPos);
            }
            else {
                positionText("NA", xPos, yEndPoint);
            }

            midWidth = midWidth + 100;
        }
    }


    /**
     * Builds a bar shape, that correspond to a given case fatality ratio
     * value (CFR), accompanied by text that indicates the CFR value near
     * a given x position on the window.
     * 
     * @param cfr
     *            Case fatality ratio.
     * 
     * @param x
     *            X position on the window.
     */
    private void buildBar(double cfr, int x) {
        int height = (int)((MAX_BAR_HEIGHT * cfr) / 100);
        int xPos = x - (BAR_WIDTH / 2);
        int y = window.getGraphPanelHeight() - BOTTOM_BORDER;
        int yPos = y - height;

        Shape bar = new Shape(xPos, yPos, BAR_WIDTH, height, Color.blue);
        window.addShape(bar);

        DecimalFormat df = new DecimalFormat("0.#");
        positionText(df.format(cfr) + "%", x, y + VERTICAL_SPACING);
    }


    /**
     * Positions a given text near given x and y coordinates on the
     * window.
     * 
     * @param text
     *            Text to add to window.
     * 
     * @param x
     *            X coordinate on the window.
     * 
     * @param y
     *            Y coordinate on the window.
     */
    private void positionText(String text, int x, int y) {
        TextShape textShape = new TextShape(0, 0, text);

        x = x - (textShape.getWidth() / 2);
        if (text.equals("NA")) {
            y = y - textShape.getHeight();
        }
        else {
            y = y + VERTICAL_SPACING;
        }

        textShape.moveTo(x, y);
        window.addShape(textShape);
    }
}
