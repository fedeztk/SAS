package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.UseCaseLogicException;

public class Test5c {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST modifica completamento task");

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        Task t1 = Task.loadTaskById(11);
        try {
            System.out.println("Before: \n" + t1);
            ktm.taskDone(t1);
            System.out.println("After: \n" + t1);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}
