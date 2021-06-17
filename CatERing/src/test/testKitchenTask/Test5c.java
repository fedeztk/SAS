package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.user.User;

public class Test5c {
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

        Task t1 = Task.loadTaskById(11);
        try {
            System.out.println(t1);
            ktm.taskDone(t1);
            System.out.println(t1);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

    }
}
