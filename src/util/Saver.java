package util;

import functions.Goal;
import functions.PackingList;
import functions.Task;

import java.io.*;

public class Saver {
    public void saveTask(TaskList taskList) throws IOException {
        PrintWriter writer = new PrintWriter("./src/Resources/TaskData.txt", "UTF-8");
        for (Task t : taskList.getTasklist().values()) {
            if (!TaskList.getTasklist().get(t.getType()).getSpecification().equals(null)) {
                writer.println(t.getType() + " " + t.getNumber());
            }
        }
        writer.close();
    }

    public void saveGoal(Goal goal) throws IOException {
        PrintWriter writer = new PrintWriter("./src/Resources/GoalData.txt", "UTF-8");
        writer.println(goal.getName() + "," + goal.getDifficulty() + "," + goal.getElevationGain() + "," + goal.getDistance() + "," + goal.getEstimatedTime() + ",");
        writer.close();
    }

    public void savePL(PackingList pl) throws IOException {
        PrintWriter writer = new PrintWriter("./src/Resources/PLData.txt", "UTF-8");
        String printThis = "";
        for (String t : pl.getPl()) {
            printThis = printThis + t + ",";
        }
        writer.println(printThis);
        writer.close();
    }
}
