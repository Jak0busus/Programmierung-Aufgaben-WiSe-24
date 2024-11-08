package de.jakob.tasks;

import de.jakob.util.Utils;

/**
 * Represents a rectangle in a 2D plane with specified dimensions and coordinates.
 * Provides utility methods for calculating area, copying, checking square properties,
 * and finding intersections between rectangles.
 */
public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;
    private int endX; //stands for the last x coordinate of the rectangle
    private int endY; //similar to endX but for the lowest y coordinate

    private static final String NEGATIVE_VALUE = "Please enter positive values for these dimensions!";

    /**
     * Constructs a rectangle with specified x and y coordinates and specified width and height.
     *
     * @param x      The x-coordinate of the starting point.
     * @param y      The y-coordinate of the starting point.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(int x, int y, int width, int height) {

        if (!arePositive(width, height)) {
            Utils.error(NEGATIVE_VALUE);
            return;
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //coordinates of the other corners
        this.endX = x + width;
        this.endY = y - height;
    }

    /**
     * Constructs a square with specified x and y coordinates and a single side length.
     *
     * @param x               The x-coordinate of the starting point.
     * @param y               The y-coordinate of the starting point.
     * @param sideLengthInput The length of each side of the square.
     */
    public Rectangle(int x, int y, int sideLengthInput) {
        this(x, y, sideLengthInput, sideLengthInput);
    }

    /**
     * Checks if all rectangles in the array are squares.
     *
     * @param rectangles The array of rectangles to check.
     * @return true if all rectangles are squares, false otherwise.
     */
    public static boolean areSquares(Rectangle... rectangles) {
        for (Rectangle r : rectangles) {
            if (r.height != r.width) return false;
        }
        return true;
    }

    /**
     * Finds the intersection rectangle of multiple rectangles, if they all intersect.
     *
     * @param rectangles The array of rectangles to find the intersection for.
     * @return A new Rectangle representing the intersection, or null if no intersection exists.
     */
    public static Rectangle intersection(Rectangle... rectangles) {

        if (rectangles.length == 0) return null;

        int length = rectangles.length;

        int[] xArray = new int[length];
        int[] yArray = new int[length];
        int[] endXArray = new int[length]; //
        int[] endYArray = new int[length];

        for (int i = 0; i < length; i++) {

            Rectangle rectangle = rectangles[i];

            xArray[i] = rectangle.x;
            yArray[i] = rectangle.y;
            endXArray[i] = rectangle.endX;
            endYArray[i] = rectangle.endY;

            if (!rectangle.doesIntersect(rectangles)) return null; //check for every rectangle if they intersect
        }

        int minY = Utils.min(yArray);
        int maxX = Utils.max(xArray);

        int width = (int) Utils.dist(maxX, 0,
                Utils.min(endXArray), 0);
        int height = (int) Utils.dist(0,
                minY, 0,
                Utils.max(endYArray));

        return new Rectangle(maxX, minY, width, height);
    }

    /**
     * Creates a copy of a specified rectangle.
     *
     * @param toCopy The rectangle to copy.
     * @return A new Rectangle instance with the same properties as the specified rectangle.
     */
    public static Rectangle copy(Rectangle toCopy) {
        return new Rectangle(toCopy.x,
                toCopy.y,
                toCopy.width,
                toCopy.height);
    }

    /**
     * Calculates the area of this rectangle.
     *
     * @return The area of the rectangle.
     */
    public int area() {
        return this.width * this.height;
    }

    /**
     * Returns a string representation of the rectangle.
     *
     * @return A string describing the coordinates of the rectangle's corners in the format "(x1|y1),(x1|y2),(x2|y2),(x2|y1)".
     */
    public String toString() {
        return "(" + x + "|" + y + ")," +
                "(" + x + "|" + endY + ")," +
                "(" + endX + "|" + endY + ")," +
                "(" + endX + "|" + y + ")";
    }

    /**
     * Gets the x-coordinate of the rectangle.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the rectangle.
     *
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
        this.endX = x + width;
    }

    /**
     * Gets the y-coordinate of the rectangle.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the rectangle.
     *
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
        this.endY = y - height;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle. Only positive values are accepted.
     *
     * @param width The width to set.
     */
    public void setWidth(int width) {
        if (!arePositive(width)) {
            return;
        }
        this.width = width;
        this.endX = x + width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle. Only positive values are accepted.
     *
     * @param height The height to set.
     */
    public void setHeight(int height) {
        if (!arePositive(height)) {
            return;
        }
        this.height = height;
        this.endY = y - height;
    }

    private boolean arePositive(int... input) {
        for (int i : input) {
            if (i < 0) {
                Utils.error(NEGATIVE_VALUE);
                return false;
            }
        }
        return true;
    }

    private boolean doesIntersect(Rectangle... rectangles) {
        for (Rectangle comparison : rectangles) {

            if (comparison.endX < x
                    || comparison.endY > y)
                return false;
        }
        return true;
    }

}
