package com.example.tracklover.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(unique = true)
    private String nome;
    private String linkFoto ;
    private Integer numeroAlbums;
    private Integer numeroFas;
    private  String linkListaDeMusicas;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

    @OneToMany(mappedBy = "artista",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Musica> musicas  = new ArrayList<>();

    public Artista() {}

    public Artista(DadosArtista dadosArtista, String tipoArtista) {
        this.nome = dadosArtista.nome();
        this.linkFoto = dadosArtista.linkFoto();
        this.numeroAlbums =dadosArtista.numeroAlbums();
        this.numeroFas = dadosArtista.numeroFas();
        this.tipoArtista = TipoArtista.fromString(tipoArtista);
        this.linkListaDeMusicas = dadosArtista.linkListaDeMusicas();

    }

    @Override
    public String toString() {
        return
                "Nome do Artista = " + nome + " " +
                "Tipo de Artista = " + tipoArtista + " ";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public Integer getNumeroAlbums() {
        return numeroAlbums;
    }

    public void setNumeroAlbums(Integer numeroAlbums) {
        this.numeroAlbums = numeroAlbums;
    }

    public Integer getNumeroFas() {
        return numeroFas;
    }

    public void setNumeroFas(Integer numeroFas) {
        this.numeroFas = numeroFas;
    }

    public String getLinkListaDeMusicas() {
        return linkListaDeMusicas;
    }

    public void setLinkListaDeMusicas(String linkListaDeMusicas) {
        this.linkListaDeMusicas = linkListaDeMusicas;
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
        musica.setArtista(this);
    }
}
