package se.callista.cadec.loom.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import se.callista.cadec.loom.product.domain.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

  @Transactional(readOnly = true)
  Product findByProductId(Long id);
}