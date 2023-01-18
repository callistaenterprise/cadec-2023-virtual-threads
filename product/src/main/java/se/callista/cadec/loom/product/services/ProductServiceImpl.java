package se.callista.cadec.loom.product.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.callista.cadec.loom.inventory.model.InventoryValue;
import se.callista.cadec.loom.product.domain.entity.Product;
import se.callista.cadec.loom.product.model.ProductValue;
import se.callista.cadec.loom.product.repository.ProductRepository;

@RequiredArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final InventoryService inventoryService;

  @Override
  public ProductValue getProduct(long productId) {
    Product product = productRepository.findByProductId(productId);
    if (product != null) {
      InventoryValue inventoryValue = inventoryService.getInventory(product.getSku());
      return ProductValue.fromEntity(product, inventoryValue.getStock());
    } else {
      throw new EntityNotFoundException("Product " + productId + " not found");
    }
  }

}
