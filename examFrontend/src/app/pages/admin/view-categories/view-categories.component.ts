import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

categories=[
  {
    cid:'',
    title:'',
   
    description:'',
  },
  // {
  //   cid:'',
  //   title:'',
   
  //   description:'',
  // },
  // {
  //   cid:'',
  //   title:'',
  //   description:'',
  // }
]

  constructor(private _category:CategoryService,private _snack:MatSnackBar) { }
  //when category is loaded ngonInit will works
  ngOnInit(): void {
//this categories() is from --------------angular categoryservice class
this._category.categories().subscribe((data:any)=>{
  //success
  this.categories=data;
  //console.log(this.categories);
},
(error)=>{
  //console.log(error);
  Swal.fire("Error !!","Error in loading data",'error');
}
)
  }

  deleteCategory(cid: any) {
    Swal.fire({
      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      title: 'Are you sure , want to delete this question',

    }).then((result) => {

      if (result.isConfirmed) {
        this._category.deleteCategory(cid).subscribe(
          (data: any) => {
            this._snack.open('Deleted','',{
              duration:3000,
            });
            this.categories=this.categories.filter((q:any)=>q.cid!=cid);
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
