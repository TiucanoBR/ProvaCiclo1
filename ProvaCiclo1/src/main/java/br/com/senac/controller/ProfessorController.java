package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Professor;
import br.com.senac.service.CursoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/prof")
public class ProfessorController {
	
	@Autowired
	ProfessorService service;
	
	@Autowired
	DisciplinaService discService;
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping("/listar")
	public ModelAndView listarProfessores() {
		ModelAndView mv = new ModelAndView("prof/listarProfessor");
		mv.addObject("profs", service.buscarTodosProfessores());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarProfessor() {
		ModelAndView mv = new ModelAndView("prof/cadastrarProfessor");
		mv.addObject("profNovo", new Professor());
		mv.addObject("discs", discService.buscarTodasDisciplinas());
		mv.addObject("cursos", cursoService.buscarTodosCursos());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(Professor prof) {
		service.salvar(prof);
		return "redirect:/prof/listar";
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("prof/alterarProfessor");
		mv.addObject("prof", service.buscarPorId(id));
		mv.addObject("discs", discService.buscarTodasDisciplinas());
		mv.addObject("cursos", cursoService.buscarTodosCursos());
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterar(Professor prof) throws ObjectNotFoundException {
		service.salvarAlteracao(prof);
		return "redirect:/prof/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable("id") Integer id) {
		service.excluir(id);
		return "redirect:/prof/listar";
	}

}
