package todo.toandoan.com.architecturecomponents.data.source;

import android.arch.lifecycle.LiveData;
import java.util.List;
import todo.toandoan.com.architecturecomponents.data.model.Task;

public interface TaskDataSource {
    LiveData<Task> getTaskById(int taskId);

    LiveData<List<Task>> getAllTask();

    void addTask(Task task);

    void updateTask(Task task);

    void deleteAllTask();
}
