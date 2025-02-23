package com.vacinas.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import com.vacinas.dtos.ImunizacaoDTO;
import com.vacinas.model.Imunizacoes;
import com.vacinas.util.*;

public class ImunizacoesDAO {

    // Método para cadastrar uma imunização
    public static void cadastrarVacina(Imunizacoes imunizacoes) {
        String sql = "INSERT INTO imunizacoes (id_paciente, id_dose, data_aplicacao, fabricante, lote, local_aplicacao, profissional_aplicador) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Definindo os valores (campos que não são opcionais)
            stmt.setInt(1, imunizacoes.getIdPaciente());
            stmt.setInt(2, imunizacoes.getIdDose());
            stmt.setDate(3, imunizacoes.getDataAplicacao());

            // Campos opcionais
            stmt.setString(4, imunizacoes.getFabricante() != null ? imunizacoes.getFabricante() : null);
            stmt.setString(5, imunizacoes.getLote() != null ? imunizacoes.getLote() : null);
            stmt.setString(6, imunizacoes.getLocalAplicacao() != null ? imunizacoes.getLocalAplicacao() : null);
            stmt.setString(7,
                    imunizacoes.getProfissionalAplicador() != null ? imunizacoes.getProfissionalAplicador() : null);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

            Conexao.closeConexao(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar uma imunização por ID
    public static Imunizacoes buscarPorId(int id) {
        String sql = "SELECT * FROM imunizacoes WHERE id = ?;";
        Imunizacoes imunizacao = null;

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                imunizacao = new Imunizacoes();
                imunizacao.setId(rs.getInt("id"));
                imunizacao.setIdPaciente(rs.getInt("id_paciente"));
                imunizacao.setIdDose(rs.getInt("id_dose"));
                imunizacao.setDataAplicacao(rs.getDate("data_aplicacao"));
                imunizacao.setFabricante(rs.getString("fabricante"));
                imunizacao.setLote(rs.getString("lote"));
                imunizacao.setLocalAplicacao(rs.getString("local_aplicacao"));
                imunizacao.setProfissionalAplicador(rs.getString("profissional_aplicador"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imunizacao;
    }

    // Método para listar todas imunizações
    public static List<Imunizacoes> listarImunizacoes() {
        List<Imunizacoes> imunizacoes = new ArrayList<>();

        String sql = "SELECT * FROM imunizacoes;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Imunizacoes imunizacao = new Imunizacoes();
                imunizacao.setId(rs.getInt("id"));
                imunizacao.setIdPaciente(rs.getInt("id_paciente"));
                imunizacao.setIdDose(rs.getInt("id_dose"));
                imunizacao.setDataAplicacao(rs.getDate("data_aplicacao"));
                imunizacao.setFabricante(rs.getString("fabricante"));
                imunizacao.setLote(rs.getString("lote"));
                imunizacao.setLocalAplicacao(rs.getString("local_aplicacao"));
                imunizacao.setProfissionalAplicador(rs.getString("profissional_aplicador"));

                imunizacoes.add(imunizacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imunizacoes;
    }

    public static List<ImunizacaoDTO> listarImunizacoesFormatadas() {
        List<ImunizacaoDTO> imunizacoesDTO = new ArrayList<>();

        String sql = "SELECT "
                + " p.nome AS nome_paciente, "
                + " v.vacina AS nome_vacina, "
                + " d.dose AS dose, "
                + " i.data_aplicacao AS data_aplicacao, "
                + " COALESCE(i.fabricante, '') AS fabricante, "
                + " COALESCE(i.lote, '') AS lote, "
                + " COALESCE(i.local_aplicacao, '') AS local_aplicacao, "
                + " COALESCE(i.profissional_aplicador, '') AS profissional_aplicador "
                + " FROM imunizacoes i "
                + " INNER JOIN paciente p ON p.id = i.id_paciente "
                + " INNER JOIN dose d ON d.id = i.id_dose "
                + " INNER JOIN vacina v ON v.id = d.id_vacina "
                + " ORDER BY i.data_aplicacao;";

        try (Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ImunizacaoDTO imunizacaoDTO = new ImunizacaoDTO();

                // Mapeando os resultados da consulta para o DTO
                imunizacaoDTO.setNomePaciente(rs.getString("nome_paciente"));
                imunizacaoDTO.setNomeVacina(rs.getString("nome_vacina"));
                imunizacaoDTO.setDose(rs.getString("dose"));
                imunizacaoDTO.setDataAplicacao(rs.getObject("data_aplicacao", LocalDate.class));
                imunizacaoDTO.setFabricante(rs.getString("fabricante"));
                imunizacaoDTO.setLote(rs.getString("lote"));
                imunizacaoDTO.setLocalAplicacao(rs.getString("local_aplicacao"));
                imunizacaoDTO.setProfissionalAplicador(rs.getString("profissional_aplicador"));

                imunizacoesDTO.add(imunizacaoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar imunizações formatadas no banco de dados.", e);
        }

        return imunizacoesDTO;
    }

    // Método para atualizar os dados de uma imunização
    public static void atualizarImunizacoes(Imunizacoes imunizacoes) {
        String sql = "UPDATE imunizacoes SET id_paciente = ?, id_dose = ?, data_aplicacao = ?, fabricante = ?, lote = ?, local_aplicacao = ?, profissional_aplicador = ? "
                +
                "WHERE id = ?;";

        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement smt = conn.prepareStatement(sql);

            smt.setInt(1, imunizacoes.getIdPaciente());
            smt.setInt(2, imunizacoes.getIdDose());
            smt.setDate(3, imunizacoes.getDataAplicacao());
            smt.setString(4, imunizacoes.getFabricante() != null ? imunizacoes.getFabricante() : null);
            smt.setString(5, imunizacoes.getLote() != null ? imunizacoes.getLote() : null);
            smt.setString(6, imunizacoes.getLocalAplicacao() != null ? imunizacoes.getLocalAplicacao() : null);
            smt.setString(7,
                    imunizacoes.getProfissionalAplicador() != null ? imunizacoes.getProfissionalAplicador() : null);
            smt.setInt(8, imunizacoes.getId());

            int rowsUpdated = smt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Vacina atualizada com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado para atualizar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma imunizacação
    public static void deletarImunizacoes(int id) {
        String sql = "DELETE FROM imunizacoes WHERE id = ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Vacina deletada com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir todas as imunizações de um paciente
    public static int excluirTodasImunizacoesPorPaciente(int idPaciente) {
        String sql = "DELETE FROM imunizacoes WHERE id_paciente = ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPaciente);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para consultar imunizações por ID do paciente
    public static List<Imunizacoes> consultarImunizacoesPorPaciente(int idPaciente) {
        List<Imunizacoes> imunizacoes = new ArrayList<>();
        String sql = "SELECT * FROM imunizacoes WHERE id_paciente = ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Imunizacoes imunizacao = new Imunizacoes();
                imunizacao.setId(rs.getInt("id"));
                imunizacao.setIdPaciente(rs.getInt("id_paciente"));
                imunizacao.setIdDose(rs.getInt("id_dose"));
                imunizacao.setDataAplicacao(rs.getDate("data_aplicacao"));
                imunizacao.setFabricante(rs.getString("fabricante"));
                imunizacao.setLote(rs.getString("lote"));
                imunizacao.setLocalAplicacao(rs.getString("local_aplicacao"));
                imunizacao.setProfissionalAplicador(rs.getString("profissional_aplicador"));

                imunizacoes.add(imunizacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imunizacoes;
    }

    // Método para consultar imunizações por ID do paciente e intervalo de datas
    public static List<Imunizacoes> consultarImunizacoesPorPacienteEPeriodo(int idPaciente, java.sql.Date dataInicio,
            java.sql.Date dataFim) {
        List<Imunizacoes> imunizacoes = new ArrayList<>();
        String sql = "SELECT * FROM imunizacoes WHERE id_paciente = ? AND data_aplicacao BETWEEN ? AND ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPaciente);
            stmt.setDate(2, dataInicio);
            stmt.setDate(3, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Imunizacoes imunizacao = new Imunizacoes();
                imunizacao.setId(rs.getInt("id"));
                imunizacao.setIdPaciente(rs.getInt("id_paciente"));
                imunizacao.setIdDose(rs.getInt("id_dose"));
                imunizacao.setDataAplicacao(rs.getDate("data_aplicacao"));
                imunizacao.setFabricante(rs.getString("fabricante"));
                imunizacao.setLote(rs.getString("lote"));
                imunizacao.setLocalAplicacao(rs.getString("local_aplicacao"));
                imunizacao.setProfissionalAplicador(rs.getString("profissional_aplicador"));

                imunizacoes.add(imunizacao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imunizacoes;
    }

}
