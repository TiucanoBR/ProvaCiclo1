package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Disciplina;
import br.com.senac.service.DisciplinaService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/disc")
public class DisciplinaController {
	
	@Autowired
	DisciplinaService service;
	
	@GetMapping("/listar")
	public ModelAndView listarDisciplinas() {
		ModelAndView mv = new ModelAndView("disc/listarDisciplina");
		mv.addObject("discs", service.buscarTodasDisciplinas());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarDisciplina() {
		ModelAndView mv = new ModelAndView("disc/cadastrarDisciplina");
		mv.addObject("discNova", new Disciplina());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(Disciplina disc) {
		service.salvar(disc);
		return "redirect:/disc/listar";
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("disc/alterarDisciplina");
		mv.addObject("disc", service.buscarPorId(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterar(Disciplina disc) throws ObjectNotFoundException {
		service.salvarAlteracao(disc);
		return "redirect:/disc/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable("id") Integer id) {
		service.excluir(id);
		return "redirect:/disc/listar";
	}

}
