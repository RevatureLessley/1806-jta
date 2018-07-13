import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-pokeapi',
  templateUrl: './pokeapi.component.html',
  styleUrls: ['./pokeapi.component.css']
})
export class PokeapiComponent implements OnInit {

  constructor(private http: HttpClient) { }

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
}
