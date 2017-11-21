package todo.toandoan.com.architecturecomponents.screen.taskdetail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import todo.toandoan.com.architecturecomponents.R;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.data.source.TaskRepository;
import todo.toandoan.com.architecturecomponents.data.source.local.TaskDataBase;
import todo.toandoan.com.architecturecomponents.data.source.local.TaskLocalDataSource;
import todo.toandoan.com.architecturecomponents.databinding.ActivityTaskDetailBinding;

public class TaskDetailActivity extends AppCompatActivity {

    private static final String BUNDLE_TASK = "BUNDLE_TASK";

    public static Intent getInstance(Context context, Task task){
        Intent intent = new Intent(context, TaskDetailActivity.class);
        intent.putExtra(BUNDLE_TASK, task);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Task task = getIntent().getExtras().getParcelable(BUNDLE_TASK);

        TaskRepository taskRepository;

        taskRepository = TaskRepository.getInstance(
            TaskLocalDataSource.getInstance(TaskDataBase.getInstance(this).taskDAO()));

        TaskDetailViewModel viewModel = ViewModelProviders.of(this)
            .get(TaskDetailViewModel.class);
        viewModel.setTask(task);
        viewModel.setTaskRepository(taskRepository);
        viewModel.setActivity(this);

        ActivityTaskDetailBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_task_detail);

        binding.setViewModel(viewModel);
        setSupportActionBar(binding.toolbar2);
        binding.toolbar2.setTitleTextColor(Color.WHITE);
        binding.toolbar2.setDrawingCacheBackgroundColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(task.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
