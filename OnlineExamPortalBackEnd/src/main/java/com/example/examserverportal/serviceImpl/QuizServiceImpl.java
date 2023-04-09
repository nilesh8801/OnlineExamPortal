package com.example.examserverportal.serviceImpl;

import com.example.examserverportal.helper.IdNotFound;
import com.example.examserverportal.helper.NotFound;
import com.example.examserverportal.model.exam.Category;
import com.example.examserverportal.model.exam.Quiz;
import com.example.examserverportal.repository.QuizRepository;
import com.example.examserverportal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {


    @Autowired
    QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getQuizzes() {
        return new ArrayList<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        Quiz quiz= this.quizRepository.findById(quizId).
                orElseThrow(()-> new IdNotFound("Category", "id", "categoryId"));
        return quiz;
    }

    @Override
    public void deleteQuiz(Long quizId) {

   Quiz quiz1= this.quizRepository.findById(quizId).
                orElseThrow(() -> new IdNotFound("Category", "id", "categoryId"));
        this.quizRepository.deleteById(quizId);


    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {

        return this.quizRepository.findBycategory(category);
    }

// get active quizzes


    @Override
    public List<Quiz> getActiveQuizzes() {
       List<Quiz> quiz= this.quizRepository.findByActive(true);

       if(quiz.isEmpty()==true){
           throw new NotFound("No any active quiz present");
       }else {
           return quiz;
       }

    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category c) {
        return  this.quizRepository.findByCategoryAndActive(c, true);
    }





}

