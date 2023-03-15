package com.cg.repository;

import com.cg.model.entity.Category;
import com.cg.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

}