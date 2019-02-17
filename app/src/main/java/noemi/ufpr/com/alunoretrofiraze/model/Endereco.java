package noemi.ufpr.com.alunoretrofiraze.model;

/**
 * Created by noemi on 17/02/2019.
 */

public class Endereco {

    private Integer id;
    private Integer numero;
    private String logradouro;
    private String complemento;
    private String bairro;

    public Endereco(){}

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, Integer numero, String logradouro, String complemento, String bairro) {
        this.id = id;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public Endereco(Integer numero, String logradouro, String complemento, String bairro) {

        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
