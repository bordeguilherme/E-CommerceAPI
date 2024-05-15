package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entities.Categoria;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	 CategoriaRepository categoriaRepo;
	
	//recuperar todos os categoriaes
	public List<Categoria> listarCategorias(){
		return categoriaRepo.findAll();
	}
	
	//recuperar um categoria pela sua chave primária
	public Categoria buscarCategoriaPorId(Long id){
		return categoriaRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Categoria", id));
	}
	
	//salvar um novo categoria
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
	
	//atualizar um determinado categoria
	public Categoria atualizarCategoria(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
	
	//deletar um determinado categoria
	public Boolean deletarCategoria(Categoria categoria) {
		if(categoria == null)
			return false;
		
		Categoria categoriaExistente = buscarCategoriaPorId(categoria.getIdCategoria());
		
		if(categoriaExistente == null)
			return false;
		
		categoriaRepo.delete(categoria);
		
		Categoria categoriaContinuaExistindo =
				buscarCategoriaPorId(categoria.getIdCategoria());
		
		if(categoriaContinuaExistindo != null)
			return false;
		
		return true;
	}

	public boolean existeCategoria(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
