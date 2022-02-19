import { AuthService } from './../../login/login/auth.service';
import { PensionerService } from './../../services/pensioner.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-pensioner',
  templateUrl: './search-pensioner.component.html',
  styleUrls: ['./search-pensioner.component.css']
})
export class SearchPensionerComponent implements OnInit {
  //public loggedInn = false;

  searchPensionerForm: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
     private pensionerService: PensionerService,
     private router: Router,
     private authService: AuthService ) {
     // this.loggedInn = this.authService.isLoggedIn();
     }

  ngOnInit(): void {
    this.searchPensionerForm = this.formBuilder.group({
      'aadharNumber' : new FormControl('')
    });



   // this.loggedIn = this.authService.isLoggedIn();
  }

  searchPensioner(){
  this.pensionerService.viewPensioner(this.searchPensionerForm.value.aadharNumber).subscribe(data=>{
    console.log(data);
    this.router.navigate(['/pensioner/view',this.searchPensionerForm.value.aadharNumber]);
  }, err =>{
    console.log(err);
  } )
  }
}
