package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer>{
    @Query("SELECT n FROM Notificacion n WHERE n.idUsuario = :idUsuario OR n.idUsuario IS NULL ORDER BY n.fechaCreacion DESC LIMIT 15")
    List<Notificacion> findNotificacionesPorUsuario(@Param("idUsuario") Integer idUsuario);

    @Transactional
    @Modifying
    @Query("UPDATE Notificacion n SET n.leida = true WHERE (n.idUsuario = :idUsuario OR n.idUsuario IS NULL) AND n.leida = false")
    void marcarTodasComoLeidas(@Param("idUsuario") Integer idUsuario);
}
