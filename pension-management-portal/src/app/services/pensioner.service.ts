import { baseUrlProcess, baseUrlDetails } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PensionerService {

  aadhar: string ='';

  //processBaseUrl: string = 'http://localhost:8100/process/api/v1/'
  constructor(private http: HttpClient) { }

  listPensionerDetails(){
    return this.http.get(`${baseUrlDetails}getAllPensioner`)
  }

  viewPensioner(id: string ){
    this.aadhar=id;
    return this.http.get(`${baseUrlDetails}PensionerDetailByAadhaar/` +id )
  }

  processPensioner(aadharNum: String){

    const aadharObj = {aadharNumber:aadharNum };
    console.log(aadharObj);
    //return this.http.post(this.processBaseUrl + 'ProcessPension',aadharObj);
    return this.http.post(`${baseUrlProcess}ProcessPension`,aadharObj);
  }


}
