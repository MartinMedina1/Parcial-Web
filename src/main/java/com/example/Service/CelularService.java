package com.example.Service;

import com.example.Dto.CelularDTO;
import com.example.Entity.Celular;
import com.example.Repository.CelularRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CelularService {

    @Autowired
    private CelularRepository celularRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CelularDTO crearCelular(CelularDTO celularDTO) {
        Celular celular = modelMapper.map(celularDTO, Celular.class);
        celular = celularRepository.save(celular);
        return modelMapper.map(celular, CelularDTO.class);
    }

    public CelularDTO editarCelular(Long id, CelularDTO celularDTO) {
        Optional<Celular> celularOptional = celularRepository.findById(id);
        if (celularOptional.isPresent()) {
            Celular celular = celularOptional.get();
            celular.setMarca(celularDTO.getMarca());
            celular.setSerial(celularDTO.getSerial());
            celular.setFechaCompra(celularDTO.getFechaCompra());
            celular.setAnoLanzamiento(celularDTO.getAnoLanzamiento());
            celular.setPrecio(celularDTO.getPrecio());
            celular.setSistemaOperativo(celularDTO.getSistemaOperativo());
            celular.setGama(celularDTO.getGama());
            celular = celularRepository.save(celular);
            return modelMapper.map(celular, CelularDTO.class);
        }
        return null;
    }

    public List<CelularDTO> traerTodos() {
        return celularRepository.findByEliminadoFalse().stream()
                .map(celular -> modelMapper.map(celular, CelularDTO.class))
                .collect(Collectors.toList());
    }

    public CelularDTO traerPorId(Long id) {
        Optional<Celular> celularOptional = celularRepository.findById(id);
        if (celularOptional.isPresent() && !celularOptional.get().isEliminado()) {
            return modelMapper.map(celularOptional.get(), CelularDTO.class);
        }
        return null;
    }

    public void eliminarCelular(Long id) {
        Optional<Celular> celularOptional = celularRepository.findById(id);
        if (celularOptional.isPresent()) {
            Celular celular = celularOptional.get();
            celular.setEliminado(true);
            celularRepository.save(celular);
        }
    }
}