import { Pedido } from './pedido';
import { Prenda } from './prenda';

export interface PedidoPrenda {
    idPedidoPrenda : number;
    pedidoId: Pedido;
	prendaId: Prenda;
	cantidad: number;
	valorTotal: number;
}