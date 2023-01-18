package se.callista.cadec.loom.product;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import se.callista.cadec.loom.product.annotation.SpringBootIntegrationTest;
import se.callista.cadec.loom.product.persistence.PostgresqlTestContainer;

@Testcontainers
@SpringBootIntegrationTest
class ProductApplicationTests {

  @Container
  private static final PostgresqlTestContainer POSTGRESQL_CONTAINER = PostgresqlTestContainer.getInstance();

  @Test
  void contextLoads() {
  }

}
