package persistence;

import businesslogic.kitchenTask.KitchenTaskEventReceiver;
import businesslogic.kitchenTask.SummarySheet;
import businesslogic.kitchenTask.Task;
import businesslogic.turn.KitchenTurn;

import java.util.ArrayList;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {
    @Override
    public void updateSummarySheetCreated(SummarySheet ss) {
        SummarySheet.saveSummarySheetCreated(ss);
    }

    @Override
    public void updateTaskAdded(Task t) {
        Task.saveTaskAdded(t);
    }

    @Override
    public void updateTaskDeleted(Task t) {
        Task.saveTaskDeleted(t);
    }

    @Override
    public void updateTaskAssigned(Task t) {
        Task.saveTaskAssigned(t);
    }

    @Override
    public void updateTaskDisassigned(Task t) {
        Task.saveTaskDisassigned(t);
    }

    @Override
    public void updateTaskSorted(SummarySheet ss) {
        ArrayList<Task> newtl = ss.getTaskList();
        for (int i = 0; i < newtl.size(); i++) {
            Task t = newtl.get(i);
            SummarySheet.saveTaskSorted(t, i);
        }
    }

    @Override
    public void updateTaskModified(Task t) {
        Task.saveTaskModified(t);
    }

    @Override
    public void updateTaskDone(Task t) {
        Task.saveTaskDone(t);
    }

    @Override
    public void updateKitchenTurnSat(KitchenTurn kt) {
        KitchenTurn.saveKitchenTurnSat(kt);
    }
}
