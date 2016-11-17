package ru.kackbip.impactMapping.screens.goals.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.api.projections.Goals;

/**
 * Created by ryashentsev on 09.10.2016.
 */

public class GoalsAdapter extends RecyclerView.Adapter {

    private List<Goals.Goal> goals = new ArrayList<>();

    public void setGoals(List<Goals.Goal> goals){
        this.goals = goals;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.goals_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Goals.Goal goal = goals.get(position);
        viewHolder.setTitle(goal.getTitle());
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        String dateString = viewHolder.itemView.getResources().getString(R.string.until, df.format(goal.getDate()));
        viewHolder.setDate(dateString);
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView date;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.goal_title);
            date = (TextView) view.findViewById(R.id.goal_date);
        }

        public void setTitle(String title){
            this.title.setText(title);
        }

        public void setDate(String date){
            this.date.setText(date);
        }
    }
}
