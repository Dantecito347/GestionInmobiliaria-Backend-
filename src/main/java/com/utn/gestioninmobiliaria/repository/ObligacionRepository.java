package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Obligacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObligacionRepository extends JpaRepository<Obligacion, Integer>{
    List<Obligacion> findByContratoIdContrato(Integer idContrato);
}
