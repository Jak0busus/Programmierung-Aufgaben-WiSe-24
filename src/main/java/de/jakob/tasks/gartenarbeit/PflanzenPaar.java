package de.jakob.tasks.gartenarbeit;

/**
 * The {@code PflanzenPaar} record represents a pair of plants.
 * It encapsulates two {@code Pflanze} instances, referred to as {@code plantA} and {@code plantB}.
 * Records are immutable data carriers in Java, making this class a simple and efficient way to store plant pairs.
 *
 * @param plantA the first plant in the pair
 * @param plantB the second plant in the pair
 */
public record PflanzenPaar(Pflanze plantA, Pflanze plantB) { }