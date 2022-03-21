package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.model.StatoCartellaEsattoriale;

public class ContribuenteConCartelleDTO {
	private Long id;
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceFiscale;
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	private Set<CartellaEsattorialeDTO> cartelleEsattoriali = new HashSet<>();

	public ContribuenteConCartelleDTO() {
		super();
	}

	public ContribuenteConCartelleDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteConCartelleDTO(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	public ContribuenteConCartelleDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteConCartelleDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo,
			Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataDiNascita, this.codiceFiscale,
				this.indirizzo);
	}

	public static ContribuenteConCartelleDTO buildContribuenteConCartelleDTOFromModel(Contribuente contribuenteModel) {
		return new ContribuenteConCartelleDTO(contribuenteModel.getId(), contribuenteModel.getNome(),
				contribuenteModel.getCognome(), contribuenteModel.getDataDiNascita(),
				contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo(),
				CartellaEsattorialeDTO
						.createCartellaEsattorialeDTOListFromModelList(
								contribuenteModel.getCartelleEsattoriali().stream().collect(Collectors.toList()), true)
						.stream().collect(Collectors.toSet()));
	}

	public static List<ContribuenteConCartelleDTO> createContribuenteConCartelleDTOListFromModelList(
			List<Contribuente> modelListInput) {
		return modelListInput.stream().map(contribuenteEntity -> {
			return ContribuenteConCartelleDTO.buildContribuenteConCartelleDTOFromModel(contribuenteEntity);
		}).collect(Collectors.toList());
	}

	public boolean isInContenzioso() {
		for (CartellaEsattorialeDTO cartellaEsattorialeItem : this.cartelleEsattoriali) {
			if (cartellaEsattorialeItem.getStato().equals(StatoCartellaEsattoriale.IN_CONTENZIOSO)) {
				return true;
			}
		}
		return false;
	}

	public Integer totaleImportoCartelle() {
		Integer result = 0;
		for (CartellaEsattorialeDTO cartellaEsattorialeItem : this.cartelleEsattoriali) {
			result += cartellaEsattorialeItem.getImporto();
		}
		return result;
	}

	public Integer totaleConclusoEPagato() {
		Integer result = 0;
		for (CartellaEsattorialeDTO cartellaEsattorialeItem : this.cartelleEsattoriali) {
			if (cartellaEsattorialeItem.getStato().equals(StatoCartellaEsattoriale.CONCLUSA)) {
				result += cartellaEsattorialeItem.getImporto();
			}
		}
		return result;
	}

	public Integer totaleInContenzioso() {
		Integer result = 0;
		for (CartellaEsattorialeDTO cartellaEsattorialeItem : this.cartelleEsattoriali) {
			if (cartellaEsattorialeItem.getStato().equals(StatoCartellaEsattoriale.IN_CONTENZIOSO)) {
				result += cartellaEsattorialeItem.getImporto();
			}
		}
		return result;
	}
}
