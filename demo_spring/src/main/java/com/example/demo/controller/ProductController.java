package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.Repository;


@RestController
@RequestMapping(path = "/api/laptop") // request toi duong dan
public class ProductController {
	
	//?
	@Autowired
	private Repository repository;
	
	@GetMapping("/getAll")
	List<Product> getAll() {
		return repository.findAll();
	}
	
	
	@GetMapping("/getOne/{id}")
	ResponseEntity<ResponseObject> getOne(@PathVariable Long id){
		Optional<Product> foundProduct = repository.findById(id);
		if(foundProduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Da tim thay san pham", "")
					);
		}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject("Fail", "Khong tim thay san pham co id =" + id, ""));
			}
					
	}
	
	
	@PostMapping("/add")
	ResponseEntity<ResponseObject> addProduct(@RequestBody Product newProduct) {
		List<Product> check = repository.findByName(newProduct.getProductName().trim());
		if(check.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Fail", "Bi trung san pham co ten la =" + newProduct.getProductName()
							, ""));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Them thanh cong", repository.save(newProduct))
					);
		}
	}


	@PutMapping("/edit")
    ResponseEntity<ResponseObject> editProduct(@PathVariable Long id, @RequestBody Product newProduct) {
		Product editProduct = repository.findById(id).map(product -> {
			product.setproductName(newProduct.getProductName());
			product.setYear(newProduct.getYear());
			product.setPrice(newProduct.getPrice());
			product.setDesc(newProduct.getDesc());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("Success", "Cap nhat thanh cong", editProduct)
				);
	}
	
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
		boolean check = repository.existsById(id);
		if(check) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Xoa thanh cong", "")
					);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Fail", "Khong tim thay san pham co id: " + id, "")
					);
		}
	}
	
}
