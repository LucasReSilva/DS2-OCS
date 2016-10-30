/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencarshop.funcionario.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author tharlysson breno
 */
public class Contrato {
    private Character cargo;
    private Double  salario;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }
    
}
