package com.mamun.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mamun.exam.model.exam.Question;
import com.mamun.exam.model.exam.Quiz;
import com.mamun.exam.service.QuestionService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
        // We will get data from url/ and then add those data and then get data using
        // requestbody and then put those data in Question question and then finally we
        // will return the data
    }

    // update the question

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));

    }

    // get all question of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
        }
        list.forEach((q)->{
            q.setAnswer("");//it will set answer null in client end 
        });


        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    // get all question
    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionOfQuiz);

    }

    // get single question
    @GetMapping("/{quesid}")
    public Question get(@PathVariable("quesid") Long quesid) {
        return this.questionService.getQuestion(quesid);
    }

    // delete question

    @DeleteMapping("/{quesid}")
    public void delete(@PathVariable("quesid") Long quesid) {
        this.questionService.deleteQuestion(quesid);
    }

    // evaluate quiz
    // @PostMapping("/eval-quiz")
    // public ResponseEntity <?> evaluateQuiz(@ResponseBody List<Question>
    // questions){
    // System.out.println(questions);
    // return ResponseEntity.ok("Got question with answer");
    // }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
       double marksGot = 0;
       int correctAnswer = 0;
       int attempted = 0;
        for(Question q: questions) {
            // System.out.println(q.getGivenAnswer());
            Question question = this.questionService.get(q.getQuesid());//got quesid with all info from database
            if (question.getAnswer().equals(q.getGivenAnswer())) { //checking database question with frontend
                // correct
                correctAnswer++;
                double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();

                marksGot+=marksSingle;
            } 
            
            if(q.getGivenAnswer()!=null || !q.getGivenAnswer().equals("")) {
                    attempted++;
            }
        }
        Map<String,Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
        return ResponseEntity.ok(map);
    }

}
