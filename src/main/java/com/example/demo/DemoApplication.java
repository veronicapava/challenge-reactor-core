package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import com.example.service.PlayerService;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	static List<Player> list = CsvUtilFile.getPlayers();
	static Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		PlayerService ps = new PlayerService();
		var jugadoresMayoresDe34 = ps.obtenerJugadoresMayoresDe34(listFlux);
		var jugadoresMayoresDe = ps.obtenerJugadoresMayoresDe(listFlux, 39);
		var jugadoresPorClub =  ps.obtenerJugadoresPorClub(listFlux, "Livorno");
		var ordenarJugadoresPorEdadDeMenorAMayor = ps.ordenarJugadoresPorEdadDeMenorAMayor(jugadoresMayoresDe);
		var listaDeNacionalidades = ps.listaDeNacionalidades(listFlux);
		var rankingPorPais = ps.ranking(list,"Argentina");


		System.out.println("Jugadores mayores de 34 años");
		jugadoresMayoresDe34.toStream().forEach(s -> System.out.println(s.toString()));


		System.out.println("Jugadores mayores de 39 años");
		jugadoresMayoresDe.toStream().forEach(s -> System.out.println(s.toString()));

		System.out.println("Jugadores ordenados por edad");
		ordenarJugadoresPorEdadDeMenorAMayor.toStream().forEach(s -> System.out.println(s.toString()));


		System.out.println("Lista de nacionalidades");
		listaDeNacionalidades.toStream().forEach(s -> System.out.println(s.toString()));


		System.out.println("Jugadores por club");
		jugadoresPorClub.toStream().forEach(s -> System.out.println(s.toString()));

		System.out.println("Ranking por pais");
		rankingPorPais.toStream().forEach( s ->  System.out.println(s.toString()));
	}
}
