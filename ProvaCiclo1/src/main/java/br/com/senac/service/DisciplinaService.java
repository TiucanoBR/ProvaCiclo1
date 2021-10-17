package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Disciplina;
import br.com.senac.repository.DisciplinaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class DisciplinaService {
	
	@Autowired
	DisciplinaRepository repo;
	
	public Disciplina salvar(Disciplina disciplina) {
		return repo.save(disciplina);
	}
	
	public List<Disciplina> salvarTodos(List<Disciplina> disciplinas){
		return repo.saveAll(disciplinas);
	}
	
	public List<Disciplina> buscarTodasDisciplinas(){
		return repo.findAll();
	}
	
	public Disciplina buscarPorId(Integer id) throws ObjectNotFoundException {
		Optional<Disciplina> disciplina = repo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException("Disciplina não encontrado!"));
	}
	
	public Disciplina salvarAlteracao(Disciplina disciplinaAlterado) throws ObjectNotFoundException {
		Disciplina disciplinaAtual = buscarPorId(disciplinaAlterado.getId());
		disciplinaAtual.setNome(disciplinaAlterado.getNome());
		disciplinaAtual.setDescricao(disciplinaAlterado.getDescricao());
		return salvar(disciplinaAtual);
	}
	
	public void excluir(Integer idAcess) {
		repo.deleteById(idAcess);
	}
}
