import java.util.HashMap;

public class TaskTracker {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int nextId = 1;

    public void addTask(Task task) {
        task.id = nextId++;
        tasks.put(task.id, task);
    }

    public void updateTask(Task task) {
        tasks.put(task.id, task);
    }

    public void addEpic(Epic epic) {
        epic.id = nextId++;
        epics.put(epic.id, epic);
    }

    public void addSubTask(SubTask subTask) {
        subTask.id = nextId++;
        subTasks.put(subTask.id, subTask);

        Epic col = epics.get(subTask.colId);
        if (col != null) {
            col.taskIds.add(subTask.id);
        }
    }
}
