import { Component, OnInit } from '@angular/core';
import { HeroService } from '../../servicios/hero.service';
import { Heroe } from '../../shared/interfaces/heroe';
import { Router } from '@angular/router';



@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {
  
  heroes:Heroe[]=[];
  constructor(private _heroesService:HeroService, private router:Router) { }

  ngOnInit() {
   this.heroes= this._heroesService.obtenerHeroes();
   console.log(this.heroes);
  }

  verMas(idx:number){
  console.log(idx);
  this.router.navigate(['/ver-mas-form',idx]);
  }
}
