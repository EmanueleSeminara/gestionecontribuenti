package it.prova.gestionecontribuenti.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository
		extends PagingAndSortingRepository<CartellaEsattoriale, Long>, JpaSpecificationExecutor<CartellaEsattoriale> {
	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);
}
