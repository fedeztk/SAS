package test.testKitchenTask;
import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.user.User;

public class Test5b {
    //disassegna compito
    public static void main(String[] args) {
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        System.out.println(u);
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
