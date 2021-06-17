package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Turn {

    private int id;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private String location;

    public Turn() {
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setStartDate(Date st){
        startDate = st;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){return endDate;}

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deadline=" + deadline +
                ", location='" + location + '\'' +
                '}';
    }
}
