package com.example.dante.diploma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dante.diploma.Activities.MainActivity;
import com.example.dante.diploma.Activities.UserStepResultsActivity;
import com.example.dante.diploma.Course;
import com.example.dante.diploma.R;
import com.example.dante.diploma.Activities.TopicsActivity;
import com.example.dante.diploma.UserInfo.CourseUserInfo;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final String TAG = "CourseAdapter";

    private ArrayList<Course> courses;

    private Context context;
    private Intent topicActivityIntent;

    private int coursePosition;
    private CourseUserInfo courseUserInfo;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference currentUserReference = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid());

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
    public void onBindViewHolder(final CourseViewHolder holder, int position) {

        holder.bind(courses.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicActivityIntent.putExtra("Course", courses.get(holder.getAdapterPosition()));
                context.startActivity(topicActivityIntent);
            }
        });

        holder.btnShowCourseResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        DiplomaUserInfo diplomaUserInfo = dataSnapshot.getValue(DiplomaUserInfo.class);
                        Log.d(TAG, diplomaUserInfo.getName() + " " + holder.getAdapterPosition());
                        courseUserInfo = diplomaUserInfo.getCourseUserInfos().get(holder.getAdapterPosition());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(view.getContext(), UserStepResultsActivity.class);
                intent.putExtra("CourseUserInfo",courseUserInfo);
                view.getContext().startActivity(intent);
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
        private Button btnContinueCourse;
        private Button btnShowCourseResults;

        public CourseViewHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "CourseViewHolder: constructor working");

            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            btnContinueCourse = itemView.findViewById(R.id.btn_continue_course);
            btnShowCourseResults = itemView.findViewById(R.id.btn_show_course_results);


        }

        public void bind(Course course){
            tvCourseName.setText(course.getName());
            tvCourseName.setVisibility(View.VISIBLE);

        }


    }
}
