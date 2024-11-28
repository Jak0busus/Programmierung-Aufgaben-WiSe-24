package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Himbeere} class represents a specific type of plant in the rose family.
 * It extends {@code Rosengewaechs} and customizes its behavior, particularly in the way it is trimmed.
 */
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

    /**
     * Trims the {@code Himbeere} by reducing its length by the specified amount.
     * The length will not fall below 0 as a result of trimming.
     *
     * @param x the amount to trim from the plant's current length
     */
    @Override
    public void schneiden(int x) {
        setLaenge(Math.max(getLaenge() - x, 0));
    }
}