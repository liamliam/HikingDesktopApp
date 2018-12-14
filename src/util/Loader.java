package util;

import functions.Goal;
import functions.PackingList;
import functions.Task;
import taskChoices.TaskChoiceBMI;
import taskChoices.TaskChoiceDPD;
import taskChoices.TaskChoiceKmRT;
import taskChoices.TaskChoiceSPD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loader {
    public TaskList loadTask() throws IOException {
        TaskList tl = new TaskList();
        List<String> lines = Files.readAllLines(Paths.get("./src/Resources/TaskData.txt"));
        TaskList loadedTaskList = new TaskList();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Task newTask = new Task();
            switch (partsOfLine.get(0)) {
                case "1":
                    TaskChoiceDPD c1 = new TaskChoiceDPD();
                    c1.taskChoice(newTask, partsOfLine.get(1));
                    break;
                case "2":
                    TaskChoiceKmRT c2 = new TaskChoiceKmRT();
                    c2.taskChoice(newTask, partsOfLine.get(1));
                    break;
                case "3":
                    TaskChoiceSPD c3 = new TaskChoiceSPD();
                    c3.taskChoice(newTask, partsOfLine.get(1));
                    break;
                case "4":
                    TaskChoiceBMI c4 = new TaskChoiceBMI();
                    c4.taskChoice(newTask, partsOfLine.get(1));
                    break;
            }
            loadedTaskList.addTask(newTask, tl);
        }
        return loadedTaskList;
    }

    public Goal loadGoal() throws IOException {
        Goal newGoal = new Goal();
        List<String> lines = Files.readAllLines(Paths.get("./src/Resources/GoalData.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitonQuote(line);
            String name = partsOfLine.get(0);
            String difficulty = partsOfLine.get(1);
            String elevationGain = partsOfLine.get(2);
            String distance = partsOfLine.get(3);
            String estimatedTime = partsOfLine.get(4);
            newGoal.setGoal(name, Double.parseDouble(difficulty), Double.parseDouble(distance), Double.parseDouble(elevationGain), Double.parseDouble(estimatedTime));
        }
        return newGoal;
    }

    public PackingList loadPL() throws IOException {
        PackingList pl = new PackingList();
        List<String> lines = Files.readAllLines(Paths.get("./src/Resources/PLData.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitonQuote(line);
            pl.setPl(partsOfLine);
        }
        return pl;
    }

    private static ArrayList<String> splitonQuote(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}