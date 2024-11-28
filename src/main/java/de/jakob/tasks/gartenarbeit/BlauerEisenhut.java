package de.jakob.tasks.gartenarbeit;

/**
 * The {@code BlauerEisenhut} class represents a specific type of plant.
 * It is known for its extremely high potential maximum length and minimal growth rate.
 * This class extends {@code Pflanze} without introducing additional behavior.
 */
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
}