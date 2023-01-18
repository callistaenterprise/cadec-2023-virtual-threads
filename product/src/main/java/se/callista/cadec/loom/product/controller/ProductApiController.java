package se.callista.cadec.loom.product.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.callista.cadec.loom.product.model.ProductValue;
import se.callista.cadec.loom.product.services.ProductService;

@RestController
@RequestMapping("/")
public class ProductApiController {

  private final ProductService productService;

  @Autowired
  public ProductApiController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = "/products/{productId}", produces = {"application/json"})
  public ResponseEntity<ProductValue> getProduct(@PathVariable("productId") long productId) {
    try {
      ProductValue branch = productService.getProduct(productId);
      return new ResponseEntity<>(branch, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

}
