package de.jakob.codescape;

public class SoNahUndDochSoFern extends DogBot{

    public void run() {

        turnLeftX(1); moveX(1);
        lastRoom ++;

        while(lastRoom != 6){
            decide();
        }

        turnLeftX(3); moveX(1); pickUp(); turnLeftX(1);

        passcode = output();

        turnLeftX(1); moveX(2); turnLeftX(3);

        goBack();

        turnLeftX(3); moveX(1); turnLeftX(1);

        write(passcode);

        turnLeftX(2); moveX(1);

    }

    private String passcode = "";

    private int lastRoom = 0;

    private String[] decision = {"L", "R", "R", "R", "R", "R"};

    private void goBack(){

        for(int i = 5; i > 1; i--){

            goX(decision[i-1].equals("R") ? "L" : "R");

        }
    }

    private void decide(){

        int currentRoom = Integer.valueOf(output());

        if(currentRoom == 6){
            lastRoom ++;
            return;
        }

        if(currentRoom > lastRoom){
            goX(decision[currentRoom - 1]);
            lastRoom ++;
        } else {
            //if the last room had wrong decision
            decision[lastRoom - 1] = "L";
            goX(decision[currentRoom - 1]);

        }
    }

    private String output(){
        return read();
    }

    private void moveX(int steps){
        for(int i = 0; i< steps; i++){
            move();
        }
    }

    private void turnLeftX(int turns){
        if(turns == 3){
            turnRight();
        } else {

            for(int i = 0; i< turns; i++){
                turnLeft();
            }
        }
    }

    private void goX(String direction){
        switch (direction) {
            case "R" -> {
                turnLeftX(3);
                moveX(1);
                turnLeftX(1);
            }

            default -> {
                turnLeftX(1);
                moveX(1);
                turnLeftX(3);
            }
        }
    }
}
