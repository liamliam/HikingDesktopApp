package taskChoices;

import functions.Task;
import ui.HikingApp;

public class TaskChoiceBMI extends TaskChoice{
    public void taskChoice(Task task, String BMIString){
        double BMInumber = Double.parseDouble(BMIString);
        String BMISpecification = ("Have a BMI of " + BMInumber);
        task.taskConstructor(4, BMInumber, BMISpecification, HikingApp.getTaskList());
    }
}
