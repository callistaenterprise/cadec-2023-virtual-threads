package se.callista.cadec.loom.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.callista.cadec.loom.product.multitenancy.domain.entity.AbstractBaseEntity;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractBaseEntity {

  @Id
  @Column(name = "id", unique = true, nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long productId;
  @Version
  @Column(name = "version", nullable = false, columnDefinition = "int default 0")
  protected Integer version;
  @Column(name = "name", length = 255, nullable = false)
  @NotNull
  @Size(max = 255)
  private String name;

  @NotNull
  @Size(max = 255)
  @JsonProperty("sku")
  private String sku;

  @Builder
  public Product(Long productId, String name, String sku, Integer version, String tenantId) {
    super(tenantId);
    this.productId = productId;
    this.name = name;
    this.sku = sku;
    this.version = version;
  }

}
