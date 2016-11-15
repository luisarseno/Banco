package br.pucrs.dao;


import br.pucrs.exception.ErroPedidoException;
import br.pucrs.model.Pedido;
import br.pucrs.model.Produto;

import java.util.ArrayList;

/**
 * Created by Santana on 11/15/2016.
 */
public class PedidoDAO {
    public boolean cadastraPedido(Pedido pedido) throws ErroPedidoException{
        return true;
    }
    public ArrayList<Pedido> getPedidos() throws ErroPedidoException{
        return null;
    }

    public int qntdPedidosByProduto(Produto produto) throws ErroPedidoException{
        return 0;
    }

}
