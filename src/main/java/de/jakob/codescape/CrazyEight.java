package de.jakob.codescape;

public class CrazyEight extends DogBot{

    public void run(){

        while(true){

            if(!isMovePossible()){

                turnLeftX(1);

                if(!isMovePossible()){
                    turnLeftX(2);
                }
                continue;
            }

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
