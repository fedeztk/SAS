package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchenTask.*;

public class Test5b {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST disassegnamento task");

        Task t1 = Task.loadTaskById(11);

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Before:");
            System.out.println(t1);
            ktm.disassignTask(t1);
            System.out.println("After:");
            System.out.println(t1);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}