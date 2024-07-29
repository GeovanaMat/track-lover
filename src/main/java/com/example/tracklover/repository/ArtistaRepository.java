package com.example.tracklover.repository;

import com.example.tracklover.model.Artista;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends Repository<Artista, Integer> {
    Artista save(Artista person);
    List<Artista> findAll();

    List<Artista> findByNomeIgnoreCase(String nome);
}
