package com.example.scriba.scribacollege.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Ian Cunningham
 */
public class QuizQuestionTest {

    @Test
    public void getSubject() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "Distributed Systems";
        quizQuestion.setSubject("Distributed Systems");
        String output = quizQuestion.getSubject();
        assertEquals(expected, output);
    }

    @Test
    public void getQuestion() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "Which of the following is a falacie of distributed computing";
        quizQuestion.setQuestion("Which of the following is a falacie of distributed computing");
        String output = quizQuestion.getQuestion();
        assertEquals(expected, output);
    }

    @Test
    public void getOptionOne() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "The network is reliable";
        quizQuestion.setOptionOne("The network is reliable");
        String output = quizQuestion.getOptionOne();
        assertEquals(expected, output);
    }

    @Test
    public void getOptionTwo() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "The network is reliable all the time, whatever the situation";
        quizQuestion.setOptionTwo("The network is reliable all the time, whatever the situation");
        String output = quizQuestion.getOptionTwo();
        assertEquals(expected, output);
    }

    @Test
    public void getOptionThree() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "Topology always changes";
        quizQuestion.setOptionThree("Topology always changes");
        String output = quizQuestion.getOptionThree();
        assertEquals(expected, output);
    }

    @Test
    public void getOptionFour() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "There is two administrators";
        quizQuestion.setOptionFour("There is two administrators");
        String output = quizQuestion.getOptionFour();
        assertEquals(expected, output);
    }

    @Test
    public void getAnswer() throws Exception {
        QuizQuestion quizQuestion = new QuizQuestion();
        String expected = "The network is reliable";
        quizQuestion.setAnswer("The network is reliable");
        String output = quizQuestion.getAnswer();
        assertEquals(expected, output);
    }
}