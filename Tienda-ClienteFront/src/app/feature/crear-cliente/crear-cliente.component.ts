import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import swal from "sweetalert2";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { RestService } from 'src/app/core/services/rest.service';


@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.scss']
})
export class CrearClienteComponent implements OnInit {

  public editing: boolean = false;
  public identificacion: number = null;
  public nombre: string = null;
  public apellido: string = null;
  public fechaNacimiento: Date = null;
  public correo: String = null;
  public celular: String = null;
  public ciudad: String = null;
  public direccionEntrega: String = null;
  public usuario: String = null;
  public password: String = null;
  public myForm: FormGroup;

  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute

  ) { }

  ngOnInit() {
    this.identificacion = this.route.snapshot.queryParams["identificacion"];
    this.nombre = this.route.snapshot.queryParams["nombre"];
    this.apellido = this.route.snapshot.queryParams["apellido"];
    this.fechaNacimiento = this.route.snapshot.queryParams["fechaNacimiento"];
    this.correo = this.route.snapshot.queryParams["correo"];
    this.celular = this.route.snapshot.queryParams["celular"];
    this.ciudad = this.route.snapshot.queryParams["ciudad"];
    this.direccionEntrega = this.route.snapshot.queryParams["direccionEntrega"];
    this.usuario = this.route.snapshot.queryParams["usuario"];
    this.password = this.route.snapshot.queryParams["password"];

    this.myForm = new FormGroup({
      identificacion: new FormControl("identificacion", [Validators.required]),
      nombre: new FormControl("nombre", [Validators.required]),
      apellido: new FormControl("apellido", [Validators.required]),
      fechaNacimiento: new FormControl("fechaNacimiento", [Validators.required]),
      correo: new FormControl("correo", [Validators.required]),
      celular: new FormControl("celular", [Validators.required]),
      ciudad: new FormControl("ciudad", [Validators.required]),
      direccionEntrega: new FormControl("direccionEntrega", [Validators.required]),
      usuario: new FormControl("usuario", [Validators.required]),
      password: new FormControl("password", [Validators.required])
  });
  this.myForm.controls["identificacion"].setValue("");
  this.myForm.controls["nombre"].setValue("");
  this.myForm.controls["apellido"].setValue("");
  this.myForm.controls["fechaNacimiento"].setValue("");
  this.myForm.controls["correo"].setValue("");
  this.myForm.controls["celular"].setValue("");
  this.myForm.controls["ciudad"].setValue("");
  this.myForm.controls["direccionEntrega"].setValue("");
  this.myForm.controls["usuario"].setValue("");
  this.myForm.controls["password"].setValue("");
}


  /**
   * Método para crear el cliente
   */
  crearCliente() {
    let controls = this.myForm.controls;
    if (this.myForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    const datosPrenda = {
      identificacion: controls["identificacion"].value,
      nombre: controls["nombre"].value,
      apellido: controls["apellido"].value,
      fechaNacimiento: controls["fechaNacimiento"].value,
      correo: controls["correo"].value,
      celular: controls["celular"].value,
      ciudad: controls["ciudad"].value,
      direccionEntrega: controls["direccionEntrega"].value,
      usuario: controls["usuario"].value,
      password: controls["password"].value,

    };
    let url = `/api/cliente/crear`;

    
    let data = {
      "identificacion": datosPrenda.identificacion,
      "nombre": datosPrenda.nombre,
      "apellido": datosPrenda.apellido,
      "fechaNacimiento": datosPrenda.fechaNacimiento,
      "correo": datosPrenda.correo,
      "celular": datosPrenda.celular,
      "ciudad": datosPrenda.ciudad,
      "direccionEntrega": datosPrenda.direccionEntrega,
      "usuario": datosPrenda.usuario,
      "password": datosPrenda.password
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

            this.router.navigate(["/cliente-form"]);
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
      this.router.navigate(["/cliente-form"]);
    }
  });
}

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
