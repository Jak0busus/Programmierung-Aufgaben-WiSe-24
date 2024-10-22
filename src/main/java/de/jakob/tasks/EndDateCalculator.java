package de.jakob.tasks;

import java.util.Scanner;

public class EndDateCalculator {

    private final static Scanner scanner = new Scanner(System.in);
    private final static String PROMPT = "Bitte geben Sie die x des Startdatums ein:";

    public void prompt() {

        int day = readInt(PROMPT.replace("x", "Tageskomponente")
                , 31);

        int month = readInt(PROMPT.replace("x", "Monatskomponente")
                , 12);

        int year = readInt(PROMPT.replace("x", "Jahreskomponente")
                , 0);
        int t = readInt("Bitte geben Sie die Anzahl an Tagen ein:"
                , 0);

        if (nextMonth(day - 1, month)
                || day == 0
                || month == 0
                || year == 0
                || t == 0) {
            System.out.println("Dieses Datum kann nicht existieren. Starte das Program erneut!");
            return;
        }

        System.out.println("Das Datum "
                + calculate(day, month, year, t)
                + " befindet sich "
                + t
                + " Tage nach dem Startdatum .\n");

        prompt();
    }

    private String calculate(int d, int m, int y, int t) {

        int years = y + t / 365;

        t = t % 365;

        int months = m;

        int days = d;

        for (int i = 0; i < t; i++) {

            if (nextYear(days, months)) {

                years++;
                months = 1;
                days = 1;

            } else if (nextMonth(days, months)) {

                months++;
                days = 1;

            } else {
                days++;
            }
        }

        return days + "." + months + "." + years;

    }

    private boolean nextYear(int day, int month) {

        return month == 12 && day >= 31;

    }

    private boolean nextMonth(int day, int month) {

        switch (month) {

            case 1, 3, 5, 7, 8, 10: return day >= 31;

            case 4, 6, 9, 11: return day >= 30;

            case 2: return day >= 29;

            default: return false;
        }

    }

    private int readInt(String prompt, int limit) {

        int number = 0;

        System.out.print(prompt + "\n");

        if (scanner.hasNextInt()) {

            int i = scanner.nextInt();
            if (i > 0 &&
                    (limit == 0 || i <= limit)) {

                number = i;

            } else {
                System.out.println("Das ist keine valide Zahl!");

            }

        } else {
            scanner.next();
            System.out.println("Das ist keine Zahl!");
        }

        return number;
    }
}
