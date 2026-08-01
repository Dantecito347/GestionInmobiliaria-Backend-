package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    
}
