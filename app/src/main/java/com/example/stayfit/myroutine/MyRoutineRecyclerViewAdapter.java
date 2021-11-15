package com.example.stayfit.myroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stayfit.R;
import com.example.stayfit.databinding.MyRoutineHeaderBinding;

import java.util.List;

public class MyRoutineRecyclerViewAdapter extends RecyclerView.Adapter<MyRoutineRecyclerViewAdapter.ViewHolder> {

    //    List<MyExerciseItemList> myListData = new ArrayList<>();
//    MyExerciseAdapter myExerciseAdapter;
    Context context;
    OnRoutineItemClickListener onRoutineItemClickListener;
    private List<MyRoutineItemList> itemListslList;

    public MyRoutineRecyclerViewAdapter(Context context, List<MyRoutineItemList> itemListslList) {
        this.itemListslList = itemListslList;
        this.context = context;
    }

    public MyRoutineRecyclerViewAdapter() {

    }



    public void setOnRoutineItemClickListener(OnRoutineItemClickListener onRoutineItemClickListener) {
        this.onRoutineItemClickListener = onRoutineItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRoutineHeaderBinding binding = MyRoutineHeaderBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyRoutineItemList list = itemListslList.get(position);
        holder.binding.tvTitle.setText(list.getTitle());
        holder.binding.ivExercise.setImageResource(list.getImg());
        holder.binding.btnAdd.setText(list.getBtnText());
        holder.binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.btnAdd.setBackgroundColor(ContextCompat.getColor(context, R.color.yelloColor));
                holder.binding.btnAdd.setText("Added");
                onRoutineItemClickListener.onAddClicked(position, list);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemListslList.size();
    }


    public interface OnRoutineItemClickListener {
        void onAddClicked(int position, MyRoutineItemList myRoutineItemList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyRoutineHeaderBinding binding;

        public ViewHolder(MyRoutineHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
