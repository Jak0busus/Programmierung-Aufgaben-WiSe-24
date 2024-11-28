package de.jakob.tasks.gartenarbeit;

/**
 * The {@code Feld} class contains logic for selecting and manipulating plants based on pairs of plants.
 * It demonstrates polymorphic behavior and pattern matching with the {@code PflanzenPaar} class.
 */
public class Feld {

    /**
     * Determines which plant to select from a given pair based on specific criteria.
     *
     * <ul>
     *     <li>If the pair contains a {@code Rosengewaechs}, the first such plant is chosen, and its length is set to its maximum.
     *         If the plant is a {@code Himbeere}, it is trimmed after selection.</li>
     *     <li>If the pair contains a {@code Salbei} and a {@code BlauerEisenhut},
     *         the selection depends on the length of the {@code Salbei}: if it is at least 5, the {@code BlauerEisenhut} is chosen; otherwise, the {@code Salbei} is selected.</li>
     *     <li>In all other cases, the first plant in the pair is selected by default.</li>
     * </ul>
     *
     * @param pair a pair of plants ({@code PflanzenPaar}) to evaluate
     * @return the selected plant based on the given criteria
     */
    public static Pflanze auswahl(PflanzenPaar pair) {

        switch (pair) {

            case PflanzenPaar(Rosengewaechs _, _), PflanzenPaar(_, Rosengewaechs _) -> {
                Rosengewaechs rosengewaechs = pair.plantA() instanceof Rosengewaechs
                        ? (Rosengewaechs) pair.plantA()
                        : (Rosengewaechs) pair.plantB();

                rosengewaechs.setLaenge(rosengewaechs.getMaxLaenge());

                if (rosengewaechs instanceof Himbeere himbeere) {
                    himbeere.schneiden(1);
                    return himbeere;
                }
                return rosengewaechs;
            }

            case PflanzenPaar(Salbei _, BlauerEisenhut _), PflanzenPaar(BlauerEisenhut _, Salbei _) -> {
                Salbei salbei = pair.plantA() instanceof Salbei
                        ? (Salbei) pair.plantA()
                        : (Salbei) pair.plantB();
                BlauerEisenhut blauerEisenhut = pair.plantA() instanceof BlauerEisenhut
                        ? (BlauerEisenhut) pair.plantA()
                        : (BlauerEisenhut) pair.plantB();

                return salbei.getLaenge() >= 5 ? blauerEisenhut : salbei;
            }

            default -> {
                return pair.plantA();
            }
        }
    }

    /**
     * Main method to demonstrate the functionality of the {@code auswahl} method with various plant pairs.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Start");
        Himbeere himbeere = new Himbeere();
        Lorbeerkirsche lorbeerkirsche = new Lorbeerkirsche();
        Salbei salbeiLang = new Salbei();
        lorbeerkirsche.waessern();
        lorbeerkirsche.schneiden(6);
        salbeiLang.waessern();
        salbeiLang.waessern();
        salbeiLang.waessern();
        salbeiLang.waessern();
        Salbei salbei = new Salbei();
        BlauerEisenhut blauerEisenhut = new BlauerEisenhut();
        blauerEisenhut.schneiden(1);
        PflanzenPaar pair1 = new PflanzenPaar(himbeere, salbei);
        PflanzenPaar pair2 = new PflanzenPaar(salbei, lorbeerkirsche);
        PflanzenPaar pair3 = new PflanzenPaar(lorbeerkirsche, blauerEisenhut);
        PflanzenPaar pair4 = new PflanzenPaar(salbeiLang, blauerEisenhut);
        PflanzenPaar pair5 = new PflanzenPaar(salbei, blauerEisenhut);

        System.out.println("Auswahl:" + auswahl(pair1) + ", Laenge:" + auswahl(pair1).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair2) + ", Laenge:" + auswahl(pair2).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair3) + ", Laenge:" + auswahl(pair3).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair4) + ", Laenge:" + auswahl(pair4).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair5) + ", Laenge:" + auswahl(pair5).getLaenge());
    }
}