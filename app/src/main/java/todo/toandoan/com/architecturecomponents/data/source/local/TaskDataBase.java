package todo.toandoan.com.architecturecomponents.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import todo.toandoan.com.architecturecomponents.data.model.Task;

import static todo.toandoan.com.architecturecomponents.data.source.local.TaskDataBase
    .DATABASE_VERSION;

@Database(entities = { Task.class }, version = DATABASE_VERSION, exportSchema = false)
public abstract class TaskDataBase extends RoomDatabase {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "TaskDB";

    public abstract TaskDAO taskDAO();

    private static TaskDataBase sInstance;

    public static TaskDataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, TaskDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        }
        return sInstance;
    }
}
