import { 
    HttpEvent, 
    HttpHandler, 
    HttpInterceptor,
     HttpRequest,
      HTTP_INTERCEPTORS } 
      from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { LoginService } from "./login.service";


//const TOKEN_HEADER = 'Authorization';


// current-user is not public url thats why  accessing this url u need to send token with header.
//    for this we need intercepter. intercepter will take each request and will add token and header with request then only can access current usres
//   Angular provides many built-in tools to help scale out large JavaScript applications. Interceptors are one of the built-in tools for specifically handling HTTP requests at a global application level.s

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private login:LoginService){}

//we have created this interceptor because by this we can intercept httprequest and with each request we can add authorization header
    intercept
    (req: HttpRequest<any>,
         next: HttpHandler
         ): Observable<HttpEvent<any>> {

       // add the jwt token (LocalStrorage) request
let authReq=req;
const token=this.login.getToken();
if(token!=null){
       
authReq=authReq.clone({setHeaders:{Authorization: `Bearer ${token}`},
});
}

return next.handle(authReq);

    }

}

export const authInterceptorProviders=[
    {  
        //passing object
        provide:HTTP_INTERCEPTORS,
        useClass:AuthInterceptor,
        multi:true,

    },
];