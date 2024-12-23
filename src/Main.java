public class Main {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();

        Task simple = new Task();
        simple.title = "Моя задача";
        simple.status = TaskStatus.NEW;
        taskTracker.addTask(simple);


        simple.status = TaskStatus.IN_PROGRESS;
        taskTracker.updateTask(simple);

        simple.status = TaskStatus.DONE;
        taskTracker.updateTask(simple);

        Epic col = new Epic();
        taskTracker.addEpic(col);

        SubTask task1 = new SubTask();
        task1.colId = col.id;
        taskTracker.addSubTask(task1);

        SubTask task2 = new SubTask();
        task2.colId = col.id;
        taskTracker.addSubTask(task2);

        SubTask task3 = new SubTask();
        task3.colId = col.id;
        taskTracker.addSubTask(task3);
    }
}
