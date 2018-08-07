import { CustomPipePipe } from './pipes/custom-pipe.pipe';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { approutes } from './routing';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';
import { DirectivesComponent } from './components/directives/directives.component'
import { PipesComponent } from './components/pipes/pipes.component';
import { Location } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { TestBed, async, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { Router } from '../../node_modules/@angular/router';

describe('RoutingUnitTest', () => {
    let component: AppComponent;
    let fixture: ComponentFixture<AppComponent>;
    let router: Router;
    let location: Location;
  
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        imports: [FormsModule,
        RouterTestingModule.withRoutes(approutes),
    HttpClientModule],
        declarations: [ AppComponent,
        NavbarComponent,
        InterpolationComponent,
        DirectivesComponent,
        PokeapiComponent,
        PipesComponent,
        CustomPipePipe ],
        schemas: [CUSTOM_ELEMENTS_SCHEMA]
      })
      .compileComponents();
      router = TestBed.get(Router);
      location = TestBed.get(Location);
    }));
  
    beforeEach(() => {
      fixture = TestBed.createComponent(AppComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('navigate to "" redirects to /interpolation', fakeAsync(() => {
        router.navigate(['']); //invokes the router change
        tick(); //A Wait for promises to be resolved
        expect(location.path()).toBe('/Interpolation');
    }));
  });
  