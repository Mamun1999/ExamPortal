import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-question',
  templateUrl: './view-quiz-question.component.html',
  styleUrls: ['./view-quiz-question.component.css']
})
export class ViewQuizQuestionComponent implements OnInit {

  qid: any;
  qTitle: any;
  questions: any;
  constructor(private _route: ActivatedRoute,
    private _question: QuestionService,
    private _snack: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.qid = this._route.snapshot.params.qid;
    this.qTitle = this._route.snapshot.params.title;
    //  console.log(this.qid);
    //  console.log(this.qTitle);
    this._question.getQuestionOfQuiz(this.qid).subscribe(
      (data: any) => {
        console.log(data);
        this.questions = data;

      },
      (error) => {
        console.log(error);
      }
    );


  }
  //delete question
  deleteQuestion(qid: any) {
    Swal.fire({
      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      title: 'Are you sure , want to delete this question',

    }).then((result) => {

      if (result.isConfirmed) {
        this._question.deleteQuestion(qid).subscribe(
          (data: any) => {
            this._snack.open('Deleted','',{
              duration:3000,
            });
            this.questions=this.questions.filter((q:any)=>q.quesid!=qid);
          },

         (error)=>{
           this._snack.open('Error in Deleting','',{
             duration:3000,
           });
           console.log(error);
         }
         );
        }


    }
    );
  }

}
