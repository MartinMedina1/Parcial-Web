package com.example.Controller;

import com.example.Dto.CelularDTO;
import com.example.Service.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celulares")
public class CelularController {

    @Autowired
    private CelularService celularService;

    @PostMapping
    public ResponseEntity<CelularDTO> crearCelular(@RequestBody CelularDTO celularDTO) {
        return ResponseEntity.ok(celularService.crearCelular(celularDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CelularDTO> editarCelular(@PathVariable Long id, @RequestBody CelularDTO celularDTO) {
        CelularDTO actualizado = celularService.editarCelular(id, celularDTO);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CelularDTO>> traerTodos() {
        return ResponseEntity.ok(celularService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CelularDTO> traerPorId(@PathVariable Long id) {
        CelularDTO celularDTO = celularService.traerPorId(id);
        return celularDTO != null ? ResponseEntity.ok(celularDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCelular(@PathVariable Long id) {
        celularService.eliminarCelular(id);
        return ResponseEntity.noContent().build();
    }
}