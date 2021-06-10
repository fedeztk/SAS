package businesslogic.kitchenTask;

import businesslogic.recipe.Job;
import businesslogic.turn.Turn;
import businesslogic.user.User;

import java.util.ArrayList;

public class Task {
    private int quantity;
    private String time; // time string or calendar??
    private boolean done;
    private User cook;

    private Task(Job j){}

    public Task create(Job j){
        return new Task(j);
    }

    public void assignTask(ArrayList<Turn> tl, int portions, int duration, User cook){
        quantity=portions;
        //time=duration;
        this.cook=cook;
        // TL????
    }

    public void disassignTask(){
        quantity=-1;
        time = null;
        done=false;
        cook = null;
    }
    public void modifyTask(ArrayList<Turn> tl, int portions, int duration, User cook, Job job){

    }

    public void done(){
        this.done=true;
    }
}
