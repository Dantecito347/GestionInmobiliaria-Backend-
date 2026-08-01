package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Integer> {
    
}
