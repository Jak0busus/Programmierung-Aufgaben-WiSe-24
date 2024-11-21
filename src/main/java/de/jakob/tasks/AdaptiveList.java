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
        if (isLast()) setNext(singletonList(value));
        else getNext().append(value);
        return this;
    }

    public boolean containsAdaptive(int value) {
        if (getValue() == value) return true;
        if (isLast()) return false;

        if (getNext().getValue() == value) {
            getNext().setValue(getValue());
            setValue(value);
            return true;
        }
        return getNext().containsAdaptive(value);
    }

    public boolean containsTopPriority(int value) {
        if (containsAdaptive(value)) {
            if (getValue() == value) return true;
            return containsTopPriority(value);
        }
        return false;
    }

    public boolean contains(int value) {
        if (getValue() == value) return true;
        if (isLast()) return false;
        return getNext().contains(value);
    }

    public String toString() { //decided to use the one available loop for the toString() method to avoid having to create a helper method
        String string = "";
        AdaptiveList current = this;

        while (current != null) {
            string += current.getValue() + (current.isLast() ? ", " : "");
            current = current.getNext();
        }
        return "[" + string + "]";
    }

}
