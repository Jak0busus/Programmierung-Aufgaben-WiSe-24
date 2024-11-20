package de.jakob.tasks;


/**
 * AdaptiveList is a custom implementation of a singly linked list.
 * Each node in the list contains an integer value and a reference to the next node.
 * The list supports various operations such as adding elements, retrieving values,
 * and rearranging the elements to prioritize certain values.
 */
public class AdaptiveList {

    private int value;
    private AdaptiveList next;

    public AdaptiveList(int value, AdaptiveList next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return this.value;
    }

    public AdaptiveList getNext() {
        return this.next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(AdaptiveList next) {
        this.next = next;
    }

    public static AdaptiveList singletonList(int value) {
        return new AdaptiveList(value, null);
    }

    public boolean isLast() {
        return next == null;
    }

    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this);
    }

    public AdaptiveList append(int value) {
        AdaptiveList toAppend = new AdaptiveList(value, null);
        if (getNext() == null) {
            setNext(toAppend);
        } else {
            getNext().append(value);
        }
        return this;
    }

    public boolean containsAdaptive(int value) {
        if (!contains(value)) return false;

        if (getNext().getValue() == value) {
            getNext().setValue(getValue());
            setValue(value);
            return true;
        }

        if (getValue() == value) return true;

        return getNext().containsAdaptive(value);
    }

    public boolean containsTopPriority(int value) {
        if (!contains(value)) return false;

        return containsTopPriority(this, value);
    }

    private boolean containsTopPriority(AdaptiveList start, int value) {
        AdaptiveList following = getNext();
        if (following != null && following.getValue() == value) {
            setNext(following.getNext()); //links the value to the one after "following"
            moveBack(start, following, value); //cant move "following" to the start cause the old AdaptiveList object wouldn't show it
            // changes first objects value to "value" and moves everything back by one
            // "following" gets put to the end to complete the list
            return true;
        }
        //"following" cant be null here because contains(...) already checked if the value is present
        return getValue() == value || following.containsTopPriority(start, value);
    }

    private static void moveBack(AdaptiveList element, AdaptiveList last, int value) {
        int old = element.getValue();
        element.setValue(value);

        if (element.getNext() == null) {
            element.setNext(last);
            last.setValue(old); //"last" could be left out by just creating a new AdaptiveList object
                                //it would then be taken care of by the garbage collector
            last.setNext(null);
        } else {
            moveBack(element.getNext(), last, old); //moves every element of the list back
        }
    }

    public boolean contains(int value) {
        if (getValue() == value) return true;
        if (getNext() == null) return false;
        return getNext().contains(value);
    }

    public String toString() { //decided to use the one available loop for the toString() method to avoid having to create a helper method
        //  it would probably be smarter to replace the recursive contains(...) logic with a loop because of
        // small performance improvements and its frequent calls
        String string = "";
        AdaptiveList current = this;

        while (current != null) {
            string += current.getValue() + (current.getNext() != null ? ", " : "");
            current = current.getNext();
        }

        return "[" + string + "]";
    }

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

    }

}
