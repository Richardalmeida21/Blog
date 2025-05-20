package br.com.richard.blog.dto;

import br.com.richard.blog.model.Categoria;

public record ArtigoResponseDto(

    String titulo,
    String conteudo,
    String autor,
    String dataPublicacao,
    Categoria categoria

) {}
