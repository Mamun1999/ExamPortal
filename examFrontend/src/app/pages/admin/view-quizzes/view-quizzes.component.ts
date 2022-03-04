import { Component, OnInit } from '@angular/core';

import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes = [
    {
      qid: 23,
      title: 'Spring Boot Quiz',
      description: 'Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications. ',
      maxMarks: '50',
      numberOfQuestion: '20',
      active: '',
      category: {
        title: 'programming',
      }
    },
    {
      qid: 23,
      title: 'Spring Boot Quiz',
      description: 'Spring Boot is an open source Java-based framework used to create a micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications. ',
      maxMarks: '50',
      numberOfQuestion: '',
      active: '',
      category: {
        title: 'programming',
      }
    },
  ];


  constructor(private _quiz: QuizService) { }

  ngOnInit(): void {
    this._quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data;//this quizzes is form this class quizzes
        console.log(this.quizzes);
      },

      (error) => {
        console.log(error);
        Swal.fire('Error', 'Error in loading data', 'error');
      }
    );
  }
  //delete quiz
  deleteQuiz(qid: any) {
   Swal.fire({
     icon:'info',
     title:'Are you sure?',
     confirmButtonText:'Delete',
     showCancelButton:true,
   }).then((result)=>{
     if(result.isConfirmed){
       //delete
       this._quiz.deleteQuiz(qid).subscribe(
        (data:any)=>{
        this.quizzes=  this.quizzes.filter((quiz)=>quiz.qid!=qid);
          Swal.fire('Deleted','Quiz deleted successfully','success');
        },
        (error)=>{
          Swal.fire('error','Quiz is not delete','error');
        }
      );
     }
   });
  }

}
