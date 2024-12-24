public class Main {
    public static void main(String[] args) {
        TaskTracker taskTracker = new TaskTracker();


        Task simple = new Task(1, "Моя задача", "Описание задачи", TaskStatus.NEW);
        taskTracker.addTask(simple);


        simple.setStatus(TaskStatus.IN_PROGRESS);
        taskTracker.updateTask(simple);
        simple.setStatus(TaskStatus.DONE);
        taskTracker.updateTask(simple);


        Epic col = new Epic(2, "Мой эпик", "Описание эпика");
        taskTracker.addEpic(col);


        SubTask task1 = new SubTask(3, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW, col.getId());
        taskTracker.addSubTask(task1);

        SubTask task2 = new SubTask(4, "Подзадача 2", "Описание подзадачи 2", TaskStatus.NEW, col.getId());
        taskTracker.addSubTask(task2);

        SubTask task3 = new SubTask(5, "Подзадача 3", "Описание подзадачи 3", TaskStatus.NEW, col.getId());
        taskTracker.addSubTask(task3);
    }
}