package util;

import Exceptions.AlreadyHaveThatTypeException;
import Exceptions.RemoveCalledWithNoTasksException;
import functions.Task;
import taskChoices.*;
import ui.HikingApp;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class TaskInteraction {
    public static Task setupTaskType(String t, JLabel jl1, String selectedString) throws AlreadyHaveThatTypeException {
        Task newTask = new Task();
        if (t.matches("([1-9]+.?[0-9]*|0.[0-9]*[1-9]+[0-9]*)")) {
            jl1.setVisible(false);
            if (selectedString.equals("Distance Per Day") && checkForExistingTaskType(1)) {
                newTask.setType(1);
                TaskChoice choice1 = new TaskChoiceDPD();
                choice1.taskChoice(newTask, t);
            } else if (selectedString.equals("Kilometer Run Time") && checkForExistingTaskType(2)) {
                newTask.setType(2);
                TaskChoiceKmRT choice2 = new TaskChoiceKmRT();
                choice2.taskChoice(newTask, t);
            } else if (selectedString.equals("Stairs Per Day") && checkForExistingTaskType(3)) {
                newTask.setType(3);
                TaskChoiceSPD choice3 = new TaskChoiceSPD();
                choice3.taskChoice(newTask, t);
            } else if (selectedString.equals("Body Mass Index") && checkForExistingTaskType(4)) {
                newTask.setType(4);
                TaskChoiceBMI choice4 = new TaskChoiceBMI();
                choice4.taskChoice(newTask, t);
            }
        } else {
            jl1.setVisible(true);
        }
        return newTask;
    }

    public static void checkOffFromTaskList(TaskList tasklist, String removeString) throws RemoveCalledWithNoTasksException {
        int removeType = 0;
        switch (removeString) {
            case "Distance Per Day":
                removeType = 1;
                break;
            case "Kilometer Run Time":
                removeType = 2;
                break;
            case "Stairs Per Day":
                removeType = 3;
                break;
            case "Body Mass Index":
                removeType = 4;
                break;
        }
        if (!getUnavailableTaskTypes().contains(removeType)) {
            throw new RemoveCalledWithNoTasksException();
        }
        removeTaskConfirmed(tasklist, removeType);
    }

    private static void removeTaskConfirmed(TaskList tasklist, int removeType) {
        Task t = new Task();
        for (Task p : HikingApp.getTaskList().getTasklist().values()) {
            if (p.getType() == removeType) {
                double num = p.getNumber();
                String s = p.getSpecification();
                t.taskConstructor(removeType, num, s, tasklist);
                tasklist.removeTask(p);
                t.removeTaskList();
                break;
            }
        }
    }

    public static String taskDisplayHelper(TaskList taskList) {
        int i = 0;
        String taskString = "";
        if (taskList.taskListSize() > 0) {
            taskString = "Tasks: ";
            for (Task t : TaskList.getTasklist().values()) {
                i++;
                taskString = taskString + "\n" + i + ". " + t.getSpecification();
            }
        }
        return taskString;
    }

    public static Set<Integer> getUnavailableTaskTypes() {
        Set<Integer> typeSet = new HashSet<>();
        for (Task t : HikingApp.getTaskList().getTasklist().values()) {
            if (!typeSet.contains(t.getType())) {
                typeSet.add(t.getType());
            }
        }
        return typeSet;
    }

    private static boolean checkForExistingTaskType(int num) {
        for (Task t : HikingApp.getTaskList().getTasklist().values()) {
            if (t.getType() == num) {
                return false;
            }
        }
        return true;
    }
}