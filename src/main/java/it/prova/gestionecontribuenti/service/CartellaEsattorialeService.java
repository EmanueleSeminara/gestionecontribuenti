package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {
	public List<CartellaEsattoriale> listAllElements();

	public CartellaEsattoriale caricaSingoloElemento(Long id);

	public void aggiorna(CartellaEsattoriale cartellaEsattorialeInstance);

	public void inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance);

	public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance);

	public void rimuoviById(Long idCartellaEsattoriale);

	public Page<CartellaEsattoriale> findByExampleWithPagination(CartellaEsattoriale example, Integer pageNo,
			Integer pageSize, String sortBy);
}
