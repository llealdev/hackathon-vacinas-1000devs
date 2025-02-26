package com.vacinas;

import spark.Spark;

import com.vacinas.routes.Rotas;
import com.vacinas.util.Conexao;
import java.sql.*;


import spark.Request;
import spark.Response;
import spark.Route;


public class Main 
{
    
    public static void main( String[] args )
    {


        try {
             Connection conn = Conexao.getConexao();

             System.out.println(conn);
             
            // Define a porta do servidor
            Spark.port(3051);   

            // Configuração do CORS (Cross-Origin Resource Sharing)
            Spark.options("/*", new Route(){
            
                @Override  
                public Object handle(Request requisicaoHttp, Response responseHttp) throws Exception{
                    String accessControlRequestHeaders = requisicaoHttp.headers("Access-Control-Request-Headers");
    
                    if(accessControlRequestHeaders != null){
                        responseHttp.header("Access-Contral-Allow-Headers", accessControlRequestHeaders);
                    }
    
                    String accessControlRequestMethod = requisicaoHttp.headers("Access-Control-Request-Method");
    
                    if (accessControlRequestMethod != null){
                        responseHttp.header("Access-Contral-Allow-Method", accessControlRequestMethod);
                    }
    
                    return "OK";
                }
            });
    
            // Configuração global do CORS para todas as rotas
            Spark.before((requisicaoHttp, respostaHttp) -> {
                respostaHttp.header("Access-Control-Allow-Origin", "*"); // Permite todas as origens
                respostaHttp.header("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
                respostaHttp.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            });

            // Executa o método para cadastrar as rotas no Spark
            Rotas.processarRotas();

            // Mensagem de inicialização do servidor
            System.out.println("Servidor SparkJava rodando na porta 3051...");           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
