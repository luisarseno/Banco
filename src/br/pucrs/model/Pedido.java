package br.pucrs.model;


import java.util.Date;

/**
 * Created by Santana on 11/15/2016.
 */
public class Pedido {

    private int codigo;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double valor;
    private Date dataPedido;

    public Pedido(int codigo, Cliente cliente, Produto produto, int quantidade, double valor, Date dataPedido) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataPedido = dataPedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
}
