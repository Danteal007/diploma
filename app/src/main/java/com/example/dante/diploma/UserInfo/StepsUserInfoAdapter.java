package com.example.dante.diploma.UserInfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dante.diploma.Activities.StepsActivity;
import com.example.dante.diploma.R;

import java.util.ArrayList;

public class StepsUserInfoAdapter extends RecyclerView.Adapter<StepsUserInfoAdapter.StepResultsViewHolder> {

    private ArrayList<StepUserInfo> stepUserInfos;

    private Context context;
    private Intent stepsActivityIntent;

    public StepsUserInfoAdapter(Context context){
        this.context = context;
        stepsActivityIntent = new Intent(context, StepsActivity.class);
        stepUserInfos = new ArrayList<>();
    }

    public void addStepUserInfo(StepUserInfo stepUserInfo){
        stepUserInfos.add(stepUserInfo);
        notifyDataSetChanged();
    }

    public void setStepUserInfos(ArrayList<StepUserInfo> stepUserInfos){
        this.stepUserInfos.addAll(stepUserInfos);
        notifyDataSetChanged();
    }

    public void clear(){
        stepUserInfos.clear();
        notifyDataSetChanged();
    }

    @Override
    public StepResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_step_user_info, parent, false);

        return new StepResultsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StepResultsViewHolder holder, final int position) {
        holder.bind(stepUserInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return stepUserInfos.size();
    }

    static class StepResultsViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_StepResult;
        private ImageView iv_StepIcon;

        public StepResultsViewHolder(View itemView) {
            super(itemView);
            tv_StepResult = itemView.findViewById(R.id.tv_step_result);
            iv_StepIcon = itemView.findViewById(R.id.iv_step_icon);
        }

        public void bind(StepUserInfo stepUserInfo){
            tv_StepResult.setText(stepUserInfo.getCorrect() ? "Ok" : "WA");
            iv_StepIcon.setBackgroundColor(stepUserInfo.getCorrect() ? Color.GREEN : Color.RED);
        }
    }
}
