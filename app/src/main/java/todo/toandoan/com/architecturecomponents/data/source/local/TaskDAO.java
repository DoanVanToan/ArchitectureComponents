package todo.toandoan.com.architecturecomponents.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import todo.toandoan.com.architecturecomponents.data.model.Task;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTask();

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    LiveData<Task> getTaskById(int taskId);

    @Insert(onConflict = REPLACE)
    void addTask(Task task);

    @Query("DELETE FROM tasks")
    void deleteAllTask();

    @Update(onConflict = REPLACE)
    void updateTask(Task... tasks);
}
