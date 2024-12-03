package de.jakob.tasks.gartenarbeit;

public final class Himbeere extends Rosengewaechs {

    public Himbeere() {
        super(10, 1, 2, 1);
    }

    @Override
    public void schneiden(int x) {
        laenge = Math.max(getLaenge() - x, 0);
    }
}