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

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen,
    //  um Listen zu erstellen.
    //Sie ist statisch, da sie nicht direkt auf den Kontext des AdaptiveList-Objekts zurückgreift,
    //  sondern ein neues AdaptiveList-Objekt zurückgibt.
    public static AdaptiveList singletonList(int value) {
        return new AdaptiveList(value, null);
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen,
    //  um zu überprüfen, ob ein Objekt weitere Elemente hinter sich hat.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    public boolean isLast() {
        return next == null;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen,
    //  um AdaptiveList-Objekte vor das Objekt, von dem diese Methode aus ausgeführt wurde, zu setzen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this);
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen,
    //  um AdaptiveList-Objekte hinter das letzte Objekt der AdaptiveList, von dem die Methode ausgeführt wurde, zu setzten.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    public AdaptiveList append(int value) {
        if (isLast()) setNext(singletonList(value));
        else getNext().append(value);
        return this;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
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

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    public boolean containsTopPriority(int value) {
        if (containsAdaptive(value)) {
            if (getValue() == value) return true;
            return containsTopPriority(value);
        }
        return false;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    public boolean contains(int value) {
        if (getValue() == value) return true;
        if (isLast()) return false;
        return getNext().contains(value);
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Zudem ist toString() eine vererbte Methode von der Klasse Object wodurch sie hier überschrieben wird und nichtmal static sein dürfte.
    @Override
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
