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
  public estado: boolean = false;
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
      stock: new FormControl("stock", [Validators.required])
  });
  this.myForm.controls["codigoPrenda"].setValue("");
  this.myForm.controls["descripcion"].setValue("");
  this.myForm.controls["estilo"].setValue("");
  this.myForm.controls["estado"].setValue("");
  this.myForm.controls["precio"].setValue("");
  this.myForm.controls["stock"].setValue("");


}
  /**
   * name
   */
  crearPrenda() {
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const datosPrenda = {
      codigoPrenda: controls["codigoPrenda"].value,
      descripcion: controls["descripcion"].value,
      estilo: controls["estilo"].value,
      estado: controls["estado"].value,
      precio: controls["precio"].value,
      stock: controls["stock"].value,

    };
    let url = `/api/prenda/crear`;

    
    let data = {
      "codigoPrenda": datosPrenda.codigoPrenda,
      "descripcion": datosPrenda.descripcion,
      "estilo": datosPrenda.estilo,
      "estado": datosPrenda.estado,
      "precio": datosPrenda.precio,
      "stock": datosPrenda.stock
    }
    let body = JSON.stringify(data);
    this.service.queryPost(url, body)
      .subscribe(data => {
        let result = data;
        if (result) {
          swal({
            title: this.translate.instant("alerts.success"),
            text: this.translate.instant("alerts.stored_urgencia"),
            type: "success",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: this.translate.instant("buttons.ok"),
          }).then(result => {
            this.router.navigate(["/prenda-form"]);
            this.myForm.reset();
          });
        } else {
          swal({
            title: this.translate.instant("alerts.error"),
            text: this.translate.instant("alerts.cannot_delete_urgencia"),
            type: "error",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: this.translate.instant("buttons.ok"),
          }).then(result => {
            return false;
          });
        }
      }, err => {
        console.log(err);
      });
  }

  volver() {
    swal({
      title: this.translate.instant("alerts.confirm"),
      text: this.translate.instant("alerts.sure_to_volver"),
      type: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: this.translate.instant("buttons.yes"),
      cancelButtonText: this.translate.instant("buttons.cancel")
    }).then(result => {
      if (result.value) {
        this.router.navigate(["/tienda"]);
      }
      else{
        this.router.navigate(["/prenda-form"]);
      }
    });
  }
   /**
   * Validates whether a field follows the validation rules
   *
   * @param controlName name of the control being evaluated
   * @param validationType type of the validation to be evaluated
   */
  public controlHasError(controlName: string, validationType: string): boolean {
    const control = this.myForm.controls[controlName];
    if (!control) {
      return false;
    }

    const result =
      control.hasError(validationType) && (control.dirty || control.touched);

    return result;
  }

}
