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

            Task t1 = Task.loadTaskById(11);
            Task t2 = Task.loadTaskById(12);
            Task t3 = Task.loadTaskById(13);



            ArrayList<KitchenTurn> akt1 =  new ArrayList<>();
//            akt1.add(KitchenTurn.loadKitchenTurnById());
//            akt1.add(KitchenTurn.loadKitchenTurnById());
//            akt1.add(KitchenTurn.loadKitchenTurnById());


            int q3 = 7;
            long millis = 3600000;
            Time time3 = new Time(millis);
            try {
                KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
                ktm.assignTask(t1, akt1);
                ktm.assignTask(t2, akt1, User.loadUser("Marinella"));
                ktm.assignTask(t3, akt1, q3, time3, User.loadUser("Paola"));
            }catch (Exception e ){

            }
        }
    }


