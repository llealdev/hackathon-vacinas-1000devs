package com.vacinas.dao;

import java.sql.*;
import java.util.*;

import com.vacinas.model.Paciente;
import com.vacinas.util.Conexao;

public class PacientesDAO {
    
    // Método para cadastrar um paciente
    public static int cadastrarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, sexo, data_nascimento) VALUES (?, ?, ?, ?);";
    
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo().toString());
            stmt.setDate(4, paciente.getDataNascimento());
    
            stmt.executeUpdate();
    
            // Obtendo o ID gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna o ID gerado
            }
    
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;  // Caso não consiga retornar o ID
    }

    // Método para buscar um paciente por ID
    public static Paciente buscarPorId(int id) {
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
    public static ArrayList<Paciente> listarPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
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
    public static int atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, sexo = ?, data_nascimento = ? WHERE id = ?;";
    
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo().toString());
            stmt.setDate(4, paciente.getDataNascimento());
            stmt.setInt(5, paciente.getId());
    
            int rowsUpdated = stmt.executeUpdate(); // Retorna o número de linhas afetadas
            return rowsUpdated; 
    
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;  // Retorna -1 em caso de erro
    }
    
    // Método para deletar um paciente
    public static int deletarPaciente(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?;";
    
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
    
            stmt.setInt(1, id);
    
            int rowsDeleted = stmt.executeUpdate(); // Retorna o número de linhas deletadas
            return rowsDeleted;
    
        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Retorna -1 em caso de erro
    }
}
