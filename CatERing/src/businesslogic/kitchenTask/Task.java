package businesslogic.kitchenTask;

import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.Cook;
import businesslogic.user.User;

import java.sql.Time;
import java.util.ArrayList;

public class Task {
    private int quantity;
    private Time time; // time string or calendar??
    private boolean done;
    private User cook;
    private Job consistingJob;
    private ArrayList<KitchenTurn> turnList;

    public Task(Job j){
        this.consistingJob=j;
        turnList = new ArrayList<>();
    }

    public void assignTask(ArrayList<KitchenTurn> tl, int portions, Time duration, User cook){
        if(quantity!=-1)quantity=portions;
        if(duration!=null)time=duration;
        if(cook!=null && ((Cook)cook.useBehaviour(User.Role.CUOCO)).isAvailable(turnList)){
            this.cook=cook;
        }
        if(tl!=null){
            turnList=tl;
        }
    }

    public void disassignTask(){
        quantity=-1;
        time = null;
        done=false;
        cook = null;
        turnList = new ArrayList<>();
    }
    public void modifyTask(ArrayList<KitchenTurn> tl, int portions, Time duration, User cook, Job job){
        if(portions!=-1)
        if(tl!=null){
            turnList = tl;
        }
        if(duration!=null){
            //this.time=duration;
        }
        if(cook!=null && ((Cook)cook.useBehaviour(User.Role.CUOCO)).isAvailable(turnList)){
            this.cook=cook;
        }
        if(job!=null){
            this.consistingJob=job;
        }
    }

    public void done(){
        this.done=true;
    }
}
