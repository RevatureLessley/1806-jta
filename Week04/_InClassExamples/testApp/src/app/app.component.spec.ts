import { FormsModule } from '@angular/forms';
import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
/*
  In order for our unit tests to work properly we need to do a bit of
  configuration.
*/


//Use describe to determine a test suite.
describe('AppComponent', () => {
  //Before each runs for each test case separately.
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      /*
       Here we add the FromsModule. Without it we couldn't test the individual components'
       ngMpdel binding.
      */
      imports: [FormsModule],
      declarations: [
        AppComponent
      ],
      /*
        By default, angualr will parse the html, since we use a lot of custom tags, this
        will raise errors. By utilizing this schema, we can avoid any errors pertaining
        to custom tags in the html.
      */
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    }).compileComponents();
  }));
  //it() is what we can call our @test method. 
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Bobbert\'s Exciting application PART 2');
  }));
  it('should render title in a h1 tag', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Bobbert\'s Exciting application PART 2');
  }));
});
/*
  Your embedded jasmine cheat sheet!
===================================================
  Various matchers:

    expect(array).toContain(member);
    expect(fn).toThrow(string);
    expect(fn).toThrowError(string);
    expect(instance).toBe(instance);
    expect(mixed).toBeDefined();
    expect(mixed).toBeFalsy();
    expect(mixed).toBeNull();
    expect(mixed).toBeTruthy();
    expect(mixed).toBeUndefined();
    expect(mixed).toEqual(mixed);
    expect(mixed).toMatch(pattern);
    expect(number).toBeCloseTo(number, decimalPlaces);
    expect(number).toBeGreaterThan(number);
    expect(number).toBeLessThan(number);
    expect(number).toBeNaN();
    expect(spy).toHaveBeenCalled();
    expect(spy).toHaveBeenCalledTimes(number);
    expect(spy).toHaveBeenCalledWith(...arguments);
===================================================

  Different setup and teardown options:

      -called once before all the ‘it’ methods are called
    beforeAll
    afterAll
        -called once for each ‘it’ method.
    beforeEach
    afterEach


*/