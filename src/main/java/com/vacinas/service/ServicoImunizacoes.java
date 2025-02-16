package com.vacinas.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinas.dao.ImunizacoesDAO;
import com.vacinas.model.Imunizacoes;

import spark.Request;
import spark.Response;
import spark.Route;

public class ServicoImunizacoes {

    public static Route cadastrarImunizacao() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                // Extrai os parâmetros do body da requisição HTTP
                int idPaciente = Integer.parseInt(request.queryParams("id_paciente"));
                int idDose = Integer.parseInt(request.queryParams("id_dose"));
                String dataAplicacaoStr = request.queryParams("data_aplicacao");
                String fabricante = request.queryParams("fabricante");
                String lote = request.queryParams("lote");
                String localAplicacao = request.queryParams("local_aplicacao");
                String profissionalAplicador = request.queryParams("profissional_aplicador");

                // Converter a Data
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dataAplicacaoStr, fmt);
                Date dataAplicacao = Date.valueOf(localDate.toString());

                // Cria o objeto imunização na memória
                Imunizacoes imunizacao = new Imunizacoes(0, idPaciente, idDose, dataAplicacao, fabricante, lote, localAplicacao, profissionalAplicador);

                try {
                    // Cadastra a imunização no banco de dados
                    ImunizacoesDAO.cadastrarVacina(imunizacao);

                    // 201 Created
                    response.status(201);
                    return "{\"message\": \"Imunização cadastrada com sucesso.\"}";

                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route buscarImunizacaoPorId() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                int id;

                try {
                    // Extrai o parâmetro id da URL (header HTTP), e converte para inteiro
                    id = Integer.parseInt(request.params(":id"));

                    Imunizacoes imunizacao = ImunizacoesDAO.buscarPorId(id);

                    if (imunizacao != null) {
                        // Define o HTTP status code
                        response.status(200); // 200 OK

                        // Retorna o objeto encontrado no formato JSON
                        return converteJson.writeValueAsString(imunizacao);
                    } else {
                        // Define o HTTP status code
                        response.status(404); // 404 Not Found
                        return "{\"message\": \"Nenhuma imunização encontrada com este ID.\"}";
                    }
                } catch (NumberFormatException e) {
                    // Define o HTTP status code
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                }
            }
        };
    }

    public static Route consultarTodasImunizacoes() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();

                // Busca todas as imunizações cadastradas
                List<Imunizacoes> imunizacoes = ImunizacoesDAO.listarImunizacoes();

                // Se a lista estiver vazia
                if (imunizacoes.isEmpty()) {
                    response.status(204); // 204 No Content
                    return "{\"message\": \"Nenhuma imunização encontrada no banco de dados.\"}";
                } else {
                    // Se não estiver vazia, devolve a lista convertida para JSON
                    response.status(200); // 200 OK
                    return converterJson.writeValueAsString(imunizacoes);
                }
            }
        };
    }

    public static Route alterarImunizacao() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    // Extrai o parâmetro id da URL
                    int id = Integer.parseInt(request.params(":id"));

                    // Extrai os parâmetros do body da requisição HTTP
                    int idPaciente = Integer.parseInt(request.queryParams("id_paciente"));
                    int idDose = Integer.parseInt(request.queryParams("id_dose"));
                    String dataAplicacaoStr = request.queryParams("data_aplicacao");
                    String fabricante = request.queryParams("fabricante");
                    String lote = request.queryParams("lote");
                    String localAplicacao = request.queryParams("local_aplicacao");
                    String profissionalAplicador = request.queryParams("profissional_aplicador");

                    // Converter a Data
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(dataAplicacaoStr, fmt);
                    Date dataAplicacao = Date.valueOf(localDate.toString());

                    // Cria o objeto imunização na memória
                    Imunizacoes imunizacao = new Imunizacoes(id, idPaciente, idDose, dataAplicacao, fabricante, lote, localAplicacao, profissionalAplicador);

                    // Atualiza a imunização no banco de dados
                    ImunizacoesDAO.atualizarImunizacoes(imunizacao);

                    response.status(200); // 200 OK
                    return "{\"message\": \"Imunização com id " + id + " foi atualizada com sucesso.\"}";

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route excluirImunizacao() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                try {
                    int id = Integer.parseInt(request.params(":id"));

                    // Exclui a imunização do banco de dados
                    ImunizacoesDAO.deletarImunizacoes(id);

                    response.status(200); // 200 OK
                    return "{\"message\": \"Imunização com id " + id + " foi excluída com sucesso.\"}";

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                }
            }
        };
    }

    public static Route excluirTodasImunizacoesPorPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));

                    // Exclui todas as imunizações do paciente
                    int rowsDeleted = ImunizacoesDAO.excluirTodasImunizacoesPorPaciente(idPaciente);

                    if (rowsDeleted > 0) {
                        response.status(200); // 200 OK
                        return "{\"message\": \"Todas as imunizações do paciente com id " + idPaciente + " foram excluídas com sucesso.\"}";
                    } else {
                        response.status(404); // 404 Not Found
                        return "{\"message\": \"Nenhuma imunização encontrada para o paciente com id " + idPaciente + ".\"}";
                    }

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route consultarImunizacoesPorPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));

                    // Busca todas as imunizações do paciente
                    List<Imunizacoes> imunizacoes = ImunizacoesDAO.consultarImunizacoesPorPaciente(idPaciente);

                    if (imunizacoes.isEmpty()) {
                        response.status(204); // 204 No Content
                        return "{\"message\": \"Nenhuma imunização encontrada para o paciente com id " + idPaciente + ".\"}";
                    } else {
                        response.status(200); // 200 OK
                        return converterJson.writeValueAsString(imunizacoes);
                    }

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route consultarImunizacoesPorPacienteEPeriodo() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    String dataInicioStr = request.params(":dt_ini");
                    String dataFimStr = request.params(":dt_fim");

                    // Converter as datas
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDateInicio = LocalDate.parse(dataInicioStr, fmt);
                    LocalDate localDateFim = LocalDate.parse(dataFimStr, fmt);
                    Date dataInicio = Date.valueOf(localDateInicio.toString());
                    Date dataFim = Date.valueOf(localDateFim.toString());

                    // Busca as imunizações do paciente no período especificado
                    List<Imunizacoes> imunizacoes = ImunizacoesDAO.consultarImunizacoesPorPacienteEPeriodo(idPaciente, dataInicio, dataFim);

                    if (imunizacoes.isEmpty()) {
                        response.status(204); // 204 No Content
                        return "{\"message\": \"Nenhuma imunização encontrada para o paciente com id " + idPaciente + " no período especificado.\"}";
                    } else {
                        response.status(200); // 200 OK
                        return converterJson.writeValueAsString(imunizacoes);
                    }

                } catch (NumberFormatException e) {
                    response.status(400); // 400 Bad Request
                    return "{\"message\": \"ID ou datas fornecidas estão no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }
}