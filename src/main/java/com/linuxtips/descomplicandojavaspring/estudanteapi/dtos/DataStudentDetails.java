package com.linuxtips.descomplicandojavaspring.estudanteapi.dtos;

import com.linuxtips.descomplicandojavaspring.estudanteapi.model.ContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;

import java.time.LocalDateTime;
import java.util.Optional;

public record DataStudentDetails(Long id, String name, String endereco, LocalDateTime criadoEm, LocalDateTime atualizadoEm, ContaBancaria contaBancaria, String curso) {


    public DataStudentDetails(Student data) {
        this(data.getId(),data.getName(), data.getEndereco(),data.getCriadoEm(),data.getAtualizadoEm(), data.getContaBancaria(), data.getCurso());
    }


    public DataStudentDetails(Optional<Student> student) {
        this(student.get().getId(),student.get().getName(), student.get().getEndereco(),student.get().getCriadoEm(),student.get().getAtualizadoEm(), student.get().getContaBancaria(), student.get().getCurso());
    }
}
