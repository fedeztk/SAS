package test.testKitchenTask;


import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchenTask.*;
import businesslogic.recipe.Recipe;
import businesslogic.turn.KitchenTurn;
import businesslogic.turn.ShiftBoard;
import businesslogic.user.User;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

public class TestKitchenTask {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");


        System.out.println("TEST creazione foglio riepilogativo");
        ServiceInfo si = ServiceInfo.loadServiceInfoById(4);
        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();
        try {
            System.out.println(ktm.createSummarySheet(si));
        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
        System.out.println("After 'creazione foglio riepilogativo':" + ktm.getCurrentSummarySheet());


        System.out.println("TEST aggiunta task");
        System.out.println("Before 'aggiunta task':" + ktm.getCurrentSummarySheet());
        //add task
        Task t2 = null, t3 = null;
        try {
            ktm.addTask(Recipe.loadRecipeById(1));
            t2 = ktm.addTask(Recipe.loadRecipeById(2));
            t3 = ktm.addTask(Recipe.loadRecipeById(3));
            ktm.addTask(Recipe.loadRecipeById(4));
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
        System.out.println("After 'aggiunta task':" + ktm.getCurrentSummarySheet());


        System.out.println("TEST ordinamento task del foglio riepilogativo");
        System.out.println("Before 'ordinamento':" + ktm.getCurrentSummarySheet().getTaskList());
        ArrayList<Task> newtl = (ArrayList<Task>) ktm.getCurrentSummarySheet().getTaskList().clone();
        Collections.shuffle(newtl);
        try {
            ktm.sortTasks(newtl);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
        System.out.println("After 'ordinamento':" + ktm.getCurrentSummarySheet().getTaskList());


        System.out.println("TEST restituzione shiftboard");
        try {
            ShiftBoard sb = CatERing.getInstance().getKitchenTaskMgr().getShiftBoard();
            System.out.println(sb);
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        }


        System.out.println("TEST assegnamento task");
        ArrayList<KitchenTurn> akt1 = new ArrayList<>();
        akt1.add(KitchenTurn.loadKitchenTurnById(2));
        akt1.add(KitchenTurn.loadKitchenTurnById(3));
        int q3 = 7;
        long millis = 3600000;
        Time time3 = new Time(millis);
        try {
            System.out.println("Before 'assegnamento':");
            System.out.println(Task.loadTaskById(t2.getId()));
            System.out.println(Task.loadTaskById(t3.getId()));
            ktm.assignTask(t2, akt1, User.loadUser("Marinella"));
            ktm.assignTask(t3, akt1, q3, time3, User.loadUser("Paola"));
            System.out.println("After 'assegnamento':");
            System.out.println(Task.loadTaskById(t2.getId()));
            System.out.println(Task.loadTaskById(t3.getId()));
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }


        System.out.println("TEST modifica saturazione task");
        KitchenTurn t1 = KitchenTurn.loadKitchenTurnById(1);
        try {
            System.out.println("Before 'modifica saturazione':");
            System.out.println(t1);
            ktm.setSaturation(t1, true);
            System.out.println("After 'modifica staurazione':");
            System.out.println(t1);
        } catch (UseCaseLogicException | SummarySheetException e) {
            e.printStackTrace();
        }
    }
}