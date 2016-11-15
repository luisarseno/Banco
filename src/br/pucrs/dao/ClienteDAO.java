package br.pucrs.dao;
import br.pucrs.model.Cliente;
import br.pucrs.model.Pedido;
import br.pucrs.exception.ErroClienteException;

import java.util.ArrayList;

/**
 * Created by Santana on 11/15/2016.
 */
public class ClienteDAO {

    public boolean  cadastraCliente(Cliente cliente) throws ErroClienteException{
        return true;
    }
    public ArrayList<Cliente> listaClientes() throws ErroClienteException{
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        return listaClientes;
    }
    public ArrayList<Pedido> getPedidosClientes(int codigoCliente) throws ErroClienteException{
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        return listaPedidos;
    }
    public Cliente getClienteByNome(String nome) throws ErroClienteException{
        Cliente cliente = null;
        return cliente;
    }

}
