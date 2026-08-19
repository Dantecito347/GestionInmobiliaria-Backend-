package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByTipoDocumento_IdTipoDocAndNroDocumento(Integer idTipoDoc, String nroDocumento);
    boolean existsByTipoDocumento_IdTipoDocAndNroDocumentoAndIdPersonaNot(Integer idTipoDoc, String nroDocumento, Long idPersona);

    @Query("SELECT p FROM Persona p WHERE p.activo = true AND (" +
           "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(p.apellido) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(CONCAT(p.nombre, ' ', p.apellido)) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(CONCAT(p.apellido, ' ', p.nombre)) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "p.nroDocumento LIKE CONCAT('%', :termino, '%'))")
    List<Persona> buscarSugerencias(@Param("termino") String termino, Pageable pageable);
}
