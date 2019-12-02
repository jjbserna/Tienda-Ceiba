import { Cliente } from './cliente';

export interface Pedido {
    numeroOrden : number;
    idCliente : Cliente;
}