package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Curso;
import br.com.senac.repository.CursoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CursoService {
	
	@Autowired
	CursoRepository repo;
	
	public Curso salvar(Curso curso) {
		return repo.save(curso);
	}
	
	public List<Curso> salvarTodos(List<Curso> cursos){
		return repo.saveAll(cursos);
	}
	
	public List<Curso> buscarTodosCursos(){
		return repo.findAll();
	}
	
	public Curso buscarPorId(Integer id) throws ObjectNotFoundException {
		Optional<Curso> curso = repo.findById(id);
		return curso.orElseThrow(() -> new ObjectNotFoundException("Curso não encontrado!"));
	}
	
	public Curso salvarAlteracao(Curso cursoAlterado) throws ObjectNotFoundException {
		Curso cursoAtual = buscarPorId(cursoAlterado.getId());
		cursoAtual.setNome(cursoAlterado.getNome());
		cursoAtual.setDescricao(cursoAlterado.getDescricao());
		cursoAtual.setProfs(cursoAlterado.getProfs());
		return salvar(cursoAtual);
	}
	
	public void excluir(Integer idAcess) {
		repo.deleteById(idAcess);
	}
}
