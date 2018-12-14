package util;

import Exceptions.*;
import functions.Goal;

public class GoalInteraction {
    public static Goal newGoal(String name, String difficulty, String elevationGain, String distance, String estimatedTime) throws MissingGoalFieldException, InvalidNameException, InvalidDifficultyException, InvalidDistanceException, InvalidElevationGainException, InvalidTimeException {
        Goal newGoal = new Goal();
        if ((name.equals("")) || (difficulty.equals("")) || distance.equals("") || elevationGain.equals("") || estimatedTime.equals("")) {
            throw new MissingGoalFieldException();
        } else if (!name.matches("^[a-zA-Z_ ]*$")) {
            throw new InvalidNameException();
        } else if (!difficulty.matches("([1-9]|[1-9]\\.[0-9]*|0\\.[0-9]*[1-9]+[0-9]*)")) {
            throw new InvalidDifficultyException();
        } else if (!elevationGain.matches("([1-9]+\\.?[0-9]*|0\\.[0-9]*[1-9]+[0-9]*)")) {
            throw new InvalidElevationGainException();
        } else if (!distance.matches("([1-9]+\\.?[0-9]*|0\\.[0-9]*[1-9]+[0-9]*)")) {
            throw new InvalidDistanceException();
        } else if (!estimatedTime.matches("([1-9]+\\.?[0-9]*|0\\.[0-9]*[1-9]+[0-9]*)")) {
            throw new InvalidTimeException();
        }
        newGoal.setGoal(name, Double.parseDouble(difficulty), Double.parseDouble(distance), Double.parseDouble(elevationGain), Double.parseDouble(estimatedTime));
        return newGoal;
    }

    public static String goalDisplayHelper(Goal goal) {
        if (!(goal.getDistance() == 0)) {
            return "Name: " + goal.getName() + '\n' +
                    "Difficulty: " + goal.getDifficulty() + "/10\n" +
                    "Elevation Gain: " + goal.getElevationGain() + "m \n" +
                    "Distance: " + goal.getDistance() + "km \n" +
                    "Estimated Time: " + goal.getEstimatedTime() + "hrs";
        }
        return "";
    }
}
