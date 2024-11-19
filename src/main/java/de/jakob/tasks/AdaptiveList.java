package de.jakob.tasks;

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
            setNext(following.getNext()); //skips the value
            moveBack(start, value, following); //cant move "following" to the start cause the old AdaptiveList object wouldn't show it
                                        // changes first objects value to value and moves everything back by one
                                        // "following" gets put to the end to complete the list
            return true;
        }
        //"following" cant be null here because contains(...) already checked if the value is present
        return getValue() == value || following.containsTopPriority(start, value);
    }

    private static void moveBack(AdaptiveList element, int value, AdaptiveList last) {
        int old = element.getValue();
        element.setValue(value);

        if (element.getNext() == null) {
            element.setNext(last);
            last.setValue(old); //"last" could be left out by just creating a new AdaptiveList object > it would then be taken care of by the garbage collector
            last.setNext(null);
        } else {
            moveBack(element.getNext(), old, last); //moves every element of the list back
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
            string = string + current.getValue();
            current = current.getNext();
            if (current != null) {
                string = string + ", ";
            }
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

    }

}
