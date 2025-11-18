package com.example.pescadito.service;

import com.example.pescadito.model.Combinado;
import com.example.pescadito.repository.CombinadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CombinadoService {
    private final CombinadoRepository combinadoRepository;

    public CombinadoService(CombinadoRepository combinadoRepository) {
        this.combinadoRepository = combinadoRepository;
    }

    // 1. Listar todos los combinados
    public List<Combinado> listarTodos() {
        return combinadoRepository.findAll();
    }

    // 2. Buscar por ID
    public Optional<Combinado> buscarPorId(Long id) {
        return combinadoRepository.findById(id);
    }

    // 3. Crear combinado
    public Combinado crearCombinado(Combinado combinado) {
        return combinadoRepository.save(combinado);
    }

    // 4. Actualizar combinado
    public Combinado actualizarCombinado(Long id, Combinado combinado) {
        combinado.setId(id);
        return combinadoRepository.save(combinado);
    }

    // 5. Eliminar combinado
    public void eliminarCombinado(Long id) {
        combinadoRepository.deleteById(id);
    }

    // 6. Buscar por disponibilidad
    public List<Combinado> buscarPorDisponibilidad(Boolean disponible) {
        return combinadoRepository.findByDisponible(disponible);
    }
}
