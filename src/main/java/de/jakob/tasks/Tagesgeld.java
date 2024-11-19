package de.jakob.tasks;

public class Tagesgeld {

    private int maxBetrag;
    private int angebotsmonate;
    private double angebotszinsen;
    private double normalzinsen;

    public Tagesgeld(int maxBetrag, int angebotsmonate, double angebotszinsen, double normalzinsen) {
        this.maxBetrag = maxBetrag;
        this.angebotsmonate = angebotsmonate;
        this.angebotszinsen = angebotszinsen;
        this.normalzinsen = normalzinsen;
    }

    public int getMaxBetrag() {
        return maxBetrag;
    }

    public void setMaxBetrag(int maxBetrag) {
        this.maxBetrag = Math.max(0, maxBetrag);
    }

    public int getAngebotsmonate() {
        return angebotsmonate;
    }

    public void setAngebotsmonate(int angebotsmonate) {
        this.angebotsmonate = Math.max(0, angebotsmonate);
    }

    public double getAngebotszinsen() {
        return angebotszinsen;
    }

    public void setAngebotszinsen(double angebotszinsen) {
        this.angebotszinsen = angebotszinsen;
    }

    public double getNormalzinsen() {
        return normalzinsen;
    }

    public void setNormalzinsen(double normalzinsen) {
        this.normalzinsen = normalzinsen;
    }

    //Diese Methode ist public, da die Berechnung auch in anderen Klassen gebraucht werden koennte
    //  Man kann aber auch argumentieren, dass sie als Hilfsmethode fuer die Klasse Tagesgeld private sein sollte
    //Sie ist ausserdem statisch, da sie nichts mit einem konkreten Tagesgeld-Objekt zu tun hat
    public static double jahresZuMonatszins(double jahreszins) {
        return (Math.pow(1 + jahreszins / 100, 1.0 / 12) - 1.0) * 100;
    }

    //Diese Methode ist private, da die Signatur nach aussen hin nicht sichtbar sein soll.
    //  Die Klasse soll ja gerade die Berechnung uebernehmen, wann welcher Zinssatz gilt,
    //  und dies nicht dem Nutzer ueberlassen.
    //Die Methode ist nicht statisch, denn sie hat mit dem Angebot eines konkreten Tagesgeld-Objekts zu tun
    private double monatsverzinsung(double init, boolean angebot) {
        double jz = angebot ? this.angebotszinsen : this.normalzinsen;
        double mz = jahresZuMonatszins(jz);
        return init * (1.0 + mz / 100);
    }

    //Diese Methode ist public, weil wir von ausserhalb der Klasse Tagesgeld die Verzinsung eines
    //  Tagesgeld-Objekts berechnen koennen wollen
    //Daher ist sie auch nicht statisch, denn sie hat mit dem Angebot eines konkreten Tagesgeld-Objekts zu tun
    public double verzinse(double init, int monate) {
        if (monate == 0) {
            return init;
        }
        double vorher = this.verzinse(init, monate - 1);
        double rest = 0.0;
        double grenze = Math.min(this.getMaxBetrag(), 100000);
        if (vorher > grenze) {
            rest = vorher - grenze;
            vorher = grenze;
        }

        return monatsverzinsung(vorher, monate <= this.getAngebotsmonate()) + rest;
    }

    //Diese Methode ist public, da wir von außerhalb der Klasse Tagesgeld die optimale Verzinsung
    //  für eine bestimmte Situation berechnen können wollen.
    //Sie ist nicht statisch, da sie direkt auf den Kontext des Tagesgeld-Objekts zurückgreift.
    public double optimaleVerzinsung(double init, int nm) {
        return optimaleVerzinsung(init, nm, nm + angebotsmonate);
    }

