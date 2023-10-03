package com.linuxtips.descomplicandojavaspring.estudanteapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.linuxtips.descomplicandojavaspring.estudanteapi.enums.TipoContaBancaria;
import jakarta.persistence.*;

@Table(name = "contasBancarias")
@Entity(name = "ContaBancaria")
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer agencia;
    @Column(nullable = false, unique = true)
    private Integer conta;
    @Column(nullable = false)
    private Integer digito;
    @Column(nullable = false)
    private TipoContaBancaria tipoContaBancaria;
    @OneToOne(mappedBy = "contaBancaria")
    @JsonBackReference
    private Student student;

    public ContaBancaria() {}

    public ContaBancaria(Long id, Integer agencia, Integer conta, Integer digito, TipoContaBancaria tipoContaBancaria, Student student) {
        this.id = id;
        this.agencia = agencia;
        this.conta = conta;
        this.digito = digito;
        this.tipoContaBancaria = tipoContaBancaria;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

    public TipoContaBancaria getTipoContaBancaria() {
        return tipoContaBancaria;
    }

    public void setTipoContaBancaria(TipoContaBancaria tipoContaBancaria) {
        this.tipoContaBancaria = tipoContaBancaria;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
