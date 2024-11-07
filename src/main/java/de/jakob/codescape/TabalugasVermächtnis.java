package de.jakob.codescape;

public class TabalugasVermächtnis {

    public void run() {

        moveX(1); turnLeftX(1);

        String code = read();

        turnLeftX(2);

        for(String move : code.split(",")){

            switch (move) {

                case "L" -> {turnLeftX(1);}
                case "R" -> {turnLeftX(3);}
                default -> {moveX(1);}
            }
        }

        moveX(1); turnLeftX(1); moveX(1);

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

    private void move(){}
    private void turnLeft(){}
    private void turnRight(){}
    private void write(String s){}
    private String read(){return null;}
    private void pickUp(){}

}
