import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HeroService } from '../../servicios/hero.service';



@Component({
  selector: 'app-buscar-heroe',
  templateUrl: './buscar-heroe.component.html',
  styleUrls: ['./buscar-heroe.component.scss']
})
export class BuscarHeroeComponent implements OnInit {
heroes:any[]=[];
  constructor(private activarRuta:ActivatedRoute, private _heroesServicio:HeroService) { 
    
  }

  activar:boolean=true;
  nombre:string="Jeison";


  ngOnInit() {
    
    this.activarRuta.params.subscribe(params=>{
      console.log(params['termino']);
      this.heroes=this._heroesServicio.buscarHeroes(params['termino']);
    })
  }


}
