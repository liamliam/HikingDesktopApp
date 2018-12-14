package taskChoices;

import functions.Task;
import ui.HikingApp;

public class TaskChoiceKmRT extends TaskChoice{
    public void taskChoice(Task task, String KmRTString){
        double KmRTnumber = Double.parseDouble(KmRTString);
        String KmRTSpecification = ("Be able to run a kilometer in " + KmRTString + " minutes or less");
        task.taskConstructor(2, KmRTnumber, KmRTSpecification, HikingApp.getTaskList());
    }
}
