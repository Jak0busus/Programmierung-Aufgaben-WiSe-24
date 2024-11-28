package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Salbei} class represents a specific type of plant.
 * It is characterized by a modest maximum length and a consistent growth rate.
 * This class extends {@code Pflanze} without adding new behaviors.
 */
public final class Salbei extends Pflanze {

    /**
     * Constructs a new {@code Salbei} instance with predefined attributes:
     * <ul>
     *     <li>Maximum length: 6</li>
     *     <li>Growth rate: 1</li>
     *     <li>Initial length: 1</li>
     * </ul>
     */
    public Salbei() {
        super(6, 1, 1);
    }
}