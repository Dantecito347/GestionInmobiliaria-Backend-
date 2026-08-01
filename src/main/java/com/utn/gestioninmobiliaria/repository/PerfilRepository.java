package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
    
}
