package com.vacinas.service;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinas.dao.DoseDAO;
import com.vacinas.model.Dose;

import spark.Request;
import spark.Response;
import spark.Route;

public class ServicoDose {

    public static Route buscarDosesPorIdVacina() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();
    
                int idVacina;
    
                try {
                    // Extrai o parâmetro id_vacina da URL (header HTTP), e converte para inteiro
                    idVacina = Integer.parseInt(request.params(":id_vacina"));
    
                    // Busca as doses no banco de dados
                    ArrayList<Dose> doses = DoseDAO.buscarPorIdVacina(idVacina);
    
                    if (!doses.isEmpty()) {
                        // Define o HTTP status code
                        response.status(200); // 200 OK
    
                        // Retorna a lista de doses no formato JSON
                        return converteJson.writeValueAsString(doses);
                    } else {
                        // Define o HTTP status code
                        response.status(404); // 404 Not Found
                        return "{\"message\": \"Nenhuma dose encontrada para a vacina com ID " + idVacina + ".\"}";
                    }
                } catch (NumberFormatException e) {
                    // Define o HTTP status code
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID da vacina fornecido está no formato incorreto.\"}";
                }
            }
        };
    }

    public static Route consultarTodasDoses() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();

                // Busca todas as doses cadastradas
                ArrayList<Dose> doses = DoseDAO.listarTodos();

                // Se a lista estiver vazia
                if (doses.isEmpty()) {
                    response.status(204); // 204 No Content
                    return "{\"message\": \"Nenhuma dose encontrada no banco de dados.\"}";
                } else {
                    // Se não estiver vazia, devolve a lista convertida para JSON
                    response.status(200); // 200 OK
                    return converterJson.writeValueAsString(doses);
                }
            }
        };
    }
}