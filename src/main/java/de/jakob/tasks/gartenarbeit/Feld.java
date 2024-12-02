package de.jakob.tasks.gartenarbeit;


public class Feld {

    public static Pflanze auswahl(PflanzenPaar pair) {

        switch (pair) {

            case PflanzenPaar(Rosengewaechs _, _), PflanzenPaar(_, Rosengewaechs _) -> {
                Rosengewaechs rosengewaechs = pair.plantA() instanceof Rosengewaechs
                        ? (Rosengewaechs) pair.plantA()
                        : (Rosengewaechs) pair.plantB();

                rosengewaechs.laenge = rosengewaechs.getMaxLaenge();

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