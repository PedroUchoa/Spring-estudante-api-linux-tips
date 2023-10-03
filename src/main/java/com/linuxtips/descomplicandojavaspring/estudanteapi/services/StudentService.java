package com.linuxtips.descomplicandojavaspring.estudanteapi.services;

import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataCreateStudent;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataStudentDetails;
import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.*;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.Student;
import com.linuxtips.descomplicandojavaspring.estudanteapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public DataCreateStudent createStudent(DataCreateStudent data) throws EstudanteDuplicadoException {
     if(!studentRepository.findByName(data.name()).isEmpty()){
         throw new EstudanteDuplicadoException("Esse estudante já existe no banco de dados");
     }
    studentRepository.save(new Student(data));
     return data;
    }

    public List<DataStudentDetails> listAllStudents(){
        return studentRepository.findAll().stream().map(DataStudentDetails::new).collect(Collectors.toList());
    }

    public DataStudentDetails findStudentById(Long id) throws EstudanteNaoEncontradoException {
        Student student = studentRepository.findById(id).orElseThrow(()->new EstudanteNaoEncontradoException(id));

        return new DataStudentDetails(student);
    }

    public void updateAnStudent(Long id, DataStudentDetails data) throws EstudanteNaoEncontradoException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EstudanteNaoEncontradoException(id));
        student.updateUser(data);
        studentRepository.save(student);
    }

    public void deleteAnStudent(Long id) throws EstudanteNaoEncontradoException {
        studentRepository.findById(id).orElseThrow(()->new EstudanteNaoEncontradoException(id));
        studentRepository.deleteById(id);
    }

    public DataStudentDetails getByName(String name) throws EstudantNaoEncontradoPeloNomeException {
     Student student = studentRepository.findByName(name).orElseThrow(()-> new EstudantNaoEncontradoPeloNomeException(name));
     return new DataStudentDetails(student);
    }

    public List<DataStudentDetails> getStudentByCurso(String curso) throws EstudanteNaoEncontradoPeloCursoException {
        List<DataStudentDetails> studentDetails = studentRepository.findByCurso(curso).get();
        if(studentDetails.isEmpty()){
            throw new EstudanteNaoEncontradoPeloCursoException(curso);
        }
        return studentDetails;
     }

    public List<DataStudentDetails> getByInicialName(String nomeInicial) throws EstudantNaoEncontradoPeloNomeException {
        List<DataStudentDetails> studentDetails= studentRepository.findByNameStartingWith(nomeInicial).get();
        if(studentDetails.isEmpty()){
            throw new EstudantNaoEncontradoPeloNomeException(nomeInicial);
        }
        return studentDetails;
    }

    public List<DataStudentDetails> getByInicialNameAndCurso(String nomeInicial,String curso) throws EstudantNaoEncontradoPeloNomeECursoException {
        List<DataStudentDetails> studentDetails=  studentRepository.findByNameStartingWithAndCurso(nomeInicial,curso).get();
        if(studentDetails.isEmpty()){
            throw new EstudantNaoEncontradoPeloNomeECursoException(nomeInicial,curso);
        }
        return studentDetails;
    }

    public List<DataStudentDetails> getByEndereco(String endereco) throws EstudanteNaoEncontradoPeloEnderecoException {
        List<DataStudentDetails> studentDetails= studentRepository.findByEnderecoStartingWithOrderByEnderecoDesc(endereco).get();
        if(studentDetails.isEmpty()){
            throw new EstudanteNaoEncontradoPeloEnderecoException(endereco);
        }
        return studentDetails;
    }

}
