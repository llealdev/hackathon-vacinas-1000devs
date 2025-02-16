package com.vacinas.dao;

import com.vacinas.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstatisticasDAO {

    // Método para contar vacinas aplicadas por paciente
    public int contarVacinasAplicadasPorPaciente(int idPaciente) {
        String sql = "SELECT COUNT(*) AS total FROM imunizacoes WHERE id_paciente = ?;";
        int total = 0;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    // Método para contar vacinas aplicáveis no próximo mês por paciente
    public int contarVacinasProximoMesPorPaciente(int idPaciente) {
        String sql = "SELECT COUNT(*) AS total " +
                     "FROM dose d " +
                     "WHERE d.idade_recomendada_aplicacao <= (SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, NOW()) + 1 " +
                     "AND d.id NOT IN (SELECT id_dose FROM imunizacoes WHERE id_paciente = ?);";
        int total = 0;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    // Método para contar vacinas atrasadas por paciente
    public int contarVacinasAtrasadasPorPaciente(int idPaciente) {
        String sql = "SELECT COUNT(*) AS total " +
                     "FROM dose d " +
                     "WHERE d.idade_recomendada_aplicacao < (SELECT TIMESTAMPDIFF(MONTH, p.data_nascimento, NOW()) FROM paciente p WHERE p.id = ?) " +
                     "AND d.id NOT IN (SELECT id_dose FROM imunizacoes WHERE id_paciente = ?);";
        int total = 0;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            stmt.setInt(2, idPaciente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    // Método para contar vacinas recomendadas acima de uma idade em meses
    public int contarVacinasAcimaDeIdade(int meses) {
        String sql = "SELECT COUNT(*) AS total FROM dose WHERE idade_recomendada_aplicacao > ?;";
        int total = 0;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, meses);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
}