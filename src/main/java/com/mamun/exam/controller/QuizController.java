package com.mamun.exam.controller;

import java.util.List;

import com.mamun.exam.model.exam.Category;
import com.mamun.exam.model.exam.Quiz;
import com.mamun.exam.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){ //Using @requestBody we can get the data
        return ResponseEntity.ok(this.quizService.addQuiz(quiz)); //this addQuiz method is from quizService
        
    }

    //updateQuiz

    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
     return ResponseEntity.ok(this.quizService.getQuizzes());

    }
    //get single quiz
    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid){
        return this.quizService.getQuiz(qid);
    }
//Data will store in Long qid from /qid
    //delete
    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid")Long qid)
    {
    this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesofcategory(@PathVariable("cid")Long cid){
        Category category=new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }
    //get active quizzes

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }
//get active quizzes by category
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cid")Long cid){
        Category category=new Category();
        category.setCid(cid);;
        return this.quizService.getActiveQuizzesOfCategory(category);
    }


}
