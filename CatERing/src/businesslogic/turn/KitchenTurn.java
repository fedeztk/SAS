package businesslogic.turn;

import java.util.Date;

public class KitchenTurn implements Turn {
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
