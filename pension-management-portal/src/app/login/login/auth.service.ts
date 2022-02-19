import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { baseUrlAuth } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private http: HttpClient) { }

  generateToken(credentials: any) {
    return this.http.post(`${baseUrlAuth}authenticate`,credentials)
  }


  loginUser(token:string){
    localStorage.setItem("token",token);
    this.isLoggedIn();
    return true;
  }

  logout() {
    localStorage.removeItem('token');
    location.reload();
    return true;
  }

  getToken() {
    return localStorage.getItem("token");
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if(token==undefined || token===''||token==null) {
      return false;
    }
    else{
      return true;
    }
  }


}
