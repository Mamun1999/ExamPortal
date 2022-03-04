import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';
// import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';


@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  
//  public Editor = ClassicEditor;


  qid: any;
  qTitle: any;
 
  //we have not created  add-question model class thats why we have to initialize here

  question={
      quiz:{
        qid:21
      },
       content:'',
       option1:'',
       option2:'',
       option3:'',
       option4:'',
       answer:'',

  };
  
  constructor(private _route:ActivatedRoute,private _question:QuestionService) { }

  ngOnInit(): void {
    this.qid=this._route.snapshot.params.qid;
    this.qTitle=this._route.snapshot.params.title;//title is from app.ts module.route
    // console.log(this.qid);
   //this.question.quiz['qid']=this.qid;
   this.question.quiz['qid']=this.qid;

  }
  formSubmit(){
    //validate
    if(this.question.content.trim()=='' || this.question.content.trim()==null ){
      return;//Swal
    }
    if(this.question.option1.trim()=='' || this.question.option1.trim()==null ){
      return;//Swal
    }
    if(this.question.option2.trim()=='' || this.question.option2.trim()==null ){
      return;//Swal
    }
    if(this.question.answer.trim()=='' || this.question.answer.trim()==null ){
      return;//Swal
    }
    //formSubmit
    this._question.addQuestion(this.question).subscribe(
      (data:any)=>
    {
      Swal.fire('Success','Question Added Successfully,Add another one','success');
      this.question.content='';
      this.question.option1='';
      this.question.option2='';
      this.question.option3='';
      this.question.option4='';
    } , 
     (error)=>{
       Swal.fire('Error',"Question not added",'error');
     }
    );
  }

}
