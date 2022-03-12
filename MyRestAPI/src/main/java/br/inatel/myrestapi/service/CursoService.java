package br.inatel.myrestapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.myrestapi.model.Curso;

@Service
public class CursoService {

	private Map<Long, Curso> mapa = new HashMap<>();
	
	@PostConstruct
	private void inicializarMapa() {
		Curso c1= new Curso(1L, "Descricao 1", 10);
		Curso c2= new Curso(2L, "Descricao 2", 20);
		Curso c3= new Curso(3L, "Descricao 3", 30);
		
		mapa.put(c1.getId(), c1);
		mapa.put(c2.getId(), c2);
		mapa.put(c3.getId(), c3);
	}
	
	public List<Curso>pesquisarCurso(){
		return mapa.entrySet().stream()
				.map(m -> m.getValue())
				.collect(Collectors.toList());
	}
	
	public Curso buscarCursoPeloId(Long cursoId) {
		Curso curso = mapa.get(cursoId);
		if(curso != null)
			return curso;
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public Curso criarCurso(Curso curso) {
		Long cursoId = gerarCursoIdUnico();
		curso.setId(cursoId);
		
		mapa.put(cursoId,  curso);
		return curso;
	}
	
	public void atualizarCurso(Curso curso) {
		mapa.put(curso.getId(), curso);
	}
	
	public void removerCurso(Long cursoId) {
		mapa.remove(cursoId);
	}
	
	private Long gerarCursoIdUnico(){
		return System.currentTimeMillis();
	}
}































