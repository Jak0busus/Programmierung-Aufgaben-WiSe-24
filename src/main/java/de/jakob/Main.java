package de.jakob;

import de.jakob.tasks.AdaptiveList;
import de.jakob.tasks.Rectangle;
import de.jakob.tasks.Tagesgeld;

public class Main {

//    public static void main(String... args) {
//        //check for a
//        Tagesgeld t = new Tagesgeld(50000, 1, 5.5, 1.5);
//
//        System.out.println(10057.187381927695 + " (expected)");
//        System.out.println(t.optimaleVerzinsung(10000, 1) + " (Erklärung befindet sich unter der Methode)");
//
//        System.out.println();
//
//        //check for b
//        Tagesgeld t1 = new Tagesgeld(50000, 3, 5.5, 1.5);
//        Tagesgeld t2 = new Tagesgeld(50000, 0, 5.5, 1.5);
//        Tagesgeld t3 = new Tagesgeld(50000, 1, 5.5, 1.5);
//
//        System.out.println(1 + " (expected)");
//        System.out.println(Tagesgeld.verkuerzeUmKuerzesteLaufzeit(true, t1, t2, t3));
//        System.out.println("2, 0, 0 (expected)");
//        System.out.println(t1.getAngebotsmonate() + "," + t2.getAngebotsmonate() + "," + t3.getAngebotsmonate());
//
//        System.out.println();
//
//        //check for c
//        Tagesgeld t0 = new Tagesgeld(5000, 9, 3.5, 1.5);
//        t1 = new Tagesgeld(50000, 6, 3.0, 2.0);
//        t2 = new Tagesgeld(150000, 5, 4.5, 1.75);
//        t3 = new Tagesgeld(60000, 8, 3.5, 1.25);
//
//        System.out.println(10080.148839674815 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(10000, 3, t0, t1, t2, t3));
//        System.out.println(10160.572744118412 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(10000, 6, t0, t1, t2, t3));
//        System.out.println(10272.751782132696 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(10000, 12, t0, t1, t2, t3));
//
//        System.out.println();
//
//        System.out.println(100910.9285190934 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(100000, 3, t0, t1, t2, t3));
//        System.out.println(101725.24878187178 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(100000, 6, t0, t1, t2, t3));
//        System.out.println(102682.28545476876 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(100000, 12, t0, t1, t2, t3));
//
//        System.out.println();
//
//        System.out.println(252032.2085361965 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(250000, 3, t0, t1, t2, t3));
//        System.out.println(253841.61267414704 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(250000, 6, t0, t1, t2, t3));
//        System.out.println(255859.91900185865 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(250000, 12, t0, t1, t2, t3));
//
//
//        System.out.println();
//
//        System.out.println(1002032.2085361965 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(1000000, 3, t0, t1, t2, t3));
//        System.out.println(1003841.6126741471 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(1000000, 6, t0, t1, t2, t3));
//        System.out.println(1005859.9190018587 + " (expected)");
//        System.out.println(Tagesgeld.verzinseParallel(1000000, 12, t0, t1, t2, t3));
//
//    }

