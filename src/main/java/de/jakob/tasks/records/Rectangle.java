package de.jakob.tasks.records;

import de.jakob.util.Utils;

/**
 * Represents a rectangle in a 2D plane with specified dimensions and coordinates.
 * Provides utility methods for calculating area, copying, checking square properties,
 * and finding intersections between rectangles.
 */
public record Rectangle(
        int x,
        int y,
        int width,
        int height,
        int endX,
        int endY) {

    private static final String NEGATIVE_VALUE = "Please enter positive values for these dimensions!";

    /**
     * Constructs a rectangle with specified x and y coordinates and specified width and height.
     *
     * @param xInput      The x-coordinate of the starting point.
     * @param yInput      The y-coordinate of the starting point.
     * @param widthInput  The width of the rectangle.
     * @param heightInput The height of the rectangle.
     */

    //wouldn't normally need a constructor because it is a record, but it allows automatically
    // calculating endX and endY to avoid code duplication

    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
        this(
                xInput,
                yInput,
                widthInput,
                heightInput,
                xInput + widthInput,
                yInput - heightInput
        );

        if (!arePositive(widthInput, heightInput)) {
            //could terminate but code works with negative values too
        }
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
        int[] endXArray = new int[length];
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

