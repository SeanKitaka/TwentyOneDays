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


    private final RecyclerInterface recyclerInterface;
    Context context;
    ArrayList<HabitModel> habitmodel;
    public HabitAdapter(Context context, ArrayList<HabitModel> habitmodel, RecyclerInterface recyclerInterface){
        this.context = context;
        this.habitmodel = habitmodel;
        this.recyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public HabitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.habit_template, parent, false);
        return new HabitAdapter.MyViewHolder(view,recyclerInterface);
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
        public MyViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);

            name = itemView.findViewById(R.id.Habit_name);
            type = itemView.findViewById(R.id.Habit_type);
            freq = itemView.findViewById(R.id.Habit_freq);
            num = itemView.findViewById(R.id.Habit_num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }
    }

    ;
}
