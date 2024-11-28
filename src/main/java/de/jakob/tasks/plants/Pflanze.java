package de.jakob.tasks.plants;

public sealed class Pflanze permits BlauerEisenhut, Salbei, Rosengewaechs {

    private final int maxLaenge;
    private final int wachstum;
    private int laenge;

    public Pflanze(int maxLaenge, int wachstum, int laenge) {
        this.maxLaenge = maxLaenge;
        this.wachstum = wachstum;
        this.laenge = laenge > maxLaenge ? 0 : laenge;
    }

    public int getMaxLaenge() {
        return maxLaenge;
    }

    public int getWachstum() {
        return wachstum;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public void waessern() {
        setLaenge(getLaenge() < getMaxLaenge() ?
                getLaenge() + getWachstum() :
                getLaenge());
    }

    public void schneiden(int x){
        setLaenge(Math.max(getLaenge() - x, 1));
    }
}
