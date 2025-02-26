package com.vacinas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {

    // Carrega as variáveis de ambiente do arquivo .env
    private static final Dotenv dotenv = Dotenv.configure().directory("./").load();

    // Obtém as variáveis de ambiente
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");


    //Atributos estáticos com os dados do Banco de Dados
    public static Connection getConexao() throws SQLException { 
        Connection connection = null;
      
        try {
            // Estabelecendo uma conexão com o banco de dados através da URL, usuário e senha
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida!");
        } catch (SQLException se) {

            throw new SQLException("Erro ao conectar! " + se.getMessage());
        }//fecha catch
        return connection;
    }//fecha metodo  
    

    public static void closeConexao(Connection connection){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

}
