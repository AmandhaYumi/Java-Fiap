package br.com.fiap.models;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Essa classe foi criada para definição dos Usuários do sistema
 * e teste da criação do Javadoc
 * @author Eliane Marion
 * @version 1.0.0
 */
@XmlRootElement
public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(){}
   //Construtor

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    /*
    Comentário: construtor que recebe login e senha
    */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }


    public Usuario(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
