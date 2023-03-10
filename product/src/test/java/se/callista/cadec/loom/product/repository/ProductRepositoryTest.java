package se.callista.cadec.loom.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.core.api.dataset.DataSet;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.callista.cadec.loom.product.annotation.SpringBootDbIntegrationTest;
import se.callista.cadec.loom.product.domain.entity.Product;
import se.callista.cadec.loom.product.multitenancy.util.TenantContext;
import se.callista.cadec.loom.product.persistence.PostgresqlTestContainer;

@Testcontainers
@SpringBootDbIntegrationTest
class ProductRepositoryTest {

  @Container
  private static final PostgresqlTestContainer POSTGRESQL_CONTAINER = PostgresqlTestContainer.getInstance();

  @Autowired
  private ProductRepository productRepository;

  @Test
  @DataSet(value = {"products.yml"})
  public void findByIdForTenant1() {

    TenantContext.setTenantId("tenant1");
    Optional<Product> product = productRepository.findById(1L);
    assertThat(product).isPresent();
    assertThat(product.get().getName()).isEqualTo("Product 1");
    TenantContext.clear();

  }

  @Test
  @DataSet(value = {"products.yml"})
  public void findByIdForTenant2() {

    TenantContext.setTenantId("tenant2");
    Assertions.assertThat(productRepository.findById(1L)).isNotPresent();
    TenantContext.clear();

  }

}