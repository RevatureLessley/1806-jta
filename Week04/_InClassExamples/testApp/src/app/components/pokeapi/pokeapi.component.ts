import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExampleService } from '../../services/example/example.service';

@Component({
  selector: 'app-pokeapi',
  templateUrl: './pokeapi.component.html',
  styleUrls: ['./pokeapi.component.css']
})
export class PokeapiComponent implements OnInit {

  constructor(private http: HttpClient, private ex:ExampleService) { }

  ngOnInit() {
  }

  public pokeId;
  public pkmn = {
    name: "",
    id: "",
    weight: "",
    sprite: "",
    sprites: [],
    spriteIndex: 0
  }

  public fetchData(){
    this.pkmn.name = "PENDING";
    this.pkmn.id = "PENDING";
    this.pkmn.weight = "PENDING";
    this.pkmn.sprite = "";

    this.pkmn.sprites = [];
    this.pkmn.spriteIndex = 0;

    this.http.get("https://pokeapi.co/api/v2/pokemon/" + this.pokeId + "/").subscribe(
      data => { //Data represents the object of a successful REST call to the url.
        this.pkmn.name = data["name"]; //Note: first parameter is always how you handle
        this.pkmn.id = data["id"];     //A success
        this.pkmn.weight = data["weight"];

        let dataSprites = data["sprites"];
        for(let index in dataSprites){
          if(dataSprites[index]!=null){
            this.pkmn.sprites.push(dataSprites[index]);
            if(index=="front_default"){
              this.pkmn.sprite = dataSprites[index];
              this.pkmn.spriteIndex = this.pkmn.sprites.length-1;
            }
          }
        }
      }, //NOTE: 2nd parameter is always how you handle and error
      error => {
        this.pkmn.name = "MissingNo";
        this.pkmn.id = "-1";
        this.pkmn.weight = "What?";
        this.pkmn.sprite = "N/A";
      }
    )
  }

  public changePicture(){
    let sprites = this.pkmn.sprites;
    let index = this.pkmn.spriteIndex;
    if(sprites[index+1]==undefined){
      this.pkmn.sprite = sprites[0];
      this.pkmn.spriteIndex = 0;
    }else{
      this.pkmn.sprite = sprites[++index];
      this.pkmn.spriteIndex = index;
    }
  }

  public fetchNpc(){

    this.ex.getNpc().subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log("ERROR");
      }
    );
  }
  /*
    Promises vs Observables

    A promise is a type of object where when a user sends data,
    we are gauranteed to get something back. In the case of sending a request we 
    either get back an object representing we recevied, or we get an object 
    representing an error with details on why we didn't get what we expected. 
    This flexibility, along side callback functions, allow us to create asynchronous 
    function calls. 
    Promises can only listen on one event at a time, on top of which, a user cannot 
    cancel the event once it is started. IE, I can't halt a request sent to a 
    server, I must wait for some kind of repsonse.

    Observables are the same as promies, except with more features. Observables 
    send the data back, essentially, as a stream. With that, Observables can provide 
    support for 0-many events at a time. Observables also support canceling of 
    a current event's stream of data.

    NOTE: Observables were introduced in ES7
  */

          //Example of POST
        /*
        this.http.post("endpoint_url", {
                                            key1: "value",
                                            key2: "value",
                                            etc : "value"
                                        }).subscribe(
                                            PASS => {}, 
                                            FAIL => {})
*/

}