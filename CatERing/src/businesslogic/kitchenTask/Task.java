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
    private int id;
    private int quantity;
    private Time time; // time string or calendar??
    private boolean done;
    private User cook;
    private Job consistingJob;
    private ArrayList<KitchenTurn> turnList;

    private Task() {
        turnList = new ArrayList<>();
    }

    public Task(Job j) {
        this.consistingJob = j;
        turnList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", time=" + time +
                ", done=" + done +
                ", cook=" + cook +
                ", consistingJob=" + consistingJob +
                ", turnList=" + turnList +
                '}';
    }

    public void assignTask(ArrayList<KitchenTurn> tl, int portions, Time duration, User cook) {
        if (quantity != -1) quantity = portions;
        if (duration != null) time = duration;
        if (cook != null && ((Cook) cook.useBehaviour(User.Role.CUOCO)).isAvailable(turnList)) {
            this.cook = cook;
        }
        if (tl != null) {
            turnList = tl;
        }
    }

    public void disassignTask() {
        quantity = -1;
        time = null;
        done = false;
        cook = null;
        done = false;
        turnList.clear();
    }

    public void modifyTask(ArrayList<KitchenTurn> tl, int portions, Time duration, User cook, Job job) throws SummarySheetException {
        if (portions != -1) {
            this.quantity = portions;
        }
        if (tl != null) {
            this.turnList = tl;
        }
        if (duration != null) {
            this.time = duration;
        }
        if (cook != null) {
            if (((Cook) cook.useBehaviour(User.Role.CUOCO)).isAvailable(turnList)) {
                this.cook = cook;
            } else {
                throw new SummarySheetException();
            }
        }
        if (job != null) {
            this.consistingJob = job;
        }
    }

    public void done() {
        this.done = true;
    }

    private void setId(int i) {
        this.id = i;
    }

    //METODI STATICI PERSISTENCE
    public static void saveTaskAdded(Task t) {
        String query = "INSERT INTO catering.Tasks (consisting_job, summarysheet_id) VALUES (" + t.consistingJob.getId() +
                "," + CatERing.getInstance().getKitchenTaskMgr().getCurrentSummarySheet().getId() + ")";
        if (PersistenceManager.executeUpdate(query) == 1) t.setId(PersistenceManager.getLastId());
        System.out.println(t);
//        {
//            @Override
//            public void handle(ResultSet rs) throws SQLException {
//                t.setId(rs.getInt("id"));
//            }
//        });
    }


    public static void saveTaskAssigned(Task t) {
        String query = "UPDATE catering.Tasks (quantity, time,done,cook_id,consisting_job) VALUES (" +
                +CatERing.getInstance().getKitchenTaskMgr().getCurrentSummarySheet().getId() + "," + t.quantity + "," + t.time + "," + t.done + ") WHERE id=" + t.getId() + ";";
        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore inserimento");

    }

    public static Task loadTaskById(int id) {
        String query = "SELECT * from catering.Tasks WHERE id=" + id + ";";
        Task t = new Task();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                t.id = rs.getInt("id");
                t.quantity = rs.getInt("quantity");
                t.time = rs.getTime("time");
                t.done = rs.getBoolean("done");
                t.cook = User.loadUserById(rs.getInt("cook_id"));

//                t.consistingJob = rs.getInt("consisting_job");
                String query = "SELECT * from catering.TurnList WHERE id=" + t.id + ";";
                PersistenceManager.executeQuery(query, new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs) throws SQLException {
                        if (rs.getString("type").charAt(0) == 'k') {
                            KitchenTurn kt = new KitchenTurn();
                            kt.setStartDate(rs.getDate("start_date"));
                            kt.setEndDate(rs.getDate("end_date"));
                            t.turnList.add(kt);
                        }
                    }
                });
            }
        });
        return t;
    }

    public int getId() {
        return this.id;
    }
}
