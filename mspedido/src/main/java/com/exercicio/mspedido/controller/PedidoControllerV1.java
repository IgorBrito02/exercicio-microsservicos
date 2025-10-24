package com.exercicio.mspedido.controller;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.service.PedidoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoControllerV1 {

    private final PedidoService service;
    
    @GetMapping
    public ResponseEntity<Page<PedidoDto>> findAll(
        @PageableDefault(size = 5)
        Pageable pagination
    ){
        return ResponseEntity.ok(service.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> findById(
        @PathVariable("id")
        Long id
    ){
        try{
            return ResponseEntity.ok(service.findById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PedidoDto> save(
        @Valid
        @RequestBody
        PedidoDto dto
    ){
        var dtoSaved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(
        @PathVariable("id")
        Long id,
        
        @Valid
        @RequestBody
        PedidoDto dto
    ){
        var dtoUpdated = service.update(id, dto);
        return ResponseEntity.ok(dtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @PathVariable
        Long id
    ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
