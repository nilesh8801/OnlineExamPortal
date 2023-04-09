package com.example.examserverportal.controller;


import com.example.examserverportal.model.exam.Question;
import com.example.examserverportal.model.exam.Quiz;
import com.example.examserverportal.service.QuestionService;
import com.example.examserverportal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuizService  quizService;

    @Autowired
    private QuestionService questionService;

    //add question
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update the question
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get all question of quid
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){
      //  Quiz quiz = new Quiz();
       // quiz.setqId(qid);
       // Set<Question> questionsofQuiz= this.questionService.getQuestionQuiz(quiz);
       // return ResponseEntity.ok(questionsofQuiz);

        Quiz quiz= this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();
        List list= new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberofQuestion())){
            list=list.subList(0, Integer.parseInt(quiz.getNumberofQuestion()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //gell all question for admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsofQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsofQuiz = this.questionService.getQuestionQuiz(quiz);
        return ResponseEntity.ok(questionsofQuiz);
    }



    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        Question question= this.questionService.getQuestion(quesId);

        return  question;
    }

    //delete question
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }


    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> question){

        System.out.println(question);
   double marksGot=0;
   int correctAnswers=0;
   int attempted=0;

   for(Question q:question){
       //single question
       Question question1=this.questionService.get(q.getQuesId());
       if(question1.getAnswer().equals(q.getGivenAnswer())){
       //correct
           correctAnswers++;

       double marksSingle=Double.parseDouble(question.get(0).getQuiz().getMaxMarks())/ question.size();
        marksGot += marksSingle;
   }

    if(q.getGivenAnswer()!=null ){
        attempted++;
    }

    }
        Map<String, Object> map = Map.of("marksGot",marksGot, "correctAnswers",correctAnswers, "attempted",attempted);
        return ResponseEntity.ok(map);

    }
}
