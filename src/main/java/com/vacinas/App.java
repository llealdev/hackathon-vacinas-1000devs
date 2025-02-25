package com.vacinas;

import spark.Spark;

import com.vacinas.routes.Rotas;

import spark.Request;
import spark.Response;
import spark.Route;


public class App 
{
    
    public static void main( String[] args )
    {


        try {
            
            // Define a porta do servidor
            Spark.port(3051);   

            // Configuração do CORS (Cross-Origin Resource Sharing)
            Spark.options("/*", new Route(){
            
                @Override  
                public Object handle(Request requisicaoHttp, Response responseHttp) throws Exception{
                    String acessControlRquestHeaders = requisicaoHttp.headers("Access-Contral-Request-Headers");
    
                    if(acessControlRquestHeaders != null){
                        responseHttp.header("Access-Contral-Allow-Headers", acessControlRquestHeaders);
                    }
    
                    String accessControlRequestMethod = requisicaoHttp.headers("Access-Contral-Request-Headers");
    
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
            System.out.println("Servidor SparkJava rodando na porta 8080...");           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
