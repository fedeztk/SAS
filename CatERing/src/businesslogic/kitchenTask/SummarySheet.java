package businesslogic.kitchenTask;

import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Job;
import businesslogic.turn.KitchenTurn;
import businesslogic.user.User;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;


public class SummarySheet {
    private int id;
    private ServiceInfo serviceUsed;
    private User owner;
    private ArrayList<Task> taskList;

    public SummarySheet() {
        taskList = new ArrayList<>();
    }

    public SummarySheet(ServiceInfo s, User u) {
        serviceUsed = s;
        owner = u;
        taskList = new ArrayList<>();
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SummarySheet {" +
                "\n\tid = " + id +
                ",\n\tserviceUsed = " + serviceUsed +
                ",\n\towner = " + owner +
                ",\n\ttaskList = " + taskList.toString().indent(8) +
                "}";
    }

    public ArrayList<Task> sortTasks(ArrayList<Task> newtl) {
        this.taskList = newtl;
        return this.taskList;
    }

    public boolean isOwner(User u) {
        return u.equals(owner);
    }

    public boolean contains(Task t) {
        return taskList.contains(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummarySheet that = (SummarySheet) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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


    //METODI STATICI PERSISTENCE
    public static void saveTaskSorted(Task t, int i) {
        String upd = "UPDATE Tasks SET position =" + i + " WHERE id =" + t.getId() + ";";
        PersistenceManager.executeUpdate(upd);
    }

    public static void saveSummarySheetCreated(SummarySheet ss) {
        String upd = "INSERT INTO catering.SummarySheets (owner_id, service_info_id) values (" +
                ss.getOwner().getId() + "," + ss.getServiceInfo().getId() + ");";
        if (PersistenceManager.executeUpdate(upd) == 1)
            ss.setId(PersistenceManager.getLastId());
    }

    public static SummarySheet loadSummarySheetById(int id) {
        String query = "SELECT * FROM SummarySheets WHERE id =" + id + ";";

        SummarySheet ss = new SummarySheet();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                ss.id = rs.getInt("id");
                ss.owner = User.loadUserById(rs.getInt("owner_id"));
                ss.serviceUsed = ServiceInfo.loadServiceInfoById(rs.getInt("service_info_id"));
                ss.taskList = Task.loadTaskBySummarySheetId(ss.id);
            }
        });
        return ss;
    }
}