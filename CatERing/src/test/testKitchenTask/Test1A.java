package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchenTask.KitchenTaskManager;
import businesslogic.kitchenTask.SummarySheet;
import businesslogic.kitchenTask.SummarySheetException;

public class Test1A {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST caricamento foglio riepilogativo dal database");

        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();

        try {
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}