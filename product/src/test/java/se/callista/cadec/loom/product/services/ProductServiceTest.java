package se.callista.cadec.loom.product.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.database.rider.core.api.dataset.DataSet;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.callista.cadec.loom.inventory.model.InventoryValue;
import se.callista.cadec.loom.product.annotation.SpringBootDbIntegrationTest;
import se.callista.cadec.loom.product.model.ProductValue;
import se.callista.cadec.loom.product.multitenancy.util.TenantContext;
import se.callista.cadec.loom.product.persistence.PostgresqlTestContainer;

@Testcontainers
@SpringBootDbIntegrationTest
public class ProductServiceTest {

  @Container
  private static final PostgresqlTestContainer POSTGRESQL_CONTAINER = PostgresqlTestContainer.getInstance();

  @Autowired
  private ProductService productService;

  @MockBean
  private InventoryService inventoryService;

  @Test
  @DataSet(value = {"products.yml"})
  public void getProductForTenant1() {

    when(inventoryService.getInventory(anyString()))
        .thenReturn(InventoryValue.builder()
            .stock(1L)
            .build());

    TenantContext.setTenantId("tenant1");
    ProductValue product = productService.getProduct(1);
    assertThat(product.getName()).isEqualTo("Product 1");
    assertThat(product.getInventory()).isEqualTo(1);
    TenantContext.clear();

    verify(inventoryService, only()).getInventory("product1");

  }

  @Test
  @DataSet(value = {"products.yml"})
  public void getProductForTenant2() {

    TenantContext.setTenantId("tenant2");
    assertThrows(EntityNotFoundException.class, () -> productService.getProduct(1));
    TenantContext.clear();

  }

}
