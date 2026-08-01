package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    
}
