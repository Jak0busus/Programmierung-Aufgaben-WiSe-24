import de.jakob.util.SimpleIO;

/**
 * Programm, welches einen einfachen Stack implementiert
 */
public class OurStack {
  int currentSize = 0;
  String[] stack;

  public static void main(String[] args) {

    OurStack s = new OurStack();
    String operation;

    do {
      operation = SimpleIO.getString(
        "Bitte geben Sie eine Operation (PUSH,POP,CLEAR,SETSIZE,PRINT,STOP) ein:");
      switch(operation) {
        case "PUSH" -> s.push();
        case "POP" -> s.pop();
        case "CLEAR" -> s.clear();
        case "SETSIZE" -> {
          do
            int size = SimpleIO.getInt(
              "Bitte geben Sie die (nicht negative) Groesse ein:");
          while(size <  0);
          s.setSize(size);
        }
        case "PRINT", "STOP" -> s.print();
        default -> SimpleIO.output("Fehlerhafte Eingabe!");
      }
    } while(!operation.equals("STOP"));
  }

  public void push() {
    // TODO
  }

  public void pop() {
    // TODO
  }

  public void clear() {
    // TODO
  }

  public void setSize(int size) {
    // TODO
  }

  public void print() {
    // TODO
  }
}
