package todo.toandoan.com.architecturecomponents.screen.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import todo.toandoan.com.architecturecomponents.R;
import todo.toandoan.com.architecturecomponents.data.model.Task;
import todo.toandoan.com.architecturecomponents.databinding.ItemTaskBinding;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTasks;
    private MainViewModel mViewModel;

    public TaskAdapter() {
        mTasks = new ArrayList<>();
    }

    public void updateData(List<Task> tasks) {
        if (tasks == null) {
            return;
        }
        mTasks.clear();
        mTasks.addAll(tasks);
        notifyDataSetChanged();
    }

    public void setViewModel(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTaskBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.item_task, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTasks != null ? mTasks.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTaskBinding mBinding;

        public ViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindData(Task task) {
            mBinding.setTask(task);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}
