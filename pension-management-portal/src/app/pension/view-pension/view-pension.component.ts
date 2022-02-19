import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { PensionerService } from 'src/app/services/pensioner.service';

@Component({
  selector: 'app-view-pension',
  templateUrl: './view-pension.component.html',
  styleUrls: ['./view-pension.component.css']
})
export class ViewPensionComponent implements OnInit {

  pensionerAadhar: string ='';
  pensionerDetails: any;
  pensionDetails: any;
  pensionerName:any;
  constructor(private pensionerService: PensionerService,
    private activatedRoute: ActivatedRoute,
    private _snackBar: MatSnackBar,
    private router: Router,) {
      this.pensionerAadhar = this.pensionerService.aadhar;
     }

  ngOnInit(): void {

  this.processPension();

this.pensionerService.viewPensioner(this.pensionerAadhar).subscribe(data=>{
  this.pensionerName = data;
})

  }

  processPension(){

    console.log(this.pensionerAadhar);

    this.pensionerService.processPensioner(this.pensionerAadhar).subscribe(data=>{
  this.pensionDetails = data;
  console.log(data);
  //this.router.navigate(['pensioner/process']);

  this._snackBar.open("Pension Processed Successfully");
    },err=>{
      console.log(err);
      this._snackBar.open("Something went wrong Could not process try again later");
    })

}

}
