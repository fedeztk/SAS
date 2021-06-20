package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.turn.KitchenTurn;
import businesslogic.UseCaseLogicException;

public class Test6 {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST modifica saturazione task");

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            ktm.loadSummarySheet(ss);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        KitchenTurn t1 = KitchenTurn.loadKitchenTurnById(1);

        try {
            System.out.println(t1);
            ktm.setSaturation(t1, true);
            System.out.println(t1);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}