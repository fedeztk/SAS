package businesslogic.turn;

import java.util.Date;

public class KitchenTurn extends Turn {
    private boolean saturated;

    public KitchenTurn(){
        saturated=false;
    }

    public boolean isSaturated (){
        return saturated;
    }

    public void setSaturated(boolean saturated) {
        this.saturated = saturated;
    }

    public Date getDate(){
        return this.date;
    }
}
