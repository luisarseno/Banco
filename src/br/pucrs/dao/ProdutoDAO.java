package br.pucrs.dao;

import br.pucrs.exception.ErroProdutoException;
import br.pucrs.model.Produto;

import java.util.ArrayList;

/**
 * Created by Santana on 11/15/2016.
 */
public class ProdutoDAO {

    public boolean cadastraProduto(Produto produto) throws ErroProdutoException{
        return true;
    }
    public ArrayList<Produto> getListProdutos() throws ErroProdutoException{
        return null;
    }
    public Produto getProdutoByDesc(String desc) throws ErroProdutoException{
        return null;
    }

}
