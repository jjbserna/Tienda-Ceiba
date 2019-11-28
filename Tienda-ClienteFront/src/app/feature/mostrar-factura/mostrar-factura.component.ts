import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import swal from "sweetalert2";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Factura } from "../../shared/interfaces/factura";
import { RestService } from 'src/app/core/services/rest.service';

@Component({
  selector: "app-mostrar-factura",
  templateUrl: "./mostrar-factura.component.html",
  styleUrls: ["./mostrar-factura.component.scss"]
})
export class MostrarFacturaComponent implements OnInit {
  public id: string;
  public editing: boolean = false;
  public myForm: FormGroup;
  public factura: Factura;
  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id')
    this.getFactura(this.id);
  }
  private getFactura(idPaciente: string) {
    let url = `/api/urgencias/facturar-urgencia/${idPaciente}`;
    this.service.queryExternalApi(url).subscribe(
      response => {
        let result = response.json();
        if (result) {
          this.factura = result;
        } else {
          console.log('error');
        }
      },
      err => {
        console.log(err);
      }
    );

  }
  confirmVolver() {
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
        this.router.navigate(["/urgencias"]);
      }
    });
  }

  facturar(idPaciente) {
    swal({
      title: this.translate.instant("alerts.confirm"),
      text: this.translate.instant("alerts.sure_to_facturar"),
      type: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: this.translate.instant("buttons.yes"),
      cancelButtonText: this.translate.instant("buttons.cancel")
    }).then(result => {
      if (result.value) {
        let url = `/api/urgencias/eliminar-urgencia/${idPaciente}`;
    this.service.queryDeleteRegular(url).subscribe(
      response => {
        let result = response;
        if (result) {
          this.router.navigate(["/urgencias"]);
          swal({
            title: this.translate.instant("alerts.success"),
            text: this.translate.instant("alerts.factuar_exito"),
            type: "success",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: this.translate.instant("buttons.ok"),
          }).then(result => {
            return false;
          });
        } else {
          swal({
            title: this.translate.instant("alerts.error"),
            text: this.translate.instant("alerts.urgencia_doesnt_exist"),
            type: "error",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: this.translate.instant("buttons.ok"),
          }).then(result => {
            return false;
          });
        }
      },
      err => {
        console.log(err);
      }
    );
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
