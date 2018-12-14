package taskChoices;

import functions.Task;
import ui.HikingApp;

public class TaskChoiceSPD extends TaskChoice{
    public void taskChoice(Task task, String SPDString){
        double SPDnumber = Double.parseDouble(SPDString);
        String SPDSpecification = ("Climb " + SPDString + " stairs per day");
        task.taskConstructor( 3, SPDnumber, SPDSpecification, HikingApp.getTaskList());
    }
}
