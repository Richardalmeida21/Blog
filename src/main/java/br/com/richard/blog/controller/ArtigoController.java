package br.com.richard.blog.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.richard.blog.dto.ArtigoRequestDto;
import br.com.richard.blog.dto.ArtigoResponseDto;
import br.com.richard.blog.model.Categoria;
import br.com.richard.blog.service.ArtigoService;

@RestController
@RequestMapping("/artigos")
@CrossOrigin(origins = "*")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @PostMapping
    public ResponseEntity<Void> criarArtigo(@RequestBody ArtigoRequestDto artigoDto) {
        artigoService.criarArtigo(artigoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/completos")
    public ResponseEntity<List<Map<String, Object>>> listarArtigosCompletos() {
        List<ArtigoResponseDto> artigos = artigoService.listarArtigos();

        List<Map<String, Object>> resultado = artigos.stream()
                .map(a -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("codigo", a.codigo()); // Usar o código como identificador
                    map.put("titulo", a.titulo());
                    map.put("conteudo", a.conteudo());
                    map.put("autor", a.autor());
                    map.put("dataPublicacao", a.dataPublicacao());
                    map.put("categoria", a.categoria());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ArtigoResponseDto>> listarArtigos() {
        List<ArtigoResponseDto> artigos = artigoService.listarArtigos();
        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtigoResponseDto> buscarArtigoPorId(@PathVariable Long id) {
        ArtigoResponseDto artigo = artigoService.buscarArtigoPorId(id);
        return ResponseEntity.ok(artigo);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ArtigoResponseDto>> listarArtigosPorCategoria(@PathVariable Categoria categoria) {
        List<ArtigoResponseDto> artigos = artigoService.listarArtigosPorCategoria(categoria);
        return ResponseEntity.ok(artigos);
    }

    @GetMapping("/test")
    public String test() {
        return "API version 3.0";
    }
}