    //Die Reihenfolge in der die verschiedenen Zinssätze angewendet werden macht mathematisch
    //  betrachtet keinen Unterschied, da das Ergebnis der Multiplikation der Zinssätze unabhängig
    //  von der Reihenfolge immer zum gleichen Ergebnis führt. Multiplikation ist nähmlich kommutativ.
    //Die geringen Unterschiede, die bei der Berechnung dieser Werte innerhalb
    //  des Programs auftreten, lassen sich darauf zurückführen wie der Computer und der Compiler mit Gleitkommazahlen umgehen.
    //Dezimalzahlen können dort nur mit begrenzter Genauigkeit verarbeitet werden,
    //  weshalb durch unterschiedliche Rundungen unterschiedliche Ergebnisse zustande kommen.

    //Diese Hilfsmethode ist private, da sie nur von der obigen Methode gleichen Namens aufgerufen
    //  werden soll. Sie ist jedoch ebenfalls nicht statisch, da sie sich auf Instanzvariablen wie
    //  angebotsmonate bezieht.
    private double optimaleVerzinsung(double init, int nm, int monate) {
        if (monate == 0) return init;

        double vorher = optimaleVerzinsung(init, nm, monate - 1);
        return monatsverzinsung(vorher, nm + angebotsmonate > monate);
    }

    //Diese Methode ist public, da wir von außerhalb der Klasse Tagesgeld mehrere Angebote parallel
    //  für eine gegebene Laufzeit verzinsen können wollen.
    // Sie ist statisch, da sie keine Instanzvariablen benötigt und mehrere Tagesgeld-Objekte
    //  verarbeitet, die als Parameter übergeben werden.
    public static double verzinseParallel(double init, int monate, Tagesgeld... ts) {
        return verzinseParallel(init, monate, ts, 0);
    }

    //Diese Hilfsmethode ist private, da sie nur innerhalb der Klasse genutzt wird.
    // Sie ist statisch, da sie keine Instanzvariablen benötigt und mehrere Tagesgeld-Objekte
    //  verarbeitet, die als Parameter übergeben werden.
    private static double verzinseParallel(double init, int monate, Tagesgeld[] ts, int pos) {
        if (monate == 0 || pos == ts.length) return init;

        Tagesgeld tg = ts[pos];

        if (tg.angebotsmonate <= 0) return verzinseParallel(init, monate, ts, pos + 1);

        double zuVerzinsen = Math.min(100000, Math.min(init, tg.maxBetrag));
        //determines the amount that can be used for this account
        init -= zuVerzinsen;

        return tg.verzinse(zuVerzinsen, monate) + verzinseParallel(init, monate, ts, pos + 1);
        //makes use of the verzinse method and calls itself again with subtracted init
    }

    //Diese Methode ist public, da wir von außerhalb der Klasse Tagesgeld die kürzeste Laufzeit
    //  eines Tagesgeld-Angebots ermitteln oder die Laufzeit entsprechend anpassen können wollen.
    //Sie ist statisch, da sie auf keinen spezifischen Kontext eines Tagesgeld-Objekts angewiesen ist,
    //  sondern mehrere Tagesgeld-Objekte verarbeitet.
    public static int verkuerzeUmKuerzesteLaufzeit(boolean verkuerze, Tagesgeld... ts) {
        if (ts == null) return Integer.MAX_VALUE;
        int smallest = verkuerzeUmKuerzesteLaufzeit(false, 0, Integer.MAX_VALUE, ts);
        //determines the smallest value
        if (verkuerze) verkuerzeUmKuerzesteLaufzeit(true, 0, smallest, ts);
        //in case of verkuerze the return value of the method isn't needed

        return smallest;
    }

    //Diese Hilfsmethode ist private, da sie nur innerhalb der Klasse genutzt wird.
    //Sie ist statisch, weil sie mehrere Tagesgeld-Objekte verarbeitet und dabei keinen Bezug zu einer
    //  spezifischen Instanz der Klasse Tagesgeld hat.
    private static int verkuerzeUmKuerzesteLaufzeit(boolean verkuerze, int pos, int smallest, Tagesgeld[] ts) {
        if (pos == ts.length) return smallest;

        if (ts[pos].angebotsmonate > 0) smallest = Math.min(smallest, ts[pos].angebotsmonate);

        if (verkuerze) ts[pos].angebotsmonate = Math.max(0, ts[pos].angebotsmonate - smallest);

        return verkuerzeUmKuerzesteLaufzeit(verkuerze, pos + 1, smallest, ts);
    }

