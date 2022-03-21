package it.prova.gestionecontribuenti.we.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecontribuenti.dto.CartellaEsattorialeDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.service.CartellaEsattorialeService;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllCartellaEsattoriales() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartellaEsattoriales = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartelleesattoriali_list_attribute",
				CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartellaEsattoriales, false));
		mv.setViewName("cartellaesattoriale/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createCartellaEsattoriale(Model model) {
		model.addAttribute("insert_cartellaesattoriale_attr", new CartellaEsattorialeDTO());
		return "cartellaesattoriale/insert";
	}

	// inietto la request perché ci potrebbe tornare utile per ispezionare i
	// parametri
	@PostMapping("/save")
	public String saveCartellaEsattoriale(
			@Valid @ModelAttribute("insert_cartellaEsattoriale_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per
		// visualizzarne nome e cognome nel campo testo
		if (cartellaEsattorialeDTO.getContribuente() == null
				|| cartellaEsattorialeDTO.getContribuente().getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaEsattorialeDTO.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(
					contribuenteService.caricaSingoloElemento(cartellaEsattorialeDTO.getContribuente().getId())));

		if (result.hasErrors()) {
			return "cartellaesattoriale/insert";
		}
		cartellaEsattorialeService.inserisciNuovo(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/search")
	public String searchCartellaEsattoriale(Model model) {
		// model.addAttribute("search_cartellaEsattoriale_attr", new
		// CartellaEsattorialeDTO());
		return "cartellaesattoriale/search";
	}

	@PostMapping("/list")
	public String listCartelleEsattoriali(CartellaEsattorialeDTO cartellaEsattorialeExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy, ModelMap model) {

		List<CartellaEsattoriale> cartelleEsattoriali = cartellaEsattorialeService.findByExampleWithPagination(
				cartellaEsattorialeExample.buildCartellaEsattorialeModel(), pageNo, pageSize, sortBy).getContent();

		model.addAttribute("cartelleesattoriali_list_attribute",
				CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartelleEsattoriali, true));
		return "cartellaesattoriale/list";
	}

	@GetMapping("/show/{idCartellaEsattoriale}")
	public String showCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
		model.addAttribute("show_cartellaEsattoriale_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		return "cartellaesattoriale/show";
	}

	@GetMapping("/delete/{idCartellaEsattoriale}")
	public String delete(@PathVariable(required = true) Long idCartellaEsattoriale, Model model,
			RedirectAttributes redirectAttrs) {

		model.addAttribute("delete_cartellaEsattoriale_attr",
				cartellaEsattorialeService.caricaSingoloElemento(idCartellaEsattoriale));
		return "cartellaesattoriale/delete";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam(required = true) Long idDaRimuovere, RedirectAttributes redirectAttrs) {

		cartellaEsattorialeService.rimuoviById(idDaRimuovere);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/edit/{idCartellaEsattoriale}")
	public String editCartellaEsattoriale(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
		model.addAttribute("edit_cartellaEsattoriale_attr", CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale), true));
		return "cartellaesattoriale/edit";
	}

	@PostMapping("/update")
	public String updateCartellaEsattoriale(
			@Valid @ModelAttribute("edit_cartellaEsattoriale_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "cartellaesattoriale/edit";
		}
		cartellaEsattorialeService.aggiorna(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}
}
