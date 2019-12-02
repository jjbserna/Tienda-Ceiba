import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import swal from "sweetalert2";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Factura } from "../../shared/interfaces/factura";
import { Cliente } from 'src/app/shared/interfaces/cliente';
import { RestService } from 'src/app/core/services/rest.service';
import { PedidoPrenda } from 'src/app/shared/interfaces/pedido-prenda';


@Component({
  selector: 'app-generar-factura',
  templateUrl: './generar-factura.component.html',
  styleUrls: ['./generar-factura.component.scss']
})
export class GenerarFacturaComponent implements OnInit {
  public id: string;
  public editing: boolean = false;
  public myForm: FormGroup;
  cliente: Cliente;
  private numeroOrden:number= null;
  public factura: Factura;
  listaPedidoPrenda: PedidoPrenda[];
  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute
    
  ) { }

 

  ngOnInit() {
    this.numeroOrden = this.route.snapshot.queryParams["numeroOrden"];
    this.listaPedidoPrenda = []
    this.myForm = new FormGroup({
      numeroOrden: new FormControl('', [Validators.required])
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
        this.router.navigate(["/factura-form"]);
      }
    });
  }



  facturar(){
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const datosOrden = {
      numeroOrden: controls["numeroOrden"].value,
    };
    let url = `/api/pedidoprenda/`+datosOrden.numeroOrden;

    this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {
          this.listaPedidoPrenda = result;
        } else {
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    );
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