    //Diese Methode ist public, da wir von ausserhalb der Klasse Tagesgeld das beste Tagesgeld fuer eine
    //  bestimmte Situation berechnen koennen wollen
    //Sie ist ausserdem statisch, da sie nicht auf einem Tagesgeld-Objekt aufgerufen wird, sondern eine
    //  Anzahl von Tagesgeld-Objekten uebergeben bekommt
    public static Tagesgeld bestesTagesgeld(double init, int monate, Tagesgeld... ts) {
        if (ts == null) {
            return null; //Methode darf sich in diesem Fall beliebig verhalten, also insb. null zurueckgeben
        }
        return ts[bestesTagesgeld(init, monate, 0, ts)];
    }

    //Diese Hilfsmethode ist private, da sie nur von obiger Methode gleichen Namens aufgerufen werden soll
    //Sie ist aber aus denselben Gruenden wie diese statisch
    private static int bestesTagesgeld(double init, int monate, int pos, Tagesgeld... ts) {
        if (pos == ts.length - 1) {
            return pos;
        }
        int bestes = bestesTagesgeld(init, monate, pos + 1, ts);
        if (ts[pos].verzinse(init, monate) > ts[bestes].verzinse(init, monate)) {
            return pos;
        } else {
            return bestes;
        }
    }

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
        System.out.println(verkuerzeUmKuerzesteLaufzeit(true, t1, t2, t3));
        System.out.println("2, 0, 0 (expected)");
        System.out.println(t1.getAngebotsmonate() + "," + t2.getAngebotsmonate() + "," + t3.getAngebotsmonate());

        System.out.println();

        //check for c
        Tagesgeld t0 = new Tagesgeld(5000, 9, 3.5, 1.5);
        t1 = new Tagesgeld(50000, 6, 3.0, 2.0);
        t2 = new Tagesgeld(150000, 5, 4.5, 1.75);
        t3 = new Tagesgeld(60000, 8, 3.5, 1.25);

        System.out.println(10080.148839674815 + " (expected)");
        System.out.println(verzinseParallel(10000, 3, t0, t1, t2, t3));
        System.out.println(10160.572744118412 + " (expected)");
        System.out.println(verzinseParallel(10000, 6, t0, t1, t2, t3));
        System.out.println(10272.751782132696 + " (expected)");
        System.out.println(verzinseParallel(10000, 12, t0, t1, t2, t3));

        System.out.println();

        System.out.println(100910.9285190934 + " (expected)");
        System.out.println(verzinseParallel(100000, 3, t0, t1, t2, t3));
        System.out.println(101725.24878187178 + " (expected)");
        System.out.println(verzinseParallel(100000, 6, t0, t1, t2, t3));
        System.out.println(102682.28545476876 + " (expected)");
        System.out.println(verzinseParallel(100000, 12, t0, t1, t2, t3));

        System.out.println();

        System.out.println(252032.2085361965 + " (expected)");
        System.out.println(verzinseParallel(250000, 3, t0, t1, t2, t3));
        System.out.println(253841.61267414704 + " (expected)");
        System.out.println(verzinseParallel(250000, 6, t0, t1, t2, t3));
        System.out.println(255859.91900185865 + " (expected)");
        System.out.println(verzinseParallel(250000, 12, t0, t1, t2, t3));

        System.out.println();

        System.out.println(1002032.2085361965 + " (expected)");
        System.out.println(verzinseParallel(1000000, 3, t0, t1, t2, t3));
        System.out.println(1003841.6126741471 + " (expected)");
        System.out.println(verzinseParallel(1000000, 6, t0, t1, t2, t3));
        System.out.println(1005859.9190018587 + " (expected)");
        System.out.println(verzinseParallel(1000000, 12, t0, t1, t2, t3));

    }
    
}
