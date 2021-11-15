package com.example.stayfit.myexercise;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stayfit.R;
import com.example.stayfit.databinding.MyExerciseHeaderBinding;
import com.example.stayfit.myroutine.MyRoutineItemList;
import java.util.List;

public class MyExerciseAdapter extends RecyclerView.Adapter<MyExerciseAdapter.ViewHolder> {
    private List<MyRoutineItemList> itemLists;
    Context context;
    onStartClickListener onStartClickListener;

    public void setOnStartClickListener(MyExerciseAdapter.onStartClickListener onStartClickListener) {
        this.onStartClickListener = onStartClickListener;
    }

    public MyExerciseAdapter(List<MyRoutineItemList> itemLists,Context context) {
        this.context = context;
        this.itemLists = itemLists;

    }

    MyExerciseHeaderBinding binding;



    @Override
    public MyExerciseAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        binding = MyExerciseHeaderBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyExerciseAdapter.ViewHolder holder, int position) {
       MyRoutineItemList list = itemLists.get(position);
       holder.binding.tvExerciseTitle.setText(list.getTitle());
       holder.binding.ivExercise.setImageResource(list.getImg());
       holder.binding.btnStarted.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               holder.binding.btnStarted.setBackgroundColor(ContextCompat.getColor(context,R.color.red_Color));
               holder.binding.btnStarted.setText("STARTED");
               onStartClickListener.onAddClick(list.getImg(),list.getTitle());
           }
       });
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      MyExerciseHeaderBinding binding;
        public ViewHolder(MyExerciseHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
   public interface onStartClickListener{
      void onAddClick(int img, String title);
    }
}
