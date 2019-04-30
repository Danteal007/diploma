package com.example.dante.diploma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dante.diploma.Activities.MainActivity;
import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.example.dante.diploma.Activities.TopicsActivity;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final String TAG = "CourseAdapter";

    private ArrayList<Course> courses;

    private Context context;
    private Intent topicActivityIntent;

    public CourseAdapter(Context context){
        this.context = context;
        Log.d(TAG, "CourseAdapter: Adapter Created");
        courses = new ArrayList<>();

        topicActivityIntent = new Intent(context, TopicsActivity.class);
    }

    public void setCourses(ArrayList<Course> courses){
        this.courses.addAll(courses);
        notifyDataSetChanged();
    }

    public void addCourse(Course course){
        this.courses.add(course);
        notifyDataSetChanged();
    }

    public void clear(){
        this.courses.clear();
        notifyDataSetChanged();
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: VievHolder started creating");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_view_holder, parent, false);

        Log.d(TAG, "onCreateViewHolder: ViewHolder created");
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, final int position) {
        holder.bind(courses.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicActivityIntent.putExtra("Course", courses.get(position));
                context.startActivity(topicActivityIntent);
            }
        });
        Log.d(TAG, "onBindViewHolder: " + courses.get(position).getName() );
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {

        private final String TAG = "CourseViewHolder";

        private TextView tvCourseName;
        //private Button btnContinueCourse;

        public CourseViewHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "CourseViewHolder: constructor working");

            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            //btnContinueCourse = itemView.findViewById(R.id.btn_continue_course);
        }

        public void bind(Course course){
            tvCourseName.setText(course.getName());
            tvCourseName.setVisibility(View.VISIBLE);

        }


    }
}
