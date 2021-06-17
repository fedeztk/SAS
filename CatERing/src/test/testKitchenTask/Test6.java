package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;

public class Test6 {
    public static void main(String[] args) {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        System.out.println(u);

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
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