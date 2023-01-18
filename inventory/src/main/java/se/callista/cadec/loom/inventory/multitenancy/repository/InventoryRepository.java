package se.callista.cadec.loom.inventory.multitenancy.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import se.callista.cadec.loom.inventory.domain.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, String> {

  Optional<Inventory> findBySku(String sku);

}