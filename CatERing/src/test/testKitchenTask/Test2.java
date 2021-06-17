package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;

public class Test2 {

    //addTask()
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
        ktm.addTask(Recipe.loadRecipeById(1));
        ktm.addTask(Recipe.loadRecipeById(2));
        ktm.addTask(Recipe.loadRecipeById(3));
    }
}
