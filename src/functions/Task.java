package functions;

import util.TaskList;

public class Task {
    private int type;
    private double number;
    private String specification;
    private static int MAX_TASK_NUM = 4;
    private TaskList tasklist;

    public void taskConstructor(int type, double number, String specification, TaskList tasklist){
        this.type = type;
        this.number = number;
        this.specification = specification;
        this.tasklist = tasklist;
    }

    public String getSpecification(){
        return this.specification;
    }
    public int getType() {
        return this.type;
    }
    public double getNumber() {
        return this.number;
    }
    public void setType(int type) {
        this.type = type;
    }
    public static int getTaskNum(){
        return MAX_TASK_NUM;
    }
    public void setTasklist(TaskList tasklist) {
        this.tasklist = tasklist;
    }
    public void removeTaskList(){
        tasklist = null;
    }
}
