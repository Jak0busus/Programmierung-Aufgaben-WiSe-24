package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Lorbeerkirsche} class represents a specific type of plant in the rose family.
 * It extends {@code Rosengewaechs} and customizes its behavior, particularly in the way it is trimmed.
 */
public final class Lorbeerkirsche extends Rosengewaechs {

    /**
     * Constructs a new {@code Lorbeerkirsche} instance with predefined attributes:
     * <ul>
     *     <li>Maximum length: 20</li>
     *     <li>Growth rate: 2</li>
     *     <li>Spreading factor: 3</li>
     *     <li>Initial length: 1</li>
     * </ul>
     */
    public Lorbeerkirsche() {
        super(20, 2, 3, 1);
    }

    /**
     * Trims the {@code Lorbeerkirsche} by reducing its length by half the specified amount.
     * The length will not fall below 0 as a result of trimming.
     *
     * @param x the amount to trim, which is halved before reducing the plant's length
     */
    @Override
    public void schneiden(int x) {
        setLaenge(Math.max(getLaenge() - x / 2, 0));
    }
}
