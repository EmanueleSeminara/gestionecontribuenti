package it.prova.gestionecontribuenti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository
		extends PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {
	@EntityGraph(attributePaths = { "cartelleEsattoriali" })
	Optional<Contribuente> findById(Long id);
}
