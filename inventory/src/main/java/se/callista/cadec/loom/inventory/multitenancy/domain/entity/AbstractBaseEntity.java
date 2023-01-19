package se.callista.cadec.loom.inventory.multitenancy.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TenantId;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractBaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Size(max = 30)
  @Column(name = "tenant_id")
  @TenantId
  private String tenantId;

  public AbstractBaseEntity(String tenantId) {
    this.tenantId = tenantId;
  }

}
