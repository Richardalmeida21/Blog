package br.com.richard.blog.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.richard.blog.dto.ArtigoRequestDto;
import br.com.richard.blog.dto.ArtigoResponseDto;
import br.com.richard.blog.model.Artigo;
import br.com.richard.blog.model.Categoria;
import br.com.richard.blog.repository.ArtigoRepository;

@Service
public class ArtigoService {

        @Autowired
        private ArtigoRepository artigoRepository;

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        public void criarArtigo(ArtigoRequestDto artigoDto) {
                Artigo artigo = new Artigo(
                                artigoDto.titulo(),
                                artigoDto.conteudo(),
                                artigoDto.autor(),
                                artigoDto.categoria());
                artigoRepository.save(artigo);
        }

        public List<ArtigoResponseDto> listarArtigos() {
                List<Artigo> artigos = artigoRepository.findAll();
                return artigos.stream()
                                .map(this::converterParaDto)
                                .toList();
        }

        public ArtigoResponseDto buscarArtigoPorId(Long id) {
                Artigo artigo = artigoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

                return converterParaDto(artigo);
        }
        
        // Novo método para buscar por código
        public ArtigoResponseDto buscarArtigoPorCodigo(String codigo) {
                Artigo artigo = artigoRepository.findByCodigo(codigo)
                                .orElseThrow(() -> new RuntimeException("Artigo com código " + codigo + " não encontrado"));

                return converterParaDto(artigo);
        }

        public List<ArtigoResponseDto> listarArtigosPorCategoria(Categoria categoria) {
                List<Artigo> artigos = artigoRepository.findByCategoria(categoria);
                return artigos.stream()
                                .map(this::converterParaDto)
                                .toList();
        }

        public void atualizarArtigo(Long id, ArtigoRequestDto artigoDto) {
                Artigo artigo = artigoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

                artigo.setTitulo(artigoDto.titulo());
                artigo.setConteudo(artigoDto.conteudo());
                artigo.setAutor(artigoDto.autor());
                artigo.setCategoria(artigoDto.categoria());
                artigoRepository.save(artigo);
        }
        
        // Novo método para atualizar por código
        public void atualizarArtigoPorCodigo(String codigo, ArtigoRequestDto artigoDto) {
                Artigo artigo = artigoRepository.findByCodigo(codigo)
                                .orElseThrow(() -> new RuntimeException("Artigo com código " + codigo + " não encontrado"));

                artigo.setTitulo(artigoDto.titulo());
                artigo.setConteudo(artigoDto.conteudo());
                artigo.setAutor(artigoDto.autor());
                artigo.setCategoria(artigoDto.categoria());
                artigoRepository.save(artigo);
        }

        public void deletarArtigo(Long id) {
                Artigo artigo = artigoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
                artigoRepository.delete(artigo);
        }
        
        // Novo método para deletar por código
        public void deletarArtigoPorCodigo(String codigo) {
                Artigo artigo = artigoRepository.findByCodigo(codigo)
                                .orElseThrow(() -> new RuntimeException("Artigo com código " + codigo + " não encontrado"));
                artigoRepository.delete(artigo);
        }

        private ArtigoResponseDto converterParaDto(Artigo artigo) {
                return new ArtigoResponseDto(
                        artigo.getId(),
                        artigo.getCodigo(),
                        artigo.getTitulo(),
                        artigo.getConteudo(),
                        artigo.getAutor(),
                        formatarData(artigo.getDataPublicacao()),
                        artigo.getCategoria());
        }

        private String formatarData(java.time.LocalDateTime data) {
                return data.format(FORMATTER);
        }
}