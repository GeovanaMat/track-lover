package com.example.tracklover.model;

public enum TipoArtista {

    SOLO,
    DULPA,
    BANDA;


    public static TipoArtista fromString(String test) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.name().equalsIgnoreCase(test)) {
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Tipo de artista inválido: " + test);
    }
}

