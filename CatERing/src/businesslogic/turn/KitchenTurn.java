package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KitchenTurn extends Turn {
    private boolean saturated;

    public KitchenTurn() {
        saturated = false;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(0, s.length() - 2) + ",\n\t" +
                "saturated = " + saturated +
                "\n}";
    }

    public boolean isSaturated() {
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
                if (rs.getString("type").charAt(0) == 'k') {
                    kt.setStartDate(rs.getDate("start_date"));
                    kt.setEndDate(rs.getDate("end_date"));
                    kt.setSaturated(rs.getBoolean("saturation"));
                    kt.setId(id);
                }

            }
        });
        return kt;
    }


    public static void saveKitchenTurnSat(KitchenTurn kt) {
        String query = "UPDATE catering.Turns SET saturation = " + (kt.saturated ? 1 : 0) + " WHERE id=" + kt.getId() + ";";

        PersistenceManager.executeUpdate(query);
    }
}