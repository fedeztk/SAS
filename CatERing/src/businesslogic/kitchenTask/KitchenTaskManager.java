package businesslogic.kitchenTask;

import businesslogic.CatERing;
import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.turn.ShiftBoard;
import businesslogic.turn.Turn;
import businesslogic.user.User;


import java.sql.Time;
import java.util.ArrayList;

public class KitchenTaskManager {
    private SummarySheet currentSummarySheet;
    private ArrayList<KitchenTaskEventReceiver> eventReceivers;

    public KitchenTaskManager(){
        eventReceivers=new ArrayList<>();
    }

    public SummarySheet createSummarySheet(ServiceInfo s) {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        currentSummarySheet = SummarySheet.create(s, u);
        return currentSummarySheet;
    }
    public SummarySheet loadSummarySheet(SummarySheet ss){
        return null;
    }

    public void addEventReceiver(KitchenTaskEventReceiver rec) {
        eventReceivers.add(rec);
    }

    public void removeEventReceiver(KitchenTaskEventReceiver rec) {
        eventReceivers.remove(rec);
    }

    public void setCurrentSummarySheet(SummarySheet ss){
        currentSummarySheet=ss;
    }

    public void notifySummarySheetCreated(SummarySheet ss){}
    public void notifyTaskAdded(Task t){}
    public void notifyTaskDeleted(Task t){}
    public void notifyTaskAssigned(Task t){}
    public void notifyTaskDisassigned(Task t){}
    public void notifyTaskModified(Task t){}
    public void notifyTaskDone(Task t){}
    public void notifyKitchenTurnSat(KitchenTurn kt){}
    public void notifyTasksSorted(ArrayList<Task> tl){}
    public Task addTask(Job j ){
        return null;
    }
//    public void deleteTask(Task t ){
//        this.currentSummarySheet.re
//    }

    public void assignTask(Task t, ArrayList<Turn> tl){
        assignTask(t, tl, null, null, null);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, String quantity){
        assignTask(t, tl, quantity, null, null);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, User u){
        assignTask(t, tl, null, null, u);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, Time time){
        assignTask(t, tl, null, time, null);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, String quantity, Time time){
        assignTask(t, tl, quantity, time, null);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, String quantity, User u){
        assignTask(t, tl, quantity, null, u);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, Time time, User u){
        assignTask(t, tl, null, time, u);
    }

    public void assignTask(Task t, ArrayList<Turn> tl, String quantity, Time time, User u){
        //TODO
    }

    public void disassignTask(Task t){
        // ??giusto o passare da summarysheet?? t.disassignTask();
    }

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl){
        return currentSummarySheet.sortTasks(newtl);
    }

    public ShiftBoard getShiftBoard(){
        return CatERing.getInstance().getKitchenTaskMgr().getShiftBoard();
    }
//    public void modifyTask(Task t, ArrayList<Task>? tl, portions?, duration? Time, cook? User, job? Job);
    public void taskDone(Task t){}
    public void setSaturation(KitchenTurn kt,  boolean v){}
}
