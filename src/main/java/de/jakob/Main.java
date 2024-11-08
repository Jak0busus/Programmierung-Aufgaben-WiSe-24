package de.jakob;

import de.jakob.tasks.EndDateCalculator;
import de.jakob.tasks.PromptCalc;
import de.jakob.tasks.Rectangle;
import de.jakob.util.Utils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //Rectangle test = new Rectangle(0, 1, 1, 1);
        output(new Rectangle(1,2,3,4));
        //new PromptCalc().prompt();
        //new EndDateCalculator().prompt();

    }

    public static void output(Rectangle test) {
        System.out.println(test.toString());
        System.out.println(test.area());
        System.out.println(test.getX() + ", " + test.getY() + ", " + test.getWidth() + ", " + test.getHeight());

    }

}
