import { Observable } from 'rxjs';
import { PensionerService } from './../../services/pensioner.service';
import { Component, OnInit } from '@angular/core';




const ELEMENT_DATA: any = [];

@Component({
  selector: 'app-list-pensioner-detail',
  templateUrl: './list-pensioner-detail.component.html',
  styleUrls: ['./list-pensioner-detail.component.css']
})
export class ListPensionerDetailComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'username', 'email','actions'];
  dataSource = ELEMENT_DATA;

  listPensionerDetails: any;
  constructor(private pensionerService: PensionerService ) { }

  ngOnInit(): void {
  this.pensionerService.listPensionerDetails().subscribe(data => {
  this.listPensionerDetails = data;
  })
}

}
