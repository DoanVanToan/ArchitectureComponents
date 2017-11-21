package todo.toandoan.com.architecturecomponents.data.source.local;

import android.arch.lifecycle.LiveData;
import java.util.List;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.data.source.TaskDataSource;

public class TaskLocalDataSource implements TaskDataSource {
    private static TaskLocalDataSource sInstance;
    private TaskDAO mTaskDAO;

    public static TaskLocalDataSource getInstance(TaskDAO taskDAO) {
        if (sInstance == null) {
            sInstance = new TaskLocalDataSource(taskDAO);
        }
        return sInstance;
    }

    public TaskLocalDataSource(TaskDAO taskDAO) {
        mTaskDAO = taskDAO;
    }

    @Override
    public LiveData<Task> getTaskById(int taskId) {
        return mTaskDAO.getTaskById(taskId);
    }

    @Override
    public LiveData<List<Task>> getAllTask() {
        return mTaskDAO.getAllTask();
    }

    @Override
    public void addTask(Task task) {
        mTaskDAO.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        mTaskDAO.updateTask(task);
    }

    @Override
    public void deleteAllTask() {
        mTaskDAO.deleteAllTask();
    }
}
