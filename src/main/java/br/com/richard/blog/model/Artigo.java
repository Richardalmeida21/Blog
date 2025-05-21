package br.com.richard.blog.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "artigos")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artigo_sequence")
    @SequenceGenerator(name = "artigo_sequence", sequenceName = "artigo_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private LocalDateTime dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @PrePersist
    public void SalvarDataPublicacao() {
        this.dataPublicacao = LocalDateTime.now();
    }

    public Artigo() {
    }

    public Artigo(String titulo, String conteudo, String autor, Categoria categoria) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Artigo other = (Artigo) obj;
        return id != null && id.equals(other.id);
    }
}