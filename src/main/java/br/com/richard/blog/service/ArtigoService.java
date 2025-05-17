package br.com.richard.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.richard.blog.dto.ArtigoRequestDto;
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
            artigoDto.autor()
        );

        artigoRepository.save(artigo);
}

    public List<Artigo> listarArtigos(){
        return artigoRepository.findAll();
    }

    public Artigo buscarArtigoPorId (Long id) {
        return artigoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
    }


}
