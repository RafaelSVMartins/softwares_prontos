package com.fatec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
