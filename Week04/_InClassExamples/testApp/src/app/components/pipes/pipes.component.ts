import { CustomPipePipe } from './../../pipes/custom-pipe.pipe';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-pipes',
  templateUrl: './pipes.component.html',
  styleUrls: ['./pipes.component.css']
})
export class PipesComponent implements OnInit {
  names = ['bobbert','timbert','edbert','bertbert'];
  stuff = "";
  constructor() { }

  ngOnInit() {
  }

}
