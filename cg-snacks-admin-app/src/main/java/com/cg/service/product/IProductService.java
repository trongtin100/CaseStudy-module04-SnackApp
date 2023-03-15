package com.cg.service.product;

import com.cg.model.entity.Category;
import com.cg.model.entity.Product;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Page<Product> findPaginated(Pageable pageable);
    // phương thức của giỏ hàng
//    Iterable<Product> findAll();
//    Optional<Product> findById(Long id);
}
