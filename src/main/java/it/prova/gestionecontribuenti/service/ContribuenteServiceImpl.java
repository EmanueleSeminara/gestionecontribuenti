package it.prova.gestionecontribuenti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecontribuenti.exceptions.ContribuenteConCartelleEsattorialiException;
import it.prova.gestionecontribuenti.exceptions.ElementNotFoundException;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.repository.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {
	@Autowired
	private ContribuenteRepository repository;

	@Transactional(readOnly = true)
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Contribuente contribuenteInstance) {
		repository.save(contribuenteInstance);
	}

	@Transactional
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Contribuente> findByExampleWithPagination(Contribuente example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<Contribuente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscae")),
						"%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getIndirizzo()))
				predicates.add(
						cb.like(cb.upper(root.get("indirizzo")), "%" + example.getIndirizzo().toUpperCase() + "%"));

			if (example.getDataDiNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiNascita"), example.getDataDiNascita()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Transactional
	public void rimuoviById(Long idContribuente) {
		if (idContribuente == null) {
			throw new IllegalArgumentException("L'id inserito non è valido!");
		}
		Contribuente contribuenteDaEliminare = repository.findById(idContribuente).get();
		if (contribuenteDaEliminare == null) {
			throw new ElementNotFoundException("Non esiste un contribuente associato a questo id");
		}
		if (contribuenteDaEliminare.getCartelleEsattoriali().size() != 0) {
			throw new ContribuenteConCartelleEsattorialiException(
					"Impossibile eliminare il contribuente, sono presenti film associati");
		}
		repository.deleteById(idContribuente);

	}

	@Transactional(readOnly = true)
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}
}
