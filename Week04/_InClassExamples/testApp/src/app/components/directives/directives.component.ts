import { Component, OnInit } from '@angular/core';


/*
  In addition to templateUrl, you can use template literals as well.
  This allows you to build the html within the typescript file itself.
  This is done by replacing 'templateUrl' with 'template', followed by 
  backticks (`) with code in between.
*/
@Component({
  selector: 'app-directives',
  templateUrl: './directives.component.html',
  //template: `<div class="rsection">Hiya!</div>`,
  styleUrls: ['./directives.component.css']
})
export class DirectivesComponent implements OnInit {
  public buttonValue = "SHOW ANSWER";
  public visible = false;

  public toggleAnswer(){
    if(this.visible = !this.visible){
      this.buttonValue = "HIDE ANSWER";
    }else{
      this.buttonValue = "SHOW ANSWER";
    }
  }

  npcs = [
    {id:1, name:"bobbert"},
      {id:2, name:"ryan"},
      {id:3, name:"Timbert"},
      {id:4, name:"salara"}
  ]
  constructor() { }

  ngOnInit() {
  }

}
