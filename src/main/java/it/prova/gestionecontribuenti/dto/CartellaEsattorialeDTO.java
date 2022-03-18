package it.prova.gestionecontribuenti.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.StatoCartellaEsattoriale;

public class CartellaEsattorialeDTO {
	private Long id;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{importo.notnull}")
	@Min(1)
	private Integer importo;
	@NotNull(message = "{stato.notblank}")
	private StatoCartellaEsattoriale stato;
	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, StatoCartellaEsattoriale stato,
			ContribuenteDTO contribuente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, StatoCartellaEsattoriale stato,
			ContribuenteDTO contribuente) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, StatoCartellaEsattoriale stato) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo, StatoCartellaEsattoriale stato) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public StatoCartellaEsattoriale getStato() {
		return stato;
	}

	public void setStato(StatoCartellaEsattoriale stato) {
		this.stato = stato;
	}

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato,
				this.contribuente.buildContribuenteModel());
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(
			CartellaEsattoriale cartellaEsattorialeModel, boolean includeContribuente) {
		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaEsattorialeModel.getId(),
				cartellaEsattorialeModel.getDescrizione(), cartellaEsattorialeModel.getImporto(),
				cartellaEsattorialeModel.getStato());

		if (includeContribuente)
			result.setContribuente(
					ContribuenteDTO.buildContribuenteDTOFromModel(cartellaEsattorialeModel.getContribuente()));

		return result;
	}

	public static List<CartellaEsattorialeDTO> createCartellaEsattorialeDTOListFromModelList(
			List<CartellaEsattoriale> modelListInput, boolean includeRegisti) {
		return modelListInput.stream().map(cartellaEsattorialeEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeEntity,
					includeRegisti);
		}).collect(Collectors.toList());
	}
}
