package br.com.richard.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.richard.blog.model.Artigo;
import br.com.richard.blog.model.Categoria;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByCategoria(Categoria categoria);

}
