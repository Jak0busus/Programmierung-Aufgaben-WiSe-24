package de.jakob.codescape;

public class AlwaysOneStepDeeper extends DogBot {

    public void run() {
        for (int i = 0; i < 4; i++) {
            moveX(2);

            if (i == 3) {
                turnLeftX(3);
                moveX(7);
                pickUp();
                moveX(1);
            }
            goIn();
        }
    }

    private void goIn() {

        turnLeftX(3);

        int i = 0;
        while (isMovePossible()) {
            i++;
            moveX(1);
        }

        turnLeftX(2);
        moveX(1);
        pickUp();
        moveX(i - 1);
        turnLeftX(3);

    }

    private void moveX(int steps) {
        for (int i = 0; i < steps; i++) {
            move();
        }
    }

    private void turnLeftX(int turns) {
        if (turns == 3) {
            turnRight();
        } else {

            for (int i = 0; i < turns; i++) {
                turnLeft();
            }
        }
    }
}
