package br.com.richard.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.richard.blog.dto.ArtigoRequestDto;
import br.com.richard.blog.dto.ArtigoResponseDto;
import br.com.richard.blog.model.Artigo;
import br.com.richard.blog.repository.ArtigoRepository;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    public void criarArtigo(ArtigoRequestDto artigoDto) {

        Artigo artigo = new Artigo(
                artigoDto.titulo(),
                artigoDto.conteudo(),
                artigoDto.autor());

        artigoRepository.save(artigo);
    }

    public List<ArtigoResponseDto> listarArtigos() {
        List<Artigo> artigos = artigoRepository.findAll();
        return artigos.stream()
                .map(artigo -> new ArtigoResponseDto(
                        artigo.getTitulo(),
                        artigo.getConteudo(),
                        artigo.getAutor(),
                        artigo.getDataPublicacao().toString()))
                .toList();
    }

    public ArtigoResponseDto buscarArtigoPorId(Long id) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

        return new ArtigoResponseDto(
                artigo.getTitulo(),
                artigo.getConteudo(),
                artigo.getAutor(),
                artigo.getDataPublicacao().toString());
    }

    public void atualizarArtigo(Long id, ArtigoRequestDto artigoDto) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

        artigo.setTitulo(artigoDto.titulo());
        artigo.setConteudo(artigoDto.conteudo());
        artigo.setAutor(artigoDto.autor());
        artigoRepository.save(artigo);
    }

    public void deletarArtigo(Long id) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
        artigoRepository.delete(artigo);
    }

}
