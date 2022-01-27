package com.wcs.dtouser.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.wcs.dtouser.entity.ProductCategory;

public class CategoryDto {
	
	private Long id;
	
	@NotBlank
	@Size(min = 2, max =100)
	private String name;
	
	private List<ProductWithMustHaveDto> productsWithMustHaveDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductWithMustHaveDto> getProductsWithMustHaveDto() {
		return productsWithMustHaveDto;
	}

	public void setProductsWithMustHaveDto(List<ProductWithMustHaveDto> productsWithMustHaveDto) {
		this.productsWithMustHaveDto = productsWithMustHaveDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
