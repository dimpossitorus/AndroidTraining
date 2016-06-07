package com.dimpossitorus.rvschedule;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

/**
 * Created by Dimpos Sitorus on 06/06/2016.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    Vector<Schedule> schedule;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.taskTitle);
            description = (TextView) itemView.findViewById(R.id.description);

            Log.d("DEBUG", "ViewHolder Constructor");
        }
    }

    public ScheduleAdapter (Vector<Schedule> _schedule) {
        schedule = (Vector<Schedule>) _schedule.clone();
        Log.d("DEBUG", "ScheduleAdapter Constructor");
        Log.d("DEBUG", schedule.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("DEBUG", "Entering onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.d("DEBUG", "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("DEBUG", "onBindViewHolder");
        holder.title.setText(schedule.get(position).getTitle());
        holder.description.setText(schedule.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

}
