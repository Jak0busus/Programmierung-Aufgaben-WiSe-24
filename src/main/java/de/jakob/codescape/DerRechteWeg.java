package de.jakob.codescape;

public class DerRechteWeg extends DogBot{

    public void run(int nr) {

        System.out.println(nr);

        moveX(2);
        switch (nr) {

            case 1,2 -> {
                turnLeftX(1); moveX(3); turnLeftX(3); moveX(2);

                if(nr == 1){
                    turnLeftX(1);
                } else {
                    turnLeftX(3);
                }
                moveX(2);
            }

            case 3,4 -> {
                turnLeftX(3); moveX(3); turnLeftX(1); moveX(2);

                if(nr == 3){
                    turnLeftX(1);
                } else {
                    turnLeftX(3);
                }
                moveX(2);
            }

        }

        while(!isMovePossible()){
            turnLeftX(1);
        }

        moveX(1); pickUp(); moveX(1);

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
}
