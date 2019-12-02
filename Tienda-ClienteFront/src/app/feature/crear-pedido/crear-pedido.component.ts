import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Router, ActivatedRoute } from "@angular/router";
import swal from "sweetalert2";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { RestService } from 'src/app/core/services/rest.service';
import { Cliente } from 'src/app/shared/interfaces/cliente';
import { PedidoPrenda } from 'src/app/shared/interfaces/pedido-prenda';
import { ListaPedidoPrenda } from 'src/app/shared/interfaces/lista-pedido-prenda';


@Component({
  selector: 'app-crear-pedido',
  templateUrl: './crear-pedido.component.html',
  styleUrls: ['./crear-pedido.component.scss']
})
export class CrearPedidoComponent implements OnInit {

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
  public createForm: FormGroup;
  public addClothForm: FormGroup;
  cliente: Cliente;
  listaPedidaPrenda: PedidoPrenda[];
  listaNueva:ListaPedidoPrenda[];
  pedidoPrenda:PedidoPrenda;

  constructor(
    private translate: TranslateService,
    private router: Router,
    private service: RestService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.listaPedidaPrenda = [];


  this.createForm = new FormGroup({
    numeroOrden: new FormControl('', [Validators.required]),
    identificacionCliente: new FormControl('', [Validators.required])
  });

  this.addClothForm = new FormGroup({
    codigoPrenda: new FormControl('', [Validators.required]),
    cantidadPrenda: new FormControl('', [Validators.required]),
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
      this.router.navigate(["/pedido-form"]);
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

crear() {
  console.log('formulario ', this.createForm.valid);
  if(this.createForm.valid) {
    const data = {numeroOrden: this.numeroOrden.value, clienteId: { idCliente: this.identificacionCliente.value }};

    this.service.queryPost(`/api/pedido/crear`, JSON.stringify(data)).subscribe( dataReturn => {
      console.log(dataReturn);
      console.log(dataReturn.json());
      this.cliente = dataReturn.json().clienteId;
    },
    error => {
      this.cliente = null;

      console.log(error);
      console.log(error.message);

      swal({
        title: this.translate.instant("alerts.error"),
        text: error.message,
        type: "error",
        showCancelButton: false,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: this.translate.instant("buttons.ok"),
      }).then(result => {
        return false;
      });
    });
  }
}

aniadirPrenda() {
  if(this.addClothForm.valid){
    // ok
    const data = {pedidoId:{idPedido: this.numeroOrden.value}, prendaId: { idPrenda: this.codigoPrenda.value },cantidad:this.cantidadPrenda.value};

    //
      this.service.queryPost(`/api/pedidoprenda/crear`, JSON.stringify(data)).subscribe( dataReturn => {
      console.log(dataReturn);
      console.log(dataReturn.json());


      this.listaPedidaPrenda.push(dataReturn.json());
      console.log(this.listaPedidaPrenda);

      if(this.listaPedidaPrenda){
        swal({
          title: this.translate.instant("alerts.success"),
          text: "Se ha registrado el pedido",
          type: "success",
          showCancelButton: false,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: this.translate.instant("buttons.ok"),
        }).then(result => {
          this.addClothForm.reset();
        });
      }else {
        swal({
          title: this.translate.instant("alerts.error"),
          text: "Error al registrar el pedido",
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
    error => {
      this.listaPedidaPrenda = null;

      console.log(error);
      console.log(error.message);

      swal({
        title: this.translate.instant("alerts.error"),
        text: error.message,
        type: "error",
        showCancelButton: false,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: this.translate.instant("buttons.ok"),
      }).then(result => {
        return false;
      });
    });

  }
}

eliminarPedido() {

  let url = `/api/pedido/eliminar/`+this.numeroOrden.value;
  this.service.queryDeleteRegular(url).subscribe(
    response => {
      let result = response.json();
      if (result) {
        this.addClothForm.reset();
        this.createForm.reset();
        this.cliente = null;
        console.log("Se eliminó el pedido");
        ;
      } else {
        console.log('error');
      }
    },
    err => {
      console.log(err);
    }
  );

}

eliminarItem(idPedidoPrenda: number, index:number){
  let url = `/api/pedidoprenda/eliminar/${idPedidoPrenda}`;
  this.service.queryDeleteRegular(url).subscribe(
    response => {
    
      if (response) {

        console.log("Se eliminó el item de pedido");
        this.listaPedidaPrenda.splice(index, 1);
        ;
      } else {
        console.log('error');
      }
    },
    err => {
      console.log(err);
    }
  );

}


nuevoPedido(){
  swal({
    title: this.translate.instant("alerts.success"),
    text: "Se han registrado los productos al pedido",
    type: "success",
    showCancelButton: false,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: this.translate.instant("buttons.ok"),
  }).then(result => {
    this.addClothForm.reset();
    this.createForm.reset();
    this.cliente = null;
  });


}

get numeroOrden() { return this.createForm.get("numeroOrden"); }
get identificacionCliente() { return this.createForm.get("identificacionCliente"); }

get codigoPrenda() { return this.addClothForm.get("codigoPrenda"); }
get cantidadPrenda() { return this.addClothForm.get("cantidadPrenda"); }

}
