package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Turn {
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private String location;

    public Turn() {
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

}
