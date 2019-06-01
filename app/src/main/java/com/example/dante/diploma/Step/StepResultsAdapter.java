package com.example.dante.diploma.Step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dante.diploma.R;
import com.example.dante.diploma.UserInfo.StepUserInfo;

import java.util.ArrayList;

public class StepResultsAdapter extends RecyclerView.Adapter<StepResultsAdapter.StepResultViewHolder>{

    private final String TAG = "StepResultsAdapter";

    private ArrayList<StepUserInfo> stepUserInfos;

    private Context context;

    public StepResultsAdapter(){
        stepUserInfos = new ArrayList<>();
    }

    public void setStepResults(ArrayList<StepUserInfo> stepUserInfos){

        this.stepUserInfos.addAll(stepUserInfos);

        notifyDataSetChanged();
    }

    public ArrayList<StepUserInfo> getStepResults(){
        return stepUserInfos;
    }

    @Override
    public StepResultsAdapter.StepResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_result_view_holder, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new StepResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepResultsAdapter.StepResultViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return stepUserInfos.size();
    }

    public class StepResultViewHolder extends RecyclerView.ViewHolder {

        TextView tvStepResultNumber;
        TextView tvStepResult;

        public StepResultViewHolder(View itemView) {
            super(itemView);
            tvStepResultNumber = itemView.findViewById(R.id.tv_step_result_number);
            tvStepResult = itemView.findViewById(R.id.tv_step_result);
        }

        public void bind(int holderPosition){
            String stepNumber = "Шаг №" + Integer.toString(holderPosition+1);
            String stepResult = " - Не пройден";

            if(stepUserInfos.get(holderPosition) != null) {
                Log.d(TAG, "stepUserInfos.get(holderPosition) != null");
                Log.d(TAG, "bind: stepNumber -  "+ stepUserInfos.get(holderPosition).getStepNumber() + ", correct " + stepUserInfos.get(holderPosition).getCorrect());

                stepResult = stepUserInfos.get(holderPosition).getCorrect() ? " - Пройден" : " - Не пройден";
            }
            tvStepResultNumber.setText(stepNumber);
            tvStepResult.setText(stepResult);

        }
    }
}
