package de.jakob.tasks;

public class AdaptiveList {

    private int value;
    private AdaptiveList next;

    public AdaptiveList(int value, AdaptiveList next) {
        this.value = value;
        this.next = next;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp von der Aufgabenstellung gegeben
    public int getValue() {
        return this.value;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp von der Aufgabenstellung gegeben
    public AdaptiveList getNext() {
        return this.next;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Void, da kein Wert returned wird
    public void setValue(int value) {
        this.value = value;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Void, da kein Wert returned wird
    public void setNext(AdaptiveList next) {
        this.next = next;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist statisch, da sie nicht direkt auf den Kontext des AdaptiveList-Objekts zurückgreift,
    //  sondern ein neues AdaptiveList-Objekt zurückgibt.
    //Rückgabetyp AdaptiveList, da singletonList für die Erstellung von Listen genutzt wird.
    public static AdaptiveList singletonList(int value) {
        return new AdaptiveList(value, null);
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp boolean, da das Element entweder das letzte sein kann, oder nicht
    public boolean isLast() {
        return next == null;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp AdaptiveList, da das neue vorderste Element der Liste benötigt wird um die gesamte Liste zu bekommen
    public AdaptiveList prepend(int value) {
        return new AdaptiveList(value, this);
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp AdaptiveList, da es in der Aufgabe gefordert wurde, könnte auch Void sein.
    public AdaptiveList append(int value) {
        if (isLast()) setNext(singletonList(value));
        else getNext().append(value);
        return this;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp boolean, da der Wert entweder in der Liste ist, oder nicht
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
    //Rückgabetyp boolean, da der Wert entweder in der Liste ist, oder nicht
    public boolean containsTopPriority(int value) {
        if (containsAdaptive(value)) {
            if (getValue() == value) return true;
            return containsTopPriority(value);
        }
        return false;
    }

    //Diese Methode ist public, da wir sie von außerhalb der Klasse AdaptiveList aufrufen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des AdaptiveList-Objekts zurückgreift.
    //Rückgabetyp boolean, da der Wert entweder in der Liste ist, oder nicht
    public boolean contains(int value) {
        if (getValue() == value) return true;
        if (isLast()) return false;
        return getNext().contains(value);
    }

    //Eigene Implementierung der toString Methode um sich gesamte Listen ausgeben zu lassen
    //gehört nicht direkt zur Aufgabe
    @Override
    public String toString() {
        String string = "";
        AdaptiveList current = this;

        while (current != null) {
            string += current.getValue() + (!current.isLast() ? ", " : "");
            current = current.getNext();
        }
        return "[" + string + "]";
    }


}
