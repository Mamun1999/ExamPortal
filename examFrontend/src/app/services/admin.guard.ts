import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private login:LoginService,private router:Router){

  }
  canActivate(
    //those route will use this canActivate  those route will get the the access of this
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

     if(this.login.isLoggedIn() && this.login.getUserRole()=='Admin'){
       return true;
     }
      this.router.navigate(['login']);
    return false;
  }
  
}
