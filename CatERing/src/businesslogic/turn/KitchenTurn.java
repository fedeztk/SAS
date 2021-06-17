package businesslogic.turn;

import java.util.Date;

public class KitchenTurn extends Turn {
    private boolean saturated;
    private Date startDate;
    private Date endDate;

    public KitchenTurn(){
        saturated=false;
    }


    public boolean isSaturated (){
        return saturated;
    }

    public void setSaturated(boolean saturated) {
        this.saturated = saturated;
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
