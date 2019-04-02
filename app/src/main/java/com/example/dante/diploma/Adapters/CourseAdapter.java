package com.example.dante.diploma.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.example.dante.diploma.ViewHolders.CourseViewHolder;

import java.util.ArrayList;
import java.util.Collection;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    private final String TAG = "CourseAdapter";

    public ArrayList<Course> courses = new ArrayList<Course>();

    public void setCourses(ArrayList<Course> courses){
        this.courses.addAll(courses);
        notifyDataSetChanged();
        //Log.d(TAG, "setCourses: " + this.courses.get(0).getName());
    }

    public void AddCourse(Course course){
        //Log.d(TAG, "AddCourse: " + course.getName());
        courses.add(course);
    }

    public void clearCoursesList(){
        this.courses.clear();
        notifyDataSetChanged();
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_view_holder, parent, false);
        Log.d(TAG, "onCreateViewHolder: ViewHolder created");
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.Bind(courses.get(position));
        Log.d(TAG, "onBindViewHolder: " + courses.get(position).getName() );
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
