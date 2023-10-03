package com.linuxtips.descomplicandojavaspring.estudanteapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataCreateStudent;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataStudentDetails;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "students")
@Entity(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, length = 10)
    private String endereco;
    @Column(nullable = false)
    private String curso;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime atualizadoEm;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "student_conta",
    joinColumns = {@JoinColumn (name = "student_id", referencedColumnName = "id" )},
            inverseJoinColumns = {@JoinColumn(name = "contaBancaria_id", referencedColumnName = "id")})
    @JsonManagedReference
    private ContaBancaria contaBancaria;

    public Student() {}

    public Student(Long id, String name, String endereco, String curso, LocalDateTime criadoEm, LocalDateTime atualizadoEm, ContaBancaria contaBancaria) {
        this.id = id;
        this.name = name;
        this.endereco = endereco;
        this.curso = curso;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.contaBancaria = contaBancaria;
    }

    public Student(DataCreateStudent data) {
        this.name = data.name();
        this.endereco = data.endereco();
        this.curso = data.curso();
        this.contaBancaria = data.contaBancaria();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updateUser(DataStudentDetails data) {
        setCurso(data.curso());
        setEndereco(data.endereco());
        setName(data.name());
        setCurso(data.curso());
    }

}
