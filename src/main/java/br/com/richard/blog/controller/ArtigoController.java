package br.com.richard.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
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

}
