import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor() { 
    console.log('servicio listo para usarse');
  }
}
