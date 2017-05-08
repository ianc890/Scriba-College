package com.example.scriba.scribacollege.activity;

import android.widget.RadioButton;
import android.widget.TextView;

import com.example.scriba.scribacollege.R;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ian Cunningham
 */
public class QuizActivityTest {

    QuizActivity quizActivity;

    @Test
    public void showQuestionList() throws Exception {

        TextView questionTV = (TextView) quizActivity.findViewById(R.id.quiz_question);
        RadioButton optionOneTV = (RadioButton) quizActivity.findViewById(R.id.radio1);
        RadioButton optionTwoTV = (RadioButton) quizActivity.findViewById(R.id.radio2);
        RadioButton optionThreeTV = (RadioButton) quizActivity.findViewById(R.id.radio3);
        RadioButton optionFourTV = (RadioButton) quizActivity.findViewById(R.id.radio4);

        assertNotNull(questionTV);
        assertNotNull(optionOneTV);
        assertNotNull(optionTwoTV);
        assertNotNull(optionThreeTV);
        assertNotNull(optionFourTV);
    }

}