package br.pucrs.model;

/**
 * Created by Santana on 11/15/2016.
 */
public class Categoria {

    private int codigo;
    private String descriacao;

    public Categoria(int codigo, String descriacao) {
        this.codigo = codigo;
        this.descriacao = descriacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescriacao() {
        return descriacao;
    }

    public void setDescriacao(String descriacao) {
        this.descriacao = descriacao;
    }
}
