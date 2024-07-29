package com.example.tracklover.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosArtista( @JsonAlias("name") String nome,
                      @JsonAlias("picture_big") String linkFoto ,
                           @JsonAlias("nb_album") Integer numeroAlbums,
                           @JsonAlias("nb_fan") Integer numeroFas,
                            @JsonAlias("tracklist") String linkListaDeMusicas
                           ) {
}
