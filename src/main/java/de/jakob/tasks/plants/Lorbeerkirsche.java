package de.jakob.tasks.plants;

public final class Lorbeerkirsche extends Rosengewaechs {

    public Lorbeerkirsche() {
        super(20, 2, 3, 1);
    }

    @Override
    public void schneiden(int x) {
        setLaenge(Math.max(getLaenge() - x / 2, 0));
    }
}
