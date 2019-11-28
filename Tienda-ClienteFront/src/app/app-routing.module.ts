import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TiendaComponent } from './feature/tienda/tienda.component';
import { CrearPrendaComponent } from './feature/crear-prenda/crear-prenda.component';
import { MostrarFacturaComponent } from './feature/mostrar-factura/mostrar-factura.component';

const routes: Routes = [
  { path: "", component: TiendaComponent },
  {
    path: "",
    children: [
      { path: "tienda", component: TiendaComponent },
      { path: "prenda-form", component: CrearPrendaComponent },
      { path: "factura/:id", component: MostrarFacturaComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
