package com.example.dante.diploma.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dante.diploma.R;
import com.example.dante.diploma.Topic;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<Topic> topicArrayList;

    public TopicAdapter(){
        topicArrayList = new ArrayList<>();
    }

    public void addTopic(Topic topic){
        topicArrayList.add(topic);
        notifyDataSetChanged();
    }

    public void setTopics(ArrayList<Topic> topics){
        topicArrayList.addAll(topics);
        notifyDataSetChanged();
    }

    public void clear(){
        topicArrayList.clear();
        notifyDataSetChanged();
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_view_holder, parent, false);

        return new TopicViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        holder.bind(topicArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTopicName;

        public TopicViewHolder(View itemView) {
            super(itemView);
            tvTopicName = itemView.findViewById(R.id.tv_topic_name);
        }

        public void bind(Topic topic){
            tvTopicName.setText(topic.getName());
        }
    }
}
