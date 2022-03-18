package com.example.service;

import com.example.demo.Player;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

import static java.lang.Integer.compare;

public class PlayerService {

    public Flux<Player> obtenerJugadoresMayoresDe34(Flux<Player> lista){
        return lista.filter(player -> player.age > 34 );
    }

    public Flux<Player> obtenerJugadoresMayoresDe(Flux<Player> lista, Integer age){
        return lista.filter(player -> player.age > age );
    }

    public Flux<Player> obtenerJugadoresPorClub(Flux<Player> lista, String club){
        return lista.filter(player -> player.club.equals(club));
    }

    public Flux<Player> ordenarJugadoresPorEdadDeMenorAMayor(Flux<Player> lista){
        return lista.sort((a,b) -> compare(a.getAge(),b.getAge()));
    }

    public Flux<String> listaDeNacionalidades (Flux<Player> lista){
        return lista.map(player -> player.getNational()
                .toUpperCase())
                .distinct();

    }

    public Flux<Player> ranking(List<Player> lista, String nationality){
        return Flux.fromIterable(lista)
                .filter(p -> Objects.equals(p.getNational(), nationality))
                .sort((a, b) -> compare(b.getWinners(),a.getWinners()))
                .take(10);
    }
}
