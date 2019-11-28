import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import swal from "sweetalert2";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { RestService } from 'src/app/core/services/rest.service';

@Component({
  selector: 'app-crear-prenda',
  templateUrl: './crear-prenda.component.html',
  styleUrls: ['./crear-prenda.component.scss']
})
export class CrearPrendaComponent implements OnInit {

  public editing: boolean = false;
  public codigoPrenda: number = null;
  public descripcion: string = null;
  public estilo: string = null;
  public estado: boolean = null;
  public precio: number = null;
  public stock: number = null;
  public myForm: FormGroup;
  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute
  ) { }


  ngOnInit() {
    this.codigoPrenda = this.route.snapshot.queryParams["codigoPrenda"];
    this.descripcion = this.route.snapshot.queryParams["descripcion"];
    this.estilo = this.route.snapshot.queryParams["estilo"];
    this.estado = this.route.snapshot.queryParams["estado"];
    this.precio = this.route.snapshot.queryParams["precio"];
    this.stock = this.route.snapshot.queryParams["stock"];



    this.myForm = new FormGroup({
      codigoPrenda: new FormControl("codigoPrenda", [Validators.required]),
      descripcion: new FormControl("descripcion", [Validators.required]),
      estilo: new FormControl("estilo", [Validators.required]),
      estado: new FormControl("estado", [Validators.required]),
      precio: new FormControl("precio", [Validators.required]),
      stock: new FormControl("estock", [Validators.required])
  });
}
  /**
   * name
   */
  crearPrenda() {
    
  }

}
