package com.example.dante.diploma.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCourseName;
    private Button btnContinueCourse;

    public CourseViewHolder(View itemView) {
        super(itemView);

        tvCourseName = itemView.findViewById(R.id.tv_course_name);
        btnContinueCourse = itemView.findViewById(R.id.btn_continue_course);
    }

    public void Bind(Course course){
        tvCourseName.setText(course.getName());
        tvCourseName.setVisibility(View.VISIBLE);

    }
}
