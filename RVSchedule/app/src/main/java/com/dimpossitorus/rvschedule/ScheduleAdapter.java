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

    Vector<String> schedule;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView itemView) {
            super(itemView);
            textView = itemView;
            Log.d("DEBUG", "ViewHolder Constructor");
        }
    }

    public ScheduleAdapter (Vector<String> _schedule) {
        schedule = (Vector<String>) _schedule.clone();
        Log.d("DEBUG", "ScheduleAdapter Constructor");
        Log.d("DEBUG", schedule.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("DEBUG", "Entering onCreateViewHolder");
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);
        //view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder viewHolder = new ViewHolder(view);
        Log.d("DEBUG", "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("DEBUG", "onBindViewHolder");
        holder.textView.setText(schedule.get(position));
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

}
