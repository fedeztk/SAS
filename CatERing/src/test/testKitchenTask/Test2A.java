package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;

public class Test2A {
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
        Task first = ktm.addTask(Recipe.loadRecipeById(1));
        ktm.addTask(Recipe.loadRecipeById(2));
        ktm.addTask(Recipe.loadRecipeById(3));
        System.out.println(ktm.getCurrentSummarySheet());
        try {
            ktm.deleteTask(first);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}
