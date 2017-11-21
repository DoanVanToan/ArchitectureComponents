package todo.toandoan.com.architecturecomponents.screen.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import java.util.List;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.data.source.TaskDataSource;
import todo.toandoan.com.architecturecomponents.screen.taskdetail.TaskDetailActivity;

public class MainViewModel extends AndroidViewModel {
    private TaskDataSource mTaskRepository;

    public final ObservableField<TaskAdapter> adapter = new ObservableField<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setTaskRepository(TaskDataSource taskRepository) {
        mTaskRepository = taskRepository;
        adapter.set(new TaskAdapter());
        adapter.get().setViewModel(this);
    }

    public LiveData<List<Task>> getTasks() {
        return mTaskRepository.getAllTask();
    }

    public void updateData(List<Task> tasks) {
        adapter.get().updateData(tasks);
    }

    public void onAddTaskClicked() {
        Task task = new Task();
        task.setDescription("toandoan architecture description  ");
        task.setName("Architecture Component ");
        mTaskRepository.addTask(task);
    }

    public void deleteAllTask() {
        mTaskRepository.deleteAllTask();
    }

    public void onCheckedChanged(Task task, boolean isSelected){
        task.setActive(isSelected);
        mTaskRepository.updateTask(task);
    }

    public void onItemClicked(Task task){
        getApplication().startActivity(TaskDetailActivity.getInstance(this.getApplication(), task));
    }
}
