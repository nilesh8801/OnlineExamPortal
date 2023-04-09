package com.example.examserverportal.controller;

import com.example.examserverportal.model.exam.Category;
import com.example.examserverportal.model.exam.Quiz;
import com.example.examserverportal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    //add quiz service
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        Quiz quiz1= this.quizService.addQuiz(quiz);
        return new ResponseEntity<Quiz>(quiz1, HttpStatus.CREATED);
    }

    //update quiz
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
         Quiz quiz1= this.quizService.updateQuiz(quiz);
        return new ResponseEntity<Quiz>(quiz1, HttpStatus.OK);
    }

    // get quiz
    @GetMapping("/")
    public ResponseEntity<List<Quiz>> quizze(Quiz quiz){
        List<Quiz> quizzes = this.quizService.getQuizzes();
        return new ResponseEntity<List<Quiz>>(quizzes, HttpStatus.ACCEPTED);

    }

    //get single quiz
    @GetMapping("/{qid}")
    public ResponseEntity<Quiz> quiz(@PathVariable("qid") Long qid){
        Quiz quiz= this.quizService.getQuiz(qid);

        return  new ResponseEntity<Quiz>(quiz, HttpStatus.ACCEPTED);
    }

    //delete the quiz
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{qid}")
    public ResponseEntity<String> delete(@PathVariable("qid") Long qid){
        this.quizService.deleteQuiz(qid);
        return new ResponseEntity<String>("User deleted successfully...", HttpStatus.OK);
    }

      // particular category  all quiz
    @GetMapping("/category/{cid}")
    public ResponseEntity<List<Quiz>> getQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category=new Category();
        category.setCid(cid);
         return new ResponseEntity<List<Quiz>>(this.quizService.getQuizzesOfCategory(category), HttpStatus.OK);
    }

    //get active quizzes
    @GetMapping("/active")
    public ResponseEntity<List<Quiz>> getActiveQuizzes(){
        List<Quiz> quiz5=this.quizService.getActiveQuizzes();
        return new ResponseEntity<List<Quiz>>(quiz5, HttpStatus.OK);
    }




    //get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public ResponseEntity<List<Quiz>> getActiveQuizzes(@PathVariable("cid") Long cid){
        Category category=new Category();
        category.setCid(cid);
        return new ResponseEntity<List<Quiz>>(this.quizService.getActiveQuizzesOfCategory(category), HttpStatus.OK);
    }
}
