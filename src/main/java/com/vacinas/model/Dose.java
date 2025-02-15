package com.vacinas.model;

public class Dose {
    private int id;
    private int idVacina;
    private String dose;
    private int idadeRecomendadaAplicacao;

    public Dose(){

    }

    public Dose(int id, int idVacina, String dose, int idadeRecomendadaAplicacao) {
        this.id = id;
        this.idVacina = idVacina;
        this.dose = dose;
        this.idadeRecomendadaAplicacao = idadeRecomendadaAplicacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(int idVacina) {
        this.idVacina = idVacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getIdadeRecomendadaAplicacao() {
        return idadeRecomendadaAplicacao;
    }

    public void setIdadeRecomendadaAplicacao(int idadeRecomendadaAplicacao) {
        this.idadeRecomendadaAplicacao = idadeRecomendadaAplicacao;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Id Vacina:" + this.idVacina + ", Dose:" + this.dose + ", Idade Recomendada Aplicacao:"
                + this.idadeRecomendadaAplicacao;
    }

}
