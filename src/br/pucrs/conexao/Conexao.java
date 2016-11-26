package br.pucrs.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Santana on 11/15/2016.
 */
public class Conexao {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        String serverName = "mysql.gustavosantana.kinghost.net";
        String mydatabase ="gustavosantana31";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "gustavosantana31";        //nome de um usuário de seu BD
        String password = "oracle10";      //sua senha de acesso

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getConexao());
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }
}
