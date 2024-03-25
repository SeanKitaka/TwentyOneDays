package com.example.twentyonedays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.MyViewHolder> {

    Context context;
    ArrayList<HabitModel> habitmodel;
    public HabitAdapter(Context context, ArrayList<HabitModel> habitmodel){
        this.context = context;
        this.habitmodel = habitmodel;
    }

    @NonNull
    @Override
    public HabitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.habit_template, parent, false);
        return new HabitAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitAdapter.MyViewHolder holder, int position) {
        holder.name.setText(habitmodel.get(position).getHabitName());
        holder.type.setText(habitmodel.get(position).getHabitType());
        holder.freq.setText(habitmodel.get(position).getHabitFreq());
        holder.num.setText(habitmodel.get(position).getHabitNum());
    }

    @Override
    public int getItemCount() {
        return habitmodel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, freq, num;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Habit_name);
            type = itemView.findViewById(R.id.Habit_type);
            freq = itemView.findViewById(R.id.Habit_freq);
            num = itemView.findViewById(R.id.Habit_num);


        }
    }

    ;
}
