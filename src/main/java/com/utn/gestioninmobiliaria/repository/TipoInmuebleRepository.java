package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.TipoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInmuebleRepository extends JpaRepository<TipoInmueble, Integer> {
    
}
