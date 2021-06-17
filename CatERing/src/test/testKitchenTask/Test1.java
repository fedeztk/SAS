package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchenTask.KitchenTaskManager;
import businesslogic.menu.Menu;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;


public class Test1 {
    public static void main(String[] args) {
        try{

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            Menu m = CatERing.getInstance().getMenuManager().createMenu("Menu da Cancellare");

            Section antipasti = CatERing.getInstance().getMenuManager().defineSection("Antipasti");
            Section primi = CatERing.getInstance().getMenuManager().defineSection("Primi");
            Section secondi = CatERing.getInstance().getMenuManager().defineSection("Secondi");

            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(0), antipasti);
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(1), antipasti);
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(2), antipasti);
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(6), secondi);
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(7), secondi);
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(3));
            CatERing.getInstance().getMenuManager().insertItem(recipes.get(4));

            CatERing.getInstance().getMenuManager().publish();

//            ServiceInfo si = new ServiceInfo("pranzo");
            //si mette un id fasullo, bisognerebbe recuperare il service dal db, quindi con l'id settato
            ServiceInfo si = ServiceInfo.loadServiceInfoById(2);
//            si.setId(10);
//            si.setMenu(m);
            KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();

            System.out.println(ktm.createSummarySheet(si));

        } catch (UseCaseLogicException | businesslogic.kitchenTask.UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
