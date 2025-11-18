package com.example.pescadito.repository;

import com.example.pescadito.model.Combinado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinadoRepository extends JpaRepository<Combinado, Long> {
    List<Combinado> findByDisponible(Boolean disponible);
}
