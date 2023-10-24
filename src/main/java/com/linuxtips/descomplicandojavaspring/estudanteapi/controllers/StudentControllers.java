package com.linuxtips.descomplicandojavaspring.estudanteapi.controllers;

import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataCreateStudent;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DataStudentDetails;
import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.EstudanteNaoEncontradoException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.services.StudentService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentControllers {

    Counter novosEstudantesCounter;

    Counter estudantesDesmatriculados;

    public StudentControllers(MeterRegistry registry){
        novosEstudantesCounter = Counter.builder("novos_estudantes_counter")
                .description("Estudantes que foram matriculados")
                .register(registry);
        estudantesDesmatriculados = Counter.builder("estudantes_desmatriculados_counter")
                .description("Estudantes que foram desmatriculados")
                .register(registry);
    }


    @Autowired
    private StudentService studentService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataCreateStudent> createStudent(@RequestBody DataCreateStudent data) throws Exception{
        novosEstudantesCounter.increment();
        studentService.createStudent(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DataStudentDetails>>listAllStudents(){
        List<DataStudentDetails> students = studentService.listAllStudents();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataStudentDetails> findStudentById(@PathVariable(name = "id") Long id) throws Exception{
        DataStudentDetails student = studentService.findStudentById(id);
        return ResponseEntity.ok().body(student);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateAnStudent(@PathVariable Long id, @RequestBody DataStudentDetails data) throws EstudanteNaoEncontradoException {
        studentService.updateAnStudent(id,data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAnStudent(@PathVariable Long id) throws Exception {
        estudantesDesmatriculados.increment();
        studentService.deleteAnStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome")
    @Transactional
    public ResponseEntity<DataStudentDetails> getByName(@RequestParam String name) throws Exception {
        DataStudentDetails studentDetails=  studentService.getByName(name);
        return ResponseEntity.ok().body(studentDetails);
    }

    @GetMapping("/nome/inicial")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DataStudentDetails>> getByInicialName(@RequestParam String nomeInicial)throws Exception{
        List<DataStudentDetails> studentDetails = studentService.getByInicialName(nomeInicial);
        return ResponseEntity.ok().body(studentDetails);
    }

    @GetMapping("/curso")
    public ResponseEntity<List<DataStudentDetails>> getByCurso(@RequestParam String curso) throws Exception {
        List<DataStudentDetails> students = studentService.getStudentByCurso(curso);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/nome-curso")
    public ResponseEntity<List<DataStudentDetails>> getByInicialNameAndCurso(@RequestParam String nomeInicial, @RequestParam String curso) throws Exception{
        List<DataStudentDetails> studentDetails= studentService.getByInicialNameAndCurso(nomeInicial,curso);
        return ResponseEntity.ok().body(studentDetails);
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<DataStudentDetails>> getByEndereco(@RequestParam String endereco)throws Exception{
        List<DataStudentDetails> students = studentService.getByEndereco(endereco);
        return ResponseEntity.ok().body(students);

    }





}
