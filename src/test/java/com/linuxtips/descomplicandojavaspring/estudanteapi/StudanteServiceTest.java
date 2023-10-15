package com.linuxtips.descomplicandojavaspring.estudanteapi;

import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataCreateStudent;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataStudentDetails;
import com.linuxtips.descomplicandojavaspring.estudanteapi.enums.TipoContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.EstudantNaoEncontradoPeloNomeException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.EstudanteDuplicadoException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.EstudanteNaoEncontradoException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.ContaBancaria;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;
import com.linuxtips.descomplicandojavaspring.estudanteapi.repositories.StudentRepository;
import com.linuxtips.descomplicandojavaspring.estudanteapi.services.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudanteServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Sucesso - Deve salvar estudante na base com sucesso")
    void deveSalvarEstudanteNaBaseComSucesso() throws EstudanteNaoEncontradoException {
        Student student = this.buildMockStudent();
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        student.setName("teste");
        studentService.updateAnStudent(student.getId(),new DataStudentDetails(student));
        assertEquals("teste", student.getName());
        verify(studentRepository).save(student);
        verify(studentRepository).findById(student.getId());
    }

    @Test
    @DisplayName("Sucesso - Deve salvar estudante na base com sucesso sem lançar exceção")
    void deveSalvarUmNovoEstudanteComSucessoSemLancarExcecao() throws EstudanteDuplicadoException {
        Student student = this.buildMockStudent();
        this.studentService.createStudent(new DataCreateStudent(student));
        assertDoesNotThrow(()->{
            studentService.createStudent(new DataCreateStudent(student));
        });
    }

    @Test
    @DisplayName("Erro - Deve lançar exception ao adcionar estudante duplicado")
    void deveAdicionarUmEstudanteDuplicadoERetonarException() throws EstudanteDuplicadoException {
        Student student = this.buildMockStudent();

        when(studentRepository.findByName(student.getName())).thenReturn(Optional.of(student));
        Exception exception = assertThrows(EstudanteDuplicadoException.class,
               () -> studentService.createStudent(new DataCreateStudent(student)));
        assertEquals("Esse estudante já existe no banco de dados",exception.getMessage());
    }

    @Test
    @DisplayName("Sucesso - Deve retornar a lista de estudantes com sucesso")
    void deveRetornarListaDeEstudantesComSucesso(){
        Student student = this.buildMockStudent();
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<DataStudentDetails> students = this.studentService.listAllStudents();
        assertEquals(1,students.size());
        verify(this.studentRepository).findAll();
    }

    @Test
    @DisplayName("Sucesso - Deve Buscar um estudante pelo id com sucesso")
    void deveBuscarUmEstudantePeloIdComSucesso() throws EstudanteNaoEncontradoException {
        Student student = this.buildMockStudent();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        var estudanteBase =this.studentService.findStudentById(1L);
        assertEquals(student.getId(), estudanteBase.id());
    }

    @Test
    @DisplayName("Erro - Deve lançar uma Exception ao buscar um estudante pelo id")
    void deveLancarUmaExceptionAoBuscarEstudantePorId(){
        Student student =this.buildMockStudent();
        when(studentRepository.findById(200L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EstudanteNaoEncontradoException.class,
                ()->studentService.findStudentById(200L));
        assertEquals("Estudante com o id: "+200L+" Não encontado na base", exception.getMessage());
        verify(this.studentRepository).findById(200L);
    }
    @Test
    @DisplayName("Erro - Deve lançar uma Exception ao atualizar")
    void deveLancarExceptionAoAtuzalizar() throws EstudanteNaoEncontradoException {
        Student student = this.buildMockStudent();
        when(studentRepository.findById(200L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EstudanteNaoEncontradoException.class,
                ()-> studentService.updateAnStudent(200L, new DataStudentDetails(student)));
        assertEquals("Estudante com o id: "+200L+" Não encontado na base", exception.getMessage());
    }

    @Test
    @DisplayName("Sucesso - Deve buscar um estudante pelo nome com sucesso")
    void deveBuscarUmEstudantePeloNomeComSucesso() throws EstudantNaoEncontradoPeloNomeException {
        Student student = this.buildMockStudent();
        when(studentRepository.findByName("al")).thenReturn(Optional.of(student));
        var studentBase = this.studentService.getByName("al");
        assertEquals("aluno", studentBase.name());
    }

    @Test
    @DisplayName("Erro - Deve lançar uma exception ao buscar um estudante pelo nome")
    void deveLancarExceptionAoBuscarPeloNome(){
        Student student = this.buildMockStudent();
        when(studentRepository.findByName("jajaj")).thenReturn(Optional.empty());
        Exception exception = assertThrows(EstudantNaoEncontradoPeloNomeException.class,
                ()->studentService.getByName("jajaj"));
        assertEquals("Estudandte com o nome: jajaj Não encontado na base",exception.getMessage());
        verify(this.studentRepository).findByName("jajaj");
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
