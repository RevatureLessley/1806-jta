import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../objects/Employee';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

/*
  The injectable decorator will mark this class as one that may have
  injected dependencies elsewhere.
  It is a matter of convention and safety to include it for all 
  service classes.
  //Also provides an easy way to have it included in the module.
*/
@Injectable({
  providedIn: 'root'
})
export class ExampleService {

    constructor(private http: HttpClient) { }

    public empId = 1;

    getEmployee(){

    
    return this.http.get<Employee[]>("http://localhost:8085/Project1/SelectEmployees");
         
      
  }
}
