package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Professor;
import br.com.senac.repository.ProfessorRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository repo;
	
	public Professor salvar(Professor professor) {
		return repo.save(professor);
	}
	
	public List<Professor> salvarTodos(List<Professor> professores){
		return repo.saveAll(professores);
	}
	
	public List<Professor> buscarTodosProfessores(){
		return repo.findAll();
	}
	
	public Professor buscarPorId(Integer id) throws ObjectNotFoundException {
		Optional<Professor> professor = repo.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado!"));
	}
	
	public Professor salvarAlteracao(Professor professorAlterado) throws ObjectNotFoundException {
		Professor professorAtual = buscarPorId(professorAlterado.getId());
		professorAtual.setNome(professorAlterado.getNome());
		professorAtual.setSobrenome(professorAlterado.getSobrenome());
		professorAtual.setDisciplina(professorAlterado.getDisciplina());
		professorAtual.setCurso(professorAlterado.getCurso());
		return salvar(professorAtual);
	}
	
	public void excluir(Integer idAcess) {
		repo.deleteById(idAcess);
	}
}
