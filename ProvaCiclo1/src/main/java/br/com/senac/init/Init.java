package br.com.senac.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Curso;
import br.com.senac.domain.Disciplina;
import br.com.senac.domain.Professor;
import br.com.senac.service.CursoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	ProfessorService profService;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Disciplina disc1 = new Disciplina();
		disc1.setNome("Prog web I");
		disc1.setDescricao("Programação web 1");
		disciplinaService.salvar(disc1);
		
		Curso curso1 = new Curso();
		curso1.setNome("ADS");
		curso1.setDescricao("Analise e desenvolvimento de sistemas");
		cursoService.salvar(curso1);
		
		Professor prof1 = new Professor();
		prof1.setNome("Marcelo");
		prof1.setSobrenome("Estruc");
		prof1.setDisciplina(disc1);
		prof1.setCurso(curso1);
		profService.salvar(prof1);
				
	}
}
