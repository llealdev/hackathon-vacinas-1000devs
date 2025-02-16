package com.vacinas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vacinas.util.Conexao;
import com.vacinas.model.Dose;

public class DoseDAO {
    private Conexao con;

    public DoseDAO() {
        con = new Conexao();
    }

    public void inserir(Dose dose){
        String sql= "INSERT INTO dose (id_vacina, dose, idade_recomendada_aplicacao) VALUES (?, ?, ?)";

        try (Connection conexao= con.getConexao();
            PreparedStatement comando= conexao.prepareStatement(sql)) {
            
            comando.setInt(1, dose.getIdVacina());
            comando.setString(2, dose.getDose());
            comando.setInt(3, dose.getIdadeRecomendadaAplicacao());

            comando.executeUpdate();

            ResultSet resultado= comando.getGeneratedKeys();
            if (resultado.next()) {
                dose.setId(resultado.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Dose dose){
        String sql = "UPDATE dose SET id_vacina = ?, dose = ?, idade_recomendada_aplicacao = ? WHERE id = ?";

        try (Connection conexao= con.getConexao();
            PreparedStatement comando= conexao.prepareStatement(sql)) {

            comando.setInt(1, dose.getIdVacina());
            comando.setString(2, dose.getDose());
            comando.setInt(3, dose.getIdadeRecomendadaAplicacao());
            comando.setInt(4, dose.getId());

            comando.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id){
        String sql= "DELETE FROM dose WHERE id = ?";

        try (Connection conexao= con.getConexao();
            PreparedStatement comando= conexao.prepareStatement(sql)) {
            
            comando.setInt(1, id);
            comando.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public Dose buscarPorId(int id){
        String sql= "SELECT * FROM dose WHERE id = ?";
        Dose dose = null;

        try (Connection conexao = con.getConexao();
            PreparedStatement comando= conexao.prepareStatement(sql)) {

                comando.setInt(1, id);
                ResultSet resultado = comando.executeQuery();
            
                if (resultado.next()) {
                    dose = new Dose(
                        resultado.getInt("id"),
                        resultado.getInt("id_vacina"),
                        resultado.getString("dose"),
                        resultado.getInt("idade_recomendade_aplicacao")
                    );
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dose;
    }

    public ArrayList<Dose> listarTodos(){
        String sql = "SELECT * FROM dose";
        ArrayList<Dose> listaDoses= new ArrayList<Dose>();

        try (Connection conexao = con.getConexao();
            Statement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery(sql)) {

                while(resultado.next()) {
                    listaDoses.add(new Dose(
                    resultado.getInt("id"),
                    resultado.getInt("id_vacina"),
                    resultado.getString("dose"),
                    resultado.getInt("id_recomendada_aplicacao")
                    ));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDoses;
    }  

}
