package it.prova.gestionecontribuenti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository {
	@EntityGraph(attributePaths = { "cartelleEsattoriali" })
	Optional<Contribuente> findById(Long id);
}
