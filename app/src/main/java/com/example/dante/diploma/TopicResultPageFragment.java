package com.example.dante.diploma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dante.diploma.Activities.UserStepResultsActivity;
import com.example.dante.diploma.Adapters.StepResultsAdapter;
import com.example.dante.diploma.UserInfo.TopicUserInfo;

public class TopicResultPageFragment extends Fragment {
    static final String TAG = "TopicResultPageFragment";

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String TOPIC_POSITION = "topic_position";
    static final String TOPIC_USER_INFO = "topic_user_info";

    int pageNumber;
    int topicPos;
    TopicUserInfo topicUserInfoX;

    public RecyclerView recyclerView;
    public StepResultsAdapter adapter;

    public static  TopicResultPageFragment newInstance(int page, TopicUserInfo topicUserInfo) throws NullPointerException{
        TopicResultPageFragment topicResultPageFragment = new TopicResultPageFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putInt(TOPIC_POSITION, topicUserInfo.getTopicNumber());
        arguments.putSerializable(TOPIC_USER_INFO, topicUserInfo);

        topicResultPageFragment.setArguments(arguments);



        return topicResultPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        topicPos = getArguments().getInt(TOPIC_POSITION);
        topicUserInfoX = (TopicUserInfo) getArguments().getSerializable(TOPIC_USER_INFO);

        Log.d(TAG, "onCreate");



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.topic_result_page, null);

        TextView textView = view.findViewById(R.id.tv_topic_results_number);

        recyclerView = view.findViewById(R.id.rv_topic_results);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new StepResultsAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setStepResults(topicUserInfoX.getStepUserInfos());


        textView.setText(Integer.toString(topicPos));
        Log.d(TAG," onCreateView: "+ Integer.toString(recyclerView.getAdapter().getItemCount()));


        return view;
    }
}
