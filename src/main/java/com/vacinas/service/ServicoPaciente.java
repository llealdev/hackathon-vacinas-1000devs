package com.vacinas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Date;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinas.dao.PacientesDAO;
import com.vacinas.model.Paciente;

import spark.Request;
import spark.Response;
import spark.Route;

public class ServicoPaciente {

public static Route cadastrarPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                // Ler JSON do corpo da requisição
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> jsonMap = objectMapper.readValue(request.body(), new TypeReference<Map<String, String>>() {});

                // Extrair os valores do JSON
                String nome = jsonMap.get("nome");
                String cpf = jsonMap.get("cpf");
                String sexoStr = jsonMap.get("sexo");
                String dataNascimentoStr = jsonMap.get("data_nascimento");

                // Converter a Data
                Date dataNascimento;
                if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(dataNascimentoStr, fmt);
                    dataNascimento = Date.valueOf(localDate); // Converter LocalDate para java.sql.Date

                } else {
                    throw new IllegalArgumentException("Data de nascimento não pode ser nula ou vazia.");
                }

                // Converter a string sexo para o enum Sexo
                Paciente.Sexo sexo = Paciente.Sexo.valueOf(sexoStr.toUpperCase());

                // cria o objeto usuario na memoria
                Paciente paciente = new Paciente(nome, cpf, sexo, dataNascimento);

                try {

                    // Retorna o ID
                    int idPaciente = PacientesDAO.cadastrarPaciente(paciente);

                    // 201 Created
                    response.status(201);

                    return "{\"message\": \"Paciente cadastrado com sucesso. ID: " + idPaciente + "\"}";

                } catch (Exception e) {
                    response.status(500); // 500 Internal Server Error
                    return "{\"message\": \"" + e.getMessage() + "\"}";
                }
            }
        };
    }

    public static Route buscarPacientePorId() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converteJson = new ObjectMapper();

                int id;

                try {
                    // extrai o parametro id da URL (header http), e converte para inteiro
                    id = Integer.parseInt(request.params(":id"));

                    Paciente paciente = PacientesDAO.buscarPorId(id);

                    if (paciente != null) {
                        // defini o http status code
                        response.status(200); // 200 OK

                        // retorna o objeto encontrado no formato json
                        return converteJson.writeValueAsString(paciente);
                    } else {
                        // defini o http status code
                        response.status(209); // 209 Consulta realizada com sucesso mas nao tem nenhum registro no banco
                        return "{\"message\": \"Nenhum usuário encontrado com este ID.\"}";
                    }
                } catch (NumberFormatException e) {
                    // defini o http status code
                    response.status(400); // 400 Requisicao incorreta, foi fornecido um id que nao pode ser convertido
                                          // para inteiro
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                }
            }
        };
    }

    public static Route consultarTodosPacientes() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper converterJson = new ObjectMapper();

                // busca todos os contatos cadastrados no ArrayList
                ArrayList<Paciente> paciente = PacientesDAO.listarPacientes();

                // se o arraylist estiver vazio
                if (paciente.isEmpty()) {
                    response.status(209);
                    return "{\"message\": \"Nenhum usuário encontrado no banco de dados.\"}";
                } else {
                    // se nao estiver vazio devolve o List convertido para json
                    response.status(200); // 200 Ok
                    return converterJson.writeValueAsString(paciente);
                }
            }
        };
    }

    public static Route alterarPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    // extrai os parametros do boddy da requisicao http
                    int id = Integer.parseInt(request.params(":id"));

                    // extrai os parametros do boddy da requisicao http
                    String nome = request.queryParams("nome");
                    String cpf = request.queryParams("cpf");
                    String sexoStr = request.queryParams("sexo");
                    String dataNascimentoStr = request.queryParams("data_nascimento");

                    // Converter a Data
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(dataNascimentoStr, fmt);
                    Date dataNascimento = Date.valueOf(localDate.toString()); // Converter LocalDate para java.sql.Date

                    // Converter a string sexo para o enum Sexo
                    Paciente.Sexo sexo = Paciente.Sexo.valueOf(sexoStr.toUpperCase());

                    // cria o objeto usuario na memoria
                    Paciente paciente = new Paciente(id, nome, cpf, sexo, dataNascimento);

                    int qtdeLinhasAlteradas = PacientesDAO.atualizarPaciente(paciente);

                    if (qtdeLinhasAlteradas > 0) {
                        response.status(200); // Ok
                        return "{\"message\": \"Usuário com id " + id + " foi atualizado com sucesso.\"}";
                        // se nao for maior que 0 nao existia o usuario no banco de dados
                    } else {
                        response.status(209); // 404 Not Found
                        return "{\"message\": \"O usuário com id " + id + " não foi encontrado.\"}";
                    }

                } catch (NumberFormatException e) {
                    // algum erro de conversao do id passado na url
                    response.status(400);
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                } catch (Exception e) {
                    response.status(500);
                    return "{\"message\": \"Erro ao processar a requisição.\"}";
                }
            }
        };
    }

    public static Route excluirPaciente() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                try {
                    int id = Integer.parseInt(request.params(":id"));

                    int linhasExcluidas = PacientesDAO.deletarPaciente(id);

                    if (linhasExcluidas > 0) {
                        response.status(200); // exclusão com sucesso
                        return "{\"message\": \"Usuário com id " + id + " foi excluído com sucesso.\"}";
                        // se nao forma maior que 0 o paciente nao existia no banco de dados
                    } else {
                        response.status(209); // id não encontrado
                        return "{\"message\": \"Usuário com id " + id + " foi encontrado no banco de dados.\"}";
                    }
                } catch (NumberFormatException e) { // alguma excecao na conversado do id fornecido na URL
                    response.status(400);
                    return "{\"message\": \"ID fornecido está no formato incorreto.\"}";
                }
            }
        };
    }

}
