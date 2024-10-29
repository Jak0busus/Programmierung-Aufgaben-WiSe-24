package de.jakob.tasks;

import de.jakob.util.SimpleIO;

import java.util.Arrays;

/**
 * Programm, welches einen einfachen Stack implementiert
 */

public class OurStack {
    int currentSize = 0;
    String[] stack = new String[0];

    public static void main(String[] args) {

        OurStack s = new OurStack();
        String operation;

        do {
            System.out.println(s.debug());
            operation = SimpleIO.getString(
                    "Bitte geben Sie eine Operation (PUSH,POP,CLEAR,SETSIZE,PRINT,STOP) ein:").toUpperCase();
            System.out.println();
            switch (operation) {
                case "PUSH" -> s.push();
                case "POP" -> s.pop();
                case "CLEAR" -> s.clear();
                case "SETSIZE" -> {
                    int size;
                    do {
                        size = SimpleIO.getInt(
                                "Bitte geben Sie die (nicht negative) Groesse ein:");
                    } while (size < 0);
                    s.setSize(size);
                }
                case "PRINT", "STOP" -> s.print();
                default -> SimpleIO.output("Fehlerhafte Eingabe!");
            }
        } while (!operation.equals("STOP"));
    }

    public void push() {
        if (currentSize < stack.length) {
            stack[currentSize++] = SimpleIO.getString("Geben Sie ein zu speicherndes Element ein :");
            return;
        }
        SimpleIO.output("Stack ist voll.");
    }

    public void pop() {
        if (currentSize > 0) {
            stack[currentSize-1] = null;
            currentSize--;
            return;
        }
        SimpleIO.output("Stack ist bereits leer.");
    }

    public void clear() {
        currentSize = 0;
        stack = new String[stack.length];
    }

    public void setSize(int size) {
        String[] newStack = new String[size];
        currentSize = 0;
        copyStack(newStack);
    }

    public void print() {
        String message;
        if (currentSize == 0) {
            message = "Stack ist leer.";
        } else {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < currentSize; i++) {
                output.append(stack[i]).append(", ");
            }
            int length = output.length();
            message = output
                    .delete(length - 2, length)
                    .toString();
        }

        SimpleIO.output(message);

    }

    private void copyStack(String[] newStack){
        for (int i = 0; i < newStack.length; i++) {
            if (i <= stack.length - 1) {
                currentSize++;
                newStack[i] = stack[i];
            } else {
                break;
            }
        }
        stack = newStack;
    }

    public String debug(){
        return currentSize + ", " + stack.length + ", " + Arrays.toString(stack);
    }

}
