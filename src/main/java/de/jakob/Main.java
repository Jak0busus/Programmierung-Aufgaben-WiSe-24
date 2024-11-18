package de.jakob;

import de.jakob.tasks.Rectangle;
import de.jakob.tasks.Tagesgeld;

public class Main {

    public static void main(String... args) {
        //check for a
        Tagesgeld t = new Tagesgeld(50000, 1, 5.5, 1.5);

        System.out.println(10057.187381927695 + " (expected)");
        System.out.println(t.optimaleVerzinsung(10000, 1) + " (Erklärung befindet sich unter der Methode)");

        System.out.println();

        //check for b
        Tagesgeld t1 = new Tagesgeld(50000, 3, 5.5, 1.5);
        Tagesgeld t2 = new Tagesgeld(50000, 0, 5.5, 1.5);
        Tagesgeld t3 = new Tagesgeld(50000, 1, 5.5, 1.5);

        System.out.println(1 + " (expected)");
        System.out.println(Tagesgeld.verkuerzeUmKuerzesteLaufzeit(true, t1, t2, t3));
        System.out.println("2, 0, 0 (expected)");
        System.out.println(t1.getAngebotsmonate() + "," + t2.getAngebotsmonate() + "," + t3.getAngebotsmonate());

        System.out.println();

        //check for c
        Tagesgeld t0 = new Tagesgeld(5000, 9, 3.5, 1.5);
        t1 = new Tagesgeld(50000, 6, 3.0, 2.0);
        t2 = new Tagesgeld(150000, 5, 4.5, 1.75);
        t3 = new Tagesgeld(60000, 8, 3.5, 1.25);

        System.out.println(10080.148839674815 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(10000, 3, t0, t1, t2, t3));
        System.out.println(10160.572744118412 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(10000, 6, t0, t1, t2, t3));
        System.out.println(10272.751782132696 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(10000, 12, t0, t1, t2, t3));

        System.out.println();

        System.out.println(100910.9285190934 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(100000, 3, t0, t1, t2, t3));
        System.out.println(101725.24878187178 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(100000, 6, t0, t1, t2, t3));
        System.out.println(102682.28545476876 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(100000, 12, t0, t1, t2, t3));

        System.out.println();

        System.out.println(252032.2085361965 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(250000, 3, t0, t1, t2, t3));
        System.out.println(253841.61267414704 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(250000, 6, t0, t1, t2, t3));
        System.out.println(255859.91900185865 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(250000, 12, t0, t1, t2, t3));


        System.out.println();

        System.out.println(1002032.2085361965 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(1000000, 3, t0, t1, t2, t3));
        System.out.println(1003841.6126741471 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(1000000, 6, t0, t1, t2, t3));
        System.out.println(1005859.9190018587 + " (expected)");
        System.out.println(Tagesgeld.verzinseParallel(1000000, 12, t0, t1, t2, t3));

    }
}
