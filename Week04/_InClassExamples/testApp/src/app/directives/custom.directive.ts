import { Directive, ElementRef, AfterViewInit } from '@angular/core';

@Directive({
  /*
    By encasing the selector with square brackets, we tell angular
    it has an element property instead of an element itself.
  */
  selector: '[appCustom]'
})
export class CustomDirective {
  /*
    Note: Access modifiers in typescript:
    Public is accessible outside of the typescript file.
    Private is for use within the typescript file specifically.
    No modifier defaults to public.

    Note: we use private for constructor parameters
  */
  constructor(private el: ElementRef) {
    
  }

  ngAfterViewInit(){
    this.el.nativeElement.style.background="red";
    this.el.nativeElement.style.color = "white";
  }


}
