package com.example.scriba.scribacollege.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ian Cunningham
 */

public class Quiz {

    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    private int score;

    public Quiz(List<QuizQuestion> quizQuestions, int score) {
        this.quizQuestions = quizQuestions;
        this.score = score;
    }

    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
