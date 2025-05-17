package br.com.richard.blog.dto;

public record ArtigoResponseDto(

    String titulo,
    String conteudo,
    String autor,
    String dataPublicacao

) {}
