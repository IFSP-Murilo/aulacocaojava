package com.sistema.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.models.model.Cidade;
import com.sistema.models.model.Professor;
import com.sistema.models.service.CidadeService;
import com.sistema.models.service.ProfessorService;
import com.sistema.web.response.Fields;
import com.sistema.web.response.MensagemRest;

@RestController
@RequestMapping(value = "/rest/professor")
public class ProfessorRestController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MensagemRest<Professor> mensagemRest;

//  ROTINAS DE INCLUSÃO
	
	@GetMapping(value="/cadastro")
	public String showFormIncluir(Model model) {
		Professor professor = new Professor();
		model.addAttribute("titulo", "Incluir Professor");
		model.addAttribute("professor", professor);
		return "/professor/incluir"; 
	}
	
	@PostMapping(value = "/incluir")
	public ResponseEntity<?> incluir(@RequestBody @Valid Professor professor, BindingResult result) {
		 if (result.hasErrors()) {
			 List<Fields> lista = result.getFieldErrors()
					                    .stream()
					                    .map(erro->{
					                    	String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
					                    	Fields field = new Fields();
					                    	field.setNameField(erro.getField());
					                    	field.setMessageErrorField(mensagem);
					                    	field.setError(true);
					                    	return field;
					                    }).collect(Collectors.toList());
			mensagemRest.setStatus(HttpStatus.BAD_REQUEST.value());
			mensagemRest.setMensagem("Erro no cadastro do Professor!");
			mensagemRest.setObject(professor);
			mensagemRest.setError(true);
			mensagemRest.setFields(lista);
		 } else {
			 professor = professorService.incluir(professor);
             mensagemRest.setError(false);
             mensagemRest.setFields(null);
			 mensagemRest.setStatus(HttpStatus.OK.value());
			 mensagemRest.setMensagem("Professor Cadastrado com sucesso!");
			 mensagemRest.setObject(professor);
		 } 
		 return ResponseEntity.ok().body(mensagemRest);
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
	public List<Professor> lista(
			@RequestParam(value="keyword", required = false ) String keyword) {
		
		List<Professor> professores = new ArrayList<>();
		
		if (Objects.isNull(keyword)) {
			professores = professorService.lista();
		} else {
			professores = professorService.lista(keyword);
		}
		
		return professores;
	}
	
	@ModelAttribute("cidades")
	public List<Cidade> listaCidade(){
		return cidadeService.lista();
	}
	

}
