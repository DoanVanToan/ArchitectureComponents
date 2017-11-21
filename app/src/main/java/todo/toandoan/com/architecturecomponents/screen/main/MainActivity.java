package todo.toandoan.com.architecturecomponents.screen.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;
import todo.toandoan.com.architecturecomponents.R;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.data.source.TaskRepository;
import todo.toandoan.com.architecturecomponents.data.source.local.TaskDataBase;
import todo.toandoan.com.architecturecomponents.data.source.local.TaskLocalDataSource;
import todo.toandoan.com.architecturecomponents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskRepository taskRepository;

        taskRepository = TaskRepository.getInstance(
            TaskLocalDataSource.getInstance(TaskDataBase.getInstance(this).taskDAO()));

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.setTaskRepository(taskRepository);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mViewModel);

        mViewModel.getTasks().
            observe(this, new Observer<List<Task>>() {
                @Override
                public void onChanged(@Nullable List<Task> tasks) {
                    mViewModel.updateData(tasks);
                }
            });

        binding.toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                mViewModel.deleteAllTask();
                break;
        }
        return true;
    }
}
