package com.sistema.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sistema.models.model.Cidade;
import com.sistema.models.service.CidadeService;
import com.sistema.models.service.CidadeService;
import com.sistema.web.response.MensagemRest;

@RestController
@RequestMapping(value = "/rest/cidade")
public class CidadeRestController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private MensagemRest<Cidade> mensagemRest;

//  ROTINAS DE INCLUSÃO
	
	@GetMapping(value="/cadastro")
	public String showFormIncluir(Model model) {
		Cidade cidade = new Cidade();
		model.addAttribute("titulo", "Incluir Cidade");
		model.addAttribute("cidade", cidade);
		return "/cidade/incluir"; 
	}
	
	@PostMapping(value = "/incluir")
	public ResponseEntity<?> incluir(@RequestBody @Valid Cidade cidade) {
		 cidade = cidadeService.incluir(cidade);
		 mensagemRest.setStatus(HttpStatus.OK.value());
		 mensagemRest.setMensagem("Cidade Cadastrado com sucesso!");
		 mensagemRest.setObject(cidade);
		 return ResponseEntity.ok().body(mensagemRest);
	}

// ROTINAS DE ALTERAÇÃO
	
	@GetMapping(value="/alterar/{id}")
	public String showFormAlterar(@PathVariable("id") Long id, Model model) {
		Cidade cidade = cidadeService.consultarPorId(id);
	
		model.addAttribute("titulo","Alterar Cidade");
		model.addAttribute("cidade", cidade);
		return "/cidade/alterar";
	}
	
	
	@PostMapping(value = "/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, @Valid Cidade cidade, BindingResult result,  RedirectAttributes attr, Model model) {
		if (result.hasErrors()) {
			 return "/cidade/alterar";
		 }
		cidadeService.alterar(id, cidade);
		model.addAttribute("titulo", "Alterar Cidade");
		model.addAttribute("cidade", new Cidade());
		attr.addFlashAttribute("success", "Registro alterado com sucesso");
		return "redirect:/cidade/lista";
	}

// ROTINAS DE EXCLUSÃO
	
	@GetMapping(value = "/excluir/{id}")
	public String showFormExcluir(@PathVariable("id") Long id, Model model) {
		Cidade cidade = cidadeService.consultarPorId(id);
		model.addAttribute("titulo","Excluir Cidade");
		model.addAttribute("cidade", cidade);
		return "/cidade/excluir";
	}
	
	@PostMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, Model model) {
		cidadeService.excluir(id);
		model.addAttribute("titulo", "Excluir Cidade");
		model.addAttribute("cidade", new Cidade());
		return "/cidade/excluir";
	}
	

// ROTINA DE CONSULTA	
	
	@GetMapping(value = "/consultar-por-id/{id}")
	public String consultarPorId(@PathVariable("id") Long id, Model model) {
		Cidade cidade = cidadeService.consultarPorId(id);
		model.addAttribute("titulo","Consultar Cidade");
		model.addAttribute("cidade", cidade);
		return "/cidade/consultar";
	}

	
	
	@GetMapping(value = "/lista")
	public List<Cidade> lista(
			@RequestParam(value="keyword", required = false ) String keyword) {
		
		List<Cidade> cidades = new ArrayList<>();
		
		if (Objects.isNull(keyword)) {
			cidades = cidadeService.lista();
		} else {
			cidades = cidadeService.lista(keyword);
		}
		
		return cidades;
	}
	
	@GetMapping(value = "/listaselecionada")
	public List<Cidade> listaCidade(){
		return cidadeService.lista();
	}
	

}
