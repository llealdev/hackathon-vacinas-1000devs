package com.vacinas.model;

public class Dose {
    private int id;
    private int idVacina;
    private String dose;
    private int idadeRecomendadaAplicacao;
    
    public Dose(){
        
    }

    public Dose(int id, int idVacina, String dose, int idadeRecomendadaAplicacao) {
        setId(id);
        setId_vacina(idVacina);
        setDose(dose);
        setIdade_recomendada_aplicacao(idadeRecomendadaAplicacao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_vacina() {
        return idVacina;
    }

    public void setId_vacina(int idVacina) {
        this.idVacina = idVacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getIdade_recomendada_aplicacao() {
        return idadeRecomendadaAplicacao;
    }

    public void setIdade_recomendada_aplicacao(int idadeRecomendadaAplicacao) {
        this.idadeRecomendadaAplicacao = idadeRecomendadaAplicacao;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", idVacina:" + this.idVacina + ", dose:" + this.dose + ", idadeRecomendadaAplicacao:"
                + this.idadeRecomendadaAplicacao;
    }

}
