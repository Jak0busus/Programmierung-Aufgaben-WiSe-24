package de.jakob.codescape;

public class ErrorCode056b4065c440 {

    public void run() {

        moveX(3); turnLeftX(1); moveX(1);

        String passcode = decode(read());
        System.out.println(passcode);

        turnLeftX(2); moveX(2); turnLeftX(1); moveX(2); turnLeftX(1);

        write(passcode);

        turnLeftX(3); moveX(1); turnLeftX(1); moveX(1); turnLeftX(3); moveX(1);

    }

    private String decode(String code){

        char[] charArray = code.toCharArray();
        char[] decodedChar = new char[charArray.length];

        for(int i = charArray.length; i > 0; i--){
            decodedChar[charArray.length - i] = charArray[i-1];
        }

        return new String(decodedChar);

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
