package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;

import java.util.ArrayList;
import java.util.Collections;

public class Test3 {
    public static void main(String[] args) {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        SummarySheet ss = SummarySheet.loadSummarySheetById(11);
        try {
            CatERing.getInstance().getKitchenTaskMgr().loadSummarySheet(ss);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        ArrayList<Task> newtl = (ArrayList<Task>) ktm.getCurrentSummarySheet().getTaskList().clone();
        Collections.shuffle(newtl);
        try {
            System.out.println(ktm.sortTasks(newtl));
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}
