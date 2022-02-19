import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
 cred: any;
 authResponse:any;
 isLoginMode = true;
  isLoading = false;
  error: string = '';
  loginForm: FormGroup = new FormGroup({});

  constructor(private authService: AuthService  ,private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      'userName': new FormControl('',[Validators.required,Validators.minLength(3)]   ),
      'password': new FormControl('',[Validators.required,Validators.minLength(3)]   ),
    })
  }

  onSubmit(){
this.cred = this.loginForm.value;
    console.log(this.cred);

    this.authService.generateToken(this.cred)
    .subscribe(data=>{
      //console.log(data);
      this.authResponse = data;
      this.authService.loginUser(this.authResponse.token);

this.router.navigate(['/pensioner/list']);

    },
    err=>{
     console.log(err);
     this.error= 'Enter Valid credentials and try again';
    }
    )
  }


}
