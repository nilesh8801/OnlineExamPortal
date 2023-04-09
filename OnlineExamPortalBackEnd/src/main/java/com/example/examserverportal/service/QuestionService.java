package com.example.examserverportal.service;

import com.example.examserverportal.model.exam.Question;
import com.example.examserverportal.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionQuiz(Quiz quiz);

    public  Question deleteQuestion(Long quesId);

    public Question get(Long questionId);
}
