package com.example.linkedinproject.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.example.linkedinproject.QAStoreManager;
import com.example.linkedinproject.model.Answer;
import com.example.linkedinproject.model.Questions;

public class VmQuestion {

    private int questionIndex = 0;
    private Questions questions;
    private ObservableBoolean isLastQuestion = new ObservableBoolean(false);
    private ObservableField<String> question = new ObservableField<>();

    public VmQuestion() {
        questions =  QAStoreManager.getInstance().getQuestions();
        question.set(questions.questions.get(questionIndex).question);
    }

    public ObservableField<String> getQuestion() {
        return question;
    }

    public ObservableBoolean getIsLastQuestion() {
        return isLastQuestion;
    }

    public void setNextQuestion() {
        questionIndex++;
        question.set(questions.questions.get(questionIndex).question);

        if (questionIndex == questions.questions.size() - 1) {
            isLastQuestion.set(true);
        }
    }

    public void saveAnswer(Answer answer) {
        QAStoreManager.getInstance().SaveQuestionAnswer(answer);
    }
}
