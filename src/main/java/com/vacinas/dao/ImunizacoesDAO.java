package com.vacinas.dao;

import java.sql.*;
import java.util.*;


import com.vacinas.model.Imunizacoes;
import com.vacinas.util.*;

public class ImunizacoesDAO{
    
    public void cadastrarVacina(Imunizacoes imunizacoes){
        String sql =  "INSERT INTO imunizacoes (id_paciente, id_dose, data_aplicacao, fabricante, lote, local_aplicacao, profissional_aplicador) VALUES (?, ?, ?, ?, ?, ?, ?);";

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
            stmt.setString(7, imunizacoes.getProfissionalAplicador() != null ? imunizacoes.getProfissionalAplicador() : null);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
            
            Conexao.closeConexao(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public Imunizacoes buscarPorId(int id) {
        String sql = "SELECT * FROM imunizacoes WHERE id = ?;";
        Imunizacoes imunizacao = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public List<Imunizacoes> listarImunizacoes(){
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

    public void atualizarImunizacoes(Imunizacoes imunizacoes) {
        String sql = "UPDATE imunizacoes SET id_paciente = ?, id_dose = ?, data_aplicacao = ?, fabricante = ?, lote = ?, local_aplicacao = ?, profissional_aplicador = ? " +
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
            smt.setString(7, imunizacoes.getProfissionalAplicador() != null ? imunizacoes.getProfissionalAplicador() : null);
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

    public void deletarVacina(int id) {
        String sql = "DELETE FROM imunizacoes WHERE id = ?;";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement smt = conn.prepareStatement(sql)) {

            smt.setInt(1, id);

            int rowsDeleted = smt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Vacina deletada com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

}
