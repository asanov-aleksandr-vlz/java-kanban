import java.util.ArrayList;

public class Epic extends Task {
    private static final TaskStatus status = TaskStatus.NEW;
    private ArrayList<Integer> taskIds = new ArrayList<>();


    public Epic(int id, String name, String description) {
        super(id, name, description, status);
    }

    public ArrayList<Integer> getTaskIds() {
        return taskIds;
    }
    public void addSubTask(int subTaskId) {
        taskIds.add(subTaskId);
    }

    public void updateStatus() {
        int completed = 0;
        for (Integer subTaskId : taskIds) {
            SubTask subTask = TaskTracker.getSubTask(subTaskId); // Убедитесь, что метод существует
            if (subTask != null && subTask.getStatus() == TaskStatus.DONE) { // Используйте TaskStatus
                completed++;
            }
        }
        if (completed == taskIds.size() && completed > 0) {
            this.setStatus(TaskStatus.DONE);
        } else if (completed > 0) {
            this.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            this.setStatus(TaskStatus.NEW);
        }
    }
}
