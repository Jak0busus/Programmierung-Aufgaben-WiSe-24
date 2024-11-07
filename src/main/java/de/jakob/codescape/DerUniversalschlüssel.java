package de.jakob.codescape;

public class DerUniversalschlüssel extends DogBot {

    public void run(String[] keys) {
        // Dein Code hier:

        moveX(3); turnLeftX(1);
        writeX(keys[0]);
        turnLeftX(3); moveX(2); turnLeftX(1);
        writeX(keys[1]);
        turnLeftX(3); moveX(2); turnLeftX(3); moveX(3); turnLeftX(3); moveX(1); turnLeftX(3);
        writeX(keys[2]);
        turnLeftX(1); moveX(2); turnLeftX(3);
        writeX(keys[3]);
        turnLeftX(1); moveX(4);


    }


    private void writeX(String string){
        write(string);
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
