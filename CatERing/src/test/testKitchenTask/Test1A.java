package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.KitchenTaskManager;
import businesslogic.kitchenTask.SummarySheet;
import businesslogic.kitchenTask.SummarySheetException;
import businesslogic.kitchenTask.UseCaseLogicException;

public class Test1A {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);
        System.out.println(ss);
        try {
            KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}
