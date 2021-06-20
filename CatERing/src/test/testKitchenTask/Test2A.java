package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;
import businesslogic.UseCaseLogicException;

public class Test2A {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST rimozione task");

        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            CatERing.getInstance().getKitchenTaskMgr().loadSummarySheet(ss);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        Task first = null;

        try {
            first = ktm.addTask(Recipe.loadRecipeById(1));
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        System.out.println("Before:\n" + ktm.getCurrentSummarySheet());
        try {
            //delete "new" task
            System.out.println("Task to delete: "+first);
            ktm.deleteTask(first);
            //delete task already present in db
            ktm.deleteTask(Task.loadTaskById(14));
            System.out.println("After:\n" + ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}