package com.vacinas.model;

import java.sql.Date;

public class Paciente {
    public enum Sexo{
        M, F;

    }
    
    private int id;
    private String nome;
    private String cpf;
    private Sexo sexo;
    private Date dataNascimento;

    public Paciente(){

    }

    public Paciente(int id, String nome, String cpf, Sexo sexo, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id= id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome= nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf= cpf;
    }

    public Sexo getSexo(){
        return sexo;
    }

    public void setSexo(Sexo sexo){
        this.sexo= sexo;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento= dataNascimento;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Nome: " + this.nome + ", Cpf: " + this.cpf + ", Sexo: " + this.sexo + ", Data Nascimento: " + this.dataNascimento;
    }
}
