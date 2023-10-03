create table student_conta(
    student_id bigint not null,
    conta_bancaria_id bigint not null,
    CONSTRAINT fk_student_id FOREIGN KEY(student_id) REFERENCES students(id),
    CONSTRAINT fk_conta_bancaria_id FOREIGN KEY(conta_bancaria_id) REFERENCES contas_bancarias(id)
);
