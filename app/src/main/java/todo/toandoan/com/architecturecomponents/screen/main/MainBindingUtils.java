package todo.toandoan.com.architecturecomponents.screen.main;

import android.arch.lifecycle.LiveData;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainBindingUtils {
    @BindingAdapter("adapter")
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter){
        view.setAdapter(adapter);
    }

    @BindingAdapter("isSelected")
    public static void setCheckBoxSelected(CheckBox view, boolean isSelected){
        view.setChecked(isSelected);
    }


    @BindingAdapter("listenner")
    public static void setCheckBoxListenner(CheckBox view,
        CompoundButton.OnCheckedChangeListener listener){
        view.setOnCheckedChangeListener(listener);
    }
}
