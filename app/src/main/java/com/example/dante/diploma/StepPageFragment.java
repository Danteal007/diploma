package com.example.dante.diploma;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.Step.Step;
import com.example.dante.diploma.UserInfo.CourseUserInfo;
import com.example.dante.diploma.UserInfo.DiplomaUserInfo;
import com.example.dante.diploma.UserInfo.StepUserInfo;
import com.example.dante.diploma.UserInfo.TopicUserInfo;
import com.example.dante.diploma.ViewHolders.Article;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference currentUserRef = firebaseDatabase.getReference("Users").child(mAuth.getCurrentUser().getUid());

    DiplomaUserInfo diplomaUserInfo;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.step_page, null);
        LinearLayout linearLayout = view.findViewById(R.id.ll_step_content);
        Button btn_checkAnswer = new Button(this.getContext());

        firebaseDatabase.
                getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //diplomaUserInfo = dataSnapshot.child(mAuth.getCurrentUser().getUid()).getValue(DiplomaUserInfo.class);
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
                tvStepPage.setWidth(article.textStyle);
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
                    linearLayout.addView(btn_checkAnswer);

                    final int finalCorrectAnswerIndex = correctAnswerIndex;

                    btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("", Integer.toString(checkedRadioButtonNumber)+ " " + Integer.toString(finalCorrectAnswerIndex));
                            if(checkedRadioButtonNumber == finalCorrectAnswerIndex){
                                //Сюда добавить логику зачета пользователю правильного ответа
                                CountCorrectAnswer(coursePos,topicPos,stepPos);
                            }
                        }
                    });
                }
                break;
            case Code:
                if(codeBlank!= null){
                    final EditText etCodeBlank = new EditText(this.getContext());
                    etCodeBlank.setText(codeBlank);
                    linearLayout.addView(etCodeBlank);
                    btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String output;

                            try {
                                output = new APITest().execute(etCodeBlank.getText().toString()).get();

                                if(output.contains(sampleOutput)){
                                    CountCorrectAnswer(coursePos, topicPos, stepPos);
                                }
                            }catch (Exception ex){

                            }


                        }
                    });

                    linearLayout.addView(btn_checkAnswer);
                }
                break;
        }
        //tvStepPage.setText(stepText);//Настроить форматирование текста, можно это сделать здесь


        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class APITest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String[] scripts) {
            String clientId = "ba69bd4e93d1491f5a85f6c229799993"; //Replace with your client ID
            String clientSecret = "5875ea69736ef71fd1b549ff03ad5dd7781e987368bc945d9c0f5816205d691b"; //Replace with your client Secret
            String script = scripts[0];
            String language = "csharp";
            String versionIndex = "1";

            String res = "";
            try {
                URL url = new URL("https://api.jdoodle.com/v1/execute");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + ModifyScript(script) +
                        "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

                System.out.println(input);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(input.getBytes());
                outputStream.flush();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
                }

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(
                        (connection.getInputStream())));

                String output;

                System.out.println("Output from JDoodle .... \n");
                while ((output = bufferedReader.readLine()) != null) {
                    System.out.println(output);
                    res = res+output;
                }

                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    public String ModifyScript(String script){
        String result = script.replace(System.getProperty("line.separator"),"");
        result = result.replace("\"","\\\"");
        return result;
    }

    public void CountCorrectAnswer(int coursePos, int topicPos, int stepPos){
        Log.d("", "onClick: Correct");

        currentUserRef.
                child("courseUserInfos").child(Integer.toString(coursePos)).
                child("topicUserInfos").child(Integer.toString(topicPos)).
                child("stepUserInfos").child(Integer.toString(stepPos)).
                child("correct").setValue(true);

        currentUserRef.
                child("courseUserInfos").child(Integer.toString(coursePos)).
                child("topicUserInfos").child(Integer.toString(topicPos)).
                child("stepUserInfos").child(Integer.toString(stepPos)).
                child("stepNumber").setValue(stepPos);

        currentUserRef.
                child("courseUserInfos").child(Integer.toString(coursePos)).
                child("topicUserInfos").child(Integer.toString(topicPos)).
                child("topicNumber").setValue(topicPos);
        Log.d("", "onClick " + topicPos);

        currentUserRef.
                child("courseUserInfos").child(Integer.toString(coursePos)).
                child("courseNumber").setValue(coursePos);
    }
}
