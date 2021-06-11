package businesslogic.kitchenTask;

import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;

import java.util.ArrayList;


//abbastanza fatto tutto
public class SummarySheet {
    private ServiceInfo serviceUsed;
    private User owner;
    private ArrayList<Task> taskList;

    public SummarySheet(ServiceInfo s, User u){
        Menu m = s.getMenu();

        serviceUsed = s;
        owner = u;
        taskList = new ArrayList<>();
        ArrayList<MenuItem> recipes = m.getAllRecipes();
        for(MenuItem r : recipes){
            Task t = new Task(r.getItemRecipe());
            taskList.add(t);
        }
    }

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl){
        this.taskList=newtl;
        return this.taskList;
    }

    public boolean isOwner(User u){
        return u==owner;
        // implementare user.equals(u)?
        // return owner.equals(u);
    }

    public boolean contains(Task t){
        return taskList.contains(t);
    }

    public void addTask(Task t){
        taskList.add(t);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int portions, int duration, User cook, Job job){
        t.assignTask(tl,portions,duration,cook);
    }

    public void deleteTask(Task t){
        taskList.remove(t);
    }

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, int duration, User cook, Job job){
        t.modifyTask(tl,portions,duration,cook,job);
    }

    public void disassignTask(Task t){
        t.disassignTask();
    }

    public void taskDone(Task t){
        t.done();
    }

}
