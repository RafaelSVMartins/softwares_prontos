package com.fatec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
