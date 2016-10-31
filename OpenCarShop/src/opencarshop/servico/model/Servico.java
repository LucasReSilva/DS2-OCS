package opencarshop.servico.model;

public class Servico {
    
    private String descrição;
    private double valorPadrão;
    private  boolean valorFixo;
    private String valorF;
    private String valorP;
    public Servico(){
    }
    
    public Servico(String desc, double valor, boolean bfixo ){
        
        this.descrição=desc;
        this.valorPadrão=valor;
        this.valorFixo=bfixo;
        
    
    
    }
    
    public String getValorP(){
        return valorP;
    }
    
    public void setValorP(double v){
        String v2=Double.toString(v).replace(".", ",");
        this.valorP="R$ "+v2;
    }
    
    
    public String getValorF(){
        return valorF;
    }
    
    public void setValorF(boolean t){
        if(t){
            this.valorF="Sim";
        }else{
            this.valorF="Não";
        }
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
