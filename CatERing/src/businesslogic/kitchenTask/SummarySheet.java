package businesslogic.kitchenTask;

import businesslogic.event.ServiceInfo;

import java.util.ArrayList;

public class SummarySheet {
    private ArrayList<Task> taskList;
    private SummarySheet(){}
    private ServiceInfo serviceUsed;

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl){
        this.taskList=newtl;
        return this.taskList;
    }
}
