package de.jakob;

import de.jakob.tasks.EndDateCalculator;
import de.jakob.tasks.PromptCalc;
import de.jakob.tasks.Rectangle;
import de.jakob.util.Utils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Rectangle test = new Rectangle(0, 1, 1, 1);
        output(Rectangle.intersection(new Rectangle(-10,10,100,100), new Rectangle(-5, -5, 5, 200)));
        //new PromptCalc().prompt();
        //new EndDateCalculator().prompt();
    }

    public static void output(Rectangle test) {
        System.out.println(test.toString());
        System.out.println(test.area());
        System.out.println(test.getX() + ", " + test.getY() + ", " + test.getWidth() + ", " + test.getHeight());

    }
}
