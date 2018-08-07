import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectivesComponent } from './directives.component';

describe('DirectivesComponent', () => {
  let component: DirectivesComponent;
  let fixture: ComponentFixture<DirectivesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectivesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectivesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('SHOW ANSWER button shows the answer', () =>{
    component.toggleAnswer();
    fixture.detectChanges(); //Run upon page changes to apply
    expect(component.buttonValue).toEqual('HIDE ANSWER');
    const el = fixture.debugElement.nativeElement;
    expect(el.querySelector('#hiddenAnswer')).toBeTruthy();
    //querySelector is how we can grab elements from the html.
  });
  it('HIDE ANSWER button hides the answer', () =>{
    component.toggleAnswer();
    component.toggleAnswer();
    fixture.detectChanges(); //Run upon page changes to apply
    expect(component.buttonValue).toEqual('SHOW ANSWER');
    const el = fixture.debugElement.nativeElement;
    expect(el.querySelector('#hiddenAnswer')).toBeFalsy();
    //querySelector is how we can grab elements from the html.
  });
});
