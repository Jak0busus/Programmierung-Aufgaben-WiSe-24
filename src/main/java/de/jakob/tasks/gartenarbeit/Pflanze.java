package de.jakob.tasks.gartenarbeit;

public sealed class Pflanze permits BlauerEisenhut, Salbei, Rosengewaechs {

    private final int maxLaenge;

    private final int wachstum;

    protected int laenge;

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


    public void waessern() {
        laenge = Math.min(getLaenge() + getWachstum(), getMaxLaenge());
    }

    public void schneiden(int x) {
        laenge = Math.max(getLaenge() - x, 0);
    }
}