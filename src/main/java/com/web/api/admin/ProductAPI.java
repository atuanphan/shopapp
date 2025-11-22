package com.web.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.dto.ProductDTO;
import com.web.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/admin/product")
public class ProductAPI {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/edit")
	public ResponseEntity<ProductDTO> productEdit(@Valid @ModelAttribute("productEdit") ProductDTO productDTO) {
		return ResponseEntity.ok(productService.addOrUpdateProduct(productDTO));//trả về status 200;	
	}
	
	@DeleteMapping("/{ids}")
	public ResponseEntity<Void> deleteProduct(@PathVariable List<Long> ids) {
		productService.deleteProducts(ids);
		return ResponseEntity.noContent().build();//trả về HTTP status 204 No Content – nghĩa là yêu cầu đã thành công, nhưng không có nội dung trả về trong body
	}
}
