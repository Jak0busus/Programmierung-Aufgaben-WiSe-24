package de.jakob.tasks;

public class Tagesgeld {
    private int maxBetrag;
    private int angebotsmonate;
    private double angebotszinsen;
    private double normalzinsen;

    public Tagesgeld(int maxBetrag, int angebotsmonate,
                     double angebotszinsen, double normalzinsen) {
        this.maxBetrag = maxBetrag;
        this.angebotsmonate = angebotsmonate;
        this.angebotszinsen = angebotszinsen;
        this.normalzinsen = normalzinsen;
    }

    public static void main(String[] args) {
        System.out.println(jahresZuMonatszins(4.5));
    }

    private static double jahresZuMonatszins(double jahreszins) {
        return (Math.pow(1 + (jahreszins / 100), 1.0 / 12) - 1) * 100;
    }

    private double monatsverzinsung(double init, boolean angebot) {
        return (angebot) ? init + init * (jahresZuMonatszins(angebotszinsen) / 100.0) : init + init * (jahresZuMonatszins(normalzinsen) / 100.0);
    }

    private double verzinse(double init, int monate) {
//        if(monate == 0) return init;
//        monate--;
//
//        int maxBetrag = Math.min(getMaxBetrag(), 100000);
//
//        if(init >= maxBetrag) return verzinse(monatsverzinsung(maxBetrag, false), monate);
//
//        return verzinse(monatsverzinsung(init, false), monate);

        double max = Math.min(100000, maxBetrag);
        max = Math.min(init, max);
        if (angebotsmonate > 0) {
            angebotsmonate--;
            return jahresZuMonatszins(angebotszinsen) * verzinse(max, monate - 1);
        }
        return jahresZuMonatszins(normalzinsen) * verzinse(max, monate - 1);

    }

    public Tagesgeld bestesTagesgeld(double init, int monate, Tagesgeld... ts) {
        if (ts == null) return null;
        return ts[hilfsmethode(init, monate, 0, ts)];
    }

    private int hilfsmethode(double init, int monate, int pos, Tagesgeld... ts) {
        if (pos == ts.length - 1) return pos;

        int bestes = hilfsmethode(init, monate, pos + 1, ts);
        if (ts[bestes].verzinse(init, monate) > ts[pos].verzinse(init, monate)) return bestes;
        return pos;
    }

    private void setMaxBetrag(int maxBetrag) {
        this.maxBetrag = Math.max(maxBetrag, 0);
    }

    private void setAngebotsmonate(int angebotsmonate) {
        this.angebotsmonate = Math.max(angebotsmonate, 0);
    }

    private void setAngebotszinsen(double angebotszinsen) {
        this.angebotszinsen = angebotszinsen;
    }

    private void setNormalzinsen(double normalzinsen) {
        this.normalzinsen = normalzinsen;
    }

    public int getMaxBetrag() {
        return maxBetrag;
    }

    public int getAngebotsmonate() {
        return angebotsmonate;
    }

    public double getAngebotszinsen() {
        return angebotszinsen;
    }

    public double getNormalzinsen() {
        return normalzinsen;
    }
}