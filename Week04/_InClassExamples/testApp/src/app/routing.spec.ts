import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppComponent } from './app.component';
import { CustomPipePipe } from './pipes/custom-pipe.pipe';
import { HttpClientModule } from '@angular/common/http';
import { Routes } from '@angular/router';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';
import { DirectivesComponent } from './components/directives/directives.component'
import { PipesComponent } from './components/pipes/pipes.component';
import { approutes } from './routing';
import { Location } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '../../node_modules/@angular/router';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

describe('InterpolationComponent', () => {
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
                        PokeapiComponent,
                        PipesComponent,
                        CustomPipePipe,
                        DirectivesComponent
                    ]
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
  
    it('should createnavigate to "" redirects to /interpolation', fakeAsync(() => {
        router.navigate(['']);
        tick();
        expect(location.path()).toBe('/Interpolation');
    }));
  });
  
  