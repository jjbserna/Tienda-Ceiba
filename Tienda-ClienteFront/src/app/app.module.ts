import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { TranslateModule, TranslateLoader } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { HttpModule } from "@angular/http";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import {
  ReactiveFormsModule,
  FormsModule
} from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { TiendaComponent } from "./feature/tienda/tienda.component";
import { CrearPrendaComponent } from "./feature/crear-prenda/crear-prenda.component";
import { esLocale } from "ngx-bootstrap/locale";
import { defineLocale } from "ngx-bootstrap/chronos";
import es from "@angular/common/locales/es";
import { registerLocaleData } from "@angular/common";
import { LOCALE_ID } from "@angular/core";
import { MostrarFacturaComponent } from './feature/mostrar-factura/mostrar-factura.component';
import { CrearClienteComponent } from './feature/crear-cliente/crear-cliente.component';
import { CrearPedidoComponent } from './feature/crear-pedido/crear-pedido.component';
import { GenerarFacturaComponent } from './feature/generar-factura/generar-factura.component';

defineLocale("es", esLocale);
registerLocaleData(es);

@NgModule({
  declarations: [
    AppComponent,
    TiendaComponent,
    CrearPrendaComponent,
    MostrarFacturaComponent,
    CrearPrendaComponent,
    CrearClienteComponent,
    CrearPedidoComponent,
    GenerarFacturaComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: "es-ES"
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/", ".json");
}
