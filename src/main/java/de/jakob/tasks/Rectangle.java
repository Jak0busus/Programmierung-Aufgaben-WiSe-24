package de.jakob.tasks;

import de.jakob.util.Utils;

public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    private int endX;
    private int endY;

    private final String error = "Please enter positive values for the rectangle!";

    public Rectangle(int x, int y, int width, int height) {

        if (!arePositive(x, y, width, height)) {
            Utils.error(error);
            return;
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.endX = x + width;
        this.endY = y - height;
    }

    public Rectangle(int x, int y, int sideLengthInput) {

        if (!arePositive(x, y, sideLengthInput)) {
            Utils.error(error);
            return;
        }

        this.x = x;
        this.y = y;
        this.width = sideLengthInput;
        this.height = sideLengthInput;

        this.endX = x + sideLengthInput;
        this.endY = y - sideLengthInput;
    }

    public Rectangle copy(Rectangle toCopy) {
        return new Rectangle(toCopy.x,
                toCopy.y,
                toCopy.width,
                toCopy.height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (!arePositive(x)) {
            return;
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (!arePositive(y)) {
            return;
        }
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (!arePositive(width)) {
            return;
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (!arePositive(height)) {
            return;
        }
        this.height = height;
    }

    public int area() {
        return this.width * this.height;
    }

    private boolean arePositive(int... input) {
        for (int i : input) {
            if (i < 0) {
                Utils.error(error);
                return false;
            }
        }
        return true;
    }

    public static boolean areSquares(Rectangle... rectangles) {
        for (Rectangle r : rectangles) {
            if (r.height != r.width) return false; //shorter than getter methods and doesn't make a difference
        }
        return true;
    }

    public static Rectangle intersection(Rectangle... rectangles) {

        int length = rectangles.length;

        int[] xArray = new int[length];
        int[] yArray = new int[length];

        int[] widthArray = new int[length];
        int[] heightArray = new int[length];

        for (int i = 0; i < length; i++) {

            Rectangle rectangle = rectangles[i];

            xArray[i] = rectangle.x;
            yArray[i] = rectangle.y;

            widthArray[i] = rectangle.endX;
            heightArray[i] = rectangle.endY;

            for (Rectangle comparison : rectangles) { //check for every rectangle if they intersect

                if (comparison.x + comparison.width < rectangle.x
                        || comparison.y - comparison.height > rectangle.y)
                    return null;

            }

        }

        int minY = Utils.min(yArray); //is going to be new Y
        int maxX = Utils.max(xArray); //is going to be new X

        int width = Utils.min(widthArray) - maxX; //subtracting the max X from the smallest right border of all rectangles, results in getting the width of the new rectangle
        int height = (int) Utils.dist(0,
                minY, 0,
                Utils.max(heightArray)); //similar to calculating the width, but using the Utils class because of possible negative y values

        return new Rectangle(maxX, minY, width, height);

    }

    public String toString() {
        return "(" + x + "|" + y + ") " +
                "(" + endX + "|" + y + ") " +
                "(" + endY + "|" + endX + ") " +
                "(" + endY + "|" + x + ")";

    }

}
