package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Pflanze} class represents a plant with properties such as maximum length,
 * growth rate, and current length. This is a sealed class permitting specific subclasses.
 * It provides methods for watering and trimming the plant.
 */
public sealed class Pflanze permits BlauerEisenhut, Salbei, Rosengewaechs {

    private final int maxLaenge;

    private final int wachstum;

    private int laenge;

    /**
     * Constructs a new {@code Pflanze} instance with the specified maximum length,
     * growth rate, and initial length. If the initial length exceeds the maximum
     * length, it is set to 0.
     *
     * @param maxLaenge the maximum length the plant can grow to
     * @param wachstum the growth rate of the plant
     * @param laenge the initial length of the plant
     */
    public Pflanze(int maxLaenge, int wachstum, int laenge) {
        this.maxLaenge = maxLaenge;
        this.wachstum = wachstum;
        this.laenge = laenge > maxLaenge ? 0 : laenge;
    }

    /**
     * Returns the maximum length the plant can grow to.
     *
     * @return the maximum length of the plant
     */
    public int getMaxLaenge() {
        return maxLaenge;
    }

    /**
     * Returns the growth rate of the plant.
     *
     * @return the growth rate of the plant
     */
    public int getWachstum() {
        return wachstum;
    }

    /**
     * Returns the current length of the plant.
     *
     * @return the current length of the plant
     */
    public int getLaenge() {
        return laenge;
    }

    /**
     * Sets the current length of the plant.
     *
     * @param laenge the new length of the plant
     */
    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    /**
     * Waters the plant, causing it to grow by the specified growth rate,
     * up to the maximum length. If the plant is already at or beyond its
     * maximum length, it does not grow further.
     */
    public void waessern() {
        setLaenge(Math.min(getLaenge() + getWachstum(), getMaxLaenge()));
    }

    /**
     * Trims the plant by reducing its length by a specified amount.
     * The plant's length will not fall below 1.
     *
     * @param x the amount to reduce the plant's length by
     */
    public void schneiden(int x) {
        setLaenge(Math.max(getLaenge() - x, 1));
    }
}