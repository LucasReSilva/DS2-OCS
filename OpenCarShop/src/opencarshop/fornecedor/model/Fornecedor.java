package opencarshop.fornecedor.model;

public class Fornecedor {
    private String razaoSocial;
    private String cnpj;
    private String email;
    private String descricao;
    private String telefone1;
    private String telefone2;
    
        public Fornecedor(){
    }
    
    public Fornecedor(String razao, String cnpj, String email, String descricao,String telefone1,String telefone2) {
        this.razaoSocial = razao;
        this.cnpj = cnpj;
        this.email = email;
        this.descricao = descricao;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
    
    
    
}
