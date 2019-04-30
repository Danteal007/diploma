package com.example.dante.diploma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dante.diploma.Step.Quiz.QuizItem;
import com.example.dante.diploma.Step.Step;
import com.example.dante.diploma.ViewHolders.Article;

import java.util.ArrayList;

public class StepPageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String STEP_TEXT = "text";
    static final String WIN_POINTS = "winpoints";
    static final String CODE_BLANK = "codeblank";
    static final String QUIZ_ITEMS = "quizitems";

    int pageNumber;
    ArrayList<Article> stepText;
    int winPoints;
    String codeBlank;
    ArrayList<QuizItem> quizItems;

    public static  StepPageFragment newInstance(int page, Step step) throws NullPointerException {
        StepPageFragment stepPageFragment = new StepPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putSerializable(STEP_TEXT, step.getText());
        arguments.putInt(WIN_POINTS, step.getWinPoints());
        arguments.putString(CODE_BLANK, step.getCodeBlank());
        arguments.putSerializable(QUIZ_ITEMS, step.getQuizItems());

        stepPageFragment.setArguments(arguments);
        return stepPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        stepText = (ArrayList<Article>) getArguments().getSerializable(STEP_TEXT);
        winPoints = getArguments().getInt(WIN_POINTS);
        codeBlank = getArguments().getString(CODE_BLANK);
        quizItems = (ArrayList<QuizItem>) getArguments().getSerializable(QUIZ_ITEMS);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.step_page, null);
        LinearLayout linearLayout = view.findViewById(R.id.ll_step_content);
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
        //tvStepPage.setText(stepText);//Настроить форматирование текста, можно это сделать здесь
        if (quizItems != null){

            int correctAnswerIndex = 0;

            Button btn_checkAnswer = new Button(this.getContext());
            final RadioGroup rg_quiz = new RadioGroup(this.getContext());
            ArrayList<RadioButton> rb_answers = new ArrayList<>();

            for (QuizItem quizItem : quizItems){

                if(quizItem.isCorrect()){
                    correctAnswerIndex = quizItems.indexOf(quizItem);
                }

                RadioButton rb_answer = new RadioButton(this.getContext());
                rb_answer.setText(quizItem.getAnswerVariant());
                rg_quiz.addView(rb_answer);
            }
            linearLayout.addView(rg_quiz);

            final int finalCorrectAnswerIndex = correctAnswerIndex;
            btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rg_quiz.getCheckedRadioButtonId() == finalCorrectAnswerIndex){
                        //Сюда добавить логику зачета пользователю правильного ответа
                    }
                }
            });
        }

        return view;
    }
}
