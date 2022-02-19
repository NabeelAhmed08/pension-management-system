import { Injectable, Injector } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from '@angular/common/http';

import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private injector: Injector) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

let authToken = this.injector.get(AuthService)
const reqHeader = req.clone({headers: req.headers.set('Authorization', `Bearer `+ authToken.getToken())});
if (authToken.isLoggedIn()==true){
  return next.handle(reqHeader);
}
else{
  return next.handle(req);
}





  }
}
