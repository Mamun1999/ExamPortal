import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  questions: any;
  qid: any;

 marksGot=0;
 correctAnswer=0;
 attempted=0;
 isSubmit=false;
 timer:any;

  constructor(private locationSt:LocationStrategy,
    private _route:ActivatedRoute,
    private _question:QuestionService
    ) { }

  ngOnInit(): void {
  this.preventBackbutton();
  this.qid=this._route.snapshot.params.qid;
  console.log(this.qid);
  this.loadQuestion();
  }
  loadQuestion(){
    this._question.getQuestionOfQuizForTest(this.qid).subscribe(
      (data:any)=>{
        this.questions=data;

        this.timer=this.questions.length * 2 * 60
        // this.questions.forEach((q:any) => 
        //   {
        //   q['givenAnswer']='';
        // });
        console.log(this.questions);
        this.startTimer();
      },
      (error)=>{
        Swal.fire('Error','error in loading Question of quiz','error');
        console.log(error);
      }
    )
  }
preventBackbutton(){
  history.pushState(null, '', location.href);
  this.locationSt.onPopState(() =>{
    history.pushState(null, '', location.href);
  });
}
submitQuiz(){
  Swal.fire({
    title: 'Do you want to submit the Quiz?',
   
    showCancelButton: true,
    confirmButtonText: 'Submit',
    
    icon:'info',
  }).then((e)=>{
    if(e.isConfirmed){
      //calculation
      this.evaluateQuiz()

      
    }
  });
}

startTimer(){
 let t= window.setInterval(()=>{
    if(this.timer<=0){
      this.evaluateQuiz();//Quiz will automatically submitted
      clearInterval(t);
    }else{
      this.timer--;
    }
  },1000)
}
getFormatedTime(){
let  minutes=Math.floor(this.timer/60)
let seconds=this.timer-minutes*60
return `${minutes} min : ${seconds} sec`;
}

evaluateQuiz(){

this._question.evaluateQuiz(this.questions).subscribe(
  (data:any)=>{
    console.log(data);
    this.marksGot=parseFloat(Number(data.marksGot).toFixed(2));
    this.correctAnswer=data.correctAnswer;
    this.attempted=data.attempted;
    this.isSubmit=true;
  },
  (error)=>{
    console.log('error');
  }

);

  // this.isSubmit=true; //Question will hide
  //     this.questions.forEach((q:any)=>{
  //       if(q.givenAnswer==q.answer){
  //         this.correctAnswer++
  //         let marksSingle=this.questions[0].quiz.maxMarks/this.questions.length
  //         this.marksGot +=marksSingle;
  //       }

  //       if(q.givenAnswer.trim()!=''){
  //         this.attempted++;
  //       }
        
  //     });
  //     console.log("Correct Answer: " +this.correctAnswer);
  //             console.log('MarksGot:' +this.marksGot);
  //             console.log("attempted: " +this.attempted);
}

printPage(){
  window.print();
}

}
