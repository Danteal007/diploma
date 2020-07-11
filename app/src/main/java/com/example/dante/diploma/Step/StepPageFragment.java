package com.example.dante.diploma.Step;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dante.diploma.CommonUtils;
import com.example.dante.diploma.FirebaseUtils;
import com.example.dante.diploma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StepPageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String COURSE_POSITION = "course_position";
    static final String TOPIC_POSITION = "topic_position";
    static final String STEP_POSITION = "step_position";
    static final String STEP_TEXT = "text";
    static final String STEP_TYPE = "steptype";
    static final String CODE_BLANK = "codeblank";
    static final String QUIZ_ITEMS = "quizitems";
    static final String SAMPLE_OUTPUT = "sample_output";

    int checkedRadioButtonNumber = -1;

    int pageNumber;
    int coursePos;
    int topicPos;
    int stepPos;
    ArrayList<Article> stepText;
    String codeBlank;
    String sampleOutput;
    ArrayList<QuizItem> quizItems;
    StepType stepType;

    LinearLayout linearLayout;
    Button btn_checkAnswer;
    ViewPager vp;

    public static  StepPageFragment newInstance(int page, Step step) throws NullPointerException {
        StepPageFragment stepPageFragment = new StepPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putInt(COURSE_POSITION, step.getCoursePos());
        arguments.putInt(TOPIC_POSITION, step.getTopicPos());
        arguments.putInt(STEP_POSITION, step.getStepPos());
        arguments.putSerializable(STEP_TEXT, step.getText());
        arguments.putSerializable(STEP_TYPE, step.getStepType());
        switch (step.getStepType()){
            case Quiz:
                arguments.putSerializable(QUIZ_ITEMS, step.getQuizItems());
                break;
            case Code:
                arguments.putString(CODE_BLANK, step.getCodeBlank());
                arguments.putString(SAMPLE_OUTPUT, step.getSampleOutput());
                break;
        }


        stepPageFragment.setArguments(arguments);
        return stepPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        coursePos = getArguments().getInt(COURSE_POSITION);
        topicPos = getArguments().getInt(TOPIC_POSITION);
        stepPos = getArguments().getInt(STEP_POSITION);
        stepText = (ArrayList<Article>) getArguments().getSerializable(STEP_TEXT);
        stepType = (StepType)getArguments().getSerializable(STEP_TYPE);
        switch (stepType){
            case Quiz:
                quizItems = (ArrayList<QuizItem>) getArguments().getSerializable(QUIZ_ITEMS);
                break;
            case Code:
                codeBlank = getArguments().getString(CODE_BLANK);
                sampleOutput = getArguments().getString(SAMPLE_OUTPUT);
                break;
        }



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.step_page, null);
        linearLayout = view.findViewById(R.id.ll_step_content);
        btn_checkAnswer = new Button(this.getContext());


        vp = (ViewPager)container;

        FirebaseUtils.getInstance().getUsersRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(stepText != null) {
            Log.d("", "onCreateView: " + (stepText == null));
            for (Article article : stepText) {
                TextView tvStepPage = new TextView(this.getContext());
                tvStepPage.setText(article.text);
                tvStepPage.setTextSize(article.textSize);
                tvStepPage.setTextColor(Color.BLACK);
                tvStepPage.setLayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                int style = article.textStyle;
                tvStepPage.setPadding(20,20,20,20);
                switch (style){
                    case 0:
                        tvStepPage.setTypeface(Typeface.DEFAULT);
                        break;
                    case 1:
                        tvStepPage.setTypeface(Typeface.DEFAULT_BOLD);
                        break;
                    case 4:
                        tvStepPage.setTypeface(Typeface.MONOSPACE);
                        tvStepPage.setBackgroundColor(Color.LTGRAY);
                        break;
                    case 5:
                        tvStepPage.setTypeface(Typeface.SANS_SERIF);
                        break;
                }
                linearLayout.addView(tvStepPage);
            }

        }
        switch (stepType){
            case Quiz:
                if (quizItems != null){

                    int correctAnswerIndex = 0;


                    final RadioGroup rg_quiz = new RadioGroup(this.getContext());
                    final ArrayList<RadioButton> rb_answers = new ArrayList<>();

                    for (QuizItem quizItem : quizItems){

                        if(quizItem.isCorrect()){
                            correctAnswerIndex = quizItems.indexOf(quizItem);
                        }

                        RadioButton rb_answer = new RadioButton(this.getContext());
                        rb_answer.setText(quizItem.getAnswerVariant());
                        rb_answers.add(rb_answer);
                        rg_quiz.addView(rb_answer);
                    }

                    for(int i = 0; i < rb_answers.size(); i++){
                        final int finalI = i;
                        rb_answers.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                                if(isChecked){
                                    checkedRadioButtonNumber = finalI;
                                }
                            }
                        });
                    }

                    linearLayout.addView(rg_quiz);

                    final int finalCorrectAnswerIndex = correctAnswerIndex;

                    btn_checkAnswer.setText("Проверить");

                    btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("", Integer.toString(checkedRadioButtonNumber)+ " " + Integer.toString(finalCorrectAnswerIndex));
                            if(checkedRadioButtonNumber == finalCorrectAnswerIndex){
                                //Сюда добавить логику зачета пользователю правильного ответа
                                CountCorrectAnswer(coursePos,topicPos,stepPos);
                            }
                            else {
                                NotifyAnswerIsNotCorrect();
                            }
                        }
                    });
                }
                linearLayout.addView(btn_checkAnswer);
                break;
            case Code:
                if(codeBlank!= null){
                    final EditText etCodeBlank = new EditText(this.getContext());
                    etCodeBlank.setText(codeBlank);
                    etCodeBlank.setTypeface(Typeface.MONOSPACE);
                    linearLayout.addView(etCodeBlank);

                    btn_checkAnswer.setText("Проверить");
                    btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String output;

                            try {
                                output = new CommonUtils.JDoodleAPI().execute(etCodeBlank.getText().toString()).get();

                                if(output.contains(sampleOutput)){
                                    CountCorrectAnswer(coursePos, topicPos, stepPos);
                                }
                                else {
                                    NotifyAnswerIsNotCorrect();
                                }
                            }catch (Exception ex){

                            }


                        }
                    });

                }
                linearLayout.addView(btn_checkAnswer);
                break;
        }



        return view;
    }

    private void NotifyAnswerIsNotCorrect() {
        btn_checkAnswer.setText("Неверно! Попробовать снова.");
        btn_checkAnswer.setBackgroundColor(Color.RED);
    }

    public void CountCorrectAnswer(int coursePos, int topicPos, final int stepPos){
        Log.d("", "onClick: Correct");

        btn_checkAnswer.setText("Верно!!!");
        btn_checkAnswer.setBackgroundColor(Color.GREEN);
        btn_checkAnswer.setTextColor(Color.WHITE);

        try {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    vp.setCurrentItem(stepPos + 1);
                }
            }, 2000);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

        FirebaseUtils.getInstance().getStepUserInfos(coursePos, topicPos, stepPos).
                child("correct").setValue(true);

        FirebaseUtils.getInstance().getStepUserInfos(coursePos, topicPos, stepPos).
                child("stepNumber").setValue(stepPos);

        FirebaseUtils.getInstance().getTopicUserInfos(coursePos, topicPos).
                child("topicNumber").setValue(topicPos);
        Log.d("", "onClick " + topicPos);

        FirebaseUtils.getInstance().getCourseUserInfos(coursePos).
                child("courseNumber").setValue(coursePos);
    }


}
