package com.vacinas.model;

public class Vacina {
    public enum PublicoAlvo{
        CRIANÇA, ADOLESCENTE, ADULTO, GESTANTE;

    }
    
    private int id;
    private String vacina;
    private String descricao;
    private int limiteAplicacao;
    private PublicoAlvo publicoAlvo;

    public Vacina() {

    }
    
    public Vacina(int id, String vacina, String descricao, int limiteAplicacao, PublicoAlvo publicoAlvo) {
        this.id = id;
        this.vacina = vacina;
        this.descricao = descricao;
        this.limiteAplicacao = limiteAplicacao;
        this.publicoAlvo = publicoAlvo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public void setLimiteAplicacao(int limiteAplicacao) {
        this.limiteAplicacao = limiteAplicacao;
    }

    public PublicoAlvo getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(PublicoAlvo publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Vacina:" + this.vacina + ", Descricao:" + this.descricao + ", Limite Aplicacao:"
                + this.limiteAplicacao + ", Publico Alvo:" + this.publicoAlvo;
    }

}