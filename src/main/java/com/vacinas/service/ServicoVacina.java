package com.vacinas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinas.dao.VacinaDAO;
import com.vacinas.model.Vacina;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class ServicoVacina {

    public static Route consultarTodasVacinas() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();
                VacinaDAO vacinaDAO = new VacinaDAO();

                // Busca todas as vacinas cadastradas
                List<Vacina> vacinas = vacinaDAO.listarVacinas();

                // Se a lista estiver vazia
                if (vacinas.isEmpty()) {
                    response.status(204); // 204 No Content
                    return "{\"message\": \"Nenhuma vacina encontrada no banco de dados.\"}";
                } else {
                    // Se não estiver vazia, devolve a lista convertida para JSON
                    response.status(200); // 200 OK
                    return converterJson.writeValueAsString(vacinas);
                }
            }
        };
    }

    public static Route consultarVacinasPorFaixaEtaria() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();
                VacinaDAO vacinaDAO = new VacinaDAO();

                try {
                    // Extrai o parâmetro faixa etária da URL
                    String faixaEtariaStr = request.params(":faixa");

                    // Converte a string para o enum PublicoAlvo
                    Vacina.PublicoAlvo faixaEtaria = Vacina.PublicoAlvo.valueOf(faixaEtariaStr.toUpperCase());

                    // Busca todas as vacinas para a faixa etária especificada
                    List<Vacina> vacinas = vacinaDAO.listarVacinasPorFaixaEtaria(faixaEtaria);

                    if (vacinas.isEmpty()) {
                        response.status(204); // 204 No Content
                        return "{\"message\": \"Nenhuma vacina encontrada para a faixa etária " + faixaEtaria + ".\"}";
                    } else {
                        response.status(200); // 200 OK
                        return converterJson.writeValueAsString(vacinas);
                    }

                } catch (IllegalArgumentException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"Faixa etária fornecida é inválida. Valores válidos: CRIANÇA, ADOLESCENTE, ADULTO, GESTANTE.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route consultarVacinasRecomendadasAcimaDeIdade() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();
                VacinaDAO vacinaDAO = new VacinaDAO();

                try {
                    // Extrai o parâmetro idade em meses da URL
                    int meses = Integer.parseInt(request.params(":meses"));

                    // Busca todas as vacinas recomendadas acima da idade especificada
                    List<Vacina> vacinas = vacinaDAO.listarVacinasRecomendadasAcimaDeIdade(meses);

                    if (vacinas.isEmpty()) {
                        response.status(204); // 204 No Content
                        return "{\"message\": \"Nenhuma vacina recomendada acima de " + meses + " meses.\"}";
                    } else {
                        response.status(200); // 200 OK
                        return converterJson.writeValueAsString(vacinas);
                    }

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"Idade fornecida está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }
}