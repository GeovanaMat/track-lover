package com.example.tracklover.main;

import com.example.tracklover.model.Artista;
import com.example.tracklover.model.DadosArtista;
import com.example.tracklover.model.Musica;
import com.example.tracklover.repository.ArtistaRepository;
import com.example.tracklover.services.ConsumoApi;
import com.example.tracklover.services.ConsumoApiDeezer;
import com.example.tracklover.services.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner leitura = new Scanner(System.in);
    List<DadosArtista> artistas = new ArrayList<>();
    ArtistaRepository repository;
    private  final ConsumoApi consumirAPI = new ConsumoApi();
    private  final ConverterDados conversorDados = new ConverterDados();

    public Main(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu() {

        var opc = -1;
        while (opc != 0) {
            System.out.println("""
                    \n Olá, bem vindo ao Track Lover
                                    
                    ---- Escolha umas das opções ----
                                    
                    1 - Cadastrar artistas
                    2 - Cadastrar musicas
                    3 - Listar musicas
                    4 - Buscar músicas por artistas
                    
                    0 - Sair
                                    
                    """);
            opc = leitura.nextInt();
            leitura.nextLine();

            switch (opc){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 0:
                    System.out.println("Saindo ...");
                default:
                    System.out.println("Opção inválida! Saindo....");
            }

        }
    }




    private void cadastrarArtista() {
        System.out.println("Qual o artista que você quer cadastrar? ");
        var nomeArtista = leitura.nextLine();
        System.out.println("Qual o tipo do artista(s) (Solo, Dupla, Banda)");
        var tipoArtista = leitura.nextLine();
        DadosArtista dadosArtista = ConsumoApiDeezer.obterDadoArtista(nomeArtista);

        var artista = new Artista(dadosArtista,tipoArtista);
        System.out.println(artista + " cadastrado com sucesso!");
        repository.save(artista);

    }


    private void cadastrarMusicas() {

        System.out.println("Qual  artista você quer busca as músicas?");
        var nomeArtista = leitura.nextLine();

        var artistasCadastrado = repository.findByNomeIgnoreCase(nomeArtista).stream().findFirst();
        System.out.println(artistasCadastrado);

        if(artistasCadastrado.isPresent()) {
            var artista = artistasCadastrado.get();
            var musicas = ConsumoApiDeezer.obterListaMusicas(artista.getLinkListaDeMusicas());
            System.out.println("Lista de Musicas do Artista: ");
            musicas.data().stream().forEach(e -> System.out.println(e.title()));

            System.out.println("Digite o nome da música de deseja cadastrar: ");
            var nomeMusica = leitura.nextLine();

            var musica =  musicas.data().stream().
                    filter(e -> e.title().equalsIgnoreCase(nomeMusica))
                    .findFirst()
                    .map(Musica::new);

            if(musica.isPresent()) {
                var musicaEncontrada = musica.get();
                artista.adicionarMusica(musicaEncontrada);

                repository.save(artista);
                System.out.println("Musica Cadastrada com sucesso!");
            } else {
                System.out.println("Musica não encontrada");
            }

        }


    }

    private void listarMusicas() {
        repository.findAll().
                forEach( a -> a
                        .getMusicas()
                        .forEach( m -> System.out.println("Nome da Musica: " + m.getTitle() + " Artista: " + a.getNome())));

    }




}
