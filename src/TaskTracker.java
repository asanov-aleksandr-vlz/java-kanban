import java.util.HashMap;
import java.util.ArrayList;
public class TaskTracker {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>(); //убрал static
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
        Epic epic = epics.remove(epicId);
        if (epic != null) {
            for (Integer subTaskId : epic.getTaskIds()) {
                subTasks.remove(subTaskId); //удалил из Map все подзадачи эпика
            }
        }
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
                epic.getTaskIds().remove(subTaskId); //убрал привидение типа к Integer
                updateEpicStatus(epic);
            }
        }
    }

    public SubTask getSubTask(int subTaskId) {
        return subTasks.get(subTaskId);
    }

    /*добавил методы ниже:
        Task getTask(int id)
        Epic getEpic(int id)
        ArrayList<Task> getTasks()
        ArrayList<Subtask> getSubtasks()
        ArrayList<Epic> getEpics()
        ArrayList<Subtask> getEpicSubtasks(int epicId)
        void deleteTasks()
        void deleteSubtasks()
        void deleteEpics()*/
    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<SubTask> getSubtasks() {
        return new ArrayList<>(subTasks.values());
    }

    public ArrayList<SubTask> getEpicSubtasks(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<SubTask> epicSubtasks = new ArrayList<>();
        if (epic != null) {
            for (Integer subTaskId : epic.getTaskIds()) {
                epicSubtasks.add(subTasks.get(subTaskId));
            }
        }
        return epicSubtasks;
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteSubtasks() {
        subTasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subTasks.clear();
    }

    private void updateEpicStatus(Epic epic) {
        epic.updateStatus();
    }
}