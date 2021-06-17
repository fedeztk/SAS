package businesslogic.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.kitchenTask.Task;
import businesslogic.recipe.Recipe;

public class Test2 {
    //addTask()
    public static void main(String[] args) {

        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        Task t = new Task(new Recipe("Lasagna"));

    }
}
