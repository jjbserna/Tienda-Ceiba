import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HeroService } from '../../servicios/hero.service';





@Component({
  selector: 'app-ver-mas',
  templateUrl: './ver-mas.component.html',
  styleUrls: ['./ver-mas.component.scss']
})
export class VerMasComponent implements OnInit {

  heroe:any={};
  logo:string;

  constructor(private activateRoute:ActivatedRoute, private _heroService:HeroService) { 
    this.activateRoute.params.subscribe(parametros=>{
      console.log(parametros); //manera general de llamar el objeto
      console.log(parametros['id']); // toma solo el valor exacto del id, en este caso el parametro que se definió en la url en el app-routing.modules.ts
      this.heroe=this._heroService.obtenerHeroePorId(parametros['id']);
      console.log(this.heroe);
      if (this.heroe.casa=='DC') {
        this.logo='assets/img/DC.png';
      } else {
        this.logo='assets/img/marvel.png';
      }
    })
  }
 
 
  ngOnInit() {
  }
  


}
