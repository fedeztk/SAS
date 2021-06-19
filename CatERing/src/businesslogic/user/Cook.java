package businesslogic.user;
import businesslogic.turn.KitchenTurn;
import java.util.ArrayList;

public class Cook implements Behaviour{

    //WARNING fake method
    public boolean isAvailable(ArrayList<KitchenTurn> kt){
        //TODO UC "indicare le proprie disponibilità"
        return true;
    }
}
