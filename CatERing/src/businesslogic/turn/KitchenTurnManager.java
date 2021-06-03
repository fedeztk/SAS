package businesslogic.turn;

public class KitchenTurnManager {
    private ShiftBoard shiftBoard;

    public ShiftBoard getShiftBoard(){
        return shiftBoard.getInstance();
    }
}
