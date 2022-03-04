import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http:HttpClient) { }

  public quizzes(){
    return this._http.get(`${baseUrl}/quiz/`);
  }
  public addQuiz(quiz: { title: string; description: string; maxMarks: string; numberOfQuestion: string; active: boolean; category: { cid: string; }; }){
    return this._http.post(`${baseUrl}/quiz/`,quiz);
  }

  public deleteQuiz(qid: any){
   return this._http.delete(`${baseUrl}/quiz/${qid}`);
  }

  //get single quiz
  public getQuiz(qid: any){
    return this._http.get(`${baseUrl}/quiz/${qid}`);
    //we got single quiz
    //return observable then we have to subscribe in our component class
  }
  public updateQuiz(quiz: any){
   return  this._http.put(`${baseUrl}/quiz/`,quiz);
  }

  //get Quizzes of category
  public getQuizzesOfCategory(cid: any){
    return this._http.get(`${baseUrl}/quiz/category/${cid}`);
  }

  //get Active quizzes
  public getActiveQuizzes(){
    return this._http.get(`${baseUrl}/quiz/active`);

  }

  //get Active quizzes of category

  public getActiveQuizzesOfCategory(cid: any){
    return this._http.get(`${baseUrl}/quiz/category/active/${cid}`);
  }

}
