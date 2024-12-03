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
        //super.schneiden(getLaenge() - 1); if laenge was private but then himbeeres schneiden method would not work
        laenge = 1;
    }
}