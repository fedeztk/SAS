package businesslogic.turn;

import businesslogic.CatERing;

public class ShiftBoard {
    private static ShiftBoard singleInstance;

    public static ShiftBoard getInstance() {
        if (singleInstance == null) {
            singleInstance = new ShiftBoard();
        }
        return singleInstance;
    }

    private ShiftBoard(){

    }
}