package opencarshop.servico.model;

public class Servico {
    
    private String descrição;
    private double valorPadrão;
    private  boolean valorFixo;
    
    
    public Servico(String desc, double valor, boolean bfixo ){
        
        this.descrição=desc;
        this.valorPadrão=valor;
        this.valorFixo=bfixo;
        
    
    
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public double getValorPadrão() {
        return valorPadrão;
    }

    public void setValorPadrão(double valorPadrão) {
        this.valorPadrão = valorPadrão;
    }

    public boolean isValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(boolean valorFixo) {
        this.valorFixo = valorFixo;
    }
    
    
    
    
    
    
}
