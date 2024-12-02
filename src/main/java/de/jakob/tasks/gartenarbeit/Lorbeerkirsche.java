package de.jakob.tasks.gartenarbeit;

public final class Lorbeerkirsche extends Rosengewaechs {

    public Lorbeerkirsche() {
        super(20, 2, 3, 1);
    }

    @Override
    public void schneiden(int x) {
        laenge = Math.max(getLaenge() - x / 2, 0);
    }
}
