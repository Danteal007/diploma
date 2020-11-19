package com.example.dante.diploma.UserInfo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dante.diploma.R;

import java.util.ArrayList;

public class TopicsUserInfoAdapter extends RecyclerView.Adapter<TopicsUserInfoAdapter.TopicResultsViewHolder> {

    private final String TAG = "CourseAdapter";

    private ArrayList<TopicUserInfo> topicUserInfos;

    private Context context;

    private int coursePosition;

    public TopicsUserInfoAdapter(Context context){
        this.context = context;
        Log.d(TAG, "CourseAdapter: Adapter Created");
        topicUserInfos = new ArrayList<>();
    }

    public void setTopicUserInfos(ArrayList<TopicUserInfo> topicUserInfos){
        this.topicUserInfos.addAll(topicUserInfos);
        notifyDataSetChanged();
    }

    public void addTopicUserInfo(TopicUserInfo topicUserInfo){
        this.topicUserInfos.add(topicUserInfo);
        notifyDataSetChanged();
    }

    public void clear(){
        this.topicUserInfos.clear();
        notifyDataSetChanged();
    }

    @Override
    public TopicResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: VievHolder started creating");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_topic_user_info, parent, false);

        Log.d(TAG, "onCreateViewHolder: ViewHolder created");
        return new TopicResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopicResultsViewHolder holder, int position) {

        holder.bind(topicUserInfos.get(position));

        Log.d(TAG, "onBindViewHolder: " + topicUserInfos.get(position).getTopicNumber() );
    }

    @Override
    public int getItemCount() {
        return topicUserInfos.size();
    }

    static class TopicResultsViewHolder extends RecyclerView.ViewHolder {

        private final String TAG = "TopicResultsViewHolder";

        private TextView tvTopicPos;
        private RecyclerView rvStepsUserInfo;

        private StepsUserInfoAdapter stepResultsAdapter;

        public TopicResultsViewHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "CourseViewHolder: constructor working");

            tvTopicPos = itemView.findViewById(R.id.tv_topic_pos);

            InitRecyclerView(itemView);


        }

        public void bind(TopicUserInfo topicUserInfo){
            tvTopicPos.setText(Integer.toString(topicUserInfo.getTopicNumber()));
            stepResultsAdapter.setStepUserInfos(topicUserInfo.getStepUserInfos());
        }

        private void InitRecyclerView(View itemView){
            rvStepsUserInfo = itemView.findViewById(R.id.rv_steps_user_info);
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvStepsUserInfo.setLayoutManager(linearLayoutManager);
            stepResultsAdapter = new StepsUserInfoAdapter(itemView.getContext());
            rvStepsUserInfo.setAdapter(stepResultsAdapter);

        }


    }
}
