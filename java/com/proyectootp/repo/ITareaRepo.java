package com.proyectootp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proyectootp.model.Tarea;

public interface ITareaRepo extends JpaRepository<Tarea, Integer>{
	@Transactional
	@Modifying
    @Query(value = "DELETE FROM detalle_tarea WHERE id_tarea = :id", nativeQuery = true)
    void borraDetalleTarea(@Param("id") Integer id);

    /* */
    @Query("SELECT t FROM Tarea t JOIN t.detalles dt JOIN dt.usuario u WHERE u.id = :idUsuario")
    List<Tarea> findTareasByUsuarioId(@Param("idUsuario") Integer idUsuario);

    
}
