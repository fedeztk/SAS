package businesslogic.kitchenTask;

import businesslogic.CatERing;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.Cook;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Task {
    private  int id;
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
        done=false;
    }
    public void modifyTask(ArrayList<KitchenTurn> tl, int portions, Time duration, User cook, Job job) throws SummarySheetException {
        if(portions!=-1){
            this.quantity=portions;
        }
        if(tl!=null){
            this.turnList = tl;
        }
        if(duration!=null){
            this.time=duration;
        }
        if(cook!=null){
            if(((Cook)cook.useBehaviour(User.Role.CUOCO)).isAvailable(turnList)){
                this.cook=cook;
            }else{
                throw new SummarySheetException();
            }
        }
        if(job!=null){
            this.consistingJob=job;
        }
    }

    public void done(){
        this.done=true;
    }

    private void setId(int id) {
        this.id=id;
    }

    //METODI STATICI PERSISTENCE
    public static void saveTaskAdded(Task t) {
        String query = "INSERT INTO catering.Tasks (summarysheet_id, quantity, time,done,cook_id,consisting_job) VALUES ("+
                +CatERing.getInstance().getKitchenTaskMgr().getCurrentSummarySheet().getId()+","+ t.quantity+","+ t.time+","+ t.done+
                ","+ t.consistingJob.getId()+")";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                t.setId(rs.getInt("id"));
            }
        });
    }
}
