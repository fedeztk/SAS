package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static KitchenTurn loadKitchenTurnById(int id) {
        String query = "SELECT * FROM Turns WHERE id=" + id + ";";
        KitchenTurn kt = new KitchenTurn();

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                if (rs.getString("type").charAt(0)=='k'){
                    kt.setStartDate(rs.getDate("start_date"));
                    kt.setEndDate(rs.getDate("end_date"));
                    kt.setSaturated(rs.getBoolean("saturation"));
                }

            }
        });
        return kt;
    }
}
