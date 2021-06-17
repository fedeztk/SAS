package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.KitchenTaskManager;
import businesslogic.kitchenTask.Task;
import businesslogic.recipe.Recipe;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;

import java.sql.Time;
import java.util.ArrayList;


public class Test5 {
        //assignTask()
        public static void main(String[] args) {

            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            User u = CatERing.getInstance().getUserManager().getCurrentUser();

            Task t1 = ;
            Task t2 = new Task(new Recipe("Ragu"));
            Task t3 = new Task(new Recipe("Pizza"));

            KitchenTurn kt1 = new KitchenTurn();
            KitchenTurn kt2 = new KitchenTurn();
            KitchenTurn kt3 = new KitchenTurn();
            KitchenTurn kt4 = new KitchenTurn();
            KitchenTurn kt5 = new KitchenTurn();

            ArrayList<KitchenTurn> akt1 = new ArrayList<>();
            ArrayList<KitchenTurn> akt2 = null;
            ArrayList<KitchenTurn> akt3 = null;

            int q3 = 7;
            long millis = 3600000;
            Time time3 = new Time(millis);
            try {
                KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
                ktm.assignTask(t1, akt1);
                ktm.assignTask(t2, akt2, User.loadUser("Marinella"));
                ktm.assignTask(t3, akt3, q3, time3, User.loadUser("Paola"));
            }catch (Exception e ){

            }
        }
    }


