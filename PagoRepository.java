package com.utn.gestioninmobiliaria.repository;
import com.utn.gestioninmobiliaria.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    @Query("SELECT p.mesCobertura, SUM(p.montoPagado) FROM Pago p " +
           "WHERE p.anioCobertura = :anio AND p.estadoPago = 'Pagado' " +
           "GROUP BY p.mesCobertura ORDER BY p.mesCobertura")
    List<Object[]> obtenerIngresosPorMes(@Param("anio") Integer anio);
}
