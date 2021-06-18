package businesslogic.kitchenTask;

import businesslogic.CatERing;
import businesslogic.recipe.Job;
import businesslogic.recipe.Recipe;
import businesslogic.turn.KitchenTurn;
import businesslogic.turn.Turn;
import businesslogic.user.Cook;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.PreparedStatement;
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

    }


    public static void saveTaskAssigned(Task t) {
        String time = t.time == null ? null : "'" + t.time + "'";
        String query = "UPDATE catering.Tasks SET quantity=" + t.quantity +
                ", time = " + time +
                ", done =" + (t.done ? 1 : 0) +
                ",cook_id= " + t.cook.getId() +
                ",consisting_job = " + t.consistingJob.getId() +
                " WHERE id=" + t.getId() + ";";
        System.out.println("Query: " + query);
        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore inserimento");
        Task.saveTurnListUpdate(t, t.turnList);
    }

    public static void saveTaskModified(Task t) {
        String time = t.time == null ? null : "'" + t.time + "'";
        String query = "Update Tasks set quantity=" + t.quantity
                + ", time=" + time
                + ", cook_id=" + t.cook.getId()
                + ", consisting_job=" + t.consistingJob.getId() +
                " WHERE id=" + t.getId() + "; ";
        System.out.println("Query: " + query);
        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore modifica task");
        Task.saveTurnListUpdate(t, t.turnList);
    }

    private static void saveTurnListUpdate(Task t, ArrayList<KitchenTurn> tl) {
        Task.saveTurnListRemoveTask(t);

        String paramQuery = "Insert into TurnList (turn_id, task_id) values (?, ?)";
        PersistenceManager.executeBatchUpdate(paramQuery, tl.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, tl.get(batchCount).getId());
                ps.setInt(2, t.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {

            }
        });
    }

    private static void saveTurnListRemoveTask(Task t) {
        String query = "DELETE FROM TurnList WHERE task_id=" + t.getId() + ";";
        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore eliminazione turnList del task");
    }

    public static void saveTaskDisassigned(Task t) {
        String query = "Update Tasks set quantity=-1, done=0, time=null, cook_id=null WHERE id=" + t.getId() + "; ";
        System.out.println("Query: " + query);
        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore disassegnamento");
        Task.saveTurnListRemoveTask(t);
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
                t.consistingJob = Recipe.loadRecipeById(rs.getInt("consisting_job"));

                String query = "SELECT *\n" +
                        "FROM (SELECT turn_id from catering.TurnList WHERE task_id=" + t.id + ") as tl\n" +
                        "    join Turns as t\n" +
                        "        on tl.turn_id = t.id;";
                PersistenceManager.executeQuery(query, new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs) throws SQLException {
                        if (rs.getString("type").charAt(0) == 'k') {
                            KitchenTurn kt = new KitchenTurn();
                            kt.setStartDate(rs.getDate("start_date"));
                            kt.setEndDate(rs.getDate("end_date"));
                            kt.setId(rs.getInt("id"));
                            t.turnList.add(kt);
                        }
                    }
                });
            }
        });
        return t;
    }

    public static ArrayList<Task> loadTaskBySummarySheetId(int id) {
        String query = "SELECT * from catering.Tasks WHERE summarysheet_id=" + id + ";";
        ArrayList<Task> t = new ArrayList<>();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                t.add(Task.loadTaskById(rs.getInt("id")));
            }
        });

        return t;
    }

    public static void saveTaskDone(Task t) {
        String query = "UPDATE catering.Tasks SET done = " + (t.done ? 1 : 0) + " WHERE id=" + t.getId() + ";";

        if (PersistenceManager.executeUpdate(query) == 0) System.out.println("Errore inserimento");
    }

    public static void saveTaskDeleted(Task t) {
        String query = "DELETE FROM Tasks WHERE id=" + t.id + ";";
        if (PersistenceManager.executeUpdate(query) == 0)
            System.out.println("Errore eliminazione");
        Task.saveTurnListRemoveTask(t);
    }


    public int getId() {
        return this.id;
    }
}
