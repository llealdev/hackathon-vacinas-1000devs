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
        setId(id);
        setVacina(vacina);
        setDescricao(descricao);
        setLimite_aplicacao(limiteAplicacao);
        setPublico_alvo(publicoAlvo);
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

    public int getLimite_aplicacao() {
        return limiteAplicacao;
    }

    public void setLimite_aplicacao(int limiteAplicacao) {
        this.limiteAplicacao = limiteAplicacao;
    }

    public PublicoAlvo getPublico_alvo() {
        return publicoAlvo;
    }

    public void setPublico_alvo(PublicoAlvo publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    @Override
    public String toString() {
        return "ID:" + this.id + ", Vacina:" + this.vacina + ", Descricao:" + this.descricao + ", Limite Aplicacao:"
                + this.limiteAplicacao + ", Publico Alvo:" + this.publicoAlvo;
    }

}