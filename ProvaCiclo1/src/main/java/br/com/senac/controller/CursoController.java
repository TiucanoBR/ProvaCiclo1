package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Curso;
import br.com.senac.service.CursoService;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoService service;
	
	@Autowired
	ProfessorService profService;
	
	@GetMapping("/listar")
	public ModelAndView listarCursos() {
		ModelAndView mv = new ModelAndView("curso/listarCurso");
		mv.addObject("cursos", service.buscarTodosCursos());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCurso() {
		ModelAndView mv = new ModelAndView("curso/cadastrarCurso");
		mv.addObject("cursoNovo", new Curso());
		mv.addObject("profs", profService.buscarTodosProfessores());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(Curso curso) {
		service.salvar(curso);
		return "redirect:/curso/listar";
	}
	
	@GetMapping("alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("curso/alterarCurso");
		mv.addObject("curso", service.buscarPorId(id));
		mv.addObject("profs", profService.buscarTodosProfessores());
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterar(Curso curso) throws ObjectNotFoundException {
		service.salvarAlteracao(curso);
		return "redirect:/curso/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable("id") Integer id) {
		service.excluir(id);
		return "redirect:/curso/listar";
	}

}
