package de.jakob.tasks.plants;

public final class Himbeere extends Rosengewaechs {

    public Himbeere() {
        super(10, 1, 2, 1);
    }

    @Override
    public void schneiden(int x) {
        setLaenge(Math.max(getLaenge() - x, 0));
    }
}
