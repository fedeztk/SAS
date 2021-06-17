package businesslogic.turn;

import businesslogic.CatERing;

import java.util.ArrayList;

public class ShiftBoard {
    private static ShiftBoard singleInstance;
    private ArrayList<KitchenTurn> ktList;

    public static ShiftBoard getInstance() {
        if (singleInstance == null) {
            singleInstance = ShiftBoard.loadShiftBoard();
        }
        return singleInstance;
    }

//    private ShiftBoard(){}

    public ArrayList<KitchenTurn> getTurn(){
        return ktList;
    }

    public static ShiftBoard loadShiftBoard(){
        //TODO inizializzare ktList, new di oggetto shiftboard
        return null;
    }

}