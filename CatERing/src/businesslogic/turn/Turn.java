package businesslogic.turn;

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
        return "\nTurn{" +
                "\n\t id=" + id +
                ",\n\t startDate=" + startDate +
                ",\n\t endDate=" + endDate +
                ",\n\t deadline=" + deadline +
                ",\n\t location='" + location + '\'' +
                "\n}";
    }
}
