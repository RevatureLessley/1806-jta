import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { CustomDirective } from './directives/custom.directive';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';
import { approutes } from './routing';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    InterpolationComponent,
    DirectivesComponent,
    CustomDirective,
    PokeapiComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(approutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
