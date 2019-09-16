package com.example.linkedinproject;

import com.example.linkedinproject.model.Answer;
import com.example.linkedinproject.model.Answers;
import com.example.linkedinproject.model.Questions;

import java.util.ArrayList;

public class QAStoreManager {

    private Questions questions;
    private Answers qaAnswers;
    private static QAStoreManager qaStoreManager;

    public static QAStoreManager getInstance() {
        if (qaStoreManager == null) {
            qaStoreManager = new QAStoreManager();
        }
        return qaStoreManager;
    }

    private QAStoreManager() {
        qaAnswers = new Answers();
    }

    public void saveParseData(Questions questions) {
        this.questions = questions;
    }

    public  Questions getQuestions() {
        return questions;
    }

    public Answers getQaAnswers() {
        return qaAnswers;
    }

    public void SaveQuestionAnswer(Answer answer) {
        qaAnswers.answers.add(answer);
    }
}
