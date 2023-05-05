package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Product;

public interface Repository extends JpaRepository<Product, Long> {

	@Query
	List<Product> findByName(String productName);
}