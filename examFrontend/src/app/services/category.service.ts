import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) { }

  //load all category

  public categories(){
    return this._http.get(`${baseUrl}/category/`);//this category is from server url /category
  }

  //add category
  public addCategory(category:{ title: string; description: string; }){ //or any
    return this._http.post(`${baseUrl}/category/`,category);
  }

  //deletecategory
  public deleteCategory(cid:any){
    return this._http.delete(`${baseUrl}/category/${cid}`);
  }
}
