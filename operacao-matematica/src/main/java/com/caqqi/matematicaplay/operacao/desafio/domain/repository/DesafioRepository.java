package com.caqqi.matematicaplay.operacao.desafio.domain.repository;

import com.caqqi.matematicaplay.operacao.desafio.domain.Desafio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesafioRepository extends CrudRepository<Desafio, Long> { }
