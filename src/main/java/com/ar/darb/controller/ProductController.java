package com.ar.darb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ar.darb.dao.Products;
import com.ar.darb.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public String homePage(Model model) {
		List<Products> prodsList = service.listOfProducts();
		model.addAttribute("listProducts", prodsList);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newProduct(Model model) {
		Products product = new Products();
		model.addAttribute("product", product);
		return "create_product/new_form";
	}

	@PostMapping("/save")
	public String saveItem(@ModelAttribute("product") Products product) {
		System.out.println(product);
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/view/{id}")
	public ModelAndView showViewForm(@PathVariable(name = "id") int id) {
		
//		ModelAndView mav = new ModelAndView("viewproduct_form");
		ModelAndView mav = new ModelAndView("viewproduct_modal");
		Products product = service.findById(id);
		mav.addObject("product", product);
//		mav.addObject("pageTitle", "Product Details (ID : "+id+" )");
		return mav;
	}
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") int id) {

		ModelAndView mav = new ModelAndView("edit_form");
		Products product = service.findById(id);
		mav.addObject("product", product);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Products product) {
		service.update(product);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}

}
