package businesslogic.kitchenTask;

import java.util.ArrayList;

public class SummarySheet {
    private ArrayList<Task> taskList;
    public SummarySheet(){}

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl){
        this.taskList=newtl;
        return this.taskList;
    }
}
