package businesslogic.user;
import businesslogic.turn.KitchenTurn;
import java.util.ArrayList;

public class Cook implements Behaviour{
    public boolean isAvailable(ArrayList<KitchenTurn> kt){
        //fantoccio
        return true;
    }
}
