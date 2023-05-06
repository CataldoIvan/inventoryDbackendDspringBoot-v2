package com.company.inventary.services;

import org.springframework.http.ResponseEntity;

import com.company.inventary.response.CategoryResponseRest;

public interface IcategoryService {

	public ResponseEntity<CategoryResponseRest>search();
	public ResponseEntity<CategoryResponseRest>searchById(Long id);
	
}
