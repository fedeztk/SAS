package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;
import businesslogic.UseCaseLogicException;

import java.sql.Time;
import java.util.ArrayList;

public class Test5a {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST modifica task");

        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        SummarySheet ss = SummarySheet.loadSummarySheetById(11);

        try {
            ktm.loadSummarySheet(ss);
            System.out.println(ktm.getCurrentSummarySheet());
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }

        Task t1 = Task.loadTaskById(11);
        Task t2 = Task.loadTaskById(12);
        Task t3 = Task.loadTaskById(13);

        ArrayList<KitchenTurn> akt1 = new ArrayList<>();
        akt1.add(KitchenTurn.loadKitchenTurnById(1));
        akt1.add(KitchenTurn.loadKitchenTurnById(2));
        akt1.add(KitchenTurn.loadKitchenTurnById(3));

        int portions2 = 27;
        int portions3 = 14;
        User cook2 = User.loadUserById(6);
        User cook3 = User.loadUserById(7);
        Recipe job3 = Recipe.loadRecipeById(12);

        long millis = 3000000;
        Time time3 = new Time(millis);

        try {
            System.out.println("Before:");
            System.out.println(t1);
            System.out.println(t2);
            System.out.println(t3);
            ktm.modifyTask(t1, akt1); //modifica solo i turni
            ktm.modifyTask(t2, portions2, cook2); //modifica porzioni e cuoco
            ktm.modifyTask(t3, akt1, portions3, time3, cook3, job3);//modifica tutto
            System.out.println("After:");
            System.out.println(t1);
            System.out.println(t2);
            System.out.println(t3);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}
