package com.web.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.converter.ProductConverter;
import com.web.entity.ProductEntity;
import com.web.model.dto.ProductDTO;
import com.web.model.request.ProductRequest;
import com.web.model.request.ProductSearchRequest;
import com.web.model.response.ProductResponse;
import com.web.repository.ProductRepository;
import com.web.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public List<ProductResponse> findAll(ProductRequest productRequest, Pageable pageable) {
		Page<ProductEntity> products = productRepository.findAll(productRequest, pageable, getAdminItemCount());
		List<ProductResponse> result = new ArrayList<>();
		for (ProductEntity item : products) {
			ProductResponse productResponse = productConverter.toProductResponse(item);
			result.add(productResponse);
		}
		return result;
	}
	
	private static final Path CURRENT_FOLDER = Paths.get("src/main/resources");

	public String updateImage(ProductDTO productDTO) {
		Path staticPath = Paths.get("static");
		Path imagePath = Paths.get("images");
		if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
			try {
				Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)
				.resolve(productDTO.getImageUrl().getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(file)) {
			os.write(productDTO.getImageUrl().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productDTO.getImageUrl().getOriginalFilename().toString();
	}
	
	public ProductResponse findById(Long id) {
		ProductEntity productEntity = productRepository.findById(id).get();
		ProductResponse productResponse = productConverter.converterToProductResponseEdit(productEntity);
		return productResponse;
	}
	
	private static final Path IMAGE_UPLOAD_DIR = Paths.get("uploads/images");
	public String saveThumbnail(ProductDTO productDTO) {
		try {
			if (!Files.exists(IMAGE_UPLOAD_DIR)) {
				Files.createDirectories(IMAGE_UPLOAD_DIR);
			}
			String originalFileName = productDTO.getImageUrl().getOriginalFilename();
			Path imagePath = IMAGE_UPLOAD_DIR.resolve(originalFileName);
			try (OutputStream os = Files.newOutputStream(imagePath)) {
	            os.write(productDTO.getImageUrl().getBytes());
	        }
			return originalFileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductDTO addOrUpdateProduct(ProductDTO productDTO) {
		ProductEntity productEntity = productConverter.converterToProductEntity(productDTO);
		productEntity.setImageUrl(saveThumbnail(productDTO));
		productRepository.save(productEntity);
		return productConverter.converterToProductDTO(productEntity);
	}

	@Override
	public void deleteProducts(List<Long> ids) {
		productRepository.deleteByIdIn(ids);
	}

	@Override
	public List<ProductResponse> findProducts(ProductSearchRequest productSearchRequest, Pageable pageable) {
		Page<ProductEntity> productEntities = productRepository.findProducts(productSearchRequest, pageable, getTotalItems());
		List<ProductResponse> result = new ArrayList<>();
		for(ProductEntity item : productEntities) {
			ProductResponse productResponse = productConverter.converterToProductResponse(item);
			result.add(productResponse);
		}
		return result;
	}

	@Override
	public int getTotalItems() {
		long total = productRepository.count();
		int pageSize = 6;;
		return (int) Math.ceil((double)(total / pageSize));
	}

	@Override
	public int getAdminItemCount() {
		double totalItem = productRepository.count() / 3;
		return (int) Math.ceil(totalItem);
	}
}
