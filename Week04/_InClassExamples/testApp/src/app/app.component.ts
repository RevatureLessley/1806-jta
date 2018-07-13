import { Component } from '@angular/core';

//Any annotations in Angular are called declarators.
//A declarator is simply used to provide meta data for angular to use and
//aid in angular bundling/compiling/deploying the application.
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Bobbert\'s Exciting application PART 2';
}
