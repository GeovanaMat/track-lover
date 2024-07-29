package com.example.tracklover.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosListaMusicas( List<DadosMusica> data) {
}
