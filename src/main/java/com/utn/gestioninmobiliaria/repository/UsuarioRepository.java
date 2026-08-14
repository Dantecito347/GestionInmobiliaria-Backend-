package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByResetToken(String resetToken);
}
