package com.utn.gestioninmobiliaria.repository;

import com.utn.gestioninmobiliaria.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByTipoDocumento_IdTipoDocAndNroDocumento(Integer idTipoDoc, String nroDocumento);
    boolean existsByTipoDocumento_IdTipoDocAndNroDocumentoAndIdPersonaNot(Integer idTipoDoc, String nroDocumento, Long idPersona);
}
