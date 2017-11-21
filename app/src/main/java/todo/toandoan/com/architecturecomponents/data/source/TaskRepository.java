package todo.toandoan.com.architecturecomponents.data.source;

import android.arch.lifecycle.LiveData;
import java.util.List;
import todo.toandoan.com.architecturecomponents.data.model.Task;

public class TaskRepository implements TaskDataSource {
    private static TaskRepository sInstance;

    private TaskDataSource mTaskLocalDataSource;

    public static TaskRepository getInstance(TaskDataSource taskLocalDataSource) {
        if (sInstance == null) {
            sInstance = new TaskRepository(taskLocalDataSource);
        }
        return sInstance;
    }

    public TaskRepository(TaskDataSource taskLocalDataSource) {
        mTaskLocalDataSource = taskLocalDataSource;
    }

    @Override
    public LiveData<Task> getTaskById(int taskId) {
        return mTaskLocalDataSource.getTaskById(taskId);
    }

    @Override
    public LiveData<List<Task>> getAllTask() {
        return mTaskLocalDataSource.getAllTask();
    }

    @Override
    public void addTask(Task task) {
        mTaskLocalDataSource.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        mTaskLocalDataSource.updateTask(task);
    }

    @Override
    public void deleteAllTask() {
        mTaskLocalDataSource.deleteAllTask();
    }
}
