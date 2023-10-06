package com.linuxtips.descomplicandojavaspring.estudanteapi;

import com.linuxtips.descomplicandojavaspring.estudanteapi.enums.TipoContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.ContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;
import com.linuxtips.descomplicandojavaspring.estudanteapi.repositories.StudentRepository;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Sucesso - Deve salvar estudante na base com sucesso")
    public void DeveSalvarEstudanteNaBaseComSucesso(){
        Student studentSalvo = this.studentRepository.save(buildMockStudent());
        assertNotNull(studentSalvo);
        assertEquals("java", studentSalvo.getCurso());
    }

    @Test
    @DisplayName("Sucesso - Deve listar todos os estudantes com estudantes com sucesso")
    public void DeveSalvarListarEstudanteCadastradosComSucesso(){
       this.studentRepository.save(buildMockStudent());
        List<Student> studentList = this.studentRepository.findAll();
        assertEquals(1,studentList.size());
    }

    @Test
    @DisplayName("Sucesso - Deve excluir estudante na base com sucesso")
    public void DeveExcluirEstudanteNaBaseComSucesso(){
        this.studentRepository.deleteById(1L);
        Optional<Student> student = this.studentRepository.findById(1L);
        assertFalse(student.isPresent());
    }

    @Test
    @DisplayName("Sucesso - Deve Busca estudante pelo nome na base sucesso")
    public void DeveBuscarEstudantePeloNomeNaBaseComSucesso(){
        this.studentRepository.save(buildMockStudent());
        Optional<Student> student = this.studentRepository.findByName("aluno");
        assertTrue(student.isPresent());
    }

    @Test
    @DisplayName("Sucesso - Deve Busca estudante pelo nome e curso na base sucesso")
    public void DeveBuscarEstudantePeloNomeECursoComSucesso(){
        this.studentRepository.save(buildMockStudent());
        List<Student> students = this.studentRepository.findByNameStartingWithAndCurso("aluno", "java").orElse(null);
        assertEquals(1, students.size());
    }

    @Test
    @DisplayName("Sucesso - Deve Buscar estudante pelo endereço na base com sucesso")
    public void DeveBuscarEstudantePeloEnderecoComSucesso(){
        this.studentRepository.save(buildMockStudent());
        List<Student> students = this.studentRepository.findByEnderecoStartingWithOrderByEnderecoDesc("rua").orElse(null);
        assertEquals(1,students.size());
    }

    @Test
    @DisplayName("Sucesso - Deve Buscar estudante pelo curso na base com sucesso")
    public void DeveBuscarEstudantePeloCursoComSucesso(){
        this.studentRepository.save(buildMockStudent());
        List<Student> students = this.studentRepository.findByCurso("java").orElse(null);
        assertEquals(1,students.size());
    }

    private Student buildMockStudent(){
        Student student = new Student();
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setTipoContaBancaria(TipoContaBancaria.valueOf("CORRENTE"));
        contaBancaria.setDigito(1);
        contaBancaria.setAgencia(1234);
        contaBancaria.setConta(123);
        student.setId(1L);
        student.setCurso("java");
        student.setName("aluno");
        student.setEndereco("rua");
        student.setContaBancaria(contaBancaria);
        return student;
    }

}
