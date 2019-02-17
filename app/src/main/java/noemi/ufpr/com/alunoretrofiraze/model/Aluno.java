package noemi.ufpr.com.alunoretrofiraze.model;

/**
 * Created by noemi on 17/02/2019.
 */

public class Aluno {
    private Integer id;
    private String cpf;
    private String nome;
    private Integer idade;
    private Endereco endereco;

    public Aluno(Integer id) {
        this.id = id;
    }

    public Aluno(Integer id, String cpf, String nome, Integer idade, Endereco endereco) {

        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public Aluno(String cpf, String nome, Integer idade, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", endereco=" + endereco +
                '}';
    }
}
