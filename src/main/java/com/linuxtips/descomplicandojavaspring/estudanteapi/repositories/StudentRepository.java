package com.linuxtips.descomplicandojavaspring.estudanteapi.repositories;

import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataStudentDetails;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name);

    Optional<List<Student>> findByCurso(String curso);

    Optional<List<Student>> findByNameStartingWith(String comecoNome);

    Optional<List<Student>> findByNameStartingWithAndCurso(String comecoNome, String curso);

    Optional<List<Student>> findByEnderecoStartingWithOrderByEnderecoDesc(String endereco);

}
