package businesslogic.kitchenTask;

import businesslogic.turn.KitchenTurn;

import java.util.ArrayList;

public interface KitchenTaskEventReceiver {
    void updateSummarySheetCreated(SummarySheet ss);
    void updateTaskAdded(Task t);
    void updateTaskDeleted(Task t);
    void updateTaskAssigned(Task t);
    void updateTaskDisassigned(Task t);
    void updateTaskSorted(ArrayList<Task> tl);
    void updateTaskModified(Task t);
    void updateTaskDone(Task t);
    void updateKitchenTurnSat(KitchenTurn kt);
}
