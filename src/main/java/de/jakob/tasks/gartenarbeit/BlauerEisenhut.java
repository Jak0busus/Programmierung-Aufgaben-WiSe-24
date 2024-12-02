package de.jakob.tasks.gartenarbeit;

public final class BlauerEisenhut extends Pflanze {

    /**
     * Constructs a new {@code BlauerEisenhut} instance with predefined attributes:
     * <ul>
     *     <li>Maximum length: {@code Integer.MAX_VALUE}, representing virtually unlimited growth potential</li>
     *     <li>Growth rate: 1</li>
     *     <li>Initial length: 1</li>
     * </ul>
     */
    public BlauerEisenhut() {
        super(Integer.MAX_VALUE, 1, 1);
    }

    @Override
    public void schneiden(int x) {
        laenge = 1;
    }
}