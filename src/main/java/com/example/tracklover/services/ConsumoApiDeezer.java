package com.example.tracklover.services;

import com.example.tracklover.model.DadosArtista;
import com.example.tracklover.model.DadosListaMusicas;
import com.example.tracklover.model.DadosMusica;

import java.util.List;

public class ConsumoApiDeezer {

    public static DadosArtista obterDadoArtista(String nomeArtista) {
        ConsumoApi consumoApi = new ConsumoApi();
        ConverterDados converterDados = new ConverterDados();

        String URL_BASE = "https://api.deezer.com/artist/";
        String urlArtista = URL_BASE + nomeArtista.replace(" ","-");

        String json = consumoApi.obterDados(urlArtista);

        return converterDados.obterDados(json,DadosArtista.class);

    }



    public static DadosListaMusicas obterListaMusicas(String artistaLinkTracklist) {
        ConsumoApi consumirApi = new ConsumoApi();
        ConverterDados converterDados = new ConverterDados();
        var json = consumirApi.obterDados(artistaLinkTracklist);


        return converterDados.obterDados(json, DadosListaMusicas.class);
    }


}
