package de.jakob.tasks.gartenarbeit;

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

    @Override
    public void schneiden(int x) {
        laenge = Math.max(getLaenge() - x / 2, 0);
    }
}
