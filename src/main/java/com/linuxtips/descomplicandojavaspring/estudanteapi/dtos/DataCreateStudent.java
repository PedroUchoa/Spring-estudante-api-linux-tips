package com.linuxtips.descomplicandojavaspring.estudanteapi.dtos;

import com.linuxtips.descomplicandojavaspring.estudanteapi.model.ContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;

public record DataCreateStudent(String name, String endereco, ContaBancaria contaBancaria, String curso) {

    public DataCreateStudent(Student data) {
        this(data.getName(), data.getEndereco(), data.getContaBancaria(), data.getCurso());
    }


}
