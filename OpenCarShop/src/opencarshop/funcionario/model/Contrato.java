/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.funcionario.model;

import java.util.Date;

/**
 *
 * @author tharlysson breno
 */
public class Contrato {
    private Character cargo;
    private Double  salario;
    private Date dataInicio;
    private Date dataTermino;

    public Character getCargo() {
        return cargo;
    }

    public void setCargo(Character cargo) {
        this.cargo = cargo;
    }

    public Double  getSalario() {
        return salario;
    }

    public void setSalario(Double  salario) {
        this.salario = salario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    
}
