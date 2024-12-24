import java.util.HashMap;

public class TaskTracker {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private static HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int nextId = 1;

    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public void addEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void deleteEpic(int epicId) {
        epics.remove(epicId);
    }

    public void addSubTask(SubTask subTask) {
        subTask.setId(nextId++);
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getColId());
        if (epic != null) {
            epic.getTaskIds().add(subTask.getId());
            updateEpicStatus(epic);
        }
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getColId());
        if (epic != null) {
            updateEpicStatus(epic);
        }
    }

    public void deleteSubTask(int subTaskId) {
        SubTask subTask = subTasks.remove(subTaskId);
        if (subTask != null) {
            Epic epic = epics.get(subTask.getColId());
            if (epic != null) {
                epic.getTaskIds().remove((Integer) subTaskId);
                updateEpicStatus(epic);
            }
        }
    }

    public static SubTask getSubTask(int subTaskId) {
        return subTasks.get(subTaskId);
    }

    private void updateEpicStatus(Epic epic) {
        epic.updateStatus();
    }
}