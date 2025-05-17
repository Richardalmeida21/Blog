package br.com.richard.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.richard.blog.model.Artigo;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
    

}
