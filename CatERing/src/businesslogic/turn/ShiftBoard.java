package businesslogic.turn;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShiftBoard {
    private static ShiftBoard singleInstance;
    private ArrayList<KitchenTurn> ktList;

    public static ShiftBoard getInstance() {
        if (singleInstance == null) {
            singleInstance = ShiftBoard.loadShiftBoard();
        }
        return singleInstance;
    }

    @Override
    public String toString() {
        return "ShiftBoard{" +
                "ktList=" + ktList +
                '}';
    }

    private ShiftBoard(ArrayList<KitchenTurn> ktl) {
        this.ktList = ktl;
    }

    public ArrayList<KitchenTurn> getTurn() {
        return ktList;
    }

    public static ShiftBoard loadShiftBoard() {
        ArrayList<KitchenTurn> ktl = new ArrayList<>();
        String query = "SELECT * FROM Turns WHERE type ='k';";

        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                KitchenTurn kt = new KitchenTurn();
                kt.setStartDate(rs.getDate("start_date"));
                kt.setEndDate(rs.getDate("end_date"));
                kt.setSaturated(rs.getBoolean("saturation"));
                kt.setId(rs.getInt("id"));
                ktl.add(kt);
            }
        });

        return new ShiftBoard(ktl);
    }
}