import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-interpolation',
  templateUrl: './interpolation.component.html',
  styleUrls: ['./interpolation.component.css']
})
export class InterpolationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  componentVariable = "Secret message!";
  componentObject = {
    author: "The Ryan Dude"
  }
  styleObject = {
    color: 'blue',
    fontFamily: 'Comic Sans MS'
  }
  //TypeScript function
  public changeStyle(){
    if(this.styleObject.color == 'blue'){
      this.styleObject.color = 'green';
    }else{
      this.styleObject.color = 'blue';
    }
  }

  visible = true;

  public toggleVisibility(){
    this.visible = !this.visible;
  }
}
