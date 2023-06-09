package com.company.inventary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventary.dao.ICategoryDao;
import com.company.inventary.model.Category;
import com.company.inventary.response.CategoryResponseRest;
@Service
public class CategoryServiceImpl implements IcategoryService {

	@Autowired
	private  ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)	
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response=new CategoryResponseRest();
		try {
			List<Category>category=(List<Category>)categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("respuesta Ok", "00", "Resp Exitosa");
		} catch (Exception e) {
			response.setMetadata("respuesta Nok", "-1", "Error al Consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly=true)	
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		CategoryResponseRest response=new CategoryResponseRest();
		List<Category>list=new ArrayList<>();
		
		try {
			Optional<Category>category=categoryDao.findById(id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("respuesta ok", "00", "Categoria encontrada");

			}else {
				response.setMetadata("respuesta Nok", "-1", "Categoria no encontrada");
				
				return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("respuesta Nok", "-1", "Error al Consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);

	}
	

}
