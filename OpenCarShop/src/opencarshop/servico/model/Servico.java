package opencarshop.servico.model;

public class Servico {

    private String descricao;
    private double valorPadrao;
    private boolean valorFixo;
    private int id;

    private String valorF;
    private String valorP;

    public Servico() {
    }

    public Servico(String desc, double valor, boolean bfixo) {

        this.descricao = desc;
        this.valorPadrao = valor;
        this.valorFixo = bfixo;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(double valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public boolean getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(boolean valorFixo) {
        this.valorFixo = valorFixo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValorP() {
        return valorP;
    }

    public void setValorP(double v) {
        String v2 = Double.toString(v).replace(".", ",");
        this.valorP = "R$ " + v2;
    }

    public String getValorF() {
        return valorF;
    }

    public void setValorF(boolean t) {
        if (t) {
            this.valorF = "Sim";
        } else {
            this.valorF = "Não";
        }
    }
}
