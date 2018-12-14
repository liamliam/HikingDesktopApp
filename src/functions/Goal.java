package functions;

public class Goal {
    private String name;
    private double difficulty, elevationGain, distance, estimatedTime;

    public void setGoal(String name, double difficulty, double elevationGain, double distance, double estimatedTime) {
        this.name = name;
        this.difficulty = difficulty;
        this.elevationGain = elevationGain;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
    }


    public String getName() {
        return name;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public double getElevationGain() {
        return elevationGain;
    }

    public double getDistance() {
        return distance;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }
}
