package com.esouza.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.esouza.cursomc.domain.Categoria;
import com.esouza.cursomc.repositories.CategoriaRepository;
import com.esouza.cursomc.services.exceptions.DataIntegrityException;
import com.esouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada ID:" + id) );
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		return categoriaRepository.save(obj);
	}
	
	public void delete(Integer id) {
		buscar(id);
		try {
			categoriaRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria possui produtos");
		}
	}
}
