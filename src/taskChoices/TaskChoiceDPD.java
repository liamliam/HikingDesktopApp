package taskChoices;

import functions.Task;
import ui.HikingApp;

public class TaskChoiceDPD extends TaskChoice{
    public void taskChoice(Task task, String DPDString) {
        double DPDnumber = Double.parseDouble(DPDString);
        String DPDSpecification = ("Walk or run " + DPDString + "km per day");
        task.taskConstructor(1, DPDnumber, DPDSpecification, HikingApp.getTaskList());
    }
}
