package com.vacinas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Atributos estáticos com os dados do Banco de Dados
    private static String URL = "";
    private static String USUARIO = "root";
    private static String SENHA = "1";

    public static Connection getConexao() throws SQLException {

        Connection c = null;
        try {
            // Estabelecendo uma conexão com o banco de dados através da URL, usuário e senha
            c = DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException se) {

            throw new SQLException("Erro ao conectar! " + se.getMessage());
        }//fecha catch

        return c;
    }//fecha metodo  
    

    public static void closeConexao(Connection c){
        if(c != null){
            try {
                c.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
