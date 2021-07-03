package com.caqqi.matematicaplay.desafios.domain.repository;

import com.caqqi.matematicaplay.desafios.domain.entity.DesafioTentativaResposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesafioTentativaRespostaRepository extends CrudRepository<DesafioTentativaResposta, Long> {

    List<DesafioTentativaResposta> findTop10ByUsuarioApelidoOrderByIdDesc(final String apelido);

    @Query("SELECT t FROM DesafioTentativaResposta t WHERE t.usuario.apelido = :apelido ORDER BY t.id DESC")
    List<DesafioTentativaResposta> top10TentativasPorUsuario(final String apelido);
}
