package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;

public class Test2 {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST aggiunta task");

        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            CatERing.getInstance().getKitchenTaskMgr().loadSummarySheet(ss);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();

        System.out.println("Before:\n" + ktm.getCurrentSummarySheet());

        //add task
        try {
            ktm.addTask(Recipe.loadRecipeById(1));
            ktm.addTask(Recipe.loadRecipeById(2));
            ktm.addTask(Recipe.loadRecipeById(3));
            ktm.addTask(Recipe.loadRecipeById(4));
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        System.out.println("\nAfter:\n" + ktm.getCurrentSummarySheet());
    }
}
