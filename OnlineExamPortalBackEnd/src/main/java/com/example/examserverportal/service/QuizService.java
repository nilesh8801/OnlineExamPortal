package com.example.examserverportal.service;

import com.example.examserverportal.model.exam.Category;
import com.example.examserverportal.model.exam.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public List<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);


   public List<Quiz> getQuizzesOfCategory(Category category);

   public List<Quiz> getActiveQuizzes();

   public List<Quiz> getActiveQuizzesOfCategory(Category c);
}