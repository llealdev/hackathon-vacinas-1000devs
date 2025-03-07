package com.vacinas.model;

import java.sql.*;

public class Imunizacoes {
    
    private int id;
    private int idPaciente;
    private int idDose;
    private Date dataAplicacao;
    private String fabricante;
    private String lote;
    private String localAplicacao;
    private String profissionalAplicador;

    public Imunizacoes(){

    }

    public Imunizacoes(int id, int idPaciente, int idDose, Date dataAplicacao, String fabricante, String lote,
            String localAplicacao, String profissionalAplicador) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDose = idDose;
        this.dataAplicacao = dataAplicacao;
        this.fabricante = fabricante;
        this.lote = lote;
        this.localAplicacao = localAplicacao;
        this.profissionalAplicador = profissionalAplicador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdDose() {
        return idDose;
    }

    public void setIdDose(int idDose) {
        this.idDose = idDose;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getLocalAplicacao() {
        return localAplicacao;
    }

    public void setLocalAplicacao(String localAplicacao) {
        this.localAplicacao = localAplicacao;
    }

    public String getProfissionalAplicador() {
        return profissionalAplicador;
    }

    public void setProfissionalAplicador(String profissionalAplicador) {
        this.profissionalAplicador = profissionalAplicador;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Id Paciente:" + this.idPaciente + ", Id Dose:" + this.idDose + ", Data Aplicacao:"
                + this.dataAplicacao + ", Fabricante:" + this.fabricante + ", Lote:" + this.lote + ", Local Apicacao:" + this.localAplicacao
                + ", Profissional Aplicador:" + this.profissionalAplicador;
    }

}