    public static void main(String[] args) {

        // Test for singletonList
        System.out.println("Testing singletonList:");
        AdaptiveList single1 = AdaptiveList.singletonList(10);
        System.out.println("Expected: [10], Actual: " + single1); // [10]
        AdaptiveList single2 = AdaptiveList.singletonList(42);
        System.out.println("Expected: [42], Actual: " + single2); // [42]

        // Test for isLast
        System.out.println("\nTesting isLast:");
        AdaptiveList list1 = AdaptiveList.singletonList(15);
        System.out.println("Expected: true, Actual: " + list1.isLast()); // true
        AdaptiveList list2 = AdaptiveList.singletonList(1).append(2).append(3);
        System.out.println("Expected: false, Actual: " + list2.isLast()); // false

        // Test for prepend
        System.out.println("\nTesting prepend:");
        AdaptiveList list3 = AdaptiveList.singletonList(17);
        System.out.println("Before prepend: " + list3);
        System.out.println("Expected: [4, 17], Actual: " + list3.prepend(4)); // [4, 17]
        AdaptiveList list4 = AdaptiveList.singletonList(5).append(10);
        System.out.println("Expected: [1, 5, 10], Actual: " + list4.prepend(1)); // [1, 5, 10]

        // Test for contains
        System.out.println("\nTesting contains:");
        AdaptiveList list7 = AdaptiveList.singletonList(4).append(17).append(25);
        System.out.println("Expected: true, Actual: " + list7.contains(17)); // true
        System.out.println("Expected: false, Actual: " + list7.contains(99)); // false

        // Test for containsAdaptive
        System.out.println("\nTesting containsAdaptive:");
        AdaptiveList list8 = AdaptiveList.singletonList(4).append(17).append(25);
        System.out.println("Before containsAdaptive(25): " + list8);
        System.out.println("Expected: true, Actual: " + list8.containsAdaptive(25)); // true
        System.out.println("Expected: [4, 25, 17], Actual: " + list8); // [4, 25, 17]

        AdaptiveList list9 = AdaptiveList.singletonList(1).append(2).append(3);
        System.out.println("Before containsAdaptive(99): " + list9);
        System.out.println("Expected: false, Actual: " + list9.containsAdaptive(99)); // false
        System.out.println("Expected: [1, 2, 3], Actual: " + list9); // [1, 2, 3]

        // Test for containsTopPriority
        System.out.println("\nTesting containsTopPriority:");
        AdaptiveList list10 = AdaptiveList.singletonList(4).append(17).append(25);
        System.out.println("Before containsTopPriority(25): " + list10);
        System.out.println("Expected: true, Actual: " + list10.containsTopPriority(25)); // true
        System.out.println("Expected: [25, 4, 17], Actual: " + list10); // [25, 4, 17]

        AdaptiveList list11 = AdaptiveList.singletonList(1).append(2).append(3);
        System.out.println("Before containsTopPriority(99): " + list11);
        System.out.println("Expected: false, Actual: " + list11.containsTopPriority(99)); // false
        System.out.println("Expected: [1, 2, 3], Actual: " + list11); // [1, 2, 3]


        // Proper Test for containsTopPriority and containsAdaptive
        AdaptiveList list12 = AdaptiveList.singletonList(0);
        for (int i = 1; i <= 100; i++) {
            list12.append(i);
        }
        for (int i = 100; i >= 0; i--) {
            list12.containsTopPriority(i);
        }
        System.out.println("Expected: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, " +
                "21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, " +
                "41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, " +
                "60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, " +
                "80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]");
        System.out.println("Actual:   " + list12);
        for (int i = 1; i <= 100; i++) {
            list12.containsTopPriority(i);
        }
        System.out.println("Expected: [100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, " +
                "79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, " +
                "60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, " +
                "40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, " +
                "20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]");
        System.out.println("Actual:   " + list12);
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                list12.containsAdaptive(i);
            }
        }
        System.out.println("Expected: [100, 98, 99, 96, 97, 94, 95, 92, 93, 90, 91, 88, 89, 86, 87, 84, 85, 82, 83, 80, " +
                "81, 78, 79, 76, 77, 74, 75, 72, 73, 70, 71, 68, 69, 66, 67, 64, 65, 62, 63, 60, " +
                "61, 58, 59, 56, 57, 54, 55, 52, 53, 50, 51, 48, 49, 46, 47, 44, 45, 42, 43, " +
                "40, 41, 38, 39, 36, 37, 34, 35, 32, 33, 30, 31, 28, 29, 26, 27, 24, 25, 22, 23, " +
                "20, 21, 18, 19, 16, 17, 14, 15, 12, 13, 10, 11, 8, 9, 6, 7, 4, 5, 2, 3, 1, 0]");
        System.out.println("Actual:   " + list12);

        new Main().adaptiveListTest();
    }

    private  void adaptiveListTest() {
        System.out.println("\nTesting singletonList():");
        AdaptiveList l1 = AdaptiveList.singletonList(5);
        expectEq(null, l1.getNext());
        expectEq(5, l1.getValue());
        System.out.println("\nTesting isLast():");
        expectEq(true, l1.isLast());
        AdaptiveList l2 = new AdaptiveList(2, l1);
        expectEq(false, l2.isLast());
        System.out.println("\nTesting prepend():");
        expectEq(1, AdaptiveList.singletonList(2).prepend(1).getValue());
        expectEq(2, AdaptiveList.singletonList(2).prepend(1).getNext().getValue());
        System.out.println("\nTesting append():");
        expectEq(2, AdaptiveList.singletonList(2).append(1).getValue());
        expectEq(1, AdaptiveList.singletonList(2).append(1).getNext().getValue());
        System.out.println("\nTesting contains():");
        assert l2.getValue() == 2; assert l1.getValue() == 5;
        assert l2.getNext() == l1; assert l1.getNext() == null;
        expectEq(true, l2.contains(5));
        expectEq(true, l2.contains(2));
        expectEq(false, l2.contains(3));
        expectEq(true, l1.contains(5));
        expectEq(false, l1.contains(2));
        expectEq(2, l2.getValue());
        expectEq(5, l1.getValue());
        expectEq(l1, l2.getNext());
        expectEq(null, l1.getNext());
        System.out.println("\nTesting containsAdaptive():");
        assert l2.getValue() == 2; assert l1.getValue() == 5;
        assert l2.getNext() == l1; assert l1.getNext() == null;
        expectEq(true, l2.containsAdaptive(5));
        expectEq(5, l2.getValue());
        expectEq(l1, l2.getNext());
        expectEq(true, l2.containsAdaptive(2));
        expectEq(2, l2.getValue());
        expectEq(l1, l2.getNext());
        expectEq(false, l2.containsAdaptive(3));
        expectEq(2, l2.getValue());
        expectEq(l1, l2.getNext());
        AdaptiveList l3 = new AdaptiveList(3, l2);
        expectEq(5, l1.getValue());
        expectEq(3, l3.getValue());
        expectEq(l1, l2.getNext());
        expectEq(l2, l3.getNext());
        expectEq(true, l3.containsTopPriority(5));
        expectEq(2, l1.getValue());
        expectEq(5, l3.getValue());
        expectEq(l1, l2.getNext());
        expectEq(l2, l3.getNext());
        expectEq(false, l3.containsTopPriority(10));
        expectEq(2, l1.getValue());
        expectEq(5, l3.getValue());
        expectEq(l1, l2.getNext());
        expectEq(l2, l3.getNext());
        System.out.println(l1 + " " + l2 + " " + l3);
        System.out.println("\n Passed " + passedTestCount + "/" + totalTestCount + " Tests.");
    }


    int totalTestCount = 0;
    int passedTestCount = 0;
    private <T> void expectEq(T expected, T actual) {
        totalTestCount++;
        if (expected == actual) {
            passedTestCount++;
            System.out.println("- [X] " + expected + " == " + actual);
        } else {
            System.err.println("- [ ] Expected " + expected + " but got " + actual);
        }
    }
}
