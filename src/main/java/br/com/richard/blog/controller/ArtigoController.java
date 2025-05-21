package br.com.richard.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Map<String, Object>>> listarArtigos() {
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

    // Novo método para buscar por código
    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> buscarArtigoPorCodigo(@PathVariable String codigo) {
        ArtigoResponseDto artigo = artigoService.buscarArtigoPorCodigo(codigo);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("codigo", artigo.codigo());
        resultado.put("titulo", artigo.titulo());
        resultado.put("conteudo", artigo.conteudo());
        resultado.put("autor", artigo.autor());
        resultado.put("dataPublicacao", artigo.dataPublicacao());
        resultado.put("categoria", artigo.categoria());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Map<String, Object>>> listarArtigosPorCategoria(@PathVariable Categoria categoria) {
        List<ArtigoResponseDto> artigos = artigoService.listarArtigosPorCategoria(categoria);

        List<Map<String, Object>> resultado = artigos.stream()
                .map(a -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("codigo", a.codigo());
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

    @GetMapping("/test")
    public String test() {
        return "API version 4.0";
    }

    // Mantém o endpoint /completos para compatibilidade
    @GetMapping("/completos")
    public ResponseEntity<List<Map<String, Object>>> listarArtigosCompletos() {
        return listarArtigos();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizarArtigo(@PathVariable String codigo, @RequestBody ArtigoRequestDto artigoDto) {
        artigoService.atualizarArtigoPorCodigo(codigo, artigoDto); // Corrigido para usar o método correto
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarArtigo(@PathVariable String codigo) {
        artigoService.deletarArtigoPorCodigo(codigo); // Corrigido para usar o método correto
        return ResponseEntity.noContent().build();
    }

}
