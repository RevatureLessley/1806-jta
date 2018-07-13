import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Npc } from '../../objects/Npc';
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


  getNpc(): Observable<Npc[]>{

    let npcs: Npc[] = [];
    this.http.get("http://localhost:8085/180618_Servlets_P2/SelectNpc")
      .subscribe(
        success => { 
          npcs = <Npc[]>success
          
        },
        error => { 
          console.log("ERROR");
          
        }
      )
      /*
        The 'of' method returns an observable of a collection of
        Posts. Think of it as a sort of stream of data that has
        callback functionality.
      */
      return of(npcs);
      
  }
}
