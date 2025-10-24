package com.exercicio.msproduto.controller;

import com.exercicio.msproduto.dto.ProdutoDto;
import com.exercicio.msproduto.service.ProdutoService;

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
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> findAll(
        @PageableDefault(size = 5)
        Pageable pagination
    ){
        return ResponseEntity.ok(service.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(
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
    public ResponseEntity<ProdutoDto> save(
        @Valid
        @RequestBody
        ProdutoDto dto
    ){
        var dtoSaved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(
        @PathVariable("id")
        Long id,
        
        @Valid
        @RequestBody
        ProdutoDto dto
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