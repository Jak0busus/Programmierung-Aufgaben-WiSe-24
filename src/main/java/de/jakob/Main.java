package de.jakob;

import de.jakob.tasks.Rectangle;

public class Main {

    public static void main(String[] args) {

        //Rectangle test = new Rectangle(0, 1, 1, 1);
        //ROutput(de.jakob.tasks.records.Rectangle.intersection(new de.jakob.tasks.records.Rectangle(-10,10,100,100), new de.jakob.tasks.records.Rectangle(-5, -5, 5, 200)));

        //output(Rectangle.intersection(new Rectangle(-10,10,100,100), new Rectangle(-5, -5, 5, 200)));

        //new de.jakob.tasks.records.Rectangle(1,2,3,4);
        //new PromptCalc().prompt();
        bewegeTurm(3 , "ALPHA", "DELTA", "OMEGA");

        //new EndDateCalculator().prompt();
    }

    //public static void()
    private static void bewegeTurm (int hoehe, String von, String ueber, String nach) {

        if (hoehe > 0) {
            bewegeTurm (hoehe-1, von, nach, ueber);
            druckeZug  (hoehe, von, nach);
            bewegeTurm (hoehe-1, ueber, von, nach);
        }

    }

    private static void druckeZug (int hoehe, String von, String nach) {

        System.out.println ("Scheibe " + hoehe + " von " + von + " nach " + nach);

    }

    public static void output(Rectangle test) {
        System.out.println(test.toString());
        System.out.println(test.area());
        System.out.println(test.getX() + ", " + test.getY() + ", " + test.getWidth() + ", " + test.getHeight());
    }
    public static void ROutput(de.jakob.tasks.records.Rectangle test) {
        System.out.println(test.toString());
        System.out.println(test.area());
        System.out.println(test.x() + ", " + test.y() + ", " + test.width() + ", " + test.height());
    }
}
