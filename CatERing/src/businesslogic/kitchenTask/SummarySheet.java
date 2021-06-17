package businesslogic.kitchenTask;

import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


//abbastanza fatto tutto
public class SummarySheet {
    private int id;
    private ServiceInfo serviceUsed;
    private User owner;
    private ArrayList<Task> taskList;

    public SummarySheet(ServiceInfo s, User u, Menu m) {

        serviceUsed = s;
        owner = u;
        taskList = new ArrayList<>();
        ArrayList<MenuItem> recipes = m.getAllRecipes();
        for (MenuItem r : recipes) {
            Task t = new Task(r.getItemRecipe());
            taskList.add(t);
        }
    }

    public int getId() {
        return this.id;
    }


    public ArrayList<Task> sortTasks(ArrayList<Task> newtl) {
        this.taskList = newtl;
        return this.taskList;
    }

    public boolean isOwner(User u) {
        return u == owner;
        // implementare user.equals(u)?
        // return owner.equals(u);
    }

    public boolean contains(Task t) {
        return taskList.contains(t);
    }

    public Task addTask(Job j) {
        Task t = new Task(j);
        taskList.add(t);
        return t;
    }

    public void assignTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration, User cook) {
        t.assignTask(tl, portions, duration, cook);
    }

    public void deleteTask(Task t) {
        taskList.remove(t);
    }

    public void modifyTask(Task t, ArrayList<KitchenTurn> tl, int portions, Time duration, User cook, Job job) throws SummarySheetException {
        t.modifyTask(tl, portions, duration, cook, job);
    }

    public void disassignTask(Task t) {
        t.disassignTask();
    }

    public void taskDone(Task t) {
        t.done();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    private User getOwner() {
        return this.owner;
    }

    private ServiceInfo getServiceInfo() {
        return this.serviceUsed;
    }

    private void setId(int id) {
        this.id = id;
    }

    //METODI STATICI PERSISTENCE

    public static void saveTaskSorted(SummarySheet ss, Task t) {

    }

    public static void saveSummarySheetCreated(SummarySheet ss) {
        String upd = "INSERT INTO catering.SummarySheets (owner_id, service_info_id) values (" +
                ss.getOwner().getId() + "," + ss.getServiceInfo().getId() + ");";
        ss.setId(PersistenceManager.executeUpdate(upd));//,new ResultHandler() {
//            @Override
//            public void handle(ResultSet rs) throws SQLException {
//                System.out.println(rs.toString());
//                ss.setId(rs.getInt("id"));
//            }
//        });
        }
    }
