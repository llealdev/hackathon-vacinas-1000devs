package com.vacinas.dao;

import com.vacinas.model.Vacina;
import com.vacinas.util.Conexao;

import java.sql.*;
import java.util.*;

public class VacinaDAO {

    // Método para cadastrar uma vacina
    public void cadastrarVacina(Vacina vacina) {
        String sql = "INSERT INTO vacina (vacina, descricao, limite_aplicacao, publico_alvo) VALUES (?, ?, ?, ?);";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, vacina.getVacina());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getLimiteAplicacao());
            stmt.setString(4, vacina.getPublicoAlvo().toString());

            stmt.executeUpdate();
            System.out.println("Vacina cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar uma vacina por ID
    public Vacina buscarPorId(int id) {
        String sql = "SELECT * FROM vacina WHERE id = ?;";
        Vacina vacina = null;

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setVacina(rs.getString("vacina"));
                vacina.setDescricao(rs.getString("descricao"));
                vacina.setLimiteAplicacao(rs.getInt("limite_aplicacao"));
                vacina.setPublicoAlvo(Vacina.PublicoAlvo.valueOf(rs.getString("publico_alvo")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacina;
    }

    // Método para listar todas as vacinas
    public List<Vacina> listarVacinas() {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacina;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setVacina(rs.getString("vacina"));
                vacina.setDescricao(rs.getString("descricao"));
                vacina.setLimiteAplicacao(rs.getInt("limite_aplicacao"));
                vacina.setPublicoAlvo(Vacina.PublicoAlvo.valueOf(rs.getString("publico_alvo")));

                vacinas.add(vacina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacinas;
    }

    // Método para atualizar os dados de uma vacina
    public void atualizarVacina(Vacina vacina) {
        String sql = "UPDATE vacina SET vacina = ?, descricao = ?, limite_aplicacao = ?, publico_alvo = ? WHERE id = ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, vacina.getVacina());
            stmt.setString(2, vacina.getDescricao());
            stmt.setInt(3, vacina.getLimiteAplicacao());
            stmt.setString(4, vacina.getPublicoAlvo().toString());
            stmt.setInt(5, vacina.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Vacina atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma vacina encontrada para atualizar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma vacina
    public void deletarVacina(int id) {
        String sql = "DELETE FROM vacina WHERE id = ?;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Vacina deletada com sucesso!");
            } else {
                System.out.println("Nenhuma vacina encontrada para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}