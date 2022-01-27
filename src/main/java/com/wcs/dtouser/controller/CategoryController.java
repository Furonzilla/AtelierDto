package com.wcs.dtouser.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.dtouser.dto.CategoryDto;
import com.wcs.dtouser.dto.CategoryMustHaveDto;
import com.wcs.dtouser.entity.Category;
import com.wcs.dtouser.entity.Product;
import com.wcs.dtouser.entity.ProductCategory;
import com.wcs.dtouser.dto.ProductDto;
import com.wcs.dtouser.dto.ProductWithMustHaveDto;
import com.wcs.dtouser.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping
	public Category create(@Valid @RequestBody CategoryDto categoryDto) {
		return categoryRepository.save(new Category(categoryDto.getName()));
	}

	// a partir de l'id catégorie récuperer catégorie + produits

	// chercher les catégorie

	// on récupère les produits lié à la catégorie

	@GetMapping("/{id}")
	public CategoryDto get(@PathVariable(required = true) Long id) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// pour cette catégory je récup toutes les associations de produit de celle ci
		List<ProductCategory> productCategories = category.getProductCategories();
		// pour chaque association de produit, je récupère le produit , je le mets dans
		// une dto dans laquelle je rajoute le mustHave
		// puis j'ajoute cette dto à mon catégoryDto
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		for (ProductCategory productCategory : productCategories) {
			Product product = productCategory.getProduct();
			ProductWithMustHaveDto productWithMustHaveDto = new ProductWithMustHaveDto();
			productWithMustHaveDto.setId(product.getId());
			productWithMustHaveDto.setName(product.getName());
			productWithMustHaveDto.setPrice(product.getPrice());
			productWithMustHaveDto.setMustHave(productCategory.isMustHave());
			categoryDto.getProductsWithMustHaveDto().add(productWithMustHaveDto);
			}
		// je renvoie le tout
		return categoryDto;
	}
}
