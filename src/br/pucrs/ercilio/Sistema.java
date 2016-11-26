package br.pucrs.ercilio;

import br.pucrs.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sistema {

    public static void main(String[] args) throws SQLException {
        try {
            Connection conexao = Conexao.getConexao();
            String strNomeCliente;
            String strDescricao;
            Scanner in = new Scanner(System.in);
            while (true) {
                menu();
                int escolha = in.nextInt();
                switch (escolha) {
                    case 1:
                        System.out.print("Informe o nome do cliente: ");
                        strNomeCliente = in.next();
                        buscaCliente(strNomeCliente);
                        break;
                    case 2:
                        System.out.print("Informe a descrição do seu produto: ");
                        strDescricao = in.next();
                        buscaProdudo(strDescricao);
                        break;
                    case 3:
                        System.out.println("Listando produtos em ordem alfabetica:");
                        listaProdutosAsc();
                        break;
                    case 4:
                        System.out.println("Fazer novo pedido: ");
                        System.out.println("Informe o nome do cliente");
                        strNomeCliente = in.next();
                        System.out.println("Informe a descrição produto");
                        strDescricao = in.next();
                        System.out.println("Informe a quantidade");
                        int qntd = in.nextInt();
                        cadastraProduto(strNomeCliente,strDescricao,qntd);
                        break;
                    case 5:
                        relatorioPedido();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Saindo do sistema");
                        break;
                }
                if (escolha == 8) {
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menu() {
        System.out.println("1- Se deseja buscar cliente por nome" +
                "\n2- Se deseja buscar produto por descrição" +
                "\n3- Se deseja listar os produtos por ordem alfabetica" +
                "\n4- Fazer um novo pedido" +
                "\n5- Relatorio de todos os pedidos que o cliente solicitou" +
                "\n6- Visualizar quantidade de pedidos de um certo produto" +
                "\n7- Listar os clientes o numero de pedidos e o valor total dos pedidos" +
                "\n8- Sair"
        );
    }

    public static void buscaCliente(String nome) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + " FROM E1_cliente"
                    + " WHERE nome_cliente = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se retornou o registro solicitado
                int Codigo_Cliente = rs.getInt("Codigo_Cliente");
                String Nome_Cliente = rs.getString("Nome_Cliente");
                String Endereco = rs.getString("Endereco");
                String Telefone = rs.getString("Telefone");
                String Status = rs.getString("Status");
                Integer Limite_Credito = rs.getInt("Limite_Credito");
                System.out.println(nome
                        + "\nCodigo_Cliente: " + Codigo_Cliente
                        + "\nNome_Cliente: " + Nome_Cliente
                        + "\nEndereco: " + Endereco
                        + "\nTelefone: " + Telefone
                        + "\nStatus: " + Status
                        + "\nLimite_Credito: " + Limite_Credito
                        + "\n"
                );
            } else {
                System.out.println("Cliente não encontrado.");
            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listaProdutosAsc() {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + "from E1_produto"
                    + " ORDER by Nome_Produto ASC ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int Codigo_Produto = rs.getInt("Codigo_Produto");
                String Nome_Produto = rs.getString("Nome_Produto");
                int Cod_Categoria = rs.getInt("Cod_Categoria");
                Integer Preco = rs.getInt("Preco");
                System.out.println(
                        "\nCodigo_Produto: " + Codigo_Produto
                                + "\nNome_Produto: " + Nome_Produto
                                + "\nCod_Categoria: " + Cod_Categoria
                                + "\nPreco: " + Preco
                                + "\n"
                );
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void buscaProdudo(String desc) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + " from E1_produto prod " +
                    "inner join E1_categoria cat on prod.cod_categoria = cat.codigo"
                    + " where prod.Nome_Produto = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, desc);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // Se retornou o registro solicitado
                int Codigo_Produto = rs.getInt("Codigo_Produto");
                String Nome_Produto = rs.getString("Nome_Produto");
                String Cod_Categoria = rs.getString("Cod_Categoria");
                String Preco = rs.getString("Preco");
                System.out.println(desc
                        + "\nCodigo_Produto: " + Codigo_Produto
                        + "\nNome_Produto: " + Nome_Produto
                        + "\nCod_Categoria: " + Cod_Categoria
                        + "\nPreço: " + Preco
                        + "\n"
                );
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastraProduto(String nomeCliente, String descProduto, int quantidade) {
        try{
            Connection conexao = Conexao.getConexao();
            int idCliente = buscaIdCliente(nomeCliente);
            int idProduto = buscaIdProduto(descProduto);
            if(idCliente != 0 && idProduto != 0){
                //insere produto
                double valor = buscaValorProduto(idProduto);
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO E1_pedido " +
                        "(Codigo_Cliente, " +
                        "Codigo_Produto, " +
                        "Quantidade, " +
                        "Valor," +
                        "Data_pedido)");
                sql.append("VALUES (?, ?, ?, ?, CURDATE() )");

                // Cadastra a medida e gera e busca id gerado
                PreparedStatement statement = conexao.prepareStatement(sql.toString(), com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, idCliente);
                statement.setInt(2, idProduto);
                statement.setInt(3, quantidade);
                statement.setDouble(4, (quantidade*valor));

                statement.executeUpdate();

                ResultSet resultset = statement.getGeneratedKeys();
                if (resultset.first()) {
                    int idPedido = resultset.getInt(1);
                    System.out.println("Pedido: "+idPedido+ " inserido com sucesso!");

                }
            } else {
                System.out.println("Cliente ou Produto inválido!");
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static int buscaIdCliente(String nomeCliente) {
        int retorno = 0;
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + " FROM E1_cliente"
                    + " WHERE nome_cliente = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nomeCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se retornou o registro solicitado
                retorno = rs.getInt("Codigo_Cliente");
            }
            rs.close();
            stmt.close();
            conexao.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            return retorno;
        }

    }

    public static int buscaIdProduto(String descProduto) {
        int retorno = 0;
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + " from E1_produto " +
                    "WHERE Nome_Produto = ? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, descProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se retornou o registro solicitado
                retorno = rs.getInt("Codigo_Produto");
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            return retorno;
        }
    }
    //. Relatório de pedidos (qual cliente comprou qual produto, em que data e qual o valor
//do pedido), ordenado pela data

    public static double buscaValorProduto(int idProduto){
        double retorno = 0F;
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT *"
                    + " from E1_produto " +
                    "WHERE Codigo_Produto = ? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se retornou o registro solicitado
                retorno = rs.getDouble("Preco");
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            return retorno;
        }
    }

    public static void relatorioPedido(){
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT\n" +
                    "  c.Nome_Cliente,\n" +
                    "  d.Nome_Produto,\n" +
                    "  p.Quantidade,\n" +
                    "  p.Valor\n" +
                    "FROM `E1_pedido` p INNER JOIN E1_cliente c ON p.Codigo_Cliente = c.Codigo_Cliente\n" +
                    "  INNER JOIN E1_produto d ON p.Codigo_Produto = d.Codigo_Produto";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // Se retornou o registro solicitado
                String nomeCliente = rs.getString("Nome_Cliente");
                String nomeProduto = rs.getString("Nome_Produto");
                double quantidade = rs.getDouble("Quantidade");
                double valor = rs.getDouble("Valor");
                System.out.println(
                        "Cliente: "+nomeCliente+"\n" +
                                "Comprou: "+nomeProduto+
                                "\nQuantidade: " +quantidade+
                                "\nValor: " +valor+
                                "\n=======================\n");
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}