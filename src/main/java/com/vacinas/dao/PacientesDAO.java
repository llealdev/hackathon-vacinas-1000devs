package com.vacinas.dao;

import java.sql.*;
import java.util.*;

import com.vacinas.model.Paciente;
import com.vacinas.util.Conexao;

public class PacientesDAO {
    
    // Método para cadastrar um paciente
    public void cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, sexo, data_nascimento) VALUES (?, ?, ?, ?);";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo().toString());
            stmt.setDate(4, paciente.getDataNascimento());

            stmt.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um paciente por ID
    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?;";
        Paciente paciente = null;

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSexo(Paciente.Sexo.valueOf(rs.getString("sexo")));
                paciente.setDataNascimento(rs.getDate("data_nascimento"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    // Método para listar todos os pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente;";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSexo(Paciente.Sexo.valueOf(rs.getString("sexo")));
                paciente.setDataNascimento(rs.getDate("data_nascimento"));

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }
    
    // Método para atualizar os dados de um paciente
    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, sexo = ?, data_nascimento = ? WHERE id = ?;";

        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo().toString());
            stmt.setDate(4, paciente.getDataNascimento());
            stmt.setInt(5, paciente.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("Nenhum paciente encontrado para atualizar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para deletar um paciente
    public void deletarPaciente(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?;";

        try {

            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Paciente deletado com sucesso!");
            } else {
                System.out.println("Nenhum paciente encontrado para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
