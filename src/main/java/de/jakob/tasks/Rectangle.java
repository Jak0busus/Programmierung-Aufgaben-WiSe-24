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
    //adding endX endY to avoid code redundancy
    private int endX; //stands for the last x coordinate of the rectangle
    private int endY; //similar to endX but for the lowest y coordinate

    private static final String NEGATIVE_VALUE = "Please enter positive values for these dimensions!";

    /**
     * Constructs a rectangle with specified x and y coordinates and specified width and height.
     *
     * @param xInput      The x-coordinate of the starting point.
     * @param yInput      The y-coordinate of the starting point.
     * @param widthInput  The width of the rectangle.
     * @param heightInput The height of the rectangle.
     */

    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {

        if (!arePositive(widthInput, heightInput)) { //arePositive throws error message if negative
            //could terminate but code works with negative values too, theoretically
        }

        x = xInput;
        y = yInput;
        width = widthInput;
        height = heightInput;

        //coordinates of the other corners
        endX = xInput + widthInput;
        endY = yInput - heightInput;
    }

    /**
     * Constructs a square with specified x and y coordinates and a single side length.
     *
     * @param xInput          The x-coordinate of the starting point.
     * @param yInput          The y-coordinate of the starting point.
     * @param sideLengthInput The length of each side of the square.
     */
    public Rectangle(int xInput, int yInput, int sideLengthInput) {
        this(xInput, yInput, sideLengthInput, sideLengthInput);
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

        int minX = rectangles[0].x;
        int maxY = rectangles[0].y;
        int maxX = rectangles[0].endX;
        int minY = rectangles[0].endY;

        for (int i = 1; i < length; i++) {
            Rectangle rectangle = rectangles[i];

            minX = Utils.max(minX, rectangle.x);
            minY = Utils.max(minY, rectangle.endY);
            maxX = Utils.min(maxX, rectangle.endX);
            maxY = Utils.min(maxY, rectangle.y);

            if (minX >= maxX || minY >= maxY) return null;
        }

        int width = (int) Utils.dist(minX, 0, maxX, 0);
        int height = (int) Utils.dist(0, minY, 0 , maxY);

        return new Rectangle(minX, maxY, width, height);
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

    /**
     * Represents a rectangle in a 2D plane with specified dimensions and coordinates.
     * Provides utility methods for calculating area, copying, checking square properties,
     * and finding intersections between rectangles.
     */
    public record RectangleRecord(
            int x,
            int y,
            int width,
            int height) { //didn't name them xInput, yInput ... because getting the values with Rectangle.widthInput() seems unlogical

        private static final String NEGATIVE_VALUE = "Please enter positive values for these dimensions!";

        //records purpose already documented
        public RectangleRecord {
            if (!arePositive(width, height)) { //arePositive throws error message if negative
                //could terminate but code works with negative values too, theoretically
            }
        }

        /**
         * Checks if all rectangles in the array are squares.
         *
         * @param rectangles The array of rectangles to check.
         * @return true if all rectangles are squares, false otherwise.
         */
        public static boolean areSquares(RectangleRecord... rectangles) {
            for (RectangleRecord r : rectangles) {
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
        public static RectangleRecord intersection(RectangleRecord... rectangles) {

            if (rectangles.length == 0) return null;

            int length = rectangles.length;

            int minX = rectangles[0].x;
            int maxY = rectangles[0].y;
            int maxX = minX + rectangles[0].width;
            int minY = maxY - rectangles[0].height;

            for (int i = 1; i < length; i++) {
                RectangleRecord rectangle = rectangles[i];

                minX = Utils.max(minX, rectangle.x);
                minY = Utils.max(minY, rectangle.y - rectangle.height);
                maxX = Utils.min(maxX, rectangle.x + rectangle.width);
                maxY = Utils.min(maxY, rectangle.y);

                if (minX >= maxX || minY >= maxY) return null;
            }

            int width = (int) Utils.dist(minX, 0, maxX, 0);
            int height = (int) Utils.dist(0, minY, 0 , maxY);

            return new RectangleRecord(minX, maxY, width, height);
        }

        /**
         * Creates a copy of a specified rectangle.
         *
         * @param toCopy The rectangle to copy.
         * @return A new Rectangle instance with the same properties as the specified rectangle.
         */
        public static RectangleRecord copy(RectangleRecord toCopy) {
            return new RectangleRecord(toCopy.x,
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
            int endY = y - height;
            int endX = x + width;
            return "(" + x + "|" + y + ")," +
                    "(" + x + "|" + endY + ")," +
                    "(" + endX + "|" + endY + ")," +
                    "(" + endX + "|" + y + ")";
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

    }


}
