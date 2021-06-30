package com.caqqi.matematicaplay.operacao.usuario.domain.repository;

import com.caqqi.matematicaplay.operacao.usuario.domain.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.apelido = :apelido")
    Optional<Usuario> buscarPorApelido(final String apelido);
}
