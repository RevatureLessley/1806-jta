import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customPipe'
})
export class CustomPipePipe implements PipeTransform {

  //You can use 'any' as a datatype!
  //Adding ? to an argument makes it optional.
  transform(input: string): any {
    let x = "";
    let count = 0;
    for(let letter of input){
      count++;
      if(!(count%3)){
        x += letter.toUpperCase();
      }else{
        x += letter;
      }
    }
    return x;
  }

}
