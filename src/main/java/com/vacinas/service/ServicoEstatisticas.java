package com.vacinas.service;

import com.vacinas.dao.EstatisticasDAO;

import spark.Request;
import spark.Response;
import spark.Route;

public class ServicoEstatisticas {

    public static Route contarVacinasAplicadasPorPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                EstatisticasDAO estatisticasDAO = new EstatisticasDAO();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int total = estatisticasDAO.contarVacinasAplicadasPorPaciente(idPaciente);
                    response.status(200);
                    return "{\"total_vacinas_aplicadas\": " + total + "}";
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route contarVacinasProximoMesPorPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                EstatisticasDAO estatisticasDAO = new EstatisticasDAO();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int total = estatisticasDAO.contarVacinasProximoMesPorPaciente(idPaciente);
                    response.status(200);
                    return "{\"total_vacinas_proximo_mes\": " + total + "}";
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route contarVacinasAtrasadasPorPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                EstatisticasDAO estatisticasDAO = new EstatisticasDAO();

                try {
                    int idPaciente = Integer.parseInt(request.params(":id"));
                    int total = estatisticasDAO.contarVacinasAtrasadasPorPaciente(idPaciente);
                    response.status(200);
                    return "{\"total_vacinas_atrasadas\": " + total + "}";
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route contarVacinasAcimaDeIdade() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                EstatisticasDAO estatisticasDAO = new EstatisticasDAO();

                try {
                    int meses = Integer.parseInt(request.params(":meses"));
                    int total = estatisticasDAO.contarVacinasAcimaDeIdade(meses);
                    response.status(200);
                    return "{\"total_vacinas_acima_de_idade\": " + total + "}";
                } catch (NumberFormatException e) {
                    response.status(400);
                    return "{\"message\": \"Idade fornecida está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }
}