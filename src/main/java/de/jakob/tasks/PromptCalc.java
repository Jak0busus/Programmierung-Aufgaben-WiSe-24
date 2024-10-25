package de.jakob.tasks;

import de.jakob.util.SimpleIO;

public class PromptCalc {

    public void prompt(){

        int i = SimpleIO.getInt("Bitte geben Sie eine Zahl ein:");

        String operator = "";

        while(!operator.equals("STOP")){

            operator = SimpleIO.getString("Bitte geben Sie eine Rechenoperation (ADD oder SUB) oder STOP ein:");

            switch(operator){

                case "ADD" -> {

                    i = i + SimpleIO.getInt("Bitte geben Sie eine Zahl ein:");
                    SimpleIO.output("Aktuelles Ergebnis: " + i);

                }

                case "SUB" -> {

                    i = i - SimpleIO.getInt("Bitte geben Sie eine Zahl ein:");
                    SimpleIO.output("Aktuelles Ergebnis: " + i);
                }

                default -> {

                    SimpleIO.output("Fehlerhafte Eingabe!");
                    return;

                }

            }

        }

        SimpleIO.output("Endergebnis: " + i);

    }

}
