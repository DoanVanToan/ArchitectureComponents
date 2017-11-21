package todo.toandoan.com.architecturecomponents.screen.taskdetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import todo.toandoan.com.architecturecomponents.R;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.data.source.TaskRepository;

public class TaskDetailViewModel extends AndroidViewModel {
    public ObservableField<Task> mTask = new ObservableField<>();
    private TaskRepository mTaskRepository;
    private TaskDetailActivity mActivity;

    public TaskDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        mTaskRepository = taskRepository;
    }

    public void setActivity(TaskDetailActivity activity) {
        mActivity = activity;
    }

    public ObservableField<Task> getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask.set(task);
    }

    public void onSaveTaskClicked(){
        mTaskRepository.updateTask(mTask.get());
        mActivity.finish();
    }

}
