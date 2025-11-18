package com.example.pescadito.controller;
import com.example.pescadito.model.Combinado;
import com.example.pescadito.service.CombinadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combinados")
public class CombinadoController {
    private final CombinadoService combinadoService;

    public CombinadoController(CombinadoService combinadoService) {
        this.combinadoService = combinadoService;
    }

    // 1. GET /api/combinados - Listar todos
    @GetMapping
    public List<Combinado> obtenerTodos() {
        return combinadoService.listarTodos();
    }

    // 2. GET /api/combinados/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Combinado> obtenerPorId(@PathVariable Long id) {
        return combinadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST /api/combinados - Crear combinado
    @PostMapping
    public ResponseEntity<?> crearCombinado(@RequestBody Combinado combinado) {
        // Validaciones
        if (combinado.getNombre() == null || combinado.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre es obligatorio");
        }
        if (combinado.getPrecio() == null || combinado.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("El precio debe ser mayor a 0");
        }
        if (combinado.getDisponible() == null) {
            return ResponseEntity.badRequest().body("El campo disponible es obligatorio");
        }
        if (combinado.getCategoria() == null || combinado.getCategoria().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo categoría es obligatorio");
        }

        return ResponseEntity.ok(combinadoService.crearCombinado(combinado));
    }

    // 4. PUT /api/combinados/{id} - Actualizar combinado
    @PutMapping("/{id}")
    public ResponseEntity<Combinado> actualizarCombinado(@PathVariable Long id, @RequestBody Combinado combinado) {
        return ResponseEntity.ok(combinadoService.actualizarCombinado(id, combinado));
    }

    // 5. DELETE /api/combinados/{id} - Eliminar combinado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCombinado(@PathVariable Long id) {
        combinadoService.eliminarCombinado(id);
        return ResponseEntity.noContent().build();
    }

    // 6. GET /api/combinados/disponibles - Buscar por disponibilidad
    @GetMapping("/disponibles")
    public List<Combinado> obtenerDisponibles() {
        return combinadoService.buscarPorDisponibilidad(true);
    }
}
