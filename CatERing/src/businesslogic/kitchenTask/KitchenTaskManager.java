package businesslogic.kitchenTask;

import businesslogic.CatERing;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.turn.ShiftBoard;
import businesslogic.turn.Turn;
import businesslogic.user.User;


import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;

public class KitchenTaskManager {
    private SummarySheet currentSummarySheet;
    private ArrayList<KitchenTaskEventReceiver> eventReceivers;

    public KitchenTaskManager(){
        eventReceivers=new ArrayList<>();
    }

    public SummarySheet createSummarySheet(ServiceInfo s) throws UseCaseLogicException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        Menu m = s.getMenu();
        if(!m.isOwner(u) || !u.isChef()){
            throw new UseCaseLogicException();
        }
        setCurrentSummarySheet(new SummarySheet(s,u,m));

        //TODO: notify
        return currentSummarySheet;
    }
    public SummarySheet loadSummarySheet(SummarySheet ss) throws UseCaseLogicException, SummarySheetException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        if(!ss.isOwner(u)){
            throw new SummarySheetException();
        }
        setCurrentSummarySheet(ss);
        return currentSummarySheet;
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
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        Task added = currentSummarySheet.addTask(j);

        //TODO: notify
        return added;
    }

    public void deleteTask(Task t) throws UseCaseLogicException, SummarySheetException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null || !currentSummarySheet.contains(t)){
            throw new SummarySheetException();
        }
        currentSummarySheet.deleteTask(t);
        //TODO: notify
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, -1, null, null);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int quantity) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, quantity, null, null);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, User u) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, -1, null, u);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, Time time) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, -1, time, null);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int quantity, Time time) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, quantity, time, null);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int quantity, User u) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, quantity, null, u);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, Time time, User u) throws UseCaseLogicException, SummarySheetException {
        assignTask(t, tl, -1, time, u);
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int quantity, Time time, User u) throws UseCaseLogicException, SummarySheetException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if(currentUser.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null){
            throw new SummarySheetException();
        }
        for(KitchenTurn kt : tl){
            if(kt.isSaturated()){
                throw new SummarySheetException();
            }
        }
        if(currentSummarySheet.contains(t)==false){
            throw new SummarySheetException();
        }
        currentSummarySheet.assignTask(t,tl, quantity, time, u);

        //TODO: notify
    }

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration, User cook, Job job) throws SummarySheetException, UseCaseLogicException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if(currentUser.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null || !currentSummarySheet.contains(t)){
            throw new SummarySheetException();
        }
        if(tl!=null){
            for(KitchenTurn kt: tl){
                if(kt.isSaturated()){
                    throw new SummarySheetException();
                }
            }
        }
        currentSummarySheet.modifyTask(t,tl,portions,duration,cook,job);

        //TODO:notify
    }
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl) throws UseCaseLogicException, SummarySheetException { modifyTask(t,tl,-1, null, null,null);}
    public void modifyTask(Task t, int portions) throws UseCaseLogicException, SummarySheetException {modifyTask(t, null, portions, null, null, null);}
    public void modifyTask(Task t, Time duration) throws UseCaseLogicException, SummarySheetException {modifyTask(t, null, -1, duration, null, null);}
    public void modifyTask(Task t, User cook) throws UseCaseLogicException, SummarySheetException { modifyTask(t, null, -1, null, cook, null);}
    public void modifyTask(Task t, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t, null, -1, null, null, job);}

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,null,null,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Time duration) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,duration, null,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,null,null,job);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,null,cook,null);}
    public void modifyTask(Task t, int portions, Time duration) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null, portions,duration,null,null);}
    public void modifyTask(Task t, int portions, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,portions,null, cook, null);}
    public void modifyTask(Task t, int portions, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,portions,null,null,job);}
    public void modifyTask(Task t, Time duration, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,-1,duration,cook,null);}
    public void modifyTask(Task t, Time duration, Job j) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,-1,duration,null,j);}
    public void modifyTask(Task t, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,-1,null, cook,job);}

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration) throws UseCaseLogicException, SummarySheetException {modifyTask(t, tl, portions,duration,null,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,null,null,job);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,null,cook,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Time duration, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,duration,null,job);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Time duration, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,duration,cook,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,null,cook,job);}
    public void modifyTask(Task t, int portions, Time duration, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t, null, portions, duration, null, job);}
    public void modifyTask(Task t, int portions, Time duration, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,portions,duration,cook,null);}
    public void modifyTask(Task t, int portions, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t, null, portions,null, cook, job);}
    public void modifyTask(Task t, Time duration, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null, -1,duration,cook,job);}
//25 -> 7

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,duration,null,job);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,duration,cook,null);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,portions,null,cook,job);}
    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, Time duration, Job job, User cook) throws UseCaseLogicException, SummarySheetException {modifyTask(t,tl,-1,duration,cook,job);}
    public void modifyTask(Task t, int portions, Time duration, User cook, Job job) throws UseCaseLogicException, SummarySheetException {modifyTask(t,null,portions,duration,cook,job);}









    public void disassignTask(Task t) throws UseCaseLogicException, SummarySheetException {
        User currentUser = CatERing.getInstance().getUserManager().getCurrentUser();
        if(currentUser.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null || !currentSummarySheet.contains(t)){
            throw new SummarySheetException();
        }
        currentSummarySheet.disassignTask(t);
        //TODO: notify
    }

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl) throws UseCaseLogicException, SummarySheetException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null){
            throw new SummarySheetException();
        }
        ArrayList <Task> tl = currentSummarySheet.sortTasks(newtl);
        //TODO: notify
        return tl;
    }

    public ShiftBoard getShiftBoard() throws UseCaseLogicException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        return CatERing.getInstance().getTurnManager().getShiftBoard();
    }
//    public void modifyTask(Task t, ArrayList<Task>? tl, portions?, duration? Time, cook? User, job? Job);
    public void taskDone(Task t) throws UseCaseLogicException, SummarySheetException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null){
            throw new SummarySheetException();
        }
        currentSummarySheet.taskDone(t);
        //TODO: notify
    }
    public void setSaturation(KitchenTurn kt,  boolean v) throws UseCaseLogicException, SummarySheetException {
        User u = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!u.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSummarySheet==null){
            throw new SummarySheetException();
        }
        kt.setSaturated(true);
        //TODO: notify
    }

}
