package util;

import functions.Task;

import java.util.*;

public class TaskList {
    private static Map<Integer, Task> tasklist = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(tasklist, taskList.tasklist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasklist);
    }

    public void addTask(Task t, TaskList tl) {
        tasklist.put(t.getType(), t);
        t.setTasklist(tl);
    }

    public void removeTask(Task t) {
        tasklist.remove(t.getType());
    }

    public Integer taskListSize() {
        return tasklist.size();
    }

    public static Map<Integer, Task> getTasklist() {
        return tasklist;
    }
}
