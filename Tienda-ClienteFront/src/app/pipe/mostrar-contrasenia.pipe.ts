import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mostrarContrasenia'
})
export class MostrarContraseniaPipe implements PipeTransform {

  transform(value: string, args?: boolean): any {
   let nuevo:string='';
    for (let index = 0; index < value.length; index++) {
     nuevo= nuevo+"*";     
    }
    if (args) {
      return value;
    }
    else{
      return nuevo;
    }
    
  }

}
