import { validateVerticalPosition } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService, private snack: MatSnackBar) { }

  ngOnInit(): void {}

  public user={
    username: '',
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    phone:'',

  };

    formSubmit()
    {
      console.log(this.user);
      if(this.user.username=='' || this.user.username=='null' )
      {
        // alert('username is required');

        this.snack.open("Username is required", '',
         {duration: 3000,
         verticalPosition: 'top',
         horizontalPosition:'right'
         });
        return ;
      }

      this.userService.adduser(this.user).subscribe(

        (data: any)=>{
           console.log(data);
          //  alert('success');
          Swal.fire('Successfully registered', 'Welcome ' +data.username,'success' )

        },
        (error)=>{
          //console.log(error);
          this.snack.open("User already exist",'',{
            duration:3000
          });
        }

      )

      
    }
    
 

}
