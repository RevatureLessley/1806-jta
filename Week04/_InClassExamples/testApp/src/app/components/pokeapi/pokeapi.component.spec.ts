import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PokeapiComponent } from './pokeapi.component';

describe('PokeapiComponent', () => {
  let component: PokeapiComponent;
  let fixture: ComponentFixture<PokeapiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PokeapiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PokeapiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
