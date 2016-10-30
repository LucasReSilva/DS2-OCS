package opencarshop.cliente.model;

public class Cliente {


    private int cdCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String data_nascimento;

    public Cliente(){
    }
    
    public Cliente(int cdCliente, String nome, String cpf, String email, String data_nascimento) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_nascimento = data_nascimento;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
        /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the data_nascimento
     */
    public String getData_nascimento() {
        return data_nascimento;
    }

    /**
     * @param data_nascimento the data_nascimento to set
     */
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
