package com.sistema.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.models.model.Cidade;
import com.sistema.models.model.Professor;
import com.sistema.models.service.CidadeService;
import com.sistema.models.service.ProfessorService;

@Controller
@RequestMapping(value = "/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CidadeService cidadeService;

//  ROTINAS DE INCLUSÃO
	
	@GetMapping(value="/cadastro")
	public String showFormIncluir(Model model) {
		Professor professor = new Professor();
		model.addAttribute("titulo", "Incluir Professor");
		model.addAttribute("professor", professor);
		return "/professor/incluir"; 
	}
	
	@PostMapping(value = "/incluir")
	public String incluir(@Valid Professor professor, BindingResult result, RedirectAttributes attr, Model model) {
		 if (result.hasErrors()) {
			 return "/professor/incluir";
		 }
		 professorService.incluir(professor);
		 model.addAttribute("titulo", "Incluir Professor");
		 model.addAttribute("professor", new Professor());
		 attr.addFlashAttribute("success", "Registro incluido com sucesso");
		 return "redirect:/professor/lista";
	}

// ROTINAS DE ALTERAÇÃO
	
	@GetMapping(value="/alterar/{id}")
	public String showFormAlterar(@PathVariable("id") Long id, Model model) {
		Professor professor = professorService.consultarPorId(id);
	
		model.addAttribute("titulo","Alterar Professor");
		model.addAttribute("professor", professor);
		return "/professor/alterar";
	}
	
	
	@PostMapping(value = "/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, @Valid Professor professor, BindingResult result,  RedirectAttributes attr, Model model) {
		if (result.hasErrors()) {
			 return "/professor/alterar";
		 }
		professorService.alterar(id, professor);
		model.addAttribute("titulo", "Alterar Professor");
		model.addAttribute("professor", new Professor());
		attr.addFlashAttribute("success", "Registro alterado com sucesso");
		return "redirect:/professor/lista";
	}

// ROTINAS DE EXCLUSÃO
	
	@GetMapping(value = "/excluir/{id}")
	public String showFormExcluir(@PathVariable("id") Long id, Model model) {
		Professor professor = professorService.consultarPorId(id);
		model.addAttribute("titulo","Excluir Professor");
		model.addAttribute("professor", professor);
		return "/professor/excluir";
	}
	
	@PostMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, Model model) {
		professorService.excluir(id);
		model.addAttribute("titulo", "Excluir Professor");
		model.addAttribute("professor", new Professor());
		return "/professor/excluir";
	}
	

// ROTINA DE CONSULTA	
	
	@GetMapping(value = "/consultar-por-id/{id}")
	public String consultarPorId(@PathVariable("id") Long id, Model model) {
		Professor professor = professorService.consultarPorId(id);
		model.addAttribute("titulo","Consultar Professor");
		model.addAttribute("professor", professor);
		return "/professor/consultar";
	}

	
	
	@GetMapping(value = "/lista")
	public String lista(
			@RequestParam(value="keyword", required = false ) String keyword,
			Model model) {
		
		List<Professor> professores = new ArrayList<>();
		
		if (Objects.isNull(keyword)) {
			professores = professorService.lista();
		} else {
			professores = professorService.lista(keyword);
		}
		
		model.addAttribute("titulo","Listagem de Professor");
		model.addAttribute("professores", professores);
		return "/professor/lista";
	}
	
	@ModelAttribute("cidades")
	public List<Cidade> listaCidade(){
		return cidadeService.lista();
	}
	

}
