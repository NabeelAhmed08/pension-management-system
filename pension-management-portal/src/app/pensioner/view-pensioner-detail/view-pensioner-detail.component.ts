import { PensionerService } from './../../services/pensioner.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-view-pensioner-detail',
  templateUrl: './view-pensioner-detail.component.html',
  styleUrls: ['./view-pensioner-detail.component.css']
})
export class ViewPensionerDetailComponent implements OnInit {

  pensionerAadhar: string = '';
  pensionerDetails: any;
  pensionDetails: any;
  constructor(private pensionerService: PensionerService,
              private activatedRoute: ActivatedRoute,
              private _snackBar: MatSnackBar,
              private router: Router
              ) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(data =>{

      this.pensionerAadhar = data.id;
    })

    this.pensionerService.viewPensioner(this.pensionerAadhar).subscribe(data=>{
     // console.log(data);
      this.pensionerDetails = data;
      this._snackBar.open("Profile Fetched Sucessfully");
   },err=> {
    this._snackBar.open("Something went wrong Could not fetch profile try again later");
   }

    )}

    processPension(){
      this.router.navigate(['/pensioner/process']);
    }


  }


