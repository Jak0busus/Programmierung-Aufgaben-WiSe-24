package de.jakob.tasks.plants;

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
        setLaenge(getLaenge() < getMaxLaenge() ?
                getLaenge() + getWachstum() * getVerbreitung() :
                getLaenge());
    }

    @Override
    public void schneiden(int x) {
        setLaenge(1);
    }
}
