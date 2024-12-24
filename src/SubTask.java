public class SubTask extends Task {
    private int colId;


    public SubTask(int id, String name, String description, TaskStatus status, int colId) {
        super(id, name, description, status);
        this.colId = colId;
    }


    public int getColId() {
        return colId;
    }
}
