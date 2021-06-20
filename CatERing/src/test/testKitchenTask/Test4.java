package test.testKitchenTask;

import businesslogic.UseCaseLogicException;
import businesslogic.CatERing;
import businesslogic.turn.ShiftBoard;

public class Test4 {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST restituzione shiftboard");

        try {
            ShiftBoard sb = CatERing.getInstance().getKitchenTaskMgr().getShiftBoard();
            System.out.println(sb);
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        }
    }
}
