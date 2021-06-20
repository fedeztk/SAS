package test.testKitchenTask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.kitchenTask.KitchenTaskManager;


public class Test1 {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println("TEST creazione foglio riepilogativo");

        ServiceInfo si = ServiceInfo.loadServiceInfoById(2);
        KitchenTaskManager ktm = CatERing.getInstance().getKitchenTaskMgr();

        try {
            System.out.println(ktm.createSummarySheet(si));
        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
