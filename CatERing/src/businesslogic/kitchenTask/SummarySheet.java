package businesslogic.kitchenTask;

import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Job;
import businesslogic.turn.Turn;
import businesslogic.user.User;

import java.util.ArrayList;


//abbastanza fatto tutto
public class SummarySheet {
    private ServiceInfo serviceUsed;
    private User owner;
    private ArrayList<Task> taskList;

    private SummarySheet(ServiceInfo s, User u){
        serviceUsed = s;
        owner = u;
    }


    public static SummarySheet create(ServiceInfo s, User u){ //le create devono essere static?
        return new SummarySheet(s,u);
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

    public void assignTask(Task t, ArrayList<Turn> tl, int portions, int duration, User cook, Job job){
        t.assignTask(tl,portions,duration,cook);
    }

    public void deleteTask(Task t){
        taskList.remove(t);
    }

    public void modifyTask(Task t, ArrayList<Turn> tl, int portions, int duration, User cook, Job job){
        t.modifyTask(tl,portions,duration,cook,job);
    }

    public void disassignTask(Task t){
        t.disassignTask();
    }

    public void taskDone(Task t){
        t.done();
    }

}
