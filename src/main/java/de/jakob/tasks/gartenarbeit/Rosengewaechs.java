package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Rosengewaechs} class represents a type of plant in the rose family.
 * It extends the {@code Pflanze} class and introduces an additional characteristic,
 * {@code verbreitung}, which affects its growth behavior.
 * This class is sealed and permits specific subclasses.
 */
public sealed class Rosengewaechs extends Pflanze permits Lorbeerkirsche, Himbeere {

    private final int verbreitung;

    /**
     * Constructs a new {@code Rosengewaechs} instance with the specified parameters.
     *
     * @param maxLaenge the maximum length the plant can grow to
     * @param wachstum the base growth rate of the plant
     * @param verbreitung the spreading factor affecting growth
     * @param laenge the initial length of the plant
     */
    public Rosengewaechs(int maxLaenge, int wachstum, int verbreitung, int laenge) {
        super(maxLaenge, wachstum, laenge);
        this.verbreitung = verbreitung;
    }

    /**
     * Returns the spreading factor of the plant.
     *
     * @return the spreading factor of the plant
     */
    public int getVerbreitung() {
        return verbreitung;
    }

    /**
     * Waters the plant, causing it to grow by a rate affected by its spreading factor.
     * The plant will grow by {@code wachstum * verbreitung}, but will not exceed
     * its maximum length.
     */
    @Override
    public void waessern() {
        setLaenge(Math.min(getLaenge() + getWachstum() * getVerbreitung(), getMaxLaenge()));
    }

    /**
     * Trims the plant, reducing its length to the minimum value of 1.
     *
     * @param x the amount to trim the plant by (ignored in this implementation)
     */
    @Override
    public void schneiden(int x) {
        setLaenge(1);
    }
}