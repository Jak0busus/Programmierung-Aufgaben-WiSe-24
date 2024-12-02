package de.jakob.tasks.gartenarbeit;

public sealed class Rosengewaechs extends Pflanze permits Lorbeerkirsche, Himbeere {

    private final int verbreitung;

    public Rosengewaechs(int maxLaenge, int wachstum, int verbreitung, int laenge) {
        super(maxLaenge, wachstum, laenge);
        this.verbreitung = verbreitung;
    }

    public int getVerbreitung() {
        return verbreitung;
    }

    @Override
    public void waessern() {
        laenge = Math.min(getLaenge() + getWachstum() * getVerbreitung(), getMaxLaenge());
    }

    @Override
    public void schneiden(int x) {
        laenge = 1;
    }
}