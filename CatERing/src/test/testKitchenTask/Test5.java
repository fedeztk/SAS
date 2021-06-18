package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;

import java.sql.Time;
import java.util.ArrayList;


public class Test5 {
    //assign
        public static void main(String[] args) {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            User u = CatERing.getInstance().getUserManager().getCurrentUser();
            System.out.println(u);
            Task t1 = Task.loadTaskById(11);
            Task t2 = Task.loadTaskById(12);
            Task t3 = Task.loadTaskById(13);

            ArrayList<KitchenTurn> akt1 =  new ArrayList<>();
            akt1.add(KitchenTurn.loadKitchenTurnById(1));
            akt1.add(KitchenTurn.loadKitchenTurnById(2));
            akt1.add(KitchenTurn.loadKitchenTurnById(3));

            int q3 = 7;
            long millis = 3600000;
            Time time3 = new Time(millis);
            KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
            SummarySheet ss = SummarySheet.loadSummarySheetById(11);

            try {
                ktm.loadSummarySheet(ss);
                System.out.println(ktm.getCurrentSummarySheet());
            } catch (UseCaseLogicException | SummarySheetException e) {
                e.printStackTrace();
            }

            try {
                ktm.assignTask(t1, akt1);
                ktm.assignTask(t2, akt1, User.loadUser("Marinella"));
                ktm.assignTask(t3, akt1, q3, time3, User.loadUser("Paola"));
                System.out.println(t1);
                System.out.println(t2);
                System.out.println(t3);
            } catch (UseCaseLogicException | SummarySheetException e) {
                e.printStackTrace();
            }
        }
    }

