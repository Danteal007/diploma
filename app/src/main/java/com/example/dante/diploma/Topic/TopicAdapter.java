package com.example.dante.diploma.Topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dante.diploma.Activities.StepsActivity;
import com.example.dante.diploma.R;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private ArrayList<Topic> topicArrayList;

    private Context context;
    private Intent stepsActivityIntent;

    public TopicAdapter(Context context){
        this.context = context;
        stepsActivityIntent = new Intent(context, StepsActivity.class);
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
    public void onBindViewHolder(TopicViewHolder holder, final int position) {
        holder.bind(topicArrayList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topicArrayList.get(position).getSteps() != null) {
                    stepsActivityIntent.putExtra("Topic", topicArrayList.get(position));
                    context.startActivity(stepsActivityIntent);
                }else {
                    Toast.makeText(context, "Раздел в разработке",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_TopicName;
        private TextView tv_TopicNumber;

        public TopicViewHolder(View itemView) {
            super(itemView);
            tv_TopicName = itemView.findViewById(R.id.tv_topic_name);
            tv_TopicNumber = itemView.findViewById(R.id.tv_topic_number);
        }

        public void bind(Topic topic){
            tv_TopicName.setText(topic.getName());
            tv_TopicNumber.setText("Тема №" + String.valueOf(topic.getTopicPos()));
        }
    }
}
