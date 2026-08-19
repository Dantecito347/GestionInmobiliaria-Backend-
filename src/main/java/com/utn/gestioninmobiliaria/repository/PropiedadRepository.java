package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Integer> {
    boolean existsByDireccionIgnoreCase(String direccion);

    boolean existsByDireccionIgnoreCaseAndIdPropiedadNot(String direccion, Integer idPropiedad);

    @Query("SELECT p FROM Propiedad p " +
           "LEFT JOIN p.propietario prop " +
           "LEFT JOIN p.tipoInmueble tipo " +
           "LEFT JOIN p.zonas z " +
           "WHERE LOWER(p.direccion) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(z.zona) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(z.nombreBarrio) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(CONCAT(z.zona, ' - ', z.nombreBarrio)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(tipo.descripcion) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(CONCAT(prop.nombre, ' ', prop.apellido)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
           "OR LOWER(CONCAT(prop.apellido, ' ', prop.nombre)) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Propiedad> buscarSugerencias(@Param("termino") String termino);

}
