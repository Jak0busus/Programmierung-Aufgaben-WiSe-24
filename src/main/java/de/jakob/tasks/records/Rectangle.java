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
        int height) { //didn't name them xInput, yInput ... because getting the values with Rectangle.widthInput() seems unlogical

    private static final String NEGATIVE_VALUE = "Please enter positive values for these dimensions!";

    //records purpose already documented
    public Rectangle {
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
            int maxX = minX + rectangles[0].width;
            int minY = maxY - rectangles[0].height;

            for (int i = 1; i < length; i++) {
                Rectangle rectangle = rectangles[i];

                minX = Utils.max(minX, rectangle.x);
                minY = Utils.max(minY, rectangle.y - rectangle.height);
                maxX = Utils.min(maxX, rectangle.x + rectangle.width);
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
