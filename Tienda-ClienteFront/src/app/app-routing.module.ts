import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TiendaComponent } from './feature/tienda/tienda.component';
import { CrearPrendaComponent } from './feature/crear-prenda/crear-prenda.component';
import { CrearClienteComponent } from './feature/crear-cliente/crear-cliente.component';
import { CrearPedidoComponent } from './feature/crear-pedido/crear-pedido.component';
import { GenerarFacturaComponent } from './feature/generar-factura/generar-factura.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { AboutComponent } from './feature/about/about.component';
import { VerMasComponent } from './feature/ver-mas/ver-mas.component';




const routes: Routes = [
  { path: "", component: TiendaComponent },
  {
    path: "",
    children: [
      { path: "tienda", component: TiendaComponent },
      { path: "prenda-form", component: CrearPrendaComponent },
      { path: "cliente-form", component: CrearClienteComponent },
      { path: "pedido-form", component: CrearPedidoComponent },
      { path: "factura-form", component: GenerarFacturaComponent },
      { path: "heroes-form", component: NavbarComponent },
      { path: "about-form", component: AboutComponent },
      { path: "ver-mas-form/:id", component: VerMasComponent },
     


    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
