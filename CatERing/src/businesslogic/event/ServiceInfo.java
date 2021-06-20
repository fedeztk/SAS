package businesslogic.event;
import businesslogic.menu.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ServiceInfo implements EventItemInfo {
    private int id;
    private String name;
    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private int participants;
    private Menu m;

    public ServiceInfo(String name) {
        this.name = name;
    }

    public ServiceInfo() {
    }

    public String toString() {
        return name + ": " + date + " (" + timeStart + "-" + timeEnd + "), " + participants + " pp.";
    }

    public Menu getMenu() {
        return m;
    }

    public void setMenu(Menu m) {
        this.m = m;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }


    // STATIC METHODS FOR PERSISTENCE
    public static ObservableList<ServiceInfo> loadServiceInfoForEvent(int event_id) {
        ObservableList<ServiceInfo> result = FXCollections.observableArrayList();
        String query = "SELECT * " +
                "FROM Services WHERE event_id = " + event_id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                ServiceInfo serv = new ServiceInfo(s);
                serv.id = rs.getInt("id");
                serv.date = rs.getDate("service_date");
                serv.timeStart = rs.getTime("time_start");
                serv.timeEnd = rs.getTime("time_end");
                serv.participants = rs.getInt("expected_participants");
                serv.m = Menu.loadMenuById(rs.getInt("approved_menu_id"));
                result.add(serv);
            }
        });

        return result;
    }

    public static ServiceInfo loadServiceInfoById(int id) {
        String query = "SELECT * FROM Services WHERE id =" + id + ";";
        ServiceInfo si = new ServiceInfo();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                si.id = id;
                si.name = rs.getString("name");
                si.date = rs.getDate("service_date");
                si.timeStart = rs.getTime("time_start");
                si.timeEnd = rs.getTime("time_end");
                si.participants = rs.getInt("expected_participants");
                si.m = Menu.loadMenuById(rs.getInt("approved_menu_id"));
            }
        });
        return si;
    }
}
