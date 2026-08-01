package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.TipoAjuste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAjusteRepository extends JpaRepository<TipoAjuste, Integer>{
    
}
