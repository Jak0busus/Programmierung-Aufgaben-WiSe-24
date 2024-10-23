package de.jakob.tasks;

import de.jakob.SimpleIO;

import java.util.Scanner;

public class EndDateCalculator {

    /**
     * Implementieren Sie ein Programm, welches zu einem gegebenen Startdatum und einer gegebenen Anzahl t an
     * Tagen ein Enddatum berechnet, sodass das Enddatum genau t Tage nach dem Startdatum liegt. Beispielsweise
     * liegt der 30.10.2024 genau einen Tag nach dem 29.10.2024. Die Berechnung soll mithilfe einer einzigen
     * geeigneten Schleife durchgeführt werden. Das Programm soll zudem weder break noch continue benutzen.
     * Verwenden Sie erneut die Klasse SimpleIO zum Einlesen und Ausgeben von Werten.
     * Das Programm fragt zunächst nach dem Startdatum. Hierzu wird der*die Benutzer*in nacheinander
     * aufgefordert, den entsprechenden Tag des Monats, die Nummer des Monats und das Jahr einzugeben. Anschließend
     * fragt das Programm nach der Anzahl t an Tagen. Alle Werte sollen als int eingelesen werden. In allen
     * Berechnungen kann die Existenz von Schaltjahren vernachlässigt werden. Falls das eingegebene Startdatum nicht
     * existiert oder t keine positive Zahl ist, so darf sich das Programm beliebig verhalten.
     */

    private final Scanner scanner = new Scanner(System.in);

    public void prompt() {
        String prompt = "Bitte geben Sie die %s des Startdatums ein:";

        int day = readInt(String.format(prompt, "Tageskomponente")
                , 31);

        int month = readInt(String.format(prompt, "Monatskomponente")
                , 12);

        int year = readInt(String.format(prompt, "Jahreskomponente")
                , -1);

        int t = readInt("Bitte geben Sie die Anzahl an Tagen ein:"
                , -1);

        if (isInvalidDate(day, month, year, t)) {
            SimpleIO.output("Dieses Datum kann nicht existieren. Starte das Program erneut!");
            return;
        }

        String date = calculate(day, month, year, t);

        SimpleIO.output("Das Datum "
                + date
                + " befindet sich "
                + t
                + " Tage nach dem Startdatum.");

    }

    private String calculate(int d, int m, int y, int t) {

        int years = y + t / 365;
        t = t % 365;

        int months = m;
        int days = d;

        for (int i = 0; i < t; i++) {

            if (nextMonth(days, months)) {

                months++;
                days = 1;

                if (months >= 13) {
                    years++;
                    months = 1;
                }

            } else {
                days++;
            }
        }

        return days + "." + months + "." + years;
    }

    private boolean nextMonth(int day, int month) {
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return day >= 31;
            case 4, 6, 9, 11:
                return day >= 30;
            case 2:
                return day >= 29;
            default:
                return true;
        }
    }

    private boolean isInvalidDate(int day, int month, int year, int t) {
        return nextMonth(day - 1, month)
                || day == -1
                || month == -1
                || year == -1
                || t == -1;
    }

    private int readInt(String prompt, int limit) {
        int number = -1;

        int i = SimpleIO.getInt(prompt);
        if (i > 0
                && i < Integer.MAX_VALUE
                && (limit == -1 || i <= limit)) {

            number = i;
        }
        return number;
    }
}
