package br.com.richard.blog.dto;

import br.com.richard.blog.model.Categoria;

public record ArtigoRequestDto(

    String titulo,
    String conteudo,
    String autor,
    Categoria categoria
    
) {}
