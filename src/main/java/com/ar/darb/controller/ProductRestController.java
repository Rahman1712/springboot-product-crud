package com.ar.darb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ar.darb.dao.Products;
import com.ar.darb.service.ProductService;

@RestController
@RequestMapping("/prod")
public class ProductRestController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/check_product")
	public Object checkProductAlreadyinList(@RequestParam int id) {
		Products product = service.findById(id);
		return product == null ? "NO_PRODUCT" : product;
	}
	
}
