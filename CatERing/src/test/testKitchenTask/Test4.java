package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.SummarySheet;
import businesslogic.kitchenTask.UseCaseLogicException;
import businesslogic.turn.ShiftBoard;

public class Test4 {
    public static void main(String[] args) {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        //TODO
        //pre condizione non utile (forse da togliere anche dai contratti)
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);
        try {
            ShiftBoard sb = CatERing.getInstance().getKitchenTaskMgr().getShiftBoard();
            System.out.println(sb);
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        }
    }
}
