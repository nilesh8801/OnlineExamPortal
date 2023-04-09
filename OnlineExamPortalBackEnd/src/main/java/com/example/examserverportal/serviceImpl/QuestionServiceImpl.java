package com.example.examserverportal.serviceImpl;

import com.example.examserverportal.helper.IdNotFound;
import com.example.examserverportal.model.exam.Question;
import com.example.examserverportal.model.exam.Quiz;
import com.example.examserverportal.repository.QuestionRepository;
import com.example.examserverportal.repository.QuizRepository;
import com.example.examserverportal.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
 QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {

        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {

        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {

        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId)
    {
        Question quest= this.questionRepository.findById(questionId).
                orElseThrow(()-> new IdNotFound("Question", "id", "questionId"));
        return quest;
    }

    @Override
    public Set<Question> getQuestionQuiz(Quiz quiz) {

        return  this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public Question deleteQuestion(Long quesId) {
        Question question=this.questionRepository.findById(quesId).
                orElseThrow(()-> new IdNotFound("Question", "id", "questionId")); ;
        question.setQuesId(quesId);
        this.questionRepository.delete(question);
        return question;
    }

    @Override
    public Question get(Long questionId)
    {
        return this.questionRepository.getOne(questionId) ;
    }
}
