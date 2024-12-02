package de.jakob.tasks.gartenarbeit;

public final class Himbeere extends Rosengewaechs {

    /**
     * Constructs a new {@code Himbeere} instance with predefined attributes:
     * <ul>
     *     <li>Maximum length: 10</li>
     *     <li>Growth rate: 1</li>
     *     <li>Spreading factor: 2</li>
     *     <li>Initial length: 1</li>
     * </ul>
     */
    public Himbeere() {
        super(10, 1, 2, 1);
    }

    @Override
    public void schneiden(int x) {
        laenge = Math.max(getLaenge() - x, 0);
    }
}