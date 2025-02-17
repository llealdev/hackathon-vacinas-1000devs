package com.vacinas.routes;


import com.vacinas.service.ServicoEstatisticas;
import com.vacinas.service.ServicoImunizacoes;
import com.vacinas.service.ServicoPaciente;
import com.vacinas.service.ServicoVacina;

import spark.Spark;

public class Rotas {

    public static void processarRotas() {
        // Rotas para Paciente
        Spark.post("/paciente/inserir", ServicoPaciente.cadastrarPaciente());
        Spark.put("/paciente/alterar/:id", ServicoPaciente.alterarPaciente());
        Spark.delete("/paciente/excluir/:id", ServicoPaciente.excluirPaciente());
        Spark.get("/paciente/consultar", ServicoPaciente.consultarTodosPacientes());
        Spark.get("/paciente/consultar/:id", ServicoPaciente.buscarPacientePorId());

        // Rotas para Imunizações
        Spark.post("/imunizacao/inserir", ServicoImunizacoes.cadastrarImunizacao());
        Spark.put("/imunizacao/alterar/:id", ServicoImunizacoes.alterarImunizacao());
        Spark.delete("/imunizacao/excluir/:id", ServicoImunizacoes.excluirImunizacao());
        Spark.delete("/imunizacao/excluir/paciente/:id", ServicoImunizacoes.excluirTodasImunizacoesPorPaciente());
        Spark.get("/imunizacao/consultar", ServicoImunizacoes.consultarTodasImunizacoes());
        Spark.get("/imunizacao/consultar/:id", ServicoImunizacoes.buscarImunizacaoPorId());
        Spark.get("/imunizacao/consultar/paciente/:id", ServicoImunizacoes.consultarImunizacoesPorPaciente());
        Spark.get("/imunizacao/consultar/paciente/:id/aplicacao/:dt_ini/:dt_fim", ServicoImunizacoes.consultarImunizacoesPorPacienteEPeriodo());

        // Rotas para Vacinas
        Spark.get("/vacinas/consultar", ServicoVacina.consultarTodasVacinas());
        Spark.get("/vacinas/consultar/faixa_etaria/:faixa", ServicoVacina.consultarVacinasPorFaixaEtaria());
        Spark.get("/vacinas/consultar/idade_maior/:meses", ServicoVacina.consultarVacinasRecomendadasAcimaDeIdade());

        // Rotas para Estatísticas
        Spark.get("/estatisticas/imunizacoes/paciente/:id", ServicoEstatisticas.contarVacinasAplicadasPorPaciente());
        Spark.get("/estatisticas/proximas_imunizacoes/paciente/:id", ServicoEstatisticas.contarVacinasProximoMesPorPaciente());
        Spark.get("/estatisticas/imunizacoes_atrasadas/paciente/:id", ServicoEstatisticas.contarVacinasAtrasadasPorPaciente());
        Spark.get("/estatisticas/imunizacoes/idade_maior/:meses", ServicoEstatisticas.contarVacinasAcimaDeIdade());
    }
}