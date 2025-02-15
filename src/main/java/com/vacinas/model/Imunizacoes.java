package com.vacinas.model;

import java.sql.Date;

public class Imunizacoes {
    
    private int id;
    private int idPaciente;
    private int idDose;
    private Date dataAplicacao;
    private String fabricante;
    private String lote;
    private String localApicacao;
    private String profissionalAplicador;

    public Imunizacoes(){
        
    }

    public Imunizacoes(int id, int idPaciente, int idDose, Date dataAplicacao, String fabricante, String lote,
        String localApicacao, String profissionalAplicador) {
        setId(id);
        setId_paciente(idPaciente);
        setId_dose(idDose);
        setDataAplicacao(dataAplicacao);
        setFabricante(fabricante);
        setLote(lote);
        setLocalApicacao(localApicacao);
        setProfissionalAplicador(profissionalAplicador);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return idPaciente;
    }

    public void setId_paciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getId_dose() {
        return idDose;
    }

    public void setId_dose(int idDose) {
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

    public String getLocalApicacao() {
        return localApicacao;
    }

    public void setLocalApicacao(String localApicacao) {
        this.localApicacao = localApicacao;
    }

    public String getProfissionalAplicador() {
        return profissionalAplicador;
    }

    public void setProfissionalAplicador(String profissionalAplicador) {
        this.profissionalAplicador = profissionalAplicador;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Id_paciente:" + this.idPaciente + ", Id_dose:" + this.idDose + ", DataAplicacao:"
                + this.dataAplicacao + ", Fabricante:" + this.fabricante + ", Lote:" + this.lote + ", LocalApicacao:" + this.localApicacao
                + ", ProfissionalAplicador:" + this.profissionalAplicador;
    }

}
