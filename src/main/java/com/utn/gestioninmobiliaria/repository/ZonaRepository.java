package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer> {
    
}
